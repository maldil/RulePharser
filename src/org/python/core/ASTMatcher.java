package org.python.core;
import org.python.antlr.ast.*;
import org.python.antlr.base.*;
import org.python.core.PyUnicode;
import java.util.List;
import java.util.Iterator;

public class ASTMatcher {
    public ASTMatcher() {

    }

    public boolean match(PyUnicode node, Object other) {
        if (!(other instanceof PyUnicode)) {
            return false;
        } else {
            PyUnicode o = (PyUnicode) other;
            return o.getString().equals(node.getString());
        }
    }

    public boolean match(org.python.antlr.ast.While node, Object other) {
        if (!(other instanceof org.python.antlr.ast.While)) {
            return false;
        } else {
            org.python.antlr.ast.While o = (org.python.antlr.ast.While) other;
            return this.safeSubtreeListMatch(node.getInternalOrelse(), o.getInternalOrelse()) &&
                    this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody()) &&
                    this.safeSubtreeMatch(node.getInternalTest(), o.getInternalTest());
        }
    }

    public boolean match(alias node, Object other) {
        if (!(other instanceof alias)) {
            return false;
        } else {
            alias o = (alias) other;
            return this.safeSubtreeListMatch(node.getInternalNameNodes(), o.getInternalNameNodes()) &&
                    this.safeSubtreeMatch(node.getInternalAsnameNode(), o.getInternalAsnameNode()) ;
        }
    }

    public boolean match(arg node, Object other) {
        if (!(other instanceof arg)) {
            return false;
        } else {
            arg o = (arg) other;
            return this.safeSubtreeMatch(node.getInternalAnnotation(), o.getInternalAnnotation()) &&
                    o.getInternalArg().equals(node.getInternalArg());
        }
    }

    public boolean match(arguments node, Object other) {
        if (!(other instanceof arguments)) {
            return false;
        } else {
            arguments o = (arguments) other;
            return this.safeSubtreeListMatch(node.getInternalArgs(), o.getInternalArgs()) &&
                    this.safeSubtreeListMatch(node.getInternalDefaults(), o.getInternalDefaults()) &&
                    this.safeSubtreeListMatch(node.getInternalKw_defaults(), o.getInternalKw_defaults()) &&
                    this.safeSubtreeListMatch(node.getInternalKwonlyargs(), o.getInternalKwonlyargs()) &&
                    this.safeSubtreeMatch(node.getInternalVararg(), o.getInternalVararg()) &&
                    this.safeSubtreeMatch(node.getInternalKwarg(), o.getInternalKwarg());
        }
    }

    public boolean match(Assert node, Object other) {
        if (!(other instanceof Assert)) {
            return false;
        } else {
            Assert o = (Assert) other;
            return this.safeSubtreeMatch(node.getInternalMsg(), o.getInternalMsg()) &&
                    this.safeSubtreeMatch(node.getInternalTest(), o.getInternalTest());
        }
    }

    public boolean match(Assign node, Object other) {
        if (!(other instanceof Assign)) {
            return false;
        } else {
            Assign o = (Assign) other;
            return this.safeSubtreeListMatch(node.getInternalTargets(), o.getInternalTargets()) &&
                    this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue());
        }
    }

    public boolean match(AsyncFor node, Object other) {
        if (!(other instanceof AsyncFor)) {
            return false;
        } else {
            AsyncFor o = (AsyncFor) other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody()) &&
                    this.safeSubtreeListMatch(node.getInternalOrelse(), o.getInternalOrelse()) &&
                    this.safeSubtreeMatch(node.getInternalIter(), o.getInternalIter()) &&
                    this.safeSubtreeMatch(node.getInternalTarget(), o.getInternalTarget());
        }
    }

    public boolean match(AsyncFunctionDef node, Object other) {
        if (!(other instanceof AsyncFunctionDef)) {
            return false;
        } else {
            AsyncFunctionDef o = (AsyncFunctionDef) other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody()) &&
                    this.safeSubtreeMatch(node.getInternalArgs(), o.getInternalArgs()) &&
                    this.safeSubtreeListMatch(node.getInternalDecorator_list(), o.getInternalDecorator_list()) &&
                    this.safeSubtreeMatch(node.getInternalNameNode(), o.getInternalNameNode()) &&
                    this.safeSubtreeMatch(node.getInternalReturnNode(), o.getInternalReturnNode()) &&
                    this.safeSubtreeMatch(node.getInternalReturns(), o.getInternalReturns())
                    && this.safeEquals(node.getInternalName(),o.getInternalName());
        }
    }


    public boolean match(AsyncWith node, Object other) {
        if (!(other instanceof AsyncWith)) {
            return false;
        } else {
            AsyncWith o = (AsyncWith) other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody()) &&
                    this.safeSubtreeListMatch(node.getInternalItems(), o.getInternalItems());
        }
    }

    public boolean match(Attribute node, Object other) {
        if (!(other instanceof Attribute)) {
            return false;
        } else {
            Attribute o = (Attribute) other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue()) &&
                    this.safeSubtreeMatch(node.getInternalAttrName(), o.getInternalAttrName());
        }
    }

    public boolean match(AugAssign node, Object other) {
        if (!(other instanceof AugAssign)) {
            return false;
        } else {
            AugAssign o = (AugAssign) other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue()) &&
                    this.safeSubtreeMatch(node.getInternalTarget(), o.getInternalTarget()) &&
                    this.safeEquals(node.getInternalOp(),o.getInternalOp());
        }
    }

    public boolean match(Await node, Object other) {
        if (!(other instanceof Await)) {
            return false;
        } else {
            Await o = (Await) other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue()) ;
        }
    }

    public boolean match(BinOp node, Object other) {
        if (!(other instanceof BinOp)) {
            return false;
        } else {
            BinOp o = (BinOp) other;
            return this.safeSubtreeMatch(node.getInternalRight(), o.getInternalRight()) &&
                    this.safeEquals(o.getInternalOp(),node.getInternalOp()) &&
                    this.safeSubtreeMatch(node.getInternalLeft(), o.getInternalLeft()) ;
        }
    }

    public boolean match(BoolOp node, Object other) {
        if (!(other instanceof BoolOp)) {
            return false;
        } else {
            BoolOp o = (BoolOp) other;
            return this.safeSubtreeListMatch(node.getInternalValues(), o.getInternalValues()) &&
                    this.safeEquals(o.getInternalOp(),node.getInternalOp());
        }
    }

    public boolean match(boolopType node, Object other) {
        if (!(other instanceof boolopType)) {
            return false;
        } else {
            boolopType o = (boolopType) other;
            return this.safeEquals(o,node);
        }
    }

    public boolean match(Break node, Object other) {
        if (!(other instanceof Bytes)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean match(Bytes node, Object other) {
        if (!(other instanceof Bytes)) {
            return false;
        } else {
            Bytes o = (Bytes) other;
            return node.getInternalS().equals(o.getInternalS());
        }
    }

    public boolean match(Call node, Object other) {
        if (!(other instanceof Call)) {
            return false;
        } else {
            Call o = (Call) other;
            return this.safeSubtreeListMatch(node.getInternalArgs(), o.getInternalArgs()) &&
                    this.safeSubtreeListMatch(node.getInternalKeywords(), o.getInternalKeywords()) &&
                    this.safeSubtreeMatch(node.getInternalFunc(), o.getInternalFunc());
        }
    }

    public boolean match(ClassDef node, Object other) {
        if (!(other instanceof ClassDef)) {
            return false;
        } else {
            ClassDef o = (ClassDef) other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody()) &&
                    this.safeSubtreeListMatch(node.getInternalBases(), o.getInternalBases()) &&
                    this.safeEquals(node.getInternalName(),o.getInternalName()) &&
                    this.safeSubtreeListMatch(node.getInternalDecorator_list(), o.getInternalDecorator_list()) &&
                    this.safeSubtreeListMatch(node.getInternalKeywords(), o.getInternalKeywords()) &&
                    this.safeSubtreeMatch(node.getInternalNameNode(), o.getInternalNameNode());
        }
    }

    public boolean match(cmpopType node, Object other) {
        if (!(other instanceof cmpopType)) {
            return false;
        } else {
            cmpopType o = (cmpopType) other;
            return this.safeEquals(o,node);
        }
    }

    public boolean match(Compare node, Object other) {
        if (!(other instanceof Compare)) {
            return false;
        } else {
            Compare o = (Compare) other;
            return this.safeSubtreeListMatch(node.getInternalComparators(), o.getInternalComparators()) &&
                    this.safeSubtreeMatch(node.getInternalLeft(), o.getInternalLeft()) &&
                    this.checkEqual(node.getInternalOps(), o.getInternalOps());
        }
    }

    public boolean match(comprehension node, Object other) {
        if (!(other instanceof comprehension)) {
            return false;
        } else {
            comprehension o = (comprehension) other;
            return this.safeSubtreeListMatch(node.getInternalIfs(), o.getInternalIfs()) &&
                    this.safeSubtreeMatch(node.getInternalIter(), o.getInternalIter()) &&
                    this.safeSubtreeMatch(node.getInternalTarget(), o.getInternalTarget());
        }
    }

    public boolean match(Constant node, Object other) {
        if (!(other instanceof Constant)) {
            return false;
        } else {
            Constant o = (Constant) other;
            return this.safeEquals(node.getInternalValue(),o.getInternalValue());
        }
    }

    public boolean match(org.python.antlr.ast.Continue node, Object other) {
        if (!(other instanceof org.python.antlr.ast.Continue)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean match(Delete node, Object other) {
        if (!(other instanceof Delete)) {
            return false;
        } else {
            Delete o = (Delete)other;
            return this.safeSubtreeListMatch(node.getInternalTargets(), o.getInternalTargets());
        }
    }

    public boolean match(Dict node, Object other) {
        if (!(other instanceof Dict)) {
            return false;
        } else {
            Dict o = (Dict)other;
            return this.safeSubtreeListMatch(node.getInternalValues(), o.getInternalValues()) &&
                    this.safeSubtreeListMatch(node.getInternalKeys(), o.getInternalKeys()) ;
        }
    }

    public boolean match(DictComp node, Object other) {
        if (!(other instanceof DictComp)) {
            return false;
        } else {
            DictComp o = (DictComp)other;
            return this.safeSubtreeListMatch(node.getInternalGenerators(), o.getInternalGenerators()) &&
                    this.safeSubtreeMatch(node.getInternalKey(), o.getInternalKey()) &&
                    this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue());
        }
    }

    public boolean match(Ellipsis node, Object other) {
        if (!(other instanceof Ellipsis)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean match(ErrorExpr node, Object other) {
        if (!(other instanceof ErrorMod)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean match(ErrorMod node, Object other) {
        if (!(other instanceof ErrorMod)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean match(ErrorSlice node, Object other) {
        if (!(other instanceof ErrorSlice)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean match(ErrorStmt node, Object other) {
        if (!(other instanceof ErrorStmt)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean match(Expr node, Object other) {
        if (!(other instanceof Expr)) {
            return false;
        } else {
            Expr o = (Expr)other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue());
        }
    }

    public boolean match(expr_contextType node, Object other) {
        if (!(other instanceof expr_contextType)) {
            return false;
        } else {
            expr_contextType o = (expr_contextType)other;
            return this.safeEquals(o,other);
        }
    }

    public boolean match(Expression node, Object other) {
        if (!(other instanceof Expression)) {
            return false;
        } else {
            Expression o = (Expression)other;
            return this.safeSubtreeMatch(node.getInternalBody(), o.getInternalBody());
        }
    }

    public boolean match(ExtSlice node, Object other) {
        if (!(other instanceof ExtSlice)) {
            return false;
        } else {
            ExtSlice o = (ExtSlice)other;
            return this.safeSubtreeListMatch(node.getInternalDims(), o.getInternalDims());
        }
    }

    public boolean match(For node, Object other) {
        if (!(other instanceof For)) {
            return false;
        } else {
            For o = (For)other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody()) &&
                    this.safeSubtreeListMatch(node.getInternalOrelse(), o.getInternalOrelse()) &&
                    this.safeSubtreeMatch(node.getInternalIter(), o.getInternalIter()) &&
                    this.safeSubtreeMatch(node.getInternalTarget(), o.getInternalTarget());
        }
    }

    public boolean match(FormattedValue node, Object other) {
        if (!(other instanceof FormattedValue)) {
            return false;
        } else {
            FormattedValue o = (FormattedValue)other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue()) &&
                    this.safeSubtreeMatch(node.getInternalFormat_spec(), o.getInternalFormat_spec()) &&
                    this.safeEquals(node.getInternalConversion(), o.getInternalConversion());
        }
    }

    public boolean match(FunctionDef node, Object other) {
        if (!(other instanceof FunctionDef)) {
            return false;
        } else {
            FunctionDef o = (FunctionDef)other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody()) &&
                    this.safeSubtreeMatch(node.getInternalArgs(), o.getInternalArgs()) &&
                    this.safeSubtreeListMatch(node.getInternalDecorator_list(), o.getInternalDecorator_list()) &&
                    this.safeSubtreeMatch(node.getInternalNameNode(), o.getInternalNameNode()) &&
                    this.safeSubtreeMatch(node.getInternalReturnNode(), o.getInternalReturnNode()) &&
                    this.safeSubtreeMatch(node.getInternalReturns(), o.getInternalReturns())
                    && this.safeEquals(node.getInternalName(), o.getInternalName());
        }
    }

    public boolean match(GeneratorExp node, Object other) {
        if (!(other instanceof GeneratorExp)) {
            return false;
        } else {
            GeneratorExp o = (GeneratorExp)other;
            return this.safeSubtreeListMatch(node.getInternalGenerators(), o.getInternalGenerators()) &&
                    this.safeSubtreeMatch(node.getInternalElt(), o.getInternalElt());
        }
    }

    public boolean match(Global node, Object other) {
        if (!(other instanceof Global)) {
            return false;
        } else {
            Global o = (Global)other;
            return this.safeSubtreeListMatch(node.getInternalNameNodes(), o.getInternalNameNodes());
        }
    }

    public boolean match(Hole node, Object other) {
        if (!(other instanceof Hole)) {
            return false;
        } else {
            Hole o = (Hole)other;
            return this.safeSubtreeMatch(node.getInternalN(), o.getInternalN());
        }
    }

    public boolean match(If node, Object other) {
        if (!(other instanceof If)) {
            return false;
        } else {
            If o = (If)other;
            return this.safeSubtreeListMatch(node.getInternalOrelse(), o.getInternalOrelse())
                    && this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody())
                    && this.safeSubtreeMatch(node.getInternalTest(), o.getInternalTest());
        }
    }

    public boolean match(IfExp node, Object other) {
        if (!(other instanceof IfExp)) {
            return false;
        } else {
            IfExp o = (IfExp)other;
            return this.safeSubtreeMatch(node.getInternalOrelse(), o.getInternalOrelse())
                    && this.safeSubtreeMatch(node.getInternalBody(), o.getInternalBody())
                    && this.safeSubtreeMatch(node.getInternalTest(), o.getInternalTest());
        }
    }

    public boolean match(Import node, Object other) {
        if (!(other instanceof Import)) {
            return false;
        } else {
            Import o = (Import)other;
            return this.safeSubtreeListMatch(node.getInternalNames(), o.getInternalNames());
        }
    }

    public boolean match(ImportFrom node, Object other) {
        if (!(other instanceof ImportFrom)) {
            return false;
        } else {
            ImportFrom o = (ImportFrom)other;
            return this.safeSubtreeListMatch(node.getInternalModuleNames(), o.getInternalModuleNames())
                    &&  this.safeSubtreeListMatch(node.getInternalNames(), o.getInternalNames())
                    &&  this.safeEquals(node.getInternalModule(), o.getInternalModule())
                    &&  this.safeEquals(node.getInternalLevel(), o.getInternalLevel());
        }
    }

    public boolean match(Index node, Object other) {
        if (!(other instanceof Index)) {
            return false;
        } else {
            Index o = (Index)other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue());
        }
    }

    public boolean match(Interactive node, Object other) {
        if (!(other instanceof Interactive)) {
            return false;
        } else {
            Interactive o = (Interactive)other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody());
        }
    }

    public boolean match(JoinedStr node, Object other) {
        if (!(other instanceof JoinedStr)) {
            return false;
        } else {
            JoinedStr o = (JoinedStr)other;
            return this.safeSubtreeListMatch(node.getInternalValues(), o.getInternalValues());
        }
    }

    public boolean match(keyword node, Object other) {
        if (!(other instanceof keyword)) {
            return false;
        } else {
            keyword o = (keyword)other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue())
                    &&  this.safeEquals(node.getInternalArg() , o.getInternalArg());
        }
    }

    public boolean match(Lambda node, Object other) {
        if (!(other instanceof Lambda)) {
            return false;
        } else {
            Lambda o = (Lambda)other;
            return this.safeSubtreeMatch(node.getInternalBody(), o.getInternalBody())
                    &&  this.safeSubtreeMatch(node.getInternalArgs(), o.getInternalArgs());
        }
    }

    public boolean match(org.python.antlr.ast.List node, Object other) {
        if (!(other instanceof org.python.antlr.ast.List)) {
            return false;
        } else {
            org.python.antlr.ast.List o = (org.python.antlr.ast.List)other;
            return this.safeSubtreeListMatch(node.getInternalElts(), o.getInternalElts());
        }
    }

    public boolean match(ListComp node, Object other) {
        if (!(other instanceof ListComp)) {
            return false;
        } else {
            ListComp o = (ListComp)other;
            return this.safeSubtreeListMatch(node.getInternalGenerators(), o.getInternalGenerators())
                    &&  this.safeSubtreeMatch(node.getInternalElt(), o.getInternalElt());
        }
    }

    public boolean match(org.python.antlr.ast.Module node, Object other) {
        if (!(other instanceof org.python.antlr.ast.Module)) {
            return false;
        } else {
            org.python.antlr.ast.Module o = (org.python.antlr.ast.Module)other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody());
        }
    }

    public boolean match(Nonlocal node, Object other) {
        if (!(other instanceof Nonlocal)) {
            return false;
        } else {
            Nonlocal o = (Nonlocal)other;
            return this.safeSubtreeListMatch(node.getInternalNameNodes(), o.getInternalNameNodes());
        }
    }

    public boolean match(Num node, Object other) {
        if (!(other instanceof Num)) {
            return false;
        } else {
            Num o = (Num)other;
            return this.safeEquals(node.getInternalN(),o.getInternalN());
        }
    }

    public boolean match(operatorType node, Object other) {
        operatorType o = (operatorType)other;
        return this.safeEquals(node,o);
    }

    public boolean match(Pass node, Object other) {
        if (other instanceof Pass) {
            return true;
        }
        return false;
    }

    public boolean match(Raise node, Object other) {
        if (!(other instanceof Raise)) {
            return false;
        } else {
            Raise o = (Raise)other;
            return this.safeSubtreeMatch(node.getInternalCause(), o.getInternalCause())
                    &&  this.safeSubtreeMatch(node.getInternalExc(), o.getInternalExc());
        }
    }

    public boolean match(Set node, Object other) {
        if (!(other instanceof Set)) {
            return false;
        } else {
            Set o = (Set)other;
            return this.safeSubtreeListMatch(node.getInternalElts(), o.getInternalElts());
        }
    }

    public boolean match(SetComp node, Object other) {
        if (!(other instanceof SetComp)) {
            return false;
        } else {
            SetComp o = (SetComp)other;
            return this.safeSubtreeMatch(node.getInternalElt(), o.getInternalElt())
                    && this.safeSubtreeListMatch(node.getInternalGenerators(), o.getInternalGenerators());
        }
    }

    public boolean match(Slice node, Object other) {
        if (!(other instanceof Slice)) {
            return false;
        } else {
            Slice o = (Slice)other;
            return this.safeSubtreeMatch(node.getInternalLower(), o.getInternalLower())
                    && this.safeSubtreeMatch(node.getInternalStep(), o.getInternalStep())
                    && this.safeSubtreeMatch(node.getInternalUpper(), o.getInternalUpper());
        }
    }

    public boolean match(Starred node, Object other) {
        if (!(other instanceof Starred)) {
            return false;
        } else {
            Starred o = (Starred)other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue())
                    && this.safeEquals(node.getInternalCtx(),o.getInternalCtx());
        }
    }

    public boolean match(Str node, Object other) {
        if (!(other instanceof Str)) {
            return false;
        } else {
            Str o = (Str)other;
            return this.safeSubtreeMatch(node.getInternalS(), o.getInternalS());
        }
    }

    public boolean match(String node, Object other) {
        if (!(other instanceof String)) {
            return false;
        } else {
            String o = (String)other;
            return node.equals(o);
        }
    }

    public boolean match(Subscript node, Object other) {
        if (!(other instanceof Subscript)) {
            return false;
        } else {
            Subscript o = (Subscript)other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue())
                    &&  this.safeSubtreeMatch(node.getInternalSlice(), o.getInternalSlice())
                    &&  this.safeEquals(node.getInternalSlice() , o.getInternalSlice());
        }
    }

    public boolean match(Suite node, Object other) {
        if (!(other instanceof Suite)) {
            return false;
        } else {
            Suite o = (Suite)other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody());
        }
    }

    public boolean match(ExceptHandler node, Object other) {
        if (!(other instanceof ExceptHandler)) {
            return false;
        } else {
            ExceptHandler o = (ExceptHandler)other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody()) &&
                    this.safeSubtreeMatch(node.getInternalType(), o.getInternalType()) &&
                    this.safeEquals(node.getInternalName(),o.getInternalName());
        }
    }

    public boolean match(TryExcept node, Object other) {
        if (!(other instanceof TryExcept)) {
            return false;
        } else {
            TryExcept o = (TryExcept)other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody()) &&
                    this.safeSubtreeListMatch(node.getInternalOrelse(), o.getInternalOrelse()) &&
                    this.safeSubtreeListMatch(node.getInternalHandlers(), o.getInternalHandlers());
        }
    }

    public boolean match(TryFinally node, Object other) {
        if (!(other instanceof TryFinally)) {
            return false;
        } else {
            TryFinally o = (TryFinally)other;
            return this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody()) &&
                    this.safeSubtreeListMatch(node.getInternalFinalbody(), o.getInternalFinalbody());
        }
    }

    public boolean match(Tuple node, Object other) {
        if (!(other instanceof Tuple)) {
            return false;
        } else {
            Tuple o = (Tuple)other;
            return this.safeSubtreeListMatch(node.getInternalElts(), o.getInternalElts());
        }
    }

    public boolean match(Name node, Object other) {
        if (!(other instanceof Name)) {
            return false;
        } else {
            Name o = (Name)other;
            return node.getInternalId().equals(o.getInternalId());
        }
    }

    public boolean match(Return node, Object other) {
        if (!(other instanceof Return)) {
            return false;
        } else {
            Return o = (Return)other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue());
        }
    }

    public boolean match(YieldFrom node, Object other) {
        if (!(other instanceof YieldFrom)) {
            return false;
        } else {
            YieldFrom o = (YieldFrom)other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue());
        }
    }

    public boolean match(withitem node, Object other) {
        if (!(other instanceof withitem)) {
            return false;
        } else {
            withitem o = (withitem)other;
            return this.safeSubtreeMatch(node.getInternalOptional_vars(), o.getInternalOptional_vars()) && this.safeSubtreeMatch(node.getInternalContext_expr(), o.getInternalContext_expr());
        }
    }

    public boolean match(With node, Object other) {
        if (!(other instanceof With)) {
            return false;
        } else {
            With o = (With)other;
            return this.safeSubtreeListMatch(node.getInternalItems(), o.getInternalItems()) && this.safeSubtreeListMatch(node.getInternalBody(), o.getInternalBody());
        }
    }

    public boolean match(UnaryOp node, Object other) {
        if (!(other instanceof UnaryOp)) {
            return false;
        } else {
            UnaryOp o = (UnaryOp)other;
            return this.safeSubtreeMatch(node.getInternalOperand(), o.getInternalOperand()) &&
                    this.safeEquals(node.getInternalOp(),o.getInternalOp());
        }
    }

    public boolean match(Yield node, Object other) {
        if (!(other instanceof Yield)) {
            return false;
        } else {
            Yield o = (Yield)other;
            return this.safeSubtreeMatch(node.getInternalValue(), o.getInternalValue());
        }
    }

    public final boolean safeSubtreeMatch(Object node1, Object node2) {
        if (node1 == null && node2 == null) {
            return true;
        } else {
            return node1 != null && node2 != null ? ((PyObject)node1).subtreeMatch(this, node2) : false;
        }
    }

    public final boolean safeSubtreeListMatch(List list1, List list2) {
        int size1 = list1.size();
        int size2 = list2.size();
        if (size1 != size2) {
            return false;
        } else {
            Iterator it1 = list1.iterator();
            Iterator it2 = list2.iterator();

            while(it1.hasNext()) {
                PyObject n1 = (PyObject)it1.next();
                PyObject n2 = (PyObject)it2.next();
                if (n1!=null && n2!=null && !n1.subtreeMatch(this, n2)) {
                    return false;
                }
                else if (n1==null && n2!=null)
                    return false;
                else if (n1!=null && n2==null)
                    return false;
            }

            return true;
        }
    }

    public static boolean checkEqual(List<cmpopType> list1, List<cmpopType> list2) {

        // If the size of the lists is different, they are not equal
        if (list1.size() != list2.size()) {
            return false;
        }

        // Check each element in the lists to see if they are equal
        for (int i = 0; i < list1.size(); i++) {
            cmpopType obj1 = list1.get(i);
            cmpopType obj2 = list2.get(i);

            // If any element is not equal, the lists are not equal
            if (obj1.equals(obj2)) {
                return false;
            }
        }

        // If all elements are equal, the lists are equal
        return true;
    }

    public static boolean safeEquals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        } else {
            return o1 != null && o2 != null ? o1.equals(o2) : false;
        }
    }

}