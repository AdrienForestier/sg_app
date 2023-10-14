package fr.an.test.sparkserver.expr;

public abstract class SimpleExprVisitor2<TRes,TParam> {

    public abstract TRes caseLiteral(SimpleExpr.LiteralExpr expr, TParam param);

    public abstract TRes caseFieldAccess(SimpleExpr.FieldAccessExpr expr, TParam param);

    public abstract TRes caseRefByIdLookup(SimpleExpr.RefByIdLookupExpr expr, TParam param);

    public abstract TRes caseGroupAccumulator(SimpleExpr.GroupAccumulatorExpr expr, TParam param);

    public abstract TRes caseBinaryOp(SimpleExpr.BinaryOpExpr expr, TParam param);

}
