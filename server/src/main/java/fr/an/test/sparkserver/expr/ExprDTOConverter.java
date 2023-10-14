package fr.an.test.sparkserver.expr;

import fr.an.test.sparkserver.rest.dto.expr.ExprDTO;
import lombok.val;

import java.util.Objects;

public class ExprDTOConverter {

    public static SimpleExpr dtoToSimpleExpr(ExprDTO expr) {
        val dtoConverter = new ExprDTO.SimpleExprDTOVisitor<SimpleExpr,Void>() {
            protected SimpleExpr toExprOrNull(ExprDTO exp) {
                return (expr != null)? toExpr(expr) : null;
            }
            protected SimpleExpr toExpr(ExprDTO exp) {
                return expr.accept(this, null);
            }

            @Override
            public SimpleExpr caseLiteral(ExprDTO.LiteralExprDTO expr, Void unused) {
                if (expr.intValue != null) {
                    return new SimpleExpr.LiteralExpr(expr.intValue);
                }else if (expr.doubleValue != null) {
                    return new SimpleExpr.LiteralExpr(expr.intValue);
                } else if (expr.strValue != null) {
                    return new SimpleExpr.LiteralExpr(expr.strValue);
                } else {
                    // should not occur
                    return new SimpleExpr.LiteralExpr(null);
                }
            }

            @Override
            public SimpleExpr caseFieldAccess(ExprDTO.FieldAccessExprDTO expr, Void unused) {
                val objExpr = toExpr(expr);
                return new SimpleExpr.FieldAccessExpr(objExpr, expr.field);
            }

            @Override
            public SimpleExpr caseRefByIdLookup(ExprDTO.RefByIdLookupExprDTO expr, Void unused) {
                val idExpr = toExpr(expr.idExpr);
                return new SimpleExpr.RefByIdLookupExpr(expr.namespace, idExpr);
            }

            @Override
            public SimpleExpr caseBinaryOp(ExprDTO.BinaryOpExprDTO expr, Void unused) {
                val leftExpr = toExpr(expr.left);
                val rightExpr = toExpr(expr.right);
                val op = Objects.requireNonNull(expr.op);
                return new SimpleExpr.BinaryOpExpr(leftExpr, op, rightExpr);
            }
        };
        return expr.accept(dtoConverter, null);
    }

}
