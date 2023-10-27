package fr.an.test.sparkserver.impl;

import com.google.common.collect.ImmutableMap;
import fr.an.test.sparkserver.impl.specific.*;
import fr.an.test.sparkserver.rest.dto.QueryRequestDTO;
import fr.an.test.sparkserver.rest.dto.QuerySimpleTableColumnsParamsDTO;
import fr.an.test.sparkserver.rest.dto.generic.RowDTO;
import fr.an.test.sparkserver.rest.dto.generic.TableInfoDTO;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispatchGenericQueryService extends AbstractDbService {
    private final ImmutableMap<String,TableGenericQueryService<?>> tableQueryServices;

    public DispatchGenericQueryService(
            UserQueryService userQueryService,
            EventQueryService eventQueryService,
            CategoryQueryService categoryQueryService,
            DateQueryService dateQueryService,
            VenueQueryService venueQueryService,
            ListingQueryService listingQueryService,
            SalesQueryService salesQueryService) {
        this.tableQueryServices = ImmutableMap.<String,TableGenericQueryService<?>>builder()
                .put("User", userQueryService)
                .put("Event", eventQueryService)
                .put("Category", categoryQueryService)
                .put("Date", dateQueryService)
                .put("Venue", venueQueryService)
                .put("Listing", listingQueryService)
                .put("Sales", salesQueryService)
                .build();
    }

    //---------------------------------------------------------------------------------------------

    public TableInfoDTO dispatchTableInfo(String tableName) {
        TableGenericQueryService<?> target = resolveTableQueryService(tableName);
        return target.tableInfo();
    }

    public List<Object> dispatchFindAllDtos(String tableName) {
        TableGenericQueryService<?> target = resolveTableQueryService(tableName);
        return (List<Object>) target.findAllDtos();
    }

    public List<Object> dispatchFindFirstDtos(String tableName, int limit) {
        TableGenericQueryService<?> target = resolveTableQueryService(tableName);
        return (List<Object>) target.firstDtos(limit);
    }

    public List<RowDTO> dispatchQuerySimpleCols(String tableName, QuerySimpleTableColumnsParamsDTO req) {
        TableGenericQueryService<?> target = resolveTableQueryService(tableName);
        return target.querySimpleCols(req);
    }

    private TableGenericQueryService<?> resolveTableQueryService(String tableName) {
        val res = tableQueryServices.get(tableName);
        if (res == null) {
            throw new IllegalArgumentException("Table not found by name '" + tableName + "'"
                    + ", expecting one of {User,Event,Category,Date,Venue,Listing,Sales}");
        }
        return res;
    }

}
