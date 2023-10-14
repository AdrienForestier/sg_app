package fr.an.test.sparkserver.sqlexpr;

import fr.an.test.sparkserver.rest.dto.expr.ParseRequestDTO;
import fr.an.test.sparkserver.rest.dto.expr.ParseResponseDTO;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.catalyst.expressions.Expression;
import org.apache.spark.sql.catalyst.parser.ParseException;
import org.apache.spark.sql.catalyst.parser.ParserInterface;
import org.springframework.stereotype.Service;
import scala.Function2;

@Service
public class SqlExprParserService {

    private final SparkSession sparkSession;
    private final ParserInterface sqlParser;

    public SqlExprParserService(SparkSession sparkSession) {
        this.sparkSession = sparkSession;
        this.sqlParser = sparkSession.sessionState().sqlParser();
    }

    public Expression parse(String exprText) {
        try {
            return sqlParser.parseExpression(exprText);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse expression '" + exprText + "'", e);
        }
    }

    public String toSql(Expression expr) {
        return expr.sql();
    }

    public ParseResponseDTO parseExprRequest(ParseRequestDTO req) {
        Expression expr = parse(req.text);

        // expr.resolved() => false !
        // sparkSession.sessionState().analyzer().resolver() ???

        String sql = toSql(expr);
        return new ParseResponseDTO(sql);
    }
}
