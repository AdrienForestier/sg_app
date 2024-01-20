import { Injectable } from '@angular/core';
import {
  CategoryRestService,
  DateRestService,
  EventRestService,
  ListingRestService,
  SalesDTO,
  SalesRestService,
  TableInfoDTO, UserRestService, VenueRestService
} from "../ext";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RestFacadeService {

  constructor(private salesRestService: SalesRestService,
              private dateRestService: DateRestService,
              private eventRestService: EventRestService,
              private listingRestService: ListingRestService,
              private categoryRestService: CategoryRestService,
              private userRestService: UserRestService,
              private venueRestService: VenueRestService
  ) { }

  getTableInfoSales(): Observable<TableInfoDTO> {
    return this.salesRestService.tableInfo2();
  }

  getTableInfoDate(): Observable<TableInfoDTO> {
    return this.dateRestService.tableInfo5();
  }

  getTableInfoEvent(): Observable<TableInfoDTO> {
    return this.eventRestService.tableInfo4();
  }

  getTableInfoListing(): Observable<TableInfoDTO> {
    return this.listingRestService.tableInfo3();
  }

  getTableInfoCategory(): Observable<TableInfoDTO> {
    return this.categoryRestService.tableInfo6();
  }

  getTableInfoUser(): Observable<TableInfoDTO> {
    return this.userRestService.tableInfo1();
  }

  getTableInfoVenue(): Observable<TableInfoDTO> {
    return this.venueRestService.tableInfo();
  }


  queryFirstSales(): Observable<SalesDTO[]> {
    return this.salesRestService.first2();
  }

}
