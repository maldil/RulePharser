package org.python.core;
import org.python.antlr.ast.*;
import org.python.antlr.base.*;
import java.util.List;
import java.util.Iterator;
public class ASTMatcher {
    public ASTMatcher() {

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
                    && node.getInternalName()== o.getInternalName();
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
                    &&  node.getInternalModule()== o.getInternalModule()
                    &&  node.getInternalLevel()== o.getInternalLevel();
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
                    &&  node.getInternalArg() == o.getInternalArg();
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
            return node.getInternalN()==o.getInternalN();
        }
    }

    public boolean match(operatorType node, Object other) {
        operatorType o = (operatorType)other;
        return node==o;
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
                    && node.getInternalCtx()==o.getInternalCtx();
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
                    &&  node.getInternalSlice() == o.getInternalSlice();
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
                    node.getInternalName().equals(o.getInternalName());
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
                    node.getInternalOp()==o.getInternalOp();
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
                if (!n1.subtreeMatch(this, n2)) {
                    return false;
                }
            }

            return true;
        }
    }

}