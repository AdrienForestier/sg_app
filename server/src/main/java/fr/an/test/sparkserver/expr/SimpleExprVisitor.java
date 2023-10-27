package fr.an.test.sparkserver.expr;

public abstract class SimpleExprVisitor {

    public abstract void caseLiteral(SimpleExpr.LiteralExpr expr);

    public abstract void caseFieldAccess(SimpleExpr.FieldAccessExpr expr);

    public abstract void caseRefByIdLookup(SimpleExpr.RefByIdLookupExpr expr);

    public abstract void caseGroupAccumulator(SimpleExpr.GroupAccumulatorExpr expr);

    public abstract void caseBinaryOp(SimpleExpr.BinaryOpExpr expr);

    public abstract void caseUnaryOp(SimpleExpr.UnaryOpExpr expr);

    public abstract void caseApplyFunc(SimpleExpr.ApplyFuncExpr expr);

}
