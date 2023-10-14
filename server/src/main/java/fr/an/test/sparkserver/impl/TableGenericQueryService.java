package fr.an.test.sparkserver.impl;

import fr.an.test.sparkserver.expr.*;
import fr.an.test.sparkserver.metadata.EvalCtx;
import fr.an.test.sparkserver.metadata.TableInfo;
import fr.an.test.sparkserver.rest.dto.QueryRequestDTO;
import fr.an.test.sparkserver.rest.dto.expr.ExprDTO;
import fr.an.test.sparkserver.rest.dto.generic.RowDTO;
import fr.an.test.sparkserver.rest.dto.generic.TableInfoDTO;
import fr.an.test.sparkserver.utils.LsUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.regex.Pattern;

@Slf4j
public class TableGenericQueryService<T> extends AbstractDbService {

    protected final TableInfo<T> tableInfo;

    protected final Dataset<Row> tableDataset;

    protected final Encoder<T> sparkEncoder;

    //---------------------------------------------------------------------------------------------

    public TableGenericQueryService(TableInfo<T> tableInfo, Dataset<Row> tableDataset) {
        this.tableInfo = tableInfo;
        this.tableDataset = tableDataset;
        this.sparkEncoder = Encoders.bean(tableInfo.objectClass);
    }

    //---------------------------------------------------------------------------------------------

    public TableInfoDTO tableInfo() {
        return tableInfo.toDTO();
    }

    public List<T> findAllDtos() {
        return toDtos(tableDataset);
    }

    public List<T> firstDtos(int limit) {
        return toDtos(tableDataset.limit(limit));
    }

    protected List<T> toDtos(Dataset<Row> ds) {
        return ds.as(sparkEncoder).collectAsList();
    }

    public List<RowDTO> query(QueryRequestDTO req) {
        Class<T> objClass = null; // TODO
        List<BiFunction<T,EvalCtx,Object>> evalFuncs =
                LsUtils.map(req.select, x -> resolveExprObjEvalFunction(objClass, x.expr));
        Dataset<Row> ds = tableDataset;
        if (req.limit != 0) {
            ds = ds.limit(req.limit);
        }
        // can not call Dataset.map() .. java.io.NotSerializableException
        val dtos = toDtos(ds);
        EvalCtx evalCtx = new EvalCtx();
        return LsUtils.map(dtos, x -> toRowDTO(x, evalFuncs, evalCtx));
    }


    protected BiFunction<T,EvalCtx,Object> resolveExprObjEvalFunction(
            Class<T> objClass,
            ExprDTO exprDto) {
        SimpleExpr expr = ExprDTOConverter.dtoToSimpleExpr(exprDto);
        // tableInfo.resolve();
        val visitor = new SimpleExprVisitor2<BiFunction<T,EvalCtx,Object>,Void>() {
            @Override
            public BiFunction<T, EvalCtx, Object> caseLiteral(SimpleExpr.LiteralExpr expr, Void unused) {
                return (obj,ctx) -> expr.value;
            }

            @Override
            public BiFunction<T, EvalCtx, Object> caseFieldAccess(SimpleExpr.FieldAccessExpr expr, Void unused) {
                Function<T, ?> objGetter = tableInfo.resolve(expr.field);
                return (obj,ctx) -> objGetter.apply(obj);
            }

            @Override
            public BiFunction<T, EvalCtx, Object> caseRefByIdLookup(SimpleExpr.RefByIdLookupExpr expr, Void unused) {
                BiFunction<T, EvalCtx, Object> idValueEvalFunc = expr.idExpr.accept(this, null);
                // TODO get or register Map<Integer,Object> objByIdMap  for namespace...
                return (obj,ctx) -> {
                    int idValue = (Integer) idValueEvalFunc.apply(obj, ctx);
                    Map<Integer,Object> objByIdMap = ctx.getProp(expr.namespace);
                    if (objByIdMap == null) {
                        objByIdMap = new HashMap<Integer,Object>();
                        // TODO need to perform "findAll", then put in map for corresponding object ids

                        throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
                        // ctx.putProp(expr.namespace, objByIdMap);
                    }
                    return objByIdMap.get(idValue);
                };

            }

            @Override
            public BiFunction<T, EvalCtx, Object> caseGroupAccumulator(SimpleExpr.GroupAccumulatorExpr expr, Void unused) {
                throw new UnsupportedOperationException("NOT IMPLEMENTED YET");
//                return null;
            }

            @Override
            public BiFunction<T, EvalCtx, Object> caseBinaryOp(SimpleExpr.BinaryOpExpr expr, Void param) {
                BiFunction<T, EvalCtx, Object> leftEvalFunc = expr.left.accept(this, param);
                BiFunction<T, EvalCtx, Object> rightEvalFunc = expr.right.accept(this, param);
                BiFunction<Object,Object,Object> opFunction;
                switch(expr.op) {
                    case "=": case "!=":
                        case "+": case "-": case "*": case "/":
                        case "<": case "<=": case ">": case ">=":
                        // coerce types, example: "int", "long" -> "long", "long"
                        SimpleBinaryOperator simpleOp = SimpleBinaryOperators.forName(expr.op);
                        opFunction = (left,right) -> {
                            if (left == null || right == null) {
                                return null; // should not occur?
                            }
                            if (left instanceof Integer) { // TODO type could be resolved once at "prepare"(=compile) time
                                int l = (Integer) left;
                                if (right instanceof Integer) {
                                    int r = (Integer) right;
                                    return simpleOp.evalInt(l, r);
                                } else if (right instanceof Long) {
                                    long  r = (Long) right;
                                    return simpleOp.evalLong((long) l, r);
                                } else if (right instanceof Double) {
                                    double r = (Double) right;
                                    return simpleOp.evalDouble((double) l, r);
                                } else {
                                    throw new UnsupportedOperationException();
                                }
                            } else if (left instanceof Long) {
                                long l = (Long) left;
                                if (right instanceof Integer) {
                                    int r = (Integer) right;
                                    return simpleOp.evalLong(l, (long) r);
                                } else if (right instanceof Long) {
                                    long r = (Long) right;
                                    return simpleOp.evalLong(l, r);
                                } else if (right instanceof Double) {
                                    double r = (Double) right;
                                    return simpleOp.evalDouble((double) l, r);
                                } else {
                                    throw new UnsupportedOperationException();
                                }
                            } else if (left instanceof String) {
                                String l = (String) left;
                                if (right instanceof String) {
                                    String r = (String) right;
                                    return simpleOp.evalString(l, r);
                                } else {
                                    throw new UnsupportedOperationException();
                                }
                            } else {
                                throw new UnsupportedOperationException();
                            }
                        };
                        break;
                    case "like": case "~":
                        opFunction = (BiFunction) likeExprToEvalFunction(expr);
                        break;
                    default:
                        // should not occur
                        throw new IllegalArgumentException("unrecognized op '" + expr.op + "'");
                }
                return (obj,ctx) -> {
                    val left = leftEvalFunc.apply(obj, ctx);
                    val right = rightEvalFunc.apply(obj, ctx);
                    return opFunction.apply(left, right);
                };
            }
        };
        return expr.accept(visitor, null);
    }

    @NotNull
    private static BiFunction<Object, Object, Boolean> likeExprToEvalFunction(SimpleExpr.BinaryOpExpr expr) {
        BiFunction<Object, Object, Boolean> opFunction;
        // right should always be LiteralExprDTO ?
        if (!(expr.right instanceof SimpleExpr.LiteralExpr)) {
            throw new IllegalArgumentException("binaryOpExpr with 'like' should use literal right expr");
        }
        String right = (String) ((SimpleExpr.LiteralExpr) expr.right).value;
        if (right.startsWith("%") && right.endsWith("%")) {
            opFunction = (left,r) -> {
                if (left == null) {
                    return null;
                }
                if (left instanceof String) {
                    String leftStr = (String) left;
                    return leftStr.indexOf(right) != -1;
                } else {
                    return null;
                }
            };
        } else if (right.endsWith("%")) {
            opFunction = (left,r) -> {
                if (left == null) {
                    return null;
                }
                if (left instanceof String) {
                    String leftStr = (String) left;
                    return leftStr.startsWith(right);
                } else {
                    return null;
                }
            };
        } else if (right.startsWith("%")) {
            opFunction = (left,r) -> {
                if (left == null) {
                    return null;
                }
                if (left instanceof String) {
                    String leftStr = (String) left;
                    return leftStr.endsWith(right);
                } else {
                    return null;
                }
            };
        } else {
            // transform to regexp
            String rightRegexpText = right.replace("%", ".*")
                    .replace("?", "."); // more replace ??
            val rightPattern = Pattern.compile(rightRegexpText);
            opFunction = (left,r) -> {
                if (left == null) {
                    return null;
                }
                if (left instanceof String) {
                    String leftStr = (String) left;
                    return rightPattern.matcher(leftStr).matches();
                } else {
                    return false;
                }
            };
        }
        return opFunction;
    }


}
