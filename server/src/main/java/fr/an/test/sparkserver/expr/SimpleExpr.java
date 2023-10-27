package fr.an.test.sparkserver.expr;

import lombok.AllArgsConstructor;

import java.util.List;

public abstract class SimpleExpr {

    // TOADD
    // protected Class<T> resolvedType;

    public abstract void accept(SimpleExprVisitor visitor);

    public abstract <TRes,TParam> TRes accept(SimpleExprVisitor2<TRes,TParam> visitor, TParam param);

    @AllArgsConstructor
    public static class LiteralExpr extends SimpleExpr {
        public Object value;

        @Override
        public void accept(SimpleExprVisitor visitor) {
            visitor.caseLiteral(this);
        }

        @Override
        public <TRes,TParam> TRes accept(SimpleExprVisitor2<TRes,TParam> visitor, TParam param) {
            return visitor.caseLiteral(this, param);
        }

    }

    @AllArgsConstructor
    public static class FieldAccessExpr extends SimpleExpr {
        public SimpleExpr objExpr;
        public String field;

        @Override
        public void accept(SimpleExprVisitor visitor) {
            visitor.caseFieldAccess(this);
        }

        @Override
        public <TRes, TParam> TRes accept(SimpleExprVisitor2<TRes, TParam> visitor, TParam param) {
            return visitor.caseFieldAccess(this, param);
        }
    }

    @AllArgsConstructor
    public static class RefByIdLookupExpr extends SimpleExpr {
        public String namespace;
        public SimpleExpr idExpr;

        @Override
        public void accept(SimpleExprVisitor visitor) {
            visitor.caseRefByIdLookup(this);
        }

        @Override
        public <TRes,TParam> TRes accept(SimpleExprVisitor2<TRes,TParam> visitor, TParam param) {
            return visitor.caseRefByIdLookup(this, param);
        }

    }

    @AllArgsConstructor
    public static class GroupAccumulatorExpr extends SimpleExpr {
        public String accumulatorFunction; // average, min, max, count, first, last, distinctSet, ...
        public SimpleExpr expr;

        @Override
        public void accept(SimpleExprVisitor visitor) {
            visitor.caseGroupAccumulator(this);
        }

        @Override
        public <TRes,TParam> TRes accept(SimpleExprVisitor2<TRes,TParam> visitor, TParam param) {
            return visitor.caseGroupAccumulator(this, param);
        }

    }


    public abstract class AbstractExprBinaryOperator {
    }

    @AllArgsConstructor
    public static class BinaryOpExpr extends SimpleExpr {
        public SimpleExpr left;
        // public EnumBinaryOp op;
        public String op;
        public SimpleExpr right;

        @Override
        public void accept(SimpleExprVisitor visitor) {
            visitor.caseBinaryOp(this);
        }

        @Override
        public <TRes,TParam> TRes accept(SimpleExprVisitor2<TRes,TParam> visitor, TParam param) {
            return visitor.caseBinaryOp(this, param);
        }

    }

    @AllArgsConstructor
    public static class UnaryOpExpr extends SimpleExpr {
        public String op;
        public SimpleExpr expr;

        @Override
        public void accept(SimpleExprVisitor visitor) {
            visitor.caseUnaryOp(this);
        }

        @Override
        public <TRes,TParam> TRes accept(SimpleExprVisitor2<TRes,TParam> visitor, TParam param) {
            return visitor.caseUnaryOp(this, param);
        }

    }

    @AllArgsConstructor
    public static class ApplyFuncExpr extends SimpleExpr {
        public String function;
        public List<SimpleExpr> args;

        @Override
        public void accept(SimpleExprVisitor visitor) {
            visitor.caseApplyFunc(this);
        }

        @Override
        public <TRes,TParam> TRes accept(SimpleExprVisitor2<TRes,TParam> visitor, TParam param) {
            return visitor.caseApplyFunc(this, param);
        }

    }

}
