package fr.an.test.sparkserver.rest.dto;

import fr.an.test.sparkserver.rest.dto.expr.AliasedExprTextDTO;
import fr.an.test.sparkserver.rest.dto.expr.ExprDTO;
import fr.an.test.sparkserver.rest.dto.expr.JoinDTO;
import fr.an.test.sparkserver.rest.dto.expr.OrderByDTO;

import java.util.List;

public class QueryRequestDTO {

    public List<AliasedExprTextDTO> select;

    public String from;

    public List<JoinDTO> joins;

    public List<ExprDTO> whereConditions;

    public List<OrderByDTO> orderBy;

    public List<AliasedExprTextDTO> groupBy;

    public List<ExprDTO> havingConditions;

    public int limit;

}
