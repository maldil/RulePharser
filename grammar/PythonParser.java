// $ANTLR 3.5.2 Python.g 2022-03-28 22:38:01

package org.python.antlr;

import org.antlr.runtime.CommonToken;

import org.python.antlr.ParseException;
import org.python.antlr.PythonTree;
import org.python.antlr.ast.alias;
import org.python.antlr.ast.arg;
import org.python.antlr.ast.arguments;
import org.python.antlr.ast.Assert;
import org.python.antlr.ast.Assign;
import org.python.antlr.ast.AsyncFor;
import org.python.antlr.ast.AsyncFunctionDef;
import org.python.antlr.ast.AsyncWith;
import org.python.antlr.ast.Attribute;
import org.python.antlr.ast.AugAssign;
import org.python.antlr.ast.Await;
import org.python.antlr.ast.BinOp;
import org.python.antlr.ast.BoolOp;
import org.python.antlr.ast.boolopType;
import org.python.antlr.ast.Break;
import org.python.antlr.ast.Bytes;
import org.python.antlr.ast.Call;
import org.python.antlr.ast.ClassDef;
import org.python.antlr.ast.cmpopType;
import org.python.antlr.ast.Compare;
import org.python.antlr.ast.comprehension;
import org.python.antlr.ast.Context;
import org.python.antlr.ast.Continue;
import org.python.antlr.ast.Delete;
import org.python.antlr.ast.Dict;
import org.python.antlr.ast.DictComp;
import org.python.antlr.ast.Ellipsis;
import org.python.antlr.ast.ErrorMod;
import org.python.antlr.ast.ExceptHandler;
import org.python.antlr.ast.Expr;
import org.python.antlr.ast.Expression;
import org.python.antlr.ast.expr_contextType;
import org.python.antlr.ast.ExtSlice;
import org.python.antlr.ast.For;
import org.python.antlr.ast.FunctionDef;
import org.python.antlr.ast.GeneratorExp;
import org.python.antlr.ast.Global;
import org.python.antlr.ast.If;
import org.python.antlr.ast.IfExp;
import org.python.antlr.ast.Import;
import org.python.antlr.ast.ImportFrom;
import org.python.antlr.ast.Index;
import org.python.antlr.ast.Interactive;
import org.python.antlr.ast.keyword;
import org.python.antlr.ast.ListComp;
import org.python.antlr.ast.Lambda;
import org.python.antlr.ast.Module;
import org.python.antlr.ast.Name;
import org.python.antlr.ast.NameConstant;
import org.python.antlr.ast.Nonlocal;
import org.python.antlr.ast.Num;
import org.python.antlr.ast.operatorType;
import org.python.antlr.ast.Pass;
import org.python.antlr.ast.Raise;
import org.python.antlr.ast.Return;
import org.python.antlr.ast.Set;
import org.python.antlr.ast.SetComp;
import org.python.antlr.ast.Slice;
import org.python.antlr.ast.Starred;
import org.python.antlr.ast.Str;
import org.python.antlr.ast.Subscript;
import org.python.antlr.ast.TryExcept;
import org.python.antlr.ast.TryFinally;
import org.python.antlr.ast.Tuple;
import org.python.antlr.ast.unaryopType;
import org.python.antlr.ast.UnaryOp;
import org.python.antlr.ast.While;
import org.python.antlr.ast.With;
import org.python.antlr.ast.withitem;
import org.python.antlr.ast.Yield;
import org.python.antlr.ast.YieldFrom;
import org.python.antlr.base.excepthandler;
import org.python.antlr.base.expr;
import org.python.antlr.base.mod;
import org.python.antlr.base.slice;
import org.python.antlr.base.stmt;
import org.python.core.Py;
import org.python.core.PyObject;
import org.python.core.PyUnicode;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;


/** Python 3.5.1 Grammar
 *
 *  Terence Parr and Loring Craymer
 *  February 2004
 *
 *  Converted to ANTLR v3 November 2005 by Terence Parr.
 *
 *  This grammar was derived automatically from the Python 2.3.3
 *  parser grammar to get a syntactically correct ANTLR grammar
 *  for Python.  Then Terence hand tweaked it to be semantically
 *  correct; i.e., removed lookahead issues etc...  It is LL(1)
 *  except for the (sometimes optional) trailing commas and semi-colons.
 *  It needs two symbols of lookahead in this case.
 *
 *  Starting with Loring's preliminary lexer for Python, I modified it
 *  to do my version of the whole nasty INDENT/DEDENT issue just so I
 *  could understand the problem better.  This grammar requires
 *  PythonTokenStream.java to work.  Also I used some rules from the
 *  semi-formal grammar on the web for Python (automatically
 *  translated to ANTLR format by an ANTLR grammar, naturally <grin>).
 *  The lexical rules for python are particularly nasty and it took me
 *  a long time to get it 'right'; i.e., think about it in the proper
 *  way.  Resist changing the lexer unless you've used ANTLR a lot. ;)
 *
 *  I (Terence) tested this by running it on the jython-2.1/Lib
 *  directory of 40k lines of Python.
 *
 *  REQUIRES ANTLR v3
 *
 *
 *  Updated the original parser for Python 2.5 features. The parser has been
 *  altered to produce an AST - the AST work started from tne newcompiler
 *  grammar from Jim Baker.  The current parsing and compiling strategy looks
 *  like this:
 *
 *  Python source->Python.g->AST (org/python/parser/ast/*)->CodeCompiler(ASM)->.class
 *  May 2016 Modified by Isaiah Peng to match Python 3.5.1 syntax
 */
@SuppressWarnings("all")
public class PythonParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ALT_NOTEQUAL", "AMPER", "AMPEREQUAL", 
		"AND", "ARROW", "AS", "ASSERT", "ASSIGN", "ASYNC", "AT", "ATEQUAL", "AWAIT", 
		"BACKQUOTE", "BREAK", "CIRCUMFLEX", "CIRCUMFLEXEQUAL", "CLASS", "COLON", 
		"COMMA", "COMMENT", "COMPLEX", "CONTINUE", "CONTINUED_LINE", "DEDENT", 
		"DEF", "DELETE", "DIGITS", "DOLLER", "DOT", "DOUBLESLASH", "DOUBLESLASHEQUAL", 
		"DOUBLESTAR", "DOUBLESTAREQUAL", "ELIF", "EQUAL", "ESC", "EXCEPT", "EXEC", 
		"Exponent", "FINALLY", "FLOAT", "FOR", "FROM", "GLOBAL", "GREATER", "GREATEREQUAL", 
		"IF", "IMPORT", "IN", "INDENT", "INT", "IS", "LAMBDA", "LBRACK", "LCURLY", 
		"LEADING_WS", "LEFTSHIFT", "LEFTSHIFTEQUAL", "LESS", "LESSEQUAL", "LETTER", 
		"LONGINT", "LPAREN", "MINUS", "MINUSEQUAL", "NAME", "NEWLINE", "NONLOCAL", 
		"NOT", "NOTEQUAL", "OR", "ORELSE", "PASS", "PERCENT", "PERCENTEQUAL", 
		"PLUS", "PLUSEQUAL", "RAISE", "RBRACK", "RCURLY", "RETURN", "RIGHTSHIFT", 
		"RIGHTSHIFTEQUAL", "RPAREN", "SEMI", "SLASH", "SLASHEQUAL", "STAR", "STAREQUAL", 
		"STRING", "TILDE", "TRAILBACKSLASH", "TRIAPOS", "TRIQUOTE", "TRY", "VBAR", 
		"VBAREQUAL", "WHILE", "WITH", "WS", "YIELD"
	};
	public static final int EOF=-1;
	public static final int ALT_NOTEQUAL=4;
	public static final int AMPER=5;
	public static final int AMPEREQUAL=6;
	public static final int AND=7;
	public static final int ARROW=8;
	public static final int AS=9;
	public static final int ASSERT=10;
	public static final int ASSIGN=11;
	public static final int ASYNC=12;
	public static final int AT=13;
	public static final int ATEQUAL=14;
	public static final int AWAIT=15;
	public static final int BACKQUOTE=16;
	public static final int BREAK=17;
	public static final int CIRCUMFLEX=18;
	public static final int CIRCUMFLEXEQUAL=19;
	public static final int CLASS=20;
	public static final int COLON=21;
	public static final int COMMA=22;
	public static final int COMMENT=23;
	public static final int COMPLEX=24;
	public static final int CONTINUE=25;
	public static final int CONTINUED_LINE=26;
	public static final int DEDENT=27;
	public static final int DEF=28;
	public static final int DELETE=29;
	public static final int DIGITS=30;
	public static final int DOLLER=31;
	public static final int DOT=32;
	public static final int DOUBLESLASH=33;
	public static final int DOUBLESLASHEQUAL=34;
	public static final int DOUBLESTAR=35;
	public static final int DOUBLESTAREQUAL=36;
	public static final int ELIF=37;
	public static final int EQUAL=38;
	public static final int ESC=39;
	public static final int EXCEPT=40;
	public static final int EXEC=41;
	public static final int Exponent=42;
	public static final int FINALLY=43;
	public static final int FLOAT=44;
	public static final int FOR=45;
	public static final int FROM=46;
	public static final int GLOBAL=47;
	public static final int GREATER=48;
	public static final int GREATEREQUAL=49;
	public static final int IF=50;
	public static final int IMPORT=51;
	public static final int IN=52;
	public static final int INDENT=53;
	public static final int INT=54;
	public static final int IS=55;
	public static final int LAMBDA=56;
	public static final int LBRACK=57;
	public static final int LCURLY=58;
	public static final int LEADING_WS=59;
	public static final int LEFTSHIFT=60;
	public static final int LEFTSHIFTEQUAL=61;
	public static final int LESS=62;
	public static final int LESSEQUAL=63;
	public static final int LETTER=64;
	public static final int LONGINT=65;
	public static final int LPAREN=66;
	public static final int MINUS=67;
	public static final int MINUSEQUAL=68;
	public static final int NAME=69;
	public static final int NEWLINE=70;
	public static final int NONLOCAL=71;
	public static final int NOT=72;
	public static final int NOTEQUAL=73;
	public static final int OR=74;
	public static final int ORELSE=75;
	public static final int PASS=76;
	public static final int PERCENT=77;
	public static final int PERCENTEQUAL=78;
	public static final int PLUS=79;
	public static final int PLUSEQUAL=80;
	public static final int RAISE=81;
	public static final int RBRACK=82;
	public static final int RCURLY=83;
	public static final int RETURN=84;
	public static final int RIGHTSHIFT=85;
	public static final int RIGHTSHIFTEQUAL=86;
	public static final int RPAREN=87;
	public static final int SEMI=88;
	public static final int SLASH=89;
	public static final int SLASHEQUAL=90;
	public static final int STAR=91;
	public static final int STAREQUAL=92;
	public static final int STRING=93;
	public static final int TILDE=94;
	public static final int TRAILBACKSLASH=95;
	public static final int TRIAPOS=96;
	public static final int TRIQUOTE=97;
	public static final int TRY=98;
	public static final int VBAR=99;
	public static final int VBAREQUAL=100;
	public static final int WHILE=101;
	public static final int WITH=102;
	public static final int WS=103;
	public static final int YIELD=104;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public PythonParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public PythonParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	protected TreeAdaptor adaptor = new CommonTreeAdaptor();

	public void setTreeAdaptor(TreeAdaptor adaptor) {
		this.adaptor = adaptor;
	}
	public TreeAdaptor getTreeAdaptor() {
		return adaptor;
	}
	@Override public String[] getTokenNames() { return PythonParser.tokenNames; }
	@Override public String getGrammarFileName() { return "Python.g"; }


	    private ErrorHandler errorHandler;

	    private GrammarActions actions = new GrammarActions();

	    private String encoding;

	    //Use to switch between python2 and python3 semantics.
	    //true is python3, false is python2.
	    private boolean python3 = true;

	    private boolean printFunction = python3;
	    private boolean unicodeLiterals = python3;

	    public void setErrorHandler(ErrorHandler eh) {
	        this.errorHandler = eh;
	        actions.setErrorHandler(eh);
	    }

	    protected Object recoverFromMismatchedToken(IntStream input, int ttype, BitSet follow)
	        throws RecognitionException {

	        Object o = errorHandler.recoverFromMismatchedToken(this, input, ttype, follow);
	        if (o != null) {
	            return o;
	        }
	        return super.recoverFromMismatchedToken(input, ttype, follow);
	    }

	    public PythonParser(TokenStream input, String encoding) {
	        this(input);
	        this.encoding = encoding;
	    }

	    @Override
	    public void reportError(RecognitionException e) {
	      // Update syntax error count and output error.
	      super.reportError(e);
	      errorHandler.reportError(this, e);
	    }

	    @Override
	    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
	        //Do nothing. We will handle error display elsewhere.
	    }


	public static class single_input_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "single_input"
	// Python.g:318:1: single_input : ( ( NEWLINE )* EOF | simple_stmt ( NEWLINE )* EOF | compound_stmt ( NEWLINE )+ EOF );
	public final PythonParser.single_input_return single_input() throws RecognitionException {
		PythonParser.single_input_return retval = new PythonParser.single_input_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token NEWLINE1=null;
		Token EOF2=null;
		Token NEWLINE4=null;
		Token EOF5=null;
		Token NEWLINE7=null;
		Token EOF8=null;
		ParserRuleReturnScope simple_stmt3 =null;
		ParserRuleReturnScope compound_stmt6 =null;

		PythonTree NEWLINE1_tree=null;
		PythonTree EOF2_tree=null;
		PythonTree NEWLINE4_tree=null;
		PythonTree EOF5_tree=null;
		PythonTree NEWLINE7_tree=null;
		PythonTree EOF8_tree=null;


		    mod mtype = null;

		try {
			// Python.g:325:5: ( ( NEWLINE )* EOF | simple_stmt ( NEWLINE )* EOF | compound_stmt ( NEWLINE )+ EOF )
			int alt4=3;
			switch ( input.LA(1) ) {
			case EOF:
			case NEWLINE:
				{
				alt4=1;
				}
				break;
			case ASSERT:
			case AWAIT:
			case BREAK:
			case COMPLEX:
			case CONTINUE:
			case DELETE:
			case DOLLER:
			case DOT:
			case FLOAT:
			case FROM:
			case GLOBAL:
			case IMPORT:
			case LAMBDA:
			case LBRACK:
			case LCURLY:
			case LONGINT:
			case LPAREN:
			case MINUS:
			case NAME:
			case NONLOCAL:
			case NOT:
			case PASS:
			case PLUS:
			case RAISE:
			case RETURN:
			case STAR:
			case STRING:
			case TILDE:
			case YIELD:
				{
				alt4=2;
				}
				break;
			case ASYNC:
			case AT:
			case CLASS:
			case DEF:
			case FOR:
			case IF:
			case TRY:
			case WHILE:
			case WITH:
				{
				alt4=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}
			switch (alt4) {
				case 1 :
					// Python.g:325:7: ( NEWLINE )* EOF
					{
					root_0 = (PythonTree)adaptor.nil();


					// Python.g:325:7: ( NEWLINE )*
					loop1:
					while (true) {
						int alt1=2;
						int LA1_0 = input.LA(1);
						if ( (LA1_0==NEWLINE) ) {
							alt1=1;
						}

						switch (alt1) {
						case 1 :
							// Python.g:325:7: NEWLINE
							{
							NEWLINE1=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_single_input118); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NEWLINE1_tree = (PythonTree)adaptor.create(NEWLINE1);
							adaptor.addChild(root_0, NEWLINE1_tree);
							}

							}
							break;

						default :
							break loop1;
						}
					}

					EOF2=(Token)match(input,EOF,FOLLOW_EOF_in_single_input121); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EOF2_tree = (PythonTree)adaptor.create(EOF2);
					adaptor.addChild(root_0, EOF2_tree);
					}

					if ( state.backtracking==0 ) {
					        mtype = new Interactive((retval.start), new ArrayList<stmt>());
					      }
					}
					break;
				case 2 :
					// Python.g:329:7: simple_stmt ( NEWLINE )* EOF
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_simple_stmt_in_single_input137);
					simple_stmt3=simple_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_stmt3.getTree());

					// Python.g:329:19: ( NEWLINE )*
					loop2:
					while (true) {
						int alt2=2;
						int LA2_0 = input.LA(1);
						if ( (LA2_0==NEWLINE) ) {
							alt2=1;
						}

						switch (alt2) {
						case 1 :
							// Python.g:329:19: NEWLINE
							{
							NEWLINE4=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_single_input139); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NEWLINE4_tree = (PythonTree)adaptor.create(NEWLINE4);
							adaptor.addChild(root_0, NEWLINE4_tree);
							}

							}
							break;

						default :
							break loop2;
						}
					}

					EOF5=(Token)match(input,EOF,FOLLOW_EOF_in_single_input142); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EOF5_tree = (PythonTree)adaptor.create(EOF5);
					adaptor.addChild(root_0, EOF5_tree);
					}

					if ( state.backtracking==0 ) {
					        mtype = new Interactive((retval.start), actions.castStmts((simple_stmt3!=null?((PythonParser.simple_stmt_return)simple_stmt3).stypes:null)));
					      }
					}
					break;
				case 3 :
					// Python.g:333:7: compound_stmt ( NEWLINE )+ EOF
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_compound_stmt_in_single_input158);
					compound_stmt6=compound_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_stmt6.getTree());

					// Python.g:333:21: ( NEWLINE )+
					int cnt3=0;
					loop3:
					while (true) {
						int alt3=2;
						int LA3_0 = input.LA(1);
						if ( (LA3_0==NEWLINE) ) {
							alt3=1;
						}

						switch (alt3) {
						case 1 :
							// Python.g:333:21: NEWLINE
							{
							NEWLINE7=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_single_input160); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NEWLINE7_tree = (PythonTree)adaptor.create(NEWLINE7);
							adaptor.addChild(root_0, NEWLINE7_tree);
							}

							}
							break;

						default :
							if ( cnt3 >= 1 ) break loop3;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(3, input);
							throw eee;
						}
						cnt3++;
					}

					EOF8=(Token)match(input,EOF,FOLLOW_EOF_in_single_input163); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EOF8_tree = (PythonTree)adaptor.create(EOF8);
					adaptor.addChild(root_0, EOF8_tree);
					}

					if ( state.backtracking==0 ) {
					        mtype = new Interactive((retval.start), actions.castStmts((compound_stmt6!=null?((PythonTree)compound_stmt6.getTree()):null)));
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = mtype;
			}
		}
		catch (RecognitionException re) {

			        reportError(re);
			        errorHandler.recover(this, input,re);
			        PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
			        retval.tree = new ErrorMod(badNode);
			    
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "single_input"


	public static class file_input_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "file_input"
	// Python.g:347:1: file_input : ( NEWLINE | stmt )* EOF ;
	public final PythonParser.file_input_return file_input() throws RecognitionException {
		PythonParser.file_input_return retval = new PythonParser.file_input_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token NEWLINE9=null;
		Token EOF11=null;
		ParserRuleReturnScope stmt10 =null;

		PythonTree NEWLINE9_tree=null;
		PythonTree EOF11_tree=null;


		    mod mtype = null;
		    List stypes = new ArrayList();

		try {
			// Python.g:365:5: ( ( NEWLINE | stmt )* EOF )
			// Python.g:365:7: ( NEWLINE | stmt )* EOF
			{
			root_0 = (PythonTree)adaptor.nil();


			// Python.g:365:7: ( NEWLINE | stmt )*
			loop5:
			while (true) {
				int alt5=3;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==NEWLINE) ) {
					alt5=1;
				}
				else if ( (LA5_0==ASSERT||(LA5_0 >= ASYNC && LA5_0 <= AT)||LA5_0==AWAIT||LA5_0==BREAK||LA5_0==CLASS||(LA5_0 >= COMPLEX && LA5_0 <= CONTINUE)||(LA5_0 >= DEF && LA5_0 <= DELETE)||(LA5_0 >= DOLLER && LA5_0 <= DOT)||(LA5_0 >= FLOAT && LA5_0 <= GLOBAL)||(LA5_0 >= IF && LA5_0 <= IMPORT)||(LA5_0 >= LAMBDA && LA5_0 <= LCURLY)||(LA5_0 >= LONGINT && LA5_0 <= MINUS)||LA5_0==NAME||(LA5_0 >= NONLOCAL && LA5_0 <= NOT)||LA5_0==PASS||LA5_0==PLUS||LA5_0==RAISE||LA5_0==RETURN||LA5_0==STAR||(LA5_0 >= STRING && LA5_0 <= TILDE)||LA5_0==TRY||(LA5_0 >= WHILE && LA5_0 <= WITH)||LA5_0==YIELD) ) {
					alt5=2;
				}

				switch (alt5) {
				case 1 :
					// Python.g:365:8: NEWLINE
					{
					NEWLINE9=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_file_input215); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NEWLINE9_tree = (PythonTree)adaptor.create(NEWLINE9);
					adaptor.addChild(root_0, NEWLINE9_tree);
					}

					}
					break;
				case 2 :
					// Python.g:366:9: stmt
					{
					pushFollow(FOLLOW_stmt_in_file_input225);
					stmt10=stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, stmt10.getTree());

					if ( state.backtracking==0 ) {
					          if ((stmt10!=null?((PythonParser.stmt_return)stmt10).stypes:null) != null) {
					              stypes.addAll((stmt10!=null?((PythonParser.stmt_return)stmt10).stypes:null));
					          }
					      }
					}
					break;

				default :
					break loop5;
				}
			}

			EOF11=(Token)match(input,EOF,FOLLOW_EOF_in_file_input244); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EOF11_tree = (PythonTree)adaptor.create(EOF11);
			adaptor.addChild(root_0, EOF11_tree);
			}

			if ( state.backtracking==0 ) {
			             mtype = new Module((retval.start), actions.castStmts(stypes));
			         }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (!stypes.isEmpty()) {
			        //The EOF token messes up the end offsets, so set them manually.
			        //XXX: this may no longer be true now that PythonTokenSource is
			        //     adjusting EOF offsets -- but needs testing before I remove
			        //     this.
			        PythonTree stop = (PythonTree)stypes.get(stypes.size() -1);
			        mtype.setCharStopIndex(stop.getCharStopIndex());
			        mtype.setTokenStopIndex(stop.getTokenStopIndex());
			    }

			    retval.tree = mtype;
			}
		}
		catch (RecognitionException re) {

			        reportError(re);
			        errorHandler.recover(this, input,re);
			        PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
			        retval.tree = new ErrorMod(badNode);
			    
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "file_input"


	public static class eval_input_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "eval_input"
	// Python.g:386:1: eval_input : ( LEADING_WS )? ( NEWLINE )* testlist[expr_contextType.Load] ( NEWLINE )* EOF ;
	public final PythonParser.eval_input_return eval_input() throws RecognitionException {
		PythonParser.eval_input_return retval = new PythonParser.eval_input_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token LEADING_WS12=null;
		Token NEWLINE13=null;
		Token NEWLINE15=null;
		Token EOF16=null;
		ParserRuleReturnScope testlist14 =null;

		PythonTree LEADING_WS12_tree=null;
		PythonTree NEWLINE13_tree=null;
		PythonTree NEWLINE15_tree=null;
		PythonTree EOF16_tree=null;


		    mod mtype = null;

		try {
			// Python.g:393:5: ( ( LEADING_WS )? ( NEWLINE )* testlist[expr_contextType.Load] ( NEWLINE )* EOF )
			// Python.g:393:7: ( LEADING_WS )? ( NEWLINE )* testlist[expr_contextType.Load] ( NEWLINE )* EOF
			{
			root_0 = (PythonTree)adaptor.nil();


			// Python.g:393:7: ( LEADING_WS )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==LEADING_WS) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// Python.g:393:7: LEADING_WS
					{
					LEADING_WS12=(Token)match(input,LEADING_WS,FOLLOW_LEADING_WS_in_eval_input298); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LEADING_WS12_tree = (PythonTree)adaptor.create(LEADING_WS12);
					adaptor.addChild(root_0, LEADING_WS12_tree);
					}

					}
					break;

			}

			// Python.g:393:19: ( NEWLINE )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==NEWLINE) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// Python.g:393:20: NEWLINE
					{
					NEWLINE13=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_eval_input302); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NEWLINE13_tree = (PythonTree)adaptor.create(NEWLINE13);
					adaptor.addChild(root_0, NEWLINE13_tree);
					}

					}
					break;

				default :
					break loop7;
				}
			}

			pushFollow(FOLLOW_testlist_in_eval_input306);
			testlist14=testlist(expr_contextType.Load);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist14.getTree());

			// Python.g:393:62: ( NEWLINE )*
			loop8:
			while (true) {
				int alt8=2;
				int LA8_0 = input.LA(1);
				if ( (LA8_0==NEWLINE) ) {
					alt8=1;
				}

				switch (alt8) {
				case 1 :
					// Python.g:393:63: NEWLINE
					{
					NEWLINE15=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_eval_input310); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NEWLINE15_tree = (PythonTree)adaptor.create(NEWLINE15);
					adaptor.addChild(root_0, NEWLINE15_tree);
					}

					}
					break;

				default :
					break loop8;
				}
			}

			EOF16=(Token)match(input,EOF,FOLLOW_EOF_in_eval_input314); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EOF16_tree = (PythonTree)adaptor.create(EOF16);
			adaptor.addChild(root_0, EOF16_tree);
			}

			if ( state.backtracking==0 ) {
			        mtype = new Expression((retval.start), actions.castExpr((testlist14!=null?((PythonTree)testlist14.getTree()):null)));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = mtype;
			}
		}
		catch (RecognitionException re) {

			        reportError(re);
			        errorHandler.recover(this, input,re);
			        PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
			        retval.tree = new ErrorMod(badNode);
			    
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "eval_input"


	public static class dotted_attr_return extends ParserRuleReturnScope {
		public expr etype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "dotted_attr"
	// Python.g:408:1: dotted_attr returns [expr etype] : n1= NAME ( ( DOT n2+= NAME )+ |) ;
	public final PythonParser.dotted_attr_return dotted_attr() throws RecognitionException {
		PythonParser.dotted_attr_return retval = new PythonParser.dotted_attr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token n1=null;
		Token DOT17=null;
		Token n2=null;
		List<Object> list_n2=null;

		PythonTree n1_tree=null;
		PythonTree DOT17_tree=null;
		PythonTree n2_tree=null;

		try {
			// Python.g:410:5: (n1= NAME ( ( DOT n2+= NAME )+ |) )
			// Python.g:410:7: n1= NAME ( ( DOT n2+= NAME )+ |)
			{
			root_0 = (PythonTree)adaptor.nil();


			n1=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_attr366); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			n1_tree = (PythonTree)adaptor.create(n1);
			adaptor.addChild(root_0, n1_tree);
			}

			// Python.g:411:7: ( ( DOT n2+= NAME )+ |)
			int alt10=2;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==DOT) ) {
				alt10=1;
			}
			else if ( (LA10_0==LPAREN||LA10_0==NEWLINE) ) {
				alt10=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// Python.g:411:9: ( DOT n2+= NAME )+
					{
					// Python.g:411:9: ( DOT n2+= NAME )+
					int cnt9=0;
					loop9:
					while (true) {
						int alt9=2;
						int LA9_0 = input.LA(1);
						if ( (LA9_0==DOT) ) {
							alt9=1;
						}

						switch (alt9) {
						case 1 :
							// Python.g:411:10: DOT n2+= NAME
							{
							DOT17=(Token)match(input,DOT,FOLLOW_DOT_in_dotted_attr377); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							DOT17_tree = (PythonTree)adaptor.create(DOT17);
							adaptor.addChild(root_0, DOT17_tree);
							}

							n2=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_attr381); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							n2_tree = (PythonTree)adaptor.create(n2);
							adaptor.addChild(root_0, n2_tree);
							}

							if (list_n2==null) list_n2=new ArrayList<Object>();
							list_n2.add(n2);
							}
							break;

						default :
							if ( cnt9 >= 1 ) break loop9;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(9, input);
							throw eee;
						}
						cnt9++;
					}

					if ( state.backtracking==0 ) {
					            retval.etype = actions.makeDottedAttr(n1, list_n2);
					        }
					}
					break;
				case 2 :
					// Python.g:416:9: 
					{
					if ( state.backtracking==0 ) {
					            retval.etype = actions.makeNameNode(n1);
					        }
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dotted_attr"


	public static class name_or_print_return extends ParserRuleReturnScope {
		public Token tok;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "name_or_print"
	// Python.g:423:1: name_or_print returns [Token tok] : ( NAME | ASYNC | AWAIT );
	public final PythonParser.name_or_print_return name_or_print() throws RecognitionException {
		PythonParser.name_or_print_return retval = new PythonParser.name_or_print_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token NAME18=null;
		Token ASYNC19=null;
		Token AWAIT20=null;

		PythonTree NAME18_tree=null;
		PythonTree ASYNC19_tree=null;
		PythonTree AWAIT20_tree=null;

		try {
			// Python.g:425:5: ( NAME | ASYNC | AWAIT )
			int alt11=3;
			switch ( input.LA(1) ) {
			case NAME:
				{
				alt11=1;
				}
				break;
			case ASYNC:
				{
				alt11=2;
				}
				break;
			case AWAIT:
				{
				alt11=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}
			switch (alt11) {
				case 1 :
					// Python.g:425:7: NAME
					{
					root_0 = (PythonTree)adaptor.nil();


					NAME18=(Token)match(input,NAME,FOLLOW_NAME_in_name_or_print445); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NAME18_tree = (PythonTree)adaptor.create(NAME18);
					adaptor.addChild(root_0, NAME18_tree);
					}

					if ( state.backtracking==0 ) {
					        retval.tok = (retval.start);
					    }
					}
					break;
				case 2 :
					// Python.g:428:7: ASYNC
					{
					root_0 = (PythonTree)adaptor.nil();


					ASYNC19=(Token)match(input,ASYNC,FOLLOW_ASYNC_in_name_or_print455); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASYNC19_tree = (PythonTree)adaptor.create(ASYNC19);
					adaptor.addChild(root_0, ASYNC19_tree);
					}

					if ( state.backtracking==0 ) {
					        retval.tok = (retval.start);
					    }
					}
					break;
				case 3 :
					// Python.g:431:7: AWAIT
					{
					root_0 = (PythonTree)adaptor.nil();


					AWAIT20=(Token)match(input,AWAIT,FOLLOW_AWAIT_in_name_or_print465); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AWAIT20_tree = (PythonTree)adaptor.create(AWAIT20);
					adaptor.addChild(root_0, AWAIT20_tree);
					}

					if ( state.backtracking==0 ) {
					        retval.tok = (retval.start);
					    }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "name_or_print"


	public static class attr_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "attr"
	// Python.g:440:1: attr : ( NAME | AND | AS | ASSERT | ASYNC | AWAIT | BREAK | CLASS | CONTINUE | DEF | DELETE | ELIF | EXCEPT | EXEC | FINALLY | FROM | FOR | GLOBAL | IF | IMPORT | IN | IS | LAMBDA | NONLOCAL | NOT | OR | ORELSE | PASS | RAISE | RETURN | TRY | WHILE | WITH | YIELD );
	public final PythonParser.attr_return attr() throws RecognitionException {
		PythonParser.attr_return retval = new PythonParser.attr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token set21=null;

		PythonTree set21_tree=null;

		try {
			// Python.g:441:5: ( NAME | AND | AS | ASSERT | ASYNC | AWAIT | BREAK | CLASS | CONTINUE | DEF | DELETE | ELIF | EXCEPT | EXEC | FINALLY | FROM | FOR | GLOBAL | IF | IMPORT | IN | IS | LAMBDA | NONLOCAL | NOT | OR | ORELSE | PASS | RAISE | RETURN | TRY | WHILE | WITH | YIELD )
			// Python.g:
			{
			root_0 = (PythonTree)adaptor.nil();


			set21=input.LT(1);
			if ( input.LA(1)==AND||(input.LA(1) >= AS && input.LA(1) <= ASSERT)||input.LA(1)==ASYNC||input.LA(1)==AWAIT||input.LA(1)==BREAK||input.LA(1)==CLASS||input.LA(1)==CONTINUE||(input.LA(1) >= DEF && input.LA(1) <= DELETE)||input.LA(1)==ELIF||(input.LA(1) >= EXCEPT && input.LA(1) <= EXEC)||input.LA(1)==FINALLY||(input.LA(1) >= FOR && input.LA(1) <= GLOBAL)||(input.LA(1) >= IF && input.LA(1) <= IN)||(input.LA(1) >= IS && input.LA(1) <= LAMBDA)||input.LA(1)==NAME||(input.LA(1) >= NONLOCAL && input.LA(1) <= NOT)||(input.LA(1) >= OR && input.LA(1) <= PASS)||input.LA(1)==RAISE||input.LA(1)==RETURN||input.LA(1)==TRY||(input.LA(1) >= WHILE && input.LA(1) <= WITH)||input.LA(1)==YIELD ) {
				input.consume();
				if ( state.backtracking==0 ) adaptor.addChild(root_0, (PythonTree)adaptor.create(set21));
				state.errorRecovery=false;
				state.failed=false;
			}
			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "attr"


	public static class decorator_return extends ParserRuleReturnScope {
		public expr etype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "decorator"
	// Python.g:478:1: decorator returns [expr etype] : AT dotted_attr ( LPAREN ( arglist |) RPAREN |) NEWLINE ;
	public final PythonParser.decorator_return decorator() throws RecognitionException {
		PythonParser.decorator_return retval = new PythonParser.decorator_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token AT22=null;
		Token LPAREN24=null;
		Token RPAREN26=null;
		Token NEWLINE27=null;
		ParserRuleReturnScope dotted_attr23 =null;
		ParserRuleReturnScope arglist25 =null;

		PythonTree AT22_tree=null;
		PythonTree LPAREN24_tree=null;
		PythonTree RPAREN26_tree=null;
		PythonTree NEWLINE27_tree=null;

		try {
			// Python.g:483:5: ( AT dotted_attr ( LPAREN ( arglist |) RPAREN |) NEWLINE )
			// Python.g:483:7: AT dotted_attr ( LPAREN ( arglist |) RPAREN |) NEWLINE
			{
			root_0 = (PythonTree)adaptor.nil();


			AT22=(Token)match(input,AT,FOLLOW_AT_in_decorator783); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			AT22_tree = (PythonTree)adaptor.create(AT22);
			adaptor.addChild(root_0, AT22_tree);
			}

			pushFollow(FOLLOW_dotted_attr_in_decorator785);
			dotted_attr23=dotted_attr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_attr23.getTree());

			// Python.g:484:5: ( LPAREN ( arglist |) RPAREN |)
			int alt13=2;
			int LA13_0 = input.LA(1);
			if ( (LA13_0==LPAREN) ) {
				alt13=1;
			}
			else if ( (LA13_0==NEWLINE) ) {
				alt13=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}

			switch (alt13) {
				case 1 :
					// Python.g:484:7: LPAREN ( arglist |) RPAREN
					{
					LPAREN24=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_decorator793); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LPAREN24_tree = (PythonTree)adaptor.create(LPAREN24);
					adaptor.addChild(root_0, LPAREN24_tree);
					}

					// Python.g:485:7: ( arglist |)
					int alt12=2;
					int LA12_0 = input.LA(1);
					if ( (LA12_0==AWAIT||LA12_0==COMPLEX||(LA12_0 >= DOLLER && LA12_0 <= DOT)||LA12_0==DOUBLESTAR||LA12_0==FLOAT||(LA12_0 >= LAMBDA && LA12_0 <= LCURLY)||(LA12_0 >= LONGINT && LA12_0 <= MINUS)||LA12_0==NAME||LA12_0==NOT||LA12_0==PLUS||LA12_0==STAR||(LA12_0 >= STRING && LA12_0 <= TILDE)) ) {
						alt12=1;
					}
					else if ( (LA12_0==RPAREN) ) {
						alt12=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 12, 0, input);
						throw nvae;
					}

					switch (alt12) {
						case 1 :
							// Python.g:485:9: arglist
							{
							pushFollow(FOLLOW_arglist_in_decorator803);
							arglist25=arglist();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, arglist25.getTree());

							if ( state.backtracking==0 ) {
							            retval.etype = actions.makeCall(LPAREN24, (dotted_attr23!=null?((PythonParser.dotted_attr_return)dotted_attr23).etype:null), (arglist25!=null?((PythonParser.arglist_return)arglist25).args:null), (arglist25!=null?((PythonParser.arglist_return)arglist25).keywords:null));
							        }
							}
							break;
						case 2 :
							// Python.g:490:9: 
							{
							if ( state.backtracking==0 ) {
							            retval.etype = actions.makeCall(LPAREN24, (dotted_attr23!=null?((PythonParser.dotted_attr_return)dotted_attr23).etype:null));
							        }
							}
							break;

					}

					RPAREN26=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_decorator847); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RPAREN26_tree = (PythonTree)adaptor.create(RPAREN26);
					adaptor.addChild(root_0, RPAREN26_tree);
					}

					}
					break;
				case 2 :
					// Python.g:496:7: 
					{
					if ( state.backtracking==0 ) {
					          retval.etype = (dotted_attr23!=null?((PythonParser.dotted_attr_return)dotted_attr23).etype:null);
					      }
					}
					break;

			}

			NEWLINE27=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_decorator869); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NEWLINE27_tree = (PythonTree)adaptor.create(NEWLINE27);
			adaptor.addChild(root_0, NEWLINE27_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = retval.etype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "decorator"


	public static class decorators_return extends ParserRuleReturnScope {
		public List etypes;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "decorators"
	// Python.g:503:1: decorators returns [List etypes] : (d+= decorator )+ ;
	public final PythonParser.decorators_return decorators() throws RecognitionException {
		PythonParser.decorators_return retval = new PythonParser.decorators_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		List<Object> list_d=null;
		RuleReturnScope d = null;

		try {
			// Python.g:505:5: ( (d+= decorator )+ )
			// Python.g:505:7: (d+= decorator )+
			{
			root_0 = (PythonTree)adaptor.nil();


			// Python.g:505:8: (d+= decorator )+
			int cnt14=0;
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==AT) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// Python.g:505:8: d+= decorator
					{
					pushFollow(FOLLOW_decorator_in_decorators897);
					d=decorator();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());

					if (list_d==null) list_d=new ArrayList<Object>();
					list_d.add(d.getTree());
					}
					break;

				default :
					if ( cnt14 >= 1 ) break loop14;
					if (state.backtracking>0) {state.failed=true; return retval;}
					EarlyExitException eee = new EarlyExitException(14, input);
					throw eee;
				}
				cnt14++;
			}

			if ( state.backtracking==0 ) {
			          retval.etypes = list_d;
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "decorators"


	public static class async_funcdef_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "async_funcdef"
	// Python.g:512:1: async_funcdef : ASYNC funcdef ;
	public final PythonParser.async_funcdef_return async_funcdef() throws RecognitionException {
		PythonParser.async_funcdef_return retval = new PythonParser.async_funcdef_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token ASYNC28=null;
		ParserRuleReturnScope funcdef29 =null;

		PythonTree ASYNC28_tree=null;


		    stmt stype = null;

		try {
			// Python.g:520:5: ( ASYNC funcdef )
			// Python.g:520:7: ASYNC funcdef
			{
			root_0 = (PythonTree)adaptor.nil();


			ASYNC28=(Token)match(input,ASYNC,FOLLOW_ASYNC_in_async_funcdef935); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ASYNC28_tree = (PythonTree)adaptor.create(ASYNC28);
			adaptor.addChild(root_0, ASYNC28_tree);
			}

			pushFollow(FOLLOW_funcdef_in_async_funcdef937);
			funcdef29=funcdef();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, funcdef29.getTree());

			if ( state.backtracking==0 ) {
			        stype = actions.makeAsyncFuncdef(ASYNC28, (funcdef29!=null?((PythonTree)funcdef29.getTree()):null));
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "async_funcdef"


	public static class decorated_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "decorated"
	// Python.g:527:1: decorated : decorators ( classdef | funcdef | async_funcdef ) ;
	public final PythonParser.decorated_return decorated() throws RecognitionException {
		PythonParser.decorated_return retval = new PythonParser.decorated_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		ParserRuleReturnScope decorators30 =null;
		ParserRuleReturnScope classdef31 =null;
		ParserRuleReturnScope funcdef32 =null;
		ParserRuleReturnScope async_funcdef33 =null;



		    stmt stype = null;

		try {
			// Python.g:535:5: ( decorators ( classdef | funcdef | async_funcdef ) )
			// Python.g:535:7: decorators ( classdef | funcdef | async_funcdef )
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_decorators_in_decorated972);
			decorators30=decorators();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, decorators30.getTree());

			// Python.g:536:9: ( classdef | funcdef | async_funcdef )
			int alt15=3;
			switch ( input.LA(1) ) {
			case CLASS:
				{
				alt15=1;
				}
				break;
			case DEF:
				{
				alt15=2;
				}
				break;
			case ASYNC:
				{
				alt15=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// Python.g:536:11: classdef
					{
					pushFollow(FOLLOW_classdef_in_decorated984);
					classdef31=classdef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, classdef31.getTree());

					if ( state.backtracking==0 ) {
					            stype = actions.castStmt((classdef31!=null?((PythonTree)classdef31.getTree()):null));
					            ((ClassDef) stype).setDecorator_list(actions.castExprs((decorators30!=null?((PythonParser.decorators_return)decorators30).etypes:null)));
					        }
					}
					break;
				case 2 :
					// Python.g:541:11: funcdef
					{
					pushFollow(FOLLOW_funcdef_in_decorated1006);
					funcdef32=funcdef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, funcdef32.getTree());

					if ( state.backtracking==0 ) {
					            stype = actions.castStmt((funcdef32!=null?((PythonTree)funcdef32.getTree()):null));
					            ((FunctionDef) stype).setDecorator_list(actions.castExprs((decorators30!=null?((PythonParser.decorators_return)decorators30).etypes:null)));
					        }
					}
					break;
				case 3 :
					// Python.g:546:11: async_funcdef
					{
					pushFollow(FOLLOW_async_funcdef_in_decorated1028);
					async_funcdef33=async_funcdef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, async_funcdef33.getTree());

					if ( state.backtracking==0 ) {
					            stype = actions.castStmt((async_funcdef33!=null?((PythonTree)async_funcdef33.getTree()):null));
					            ((AsyncFunctionDef) stype).setDecorator_list(actions.castExprs((decorators30!=null?((PythonParser.decorators_return)decorators30).etypes:null)));
					        }
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "decorated"


	public static class funcdef_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "funcdef"
	// Python.g:555:1: funcdef : DEF name_or_print parameters ( ARROW test[expr_contextType.Load] )? COLON suite[false] ;
	public final PythonParser.funcdef_return funcdef() throws RecognitionException {
		PythonParser.funcdef_return retval = new PythonParser.funcdef_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token DEF34=null;
		Token ARROW37=null;
		Token COLON39=null;
		ParserRuleReturnScope name_or_print35 =null;
		ParserRuleReturnScope parameters36 =null;
		ParserRuleReturnScope test38 =null;
		ParserRuleReturnScope suite40 =null;

		PythonTree DEF34_tree=null;
		PythonTree ARROW37_tree=null;
		PythonTree COLON39_tree=null;


		    stmt stype = null;

		try {
			// Python.g:563:5: ( DEF name_or_print parameters ( ARROW test[expr_contextType.Load] )? COLON suite[false] )
			// Python.g:563:7: DEF name_or_print parameters ( ARROW test[expr_contextType.Load] )? COLON suite[false]
			{
			root_0 = (PythonTree)adaptor.nil();


			DEF34=(Token)match(input,DEF,FOLLOW_DEF_in_funcdef1077); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DEF34_tree = (PythonTree)adaptor.create(DEF34);
			adaptor.addChild(root_0, DEF34_tree);
			}

			pushFollow(FOLLOW_name_or_print_in_funcdef1079);
			name_or_print35=name_or_print();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, name_or_print35.getTree());

			pushFollow(FOLLOW_parameters_in_funcdef1081);
			parameters36=parameters();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, parameters36.getTree());

			// Python.g:563:36: ( ARROW test[expr_contextType.Load] )?
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==ARROW) ) {
				alt16=1;
			}
			switch (alt16) {
				case 1 :
					// Python.g:563:37: ARROW test[expr_contextType.Load]
					{
					ARROW37=(Token)match(input,ARROW,FOLLOW_ARROW_in_funcdef1084); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ARROW37_tree = (PythonTree)adaptor.create(ARROW37);
					adaptor.addChild(root_0, ARROW37_tree);
					}

					pushFollow(FOLLOW_test_in_funcdef1086);
					test38=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, test38.getTree());

					}
					break;

			}

			COLON39=(Token)match(input,COLON,FOLLOW_COLON_in_funcdef1091); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON39_tree = (PythonTree)adaptor.create(COLON39);
			adaptor.addChild(root_0, COLON39_tree);
			}

			pushFollow(FOLLOW_suite_in_funcdef1093);
			suite40=suite(false);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, suite40.getTree());

			if ( state.backtracking==0 ) {
			        stype = actions.makeFuncdef(DEF34, (name_or_print35!=null?(name_or_print35.start):null), (parameters36!=null?((PythonParser.parameters_return)parameters36).args:null), (suite40!=null?((PythonParser.suite_return)suite40).stypes:null), actions.castExpr((test38!=null?((PythonTree)test38.getTree()):null)));
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "funcdef"


	public static class parameters_return extends ParserRuleReturnScope {
		public arguments args;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "parameters"
	// Python.g:570:1: parameters returns [arguments args] : LPAREN ( typedargslist |) RPAREN ;
	public final PythonParser.parameters_return parameters() throws RecognitionException {
		PythonParser.parameters_return retval = new PythonParser.parameters_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token LPAREN41=null;
		Token RPAREN43=null;
		ParserRuleReturnScope typedargslist42 =null;

		PythonTree LPAREN41_tree=null;
		PythonTree RPAREN43_tree=null;

		try {
			// Python.g:572:5: ( LPAREN ( typedargslist |) RPAREN )
			// Python.g:572:7: LPAREN ( typedargslist |) RPAREN
			{
			root_0 = (PythonTree)adaptor.nil();


			LPAREN41=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_parameters1126); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			LPAREN41_tree = (PythonTree)adaptor.create(LPAREN41);
			adaptor.addChild(root_0, LPAREN41_tree);
			}

			// Python.g:573:7: ( typedargslist |)
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==ASYNC||LA17_0==AWAIT||LA17_0==DOUBLESTAR||LA17_0==NAME||LA17_0==STAR) ) {
				alt17=1;
			}
			else if ( (LA17_0==RPAREN) ) {
				alt17=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 17, 0, input);
				throw nvae;
			}

			switch (alt17) {
				case 1 :
					// Python.g:573:8: typedargslist
					{
					pushFollow(FOLLOW_typedargslist_in_parameters1135);
					typedargslist42=typedargslist();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, typedargslist42.getTree());

					if ( state.backtracking==0 ) {
					              retval.args = (typedargslist42!=null?((PythonParser.typedargslist_return)typedargslist42).args:null);
					        }
					}
					break;
				case 2 :
					// Python.g:578:9: 
					{
					if ( state.backtracking==0 ) {
					            retval.args = new arguments((retval.start), new ArrayList<arg>(), (arg)null,
					            new ArrayList<arg>(), new ArrayList<expr>(), (arg) null, new ArrayList<expr>());
					        }
					}
					break;

			}

			RPAREN43=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_parameters1179); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			RPAREN43_tree = (PythonTree)adaptor.create(RPAREN43);
			adaptor.addChild(root_0, RPAREN43_tree);
			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "parameters"


	public static class tdefparameter_return extends ParserRuleReturnScope {
		public arg etype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "tdefparameter"
	// Python.g:587:1: tdefparameter[List defaults] returns [arg etype] : tfpdef ( ASSIGN test[expr_contextType.Load] )? ;
	public final PythonParser.tdefparameter_return tdefparameter(List defaults) throws RecognitionException {
		PythonParser.tdefparameter_return retval = new PythonParser.tdefparameter_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token ASSIGN45=null;
		ParserRuleReturnScope tfpdef44 =null;
		ParserRuleReturnScope test46 =null;

		PythonTree ASSIGN45_tree=null;

		try {
			// Python.g:592:5: ( tfpdef ( ASSIGN test[expr_contextType.Load] )? )
			// Python.g:592:7: tfpdef ( ASSIGN test[expr_contextType.Load] )?
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_tfpdef_in_tdefparameter1212);
			tfpdef44=tfpdef();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, tfpdef44.getTree());

			// Python.g:592:14: ( ASSIGN test[expr_contextType.Load] )?
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==ASSIGN) ) {
				alt18=1;
			}
			switch (alt18) {
				case 1 :
					// Python.g:592:15: ASSIGN test[expr_contextType.Load]
					{
					ASSIGN45=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_tdefparameter1215); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASSIGN45_tree = (PythonTree)adaptor.create(ASSIGN45);
					adaptor.addChild(root_0, ASSIGN45_tree);
					}

					pushFollow(FOLLOW_test_in_tdefparameter1217);
					test46=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, test46.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          retval.etype = actions.castArg((tfpdef44!=null?((PythonTree)tfpdef44.getTree()):null));
			          if (ASSIGN45 != null) {
			              defaults.add((test46!=null?((PythonTree)test46.getTree()):null));
			          } else {
			              defaults.add(null);
			          }
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = retval.etype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tdefparameter"


	public static class vdefparameter_return extends ParserRuleReturnScope {
		public expr etype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "vdefparameter"
	// Python.g:604:1: vdefparameter[List defaults] returns [expr etype] : vfpdef[expr_contextType.Param] ( ASSIGN test[expr_contextType.Load] )? ;
	public final PythonParser.vdefparameter_return vdefparameter(List defaults) throws RecognitionException {
		PythonParser.vdefparameter_return retval = new PythonParser.vdefparameter_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token ASSIGN48=null;
		ParserRuleReturnScope vfpdef47 =null;
		ParserRuleReturnScope test49 =null;

		PythonTree ASSIGN48_tree=null;

		try {
			// Python.g:609:5: ( vfpdef[expr_contextType.Param] ( ASSIGN test[expr_contextType.Load] )? )
			// Python.g:609:7: vfpdef[expr_contextType.Param] ( ASSIGN test[expr_contextType.Load] )?
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_vfpdef_in_vdefparameter1261);
			vfpdef47=vfpdef(expr_contextType.Param);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, vfpdef47.getTree());

			// Python.g:609:38: ( ASSIGN test[expr_contextType.Load] )?
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==ASSIGN) ) {
				alt19=1;
			}
			switch (alt19) {
				case 1 :
					// Python.g:609:39: ASSIGN test[expr_contextType.Load]
					{
					ASSIGN48=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_vdefparameter1265); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ASSIGN48_tree = (PythonTree)adaptor.create(ASSIGN48);
					adaptor.addChild(root_0, ASSIGN48_tree);
					}

					pushFollow(FOLLOW_test_in_vdefparameter1267);
					test49=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, test49.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          retval.etype = actions.castExpr((vfpdef47!=null?((PythonTree)vfpdef47.getTree()):null));
			          if (ASSIGN48 != null) {
			              defaults.add((test49!=null?((PythonTree)test49.getTree()):null));
			          } else {
			              defaults.add(null);
			          }
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = retval.etype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "vdefparameter"


	public static class typedargslist_return extends ParserRuleReturnScope {
		public arguments args;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "typedargslist"
	// Python.g:625:1: typedargslist returns [arguments args] : (d+= tdefparameter[defaults] ( options {greedy=true; } : COMMA d+= tdefparameter[defaults] )* ( COMMA ( STAR (vararg= tfpdef )? ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= tfpdef )? | DOUBLESTAR kwarg= tfpdef )? )? | STAR (vararg= tfpdef )? ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= tfpdef )? | DOUBLESTAR kwarg= tfpdef );
	public final PythonParser.typedargslist_return typedargslist() throws RecognitionException {
		PythonParser.typedargslist_return retval = new PythonParser.typedargslist_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COMMA50=null;
		Token COMMA51=null;
		Token STAR52=null;
		Token COMMA53=null;
		Token COMMA54=null;
		Token COMMA55=null;
		Token DOUBLESTAR56=null;
		Token DOUBLESTAR57=null;
		Token STAR58=null;
		Token COMMA59=null;
		Token COMMA60=null;
		Token COMMA61=null;
		Token DOUBLESTAR62=null;
		Token DOUBLESTAR63=null;
		List<Object> list_d=null;
		List<Object> list_kw=null;
		ParserRuleReturnScope vararg =null;
		ParserRuleReturnScope kwarg =null;
		RuleReturnScope d = null;
		RuleReturnScope kw = null;
		PythonTree COMMA50_tree=null;
		PythonTree COMMA51_tree=null;
		PythonTree STAR52_tree=null;
		PythonTree COMMA53_tree=null;
		PythonTree COMMA54_tree=null;
		PythonTree COMMA55_tree=null;
		PythonTree DOUBLESTAR56_tree=null;
		PythonTree DOUBLESTAR57_tree=null;
		PythonTree STAR58_tree=null;
		PythonTree COMMA59_tree=null;
		PythonTree COMMA60_tree=null;
		PythonTree COMMA61_tree=null;
		PythonTree DOUBLESTAR62_tree=null;
		PythonTree DOUBLESTAR63_tree=null;


		    List defaults = new ArrayList();
		    List kw_defaults = new ArrayList();

		try {
			// Python.g:631:5: (d+= tdefparameter[defaults] ( options {greedy=true; } : COMMA d+= tdefparameter[defaults] )* ( COMMA ( STAR (vararg= tfpdef )? ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= tfpdef )? | DOUBLESTAR kwarg= tfpdef )? )? | STAR (vararg= tfpdef )? ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= tfpdef )? | DOUBLESTAR kwarg= tfpdef )
			int alt31=3;
			switch ( input.LA(1) ) {
			case ASYNC:
			case AWAIT:
			case NAME:
				{
				alt31=1;
				}
				break;
			case STAR:
				{
				alt31=2;
				}
				break;
			case DOUBLESTAR:
				{
				alt31=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 31, 0, input);
				throw nvae;
			}
			switch (alt31) {
				case 1 :
					// Python.g:631:7: d+= tdefparameter[defaults] ( options {greedy=true; } : COMMA d+= tdefparameter[defaults] )* ( COMMA ( STAR (vararg= tfpdef )? ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= tfpdef )? | DOUBLESTAR kwarg= tfpdef )? )?
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_tdefparameter_in_typedargslist1315);
					d=tdefparameter(defaults);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());

					if (list_d==null) list_d=new ArrayList<Object>();
					list_d.add(d.getTree());
					// Python.g:631:34: ( options {greedy=true; } : COMMA d+= tdefparameter[defaults] )*
					loop20:
					while (true) {
						int alt20=2;
						int LA20_0 = input.LA(1);
						if ( (LA20_0==COMMA) ) {
							int LA20_1 = input.LA(2);
							if ( (LA20_1==ASYNC||LA20_1==AWAIT||LA20_1==NAME) ) {
								alt20=1;
							}

						}

						switch (alt20) {
						case 1 :
							// Python.g:631:58: COMMA d+= tdefparameter[defaults]
							{
							COMMA50=(Token)match(input,COMMA,FOLLOW_COMMA_in_typedargslist1326); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA50_tree = (PythonTree)adaptor.create(COMMA50);
							adaptor.addChild(root_0, COMMA50_tree);
							}

							pushFollow(FOLLOW_tdefparameter_in_typedargslist1330);
							d=tdefparameter(defaults);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());

							if (list_d==null) list_d=new ArrayList<Object>();
							list_d.add(d.getTree());
							}
							break;

						default :
							break loop20;
						}
					}

					// Python.g:632:7: ( COMMA ( STAR (vararg= tfpdef )? ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= tfpdef )? | DOUBLESTAR kwarg= tfpdef )? )?
					int alt26=2;
					int LA26_0 = input.LA(1);
					if ( (LA26_0==COMMA) ) {
						alt26=1;
					}
					switch (alt26) {
						case 1 :
							// Python.g:632:8: COMMA ( STAR (vararg= tfpdef )? ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= tfpdef )? | DOUBLESTAR kwarg= tfpdef )?
							{
							COMMA51=(Token)match(input,COMMA,FOLLOW_COMMA_in_typedargslist1342); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA51_tree = (PythonTree)adaptor.create(COMMA51);
							adaptor.addChild(root_0, COMMA51_tree);
							}

							// Python.g:633:11: ( STAR (vararg= tfpdef )? ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= tfpdef )? | DOUBLESTAR kwarg= tfpdef )?
							int alt25=3;
							int LA25_0 = input.LA(1);
							if ( (LA25_0==STAR) ) {
								alt25=1;
							}
							else if ( (LA25_0==DOUBLESTAR) ) {
								alt25=2;
							}
							switch (alt25) {
								case 1 :
									// Python.g:633:12: STAR (vararg= tfpdef )? ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= tfpdef )?
									{
									STAR52=(Token)match(input,STAR,FOLLOW_STAR_in_typedargslist1355); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									STAR52_tree = (PythonTree)adaptor.create(STAR52);
									adaptor.addChild(root_0, STAR52_tree);
									}

									// Python.g:633:17: (vararg= tfpdef )?
									int alt21=2;
									int LA21_0 = input.LA(1);
									if ( (LA21_0==ASYNC||LA21_0==AWAIT||LA21_0==NAME) ) {
										alt21=1;
									}
									switch (alt21) {
										case 1 :
											// Python.g:633:18: vararg= tfpdef
											{
											pushFollow(FOLLOW_tfpdef_in_typedargslist1360);
											vararg=tfpdef();
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, vararg.getTree());

											}
											break;

									}

									// Python.g:633:34: ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )?
									int alt23=2;
									int LA23_0 = input.LA(1);
									if ( (LA23_0==COMMA) ) {
										int LA23_1 = input.LA(2);
										if ( (LA23_1==ASYNC||LA23_1==AWAIT||LA23_1==NAME) ) {
											alt23=1;
										}
									}
									switch (alt23) {
										case 1 :
											// Python.g:633:35: COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )*
											{
											COMMA53=(Token)match(input,COMMA,FOLLOW_COMMA_in_typedargslist1365); if (state.failed) return retval;
											if ( state.backtracking==0 ) {
											COMMA53_tree = (PythonTree)adaptor.create(COMMA53);
											adaptor.addChild(root_0, COMMA53_tree);
											}

											pushFollow(FOLLOW_tdefparameter_in_typedargslist1369);
											kw=tdefparameter(kw_defaults);
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, kw.getTree());

											if (list_kw==null) list_kw=new ArrayList<Object>();
											list_kw.add(kw.getTree());
											// Python.g:633:72: ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )*
											loop22:
											while (true) {
												int alt22=2;
												int LA22_0 = input.LA(1);
												if ( (LA22_0==COMMA) ) {
													int LA22_1 = input.LA(2);
													if ( (LA22_1==ASYNC||LA22_1==AWAIT||LA22_1==NAME) ) {
														alt22=1;
													}

												}

												switch (alt22) {
												case 1 :
													// Python.g:633:96: COMMA kw+= tdefparameter[kw_defaults]
													{
													COMMA54=(Token)match(input,COMMA,FOLLOW_COMMA_in_typedargslist1380); if (state.failed) return retval;
													if ( state.backtracking==0 ) {
													COMMA54_tree = (PythonTree)adaptor.create(COMMA54);
													adaptor.addChild(root_0, COMMA54_tree);
													}

													pushFollow(FOLLOW_tdefparameter_in_typedargslist1384);
													kw=tdefparameter(kw_defaults);
													state._fsp--;
													if (state.failed) return retval;
													if ( state.backtracking==0 ) adaptor.addChild(root_0, kw.getTree());

													if (list_kw==null) list_kw=new ArrayList<Object>();
													list_kw.add(kw.getTree());
													}
													break;

												default :
													break loop22;
												}
											}

											}
											break;

									}

									// Python.g:633:137: ( COMMA DOUBLESTAR kwarg= tfpdef )?
									int alt24=2;
									int LA24_0 = input.LA(1);
									if ( (LA24_0==COMMA) ) {
										alt24=1;
									}
									switch (alt24) {
										case 1 :
											// Python.g:633:138: COMMA DOUBLESTAR kwarg= tfpdef
											{
											COMMA55=(Token)match(input,COMMA,FOLLOW_COMMA_in_typedargslist1392); if (state.failed) return retval;
											if ( state.backtracking==0 ) {
											COMMA55_tree = (PythonTree)adaptor.create(COMMA55);
											adaptor.addChild(root_0, COMMA55_tree);
											}

											DOUBLESTAR56=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_typedargslist1394); if (state.failed) return retval;
											if ( state.backtracking==0 ) {
											DOUBLESTAR56_tree = (PythonTree)adaptor.create(DOUBLESTAR56);
											adaptor.addChild(root_0, DOUBLESTAR56_tree);
											}

											pushFollow(FOLLOW_tfpdef_in_typedargslist1398);
											kwarg=tfpdef();
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, kwarg.getTree());

											}
											break;

									}

									}
									break;
								case 2 :
									// Python.g:634:13: DOUBLESTAR kwarg= tfpdef
									{
									DOUBLESTAR57=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_typedargslist1414); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									DOUBLESTAR57_tree = (PythonTree)adaptor.create(DOUBLESTAR57);
									adaptor.addChild(root_0, DOUBLESTAR57_tree);
									}

									pushFollow(FOLLOW_tfpdef_in_typedargslist1418);
									kwarg=tfpdef();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, kwarg.getTree());

									}
									break;

							}

							}
							break;

					}

					if ( state.backtracking==0 ) {
					          //if (STAR52_tree != null && (vararg!=null?((PythonTree)vararg.getTree()):null) == null && list_kw.isEmpty()) {
					          //    actions.errorNoNamedArguments(STAR52_tree);
					          //}

					          retval.args = new arguments((retval.start), actions.castArgs(list_d),
					          actions.castArg((vararg!=null?((PythonTree)vararg.getTree()):null)), actions.castArgs(list_kw),
					          kw_defaults, actions.castArg((kwarg!=null?((PythonTree)kwarg.getTree()):null)), defaults);
					      }
					}
					break;
				case 2 :
					// Python.g:646:7: STAR (vararg= tfpdef )? ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= tfpdef )?
					{
					root_0 = (PythonTree)adaptor.nil();


					STAR58=(Token)match(input,STAR,FOLLOW_STAR_in_typedargslist1456); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STAR58_tree = (PythonTree)adaptor.create(STAR58);
					adaptor.addChild(root_0, STAR58_tree);
					}

					// Python.g:646:12: (vararg= tfpdef )?
					int alt27=2;
					int LA27_0 = input.LA(1);
					if ( (LA27_0==ASYNC||LA27_0==AWAIT||LA27_0==NAME) ) {
						alt27=1;
					}
					switch (alt27) {
						case 1 :
							// Python.g:646:13: vararg= tfpdef
							{
							pushFollow(FOLLOW_tfpdef_in_typedargslist1461);
							vararg=tfpdef();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, vararg.getTree());

							}
							break;

					}

					// Python.g:646:29: ( COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )* )?
					int alt29=2;
					int LA29_0 = input.LA(1);
					if ( (LA29_0==COMMA) ) {
						int LA29_1 = input.LA(2);
						if ( (LA29_1==ASYNC||LA29_1==AWAIT||LA29_1==NAME) ) {
							alt29=1;
						}
					}
					switch (alt29) {
						case 1 :
							// Python.g:646:30: COMMA kw+= tdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )*
							{
							COMMA59=(Token)match(input,COMMA,FOLLOW_COMMA_in_typedargslist1466); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA59_tree = (PythonTree)adaptor.create(COMMA59);
							adaptor.addChild(root_0, COMMA59_tree);
							}

							pushFollow(FOLLOW_tdefparameter_in_typedargslist1470);
							kw=tdefparameter(kw_defaults);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, kw.getTree());

							if (list_kw==null) list_kw=new ArrayList<Object>();
							list_kw.add(kw.getTree());
							// Python.g:646:67: ( options {greedy=true; } : COMMA kw+= tdefparameter[kw_defaults] )*
							loop28:
							while (true) {
								int alt28=2;
								int LA28_0 = input.LA(1);
								if ( (LA28_0==COMMA) ) {
									int LA28_1 = input.LA(2);
									if ( (LA28_1==ASYNC||LA28_1==AWAIT||LA28_1==NAME) ) {
										alt28=1;
									}

								}

								switch (alt28) {
								case 1 :
									// Python.g:646:91: COMMA kw+= tdefparameter[kw_defaults]
									{
									COMMA60=(Token)match(input,COMMA,FOLLOW_COMMA_in_typedargslist1481); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									COMMA60_tree = (PythonTree)adaptor.create(COMMA60);
									adaptor.addChild(root_0, COMMA60_tree);
									}

									pushFollow(FOLLOW_tdefparameter_in_typedargslist1485);
									kw=tdefparameter(kw_defaults);
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, kw.getTree());

									if (list_kw==null) list_kw=new ArrayList<Object>();
									list_kw.add(kw.getTree());
									}
									break;

								default :
									break loop28;
								}
							}

							}
							break;

					}

					// Python.g:646:132: ( COMMA DOUBLESTAR kwarg= tfpdef )?
					int alt30=2;
					int LA30_0 = input.LA(1);
					if ( (LA30_0==COMMA) ) {
						alt30=1;
					}
					switch (alt30) {
						case 1 :
							// Python.g:646:133: COMMA DOUBLESTAR kwarg= tfpdef
							{
							COMMA61=(Token)match(input,COMMA,FOLLOW_COMMA_in_typedargslist1493); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA61_tree = (PythonTree)adaptor.create(COMMA61);
							adaptor.addChild(root_0, COMMA61_tree);
							}

							DOUBLESTAR62=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_typedargslist1495); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							DOUBLESTAR62_tree = (PythonTree)adaptor.create(DOUBLESTAR62);
							adaptor.addChild(root_0, DOUBLESTAR62_tree);
							}

							pushFollow(FOLLOW_tfpdef_in_typedargslist1499);
							kwarg=tfpdef();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, kwarg.getTree());

							}
							break;

					}

					if ( state.backtracking==0 ) {
					          retval.args = new arguments((retval.start), actions.castArgs(list_d),
					          actions.castArg((vararg!=null?((PythonTree)vararg.getTree()):null)), actions.castArgs(list_kw),
					          kw_defaults, actions.castArg((kwarg!=null?((PythonTree)kwarg.getTree()):null)), defaults);
					      }
					}
					break;
				case 3 :
					// Python.g:652:7: DOUBLESTAR kwarg= tfpdef
					{
					root_0 = (PythonTree)adaptor.nil();


					DOUBLESTAR63=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_typedargslist1517); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOUBLESTAR63_tree = (PythonTree)adaptor.create(DOUBLESTAR63);
					adaptor.addChild(root_0, DOUBLESTAR63_tree);
					}

					pushFollow(FOLLOW_tfpdef_in_typedargslist1521);
					kwarg=tfpdef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, kwarg.getTree());

					if ( state.backtracking==0 ) {
					          retval.args = new arguments((retval.start), actions.castArgs(list_d), null,
					          actions.castArgs(list_kw), kw_defaults, actions.castArg((kwarg!=null?((PythonTree)kwarg.getTree()):null)), defaults);
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "typedargslist"


	public static class tfpdef_return extends ParserRuleReturnScope {
		public arg etype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "tfpdef"
	// Python.g:660:1: tfpdef returns [arg etype] : name_or_print ( COLON test[expr_contextType.Load] )? ;
	public final PythonParser.tfpdef_return tfpdef() throws RecognitionException {
		PythonParser.tfpdef_return retval = new PythonParser.tfpdef_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COLON65=null;
		ParserRuleReturnScope name_or_print64 =null;
		ParserRuleReturnScope test66 =null;

		PythonTree COLON65_tree=null;


		  arg etype = null;

		try {
			// Python.g:669:5: ( name_or_print ( COLON test[expr_contextType.Load] )? )
			// Python.g:669:7: name_or_print ( COLON test[expr_contextType.Load] )?
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_name_or_print_in_tfpdef1561);
			name_or_print64=name_or_print();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, name_or_print64.getTree());

			// Python.g:669:21: ( COLON test[expr_contextType.Load] )?
			int alt32=2;
			int LA32_0 = input.LA(1);
			if ( (LA32_0==COLON) ) {
				alt32=1;
			}
			switch (alt32) {
				case 1 :
					// Python.g:669:22: COLON test[expr_contextType.Load]
					{
					COLON65=(Token)match(input,COLON,FOLLOW_COLON_in_tfpdef1564); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COLON65_tree = (PythonTree)adaptor.create(COLON65);
					adaptor.addChild(root_0, COLON65_tree);
					}

					pushFollow(FOLLOW_test_in_tfpdef1566);
					test66=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, test66.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          retval.etype = new arg((name_or_print64!=null?((PythonTree)name_or_print64.getTree()):null), (name_or_print64!=null?input.toString(name_or_print64.start,name_or_print64.stop):null), actions.castExpr((test66!=null?((PythonTree)test66.getTree()):null)));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (retval.etype != null) {
			        retval.tree = retval.etype;
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tfpdef"


	public static class varargslist_return extends ParserRuleReturnScope {
		public arguments args;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "varargslist"
	// Python.g:681:1: varargslist returns [arguments args] : (d+= vdefparameter[defaults] ( options {greedy=true; } : COMMA d+= vdefparameter[defaults] )* ( COMMA ( STAR (vararg= vfpdef[expr_contextType.Param] )? ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? | DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? )? | STAR (vararg= vfpdef[expr_contextType.Param] )? ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? | DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] );
	public final PythonParser.varargslist_return varargslist() throws RecognitionException {
		PythonParser.varargslist_return retval = new PythonParser.varargslist_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COMMA67=null;
		Token COMMA68=null;
		Token STAR69=null;
		Token COMMA70=null;
		Token COMMA71=null;
		Token COMMA72=null;
		Token DOUBLESTAR73=null;
		Token DOUBLESTAR74=null;
		Token STAR75=null;
		Token COMMA76=null;
		Token COMMA77=null;
		Token COMMA78=null;
		Token DOUBLESTAR79=null;
		Token DOUBLESTAR80=null;
		List<Object> list_d=null;
		List<Object> list_kw=null;
		ParserRuleReturnScope vararg =null;
		ParserRuleReturnScope kwarg =null;
		RuleReturnScope d = null;
		RuleReturnScope kw = null;
		PythonTree COMMA67_tree=null;
		PythonTree COMMA68_tree=null;
		PythonTree STAR69_tree=null;
		PythonTree COMMA70_tree=null;
		PythonTree COMMA71_tree=null;
		PythonTree COMMA72_tree=null;
		PythonTree DOUBLESTAR73_tree=null;
		PythonTree DOUBLESTAR74_tree=null;
		PythonTree STAR75_tree=null;
		PythonTree COMMA76_tree=null;
		PythonTree COMMA77_tree=null;
		PythonTree COMMA78_tree=null;
		PythonTree DOUBLESTAR79_tree=null;
		PythonTree DOUBLESTAR80_tree=null;


		    List defaults = new ArrayList();
		    List kw_defaults = new ArrayList();

		try {
			// Python.g:687:5: (d+= vdefparameter[defaults] ( options {greedy=true; } : COMMA d+= vdefparameter[defaults] )* ( COMMA ( STAR (vararg= vfpdef[expr_contextType.Param] )? ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? | DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? )? | STAR (vararg= vfpdef[expr_contextType.Param] )? ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? | DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )
			int alt44=3;
			switch ( input.LA(1) ) {
			case NAME:
				{
				alt44=1;
				}
				break;
			case STAR:
				{
				alt44=2;
				}
				break;
			case DOUBLESTAR:
				{
				alt44=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 44, 0, input);
				throw nvae;
			}
			switch (alt44) {
				case 1 :
					// Python.g:687:7: d+= vdefparameter[defaults] ( options {greedy=true; } : COMMA d+= vdefparameter[defaults] )* ( COMMA ( STAR (vararg= vfpdef[expr_contextType.Param] )? ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? | DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? )?
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_vdefparameter_in_varargslist1615);
					d=vdefparameter(defaults);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());

					if (list_d==null) list_d=new ArrayList<Object>();
					list_d.add(d.getTree());
					// Python.g:687:34: ( options {greedy=true; } : COMMA d+= vdefparameter[defaults] )*
					loop33:
					while (true) {
						int alt33=2;
						int LA33_0 = input.LA(1);
						if ( (LA33_0==COMMA) ) {
							int LA33_1 = input.LA(2);
							if ( (LA33_1==NAME) ) {
								alt33=1;
							}

						}

						switch (alt33) {
						case 1 :
							// Python.g:687:58: COMMA d+= vdefparameter[defaults]
							{
							COMMA67=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1626); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA67_tree = (PythonTree)adaptor.create(COMMA67);
							adaptor.addChild(root_0, COMMA67_tree);
							}

							pushFollow(FOLLOW_vdefparameter_in_varargslist1630);
							d=vdefparameter(defaults);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());

							if (list_d==null) list_d=new ArrayList<Object>();
							list_d.add(d.getTree());
							}
							break;

						default :
							break loop33;
						}
					}

					// Python.g:688:7: ( COMMA ( STAR (vararg= vfpdef[expr_contextType.Param] )? ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? | DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0==COMMA) ) {
						alt39=1;
					}
					switch (alt39) {
						case 1 :
							// Python.g:688:8: COMMA ( STAR (vararg= vfpdef[expr_contextType.Param] )? ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? | DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )?
							{
							COMMA68=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1642); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA68_tree = (PythonTree)adaptor.create(COMMA68);
							adaptor.addChild(root_0, COMMA68_tree);
							}

							// Python.g:689:11: ( STAR (vararg= vfpdef[expr_contextType.Param] )? ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )? | DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )?
							int alt38=3;
							int LA38_0 = input.LA(1);
							if ( (LA38_0==STAR) ) {
								alt38=1;
							}
							else if ( (LA38_0==DOUBLESTAR) ) {
								alt38=2;
							}
							switch (alt38) {
								case 1 :
									// Python.g:689:12: STAR (vararg= vfpdef[expr_contextType.Param] )? ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )?
									{
									STAR69=(Token)match(input,STAR,FOLLOW_STAR_in_varargslist1655); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									STAR69_tree = (PythonTree)adaptor.create(STAR69);
									adaptor.addChild(root_0, STAR69_tree);
									}

									// Python.g:689:17: (vararg= vfpdef[expr_contextType.Param] )?
									int alt34=2;
									int LA34_0 = input.LA(1);
									if ( (LA34_0==NAME) ) {
										alt34=1;
									}
									switch (alt34) {
										case 1 :
											// Python.g:689:18: vararg= vfpdef[expr_contextType.Param]
											{
											pushFollow(FOLLOW_vfpdef_in_varargslist1660);
											vararg=vfpdef(expr_contextType.Param);
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, vararg.getTree());

											}
											break;

									}

									// Python.g:689:58: ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )?
									int alt36=2;
									int LA36_0 = input.LA(1);
									if ( (LA36_0==COMMA) ) {
										int LA36_1 = input.LA(2);
										if ( (LA36_1==NAME) ) {
											alt36=1;
										}
									}
									switch (alt36) {
										case 1 :
											// Python.g:689:59: COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )*
											{
											COMMA70=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1666); if (state.failed) return retval;
											if ( state.backtracking==0 ) {
											COMMA70_tree = (PythonTree)adaptor.create(COMMA70);
											adaptor.addChild(root_0, COMMA70_tree);
											}

											pushFollow(FOLLOW_vdefparameter_in_varargslist1670);
											kw=vdefparameter(kw_defaults);
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, kw.getTree());

											if (list_kw==null) list_kw=new ArrayList<Object>();
											list_kw.add(kw.getTree());
											// Python.g:689:96: ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )*
											loop35:
											while (true) {
												int alt35=2;
												int LA35_0 = input.LA(1);
												if ( (LA35_0==COMMA) ) {
													int LA35_1 = input.LA(2);
													if ( (LA35_1==NAME) ) {
														alt35=1;
													}

												}

												switch (alt35) {
												case 1 :
													// Python.g:689:120: COMMA kw+= vdefparameter[kw_defaults]
													{
													COMMA71=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1681); if (state.failed) return retval;
													if ( state.backtracking==0 ) {
													COMMA71_tree = (PythonTree)adaptor.create(COMMA71);
													adaptor.addChild(root_0, COMMA71_tree);
													}

													pushFollow(FOLLOW_vdefparameter_in_varargslist1685);
													kw=vdefparameter(kw_defaults);
													state._fsp--;
													if (state.failed) return retval;
													if ( state.backtracking==0 ) adaptor.addChild(root_0, kw.getTree());

													if (list_kw==null) list_kw=new ArrayList<Object>();
													list_kw.add(kw.getTree());
													}
													break;

												default :
													break loop35;
												}
											}

											}
											break;

									}

									// Python.g:689:161: ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )?
									int alt37=2;
									int LA37_0 = input.LA(1);
									if ( (LA37_0==COMMA) ) {
										alt37=1;
									}
									switch (alt37) {
										case 1 :
											// Python.g:689:162: COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param]
											{
											COMMA72=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1693); if (state.failed) return retval;
											if ( state.backtracking==0 ) {
											COMMA72_tree = (PythonTree)adaptor.create(COMMA72);
											adaptor.addChild(root_0, COMMA72_tree);
											}

											DOUBLESTAR73=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1695); if (state.failed) return retval;
											if ( state.backtracking==0 ) {
											DOUBLESTAR73_tree = (PythonTree)adaptor.create(DOUBLESTAR73);
											adaptor.addChild(root_0, DOUBLESTAR73_tree);
											}

											pushFollow(FOLLOW_vfpdef_in_varargslist1699);
											kwarg=vfpdef(expr_contextType.Param);
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, kwarg.getTree());

											}
											break;

									}

									}
									break;
								case 2 :
									// Python.g:690:13: DOUBLESTAR kwarg= vfpdef[expr_contextType.Param]
									{
									DOUBLESTAR74=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1716); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									DOUBLESTAR74_tree = (PythonTree)adaptor.create(DOUBLESTAR74);
									adaptor.addChild(root_0, DOUBLESTAR74_tree);
									}

									pushFollow(FOLLOW_vfpdef_in_varargslist1720);
									kwarg=vfpdef(expr_contextType.Param);
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, kwarg.getTree());

									}
									break;

							}

							}
							break;

					}

					if ( state.backtracking==0 ) {
					          retval.args = new arguments((retval.start), actions.castArgs(list_d),
					          actions.castArg((vararg!=null?((PythonTree)vararg.getTree()):null)), actions.castArgs(list_kw),
					          kw_defaults, actions.castArg((kwarg!=null?((PythonTree)kwarg.getTree()):null)), defaults);
					      }
					}
					break;
				case 2 :
					// Python.g:698:7: STAR (vararg= vfpdef[expr_contextType.Param] )? ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )? ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )?
					{
					root_0 = (PythonTree)adaptor.nil();


					STAR75=(Token)match(input,STAR,FOLLOW_STAR_in_varargslist1759); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STAR75_tree = (PythonTree)adaptor.create(STAR75);
					adaptor.addChild(root_0, STAR75_tree);
					}

					// Python.g:698:12: (vararg= vfpdef[expr_contextType.Param] )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0==NAME) ) {
						alt40=1;
					}
					switch (alt40) {
						case 1 :
							// Python.g:698:13: vararg= vfpdef[expr_contextType.Param]
							{
							pushFollow(FOLLOW_vfpdef_in_varargslist1764);
							vararg=vfpdef(expr_contextType.Param);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, vararg.getTree());

							}
							break;

					}

					// Python.g:698:53: ( COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )* )?
					int alt42=2;
					int LA42_0 = input.LA(1);
					if ( (LA42_0==COMMA) ) {
						int LA42_1 = input.LA(2);
						if ( (LA42_1==NAME) ) {
							alt42=1;
						}
					}
					switch (alt42) {
						case 1 :
							// Python.g:698:54: COMMA kw+= vdefparameter[kw_defaults] ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )*
							{
							COMMA76=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1770); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA76_tree = (PythonTree)adaptor.create(COMMA76);
							adaptor.addChild(root_0, COMMA76_tree);
							}

							pushFollow(FOLLOW_vdefparameter_in_varargslist1774);
							kw=vdefparameter(kw_defaults);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, kw.getTree());

							if (list_kw==null) list_kw=new ArrayList<Object>();
							list_kw.add(kw.getTree());
							// Python.g:698:91: ( options {greedy=true; } : COMMA kw+= vdefparameter[kw_defaults] )*
							loop41:
							while (true) {
								int alt41=2;
								int LA41_0 = input.LA(1);
								if ( (LA41_0==COMMA) ) {
									int LA41_1 = input.LA(2);
									if ( (LA41_1==NAME) ) {
										alt41=1;
									}

								}

								switch (alt41) {
								case 1 :
									// Python.g:698:115: COMMA kw+= vdefparameter[kw_defaults]
									{
									COMMA77=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1785); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									COMMA77_tree = (PythonTree)adaptor.create(COMMA77);
									adaptor.addChild(root_0, COMMA77_tree);
									}

									pushFollow(FOLLOW_vdefparameter_in_varargslist1789);
									kw=vdefparameter(kw_defaults);
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, kw.getTree());

									if (list_kw==null) list_kw=new ArrayList<Object>();
									list_kw.add(kw.getTree());
									}
									break;

								default :
									break loop41;
								}
							}

							}
							break;

					}

					// Python.g:698:156: ( COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param] )?
					int alt43=2;
					int LA43_0 = input.LA(1);
					if ( (LA43_0==COMMA) ) {
						alt43=1;
					}
					switch (alt43) {
						case 1 :
							// Python.g:698:157: COMMA DOUBLESTAR kwarg= vfpdef[expr_contextType.Param]
							{
							COMMA78=(Token)match(input,COMMA,FOLLOW_COMMA_in_varargslist1797); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA78_tree = (PythonTree)adaptor.create(COMMA78);
							adaptor.addChild(root_0, COMMA78_tree);
							}

							DOUBLESTAR79=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1799); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							DOUBLESTAR79_tree = (PythonTree)adaptor.create(DOUBLESTAR79);
							adaptor.addChild(root_0, DOUBLESTAR79_tree);
							}

							pushFollow(FOLLOW_vfpdef_in_varargslist1803);
							kwarg=vfpdef(expr_contextType.Param);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, kwarg.getTree());

							}
							break;

					}

					if ( state.backtracking==0 ) {
					          retval.args = new arguments((retval.start), actions.castArgs(list_d),
					          actions.castArg((vararg!=null?((PythonTree)vararg.getTree()):null)),
					          actions.castArgs(list_kw), kw_defaults, actions.castArg((kwarg!=null?((PythonTree)kwarg.getTree()):null)), defaults);
					      }
					}
					break;
				case 3 :
					// Python.g:704:7: DOUBLESTAR kwarg= vfpdef[expr_contextType.Param]
					{
					root_0 = (PythonTree)adaptor.nil();


					DOUBLESTAR80=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_varargslist1822); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOUBLESTAR80_tree = (PythonTree)adaptor.create(DOUBLESTAR80);
					adaptor.addChild(root_0, DOUBLESTAR80_tree);
					}

					pushFollow(FOLLOW_vfpdef_in_varargslist1826);
					kwarg=vfpdef(expr_contextType.Param);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, kwarg.getTree());

					if ( state.backtracking==0 ) {
					          retval.args = new arguments((retval.start), actions.castArgs(list_d), null,
					          actions.castArgs(list_kw), kw_defaults,
					          actions.castArg((kwarg!=null?((PythonTree)kwarg.getTree()):null)), defaults);
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "varargslist"


	public static class vfpdef_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "vfpdef"
	// Python.g:713:1: vfpdef[expr_contextType ctype] : NAME ;
	public final PythonParser.vfpdef_return vfpdef(expr_contextType ctype) throws RecognitionException {
		PythonParser.vfpdef_return retval = new PythonParser.vfpdef_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token NAME81=null;

		PythonTree NAME81_tree=null;


		    expr etype = null;

		try {
			// Python.g:722:5: ( NAME )
			// Python.g:722:7: NAME
			{
			root_0 = (PythonTree)adaptor.nil();


			NAME81=(Token)match(input,NAME,FOLLOW_NAME_in_vfpdef1864); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NAME81_tree = (PythonTree)adaptor.create(NAME81);
			adaptor.addChild(root_0, NAME81_tree);
			}

			if ( state.backtracking==0 ) {
			          etype = new Name(NAME81, (NAME81!=null?NAME81.getText():null), ctype);
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (etype != null) {
			        retval.tree = etype;
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "vfpdef"


	public static class stmt_return extends ParserRuleReturnScope {
		public List stypes;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "stmt"
	// Python.g:729:1: stmt returns [List stypes] : ( simple_stmt | compound_stmt );
	public final PythonParser.stmt_return stmt() throws RecognitionException {
		PythonParser.stmt_return retval = new PythonParser.stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		ParserRuleReturnScope simple_stmt82 =null;
		ParserRuleReturnScope compound_stmt83 =null;


		try {
			// Python.g:731:5: ( simple_stmt | compound_stmt )
			int alt45=2;
			int LA45_0 = input.LA(1);
			if ( (LA45_0==ASSERT||LA45_0==AWAIT||LA45_0==BREAK||(LA45_0 >= COMPLEX && LA45_0 <= CONTINUE)||LA45_0==DELETE||(LA45_0 >= DOLLER && LA45_0 <= DOT)||LA45_0==FLOAT||(LA45_0 >= FROM && LA45_0 <= GLOBAL)||LA45_0==IMPORT||(LA45_0 >= LAMBDA && LA45_0 <= LCURLY)||(LA45_0 >= LONGINT && LA45_0 <= MINUS)||LA45_0==NAME||(LA45_0 >= NONLOCAL && LA45_0 <= NOT)||LA45_0==PASS||LA45_0==PLUS||LA45_0==RAISE||LA45_0==RETURN||LA45_0==STAR||(LA45_0 >= STRING && LA45_0 <= TILDE)||LA45_0==YIELD) ) {
				alt45=1;
			}
			else if ( ((LA45_0 >= ASYNC && LA45_0 <= AT)||LA45_0==CLASS||LA45_0==DEF||LA45_0==FOR||LA45_0==IF||LA45_0==TRY||(LA45_0 >= WHILE && LA45_0 <= WITH)) ) {
				alt45=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 45, 0, input);
				throw nvae;
			}

			switch (alt45) {
				case 1 :
					// Python.g:731:7: simple_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_simple_stmt_in_stmt1898);
					simple_stmt82=simple_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_stmt82.getTree());

					if ( state.backtracking==0 ) {
					          retval.stypes = (simple_stmt82!=null?((PythonParser.simple_stmt_return)simple_stmt82).stypes:null);
					      }
					}
					break;
				case 2 :
					// Python.g:735:7: compound_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_compound_stmt_in_stmt1914);
					compound_stmt83=compound_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, compound_stmt83.getTree());

					if ( state.backtracking==0 ) {
					          retval.stypes = new ArrayList();
					          retval.stypes.add((compound_stmt83!=null?((PythonTree)compound_stmt83.getTree()):null));
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "stmt"


	public static class simple_stmt_return extends ParserRuleReturnScope {
		public List stypes;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "simple_stmt"
	// Python.g:743:1: simple_stmt returns [List stypes] :s+= small_stmt ( options {greedy=true; } : SEMI s+= small_stmt )* ( SEMI )? NEWLINE ;
	public final PythonParser.simple_stmt_return simple_stmt() throws RecognitionException {
		PythonParser.simple_stmt_return retval = new PythonParser.simple_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token SEMI84=null;
		Token SEMI85=null;
		Token NEWLINE86=null;
		List<Object> list_s=null;
		RuleReturnScope s = null;
		PythonTree SEMI84_tree=null;
		PythonTree SEMI85_tree=null;
		PythonTree NEWLINE86_tree=null;

		try {
			// Python.g:745:5: (s+= small_stmt ( options {greedy=true; } : SEMI s+= small_stmt )* ( SEMI )? NEWLINE )
			// Python.g:745:7: s+= small_stmt ( options {greedy=true; } : SEMI s+= small_stmt )* ( SEMI )? NEWLINE
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_small_stmt_in_simple_stmt1950);
			s=small_stmt();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());

			if (list_s==null) list_s=new ArrayList<Object>();
			list_s.add(s.getTree());
			// Python.g:745:21: ( options {greedy=true; } : SEMI s+= small_stmt )*
			loop46:
			while (true) {
				int alt46=2;
				int LA46_0 = input.LA(1);
				if ( (LA46_0==SEMI) ) {
					int LA46_1 = input.LA(2);
					if ( (LA46_1==ASSERT||LA46_1==AWAIT||LA46_1==BREAK||(LA46_1 >= COMPLEX && LA46_1 <= CONTINUE)||LA46_1==DELETE||(LA46_1 >= DOLLER && LA46_1 <= DOT)||LA46_1==FLOAT||(LA46_1 >= FROM && LA46_1 <= GLOBAL)||LA46_1==IMPORT||(LA46_1 >= LAMBDA && LA46_1 <= LCURLY)||(LA46_1 >= LONGINT && LA46_1 <= MINUS)||LA46_1==NAME||(LA46_1 >= NONLOCAL && LA46_1 <= NOT)||LA46_1==PASS||LA46_1==PLUS||LA46_1==RAISE||LA46_1==RETURN||LA46_1==STAR||(LA46_1 >= STRING && LA46_1 <= TILDE)||LA46_1==YIELD) ) {
						alt46=1;
					}

				}

				switch (alt46) {
				case 1 :
					// Python.g:745:45: SEMI s+= small_stmt
					{
					SEMI84=(Token)match(input,SEMI,FOLLOW_SEMI_in_simple_stmt1960); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SEMI84_tree = (PythonTree)adaptor.create(SEMI84);
					adaptor.addChild(root_0, SEMI84_tree);
					}

					pushFollow(FOLLOW_small_stmt_in_simple_stmt1964);
					s=small_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());

					if (list_s==null) list_s=new ArrayList<Object>();
					list_s.add(s.getTree());
					}
					break;

				default :
					break loop46;
				}
			}

			// Python.g:745:66: ( SEMI )?
			int alt47=2;
			int LA47_0 = input.LA(1);
			if ( (LA47_0==SEMI) ) {
				alt47=1;
			}
			switch (alt47) {
				case 1 :
					// Python.g:745:67: SEMI
					{
					SEMI85=(Token)match(input,SEMI,FOLLOW_SEMI_in_simple_stmt1969); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SEMI85_tree = (PythonTree)adaptor.create(SEMI85);
					adaptor.addChild(root_0, SEMI85_tree);
					}

					}
					break;

			}

			NEWLINE86=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_simple_stmt1973); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NEWLINE86_tree = (PythonTree)adaptor.create(NEWLINE86);
			adaptor.addChild(root_0, NEWLINE86_tree);
			}

			if ( state.backtracking==0 ) {
			          retval.stypes = list_s;
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "simple_stmt"


	public static class small_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "small_stmt"
	// Python.g:753:1: small_stmt : ( expr_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt | global_stmt | nonlocal_stmt | assert_stmt );
	public final PythonParser.small_stmt_return small_stmt() throws RecognitionException {
		PythonParser.small_stmt_return retval = new PythonParser.small_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		ParserRuleReturnScope expr_stmt87 =null;
		ParserRuleReturnScope del_stmt88 =null;
		ParserRuleReturnScope pass_stmt89 =null;
		ParserRuleReturnScope flow_stmt90 =null;
		ParserRuleReturnScope import_stmt91 =null;
		ParserRuleReturnScope global_stmt92 =null;
		ParserRuleReturnScope nonlocal_stmt93 =null;
		ParserRuleReturnScope assert_stmt94 =null;


		try {
			// Python.g:753:12: ( expr_stmt | del_stmt | pass_stmt | flow_stmt | import_stmt | global_stmt | nonlocal_stmt | assert_stmt )
			int alt48=8;
			switch ( input.LA(1) ) {
			case AWAIT:
			case COMPLEX:
			case DOLLER:
			case DOT:
			case FLOAT:
			case LAMBDA:
			case LBRACK:
			case LCURLY:
			case LONGINT:
			case LPAREN:
			case MINUS:
			case NAME:
			case NOT:
			case PLUS:
			case STAR:
			case STRING:
			case TILDE:
				{
				alt48=1;
				}
				break;
			case DELETE:
				{
				alt48=2;
				}
				break;
			case PASS:
				{
				alt48=3;
				}
				break;
			case BREAK:
			case CONTINUE:
			case RAISE:
			case RETURN:
			case YIELD:
				{
				alt48=4;
				}
				break;
			case FROM:
			case IMPORT:
				{
				alt48=5;
				}
				break;
			case GLOBAL:
				{
				alt48=6;
				}
				break;
			case NONLOCAL:
				{
				alt48=7;
				}
				break;
			case ASSERT:
				{
				alt48=8;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 48, 0, input);
				throw nvae;
			}
			switch (alt48) {
				case 1 :
					// Python.g:753:14: expr_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_expr_stmt_in_small_stmt1996);
					expr_stmt87=expr_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr_stmt87.getTree());

					}
					break;
				case 2 :
					// Python.g:754:14: del_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_del_stmt_in_small_stmt2011);
					del_stmt88=del_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, del_stmt88.getTree());

					}
					break;
				case 3 :
					// Python.g:755:14: pass_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_pass_stmt_in_small_stmt2026);
					pass_stmt89=pass_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, pass_stmt89.getTree());

					}
					break;
				case 4 :
					// Python.g:756:14: flow_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_flow_stmt_in_small_stmt2041);
					flow_stmt90=flow_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, flow_stmt90.getTree());

					}
					break;
				case 5 :
					// Python.g:757:14: import_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_import_stmt_in_small_stmt2056);
					import_stmt91=import_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, import_stmt91.getTree());

					}
					break;
				case 6 :
					// Python.g:758:14: global_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_global_stmt_in_small_stmt2071);
					global_stmt92=global_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, global_stmt92.getTree());

					}
					break;
				case 7 :
					// Python.g:759:14: nonlocal_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_nonlocal_stmt_in_small_stmt2086);
					nonlocal_stmt93=nonlocal_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, nonlocal_stmt93.getTree());

					}
					break;
				case 8 :
					// Python.g:760:14: assert_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_assert_stmt_in_small_stmt2101);
					assert_stmt94=assert_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, assert_stmt94.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "small_stmt"


	public static class star_expr_return extends ParserRuleReturnScope {
		public expr etype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "star_expr"
	// Python.g:764:1: star_expr[expr_contextType ctype] returns [expr etype] : STAR expr[ctype] ;
	public final PythonParser.star_expr_return star_expr(expr_contextType ctype) throws RecognitionException {
		PythonParser.star_expr_return retval = new PythonParser.star_expr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token STAR95=null;
		ParserRuleReturnScope expr96 =null;

		PythonTree STAR95_tree=null;

		try {
			// Python.g:769:5: ( STAR expr[ctype] )
			// Python.g:769:7: STAR expr[ctype]
			{
			root_0 = (PythonTree)adaptor.nil();


			STAR95=(Token)match(input,STAR,FOLLOW_STAR_in_star_expr2141); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			STAR95_tree = (PythonTree)adaptor.create(STAR95);
			adaptor.addChild(root_0, STAR95_tree);
			}

			pushFollow(FOLLOW_expr_in_star_expr2143);
			expr96=expr(ctype);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, expr96.getTree());

			if ( state.backtracking==0 ) {
			        retval.etype = new Starred(STAR95, actions.castExpr((expr96!=null?((PythonTree)expr96.getTree()):null)), ctype);
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = retval.etype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "star_expr"


	public static class expr_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "expr_stmt"
	// Python.g:776:1: expr_stmt : ( ( testlist_star_expr[null] augassign )=>lhs= testlist_star_expr[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist_star_expr[expr_contextType.Load] ) ) | ( testlist_star_expr[null] ':' test[null] ASSIGN )=>lhs= testlist_star_expr[expr_contextType.Store] ':' test[null] (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | ( testlist_star_expr[null] ASSIGN )=>lhs= testlist_star_expr[expr_contextType.Store] (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) |lhs= testlist_star_expr[expr_contextType.Load] ) ;
	public final PythonParser.expr_stmt_return expr_stmt() throws RecognitionException {
		PythonParser.expr_stmt_return retval = new PythonParser.expr_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token at=null;
		Token ay=null;
		Token char_literal97=null;
		List<Object> list_t=null;
		List<Object> list_y2=null;
		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope aay =null;
		ParserRuleReturnScope y1 =null;
		ParserRuleReturnScope aat =null;
		ParserRuleReturnScope rhs =null;
		ParserRuleReturnScope test98 =null;
		RuleReturnScope t = null;
		RuleReturnScope y2 = null;
		PythonTree at_tree=null;
		PythonTree ay_tree=null;
		PythonTree char_literal97_tree=null;


		    stmt stype = null;

		try {
			// Python.g:785:5: ( ( ( testlist_star_expr[null] augassign )=>lhs= testlist_star_expr[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist_star_expr[expr_contextType.Load] ) ) | ( testlist_star_expr[null] ':' test[null] ASSIGN )=>lhs= testlist_star_expr[expr_contextType.Store] ':' test[null] (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | ( testlist_star_expr[null] ASSIGN )=>lhs= testlist_star_expr[expr_contextType.Store] (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) |lhs= testlist_star_expr[expr_contextType.Load] ) )
			// Python.g:785:7: ( ( testlist_star_expr[null] augassign )=>lhs= testlist_star_expr[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist_star_expr[expr_contextType.Load] ) ) | ( testlist_star_expr[null] ':' test[null] ASSIGN )=>lhs= testlist_star_expr[expr_contextType.Store] ':' test[null] (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | ( testlist_star_expr[null] ASSIGN )=>lhs= testlist_star_expr[expr_contextType.Store] (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) |lhs= testlist_star_expr[expr_contextType.Load] )
			{
			root_0 = (PythonTree)adaptor.nil();


			// Python.g:785:7: ( ( testlist_star_expr[null] augassign )=>lhs= testlist_star_expr[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist_star_expr[expr_contextType.Load] ) ) | ( testlist_star_expr[null] ':' test[null] ASSIGN )=>lhs= testlist_star_expr[expr_contextType.Store] ':' test[null] (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) | ( testlist_star_expr[null] ASSIGN )=>lhs= testlist_star_expr[expr_contextType.Store] (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) ) |lhs= testlist_star_expr[expr_contextType.Load] )
			int alt56=4;
			switch ( input.LA(1) ) {
			case NOT:
				{
				int LA56_1 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case PLUS:
				{
				int LA56_2 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case MINUS:
				{
				int LA56_3 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case TILDE:
				{
				int LA56_4 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case AWAIT:
				{
				int LA56_5 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case LPAREN:
				{
				int LA56_6 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case LBRACK:
				{
				int LA56_7 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case LCURLY:
				{
				int LA56_8 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case NAME:
				{
				int LA56_9 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case LONGINT:
				{
				int LA56_10 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case FLOAT:
				{
				int LA56_11 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case COMPLEX:
				{
				int LA56_12 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case DOT:
				{
				int LA56_13 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case DOLLER:
				{
				int LA56_14 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case STRING:
				{
				int LA56_15 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case LAMBDA:
				{
				int LA56_16 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			case STAR:
				{
				int LA56_17 = input.LA(2);
				if ( (synpred1_Python()) ) {
					alt56=1;
				}
				else if ( (synpred2_Python()) ) {
					alt56=2;
				}
				else if ( (synpred3_Python()) ) {
					alt56=3;
				}
				else if ( (true) ) {
					alt56=4;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 56, 0, input);
				throw nvae;
			}
			switch (alt56) {
				case 1 :
					// Python.g:785:8: ( testlist_star_expr[null] augassign )=>lhs= testlist_star_expr[expr_contextType.AugStore] ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist_star_expr[expr_contextType.Load] ) )
					{
					pushFollow(FOLLOW_testlist_star_expr_in_expr_stmt2187);
					lhs=testlist_star_expr(expr_contextType.AugStore);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs.getTree());

					// Python.g:786:9: ( (aay= augassign y1= yield_expr ) | (aat= augassign rhs= testlist_star_expr[expr_contextType.Load] ) )
					int alt49=2;
					switch ( input.LA(1) ) {
					case PLUSEQUAL:
						{
						int LA49_1 = input.LA(2);
						if ( (LA49_1==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_1==AWAIT||LA49_1==COMPLEX||(LA49_1 >= DOLLER && LA49_1 <= DOT)||LA49_1==FLOAT||(LA49_1 >= LAMBDA && LA49_1 <= LCURLY)||(LA49_1 >= LONGINT && LA49_1 <= MINUS)||LA49_1==NAME||LA49_1==NOT||LA49_1==PLUS||LA49_1==STAR||(LA49_1 >= STRING && LA49_1 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 1, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case MINUSEQUAL:
						{
						int LA49_2 = input.LA(2);
						if ( (LA49_2==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_2==AWAIT||LA49_2==COMPLEX||(LA49_2 >= DOLLER && LA49_2 <= DOT)||LA49_2==FLOAT||(LA49_2 >= LAMBDA && LA49_2 <= LCURLY)||(LA49_2 >= LONGINT && LA49_2 <= MINUS)||LA49_2==NAME||LA49_2==NOT||LA49_2==PLUS||LA49_2==STAR||(LA49_2 >= STRING && LA49_2 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 2, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case STAREQUAL:
						{
						int LA49_3 = input.LA(2);
						if ( (LA49_3==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_3==AWAIT||LA49_3==COMPLEX||(LA49_3 >= DOLLER && LA49_3 <= DOT)||LA49_3==FLOAT||(LA49_3 >= LAMBDA && LA49_3 <= LCURLY)||(LA49_3 >= LONGINT && LA49_3 <= MINUS)||LA49_3==NAME||LA49_3==NOT||LA49_3==PLUS||LA49_3==STAR||(LA49_3 >= STRING && LA49_3 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case ATEQUAL:
						{
						int LA49_4 = input.LA(2);
						if ( (LA49_4==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_4==AWAIT||LA49_4==COMPLEX||(LA49_4 >= DOLLER && LA49_4 <= DOT)||LA49_4==FLOAT||(LA49_4 >= LAMBDA && LA49_4 <= LCURLY)||(LA49_4 >= LONGINT && LA49_4 <= MINUS)||LA49_4==NAME||LA49_4==NOT||LA49_4==PLUS||LA49_4==STAR||(LA49_4 >= STRING && LA49_4 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 4, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case SLASHEQUAL:
						{
						int LA49_5 = input.LA(2);
						if ( (LA49_5==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_5==AWAIT||LA49_5==COMPLEX||(LA49_5 >= DOLLER && LA49_5 <= DOT)||LA49_5==FLOAT||(LA49_5 >= LAMBDA && LA49_5 <= LCURLY)||(LA49_5 >= LONGINT && LA49_5 <= MINUS)||LA49_5==NAME||LA49_5==NOT||LA49_5==PLUS||LA49_5==STAR||(LA49_5 >= STRING && LA49_5 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 5, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case PERCENTEQUAL:
						{
						int LA49_6 = input.LA(2);
						if ( (LA49_6==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_6==AWAIT||LA49_6==COMPLEX||(LA49_6 >= DOLLER && LA49_6 <= DOT)||LA49_6==FLOAT||(LA49_6 >= LAMBDA && LA49_6 <= LCURLY)||(LA49_6 >= LONGINT && LA49_6 <= MINUS)||LA49_6==NAME||LA49_6==NOT||LA49_6==PLUS||LA49_6==STAR||(LA49_6 >= STRING && LA49_6 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 6, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case AMPEREQUAL:
						{
						int LA49_7 = input.LA(2);
						if ( (LA49_7==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_7==AWAIT||LA49_7==COMPLEX||(LA49_7 >= DOLLER && LA49_7 <= DOT)||LA49_7==FLOAT||(LA49_7 >= LAMBDA && LA49_7 <= LCURLY)||(LA49_7 >= LONGINT && LA49_7 <= MINUS)||LA49_7==NAME||LA49_7==NOT||LA49_7==PLUS||LA49_7==STAR||(LA49_7 >= STRING && LA49_7 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 7, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case VBAREQUAL:
						{
						int LA49_8 = input.LA(2);
						if ( (LA49_8==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_8==AWAIT||LA49_8==COMPLEX||(LA49_8 >= DOLLER && LA49_8 <= DOT)||LA49_8==FLOAT||(LA49_8 >= LAMBDA && LA49_8 <= LCURLY)||(LA49_8 >= LONGINT && LA49_8 <= MINUS)||LA49_8==NAME||LA49_8==NOT||LA49_8==PLUS||LA49_8==STAR||(LA49_8 >= STRING && LA49_8 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 8, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case CIRCUMFLEXEQUAL:
						{
						int LA49_9 = input.LA(2);
						if ( (LA49_9==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_9==AWAIT||LA49_9==COMPLEX||(LA49_9 >= DOLLER && LA49_9 <= DOT)||LA49_9==FLOAT||(LA49_9 >= LAMBDA && LA49_9 <= LCURLY)||(LA49_9 >= LONGINT && LA49_9 <= MINUS)||LA49_9==NAME||LA49_9==NOT||LA49_9==PLUS||LA49_9==STAR||(LA49_9 >= STRING && LA49_9 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 9, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case LEFTSHIFTEQUAL:
						{
						int LA49_10 = input.LA(2);
						if ( (LA49_10==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_10==AWAIT||LA49_10==COMPLEX||(LA49_10 >= DOLLER && LA49_10 <= DOT)||LA49_10==FLOAT||(LA49_10 >= LAMBDA && LA49_10 <= LCURLY)||(LA49_10 >= LONGINT && LA49_10 <= MINUS)||LA49_10==NAME||LA49_10==NOT||LA49_10==PLUS||LA49_10==STAR||(LA49_10 >= STRING && LA49_10 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 10, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case RIGHTSHIFTEQUAL:
						{
						int LA49_11 = input.LA(2);
						if ( (LA49_11==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_11==AWAIT||LA49_11==COMPLEX||(LA49_11 >= DOLLER && LA49_11 <= DOT)||LA49_11==FLOAT||(LA49_11 >= LAMBDA && LA49_11 <= LCURLY)||(LA49_11 >= LONGINT && LA49_11 <= MINUS)||LA49_11==NAME||LA49_11==NOT||LA49_11==PLUS||LA49_11==STAR||(LA49_11 >= STRING && LA49_11 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 11, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case DOUBLESTAREQUAL:
						{
						int LA49_12 = input.LA(2);
						if ( (LA49_12==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_12==AWAIT||LA49_12==COMPLEX||(LA49_12 >= DOLLER && LA49_12 <= DOT)||LA49_12==FLOAT||(LA49_12 >= LAMBDA && LA49_12 <= LCURLY)||(LA49_12 >= LONGINT && LA49_12 <= MINUS)||LA49_12==NAME||LA49_12==NOT||LA49_12==PLUS||LA49_12==STAR||(LA49_12 >= STRING && LA49_12 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 12, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case DOUBLESLASHEQUAL:
						{
						int LA49_13 = input.LA(2);
						if ( (LA49_13==YIELD) ) {
							alt49=1;
						}
						else if ( (LA49_13==AWAIT||LA49_13==COMPLEX||(LA49_13 >= DOLLER && LA49_13 <= DOT)||LA49_13==FLOAT||(LA49_13 >= LAMBDA && LA49_13 <= LCURLY)||(LA49_13 >= LONGINT && LA49_13 <= MINUS)||LA49_13==NAME||LA49_13==NOT||LA49_13==PLUS||LA49_13==STAR||(LA49_13 >= STRING && LA49_13 <= TILDE)) ) {
							alt49=2;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 49, 13, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 49, 0, input);
						throw nvae;
					}
					switch (alt49) {
						case 1 :
							// Python.g:786:11: (aay= augassign y1= yield_expr )
							{
							// Python.g:786:11: (aay= augassign y1= yield_expr )
							// Python.g:786:12: aay= augassign y1= yield_expr
							{
							pushFollow(FOLLOW_augassign_in_expr_stmt2203);
							aay=augassign();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, aay.getTree());

							pushFollow(FOLLOW_yield_expr_in_expr_stmt2207);
							y1=yield_expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, y1.getTree());

							if ( state.backtracking==0 ) {
							               actions.checkAugAssign(actions.castExpr((lhs!=null?((PythonTree)lhs.getTree()):null)));
							               stype = new AugAssign((lhs!=null?((PythonTree)lhs.getTree()):null), actions.castExpr((lhs!=null?((PythonTree)lhs.getTree()):null)), (aay!=null?((PythonParser.augassign_return)aay).op:null), actions.castExpr((y1!=null?((PythonParser.yield_expr_return)y1).etype:null)));
							           }
							}

							}
							break;
						case 2 :
							// Python.g:792:11: (aat= augassign rhs= testlist_star_expr[expr_contextType.Load] )
							{
							// Python.g:792:11: (aat= augassign rhs= testlist_star_expr[expr_contextType.Load] )
							// Python.g:792:12: aat= augassign rhs= testlist_star_expr[expr_contextType.Load]
							{
							pushFollow(FOLLOW_augassign_in_expr_stmt2247);
							aat=augassign();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, aat.getTree());

							pushFollow(FOLLOW_testlist_star_expr_in_expr_stmt2251);
							rhs=testlist_star_expr(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, rhs.getTree());

							if ( state.backtracking==0 ) {
							               actions.checkAugAssign(actions.castExpr((lhs!=null?((PythonTree)lhs.getTree()):null)));
							               stype = new AugAssign((lhs!=null?((PythonTree)lhs.getTree()):null), actions.castExpr((lhs!=null?((PythonTree)lhs.getTree()):null)), (aat!=null?((PythonParser.augassign_return)aat).op:null), actions.castExpr((rhs!=null?((PythonTree)rhs.getTree()):null)));
							           }
							}

							}
							break;

					}

					}
					break;
				case 2 :
					// Python.g:799:7: ( testlist_star_expr[null] ':' test[null] ASSIGN )=>lhs= testlist_star_expr[expr_contextType.Store] ':' test[null] (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) )
					{
					pushFollow(FOLLOW_testlist_star_expr_in_expr_stmt2311);
					lhs=testlist_star_expr(expr_contextType.Store);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs.getTree());

					char_literal97=(Token)match(input,COLON,FOLLOW_COLON_in_expr_stmt2314); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					char_literal97_tree = (PythonTree)adaptor.create(char_literal97);
					adaptor.addChild(root_0, char_literal97_tree);
					}

					pushFollow(FOLLOW_test_in_expr_stmt2316);
					test98=test(null);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, test98.getTree());

					// Python.g:800:9: (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) )
					int alt52=3;
					int LA52_0 = input.LA(1);
					if ( (LA52_0==NEWLINE||LA52_0==SEMI) ) {
						alt52=1;
					}
					else if ( (LA52_0==ASSIGN) ) {
						int LA52_2 = input.LA(2);
						if ( (LA52_2==AWAIT||LA52_2==COMPLEX||(LA52_2 >= DOLLER && LA52_2 <= DOT)||LA52_2==FLOAT||(LA52_2 >= LAMBDA && LA52_2 <= LCURLY)||(LA52_2 >= LONGINT && LA52_2 <= MINUS)||LA52_2==NAME||LA52_2==NOT||LA52_2==PLUS||LA52_2==STAR||(LA52_2 >= STRING && LA52_2 <= TILDE)) ) {
							alt52=2;
						}
						else if ( (LA52_2==YIELD) ) {
							alt52=3;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 52, 2, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 52, 0, input);
						throw nvae;
					}

					switch (alt52) {
						case 1 :
							// Python.g:801:9: 
							{
							}
							break;
						case 2 :
							// Python.g:801:11: ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ )
							{
							// Python.g:801:11: ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ )
							// Python.g:801:12: (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+
							{
							// Python.g:801:12: (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+
							int cnt50=0;
							loop50:
							while (true) {
								int alt50=2;
								int LA50_0 = input.LA(1);
								if ( (LA50_0==ASSIGN) ) {
									alt50=1;
								}

								switch (alt50) {
								case 1 :
									// Python.g:801:13: at= ASSIGN t+= testlist_star_expr[expr_contextType.Store]
									{
									at=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_expr_stmt2343); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									at_tree = (PythonTree)adaptor.create(at);
									adaptor.addChild(root_0, at_tree);
									}

									pushFollow(FOLLOW_testlist_star_expr_in_expr_stmt2347);
									t=testlist_star_expr(expr_contextType.Store);
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

									if (list_t==null) list_t=new ArrayList<Object>();
									list_t.add(t.getTree());
									}
									break;

								default :
									if ( cnt50 >= 1 ) break loop50;
									if (state.backtracking>0) {state.failed=true; return retval;}
									EarlyExitException eee = new EarlyExitException(50, input);
									throw eee;
								}
								cnt50++;
							}

							if ( state.backtracking==0 ) {
							                stype = new Assign((lhs!=null?((PythonTree)lhs.getTree()):null), actions.makeAssignTargets(
							                    actions.castExpr((lhs!=null?((PythonTree)lhs.getTree()):null)), list_t), actions.makeAssignValue(list_t));
							            }
							}

							}
							break;
						case 3 :
							// Python.g:807:11: ( (ay= ASSIGN y2+= yield_expr )+ )
							{
							// Python.g:807:11: ( (ay= ASSIGN y2+= yield_expr )+ )
							// Python.g:807:12: (ay= ASSIGN y2+= yield_expr )+
							{
							// Python.g:807:12: (ay= ASSIGN y2+= yield_expr )+
							int cnt51=0;
							loop51:
							while (true) {
								int alt51=2;
								int LA51_0 = input.LA(1);
								if ( (LA51_0==ASSIGN) ) {
									alt51=1;
								}

								switch (alt51) {
								case 1 :
									// Python.g:807:13: ay= ASSIGN y2+= yield_expr
									{
									ay=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_expr_stmt2392); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									ay_tree = (PythonTree)adaptor.create(ay);
									adaptor.addChild(root_0, ay_tree);
									}

									pushFollow(FOLLOW_yield_expr_in_expr_stmt2396);
									y2=yield_expr();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, y2.getTree());

									if (list_y2==null) list_y2=new ArrayList<Object>();
									list_y2.add(y2.getTree());
									}
									break;

								default :
									if ( cnt51 >= 1 ) break loop51;
									if (state.backtracking>0) {state.failed=true; return retval;}
									EarlyExitException eee = new EarlyExitException(51, input);
									throw eee;
								}
								cnt51++;
							}

							if ( state.backtracking==0 ) {
							                stype = new Assign((lhs!=null?(lhs.start):null), actions.makeAssignTargets(
							                    actions.castExpr((lhs!=null?((PythonTree)lhs.getTree()):null)), list_y2), actions.makeAssignValue(list_y2));
							            }
							}

							}
							break;

					}

					}
					break;
				case 3 :
					// Python.g:814:7: ( testlist_star_expr[null] ASSIGN )=>lhs= testlist_star_expr[expr_contextType.Store] (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) )
					{
					pushFollow(FOLLOW_testlist_star_expr_in_expr_stmt2453);
					lhs=testlist_star_expr(expr_contextType.Store);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs.getTree());

					// Python.g:815:9: (| ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ ) | ( (ay= ASSIGN y2+= yield_expr )+ ) )
					int alt55=3;
					int LA55_0 = input.LA(1);
					if ( (LA55_0==NEWLINE||LA55_0==SEMI) ) {
						alt55=1;
					}
					else if ( (LA55_0==ASSIGN) ) {
						int LA55_2 = input.LA(2);
						if ( (LA55_2==AWAIT||LA55_2==COMPLEX||(LA55_2 >= DOLLER && LA55_2 <= DOT)||LA55_2==FLOAT||(LA55_2 >= LAMBDA && LA55_2 <= LCURLY)||(LA55_2 >= LONGINT && LA55_2 <= MINUS)||LA55_2==NAME||LA55_2==NOT||LA55_2==PLUS||LA55_2==STAR||(LA55_2 >= STRING && LA55_2 <= TILDE)) ) {
							alt55=2;
						}
						else if ( (LA55_2==YIELD) ) {
							alt55=3;
						}

						else {
							if (state.backtracking>0) {state.failed=true; return retval;}
							int nvaeMark = input.mark();
							try {
								input.consume();
								NoViableAltException nvae =
									new NoViableAltException("", 55, 2, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 55, 0, input);
						throw nvae;
					}

					switch (alt55) {
						case 1 :
							// Python.g:816:9: 
							{
							}
							break;
						case 2 :
							// Python.g:816:11: ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ )
							{
							// Python.g:816:11: ( (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+ )
							// Python.g:816:12: (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+
							{
							// Python.g:816:12: (at= ASSIGN t+= testlist_star_expr[expr_contextType.Store] )+
							int cnt53=0;
							loop53:
							while (true) {
								int alt53=2;
								int LA53_0 = input.LA(1);
								if ( (LA53_0==ASSIGN) ) {
									alt53=1;
								}

								switch (alt53) {
								case 1 :
									// Python.g:816:13: at= ASSIGN t+= testlist_star_expr[expr_contextType.Store]
									{
									at=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_expr_stmt2480); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									at_tree = (PythonTree)adaptor.create(at);
									adaptor.addChild(root_0, at_tree);
									}

									pushFollow(FOLLOW_testlist_star_expr_in_expr_stmt2484);
									t=testlist_star_expr(expr_contextType.Store);
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

									if (list_t==null) list_t=new ArrayList<Object>();
									list_t.add(t.getTree());
									}
									break;

								default :
									if ( cnt53 >= 1 ) break loop53;
									if (state.backtracking>0) {state.failed=true; return retval;}
									EarlyExitException eee = new EarlyExitException(53, input);
									throw eee;
								}
								cnt53++;
							}

							if ( state.backtracking==0 ) {
							                stype = new Assign((lhs!=null?((PythonTree)lhs.getTree()):null), actions.makeAssignTargets(
							                    actions.castExpr((lhs!=null?((PythonTree)lhs.getTree()):null)), list_t), actions.makeAssignValue(list_t));
							            }
							}

							}
							break;
						case 3 :
							// Python.g:822:11: ( (ay= ASSIGN y2+= yield_expr )+ )
							{
							// Python.g:822:11: ( (ay= ASSIGN y2+= yield_expr )+ )
							// Python.g:822:12: (ay= ASSIGN y2+= yield_expr )+
							{
							// Python.g:822:12: (ay= ASSIGN y2+= yield_expr )+
							int cnt54=0;
							loop54:
							while (true) {
								int alt54=2;
								int LA54_0 = input.LA(1);
								if ( (LA54_0==ASSIGN) ) {
									alt54=1;
								}

								switch (alt54) {
								case 1 :
									// Python.g:822:13: ay= ASSIGN y2+= yield_expr
									{
									ay=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_expr_stmt2529); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									ay_tree = (PythonTree)adaptor.create(ay);
									adaptor.addChild(root_0, ay_tree);
									}

									pushFollow(FOLLOW_yield_expr_in_expr_stmt2533);
									y2=yield_expr();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, y2.getTree());

									if (list_y2==null) list_y2=new ArrayList<Object>();
									list_y2.add(y2.getTree());
									}
									break;

								default :
									if ( cnt54 >= 1 ) break loop54;
									if (state.backtracking>0) {state.failed=true; return retval;}
									EarlyExitException eee = new EarlyExitException(54, input);
									throw eee;
								}
								cnt54++;
							}

							if ( state.backtracking==0 ) {
							                stype = new Assign((lhs!=null?(lhs.start):null), actions.makeAssignTargets(
							                    actions.castExpr((lhs!=null?((PythonTree)lhs.getTree()):null)), list_y2), actions.makeAssignValue(list_y2));
							            }
							}

							}
							break;

					}

					}
					break;
				case 4 :
					// Python.g:829:7: lhs= testlist_star_expr[expr_contextType.Load]
					{
					pushFollow(FOLLOW_testlist_star_expr_in_expr_stmt2581);
					lhs=testlist_star_expr(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, lhs.getTree());

					if ( state.backtracking==0 ) {
					          stype = new Expr((lhs!=null?(lhs.start):null), actions.castExpr((lhs!=null?((PythonTree)lhs.getTree()):null)));
					      }
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (stype != null) {
			        retval.tree = stype;
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr_stmt"


	public static class testlist_star_expr_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "testlist_star_expr"
	// Python.g:837:1: testlist_star_expr[expr_contextType ctype] : ( ( ( test[null] | star_expr[null] ) COMMA )=> (t+= test_or_star_expr[ctype] ) ( options {greedy=true; } : COMMA (t+= test_or_star_expr[ctype] ) )* ( COMMA )? | ( test[ctype] | star_expr[ctype] ) );
	public final PythonParser.testlist_star_expr_return testlist_star_expr(expr_contextType ctype) throws RecognitionException {
		PythonParser.testlist_star_expr_return retval = new PythonParser.testlist_star_expr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COMMA99=null;
		Token COMMA100=null;
		List<Object> list_t=null;
		ParserRuleReturnScope test101 =null;
		ParserRuleReturnScope star_expr102 =null;
		RuleReturnScope t = null;
		PythonTree COMMA99_tree=null;
		PythonTree COMMA100_tree=null;


		    expr etype = null;

		try {
			// Python.g:847:5: ( ( ( test[null] | star_expr[null] ) COMMA )=> (t+= test_or_star_expr[ctype] ) ( options {greedy=true; } : COMMA (t+= test_or_star_expr[ctype] ) )* ( COMMA )? | ( test[ctype] | star_expr[ctype] ) )
			int alt60=2;
			switch ( input.LA(1) ) {
			case NOT:
				{
				int LA60_1 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case PLUS:
				{
				int LA60_2 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case MINUS:
				{
				int LA60_3 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case TILDE:
				{
				int LA60_4 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case AWAIT:
				{
				int LA60_5 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case LPAREN:
				{
				int LA60_6 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case LBRACK:
				{
				int LA60_7 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case LCURLY:
				{
				int LA60_8 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case NAME:
				{
				int LA60_9 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case LONGINT:
				{
				int LA60_10 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case FLOAT:
				{
				int LA60_11 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case COMPLEX:
				{
				int LA60_12 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case DOT:
				{
				int LA60_13 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case DOLLER:
				{
				int LA60_14 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case STRING:
				{
				int LA60_15 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case LAMBDA:
				{
				int LA60_16 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			case STAR:
				{
				int LA60_17 = input.LA(2);
				if ( (synpred4_Python()) ) {
					alt60=1;
				}
				else if ( (true) ) {
					alt60=2;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 60, 0, input);
				throw nvae;
			}
			switch (alt60) {
				case 1 :
					// Python.g:847:7: ( ( test[null] | star_expr[null] ) COMMA )=> (t+= test_or_star_expr[ctype] ) ( options {greedy=true; } : COMMA (t+= test_or_star_expr[ctype] ) )* ( COMMA )?
					{
					root_0 = (PythonTree)adaptor.nil();


					// Python.g:849:6: (t+= test_or_star_expr[ctype] )
					// Python.g:849:7: t+= test_or_star_expr[ctype]
					{
					pushFollow(FOLLOW_test_or_star_expr_in_testlist_star_expr2655);
					t=test_or_star_expr(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

					if (list_t==null) list_t=new ArrayList<Object>();
					list_t.add(t.getTree());
					}

					// Python.g:849:36: ( options {greedy=true; } : COMMA (t+= test_or_star_expr[ctype] ) )*
					loop57:
					while (true) {
						int alt57=2;
						int LA57_0 = input.LA(1);
						if ( (LA57_0==COMMA) ) {
							int LA57_1 = input.LA(2);
							if ( (LA57_1==AWAIT||LA57_1==COMPLEX||(LA57_1 >= DOLLER && LA57_1 <= DOT)||LA57_1==FLOAT||(LA57_1 >= LAMBDA && LA57_1 <= LCURLY)||(LA57_1 >= LONGINT && LA57_1 <= MINUS)||LA57_1==NAME||LA57_1==NOT||LA57_1==PLUS||LA57_1==STAR||(LA57_1 >= STRING && LA57_1 <= TILDE)) ) {
								alt57=1;
							}

						}

						switch (alt57) {
						case 1 :
							// Python.g:849:60: COMMA (t+= test_or_star_expr[ctype] )
							{
							COMMA99=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist_star_expr2667); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA99_tree = (PythonTree)adaptor.create(COMMA99);
							adaptor.addChild(root_0, COMMA99_tree);
							}

							// Python.g:849:66: (t+= test_or_star_expr[ctype] )
							// Python.g:849:67: t+= test_or_star_expr[ctype]
							{
							pushFollow(FOLLOW_test_or_star_expr_in_testlist_star_expr2672);
							t=test_or_star_expr(ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

							if (list_t==null) list_t=new ArrayList<Object>();
							list_t.add(t.getTree());
							}

							}
							break;

						default :
							break loop57;
						}
					}

					// Python.g:849:98: ( COMMA )?
					int alt58=2;
					int LA58_0 = input.LA(1);
					if ( (LA58_0==COMMA) ) {
						alt58=1;
					}
					switch (alt58) {
						case 1 :
							// Python.g:849:99: COMMA
							{
							COMMA100=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist_star_expr2679); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA100_tree = (PythonTree)adaptor.create(COMMA100);
							adaptor.addChild(root_0, COMMA100_tree);
							}

							}
							break;

					}

					if ( state.backtracking==0 ) {
					          etype = new Tuple((retval.start), actions.castExprs(list_t), ctype);
					      }
					}
					break;
				case 2 :
					// Python.g:853:7: ( test[ctype] | star_expr[ctype] )
					{
					root_0 = (PythonTree)adaptor.nil();


					// Python.g:853:7: ( test[ctype] | star_expr[ctype] )
					int alt59=2;
					int LA59_0 = input.LA(1);
					if ( (LA59_0==AWAIT||LA59_0==COMPLEX||(LA59_0 >= DOLLER && LA59_0 <= DOT)||LA59_0==FLOAT||(LA59_0 >= LAMBDA && LA59_0 <= LCURLY)||(LA59_0 >= LONGINT && LA59_0 <= MINUS)||LA59_0==NAME||LA59_0==NOT||LA59_0==PLUS||(LA59_0 >= STRING && LA59_0 <= TILDE)) ) {
						alt59=1;
					}
					else if ( (LA59_0==STAR) ) {
						alt59=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 59, 0, input);
						throw nvae;
					}

					switch (alt59) {
						case 1 :
							// Python.g:853:8: test[ctype]
							{
							pushFollow(FOLLOW_test_in_testlist_star_expr2698);
							test101=test(ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, test101.getTree());

							}
							break;
						case 2 :
							// Python.g:854:7: star_expr[ctype]
							{
							pushFollow(FOLLOW_star_expr_in_testlist_star_expr2707);
							star_expr102=star_expr(ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, star_expr102.getTree());

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (etype != null) {
			        retval.tree = etype;
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "testlist_star_expr"


	public static class test_or_star_expr_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "test_or_star_expr"
	// Python.g:858:1: test_or_star_expr[expr_contextType ctype] : ( test[ctype] | star_expr[ctype] );
	public final PythonParser.test_or_star_expr_return test_or_star_expr(expr_contextType ctype) throws RecognitionException {
		PythonParser.test_or_star_expr_return retval = new PythonParser.test_or_star_expr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		ParserRuleReturnScope test103 =null;
		ParserRuleReturnScope star_expr104 =null;


		try {
			// Python.g:859:5: ( test[ctype] | star_expr[ctype] )
			int alt61=2;
			int LA61_0 = input.LA(1);
			if ( (LA61_0==AWAIT||LA61_0==COMPLEX||(LA61_0 >= DOLLER && LA61_0 <= DOT)||LA61_0==FLOAT||(LA61_0 >= LAMBDA && LA61_0 <= LCURLY)||(LA61_0 >= LONGINT && LA61_0 <= MINUS)||LA61_0==NAME||LA61_0==NOT||LA61_0==PLUS||(LA61_0 >= STRING && LA61_0 <= TILDE)) ) {
				alt61=1;
			}
			else if ( (LA61_0==STAR) ) {
				alt61=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 61, 0, input);
				throw nvae;
			}

			switch (alt61) {
				case 1 :
					// Python.g:859:7: test[ctype]
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_test_in_test_or_star_expr2728);
					test103=test(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, test103.getTree());

					}
					break;
				case 2 :
					// Python.g:860:7: star_expr[ctype]
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_star_expr_in_test_or_star_expr2737);
					star_expr104=star_expr(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, star_expr104.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "test_or_star_expr"


	public static class augassign_return extends ParserRuleReturnScope {
		public operatorType op;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "augassign"
	// Python.g:865:1: augassign returns [operatorType op] : ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | ATEQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL );
	public final PythonParser.augassign_return augassign() throws RecognitionException {
		PythonParser.augassign_return retval = new PythonParser.augassign_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token PLUSEQUAL105=null;
		Token MINUSEQUAL106=null;
		Token STAREQUAL107=null;
		Token ATEQUAL108=null;
		Token SLASHEQUAL109=null;
		Token PERCENTEQUAL110=null;
		Token AMPEREQUAL111=null;
		Token VBAREQUAL112=null;
		Token CIRCUMFLEXEQUAL113=null;
		Token LEFTSHIFTEQUAL114=null;
		Token RIGHTSHIFTEQUAL115=null;
		Token DOUBLESTAREQUAL116=null;
		Token DOUBLESLASHEQUAL117=null;

		PythonTree PLUSEQUAL105_tree=null;
		PythonTree MINUSEQUAL106_tree=null;
		PythonTree STAREQUAL107_tree=null;
		PythonTree ATEQUAL108_tree=null;
		PythonTree SLASHEQUAL109_tree=null;
		PythonTree PERCENTEQUAL110_tree=null;
		PythonTree AMPEREQUAL111_tree=null;
		PythonTree VBAREQUAL112_tree=null;
		PythonTree CIRCUMFLEXEQUAL113_tree=null;
		PythonTree LEFTSHIFTEQUAL114_tree=null;
		PythonTree RIGHTSHIFTEQUAL115_tree=null;
		PythonTree DOUBLESTAREQUAL116_tree=null;
		PythonTree DOUBLESLASHEQUAL117_tree=null;

		try {
			// Python.g:867:5: ( PLUSEQUAL | MINUSEQUAL | STAREQUAL | ATEQUAL | SLASHEQUAL | PERCENTEQUAL | AMPEREQUAL | VBAREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL )
			int alt62=13;
			switch ( input.LA(1) ) {
			case PLUSEQUAL:
				{
				alt62=1;
				}
				break;
			case MINUSEQUAL:
				{
				alt62=2;
				}
				break;
			case STAREQUAL:
				{
				alt62=3;
				}
				break;
			case ATEQUAL:
				{
				alt62=4;
				}
				break;
			case SLASHEQUAL:
				{
				alt62=5;
				}
				break;
			case PERCENTEQUAL:
				{
				alt62=6;
				}
				break;
			case AMPEREQUAL:
				{
				alt62=7;
				}
				break;
			case VBAREQUAL:
				{
				alt62=8;
				}
				break;
			case CIRCUMFLEXEQUAL:
				{
				alt62=9;
				}
				break;
			case LEFTSHIFTEQUAL:
				{
				alt62=10;
				}
				break;
			case RIGHTSHIFTEQUAL:
				{
				alt62=11;
				}
				break;
			case DOUBLESTAREQUAL:
				{
				alt62=12;
				}
				break;
			case DOUBLESLASHEQUAL:
				{
				alt62=13;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 62, 0, input);
				throw nvae;
			}
			switch (alt62) {
				case 1 :
					// Python.g:867:7: PLUSEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					PLUSEQUAL105=(Token)match(input,PLUSEQUAL,FOLLOW_PLUSEQUAL_in_augassign2765); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PLUSEQUAL105_tree = (PythonTree)adaptor.create(PLUSEQUAL105);
					adaptor.addChild(root_0, PLUSEQUAL105_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.Add;
					        }
					}
					break;
				case 2 :
					// Python.g:871:7: MINUSEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					MINUSEQUAL106=(Token)match(input,MINUSEQUAL,FOLLOW_MINUSEQUAL_in_augassign2783); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					MINUSEQUAL106_tree = (PythonTree)adaptor.create(MINUSEQUAL106);
					adaptor.addChild(root_0, MINUSEQUAL106_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.Sub;
					        }
					}
					break;
				case 3 :
					// Python.g:875:7: STAREQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					STAREQUAL107=(Token)match(input,STAREQUAL,FOLLOW_STAREQUAL_in_augassign2801); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STAREQUAL107_tree = (PythonTree)adaptor.create(STAREQUAL107);
					adaptor.addChild(root_0, STAREQUAL107_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.Mult;
					        }
					}
					break;
				case 4 :
					// Python.g:879:7: ATEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					ATEQUAL108=(Token)match(input,ATEQUAL,FOLLOW_ATEQUAL_in_augassign2819); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ATEQUAL108_tree = (PythonTree)adaptor.create(ATEQUAL108);
					adaptor.addChild(root_0, ATEQUAL108_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.MatMult;
					        }
					}
					break;
				case 5 :
					// Python.g:883:7: SLASHEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					SLASHEQUAL109=(Token)match(input,SLASHEQUAL,FOLLOW_SLASHEQUAL_in_augassign2837); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SLASHEQUAL109_tree = (PythonTree)adaptor.create(SLASHEQUAL109);
					adaptor.addChild(root_0, SLASHEQUAL109_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.Div;
					        }
					}
					break;
				case 6 :
					// Python.g:887:7: PERCENTEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					PERCENTEQUAL110=(Token)match(input,PERCENTEQUAL,FOLLOW_PERCENTEQUAL_in_augassign2855); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PERCENTEQUAL110_tree = (PythonTree)adaptor.create(PERCENTEQUAL110);
					adaptor.addChild(root_0, PERCENTEQUAL110_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.Mod;
					        }
					}
					break;
				case 7 :
					// Python.g:891:7: AMPEREQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					AMPEREQUAL111=(Token)match(input,AMPEREQUAL,FOLLOW_AMPEREQUAL_in_augassign2873); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AMPEREQUAL111_tree = (PythonTree)adaptor.create(AMPEREQUAL111);
					adaptor.addChild(root_0, AMPEREQUAL111_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.BitAnd;
					        }
					}
					break;
				case 8 :
					// Python.g:895:7: VBAREQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					VBAREQUAL112=(Token)match(input,VBAREQUAL,FOLLOW_VBAREQUAL_in_augassign2891); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					VBAREQUAL112_tree = (PythonTree)adaptor.create(VBAREQUAL112);
					adaptor.addChild(root_0, VBAREQUAL112_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.BitOr;
					        }
					}
					break;
				case 9 :
					// Python.g:899:7: CIRCUMFLEXEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					CIRCUMFLEXEQUAL113=(Token)match(input,CIRCUMFLEXEQUAL,FOLLOW_CIRCUMFLEXEQUAL_in_augassign2909); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					CIRCUMFLEXEQUAL113_tree = (PythonTree)adaptor.create(CIRCUMFLEXEQUAL113);
					adaptor.addChild(root_0, CIRCUMFLEXEQUAL113_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.BitXor;
					        }
					}
					break;
				case 10 :
					// Python.g:903:7: LEFTSHIFTEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					LEFTSHIFTEQUAL114=(Token)match(input,LEFTSHIFTEQUAL,FOLLOW_LEFTSHIFTEQUAL_in_augassign2927); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LEFTSHIFTEQUAL114_tree = (PythonTree)adaptor.create(LEFTSHIFTEQUAL114);
					adaptor.addChild(root_0, LEFTSHIFTEQUAL114_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.LShift;
					        }
					}
					break;
				case 11 :
					// Python.g:907:7: RIGHTSHIFTEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					RIGHTSHIFTEQUAL115=(Token)match(input,RIGHTSHIFTEQUAL,FOLLOW_RIGHTSHIFTEQUAL_in_augassign2945); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RIGHTSHIFTEQUAL115_tree = (PythonTree)adaptor.create(RIGHTSHIFTEQUAL115);
					adaptor.addChild(root_0, RIGHTSHIFTEQUAL115_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.RShift;
					        }
					}
					break;
				case 12 :
					// Python.g:911:7: DOUBLESTAREQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					DOUBLESTAREQUAL116=(Token)match(input,DOUBLESTAREQUAL,FOLLOW_DOUBLESTAREQUAL_in_augassign2963); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOUBLESTAREQUAL116_tree = (PythonTree)adaptor.create(DOUBLESTAREQUAL116);
					adaptor.addChild(root_0, DOUBLESTAREQUAL116_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.Pow;
					        }
					}
					break;
				case 13 :
					// Python.g:915:7: DOUBLESLASHEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					DOUBLESLASHEQUAL117=(Token)match(input,DOUBLESLASHEQUAL,FOLLOW_DOUBLESLASHEQUAL_in_augassign2981); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOUBLESLASHEQUAL117_tree = (PythonTree)adaptor.create(DOUBLESLASHEQUAL117);
					adaptor.addChild(root_0, DOUBLESLASHEQUAL117_tree);
					}

					if ( state.backtracking==0 ) {
					            retval.op = operatorType.FloorDiv;
					        }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "augassign"


	public static class del_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "del_stmt"
	// Python.g:922:1: del_stmt : DELETE del_list ;
	public final PythonParser.del_stmt_return del_stmt() throws RecognitionException {
		PythonParser.del_stmt_return retval = new PythonParser.del_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token DELETE118=null;
		ParserRuleReturnScope del_list119 =null;

		PythonTree DELETE118_tree=null;


		    stmt stype = null;

		try {
			// Python.g:929:5: ( DELETE del_list )
			// Python.g:929:7: DELETE del_list
			{
			root_0 = (PythonTree)adaptor.nil();


			DELETE118=(Token)match(input,DELETE,FOLLOW_DELETE_in_del_stmt3019); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			DELETE118_tree = (PythonTree)adaptor.create(DELETE118);
			adaptor.addChild(root_0, DELETE118_tree);
			}

			pushFollow(FOLLOW_del_list_in_del_stmt3021);
			del_list119=del_list();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, del_list119.getTree());

			if ( state.backtracking==0 ) {
			          stype = new Delete(DELETE118, (del_list119!=null?((PythonParser.del_list_return)del_list119).etypes:null));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "del_stmt"


	public static class pass_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "pass_stmt"
	// Python.g:936:1: pass_stmt : PASS ;
	public final PythonParser.pass_stmt_return pass_stmt() throws RecognitionException {
		PythonParser.pass_stmt_return retval = new PythonParser.pass_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token PASS120=null;

		PythonTree PASS120_tree=null;


		    stmt stype = null;

		try {
			// Python.g:943:5: ( PASS )
			// Python.g:943:7: PASS
			{
			root_0 = (PythonTree)adaptor.nil();


			PASS120=(Token)match(input,PASS,FOLLOW_PASS_in_pass_stmt3057); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			PASS120_tree = (PythonTree)adaptor.create(PASS120);
			adaptor.addChild(root_0, PASS120_tree);
			}

			if ( state.backtracking==0 ) {
			          stype = new Pass(PASS120);
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "pass_stmt"


	public static class flow_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "flow_stmt"
	// Python.g:950:1: flow_stmt : ( break_stmt | continue_stmt | return_stmt | raise_stmt | yield_stmt );
	public final PythonParser.flow_stmt_return flow_stmt() throws RecognitionException {
		PythonParser.flow_stmt_return retval = new PythonParser.flow_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		ParserRuleReturnScope break_stmt121 =null;
		ParserRuleReturnScope continue_stmt122 =null;
		ParserRuleReturnScope return_stmt123 =null;
		ParserRuleReturnScope raise_stmt124 =null;
		ParserRuleReturnScope yield_stmt125 =null;


		try {
			// Python.g:951:5: ( break_stmt | continue_stmt | return_stmt | raise_stmt | yield_stmt )
			int alt63=5;
			switch ( input.LA(1) ) {
			case BREAK:
				{
				alt63=1;
				}
				break;
			case CONTINUE:
				{
				alt63=2;
				}
				break;
			case RETURN:
				{
				alt63=3;
				}
				break;
			case RAISE:
				{
				alt63=4;
				}
				break;
			case YIELD:
				{
				alt63=5;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 63, 0, input);
				throw nvae;
			}
			switch (alt63) {
				case 1 :
					// Python.g:951:7: break_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_break_stmt_in_flow_stmt3083);
					break_stmt121=break_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, break_stmt121.getTree());

					}
					break;
				case 2 :
					// Python.g:952:7: continue_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_continue_stmt_in_flow_stmt3091);
					continue_stmt122=continue_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, continue_stmt122.getTree());

					}
					break;
				case 3 :
					// Python.g:953:7: return_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_return_stmt_in_flow_stmt3099);
					return_stmt123=return_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, return_stmt123.getTree());

					}
					break;
				case 4 :
					// Python.g:954:7: raise_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_raise_stmt_in_flow_stmt3107);
					raise_stmt124=raise_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, raise_stmt124.getTree());

					}
					break;
				case 5 :
					// Python.g:955:7: yield_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_yield_stmt_in_flow_stmt3115);
					yield_stmt125=yield_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, yield_stmt125.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "flow_stmt"


	public static class break_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "break_stmt"
	// Python.g:959:1: break_stmt : BREAK ;
	public final PythonParser.break_stmt_return break_stmt() throws RecognitionException {
		PythonParser.break_stmt_return retval = new PythonParser.break_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token BREAK126=null;

		PythonTree BREAK126_tree=null;


		    stmt stype = null;

		try {
			// Python.g:966:5: ( BREAK )
			// Python.g:966:7: BREAK
			{
			root_0 = (PythonTree)adaptor.nil();


			BREAK126=(Token)match(input,BREAK,FOLLOW_BREAK_in_break_stmt3143); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			BREAK126_tree = (PythonTree)adaptor.create(BREAK126);
			adaptor.addChild(root_0, BREAK126_tree);
			}

			if ( state.backtracking==0 ) {
			          stype = new Break(BREAK126);
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "break_stmt"


	public static class continue_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "continue_stmt"
	// Python.g:973:1: continue_stmt : CONTINUE ;
	public final PythonParser.continue_stmt_return continue_stmt() throws RecognitionException {
		PythonParser.continue_stmt_return retval = new PythonParser.continue_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token CONTINUE127=null;

		PythonTree CONTINUE127_tree=null;


		    stmt stype = null;

		try {
			// Python.g:980:5: ( CONTINUE )
			// Python.g:980:7: CONTINUE
			{
			root_0 = (PythonTree)adaptor.nil();


			CONTINUE127=(Token)match(input,CONTINUE,FOLLOW_CONTINUE_in_continue_stmt3179); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			CONTINUE127_tree = (PythonTree)adaptor.create(CONTINUE127);
			adaptor.addChild(root_0, CONTINUE127_tree);
			}

			if ( state.backtracking==0 ) {
			          if (!suite_stack.isEmpty() && suite_stack.peek().continueIllegal) {
			              errorHandler.error("'continue' not supported inside 'finally' clause", new PythonTree((retval.start)));
			          }
			          stype = new Continue(CONTINUE127);
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "continue_stmt"


	public static class return_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "return_stmt"
	// Python.g:990:1: return_stmt : RETURN ( testlist[expr_contextType.Load] |) ;
	public final PythonParser.return_stmt_return return_stmt() throws RecognitionException {
		PythonParser.return_stmt_return retval = new PythonParser.return_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token RETURN128=null;
		ParserRuleReturnScope testlist129 =null;

		PythonTree RETURN128_tree=null;


		    stmt stype = null;

		try {
			// Python.g:997:5: ( RETURN ( testlist[expr_contextType.Load] |) )
			// Python.g:997:7: RETURN ( testlist[expr_contextType.Load] |)
			{
			root_0 = (PythonTree)adaptor.nil();


			RETURN128=(Token)match(input,RETURN,FOLLOW_RETURN_in_return_stmt3215); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			RETURN128_tree = (PythonTree)adaptor.create(RETURN128);
			adaptor.addChild(root_0, RETURN128_tree);
			}

			// Python.g:998:7: ( testlist[expr_contextType.Load] |)
			int alt64=2;
			int LA64_0 = input.LA(1);
			if ( (LA64_0==AWAIT||LA64_0==COMPLEX||(LA64_0 >= DOLLER && LA64_0 <= DOT)||LA64_0==FLOAT||(LA64_0 >= LAMBDA && LA64_0 <= LCURLY)||(LA64_0 >= LONGINT && LA64_0 <= MINUS)||LA64_0==NAME||LA64_0==NOT||LA64_0==PLUS||(LA64_0 >= STRING && LA64_0 <= TILDE)) ) {
				alt64=1;
			}
			else if ( (LA64_0==NEWLINE||LA64_0==SEMI) ) {
				alt64=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 64, 0, input);
				throw nvae;
			}

			switch (alt64) {
				case 1 :
					// Python.g:998:8: testlist[expr_contextType.Load]
					{
					pushFollow(FOLLOW_testlist_in_return_stmt3224);
					testlist129=testlist(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist129.getTree());

					if ( state.backtracking==0 ) {
					           stype = new Return(RETURN128, actions.castExpr((testlist129!=null?((PythonTree)testlist129.getTree()):null)));
					       }
					}
					break;
				case 2 :
					// Python.g:1003:8: 
					{
					if ( state.backtracking==0 ) {
					           stype = new Return(RETURN128, null);
					       }
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "return_stmt"


	public static class yield_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "yield_stmt"
	// Python.g:1010:1: yield_stmt : yield_expr ;
	public final PythonParser.yield_stmt_return yield_stmt() throws RecognitionException {
		PythonParser.yield_stmt_return retval = new PythonParser.yield_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		ParserRuleReturnScope yield_expr130 =null;



		    stmt stype = null;

		try {
			// Python.g:1017:5: ( yield_expr )
			// Python.g:1017:7: yield_expr
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_yield_expr_in_yield_stmt3289);
			yield_expr130=yield_expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, yield_expr130.getTree());

			if ( state.backtracking==0 ) {
			        stype = new Expr((yield_expr130!=null?(yield_expr130.start):null), actions.castExpr((yield_expr130!=null?((PythonParser.yield_expr_return)yield_expr130).etype:null)));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "yield_stmt"


	public static class raise_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "raise_stmt"
	// Python.g:1024:1: raise_stmt : RAISE (t1= test[expr_contextType.Load] ( FROM t2= test[expr_contextType.Load] )? )? ;
	public final PythonParser.raise_stmt_return raise_stmt() throws RecognitionException {
		PythonParser.raise_stmt_return retval = new PythonParser.raise_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token RAISE131=null;
		Token FROM132=null;
		ParserRuleReturnScope t1 =null;
		ParserRuleReturnScope t2 =null;

		PythonTree RAISE131_tree=null;
		PythonTree FROM132_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1031:5: ( RAISE (t1= test[expr_contextType.Load] ( FROM t2= test[expr_contextType.Load] )? )? )
			// Python.g:1031:7: RAISE (t1= test[expr_contextType.Load] ( FROM t2= test[expr_contextType.Load] )? )?
			{
			root_0 = (PythonTree)adaptor.nil();


			RAISE131=(Token)match(input,RAISE,FOLLOW_RAISE_in_raise_stmt3325); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			RAISE131_tree = (PythonTree)adaptor.create(RAISE131);
			adaptor.addChild(root_0, RAISE131_tree);
			}

			// Python.g:1031:13: (t1= test[expr_contextType.Load] ( FROM t2= test[expr_contextType.Load] )? )?
			int alt66=2;
			int LA66_0 = input.LA(1);
			if ( (LA66_0==AWAIT||LA66_0==COMPLEX||(LA66_0 >= DOLLER && LA66_0 <= DOT)||LA66_0==FLOAT||(LA66_0 >= LAMBDA && LA66_0 <= LCURLY)||(LA66_0 >= LONGINT && LA66_0 <= MINUS)||LA66_0==NAME||LA66_0==NOT||LA66_0==PLUS||(LA66_0 >= STRING && LA66_0 <= TILDE)) ) {
				alt66=1;
			}
			switch (alt66) {
				case 1 :
					// Python.g:1031:14: t1= test[expr_contextType.Load] ( FROM t2= test[expr_contextType.Load] )?
					{
					pushFollow(FOLLOW_test_in_raise_stmt3330);
					t1=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());

					// Python.g:1031:45: ( FROM t2= test[expr_contextType.Load] )?
					int alt65=2;
					int LA65_0 = input.LA(1);
					if ( (LA65_0==FROM) ) {
						alt65=1;
					}
					switch (alt65) {
						case 1 :
							// Python.g:1031:46: FROM t2= test[expr_contextType.Load]
							{
							FROM132=(Token)match(input,FROM,FOLLOW_FROM_in_raise_stmt3334); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							FROM132_tree = (PythonTree)adaptor.create(FROM132);
							adaptor.addChild(root_0, FROM132_tree);
							}

							pushFollow(FOLLOW_test_in_raise_stmt3338);
							t2=test(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

							}
							break;

					}

					}
					break;

			}

			if ( state.backtracking==0 ) {
			        stype = new Raise(RAISE131, actions.castExpr((t1!=null?((PythonTree)t1.getTree()):null)), actions.castExpr((t2!=null?((PythonTree)t2.getTree()):null)));
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "raise_stmt"


	public static class import_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "import_stmt"
	// Python.g:1038:1: import_stmt : ( import_name | import_from );
	public final PythonParser.import_stmt_return import_stmt() throws RecognitionException {
		PythonParser.import_stmt_return retval = new PythonParser.import_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		ParserRuleReturnScope import_name133 =null;
		ParserRuleReturnScope import_from134 =null;


		try {
			// Python.g:1039:5: ( import_name | import_from )
			int alt67=2;
			int LA67_0 = input.LA(1);
			if ( (LA67_0==IMPORT) ) {
				alt67=1;
			}
			else if ( (LA67_0==FROM) ) {
				alt67=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 67, 0, input);
				throw nvae;
			}

			switch (alt67) {
				case 1 :
					// Python.g:1039:7: import_name
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_import_name_in_import_stmt3367);
					import_name133=import_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, import_name133.getTree());

					}
					break;
				case 2 :
					// Python.g:1040:7: import_from
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_import_from_in_import_stmt3375);
					import_from134=import_from();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, import_from134.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "import_stmt"


	public static class import_name_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "import_name"
	// Python.g:1044:1: import_name : IMPORT dotted_as_names ;
	public final PythonParser.import_name_return import_name() throws RecognitionException {
		PythonParser.import_name_return retval = new PythonParser.import_name_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token IMPORT135=null;
		ParserRuleReturnScope dotted_as_names136 =null;

		PythonTree IMPORT135_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1051:5: ( IMPORT dotted_as_names )
			// Python.g:1051:7: IMPORT dotted_as_names
			{
			root_0 = (PythonTree)adaptor.nil();


			IMPORT135=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_import_name3403); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			IMPORT135_tree = (PythonTree)adaptor.create(IMPORT135);
			adaptor.addChild(root_0, IMPORT135_tree);
			}

			pushFollow(FOLLOW_dotted_as_names_in_import_name3405);
			dotted_as_names136=dotted_as_names();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_as_names136.getTree());

			if ( state.backtracking==0 ) {
			          stype = new Import(IMPORT135, (dotted_as_names136!=null?((PythonParser.dotted_as_names_return)dotted_as_names136).atypes:null));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "import_name"


	public static class import_from_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "import_from"
	// Python.g:1059:1: import_from : FROM ( (d+= DOT )* dotted_name | (d+= DOT )+ ) IMPORT ( STAR |i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN ) ;
	public final PythonParser.import_from_return import_from() throws RecognitionException {
		PythonParser.import_from_return retval = new PythonParser.import_from_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token FROM137=null;
		Token IMPORT139=null;
		Token STAR140=null;
		Token LPAREN141=null;
		Token COMMA142=null;
		Token RPAREN143=null;
		Token d=null;
		List<Object> list_d=null;
		ParserRuleReturnScope i1 =null;
		ParserRuleReturnScope i2 =null;
		ParserRuleReturnScope dotted_name138 =null;

		PythonTree FROM137_tree=null;
		PythonTree IMPORT139_tree=null;
		PythonTree STAR140_tree=null;
		PythonTree LPAREN141_tree=null;
		PythonTree COMMA142_tree=null;
		PythonTree RPAREN143_tree=null;
		PythonTree d_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1066:5: ( FROM ( (d+= DOT )* dotted_name | (d+= DOT )+ ) IMPORT ( STAR |i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN ) )
			// Python.g:1066:7: FROM ( (d+= DOT )* dotted_name | (d+= DOT )+ ) IMPORT ( STAR |i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN )
			{
			root_0 = (PythonTree)adaptor.nil();


			FROM137=(Token)match(input,FROM,FOLLOW_FROM_in_import_from3442); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			FROM137_tree = (PythonTree)adaptor.create(FROM137);
			adaptor.addChild(root_0, FROM137_tree);
			}

			// Python.g:1066:12: ( (d+= DOT )* dotted_name | (d+= DOT )+ )
			int alt70=2;
			alt70 = dfa70.predict(input);
			switch (alt70) {
				case 1 :
					// Python.g:1066:13: (d+= DOT )* dotted_name
					{
					// Python.g:1066:14: (d+= DOT )*
					loop68:
					while (true) {
						int alt68=2;
						int LA68_0 = input.LA(1);
						if ( (LA68_0==DOT) ) {
							alt68=1;
						}

						switch (alt68) {
						case 1 :
							// Python.g:1066:14: d+= DOT
							{
							d=(Token)match(input,DOT,FOLLOW_DOT_in_import_from3447); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							d_tree = (PythonTree)adaptor.create(d);
							adaptor.addChild(root_0, d_tree);
							}

							if (list_d==null) list_d=new ArrayList<Object>();
							list_d.add(d);
							}
							break;

						default :
							break loop68;
						}
					}

					pushFollow(FOLLOW_dotted_name_in_import_from3450);
					dotted_name138=dotted_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_name138.getTree());

					}
					break;
				case 2 :
					// Python.g:1066:35: (d+= DOT )+
					{
					// Python.g:1066:36: (d+= DOT )+
					int cnt69=0;
					loop69:
					while (true) {
						int alt69=2;
						int LA69_0 = input.LA(1);
						if ( (LA69_0==DOT) ) {
							alt69=1;
						}

						switch (alt69) {
						case 1 :
							// Python.g:1066:36: d+= DOT
							{
							d=(Token)match(input,DOT,FOLLOW_DOT_in_import_from3456); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							d_tree = (PythonTree)adaptor.create(d);
							adaptor.addChild(root_0, d_tree);
							}

							if (list_d==null) list_d=new ArrayList<Object>();
							list_d.add(d);
							}
							break;

						default :
							if ( cnt69 >= 1 ) break loop69;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(69, input);
							throw eee;
						}
						cnt69++;
					}

					}
					break;

			}

			IMPORT139=(Token)match(input,IMPORT,FOLLOW_IMPORT_in_import_from3460); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			IMPORT139_tree = (PythonTree)adaptor.create(IMPORT139);
			adaptor.addChild(root_0, IMPORT139_tree);
			}

			// Python.g:1067:9: ( STAR |i1= import_as_names | LPAREN i2= import_as_names ( COMMA )? RPAREN )
			int alt72=3;
			switch ( input.LA(1) ) {
			case STAR:
				{
				alt72=1;
				}
				break;
			case NAME:
				{
				alt72=2;
				}
				break;
			case LPAREN:
				{
				alt72=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 72, 0, input);
				throw nvae;
			}
			switch (alt72) {
				case 1 :
					// Python.g:1067:10: STAR
					{
					STAR140=(Token)match(input,STAR,FOLLOW_STAR_in_import_from3471); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STAR140_tree = (PythonTree)adaptor.create(STAR140);
					adaptor.addChild(root_0, STAR140_tree);
					}

					if ( state.backtracking==0 ) {
					             stype = new ImportFrom(FROM137, actions.makeFromText(list_d, (dotted_name138!=null?((PythonParser.dotted_name_return)dotted_name138).names:null)),
					                 actions.makeModuleNameNode(list_d, (dotted_name138!=null?((PythonParser.dotted_name_return)dotted_name138).names:null)),
					                 actions.makeStarAlias(STAR140), actions.makeLevel(list_d));
					         }
					}
					break;
				case 2 :
					// Python.g:1073:11: i1= import_as_names
					{
					pushFollow(FOLLOW_import_as_names_in_import_from3496);
					i1=import_as_names();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, i1.getTree());

					if ( state.backtracking==0 ) {
					             String dottedText = (dotted_name138!=null?input.toString(dotted_name138.start,dotted_name138.stop):null);
					             if (dottedText != null && dottedText.equals("__future__")) {
					                 List<alias> aliases = (i1!=null?((PythonParser.import_as_names_return)i1).atypes:null);
					                 for(alias a: aliases) {
					                     if (a != null) {
					                         if (a.getInternalName().equals("print_function")) {
					                             printFunction = true;
					                         } else if (a.getInternalName().equals("unicode_literals")) {
					                             unicodeLiterals = true;
					                         }
					                     }
					                 }
					             }
					             stype = new ImportFrom(FROM137, actions.makeFromText(list_d, (dotted_name138!=null?((PythonParser.dotted_name_return)dotted_name138).names:null)),
					                 actions.makeModuleNameNode(list_d, (dotted_name138!=null?((PythonParser.dotted_name_return)dotted_name138).names:null)),
					                 actions.makeAliases((i1!=null?((PythonParser.import_as_names_return)i1).atypes:null)), actions.makeLevel(list_d));
					         }
					}
					break;
				case 3 :
					// Python.g:1092:11: LPAREN i2= import_as_names ( COMMA )? RPAREN
					{
					LPAREN141=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_import_from3519); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LPAREN141_tree = (PythonTree)adaptor.create(LPAREN141);
					adaptor.addChild(root_0, LPAREN141_tree);
					}

					pushFollow(FOLLOW_import_as_names_in_import_from3523);
					i2=import_as_names();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, i2.getTree());

					// Python.g:1092:37: ( COMMA )?
					int alt71=2;
					int LA71_0 = input.LA(1);
					if ( (LA71_0==COMMA) ) {
						alt71=1;
					}
					switch (alt71) {
						case 1 :
							// Python.g:1092:37: COMMA
							{
							COMMA142=(Token)match(input,COMMA,FOLLOW_COMMA_in_import_from3525); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA142_tree = (PythonTree)adaptor.create(COMMA142);
							adaptor.addChild(root_0, COMMA142_tree);
							}

							}
							break;

					}

					RPAREN143=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_import_from3528); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RPAREN143_tree = (PythonTree)adaptor.create(RPAREN143);
					adaptor.addChild(root_0, RPAREN143_tree);
					}

					if ( state.backtracking==0 ) {
					             //XXX: this is almost a complete C&P of the code above - is there some way
					             //     to factor it out?
					             String dottedText = (dotted_name138!=null?input.toString(dotted_name138.start,dotted_name138.stop):null);
					             if (dottedText != null && dottedText.equals("__future__")) {
					                 List<alias> aliases = (i2!=null?((PythonParser.import_as_names_return)i2).atypes:null);
					                 for(alias a: aliases) {
					                     if (a != null) {
					                         if (a.getInternalName().equals("print_function")) {
					                             printFunction = true;
					                         } else if (a.getInternalName().equals("unicode_literals")) {
					                             unicodeLiterals = true;
					                         }
					                     }
					                 }
					             }
					             stype = new ImportFrom(FROM137, actions.makeFromText(list_d, (dotted_name138!=null?((PythonParser.dotted_name_return)dotted_name138).names:null)),
					                 actions.makeModuleNameNode(list_d, (dotted_name138!=null?((PythonParser.dotted_name_return)dotted_name138).names:null)),
					                 actions.makeAliases((i2!=null?((PythonParser.import_as_names_return)i2).atypes:null)), actions.makeLevel(list_d));
					         }
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "import_from"


	public static class import_as_names_return extends ParserRuleReturnScope {
		public List<alias> atypes;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "import_as_names"
	// Python.g:1117:1: import_as_names returns [List<alias> atypes] :n+= import_as_name ( COMMA !n+= import_as_name )* ;
	public final PythonParser.import_as_names_return import_as_names() throws RecognitionException {
		PythonParser.import_as_names_return retval = new PythonParser.import_as_names_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COMMA144=null;
		List<Object> list_n=null;
		RuleReturnScope n = null;
		PythonTree COMMA144_tree=null;

		try {
			// Python.g:1119:5: (n+= import_as_name ( COMMA !n+= import_as_name )* )
			// Python.g:1119:7: n+= import_as_name ( COMMA !n+= import_as_name )*
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_import_as_name_in_import_as_names3577);
			n=import_as_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, n.getTree());

			if (list_n==null) list_n=new ArrayList<Object>();
			list_n.add(n.getTree());
			// Python.g:1119:25: ( COMMA !n+= import_as_name )*
			loop73:
			while (true) {
				int alt73=2;
				int LA73_0 = input.LA(1);
				if ( (LA73_0==COMMA) ) {
					int LA73_2 = input.LA(2);
					if ( (LA73_2==NAME) ) {
						alt73=1;
					}

				}

				switch (alt73) {
				case 1 :
					// Python.g:1119:26: COMMA !n+= import_as_name
					{
					COMMA144=(Token)match(input,COMMA,FOLLOW_COMMA_in_import_as_names3580); if (state.failed) return retval;
					pushFollow(FOLLOW_import_as_name_in_import_as_names3585);
					n=import_as_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, n.getTree());

					if (list_n==null) list_n=new ArrayList<Object>();
					list_n.add(n.getTree());
					}
					break;

				default :
					break loop73;
				}
			}

			if ( state.backtracking==0 ) {
			        retval.atypes = list_n;
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "import_as_names"


	public static class import_as_name_return extends ParserRuleReturnScope {
		public alias atype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "import_as_name"
	// Python.g:1126:1: import_as_name returns [alias atype] : name= NAME ( AS asname= NAME )? ;
	public final PythonParser.import_as_name_return import_as_name() throws RecognitionException {
		PythonParser.import_as_name_return retval = new PythonParser.import_as_name_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token name=null;
		Token asname=null;
		Token AS145=null;

		PythonTree name_tree=null;
		PythonTree asname_tree=null;
		PythonTree AS145_tree=null;

		try {
			// Python.g:1131:5: (name= NAME ( AS asname= NAME )? )
			// Python.g:1131:7: name= NAME ( AS asname= NAME )?
			{
			root_0 = (PythonTree)adaptor.nil();


			name=(Token)match(input,NAME,FOLLOW_NAME_in_import_as_name3626); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			name_tree = (PythonTree)adaptor.create(name);
			adaptor.addChild(root_0, name_tree);
			}

			// Python.g:1131:17: ( AS asname= NAME )?
			int alt74=2;
			int LA74_0 = input.LA(1);
			if ( (LA74_0==AS) ) {
				alt74=1;
			}
			switch (alt74) {
				case 1 :
					// Python.g:1131:18: AS asname= NAME
					{
					AS145=(Token)match(input,AS,FOLLOW_AS_in_import_as_name3629); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AS145_tree = (PythonTree)adaptor.create(AS145);
					adaptor.addChild(root_0, AS145_tree);
					}

					asname=(Token)match(input,NAME,FOLLOW_NAME_in_import_as_name3633); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					asname_tree = (PythonTree)adaptor.create(asname);
					adaptor.addChild(root_0, asname_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {
			        retval.atype = new alias(actions.makeNameNode(name), actions.makeNameNode(asname));
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = retval.atype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "import_as_name"


	public static class dotted_as_name_return extends ParserRuleReturnScope {
		public alias atype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "dotted_as_name"
	// Python.g:1139:1: dotted_as_name returns [alias atype] : dotted_name ( AS asname= NAME )? ;
	public final PythonParser.dotted_as_name_return dotted_as_name() throws RecognitionException {
		PythonParser.dotted_as_name_return retval = new PythonParser.dotted_as_name_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token asname=null;
		Token AS147=null;
		ParserRuleReturnScope dotted_name146 =null;

		PythonTree asname_tree=null;
		PythonTree AS147_tree=null;

		try {
			// Python.g:1144:5: ( dotted_name ( AS asname= NAME )? )
			// Python.g:1144:7: dotted_name ( AS asname= NAME )?
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_dotted_name_in_dotted_as_name3673);
			dotted_name146=dotted_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, dotted_name146.getTree());

			// Python.g:1144:19: ( AS asname= NAME )?
			int alt75=2;
			int LA75_0 = input.LA(1);
			if ( (LA75_0==AS) ) {
				alt75=1;
			}
			switch (alt75) {
				case 1 :
					// Python.g:1144:20: AS asname= NAME
					{
					AS147=(Token)match(input,AS,FOLLOW_AS_in_dotted_as_name3676); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AS147_tree = (PythonTree)adaptor.create(AS147);
					adaptor.addChild(root_0, AS147_tree);
					}

					asname=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_as_name3680); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					asname_tree = (PythonTree)adaptor.create(asname);
					adaptor.addChild(root_0, asname_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {
			        retval.atype = new alias((dotted_name146!=null?((PythonParser.dotted_name_return)dotted_name146).names:null), actions.makeNameNode(asname));
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = retval.atype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dotted_as_name"


	public static class dotted_as_names_return extends ParserRuleReturnScope {
		public List<alias> atypes;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "dotted_as_names"
	// Python.g:1151:1: dotted_as_names returns [List<alias> atypes] :d+= dotted_as_name ( COMMA !d+= dotted_as_name )* ;
	public final PythonParser.dotted_as_names_return dotted_as_names() throws RecognitionException {
		PythonParser.dotted_as_names_return retval = new PythonParser.dotted_as_names_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COMMA148=null;
		List<Object> list_d=null;
		RuleReturnScope d = null;
		PythonTree COMMA148_tree=null;

		try {
			// Python.g:1153:5: (d+= dotted_as_name ( COMMA !d+= dotted_as_name )* )
			// Python.g:1153:7: d+= dotted_as_name ( COMMA !d+= dotted_as_name )*
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_dotted_as_name_in_dotted_as_names3716);
			d=dotted_as_name();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());

			if (list_d==null) list_d=new ArrayList<Object>();
			list_d.add(d.getTree());
			// Python.g:1153:25: ( COMMA !d+= dotted_as_name )*
			loop76:
			while (true) {
				int alt76=2;
				int LA76_0 = input.LA(1);
				if ( (LA76_0==COMMA) ) {
					alt76=1;
				}

				switch (alt76) {
				case 1 :
					// Python.g:1153:26: COMMA !d+= dotted_as_name
					{
					COMMA148=(Token)match(input,COMMA,FOLLOW_COMMA_in_dotted_as_names3719); if (state.failed) return retval;
					pushFollow(FOLLOW_dotted_as_name_in_dotted_as_names3724);
					d=dotted_as_name();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, d.getTree());

					if (list_d==null) list_d=new ArrayList<Object>();
					list_d.add(d.getTree());
					}
					break;

				default :
					break loop76;
				}
			}

			if ( state.backtracking==0 ) {
			        retval.atypes = list_d;
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dotted_as_names"


	public static class dotted_name_return extends ParserRuleReturnScope {
		public List<Name> names;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "dotted_name"
	// Python.g:1160:1: dotted_name returns [List<Name> names] : NAME ( DOT dn+= attr )* ;
	public final PythonParser.dotted_name_return dotted_name() throws RecognitionException {
		PythonParser.dotted_name_return retval = new PythonParser.dotted_name_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token NAME149=null;
		Token DOT150=null;
		List<Object> list_dn=null;
		RuleReturnScope dn = null;
		PythonTree NAME149_tree=null;
		PythonTree DOT150_tree=null;

		try {
			// Python.g:1162:5: ( NAME ( DOT dn+= attr )* )
			// Python.g:1162:7: NAME ( DOT dn+= attr )*
			{
			root_0 = (PythonTree)adaptor.nil();


			NAME149=(Token)match(input,NAME,FOLLOW_NAME_in_dotted_name3758); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NAME149_tree = (PythonTree)adaptor.create(NAME149);
			adaptor.addChild(root_0, NAME149_tree);
			}

			// Python.g:1162:12: ( DOT dn+= attr )*
			loop77:
			while (true) {
				int alt77=2;
				int LA77_0 = input.LA(1);
				if ( (LA77_0==DOT) ) {
					alt77=1;
				}

				switch (alt77) {
				case 1 :
					// Python.g:1162:13: DOT dn+= attr
					{
					DOT150=(Token)match(input,DOT,FOLLOW_DOT_in_dotted_name3761); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOT150_tree = (PythonTree)adaptor.create(DOT150);
					adaptor.addChild(root_0, DOT150_tree);
					}

					pushFollow(FOLLOW_attr_in_dotted_name3765);
					dn=attr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, dn.getTree());

					if (list_dn==null) list_dn=new ArrayList<Object>();
					list_dn.add(dn.getTree());
					}
					break;

				default :
					break loop77;
				}
			}

			if ( state.backtracking==0 ) {
			        retval.names = actions.makeDottedName(NAME149, list_dn);
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dotted_name"


	public static class global_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "global_stmt"
	// Python.g:1169:1: global_stmt : GLOBAL n+= NAME ( COMMA n+= NAME )* ;
	public final PythonParser.global_stmt_return global_stmt() throws RecognitionException {
		PythonParser.global_stmt_return retval = new PythonParser.global_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token GLOBAL151=null;
		Token COMMA152=null;
		Token n=null;
		List<Object> list_n=null;

		PythonTree GLOBAL151_tree=null;
		PythonTree COMMA152_tree=null;
		PythonTree n_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1176:5: ( GLOBAL n+= NAME ( COMMA n+= NAME )* )
			// Python.g:1176:7: GLOBAL n+= NAME ( COMMA n+= NAME )*
			{
			root_0 = (PythonTree)adaptor.nil();


			GLOBAL151=(Token)match(input,GLOBAL,FOLLOW_GLOBAL_in_global_stmt3801); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			GLOBAL151_tree = (PythonTree)adaptor.create(GLOBAL151);
			adaptor.addChild(root_0, GLOBAL151_tree);
			}

			n=(Token)match(input,NAME,FOLLOW_NAME_in_global_stmt3805); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			n_tree = (PythonTree)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);
			}

			if (list_n==null) list_n=new ArrayList<Object>();
			list_n.add(n);
			// Python.g:1176:22: ( COMMA n+= NAME )*
			loop78:
			while (true) {
				int alt78=2;
				int LA78_0 = input.LA(1);
				if ( (LA78_0==COMMA) ) {
					alt78=1;
				}

				switch (alt78) {
				case 1 :
					// Python.g:1176:23: COMMA n+= NAME
					{
					COMMA152=(Token)match(input,COMMA,FOLLOW_COMMA_in_global_stmt3808); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA152_tree = (PythonTree)adaptor.create(COMMA152);
					adaptor.addChild(root_0, COMMA152_tree);
					}

					n=(Token)match(input,NAME,FOLLOW_NAME_in_global_stmt3812); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					n_tree = (PythonTree)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);
					}

					if (list_n==null) list_n=new ArrayList<Object>();
					list_n.add(n);
					}
					break;

				default :
					break loop78;
				}
			}

			if ( state.backtracking==0 ) {
			          stype = new Global(GLOBAL151, actions.makeNames(list_n), actions.makeNameNodes(list_n));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "global_stmt"


	public static class nonlocal_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "nonlocal_stmt"
	// Python.g:1183:1: nonlocal_stmt : NONLOCAL n+= NAME ( COMMA n+= NAME )* ;
	public final PythonParser.nonlocal_stmt_return nonlocal_stmt() throws RecognitionException {
		PythonParser.nonlocal_stmt_return retval = new PythonParser.nonlocal_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token NONLOCAL153=null;
		Token COMMA154=null;
		Token n=null;
		List<Object> list_n=null;

		PythonTree NONLOCAL153_tree=null;
		PythonTree COMMA154_tree=null;
		PythonTree n_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1190:5: ( NONLOCAL n+= NAME ( COMMA n+= NAME )* )
			// Python.g:1190:7: NONLOCAL n+= NAME ( COMMA n+= NAME )*
			{
			root_0 = (PythonTree)adaptor.nil();


			NONLOCAL153=(Token)match(input,NONLOCAL,FOLLOW_NONLOCAL_in_nonlocal_stmt3850); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NONLOCAL153_tree = (PythonTree)adaptor.create(NONLOCAL153);
			adaptor.addChild(root_0, NONLOCAL153_tree);
			}

			n=(Token)match(input,NAME,FOLLOW_NAME_in_nonlocal_stmt3854); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			n_tree = (PythonTree)adaptor.create(n);
			adaptor.addChild(root_0, n_tree);
			}

			if (list_n==null) list_n=new ArrayList<Object>();
			list_n.add(n);
			// Python.g:1190:24: ( COMMA n+= NAME )*
			loop79:
			while (true) {
				int alt79=2;
				int LA79_0 = input.LA(1);
				if ( (LA79_0==COMMA) ) {
					alt79=1;
				}

				switch (alt79) {
				case 1 :
					// Python.g:1190:25: COMMA n+= NAME
					{
					COMMA154=(Token)match(input,COMMA,FOLLOW_COMMA_in_nonlocal_stmt3857); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA154_tree = (PythonTree)adaptor.create(COMMA154);
					adaptor.addChild(root_0, COMMA154_tree);
					}

					n=(Token)match(input,NAME,FOLLOW_NAME_in_nonlocal_stmt3861); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					n_tree = (PythonTree)adaptor.create(n);
					adaptor.addChild(root_0, n_tree);
					}

					if (list_n==null) list_n=new ArrayList<Object>();
					list_n.add(n);
					}
					break;

				default :
					break loop79;
				}
			}

			if ( state.backtracking==0 ) {
			          stype = new Nonlocal(NONLOCAL153, actions.makeNames(list_n), actions.makeNameNodes(list_n));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "nonlocal_stmt"


	public static class assert_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "assert_stmt"
	// Python.g:1197:1: assert_stmt : ASSERT t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? ;
	public final PythonParser.assert_stmt_return assert_stmt() throws RecognitionException {
		PythonParser.assert_stmt_return retval = new PythonParser.assert_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token ASSERT155=null;
		Token COMMA156=null;
		ParserRuleReturnScope t1 =null;
		ParserRuleReturnScope t2 =null;

		PythonTree ASSERT155_tree=null;
		PythonTree COMMA156_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1204:5: ( ASSERT t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )? )
			// Python.g:1204:7: ASSERT t1= test[expr_contextType.Load] ( COMMA t2= test[expr_contextType.Load] )?
			{
			root_0 = (PythonTree)adaptor.nil();


			ASSERT155=(Token)match(input,ASSERT,FOLLOW_ASSERT_in_assert_stmt3899); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ASSERT155_tree = (PythonTree)adaptor.create(ASSERT155);
			adaptor.addChild(root_0, ASSERT155_tree);
			}

			pushFollow(FOLLOW_test_in_assert_stmt3903);
			t1=test(expr_contextType.Load);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());

			// Python.g:1204:45: ( COMMA t2= test[expr_contextType.Load] )?
			int alt80=2;
			int LA80_0 = input.LA(1);
			if ( (LA80_0==COMMA) ) {
				alt80=1;
			}
			switch (alt80) {
				case 1 :
					// Python.g:1204:46: COMMA t2= test[expr_contextType.Load]
					{
					COMMA156=(Token)match(input,COMMA,FOLLOW_COMMA_in_assert_stmt3907); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA156_tree = (PythonTree)adaptor.create(COMMA156);
					adaptor.addChild(root_0, COMMA156_tree);
					}

					pushFollow(FOLLOW_test_in_assert_stmt3911);
					t2=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          stype = new Assert(ASSERT155, actions.castExpr((t1!=null?((PythonTree)t1.getTree()):null)), actions.castExpr((t2!=null?((PythonTree)t2.getTree()):null)));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "assert_stmt"


	public static class compound_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "compound_stmt"
	// Python.g:1211:1: compound_stmt : ( if_stmt | while_stmt | for_stmt | try_stmt | with_stmt | funcdef | classdef | decorated | async_stmt );
	public final PythonParser.compound_stmt_return compound_stmt() throws RecognitionException {
		PythonParser.compound_stmt_return retval = new PythonParser.compound_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		ParserRuleReturnScope if_stmt157 =null;
		ParserRuleReturnScope while_stmt158 =null;
		ParserRuleReturnScope for_stmt159 =null;
		ParserRuleReturnScope try_stmt160 =null;
		ParserRuleReturnScope with_stmt161 =null;
		ParserRuleReturnScope funcdef162 =null;
		ParserRuleReturnScope classdef163 =null;
		ParserRuleReturnScope decorated164 =null;
		ParserRuleReturnScope async_stmt165 =null;


		try {
			// Python.g:1212:5: ( if_stmt | while_stmt | for_stmt | try_stmt | with_stmt | funcdef | classdef | decorated | async_stmt )
			int alt81=9;
			switch ( input.LA(1) ) {
			case IF:
				{
				alt81=1;
				}
				break;
			case WHILE:
				{
				alt81=2;
				}
				break;
			case FOR:
				{
				alt81=3;
				}
				break;
			case TRY:
				{
				alt81=4;
				}
				break;
			case WITH:
				{
				alt81=5;
				}
				break;
			case DEF:
				{
				alt81=6;
				}
				break;
			case CLASS:
				{
				alt81=7;
				}
				break;
			case AT:
				{
				alt81=8;
				}
				break;
			case ASYNC:
				{
				alt81=9;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 81, 0, input);
				throw nvae;
			}
			switch (alt81) {
				case 1 :
					// Python.g:1212:7: if_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_if_stmt_in_compound_stmt3940);
					if_stmt157=if_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, if_stmt157.getTree());

					}
					break;
				case 2 :
					// Python.g:1213:7: while_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_while_stmt_in_compound_stmt3948);
					while_stmt158=while_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, while_stmt158.getTree());

					}
					break;
				case 3 :
					// Python.g:1214:7: for_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_for_stmt_in_compound_stmt3956);
					for_stmt159=for_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, for_stmt159.getTree());

					}
					break;
				case 4 :
					// Python.g:1215:7: try_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_try_stmt_in_compound_stmt3964);
					try_stmt160=try_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, try_stmt160.getTree());

					}
					break;
				case 5 :
					// Python.g:1216:7: with_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_with_stmt_in_compound_stmt3972);
					with_stmt161=with_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, with_stmt161.getTree());

					}
					break;
				case 6 :
					// Python.g:1217:7: funcdef
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_funcdef_in_compound_stmt3980);
					funcdef162=funcdef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, funcdef162.getTree());

					}
					break;
				case 7 :
					// Python.g:1218:7: classdef
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_classdef_in_compound_stmt3988);
					classdef163=classdef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, classdef163.getTree());

					}
					break;
				case 8 :
					// Python.g:1219:7: decorated
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_decorated_in_compound_stmt3996);
					decorated164=decorated();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, decorated164.getTree());

					}
					break;
				case 9 :
					// Python.g:1220:7: async_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_async_stmt_in_compound_stmt4004);
					async_stmt165=async_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, async_stmt165.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "compound_stmt"


	public static class async_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "async_stmt"
	// Python.g:1224:1: async_stmt : ASYNC ( funcdef | with_stmt | for_stmt ) ;
	public final PythonParser.async_stmt_return async_stmt() throws RecognitionException {
		PythonParser.async_stmt_return retval = new PythonParser.async_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token ASYNC166=null;
		ParserRuleReturnScope funcdef167 =null;
		ParserRuleReturnScope with_stmt168 =null;
		ParserRuleReturnScope for_stmt169 =null;

		PythonTree ASYNC166_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1231:5: ( ASYNC ( funcdef | with_stmt | for_stmt ) )
			// Python.g:1231:7: ASYNC ( funcdef | with_stmt | for_stmt )
			{
			root_0 = (PythonTree)adaptor.nil();


			ASYNC166=(Token)match(input,ASYNC,FOLLOW_ASYNC_in_async_stmt4032); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ASYNC166_tree = (PythonTree)adaptor.create(ASYNC166);
			adaptor.addChild(root_0, ASYNC166_tree);
			}

			// Python.g:1232:9: ( funcdef | with_stmt | for_stmt )
			int alt82=3;
			switch ( input.LA(1) ) {
			case DEF:
				{
				alt82=1;
				}
				break;
			case WITH:
				{
				alt82=2;
				}
				break;
			case FOR:
				{
				alt82=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 82, 0, input);
				throw nvae;
			}
			switch (alt82) {
				case 1 :
					// Python.g:1232:11: funcdef
					{
					pushFollow(FOLLOW_funcdef_in_async_stmt4044);
					funcdef167=funcdef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, funcdef167.getTree());

					if ( state.backtracking==0 ) {
					            stype = actions.makeAsyncFuncdef(ASYNC166, (funcdef167!=null?((PythonTree)funcdef167.getTree()):null));
					        }
					}
					break;
				case 2 :
					// Python.g:1236:11: with_stmt
					{
					pushFollow(FOLLOW_with_stmt_in_async_stmt4066);
					with_stmt168=with_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, with_stmt168.getTree());

					if ( state.backtracking==0 ) {
					            stype = actions.makeAsyncWith(ASYNC166, (with_stmt168!=null?((PythonParser.with_stmt_return)with_stmt168).items:null), (with_stmt168!=null?((PythonParser.with_stmt_return)with_stmt168).stypes:null));
					        }
					}
					break;
				case 3 :
					// Python.g:1240:11: for_stmt
					{
					pushFollow(FOLLOW_for_stmt_in_async_stmt4088);
					for_stmt169=for_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, for_stmt169.getTree());

					if ( state.backtracking==0 ) {
					            stype = actions.makeAsyncFor(ASYNC166, (for_stmt169!=null?((PythonTree)for_stmt169.getTree()):null));
					        }
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "async_stmt"


	public static class if_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "if_stmt"
	// Python.g:1248:1: if_stmt : IF test[expr_contextType.Load] COLON ifsuite= suite[false] ( elif_clause )? ;
	public final PythonParser.if_stmt_return if_stmt() throws RecognitionException {
		PythonParser.if_stmt_return retval = new PythonParser.if_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token IF170=null;
		Token COLON172=null;
		ParserRuleReturnScope ifsuite =null;
		ParserRuleReturnScope test171 =null;
		ParserRuleReturnScope elif_clause173 =null;

		PythonTree IF170_tree=null;
		PythonTree COLON172_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1255:5: ( IF test[expr_contextType.Load] COLON ifsuite= suite[false] ( elif_clause )? )
			// Python.g:1255:7: IF test[expr_contextType.Load] COLON ifsuite= suite[false] ( elif_clause )?
			{
			root_0 = (PythonTree)adaptor.nil();


			IF170=(Token)match(input,IF,FOLLOW_IF_in_if_stmt4136); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			IF170_tree = (PythonTree)adaptor.create(IF170);
			adaptor.addChild(root_0, IF170_tree);
			}

			pushFollow(FOLLOW_test_in_if_stmt4138);
			test171=test(expr_contextType.Load);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, test171.getTree());

			COLON172=(Token)match(input,COLON,FOLLOW_COLON_in_if_stmt4141); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON172_tree = (PythonTree)adaptor.create(COLON172);
			adaptor.addChild(root_0, COLON172_tree);
			}

			pushFollow(FOLLOW_suite_in_if_stmt4145);
			ifsuite=suite(false);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, ifsuite.getTree());

			// Python.g:1255:65: ( elif_clause )?
			int alt83=2;
			int LA83_0 = input.LA(1);
			if ( (LA83_0==ELIF||LA83_0==ORELSE) ) {
				alt83=1;
			}
			switch (alt83) {
				case 1 :
					// Python.g:1255:65: elif_clause
					{
					pushFollow(FOLLOW_elif_clause_in_if_stmt4148);
					elif_clause173=elif_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, elif_clause173.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          stype = new If(IF170, actions.castExpr((test171!=null?((PythonTree)test171.getTree()):null)), actions.castStmts((ifsuite!=null?((PythonParser.suite_return)ifsuite).stypes:null)),
			              actions.makeElse((elif_clause173!=null?((PythonParser.elif_clause_return)elif_clause173).stypes:null), (elif_clause173!=null?((PythonTree)elif_clause173.getTree()):null)));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "if_stmt"


	public static class elif_clause_return extends ParserRuleReturnScope {
		public List stypes;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "elif_clause"
	// Python.g:1263:1: elif_clause returns [List stypes] : ( else_clause | ELIF test[expr_contextType.Load] COLON suite[false] (e2= elif_clause |) );
	public final PythonParser.elif_clause_return elif_clause() throws RecognitionException {
		PythonParser.elif_clause_return retval = new PythonParser.elif_clause_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token ELIF175=null;
		Token COLON177=null;
		ParserRuleReturnScope e2 =null;
		ParserRuleReturnScope else_clause174 =null;
		ParserRuleReturnScope test176 =null;
		ParserRuleReturnScope suite178 =null;

		PythonTree ELIF175_tree=null;
		PythonTree COLON177_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1273:5: ( else_clause | ELIF test[expr_contextType.Load] COLON suite[false] (e2= elif_clause |) )
			int alt85=2;
			int LA85_0 = input.LA(1);
			if ( (LA85_0==ORELSE) ) {
				alt85=1;
			}
			else if ( (LA85_0==ELIF) ) {
				alt85=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 85, 0, input);
				throw nvae;
			}

			switch (alt85) {
				case 1 :
					// Python.g:1273:7: else_clause
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_else_clause_in_elif_clause4193);
					else_clause174=else_clause();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, else_clause174.getTree());

					if ( state.backtracking==0 ) {
					          retval.stypes = (else_clause174!=null?((PythonParser.else_clause_return)else_clause174).stypes:null);
					      }
					}
					break;
				case 2 :
					// Python.g:1277:7: ELIF test[expr_contextType.Load] COLON suite[false] (e2= elif_clause |)
					{
					root_0 = (PythonTree)adaptor.nil();


					ELIF175=(Token)match(input,ELIF,FOLLOW_ELIF_in_elif_clause4209); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ELIF175_tree = (PythonTree)adaptor.create(ELIF175);
					adaptor.addChild(root_0, ELIF175_tree);
					}

					pushFollow(FOLLOW_test_in_elif_clause4211);
					test176=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, test176.getTree());

					COLON177=(Token)match(input,COLON,FOLLOW_COLON_in_elif_clause4214); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COLON177_tree = (PythonTree)adaptor.create(COLON177);
					adaptor.addChild(root_0, COLON177_tree);
					}

					pushFollow(FOLLOW_suite_in_elif_clause4216);
					suite178=suite(false);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, suite178.getTree());

					// Python.g:1278:7: (e2= elif_clause |)
					int alt84=2;
					int LA84_0 = input.LA(1);
					if ( (LA84_0==ELIF||LA84_0==ORELSE) ) {
						alt84=1;
					}
					else if ( (LA84_0==EOF||LA84_0==ASSERT||(LA84_0 >= ASYNC && LA84_0 <= AT)||LA84_0==AWAIT||LA84_0==BREAK||LA84_0==CLASS||(LA84_0 >= COMPLEX && LA84_0 <= CONTINUE)||(LA84_0 >= DEDENT && LA84_0 <= DELETE)||(LA84_0 >= DOLLER && LA84_0 <= DOT)||(LA84_0 >= FLOAT && LA84_0 <= GLOBAL)||(LA84_0 >= IF && LA84_0 <= IMPORT)||(LA84_0 >= LAMBDA && LA84_0 <= LCURLY)||(LA84_0 >= LONGINT && LA84_0 <= MINUS)||(LA84_0 >= NAME && LA84_0 <= NOT)||LA84_0==PASS||LA84_0==PLUS||LA84_0==RAISE||LA84_0==RETURN||LA84_0==STAR||(LA84_0 >= STRING && LA84_0 <= TILDE)||LA84_0==TRY||(LA84_0 >= WHILE && LA84_0 <= WITH)||LA84_0==YIELD) ) {
						alt84=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 84, 0, input);
						throw nvae;
					}

					switch (alt84) {
						case 1 :
							// Python.g:1278:8: e2= elif_clause
							{
							pushFollow(FOLLOW_elif_clause_in_elif_clause4228);
							e2=elif_clause();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, e2.getTree());

							if ( state.backtracking==0 ) {
							           stype = new If((test176!=null?(test176.start):null), actions.castExpr((test176!=null?((PythonTree)test176.getTree()):null)), actions.castStmts((suite178!=null?((PythonParser.suite_return)suite178).stypes:null)), actions.makeElse((e2!=null?((PythonParser.elif_clause_return)e2).stypes:null), (e2!=null?((PythonTree)e2.getTree()):null)));
							       }
							}
							break;
						case 2 :
							// Python.g:1283:8: 
							{
							if ( state.backtracking==0 ) {
							           stype = new If((test176!=null?(test176.start):null), actions.castExpr((test176!=null?((PythonTree)test176.getTree()):null)), actions.castStmts((suite178!=null?((PythonParser.suite_return)suite178).stypes:null)), new ArrayList<stmt>());
							       }
							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   if (stype != null) {
			       retval.tree = stype;
			   }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "elif_clause"


	public static class else_clause_return extends ParserRuleReturnScope {
		public List stypes;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "else_clause"
	// Python.g:1290:1: else_clause returns [List stypes] : ORELSE COLON elsesuite= suite[false] ;
	public final PythonParser.else_clause_return else_clause() throws RecognitionException {
		PythonParser.else_clause_return retval = new PythonParser.else_clause_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token ORELSE179=null;
		Token COLON180=null;
		ParserRuleReturnScope elsesuite =null;

		PythonTree ORELSE179_tree=null;
		PythonTree COLON180_tree=null;

		try {
			// Python.g:1292:5: ( ORELSE COLON elsesuite= suite[false] )
			// Python.g:1292:7: ORELSE COLON elsesuite= suite[false]
			{
			root_0 = (PythonTree)adaptor.nil();


			ORELSE179=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_else_clause4288); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			ORELSE179_tree = (PythonTree)adaptor.create(ORELSE179);
			adaptor.addChild(root_0, ORELSE179_tree);
			}

			COLON180=(Token)match(input,COLON,FOLLOW_COLON_in_else_clause4290); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON180_tree = (PythonTree)adaptor.create(COLON180);
			adaptor.addChild(root_0, COLON180_tree);
			}

			pushFollow(FOLLOW_suite_in_else_clause4294);
			elsesuite=suite(false);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, elsesuite.getTree());

			if ( state.backtracking==0 ) {
			          retval.stypes = (elsesuite!=null?((PythonParser.suite_return)elsesuite).stypes:null);
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "else_clause"


	public static class while_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "while_stmt"
	// Python.g:1299:1: while_stmt : WHILE test[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? ;
	public final PythonParser.while_stmt_return while_stmt() throws RecognitionException {
		PythonParser.while_stmt_return retval = new PythonParser.while_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token WHILE181=null;
		Token COLON183=null;
		Token ORELSE184=null;
		Token COLON185=null;
		ParserRuleReturnScope s1 =null;
		ParserRuleReturnScope s2 =null;
		ParserRuleReturnScope test182 =null;

		PythonTree WHILE181_tree=null;
		PythonTree COLON183_tree=null;
		PythonTree ORELSE184_tree=null;
		PythonTree COLON185_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1306:5: ( WHILE test[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? )
			// Python.g:1306:7: WHILE test[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )?
			{
			root_0 = (PythonTree)adaptor.nil();


			WHILE181=(Token)match(input,WHILE,FOLLOW_WHILE_in_while_stmt4331); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			WHILE181_tree = (PythonTree)adaptor.create(WHILE181);
			adaptor.addChild(root_0, WHILE181_tree);
			}

			pushFollow(FOLLOW_test_in_while_stmt4333);
			test182=test(expr_contextType.Load);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, test182.getTree());

			COLON183=(Token)match(input,COLON,FOLLOW_COLON_in_while_stmt4336); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON183_tree = (PythonTree)adaptor.create(COLON183);
			adaptor.addChild(root_0, COLON183_tree);
			}

			pushFollow(FOLLOW_suite_in_while_stmt4340);
			s1=suite(false);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, s1.getTree());

			// Python.g:1306:63: ( ORELSE COLON s2= suite[false] )?
			int alt86=2;
			int LA86_0 = input.LA(1);
			if ( (LA86_0==ORELSE) ) {
				alt86=1;
			}
			switch (alt86) {
				case 1 :
					// Python.g:1306:64: ORELSE COLON s2= suite[false]
					{
					ORELSE184=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_while_stmt4344); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ORELSE184_tree = (PythonTree)adaptor.create(ORELSE184);
					adaptor.addChild(root_0, ORELSE184_tree);
					}

					COLON185=(Token)match(input,COLON,FOLLOW_COLON_in_while_stmt4346); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COLON185_tree = (PythonTree)adaptor.create(COLON185);
					adaptor.addChild(root_0, COLON185_tree);
					}

					pushFollow(FOLLOW_suite_in_while_stmt4350);
					s2=suite(false);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, s2.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          stype = actions.makeWhile(WHILE181, actions.castExpr((test182!=null?((PythonTree)test182.getTree()):null)), (s1!=null?((PythonParser.suite_return)s1).stypes:null), (s2!=null?((PythonParser.suite_return)s2).stypes:null));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "while_stmt"


	public static class for_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "for_stmt"
	// Python.g:1313:1: for_stmt : FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? ;
	public final PythonParser.for_stmt_return for_stmt() throws RecognitionException {
		PythonParser.for_stmt_return retval = new PythonParser.for_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token FOR186=null;
		Token IN188=null;
		Token COLON190=null;
		Token ORELSE191=null;
		Token COLON192=null;
		ParserRuleReturnScope s1 =null;
		ParserRuleReturnScope s2 =null;
		ParserRuleReturnScope exprlist187 =null;
		ParserRuleReturnScope testlist189 =null;

		PythonTree FOR186_tree=null;
		PythonTree IN188_tree=null;
		PythonTree COLON190_tree=null;
		PythonTree ORELSE191_tree=null;
		PythonTree COLON192_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1320:5: ( FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )? )
			// Python.g:1320:7: FOR exprlist[expr_contextType.Store] IN testlist[expr_contextType.Load] COLON s1= suite[false] ( ORELSE COLON s2= suite[false] )?
			{
			root_0 = (PythonTree)adaptor.nil();


			FOR186=(Token)match(input,FOR,FOLLOW_FOR_in_for_stmt4389); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			FOR186_tree = (PythonTree)adaptor.create(FOR186);
			adaptor.addChild(root_0, FOR186_tree);
			}

			pushFollow(FOLLOW_exprlist_in_for_stmt4391);
			exprlist187=exprlist(expr_contextType.Store);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, exprlist187.getTree());

			IN188=(Token)match(input,IN,FOLLOW_IN_in_for_stmt4394); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			IN188_tree = (PythonTree)adaptor.create(IN188);
			adaptor.addChild(root_0, IN188_tree);
			}

			pushFollow(FOLLOW_testlist_in_for_stmt4396);
			testlist189=testlist(expr_contextType.Load);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist189.getTree());

			COLON190=(Token)match(input,COLON,FOLLOW_COLON_in_for_stmt4399); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON190_tree = (PythonTree)adaptor.create(COLON190);
			adaptor.addChild(root_0, COLON190_tree);
			}

			pushFollow(FOLLOW_suite_in_for_stmt4403);
			s1=suite(false);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, s1.getTree());

			// Python.g:1321:9: ( ORELSE COLON s2= suite[false] )?
			int alt87=2;
			int LA87_0 = input.LA(1);
			if ( (LA87_0==ORELSE) ) {
				alt87=1;
			}
			switch (alt87) {
				case 1 :
					// Python.g:1321:10: ORELSE COLON s2= suite[false]
					{
					ORELSE191=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_for_stmt4415); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ORELSE191_tree = (PythonTree)adaptor.create(ORELSE191);
					adaptor.addChild(root_0, ORELSE191_tree);
					}

					COLON192=(Token)match(input,COLON,FOLLOW_COLON_in_for_stmt4417); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COLON192_tree = (PythonTree)adaptor.create(COLON192);
					adaptor.addChild(root_0, COLON192_tree);
					}

					pushFollow(FOLLOW_suite_in_for_stmt4421);
					s2=suite(false);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, s2.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          stype = actions.makeFor(FOR186, (exprlist187!=null?((PythonParser.exprlist_return)exprlist187).etype:null), actions.castExpr((testlist189!=null?((PythonTree)testlist189.getTree()):null)), (s1!=null?((PythonParser.suite_return)s1).stypes:null), (s2!=null?((PythonParser.suite_return)s2).stypes:null));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "for_stmt"


	public static class try_stmt_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "try_stmt"
	// Python.g:1332:1: try_stmt : TRY COLON trysuite= suite[!$suite.isEmpty() && $suite::continueIllegal] ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] ) ;
	public final PythonParser.try_stmt_return try_stmt() throws RecognitionException {
		PythonParser.try_stmt_return retval = new PythonParser.try_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token TRY193=null;
		Token COLON194=null;
		Token ORELSE195=null;
		Token COLON196=null;
		Token FINALLY197=null;
		Token COLON198=null;
		Token FINALLY199=null;
		Token COLON200=null;
		List<Object> list_e=null;
		ParserRuleReturnScope trysuite =null;
		ParserRuleReturnScope elsesuite =null;
		ParserRuleReturnScope finalsuite =null;
		RuleReturnScope e = null;
		PythonTree TRY193_tree=null;
		PythonTree COLON194_tree=null;
		PythonTree ORELSE195_tree=null;
		PythonTree COLON196_tree=null;
		PythonTree FINALLY197_tree=null;
		PythonTree COLON198_tree=null;
		PythonTree FINALLY199_tree=null;
		PythonTree COLON200_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1339:5: ( TRY COLON trysuite= suite[!$suite.isEmpty() && $suite::continueIllegal] ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] ) )
			// Python.g:1339:7: TRY COLON trysuite= suite[!$suite.isEmpty() && $suite::continueIllegal] ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] )
			{
			root_0 = (PythonTree)adaptor.nil();


			TRY193=(Token)match(input,TRY,FOLLOW_TRY_in_try_stmt4464); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			TRY193_tree = (PythonTree)adaptor.create(TRY193);
			adaptor.addChild(root_0, TRY193_tree);
			}

			COLON194=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt4466); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON194_tree = (PythonTree)adaptor.create(COLON194);
			adaptor.addChild(root_0, COLON194_tree);
			}

			pushFollow(FOLLOW_suite_in_try_stmt4470);
			trysuite=suite(!suite_stack.isEmpty() && suite_stack.peek().continueIllegal);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, trysuite.getTree());

			// Python.g:1340:7: ( (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )? | FINALLY COLON finalsuite= suite[true] )
			int alt91=2;
			int LA91_0 = input.LA(1);
			if ( (LA91_0==EXCEPT) ) {
				alt91=1;
			}
			else if ( (LA91_0==FINALLY) ) {
				alt91=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 91, 0, input);
				throw nvae;
			}

			switch (alt91) {
				case 1 :
					// Python.g:1340:9: (e+= except_clause )+ ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )? ( FINALLY COLON finalsuite= suite[true] )?
					{
					// Python.g:1340:10: (e+= except_clause )+
					int cnt88=0;
					loop88:
					while (true) {
						int alt88=2;
						int LA88_0 = input.LA(1);
						if ( (LA88_0==EXCEPT) ) {
							alt88=1;
						}

						switch (alt88) {
						case 1 :
							// Python.g:1340:10: e+= except_clause
							{
							pushFollow(FOLLOW_except_clause_in_try_stmt4483);
							e=except_clause();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());

							if (list_e==null) list_e=new ArrayList<Object>();
							list_e.add(e.getTree());
							}
							break;

						default :
							if ( cnt88 >= 1 ) break loop88;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(88, input);
							throw eee;
						}
						cnt88++;
					}

					// Python.g:1340:27: ( ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal] )?
					int alt89=2;
					int LA89_0 = input.LA(1);
					if ( (LA89_0==ORELSE) ) {
						alt89=1;
					}
					switch (alt89) {
						case 1 :
							// Python.g:1340:28: ORELSE COLON elsesuite= suite[!$suite.isEmpty() && $suite::continueIllegal]
							{
							ORELSE195=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_try_stmt4487); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							ORELSE195_tree = (PythonTree)adaptor.create(ORELSE195);
							adaptor.addChild(root_0, ORELSE195_tree);
							}

							COLON196=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt4489); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COLON196_tree = (PythonTree)adaptor.create(COLON196);
							adaptor.addChild(root_0, COLON196_tree);
							}

							pushFollow(FOLLOW_suite_in_try_stmt4493);
							elsesuite=suite(!suite_stack.isEmpty() && suite_stack.peek().continueIllegal);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, elsesuite.getTree());

							}
							break;

					}

					// Python.g:1340:105: ( FINALLY COLON finalsuite= suite[true] )?
					int alt90=2;
					int LA90_0 = input.LA(1);
					if ( (LA90_0==FINALLY) ) {
						alt90=1;
					}
					switch (alt90) {
						case 1 :
							// Python.g:1340:106: FINALLY COLON finalsuite= suite[true]
							{
							FINALLY197=(Token)match(input,FINALLY,FOLLOW_FINALLY_in_try_stmt4499); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							FINALLY197_tree = (PythonTree)adaptor.create(FINALLY197);
							adaptor.addChild(root_0, FINALLY197_tree);
							}

							COLON198=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt4501); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COLON198_tree = (PythonTree)adaptor.create(COLON198);
							adaptor.addChild(root_0, COLON198_tree);
							}

							pushFollow(FOLLOW_suite_in_try_stmt4505);
							finalsuite=suite(true);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, finalsuite.getTree());

							}
							break;

					}

					if ( state.backtracking==0 ) {
					            stype = actions.makeTryExcept(TRY193, (trysuite!=null?((PythonParser.suite_return)trysuite).stypes:null), list_e, (elsesuite!=null?((PythonParser.suite_return)elsesuite).stypes:null), (finalsuite!=null?((PythonParser.suite_return)finalsuite).stypes:null));
					        }
					}
					break;
				case 2 :
					// Python.g:1344:9: FINALLY COLON finalsuite= suite[true]
					{
					FINALLY199=(Token)match(input,FINALLY,FOLLOW_FINALLY_in_try_stmt4528); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FINALLY199_tree = (PythonTree)adaptor.create(FINALLY199);
					adaptor.addChild(root_0, FINALLY199_tree);
					}

					COLON200=(Token)match(input,COLON,FOLLOW_COLON_in_try_stmt4530); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COLON200_tree = (PythonTree)adaptor.create(COLON200);
					adaptor.addChild(root_0, COLON200_tree);
					}

					pushFollow(FOLLOW_suite_in_try_stmt4534);
					finalsuite=suite(true);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, finalsuite.getTree());

					if ( state.backtracking==0 ) {
					            stype = actions.makeTryFinally(TRY193, (trysuite!=null?((PythonParser.suite_return)trysuite).stypes:null), (finalsuite!=null?((PythonParser.suite_return)finalsuite).stypes:null));
					        }
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "try_stmt"


	public static class with_stmt_return extends ParserRuleReturnScope {
		public List items;
		public List stypes;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "with_stmt"
	// Python.g:1352:1: with_stmt returns [List items, List stypes] : WITH w+= with_item ( options {greedy=true; } : COMMA w+= with_item )* COLON suite[false] ;
	public final PythonParser.with_stmt_return with_stmt() throws RecognitionException {
		PythonParser.with_stmt_return retval = new PythonParser.with_stmt_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token WITH201=null;
		Token COMMA202=null;
		Token COLON203=null;
		List<Object> list_w=null;
		ParserRuleReturnScope suite204 =null;
		RuleReturnScope w = null;
		PythonTree WITH201_tree=null;
		PythonTree COMMA202_tree=null;
		PythonTree COLON203_tree=null;


		    stmt stype = null;

		try {
			// Python.g:1359:5: ( WITH w+= with_item ( options {greedy=true; } : COMMA w+= with_item )* COLON suite[false] )
			// Python.g:1359:7: WITH w+= with_item ( options {greedy=true; } : COMMA w+= with_item )* COLON suite[false]
			{
			root_0 = (PythonTree)adaptor.nil();


			WITH201=(Token)match(input,WITH,FOLLOW_WITH_in_with_stmt4587); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			WITH201_tree = (PythonTree)adaptor.create(WITH201);
			adaptor.addChild(root_0, WITH201_tree);
			}

			pushFollow(FOLLOW_with_item_in_with_stmt4591);
			w=with_item();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, w.getTree());

			if (list_w==null) list_w=new ArrayList<Object>();
			list_w.add(w.getTree());
			// Python.g:1359:25: ( options {greedy=true; } : COMMA w+= with_item )*
			loop92:
			while (true) {
				int alt92=2;
				int LA92_0 = input.LA(1);
				if ( (LA92_0==COMMA) ) {
					alt92=1;
				}

				switch (alt92) {
				case 1 :
					// Python.g:1359:49: COMMA w+= with_item
					{
					COMMA202=(Token)match(input,COMMA,FOLLOW_COMMA_in_with_stmt4601); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA202_tree = (PythonTree)adaptor.create(COMMA202);
					adaptor.addChild(root_0, COMMA202_tree);
					}

					pushFollow(FOLLOW_with_item_in_with_stmt4605);
					w=with_item();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, w.getTree());

					if (list_w==null) list_w=new ArrayList<Object>();
					list_w.add(w.getTree());
					}
					break;

				default :
					break loop92;
				}
			}

			COLON203=(Token)match(input,COLON,FOLLOW_COLON_in_with_stmt4609); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON203_tree = (PythonTree)adaptor.create(COLON203);
			adaptor.addChild(root_0, COLON203_tree);
			}

			pushFollow(FOLLOW_suite_in_with_stmt4611);
			suite204=suite(false);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, suite204.getTree());

			if ( state.backtracking==0 ) {
			        retval.items = list_w;
			        retval.stypes = (suite204!=null?((PythonParser.suite_return)suite204).stypes:null);
			        stype = actions.makeWith(WITH201, list_w, (suite204!=null?((PythonParser.suite_return)suite204).stypes:null));
			    }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "with_stmt"


	public static class with_item_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "with_item"
	// Python.g:1368:1: with_item : test[expr_contextType.Load] ( AS expr[expr_contextType.Store] )? ;
	public final PythonParser.with_item_return with_item() throws RecognitionException {
		PythonParser.with_item_return retval = new PythonParser.with_item_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token AS206=null;
		ParserRuleReturnScope test205 =null;
		ParserRuleReturnScope expr207 =null;

		PythonTree AS206_tree=null;


		    withitem stype = null;

		try {
			// Python.g:1375:5: ( test[expr_contextType.Load] ( AS expr[expr_contextType.Store] )? )
			// Python.g:1375:7: test[expr_contextType.Load] ( AS expr[expr_contextType.Store] )?
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_test_in_with_item4646);
			test205=test(expr_contextType.Load);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, test205.getTree());

			// Python.g:1375:35: ( AS expr[expr_contextType.Store] )?
			int alt93=2;
			int LA93_0 = input.LA(1);
			if ( (LA93_0==AS) ) {
				alt93=1;
			}
			switch (alt93) {
				case 1 :
					// Python.g:1375:36: AS expr[expr_contextType.Store]
					{
					AS206=(Token)match(input,AS,FOLLOW_AS_in_with_item4650); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AS206_tree = (PythonTree)adaptor.create(AS206);
					adaptor.addChild(root_0, AS206_tree);
					}

					pushFollow(FOLLOW_expr_in_with_item4652);
					expr207=expr(expr_contextType.Store);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr207.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          expr item = actions.castExpr((test205!=null?((PythonTree)test205.getTree()):null));
			          expr var = null;
			          if ((expr207!=null?(expr207.start):null) != null) {
			              var = actions.castExpr((expr207!=null?((PythonTree)expr207.getTree()):null));
			              actions.checkAssign(var);
			          }
			          stype = new withitem((test205!=null?(test205.start):null), item, var);
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "with_item"


	public static class except_clause_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "except_clause"
	// Python.g:1388:1: except_clause : EXCEPT (t1= test[expr_contextType.Load] ( AS NAME )? )? COLON suite[!$suite.isEmpty() && $suite::continueIllegal] ;
	public final PythonParser.except_clause_return except_clause() throws RecognitionException {
		PythonParser.except_clause_return retval = new PythonParser.except_clause_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token EXCEPT208=null;
		Token AS209=null;
		Token NAME210=null;
		Token COLON211=null;
		ParserRuleReturnScope t1 =null;
		ParserRuleReturnScope suite212 =null;

		PythonTree EXCEPT208_tree=null;
		PythonTree AS209_tree=null;
		PythonTree NAME210_tree=null;
		PythonTree COLON211_tree=null;


		    excepthandler extype = null;

		try {
			// Python.g:1395:5: ( EXCEPT (t1= test[expr_contextType.Load] ( AS NAME )? )? COLON suite[!$suite.isEmpty() && $suite::continueIllegal] )
			// Python.g:1395:7: EXCEPT (t1= test[expr_contextType.Load] ( AS NAME )? )? COLON suite[!$suite.isEmpty() && $suite::continueIllegal]
			{
			root_0 = (PythonTree)adaptor.nil();


			EXCEPT208=(Token)match(input,EXCEPT,FOLLOW_EXCEPT_in_except_clause4691); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			EXCEPT208_tree = (PythonTree)adaptor.create(EXCEPT208);
			adaptor.addChild(root_0, EXCEPT208_tree);
			}

			// Python.g:1395:14: (t1= test[expr_contextType.Load] ( AS NAME )? )?
			int alt95=2;
			int LA95_0 = input.LA(1);
			if ( (LA95_0==AWAIT||LA95_0==COMPLEX||(LA95_0 >= DOLLER && LA95_0 <= DOT)||LA95_0==FLOAT||(LA95_0 >= LAMBDA && LA95_0 <= LCURLY)||(LA95_0 >= LONGINT && LA95_0 <= MINUS)||LA95_0==NAME||LA95_0==NOT||LA95_0==PLUS||(LA95_0 >= STRING && LA95_0 <= TILDE)) ) {
				alt95=1;
			}
			switch (alt95) {
				case 1 :
					// Python.g:1395:15: t1= test[expr_contextType.Load] ( AS NAME )?
					{
					pushFollow(FOLLOW_test_in_except_clause4696);
					t1=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());

					// Python.g:1395:46: ( AS NAME )?
					int alt94=2;
					int LA94_0 = input.LA(1);
					if ( (LA94_0==AS) ) {
						alt94=1;
					}
					switch (alt94) {
						case 1 :
							// Python.g:1395:47: AS NAME
							{
							AS209=(Token)match(input,AS,FOLLOW_AS_in_except_clause4700); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							AS209_tree = (PythonTree)adaptor.create(AS209);
							adaptor.addChild(root_0, AS209_tree);
							}

							NAME210=(Token)match(input,NAME,FOLLOW_NAME_in_except_clause4702); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							NAME210_tree = (PythonTree)adaptor.create(NAME210);
							adaptor.addChild(root_0, NAME210_tree);
							}

							}
							break;

					}

					}
					break;

			}

			COLON211=(Token)match(input,COLON,FOLLOW_COLON_in_except_clause4708); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON211_tree = (PythonTree)adaptor.create(COLON211);
			adaptor.addChild(root_0, COLON211_tree);
			}

			pushFollow(FOLLOW_suite_in_except_clause4710);
			suite212=suite(!suite_stack.isEmpty() && suite_stack.peek().continueIllegal);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, suite212.getTree());

			if ( state.backtracking==0 ) {
			          String name = null;
			          if (NAME210 != null) {
			              name = NAME210.getText();
			          }
			          extype = new ExceptHandler(EXCEPT208, actions.castExpr((t1!=null?((PythonTree)t1.getTree()):null)), name,
			              actions.castStmts((suite212!=null?((PythonParser.suite_return)suite212).stypes:null)));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = extype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "except_clause"


	protected static class suite_scope {
		boolean continueIllegal;
	}
	protected Stack<suite_scope> suite_stack = new Stack<suite_scope>();

	public static class suite_return extends ParserRuleReturnScope {
		public List stypes;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "suite"
	// Python.g:1407:1: suite[boolean fromFinally] returns [List stypes] : ( simple_stmt | NEWLINE INDENT ( stmt )+ DEDENT );
	public final PythonParser.suite_return suite(boolean fromFinally) throws RecognitionException {
		suite_stack.push(new suite_scope());
		PythonParser.suite_return retval = new PythonParser.suite_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token NEWLINE214=null;
		Token INDENT215=null;
		Token DEDENT217=null;
		ParserRuleReturnScope simple_stmt213 =null;
		ParserRuleReturnScope stmt216 =null;

		PythonTree NEWLINE214_tree=null;
		PythonTree INDENT215_tree=null;
		PythonTree DEDENT217_tree=null;


		    if (suite_stack.peek().continueIllegal || fromFinally) {
		        suite_stack.peek().continueIllegal = true;
		    } else {
		        suite_stack.peek().continueIllegal = false;
		    }
		    retval.stypes = new ArrayList();

		try {
			// Python.g:1420:5: ( simple_stmt | NEWLINE INDENT ( stmt )+ DEDENT )
			int alt97=2;
			int LA97_0 = input.LA(1);
			if ( (LA97_0==ASSERT||LA97_0==AWAIT||LA97_0==BREAK||(LA97_0 >= COMPLEX && LA97_0 <= CONTINUE)||LA97_0==DELETE||(LA97_0 >= DOLLER && LA97_0 <= DOT)||LA97_0==FLOAT||(LA97_0 >= FROM && LA97_0 <= GLOBAL)||LA97_0==IMPORT||(LA97_0 >= LAMBDA && LA97_0 <= LCURLY)||(LA97_0 >= LONGINT && LA97_0 <= MINUS)||LA97_0==NAME||(LA97_0 >= NONLOCAL && LA97_0 <= NOT)||LA97_0==PASS||LA97_0==PLUS||LA97_0==RAISE||LA97_0==RETURN||LA97_0==STAR||(LA97_0 >= STRING && LA97_0 <= TILDE)||LA97_0==YIELD) ) {
				alt97=1;
			}
			else if ( (LA97_0==NEWLINE) ) {
				alt97=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 97, 0, input);
				throw nvae;
			}

			switch (alt97) {
				case 1 :
					// Python.g:1420:7: simple_stmt
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_simple_stmt_in_suite4756);
					simple_stmt213=simple_stmt();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, simple_stmt213.getTree());

					if ( state.backtracking==0 ) {
					          retval.stypes = (simple_stmt213!=null?((PythonParser.simple_stmt_return)simple_stmt213).stypes:null);
					      }
					}
					break;
				case 2 :
					// Python.g:1424:7: NEWLINE INDENT ( stmt )+ DEDENT
					{
					root_0 = (PythonTree)adaptor.nil();


					NEWLINE214=(Token)match(input,NEWLINE,FOLLOW_NEWLINE_in_suite4772); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NEWLINE214_tree = (PythonTree)adaptor.create(NEWLINE214);
					adaptor.addChild(root_0, NEWLINE214_tree);
					}

					INDENT215=(Token)match(input,INDENT,FOLLOW_INDENT_in_suite4774); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INDENT215_tree = (PythonTree)adaptor.create(INDENT215);
					adaptor.addChild(root_0, INDENT215_tree);
					}

					// Python.g:1425:7: ( stmt )+
					int cnt96=0;
					loop96:
					while (true) {
						int alt96=2;
						int LA96_0 = input.LA(1);
						if ( (LA96_0==ASSERT||(LA96_0 >= ASYNC && LA96_0 <= AT)||LA96_0==AWAIT||LA96_0==BREAK||LA96_0==CLASS||(LA96_0 >= COMPLEX && LA96_0 <= CONTINUE)||(LA96_0 >= DEF && LA96_0 <= DELETE)||(LA96_0 >= DOLLER && LA96_0 <= DOT)||(LA96_0 >= FLOAT && LA96_0 <= GLOBAL)||(LA96_0 >= IF && LA96_0 <= IMPORT)||(LA96_0 >= LAMBDA && LA96_0 <= LCURLY)||(LA96_0 >= LONGINT && LA96_0 <= MINUS)||LA96_0==NAME||(LA96_0 >= NONLOCAL && LA96_0 <= NOT)||LA96_0==PASS||LA96_0==PLUS||LA96_0==RAISE||LA96_0==RETURN||LA96_0==STAR||(LA96_0 >= STRING && LA96_0 <= TILDE)||LA96_0==TRY||(LA96_0 >= WHILE && LA96_0 <= WITH)||LA96_0==YIELD) ) {
							alt96=1;
						}

						switch (alt96) {
						case 1 :
							// Python.g:1425:8: stmt
							{
							pushFollow(FOLLOW_stmt_in_suite4783);
							stmt216=stmt();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, stmt216.getTree());

							if ( state.backtracking==0 ) {
							           if ((stmt216!=null?((PythonParser.stmt_return)stmt216).stypes:null) != null) {
							               retval.stypes.addAll((stmt216!=null?((PythonParser.stmt_return)stmt216).stypes:null));
							           }
							       }
							}
							break;

						default :
							if ( cnt96 >= 1 ) break loop96;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(96, input);
							throw eee;
						}
						cnt96++;
					}

					DEDENT217=(Token)match(input,DEDENT,FOLLOW_DEDENT_in_suite4803); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DEDENT217_tree = (PythonTree)adaptor.create(DEDENT217);
					adaptor.addChild(root_0, DEDENT217_tree);
					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
			suite_stack.pop();
		}
		return retval;
	}
	// $ANTLR end "suite"


	public static class test_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "test"
	// Python.g:1435:1: test[expr_contextType ctype] : (o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test ) | lambdef );
	public final PythonParser.test_return test(expr_contextType ctype) throws RecognitionException {
		PythonParser.test_return retval = new PythonParser.test_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token IF218=null;
		Token ORELSE219=null;
		ParserRuleReturnScope o1 =null;
		ParserRuleReturnScope o2 =null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope lambdef220 =null;

		PythonTree IF218_tree=null;
		PythonTree ORELSE219_tree=null;
		RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
		RewriteRuleTokenStream stream_ORELSE=new RewriteRuleTokenStream(adaptor,"token ORELSE");
		RewriteRuleSubtreeStream stream_or_test=new RewriteRuleSubtreeStream(adaptor,"rule or_test");
		RewriteRuleSubtreeStream stream_test=new RewriteRuleSubtreeStream(adaptor,"rule test");


		    expr etype = null;

		try {
			// Python.g:1444:5: (o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test ) | lambdef )
			int alt99=2;
			int LA99_0 = input.LA(1);
			if ( (LA99_0==AWAIT||LA99_0==COMPLEX||(LA99_0 >= DOLLER && LA99_0 <= DOT)||LA99_0==FLOAT||(LA99_0 >= LBRACK && LA99_0 <= LCURLY)||(LA99_0 >= LONGINT && LA99_0 <= MINUS)||LA99_0==NAME||LA99_0==NOT||LA99_0==PLUS||(LA99_0 >= STRING && LA99_0 <= TILDE)) ) {
				alt99=1;
			}
			else if ( (LA99_0==LAMBDA) ) {
				alt99=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 99, 0, input);
				throw nvae;
			}

			switch (alt99) {
				case 1 :
					// Python.g:1444:6: o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test )
					{
					pushFollow(FOLLOW_or_test_in_test4833);
					o1=or_test(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_or_test.add(o1.getTree());
					// Python.g:1445:7: ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test )
					int alt98=2;
					int LA98_0 = input.LA(1);
					if ( (LA98_0==IF) ) {
						int LA98_1 = input.LA(2);
						if ( (synpred5_Python()) ) {
							alt98=1;
						}
						else if ( (true) ) {
							alt98=2;
						}

					}
					else if ( (LA98_0==EOF||LA98_0==AMPEREQUAL||LA98_0==AS||LA98_0==ASSIGN||LA98_0==ATEQUAL||LA98_0==CIRCUMFLEXEQUAL||(LA98_0 >= COLON && LA98_0 <= COMMA)||LA98_0==DOUBLESLASHEQUAL||LA98_0==DOUBLESTAREQUAL||(LA98_0 >= FOR && LA98_0 <= FROM)||LA98_0==LEFTSHIFTEQUAL||LA98_0==MINUSEQUAL||LA98_0==NEWLINE||LA98_0==PERCENTEQUAL||LA98_0==PLUSEQUAL||(LA98_0 >= RBRACK && LA98_0 <= RCURLY)||(LA98_0 >= RIGHTSHIFTEQUAL && LA98_0 <= SEMI)||LA98_0==SLASHEQUAL||LA98_0==STAREQUAL||LA98_0==VBAREQUAL) ) {
						alt98=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 98, 0, input);
						throw nvae;
					}

					switch (alt98) {
						case 1 :
							// Python.g:1445:9: ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load]
							{
							IF218=(Token)match(input,IF,FOLLOW_IF_in_test4855); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_IF.add(IF218);

							pushFollow(FOLLOW_or_test_in_test4859);
							o2=or_test(ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_or_test.add(o2.getTree());
							ORELSE219=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_test4862); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_ORELSE.add(ORELSE219);

							pushFollow(FOLLOW_test_in_test4866);
							e=test(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_test.add(e.getTree());
							if ( state.backtracking==0 ) {
							             etype = new IfExp((o1!=null?(o1.start):null), actions.castExpr((o2!=null?((PythonTree)o2.getTree()):null)), actions.castExpr((o1!=null?((PythonTree)o1.getTree()):null)), actions.castExpr((e!=null?((PythonTree)e.getTree()):null)));
							         }
							}
							break;
						case 2 :
							// Python.g:1450:6: 
							{
							// AST REWRITE
							// elements: or_test
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							if ( state.backtracking==0 ) {
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (PythonTree)adaptor.nil();
							// 1450:6: -> or_test
							{
								adaptor.addChild(root_0, stream_or_test.nextTree());
							}


							retval.tree = root_0;
							}

							}
							break;

					}

					}
					break;
				case 2 :
					// Python.g:1452:7: lambdef
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_lambdef_in_test4911);
					lambdef220=lambdef();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, lambdef220.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   if (etype != null) {
			       retval.tree = etype;
			   }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "test"


	public static class test_nocond_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "test_nocond"
	// Python.g:1456:1: test_nocond[expr_contextType ctype] : (o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test ) | lambdef_nocond );
	public final PythonParser.test_nocond_return test_nocond(expr_contextType ctype) throws RecognitionException {
		PythonParser.test_nocond_return retval = new PythonParser.test_nocond_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token IF221=null;
		Token ORELSE222=null;
		ParserRuleReturnScope o1 =null;
		ParserRuleReturnScope o2 =null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope lambdef_nocond223 =null;

		PythonTree IF221_tree=null;
		PythonTree ORELSE222_tree=null;
		RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
		RewriteRuleTokenStream stream_ORELSE=new RewriteRuleTokenStream(adaptor,"token ORELSE");
		RewriteRuleSubtreeStream stream_or_test=new RewriteRuleSubtreeStream(adaptor,"rule or_test");
		RewriteRuleSubtreeStream stream_test=new RewriteRuleSubtreeStream(adaptor,"rule test");


		    expr etype = null;

		try {
			// Python.g:1465:5: (o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test ) | lambdef_nocond )
			int alt101=2;
			int LA101_0 = input.LA(1);
			if ( (LA101_0==AWAIT||LA101_0==COMPLEX||(LA101_0 >= DOLLER && LA101_0 <= DOT)||LA101_0==FLOAT||(LA101_0 >= LBRACK && LA101_0 <= LCURLY)||(LA101_0 >= LONGINT && LA101_0 <= MINUS)||LA101_0==NAME||LA101_0==NOT||LA101_0==PLUS||(LA101_0 >= STRING && LA101_0 <= TILDE)) ) {
				alt101=1;
			}
			else if ( (LA101_0==LAMBDA) ) {
				alt101=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 101, 0, input);
				throw nvae;
			}

			switch (alt101) {
				case 1 :
					// Python.g:1465:7: o1= or_test[ctype] ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test )
					{
					pushFollow(FOLLOW_or_test_in_test_nocond4942);
					o1=or_test(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_or_test.add(o1.getTree());
					// Python.g:1466:7: ( ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load] | -> or_test )
					int alt100=2;
					int LA100_0 = input.LA(1);
					if ( (LA100_0==IF) ) {
						int LA100_1 = input.LA(2);
						if ( (synpred6_Python()) ) {
							alt100=1;
						}
						else if ( (true) ) {
							alt100=2;
						}

					}
					else if ( (LA100_0==COMMA||LA100_0==FOR||(LA100_0 >= RBRACK && LA100_0 <= RCURLY)||LA100_0==RPAREN) ) {
						alt100=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 100, 0, input);
						throw nvae;
					}

					switch (alt100) {
						case 1 :
							// Python.g:1466:9: ( IF or_test[null] ORELSE )=> IF o2= or_test[ctype] ORELSE e= test[expr_contextType.Load]
							{
							IF221=(Token)match(input,IF,FOLLOW_IF_in_test_nocond4964); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_IF.add(IF221);

							pushFollow(FOLLOW_or_test_in_test_nocond4968);
							o2=or_test(ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_or_test.add(o2.getTree());
							ORELSE222=(Token)match(input,ORELSE,FOLLOW_ORELSE_in_test_nocond4971); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_ORELSE.add(ORELSE222);

							pushFollow(FOLLOW_test_in_test_nocond4975);
							e=test(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_test.add(e.getTree());
							if ( state.backtracking==0 ) {
							             etype = new IfExp((o1!=null?(o1.start):null), actions.castExpr((o2!=null?((PythonTree)o2.getTree()):null)), actions.castExpr((o1!=null?((PythonTree)o1.getTree()):null)), actions.castExpr((e!=null?((PythonTree)e.getTree()):null)));
							         }
							}
							break;
						case 2 :
							// Python.g:1471:6: 
							{
							// AST REWRITE
							// elements: or_test
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							if ( state.backtracking==0 ) {
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (PythonTree)adaptor.nil();
							// 1471:6: -> or_test
							{
								adaptor.addChild(root_0, stream_or_test.nextTree());
							}


							retval.tree = root_0;
							}

							}
							break;

					}

					}
					break;
				case 2 :
					// Python.g:1473:7: lambdef_nocond
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_lambdef_nocond_in_test_nocond5020);
					lambdef_nocond223=lambdef_nocond();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, lambdef_nocond223.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   if (etype != null) {
			       retval.tree = etype;
			   }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "test_nocond"


	public static class or_test_return extends ParserRuleReturnScope {
		public Token leftTok;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "or_test"
	// Python.g:1478:1: or_test[expr_contextType ctype] returns [Token leftTok] : left= and_test[ctype] ( (or= OR right+= and_test[ctype] )+ | -> $left) ;
	public final PythonParser.or_test_return or_test(expr_contextType ctype) throws RecognitionException {
		PythonParser.or_test_return retval = new PythonParser.or_test_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token or=null;
		List<Object> list_right=null;
		ParserRuleReturnScope left =null;
		RuleReturnScope right = null;
		PythonTree or_tree=null;
		RewriteRuleTokenStream stream_OR=new RewriteRuleTokenStream(adaptor,"token OR");
		RewriteRuleSubtreeStream stream_and_test=new RewriteRuleSubtreeStream(adaptor,"rule and_test");

		try {
			// Python.g:1489:5: (left= and_test[ctype] ( (or= OR right+= and_test[ctype] )+ | -> $left) )
			// Python.g:1489:7: left= and_test[ctype] ( (or= OR right+= and_test[ctype] )+ | -> $left)
			{
			pushFollow(FOLLOW_and_test_in_or_test5056);
			left=and_test(ctype);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_and_test.add(left.getTree());
			// Python.g:1490:9: ( (or= OR right+= and_test[ctype] )+ | -> $left)
			int alt103=2;
			int LA103_0 = input.LA(1);
			if ( (LA103_0==OR) ) {
				alt103=1;
			}
			else if ( (LA103_0==EOF||LA103_0==AMPEREQUAL||LA103_0==AS||LA103_0==ASSIGN||LA103_0==ATEQUAL||LA103_0==CIRCUMFLEXEQUAL||(LA103_0 >= COLON && LA103_0 <= COMMA)||LA103_0==DOUBLESLASHEQUAL||LA103_0==DOUBLESTAREQUAL||(LA103_0 >= FOR && LA103_0 <= FROM)||LA103_0==IF||LA103_0==LEFTSHIFTEQUAL||LA103_0==MINUSEQUAL||LA103_0==NEWLINE||LA103_0==ORELSE||LA103_0==PERCENTEQUAL||LA103_0==PLUSEQUAL||(LA103_0 >= RBRACK && LA103_0 <= RCURLY)||(LA103_0 >= RIGHTSHIFTEQUAL && LA103_0 <= SEMI)||LA103_0==SLASHEQUAL||LA103_0==STAREQUAL||LA103_0==VBAREQUAL) ) {
				alt103=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 103, 0, input);
				throw nvae;
			}

			switch (alt103) {
				case 1 :
					// Python.g:1490:11: (or= OR right+= and_test[ctype] )+
					{
					// Python.g:1490:11: (or= OR right+= and_test[ctype] )+
					int cnt102=0;
					loop102:
					while (true) {
						int alt102=2;
						int LA102_0 = input.LA(1);
						if ( (LA102_0==OR) ) {
							alt102=1;
						}

						switch (alt102) {
						case 1 :
							// Python.g:1490:12: or= OR right+= and_test[ctype]
							{
							or=(Token)match(input,OR,FOLLOW_OR_in_or_test5072); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_OR.add(or);

							pushFollow(FOLLOW_and_test_in_or_test5076);
							right=and_test(ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_and_test.add(right.getTree());
							if (list_right==null) list_right=new ArrayList<Object>();
							list_right.add(right.getTree());
							}
							break;

						default :
							if ( cnt102 >= 1 ) break loop102;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(102, input);
							throw eee;
						}
						cnt102++;
					}

					}
					break;
				case 2 :
					// Python.g:1493:8: 
					{
					// AST REWRITE
					// elements: left
					// token labels: 
					// rule labels: left, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (PythonTree)adaptor.nil();
					// 1493:8: -> $left
					{
						adaptor.addChild(root_0, stream_left.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (or != null) {
			        Token tok = (left!=null?(left.start):null);
			        if ((left!=null?((PythonParser.and_test_return)left).leftTok:null) != null) {
			            tok = (left!=null?((PythonParser.and_test_return)left).leftTok:null);
			        }
			        retval.tree = actions.makeBoolOp(tok, (left!=null?((PythonTree)left.getTree()):null), boolopType.Or, list_right);
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "or_test"


	public static class and_test_return extends ParserRuleReturnScope {
		public Token leftTok;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "and_test"
	// Python.g:1498:1: and_test[expr_contextType ctype] returns [Token leftTok] : left= not_test[ctype] ( (and= AND right+= not_test[ctype] )+ | -> $left) ;
	public final PythonParser.and_test_return and_test(expr_contextType ctype) throws RecognitionException {
		PythonParser.and_test_return retval = new PythonParser.and_test_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token and=null;
		List<Object> list_right=null;
		ParserRuleReturnScope left =null;
		RuleReturnScope right = null;
		PythonTree and_tree=null;
		RewriteRuleTokenStream stream_AND=new RewriteRuleTokenStream(adaptor,"token AND");
		RewriteRuleSubtreeStream stream_not_test=new RewriteRuleSubtreeStream(adaptor,"rule not_test");

		try {
			// Python.g:1509:5: (left= not_test[ctype] ( (and= AND right+= not_test[ctype] )+ | -> $left) )
			// Python.g:1509:7: left= not_test[ctype] ( (and= AND right+= not_test[ctype] )+ | -> $left)
			{
			pushFollow(FOLLOW_not_test_in_and_test5157);
			left=not_test(ctype);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_not_test.add(left.getTree());
			// Python.g:1510:9: ( (and= AND right+= not_test[ctype] )+ | -> $left)
			int alt105=2;
			int LA105_0 = input.LA(1);
			if ( (LA105_0==AND) ) {
				alt105=1;
			}
			else if ( (LA105_0==EOF||LA105_0==AMPEREQUAL||LA105_0==AS||LA105_0==ASSIGN||LA105_0==ATEQUAL||LA105_0==CIRCUMFLEXEQUAL||(LA105_0 >= COLON && LA105_0 <= COMMA)||LA105_0==DOUBLESLASHEQUAL||LA105_0==DOUBLESTAREQUAL||(LA105_0 >= FOR && LA105_0 <= FROM)||LA105_0==IF||LA105_0==LEFTSHIFTEQUAL||LA105_0==MINUSEQUAL||LA105_0==NEWLINE||(LA105_0 >= OR && LA105_0 <= ORELSE)||LA105_0==PERCENTEQUAL||LA105_0==PLUSEQUAL||(LA105_0 >= RBRACK && LA105_0 <= RCURLY)||(LA105_0 >= RIGHTSHIFTEQUAL && LA105_0 <= SEMI)||LA105_0==SLASHEQUAL||LA105_0==STAREQUAL||LA105_0==VBAREQUAL) ) {
				alt105=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 105, 0, input);
				throw nvae;
			}

			switch (alt105) {
				case 1 :
					// Python.g:1510:11: (and= AND right+= not_test[ctype] )+
					{
					// Python.g:1510:11: (and= AND right+= not_test[ctype] )+
					int cnt104=0;
					loop104:
					while (true) {
						int alt104=2;
						int LA104_0 = input.LA(1);
						if ( (LA104_0==AND) ) {
							alt104=1;
						}

						switch (alt104) {
						case 1 :
							// Python.g:1510:12: and= AND right+= not_test[ctype]
							{
							and=(Token)match(input,AND,FOLLOW_AND_in_and_test5173); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_AND.add(and);

							pushFollow(FOLLOW_not_test_in_and_test5177);
							right=not_test(ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_not_test.add(right.getTree());
							if (list_right==null) list_right=new ArrayList<Object>();
							list_right.add(right.getTree());
							}
							break;

						default :
							if ( cnt104 >= 1 ) break loop104;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(104, input);
							throw eee;
						}
						cnt104++;
					}

					}
					break;
				case 2 :
					// Python.g:1513:8: 
					{
					// AST REWRITE
					// elements: left
					// token labels: 
					// rule labels: left, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (PythonTree)adaptor.nil();
					// 1513:8: -> $left
					{
						adaptor.addChild(root_0, stream_left.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (and != null) {
			        Token tok = (left!=null?(left.start):null);
			        if ((left!=null?((PythonParser.not_test_return)left).leftTok:null) != null) {
			            tok = (left!=null?((PythonParser.not_test_return)left).leftTok:null);
			        }
			        retval.tree = actions.makeBoolOp(tok, (left!=null?((PythonTree)left.getTree()):null), boolopType.And, list_right);
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "and_test"


	public static class not_test_return extends ParserRuleReturnScope {
		public Token leftTok;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "not_test"
	// Python.g:1518:1: not_test[expr_contextType ctype] returns [Token leftTok] : ( NOT nt= not_test[ctype] | comparison[ctype] );
	public final PythonParser.not_test_return not_test(expr_contextType ctype) throws RecognitionException {
		PythonParser.not_test_return retval = new PythonParser.not_test_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token NOT224=null;
		ParserRuleReturnScope nt =null;
		ParserRuleReturnScope comparison225 =null;

		PythonTree NOT224_tree=null;


		    expr etype = null;

		try {
			// Python.g:1528:5: ( NOT nt= not_test[ctype] | comparison[ctype] )
			int alt106=2;
			int LA106_0 = input.LA(1);
			if ( (LA106_0==NOT) ) {
				alt106=1;
			}
			else if ( (LA106_0==AWAIT||LA106_0==COMPLEX||(LA106_0 >= DOLLER && LA106_0 <= DOT)||LA106_0==FLOAT||(LA106_0 >= LBRACK && LA106_0 <= LCURLY)||(LA106_0 >= LONGINT && LA106_0 <= MINUS)||LA106_0==NAME||LA106_0==PLUS||(LA106_0 >= STRING && LA106_0 <= TILDE)) ) {
				alt106=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 106, 0, input);
				throw nvae;
			}

			switch (alt106) {
				case 1 :
					// Python.g:1528:7: NOT nt= not_test[ctype]
					{
					root_0 = (PythonTree)adaptor.nil();


					NOT224=(Token)match(input,NOT,FOLLOW_NOT_in_not_test5261); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NOT224_tree = (PythonTree)adaptor.create(NOT224);
					adaptor.addChild(root_0, NOT224_tree);
					}

					pushFollow(FOLLOW_not_test_in_not_test5265);
					nt=not_test(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, nt.getTree());

					if ( state.backtracking==0 ) {
					          etype = new UnaryOp(NOT224, unaryopType.Not, actions.castExpr((nt!=null?((PythonTree)nt.getTree()):null)));
					      }
					}
					break;
				case 2 :
					// Python.g:1532:7: comparison[ctype]
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_comparison_in_not_test5282);
					comparison225=comparison(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, comparison225.getTree());

					if ( state.backtracking==0 ) {
					          retval.leftTok = (comparison225!=null?((PythonParser.comparison_return)comparison225).leftTok:null);
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   if (etype != null) {
			       retval.tree = etype;
			   }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "not_test"


	public static class comparison_return extends ParserRuleReturnScope {
		public Token leftTok;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "comparison"
	// Python.g:1539:1: comparison[expr_contextType ctype] returns [Token leftTok] : left= expr[ctype] ( ( comp_op right+= expr[ctype] )+ | -> $left) ;
	public final PythonParser.comparison_return comparison(expr_contextType ctype) throws RecognitionException {
		PythonParser.comparison_return retval = new PythonParser.comparison_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		List<Object> list_right=null;
		ParserRuleReturnScope left =null;
		ParserRuleReturnScope comp_op226 =null;
		RuleReturnScope right = null;
		RewriteRuleSubtreeStream stream_comp_op=new RewriteRuleSubtreeStream(adaptor,"rule comp_op");
		RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");


		    List cmps = new ArrayList();

		try {
			// Python.g:1551:5: (left= expr[ctype] ( ( comp_op right+= expr[ctype] )+ | -> $left) )
			// Python.g:1551:7: left= expr[ctype] ( ( comp_op right+= expr[ctype] )+ | -> $left)
			{
			pushFollow(FOLLOW_expr_in_comparison5331);
			left=expr(ctype);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_expr.add(left.getTree());
			// Python.g:1552:8: ( ( comp_op right+= expr[ctype] )+ | -> $left)
			int alt108=2;
			int LA108_0 = input.LA(1);
			if ( (LA108_0==ALT_NOTEQUAL||LA108_0==EQUAL||(LA108_0 >= GREATER && LA108_0 <= GREATEREQUAL)||LA108_0==IN||LA108_0==IS||(LA108_0 >= LESS && LA108_0 <= LESSEQUAL)||(LA108_0 >= NOT && LA108_0 <= NOTEQUAL)) ) {
				alt108=1;
			}
			else if ( (LA108_0==EOF||(LA108_0 >= AMPEREQUAL && LA108_0 <= AND)||LA108_0==AS||LA108_0==ASSIGN||LA108_0==ATEQUAL||LA108_0==CIRCUMFLEXEQUAL||(LA108_0 >= COLON && LA108_0 <= COMMA)||LA108_0==DOUBLESLASHEQUAL||LA108_0==DOUBLESTAREQUAL||(LA108_0 >= FOR && LA108_0 <= FROM)||LA108_0==IF||LA108_0==LEFTSHIFTEQUAL||LA108_0==MINUSEQUAL||LA108_0==NEWLINE||(LA108_0 >= OR && LA108_0 <= ORELSE)||LA108_0==PERCENTEQUAL||LA108_0==PLUSEQUAL||(LA108_0 >= RBRACK && LA108_0 <= RCURLY)||(LA108_0 >= RIGHTSHIFTEQUAL && LA108_0 <= SEMI)||LA108_0==SLASHEQUAL||LA108_0==STAREQUAL||LA108_0==VBAREQUAL) ) {
				alt108=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 108, 0, input);
				throw nvae;
			}

			switch (alt108) {
				case 1 :
					// Python.g:1552:10: ( comp_op right+= expr[ctype] )+
					{
					// Python.g:1552:10: ( comp_op right+= expr[ctype] )+
					int cnt107=0;
					loop107:
					while (true) {
						int alt107=2;
						int LA107_0 = input.LA(1);
						if ( (LA107_0==ALT_NOTEQUAL||LA107_0==EQUAL||(LA107_0 >= GREATER && LA107_0 <= GREATEREQUAL)||LA107_0==IN||LA107_0==IS||(LA107_0 >= LESS && LA107_0 <= LESSEQUAL)||(LA107_0 >= NOT && LA107_0 <= NOTEQUAL)) ) {
							alt107=1;
						}

						switch (alt107) {
						case 1 :
							// Python.g:1552:12: comp_op right+= expr[ctype]
							{
							pushFollow(FOLLOW_comp_op_in_comparison5345);
							comp_op226=comp_op();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_comp_op.add(comp_op226.getTree());
							pushFollow(FOLLOW_expr_in_comparison5349);
							right=expr(ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_expr.add(right.getTree());
							if (list_right==null) list_right=new ArrayList<Object>();
							list_right.add(right.getTree());
							if ( state.backtracking==0 ) {
							               cmps.add((comp_op226!=null?((PythonParser.comp_op_return)comp_op226).op:null));
							           }
							}
							break;

						default :
							if ( cnt107 >= 1 ) break loop107;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(107, input);
							throw eee;
						}
						cnt107++;
					}

					}
					break;
				case 2 :
					// Python.g:1558:7: 
					{
					// AST REWRITE
					// elements: left
					// token labels: 
					// rule labels: left, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (PythonTree)adaptor.nil();
					// 1558:7: -> $left
					{
						adaptor.addChild(root_0, stream_left.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.leftTok = (left!=null?((PythonParser.expr_return)left).leftTok:null);
			    if (!cmps.isEmpty()) {
			        retval.tree = new Compare((left!=null?(left.start):null), actions.castExpr((left!=null?((PythonTree)left.getTree()):null)), actions.makeCmpOps(cmps),
			            actions.castExprs(list_right));
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comparison"


	public static class comp_op_return extends ParserRuleReturnScope {
		public cmpopType op;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "comp_op"
	// Python.g:1563:1: comp_op returns [cmpopType op] : ( LESS | GREATER | EQUAL | GREATEREQUAL | LESSEQUAL | ALT_NOTEQUAL | NOTEQUAL | IN | NOT IN | IS | IS NOT );
	public final PythonParser.comp_op_return comp_op() throws RecognitionException {
		PythonParser.comp_op_return retval = new PythonParser.comp_op_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token LESS227=null;
		Token GREATER228=null;
		Token EQUAL229=null;
		Token GREATEREQUAL230=null;
		Token LESSEQUAL231=null;
		Token ALT_NOTEQUAL232=null;
		Token NOTEQUAL233=null;
		Token IN234=null;
		Token NOT235=null;
		Token IN236=null;
		Token IS237=null;
		Token IS238=null;
		Token NOT239=null;

		PythonTree LESS227_tree=null;
		PythonTree GREATER228_tree=null;
		PythonTree EQUAL229_tree=null;
		PythonTree GREATEREQUAL230_tree=null;
		PythonTree LESSEQUAL231_tree=null;
		PythonTree ALT_NOTEQUAL232_tree=null;
		PythonTree NOTEQUAL233_tree=null;
		PythonTree IN234_tree=null;
		PythonTree NOT235_tree=null;
		PythonTree IN236_tree=null;
		PythonTree IS237_tree=null;
		PythonTree IS238_tree=null;
		PythonTree NOT239_tree=null;

		try {
			// Python.g:1565:5: ( LESS | GREATER | EQUAL | GREATEREQUAL | LESSEQUAL | ALT_NOTEQUAL | NOTEQUAL | IN | NOT IN | IS | IS NOT )
			int alt109=11;
			switch ( input.LA(1) ) {
			case LESS:
				{
				alt109=1;
				}
				break;
			case GREATER:
				{
				alt109=2;
				}
				break;
			case EQUAL:
				{
				alt109=3;
				}
				break;
			case GREATEREQUAL:
				{
				alt109=4;
				}
				break;
			case LESSEQUAL:
				{
				alt109=5;
				}
				break;
			case ALT_NOTEQUAL:
				{
				alt109=6;
				}
				break;
			case NOTEQUAL:
				{
				alt109=7;
				}
				break;
			case IN:
				{
				alt109=8;
				}
				break;
			case NOT:
				{
				alt109=9;
				}
				break;
			case IS:
				{
				int LA109_10 = input.LA(2);
				if ( (LA109_10==NOT) ) {
					alt109=11;
				}
				else if ( (LA109_10==AWAIT||LA109_10==COMPLEX||(LA109_10 >= DOLLER && LA109_10 <= DOT)||LA109_10==FLOAT||(LA109_10 >= LBRACK && LA109_10 <= LCURLY)||(LA109_10 >= LONGINT && LA109_10 <= MINUS)||LA109_10==NAME||LA109_10==PLUS||(LA109_10 >= STRING && LA109_10 <= TILDE)) ) {
					alt109=10;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 109, 10, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 109, 0, input);
				throw nvae;
			}
			switch (alt109) {
				case 1 :
					// Python.g:1565:7: LESS
					{
					root_0 = (PythonTree)adaptor.nil();


					LESS227=(Token)match(input,LESS,FOLLOW_LESS_in_comp_op5430); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LESS227_tree = (PythonTree)adaptor.create(LESS227);
					adaptor.addChild(root_0, LESS227_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.Lt;
					      }
					}
					break;
				case 2 :
					// Python.g:1569:7: GREATER
					{
					root_0 = (PythonTree)adaptor.nil();


					GREATER228=(Token)match(input,GREATER,FOLLOW_GREATER_in_comp_op5446); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					GREATER228_tree = (PythonTree)adaptor.create(GREATER228);
					adaptor.addChild(root_0, GREATER228_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.Gt;
					      }
					}
					break;
				case 3 :
					// Python.g:1573:7: EQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					EQUAL229=(Token)match(input,EQUAL,FOLLOW_EQUAL_in_comp_op5462); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					EQUAL229_tree = (PythonTree)adaptor.create(EQUAL229);
					adaptor.addChild(root_0, EQUAL229_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.Eq;
					      }
					}
					break;
				case 4 :
					// Python.g:1577:7: GREATEREQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					GREATEREQUAL230=(Token)match(input,GREATEREQUAL,FOLLOW_GREATEREQUAL_in_comp_op5478); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					GREATEREQUAL230_tree = (PythonTree)adaptor.create(GREATEREQUAL230);
					adaptor.addChild(root_0, GREATEREQUAL230_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.GtE;
					      }
					}
					break;
				case 5 :
					// Python.g:1581:7: LESSEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					LESSEQUAL231=(Token)match(input,LESSEQUAL,FOLLOW_LESSEQUAL_in_comp_op5494); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LESSEQUAL231_tree = (PythonTree)adaptor.create(LESSEQUAL231);
					adaptor.addChild(root_0, LESSEQUAL231_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.LtE;
					      }
					}
					break;
				case 6 :
					// Python.g:1585:7: ALT_NOTEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					ALT_NOTEQUAL232=(Token)match(input,ALT_NOTEQUAL,FOLLOW_ALT_NOTEQUAL_in_comp_op5510); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					ALT_NOTEQUAL232_tree = (PythonTree)adaptor.create(ALT_NOTEQUAL232);
					adaptor.addChild(root_0, ALT_NOTEQUAL232_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.NotEq;
					      }
					}
					break;
				case 7 :
					// Python.g:1589:7: NOTEQUAL
					{
					root_0 = (PythonTree)adaptor.nil();


					NOTEQUAL233=(Token)match(input,NOTEQUAL,FOLLOW_NOTEQUAL_in_comp_op5526); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NOTEQUAL233_tree = (PythonTree)adaptor.create(NOTEQUAL233);
					adaptor.addChild(root_0, NOTEQUAL233_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.NotEq;
					      }
					}
					break;
				case 8 :
					// Python.g:1593:7: IN
					{
					root_0 = (PythonTree)adaptor.nil();


					IN234=(Token)match(input,IN,FOLLOW_IN_in_comp_op5542); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IN234_tree = (PythonTree)adaptor.create(IN234);
					adaptor.addChild(root_0, IN234_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.In;
					      }
					}
					break;
				case 9 :
					// Python.g:1597:7: NOT IN
					{
					root_0 = (PythonTree)adaptor.nil();


					NOT235=(Token)match(input,NOT,FOLLOW_NOT_in_comp_op5558); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NOT235_tree = (PythonTree)adaptor.create(NOT235);
					adaptor.addChild(root_0, NOT235_tree);
					}

					IN236=(Token)match(input,IN,FOLLOW_IN_in_comp_op5560); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IN236_tree = (PythonTree)adaptor.create(IN236);
					adaptor.addChild(root_0, IN236_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.NotIn;
					      }
					}
					break;
				case 10 :
					// Python.g:1601:7: IS
					{
					root_0 = (PythonTree)adaptor.nil();


					IS237=(Token)match(input,IS,FOLLOW_IS_in_comp_op5576); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IS237_tree = (PythonTree)adaptor.create(IS237);
					adaptor.addChild(root_0, IS237_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.Is;
					      }
					}
					break;
				case 11 :
					// Python.g:1605:7: IS NOT
					{
					root_0 = (PythonTree)adaptor.nil();


					IS238=(Token)match(input,IS,FOLLOW_IS_in_comp_op5592); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					IS238_tree = (PythonTree)adaptor.create(IS238);
					adaptor.addChild(root_0, IS238_tree);
					}

					NOT239=(Token)match(input,NOT,FOLLOW_NOT_in_comp_op5594); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NOT239_tree = (PythonTree)adaptor.create(NOT239);
					adaptor.addChild(root_0, NOT239_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = cmpopType.IsNot;
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comp_op"


	protected static class expr_scope {
		expr_contextType ctype;
	}
	protected Stack<expr_scope> expr_stack = new Stack<expr_scope>();

	public static class expr_return extends ParserRuleReturnScope {
		public Token leftTok;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "expr"
	// Python.g:1612:1: expr[expr_contextType ect] returns [Token leftTok] : left= xor_expr ( (op= VBAR right+= xor_expr )+ | -> $left) ;
	public final PythonParser.expr_return expr(expr_contextType ect) throws RecognitionException {
		expr_stack.push(new expr_scope());
		PythonParser.expr_return retval = new PythonParser.expr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token op=null;
		List<Object> list_right=null;
		ParserRuleReturnScope left =null;
		RuleReturnScope right = null;
		PythonTree op_tree=null;
		RewriteRuleTokenStream stream_VBAR=new RewriteRuleTokenStream(adaptor,"token VBAR");
		RewriteRuleSubtreeStream stream_xor_expr=new RewriteRuleSubtreeStream(adaptor,"rule xor_expr");


		    expr_stack.peek().ctype = ect;

		try {
			// Python.g:1630:5: (left= xor_expr ( (op= VBAR right+= xor_expr )+ | -> $left) )
			// Python.g:1630:7: left= xor_expr ( (op= VBAR right+= xor_expr )+ | -> $left)
			{
			pushFollow(FOLLOW_xor_expr_in_expr5646);
			left=xor_expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_xor_expr.add(left.getTree());
			// Python.g:1631:9: ( (op= VBAR right+= xor_expr )+ | -> $left)
			int alt111=2;
			int LA111_0 = input.LA(1);
			if ( (LA111_0==VBAR) ) {
				alt111=1;
			}
			else if ( (LA111_0==EOF||LA111_0==ALT_NOTEQUAL||(LA111_0 >= AMPEREQUAL && LA111_0 <= AND)||LA111_0==AS||LA111_0==ASSIGN||LA111_0==ATEQUAL||LA111_0==CIRCUMFLEXEQUAL||(LA111_0 >= COLON && LA111_0 <= COMMA)||LA111_0==DOUBLESLASHEQUAL||LA111_0==DOUBLESTAREQUAL||LA111_0==EQUAL||(LA111_0 >= FOR && LA111_0 <= FROM)||(LA111_0 >= GREATER && LA111_0 <= IF)||LA111_0==IN||LA111_0==IS||(LA111_0 >= LEFTSHIFTEQUAL && LA111_0 <= LESSEQUAL)||LA111_0==MINUSEQUAL||LA111_0==NEWLINE||(LA111_0 >= NOT && LA111_0 <= ORELSE)||LA111_0==PERCENTEQUAL||LA111_0==PLUSEQUAL||(LA111_0 >= RBRACK && LA111_0 <= RCURLY)||(LA111_0 >= RIGHTSHIFTEQUAL && LA111_0 <= SEMI)||LA111_0==SLASHEQUAL||LA111_0==STAREQUAL||LA111_0==VBAREQUAL) ) {
				alt111=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 111, 0, input);
				throw nvae;
			}

			switch (alt111) {
				case 1 :
					// Python.g:1631:11: (op= VBAR right+= xor_expr )+
					{
					// Python.g:1631:11: (op= VBAR right+= xor_expr )+
					int cnt110=0;
					loop110:
					while (true) {
						int alt110=2;
						int LA110_0 = input.LA(1);
						if ( (LA110_0==VBAR) ) {
							alt110=1;
						}

						switch (alt110) {
						case 1 :
							// Python.g:1631:12: op= VBAR right+= xor_expr
							{
							op=(Token)match(input,VBAR,FOLLOW_VBAR_in_expr5661); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_VBAR.add(op);

							pushFollow(FOLLOW_xor_expr_in_expr5665);
							right=xor_expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_xor_expr.add(right.getTree());
							if (list_right==null) list_right=new ArrayList<Object>();
							list_right.add(right.getTree());
							}
							break;

						default :
							if ( cnt110 >= 1 ) break loop110;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(110, input);
							throw eee;
						}
						cnt110++;
					}

					}
					break;
				case 2 :
					// Python.g:1634:8: 
					{
					// AST REWRITE
					// elements: left
					// token labels: 
					// rule labels: left, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (PythonTree)adaptor.nil();
					// 1634:8: -> $left
					{
						adaptor.addChild(root_0, stream_left.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.leftTok = (left!=null?((PythonParser.xor_expr_return)left).lparen:null);
			    if (op != null) {
			        Token tok = (left!=null?(left.start):null);
			        if ((left!=null?((PythonParser.xor_expr_return)left).lparen:null) != null) {
			            tok = (left!=null?((PythonParser.xor_expr_return)left).lparen:null);
			        }
			        retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.getTree()):null), operatorType.BitOr, list_right);
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
			expr_stack.pop();
		}
		return retval;
	}
	// $ANTLR end "expr"


	public static class xor_expr_return extends ParserRuleReturnScope {
		public Token lparen = null;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "xor_expr"
	// Python.g:1640:1: xor_expr returns [Token lparen = null] : left= and_expr ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left) ;
	public final PythonParser.xor_expr_return xor_expr() throws RecognitionException {
		PythonParser.xor_expr_return retval = new PythonParser.xor_expr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token op=null;
		List<Object> list_right=null;
		ParserRuleReturnScope left =null;
		RuleReturnScope right = null;
		PythonTree op_tree=null;
		RewriteRuleTokenStream stream_CIRCUMFLEX=new RewriteRuleTokenStream(adaptor,"token CIRCUMFLEX");
		RewriteRuleSubtreeStream stream_and_expr=new RewriteRuleSubtreeStream(adaptor,"rule and_expr");

		try {
			// Python.g:1652:5: (left= and_expr ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left) )
			// Python.g:1652:7: left= and_expr ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left)
			{
			pushFollow(FOLLOW_and_expr_in_xor_expr5744);
			left=and_expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_and_expr.add(left.getTree());
			// Python.g:1653:9: ( (op= CIRCUMFLEX right+= and_expr )+ | -> $left)
			int alt113=2;
			int LA113_0 = input.LA(1);
			if ( (LA113_0==CIRCUMFLEX) ) {
				alt113=1;
			}
			else if ( (LA113_0==EOF||LA113_0==ALT_NOTEQUAL||(LA113_0 >= AMPEREQUAL && LA113_0 <= AND)||LA113_0==AS||LA113_0==ASSIGN||LA113_0==ATEQUAL||LA113_0==CIRCUMFLEXEQUAL||(LA113_0 >= COLON && LA113_0 <= COMMA)||LA113_0==DOUBLESLASHEQUAL||LA113_0==DOUBLESTAREQUAL||LA113_0==EQUAL||(LA113_0 >= FOR && LA113_0 <= FROM)||(LA113_0 >= GREATER && LA113_0 <= IF)||LA113_0==IN||LA113_0==IS||(LA113_0 >= LEFTSHIFTEQUAL && LA113_0 <= LESSEQUAL)||LA113_0==MINUSEQUAL||LA113_0==NEWLINE||(LA113_0 >= NOT && LA113_0 <= ORELSE)||LA113_0==PERCENTEQUAL||LA113_0==PLUSEQUAL||(LA113_0 >= RBRACK && LA113_0 <= RCURLY)||(LA113_0 >= RIGHTSHIFTEQUAL && LA113_0 <= SEMI)||LA113_0==SLASHEQUAL||LA113_0==STAREQUAL||(LA113_0 >= VBAR && LA113_0 <= VBAREQUAL)) ) {
				alt113=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 113, 0, input);
				throw nvae;
			}

			switch (alt113) {
				case 1 :
					// Python.g:1653:11: (op= CIRCUMFLEX right+= and_expr )+
					{
					// Python.g:1653:11: (op= CIRCUMFLEX right+= and_expr )+
					int cnt112=0;
					loop112:
					while (true) {
						int alt112=2;
						int LA112_0 = input.LA(1);
						if ( (LA112_0==CIRCUMFLEX) ) {
							alt112=1;
						}

						switch (alt112) {
						case 1 :
							// Python.g:1653:12: op= CIRCUMFLEX right+= and_expr
							{
							op=(Token)match(input,CIRCUMFLEX,FOLLOW_CIRCUMFLEX_in_xor_expr5759); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_CIRCUMFLEX.add(op);

							pushFollow(FOLLOW_and_expr_in_xor_expr5763);
							right=and_expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_and_expr.add(right.getTree());
							if (list_right==null) list_right=new ArrayList<Object>();
							list_right.add(right.getTree());
							}
							break;

						default :
							if ( cnt112 >= 1 ) break loop112;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(112, input);
							throw eee;
						}
						cnt112++;
					}

					}
					break;
				case 2 :
					// Python.g:1656:8: 
					{
					// AST REWRITE
					// elements: left
					// token labels: 
					// rule labels: left, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (PythonTree)adaptor.nil();
					// 1656:8: -> $left
					{
						adaptor.addChild(root_0, stream_left.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (op != null) {
			        Token tok = (left!=null?(left.start):null);
			        if ((left!=null?((PythonParser.and_expr_return)left).lparen:null) != null) {
			            tok = (left!=null?((PythonParser.and_expr_return)left).lparen:null);
			        }
			        retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.getTree()):null), operatorType.BitXor, list_right);
			    }
			    retval.lparen = (left!=null?((PythonParser.and_expr_return)left).lparen:null);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "xor_expr"


	public static class and_expr_return extends ParserRuleReturnScope {
		public Token lparen = null;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "and_expr"
	// Python.g:1661:1: and_expr returns [Token lparen = null] : left= shift_expr ( (op= AMPER right+= shift_expr )+ | -> $left) ;
	public final PythonParser.and_expr_return and_expr() throws RecognitionException {
		PythonParser.and_expr_return retval = new PythonParser.and_expr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token op=null;
		List<Object> list_right=null;
		ParserRuleReturnScope left =null;
		RuleReturnScope right = null;
		PythonTree op_tree=null;
		RewriteRuleTokenStream stream_AMPER=new RewriteRuleTokenStream(adaptor,"token AMPER");
		RewriteRuleSubtreeStream stream_shift_expr=new RewriteRuleSubtreeStream(adaptor,"rule shift_expr");

		try {
			// Python.g:1673:5: (left= shift_expr ( (op= AMPER right+= shift_expr )+ | -> $left) )
			// Python.g:1673:7: left= shift_expr ( (op= AMPER right+= shift_expr )+ | -> $left)
			{
			pushFollow(FOLLOW_shift_expr_in_and_expr5841);
			left=shift_expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_shift_expr.add(left.getTree());
			// Python.g:1674:9: ( (op= AMPER right+= shift_expr )+ | -> $left)
			int alt115=2;
			int LA115_0 = input.LA(1);
			if ( (LA115_0==AMPER) ) {
				alt115=1;
			}
			else if ( (LA115_0==EOF||LA115_0==ALT_NOTEQUAL||(LA115_0 >= AMPEREQUAL && LA115_0 <= AND)||LA115_0==AS||LA115_0==ASSIGN||LA115_0==ATEQUAL||(LA115_0 >= CIRCUMFLEX && LA115_0 <= CIRCUMFLEXEQUAL)||(LA115_0 >= COLON && LA115_0 <= COMMA)||LA115_0==DOUBLESLASHEQUAL||LA115_0==DOUBLESTAREQUAL||LA115_0==EQUAL||(LA115_0 >= FOR && LA115_0 <= FROM)||(LA115_0 >= GREATER && LA115_0 <= IF)||LA115_0==IN||LA115_0==IS||(LA115_0 >= LEFTSHIFTEQUAL && LA115_0 <= LESSEQUAL)||LA115_0==MINUSEQUAL||LA115_0==NEWLINE||(LA115_0 >= NOT && LA115_0 <= ORELSE)||LA115_0==PERCENTEQUAL||LA115_0==PLUSEQUAL||(LA115_0 >= RBRACK && LA115_0 <= RCURLY)||(LA115_0 >= RIGHTSHIFTEQUAL && LA115_0 <= SEMI)||LA115_0==SLASHEQUAL||LA115_0==STAREQUAL||(LA115_0 >= VBAR && LA115_0 <= VBAREQUAL)) ) {
				alt115=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 115, 0, input);
				throw nvae;
			}

			switch (alt115) {
				case 1 :
					// Python.g:1674:11: (op= AMPER right+= shift_expr )+
					{
					// Python.g:1674:11: (op= AMPER right+= shift_expr )+
					int cnt114=0;
					loop114:
					while (true) {
						int alt114=2;
						int LA114_0 = input.LA(1);
						if ( (LA114_0==AMPER) ) {
							alt114=1;
						}

						switch (alt114) {
						case 1 :
							// Python.g:1674:12: op= AMPER right+= shift_expr
							{
							op=(Token)match(input,AMPER,FOLLOW_AMPER_in_and_expr5856); if (state.failed) return retval; 
							if ( state.backtracking==0 ) stream_AMPER.add(op);

							pushFollow(FOLLOW_shift_expr_in_and_expr5860);
							right=shift_expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_shift_expr.add(right.getTree());
							if (list_right==null) list_right=new ArrayList<Object>();
							list_right.add(right.getTree());
							}
							break;

						default :
							if ( cnt114 >= 1 ) break loop114;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(114, input);
							throw eee;
						}
						cnt114++;
					}

					}
					break;
				case 2 :
					// Python.g:1677:8: 
					{
					// AST REWRITE
					// elements: left
					// token labels: 
					// rule labels: left, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (PythonTree)adaptor.nil();
					// 1677:8: -> $left
					{
						adaptor.addChild(root_0, stream_left.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (op != null) {
			        Token tok = (left!=null?(left.start):null);
			        if ((left!=null?((PythonParser.shift_expr_return)left).lparen:null) != null) {
			            tok = (left!=null?((PythonParser.shift_expr_return)left).lparen:null);
			        }
			        retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.getTree()):null), operatorType.BitAnd, list_right);
			    }
			    retval.lparen = (left!=null?((PythonParser.shift_expr_return)left).lparen:null);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "and_expr"


	public static class shift_expr_return extends ParserRuleReturnScope {
		public Token lparen = null;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "shift_expr"
	// Python.g:1682:1: shift_expr returns [Token lparen = null] : left= arith_expr ( ( shift_op right+= arith_expr )+ | -> $left) ;
	public final PythonParser.shift_expr_return shift_expr() throws RecognitionException {
		PythonParser.shift_expr_return retval = new PythonParser.shift_expr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		List<Object> list_right=null;
		ParserRuleReturnScope left =null;
		ParserRuleReturnScope shift_op240 =null;
		RuleReturnScope right = null;
		RewriteRuleSubtreeStream stream_shift_op=new RewriteRuleSubtreeStream(adaptor,"rule shift_op");
		RewriteRuleSubtreeStream stream_arith_expr=new RewriteRuleSubtreeStream(adaptor,"rule arith_expr");


		    List ops = new ArrayList();
		    List toks = new ArrayList();

		try {
			// Python.g:1698:5: (left= arith_expr ( ( shift_op right+= arith_expr )+ | -> $left) )
			// Python.g:1698:7: left= arith_expr ( ( shift_op right+= arith_expr )+ | -> $left)
			{
			pushFollow(FOLLOW_arith_expr_in_shift_expr5943);
			left=arith_expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_arith_expr.add(left.getTree());
			// Python.g:1699:9: ( ( shift_op right+= arith_expr )+ | -> $left)
			int alt117=2;
			int LA117_0 = input.LA(1);
			if ( (LA117_0==LEFTSHIFT||LA117_0==RIGHTSHIFT) ) {
				alt117=1;
			}
			else if ( (LA117_0==EOF||(LA117_0 >= ALT_NOTEQUAL && LA117_0 <= AND)||LA117_0==AS||LA117_0==ASSIGN||LA117_0==ATEQUAL||(LA117_0 >= CIRCUMFLEX && LA117_0 <= CIRCUMFLEXEQUAL)||(LA117_0 >= COLON && LA117_0 <= COMMA)||LA117_0==DOUBLESLASHEQUAL||LA117_0==DOUBLESTAREQUAL||LA117_0==EQUAL||(LA117_0 >= FOR && LA117_0 <= FROM)||(LA117_0 >= GREATER && LA117_0 <= IF)||LA117_0==IN||LA117_0==IS||(LA117_0 >= LEFTSHIFTEQUAL && LA117_0 <= LESSEQUAL)||LA117_0==MINUSEQUAL||LA117_0==NEWLINE||(LA117_0 >= NOT && LA117_0 <= ORELSE)||LA117_0==PERCENTEQUAL||LA117_0==PLUSEQUAL||(LA117_0 >= RBRACK && LA117_0 <= RCURLY)||(LA117_0 >= RIGHTSHIFTEQUAL && LA117_0 <= SEMI)||LA117_0==SLASHEQUAL||LA117_0==STAREQUAL||(LA117_0 >= VBAR && LA117_0 <= VBAREQUAL)) ) {
				alt117=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 117, 0, input);
				throw nvae;
			}

			switch (alt117) {
				case 1 :
					// Python.g:1699:11: ( shift_op right+= arith_expr )+
					{
					// Python.g:1699:11: ( shift_op right+= arith_expr )+
					int cnt116=0;
					loop116:
					while (true) {
						int alt116=2;
						int LA116_0 = input.LA(1);
						if ( (LA116_0==LEFTSHIFT||LA116_0==RIGHTSHIFT) ) {
							alt116=1;
						}

						switch (alt116) {
						case 1 :
							// Python.g:1699:13: shift_op right+= arith_expr
							{
							pushFollow(FOLLOW_shift_op_in_shift_expr5957);
							shift_op240=shift_op();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_shift_op.add(shift_op240.getTree());
							pushFollow(FOLLOW_arith_expr_in_shift_expr5961);
							right=arith_expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_arith_expr.add(right.getTree());
							if (list_right==null) list_right=new ArrayList<Object>();
							list_right.add(right.getTree());
							if ( state.backtracking==0 ) {
							                ops.add((shift_op240!=null?((PythonParser.shift_op_return)shift_op240).op:null));
							                toks.add((shift_op240!=null?(shift_op240.start):null));
							            }
							}
							break;

						default :
							if ( cnt116 >= 1 ) break loop116;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(116, input);
							throw eee;
						}
						cnt116++;
					}

					}
					break;
				case 2 :
					// Python.g:1706:8: 
					{
					// AST REWRITE
					// elements: left
					// token labels: 
					// rule labels: left, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (PythonTree)adaptor.nil();
					// 1706:8: -> $left
					{
						adaptor.addChild(root_0, stream_left.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (!ops.isEmpty()) {
			        Token tok = (left!=null?(left.start):null);
			        if ((left!=null?((PythonParser.arith_expr_return)left).lparen:null) != null) {
			            tok = (left!=null?((PythonParser.arith_expr_return)left).lparen:null);
			        }
			        retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.getTree()):null), ops, list_right, toks);
			    }
			    retval.lparen = (left!=null?((PythonParser.arith_expr_return)left).lparen:null);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "shift_expr"


	public static class shift_op_return extends ParserRuleReturnScope {
		public operatorType op;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "shift_op"
	// Python.g:1710:1: shift_op returns [operatorType op] : ( LEFTSHIFT | RIGHTSHIFT );
	public final PythonParser.shift_op_return shift_op() throws RecognitionException {
		PythonParser.shift_op_return retval = new PythonParser.shift_op_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token LEFTSHIFT241=null;
		Token RIGHTSHIFT242=null;

		PythonTree LEFTSHIFT241_tree=null;
		PythonTree RIGHTSHIFT242_tree=null;

		try {
			// Python.g:1712:5: ( LEFTSHIFT | RIGHTSHIFT )
			int alt118=2;
			int LA118_0 = input.LA(1);
			if ( (LA118_0==LEFTSHIFT) ) {
				alt118=1;
			}
			else if ( (LA118_0==RIGHTSHIFT) ) {
				alt118=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 118, 0, input);
				throw nvae;
			}

			switch (alt118) {
				case 1 :
					// Python.g:1712:7: LEFTSHIFT
					{
					root_0 = (PythonTree)adaptor.nil();


					LEFTSHIFT241=(Token)match(input,LEFTSHIFT,FOLLOW_LEFTSHIFT_in_shift_op6045); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LEFTSHIFT241_tree = (PythonTree)adaptor.create(LEFTSHIFT241);
					adaptor.addChild(root_0, LEFTSHIFT241_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = operatorType.LShift;
					      }
					}
					break;
				case 2 :
					// Python.g:1716:7: RIGHTSHIFT
					{
					root_0 = (PythonTree)adaptor.nil();


					RIGHTSHIFT242=(Token)match(input,RIGHTSHIFT,FOLLOW_RIGHTSHIFT_in_shift_op6061); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RIGHTSHIFT242_tree = (PythonTree)adaptor.create(RIGHTSHIFT242);
					adaptor.addChild(root_0, RIGHTSHIFT242_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = operatorType.RShift;
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "shift_op"


	public static class arith_expr_return extends ParserRuleReturnScope {
		public Token lparen = null;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "arith_expr"
	// Python.g:1723:1: arith_expr returns [Token lparen = null] : left= term ( ( arith_op right+= term )+ | -> $left) ;
	public final PythonParser.arith_expr_return arith_expr() throws RecognitionException {
		PythonParser.arith_expr_return retval = new PythonParser.arith_expr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		List<Object> list_right=null;
		ParserRuleReturnScope left =null;
		ParserRuleReturnScope arith_op243 =null;
		RuleReturnScope right = null;
		RewriteRuleSubtreeStream stream_term=new RewriteRuleSubtreeStream(adaptor,"rule term");
		RewriteRuleSubtreeStream stream_arith_op=new RewriteRuleSubtreeStream(adaptor,"rule arith_op");


		    List ops = new ArrayList();
		    List toks = new ArrayList();

		try {
			// Python.g:1739:5: (left= term ( ( arith_op right+= term )+ | -> $left) )
			// Python.g:1739:7: left= term ( ( arith_op right+= term )+ | -> $left)
			{
			pushFollow(FOLLOW_term_in_arith_expr6107);
			left=term();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_term.add(left.getTree());
			// Python.g:1740:9: ( ( arith_op right+= term )+ | -> $left)
			int alt120=2;
			int LA120_0 = input.LA(1);
			if ( (LA120_0==MINUS||LA120_0==PLUS) ) {
				alt120=1;
			}
			else if ( (LA120_0==EOF||(LA120_0 >= ALT_NOTEQUAL && LA120_0 <= AND)||LA120_0==AS||LA120_0==ASSIGN||LA120_0==ATEQUAL||(LA120_0 >= CIRCUMFLEX && LA120_0 <= CIRCUMFLEXEQUAL)||(LA120_0 >= COLON && LA120_0 <= COMMA)||LA120_0==DOUBLESLASHEQUAL||LA120_0==DOUBLESTAREQUAL||LA120_0==EQUAL||(LA120_0 >= FOR && LA120_0 <= FROM)||(LA120_0 >= GREATER && LA120_0 <= IF)||LA120_0==IN||LA120_0==IS||(LA120_0 >= LEFTSHIFT && LA120_0 <= LESSEQUAL)||LA120_0==MINUSEQUAL||LA120_0==NEWLINE||(LA120_0 >= NOT && LA120_0 <= ORELSE)||LA120_0==PERCENTEQUAL||LA120_0==PLUSEQUAL||(LA120_0 >= RBRACK && LA120_0 <= RCURLY)||(LA120_0 >= RIGHTSHIFT && LA120_0 <= SEMI)||LA120_0==SLASHEQUAL||LA120_0==STAREQUAL||(LA120_0 >= VBAR && LA120_0 <= VBAREQUAL)) ) {
				alt120=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 120, 0, input);
				throw nvae;
			}

			switch (alt120) {
				case 1 :
					// Python.g:1740:11: ( arith_op right+= term )+
					{
					// Python.g:1740:11: ( arith_op right+= term )+
					int cnt119=0;
					loop119:
					while (true) {
						int alt119=2;
						int LA119_0 = input.LA(1);
						if ( (LA119_0==MINUS||LA119_0==PLUS) ) {
							alt119=1;
						}

						switch (alt119) {
						case 1 :
							// Python.g:1740:12: arith_op right+= term
							{
							pushFollow(FOLLOW_arith_op_in_arith_expr6120);
							arith_op243=arith_op();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_arith_op.add(arith_op243.getTree());
							pushFollow(FOLLOW_term_in_arith_expr6124);
							right=term();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_term.add(right.getTree());
							if (list_right==null) list_right=new ArrayList<Object>();
							list_right.add(right.getTree());
							if ( state.backtracking==0 ) {
							               ops.add((arith_op243!=null?((PythonParser.arith_op_return)arith_op243).op:null));
							               toks.add((arith_op243!=null?(arith_op243.start):null));
							           }
							}
							break;

						default :
							if ( cnt119 >= 1 ) break loop119;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(119, input);
							throw eee;
						}
						cnt119++;
					}

					}
					break;
				case 2 :
					// Python.g:1747:8: 
					{
					// AST REWRITE
					// elements: left
					// token labels: 
					// rule labels: left, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (PythonTree)adaptor.nil();
					// 1747:8: -> $left
					{
						adaptor.addChild(root_0, stream_left.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (!ops.isEmpty()) {
			        Token tok = (left!=null?(left.start):null);
			        if ((left!=null?((PythonParser.term_return)left).lparen:null) != null) {
			            tok = (left!=null?((PythonParser.term_return)left).lparen:null);
			        }
			        retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.getTree()):null), ops, list_right, toks);
			    }
			    retval.lparen = (left!=null?((PythonParser.term_return)left).lparen:null);
			}
		}
		catch (RewriteCardinalityException rce) {

			        PythonTree badNode = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), null);
			        retval.tree = badNode;
			        errorHandler.error("Internal Parser Error", badNode);
			    
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arith_expr"


	public static class arith_op_return extends ParserRuleReturnScope {
		public operatorType op;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "arith_op"
	// Python.g:1759:1: arith_op returns [operatorType op] : ( PLUS | MINUS );
	public final PythonParser.arith_op_return arith_op() throws RecognitionException {
		PythonParser.arith_op_return retval = new PythonParser.arith_op_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token PLUS244=null;
		Token MINUS245=null;

		PythonTree PLUS244_tree=null;
		PythonTree MINUS245_tree=null;

		try {
			// Python.g:1761:5: ( PLUS | MINUS )
			int alt121=2;
			int LA121_0 = input.LA(1);
			if ( (LA121_0==PLUS) ) {
				alt121=1;
			}
			else if ( (LA121_0==MINUS) ) {
				alt121=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 121, 0, input);
				throw nvae;
			}

			switch (alt121) {
				case 1 :
					// Python.g:1761:7: PLUS
					{
					root_0 = (PythonTree)adaptor.nil();


					PLUS244=(Token)match(input,PLUS,FOLLOW_PLUS_in_arith_op6232); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PLUS244_tree = (PythonTree)adaptor.create(PLUS244);
					adaptor.addChild(root_0, PLUS244_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = operatorType.Add;
					      }
					}
					break;
				case 2 :
					// Python.g:1765:7: MINUS
					{
					root_0 = (PythonTree)adaptor.nil();


					MINUS245=(Token)match(input,MINUS,FOLLOW_MINUS_in_arith_op6248); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					MINUS245_tree = (PythonTree)adaptor.create(MINUS245);
					adaptor.addChild(root_0, MINUS245_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = operatorType.Sub;
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arith_op"


	public static class term_return extends ParserRuleReturnScope {
		public Token lparen = null;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "term"
	// Python.g:1772:1: term returns [Token lparen = null] : left= factor ( ( term_op right+= factor )+ | -> $left) ;
	public final PythonParser.term_return term() throws RecognitionException {
		PythonParser.term_return retval = new PythonParser.term_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		List<Object> list_right=null;
		ParserRuleReturnScope left =null;
		ParserRuleReturnScope term_op246 =null;
		RuleReturnScope right = null;
		RewriteRuleSubtreeStream stream_term_op=new RewriteRuleSubtreeStream(adaptor,"rule term_op");
		RewriteRuleSubtreeStream stream_factor=new RewriteRuleSubtreeStream(adaptor,"rule factor");


		    List ops = new ArrayList();
		    List toks = new ArrayList();

		try {
			// Python.g:1788:5: (left= factor ( ( term_op right+= factor )+ | -> $left) )
			// Python.g:1788:7: left= factor ( ( term_op right+= factor )+ | -> $left)
			{
			pushFollow(FOLLOW_factor_in_term6294);
			left=factor();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) stream_factor.add(left.getTree());
			// Python.g:1789:9: ( ( term_op right+= factor )+ | -> $left)
			int alt123=2;
			int LA123_0 = input.LA(1);
			if ( (LA123_0==AT||LA123_0==DOUBLESLASH||LA123_0==PERCENT||LA123_0==SLASH||LA123_0==STAR) ) {
				alt123=1;
			}
			else if ( (LA123_0==EOF||(LA123_0 >= ALT_NOTEQUAL && LA123_0 <= AND)||LA123_0==AS||LA123_0==ASSIGN||LA123_0==ATEQUAL||(LA123_0 >= CIRCUMFLEX && LA123_0 <= CIRCUMFLEXEQUAL)||(LA123_0 >= COLON && LA123_0 <= COMMA)||LA123_0==DOUBLESLASHEQUAL||LA123_0==DOUBLESTAREQUAL||LA123_0==EQUAL||(LA123_0 >= FOR && LA123_0 <= FROM)||(LA123_0 >= GREATER && LA123_0 <= IF)||LA123_0==IN||LA123_0==IS||(LA123_0 >= LEFTSHIFT && LA123_0 <= LESSEQUAL)||(LA123_0 >= MINUS && LA123_0 <= MINUSEQUAL)||LA123_0==NEWLINE||(LA123_0 >= NOT && LA123_0 <= ORELSE)||(LA123_0 >= PERCENTEQUAL && LA123_0 <= PLUSEQUAL)||(LA123_0 >= RBRACK && LA123_0 <= RCURLY)||(LA123_0 >= RIGHTSHIFT && LA123_0 <= SEMI)||LA123_0==SLASHEQUAL||LA123_0==STAREQUAL||(LA123_0 >= VBAR && LA123_0 <= VBAREQUAL)) ) {
				alt123=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 123, 0, input);
				throw nvae;
			}

			switch (alt123) {
				case 1 :
					// Python.g:1789:11: ( term_op right+= factor )+
					{
					// Python.g:1789:11: ( term_op right+= factor )+
					int cnt122=0;
					loop122:
					while (true) {
						int alt122=2;
						int LA122_0 = input.LA(1);
						if ( (LA122_0==AT||LA122_0==DOUBLESLASH||LA122_0==PERCENT||LA122_0==SLASH||LA122_0==STAR) ) {
							alt122=1;
						}

						switch (alt122) {
						case 1 :
							// Python.g:1789:12: term_op right+= factor
							{
							pushFollow(FOLLOW_term_op_in_term6307);
							term_op246=term_op();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_term_op.add(term_op246.getTree());
							pushFollow(FOLLOW_factor_in_term6311);
							right=factor();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_factor.add(right.getTree());
							if (list_right==null) list_right=new ArrayList<Object>();
							list_right.add(right.getTree());
							if ( state.backtracking==0 ) {
							              ops.add((term_op246!=null?((PythonParser.term_op_return)term_op246).op:null));
							              toks.add((term_op246!=null?(term_op246.start):null));
							          }
							}
							break;

						default :
							if ( cnt122 >= 1 ) break loop122;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(122, input);
							throw eee;
						}
						cnt122++;
					}

					}
					break;
				case 2 :
					// Python.g:1796:8: 
					{
					// AST REWRITE
					// elements: left
					// token labels: 
					// rule labels: left, retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_left=new RewriteRuleSubtreeStream(adaptor,"rule left",left!=null?left.getTree():null);
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (PythonTree)adaptor.nil();
					// 1796:8: -> $left
					{
						adaptor.addChild(root_0, stream_left.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.lparen = (left!=null?((PythonParser.factor_return)left).lparen:null);
			    if (!ops.isEmpty()) {
			        Token tok = (left!=null?(left.start):null);
			        if ((left!=null?((PythonParser.factor_return)left).lparen:null) != null) {
			            tok = (left!=null?((PythonParser.factor_return)left).lparen:null);
			        }
			        retval.tree = actions.makeBinOp(tok, (left!=null?((PythonTree)left.getTree()):null), ops, list_right, toks);
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "term"


	public static class term_op_return extends ParserRuleReturnScope {
		public operatorType op;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "term_op"
	// Python.g:1800:1: term_op returns [operatorType op] : ( STAR | AT | SLASH | PERCENT | DOUBLESLASH );
	public final PythonParser.term_op_return term_op() throws RecognitionException {
		PythonParser.term_op_return retval = new PythonParser.term_op_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token STAR247=null;
		Token AT248=null;
		Token SLASH249=null;
		Token PERCENT250=null;
		Token DOUBLESLASH251=null;

		PythonTree STAR247_tree=null;
		PythonTree AT248_tree=null;
		PythonTree SLASH249_tree=null;
		PythonTree PERCENT250_tree=null;
		PythonTree DOUBLESLASH251_tree=null;

		try {
			// Python.g:1802:5: ( STAR | AT | SLASH | PERCENT | DOUBLESLASH )
			int alt124=5;
			switch ( input.LA(1) ) {
			case STAR:
				{
				alt124=1;
				}
				break;
			case AT:
				{
				alt124=2;
				}
				break;
			case SLASH:
				{
				alt124=3;
				}
				break;
			case PERCENT:
				{
				alt124=4;
				}
				break;
			case DOUBLESLASH:
				{
				alt124=5;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 124, 0, input);
				throw nvae;
			}
			switch (alt124) {
				case 1 :
					// Python.g:1802:7: STAR
					{
					root_0 = (PythonTree)adaptor.nil();


					STAR247=(Token)match(input,STAR,FOLLOW_STAR_in_term_op6393); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STAR247_tree = (PythonTree)adaptor.create(STAR247);
					adaptor.addChild(root_0, STAR247_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = operatorType.Mult;
					      }
					}
					break;
				case 2 :
					// Python.g:1806:7: AT
					{
					root_0 = (PythonTree)adaptor.nil();


					AT248=(Token)match(input,AT,FOLLOW_AT_in_term_op6409); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AT248_tree = (PythonTree)adaptor.create(AT248);
					adaptor.addChild(root_0, AT248_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = operatorType.MatMult;
					      }
					}
					break;
				case 3 :
					// Python.g:1810:7: SLASH
					{
					root_0 = (PythonTree)adaptor.nil();


					SLASH249=(Token)match(input,SLASH,FOLLOW_SLASH_in_term_op6425); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					SLASH249_tree = (PythonTree)adaptor.create(SLASH249);
					adaptor.addChild(root_0, SLASH249_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = operatorType.Div;
					      }
					}
					break;
				case 4 :
					// Python.g:1814:7: PERCENT
					{
					root_0 = (PythonTree)adaptor.nil();


					PERCENT250=(Token)match(input,PERCENT,FOLLOW_PERCENT_in_term_op6441); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PERCENT250_tree = (PythonTree)adaptor.create(PERCENT250);
					adaptor.addChild(root_0, PERCENT250_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = operatorType.Mod;
					      }
					}
					break;
				case 5 :
					// Python.g:1818:7: DOUBLESLASH
					{
					root_0 = (PythonTree)adaptor.nil();


					DOUBLESLASH251=(Token)match(input,DOUBLESLASH,FOLLOW_DOUBLESLASH_in_term_op6457); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOUBLESLASH251_tree = (PythonTree)adaptor.create(DOUBLESLASH251);
					adaptor.addChild(root_0, DOUBLESLASH251_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.op = operatorType.FloorDiv;
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "term_op"


	public static class factor_return extends ParserRuleReturnScope {
		public expr etype;
		public Token lparen = null;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "factor"
	// Python.g:1825:1: factor returns [expr etype, Token lparen = null] : ( PLUS p= factor | MINUS m= factor | TILDE t= factor | power );
	public final PythonParser.factor_return factor() throws RecognitionException {
		PythonParser.factor_return retval = new PythonParser.factor_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token PLUS252=null;
		Token MINUS253=null;
		Token TILDE254=null;
		ParserRuleReturnScope p =null;
		ParserRuleReturnScope m =null;
		ParserRuleReturnScope t =null;
		ParserRuleReturnScope power255 =null;

		PythonTree PLUS252_tree=null;
		PythonTree MINUS253_tree=null;
		PythonTree TILDE254_tree=null;

		try {
			// Python.g:1830:5: ( PLUS p= factor | MINUS m= factor | TILDE t= factor | power )
			int alt125=4;
			switch ( input.LA(1) ) {
			case PLUS:
				{
				alt125=1;
				}
				break;
			case MINUS:
				{
				alt125=2;
				}
				break;
			case TILDE:
				{
				alt125=3;
				}
				break;
			case AWAIT:
			case COMPLEX:
			case DOLLER:
			case DOT:
			case FLOAT:
			case LBRACK:
			case LCURLY:
			case LONGINT:
			case LPAREN:
			case NAME:
			case STRING:
				{
				alt125=4;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 125, 0, input);
				throw nvae;
			}
			switch (alt125) {
				case 1 :
					// Python.g:1830:7: PLUS p= factor
					{
					root_0 = (PythonTree)adaptor.nil();


					PLUS252=(Token)match(input,PLUS,FOLLOW_PLUS_in_factor6496); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					PLUS252_tree = (PythonTree)adaptor.create(PLUS252);
					adaptor.addChild(root_0, PLUS252_tree);
					}

					pushFollow(FOLLOW_factor_in_factor6500);
					p=factor();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, p.getTree());

					if ( state.backtracking==0 ) {
					          retval.etype = new UnaryOp(PLUS252, unaryopType.UAdd, (p!=null?((PythonParser.factor_return)p).etype:null));
					      }
					}
					break;
				case 2 :
					// Python.g:1834:7: MINUS m= factor
					{
					root_0 = (PythonTree)adaptor.nil();


					MINUS253=(Token)match(input,MINUS,FOLLOW_MINUS_in_factor6516); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					MINUS253_tree = (PythonTree)adaptor.create(MINUS253);
					adaptor.addChild(root_0, MINUS253_tree);
					}

					pushFollow(FOLLOW_factor_in_factor6520);
					m=factor();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, m.getTree());

					if ( state.backtracking==0 ) {
					          retval.etype = actions.negate(MINUS253, (m!=null?((PythonParser.factor_return)m).etype:null));
					      }
					}
					break;
				case 3 :
					// Python.g:1838:7: TILDE t= factor
					{
					root_0 = (PythonTree)adaptor.nil();


					TILDE254=(Token)match(input,TILDE,FOLLOW_TILDE_in_factor6536); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					TILDE254_tree = (PythonTree)adaptor.create(TILDE254);
					adaptor.addChild(root_0, TILDE254_tree);
					}

					pushFollow(FOLLOW_factor_in_factor6540);
					t=factor();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

					if ( state.backtracking==0 ) {
					          retval.etype = new UnaryOp(TILDE254, unaryopType.Invert, (t!=null?((PythonParser.factor_return)t).etype:null));
					      }
					}
					break;
				case 4 :
					// Python.g:1842:7: power
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_power_in_factor6556);
					power255=power();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, power255.getTree());

					if ( state.backtracking==0 ) {
					          retval.etype = actions.castExpr((power255!=null?((PythonTree)power255.getTree()):null));
					          retval.lparen = (power255!=null?((PythonParser.power_return)power255).lparen:null);
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = retval.etype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "factor"


	public static class power_return extends ParserRuleReturnScope {
		public expr etype;
		public Token lparen = null;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "power"
	// Python.g:1850:1: power returns [expr etype, Token lparen = null] : atom_expr ( options {greedy=true; } :d= DOUBLESTAR factor )? ;
	public final PythonParser.power_return power() throws RecognitionException {
		PythonParser.power_return retval = new PythonParser.power_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token d=null;
		ParserRuleReturnScope atom_expr256 =null;
		ParserRuleReturnScope factor257 =null;

		PythonTree d_tree=null;

		try {
			// Python.g:1855:5: ( atom_expr ( options {greedy=true; } :d= DOUBLESTAR factor )? )
			// Python.g:1855:7: atom_expr ( options {greedy=true; } :d= DOUBLESTAR factor )?
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_atom_expr_in_power6595);
			atom_expr256=atom_expr();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, atom_expr256.getTree());

			// Python.g:1855:17: ( options {greedy=true; } :d= DOUBLESTAR factor )?
			int alt126=2;
			int LA126_0 = input.LA(1);
			if ( (LA126_0==DOUBLESTAR) ) {
				alt126=1;
			}
			switch (alt126) {
				case 1 :
					// Python.g:1855:41: d= DOUBLESTAR factor
					{
					d=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_power6607); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					d_tree = (PythonTree)adaptor.create(d);
					adaptor.addChild(root_0, d_tree);
					}

					pushFollow(FOLLOW_factor_in_power6609);
					factor257=factor();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, factor257.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          retval.lparen = (atom_expr256!=null?((PythonParser.atom_expr_return)atom_expr256).lparen:null);
			          retval.etype = actions.castExpr((atom_expr256!=null?((PythonTree)atom_expr256.getTree()):null));
			          if (d != null) {
			              List right = new ArrayList();
			              right.add((factor257!=null?((PythonTree)factor257.getTree()):null));
			              retval.etype = actions.makeBinOp((atom_expr256!=null?(atom_expr256.start):null), retval.etype, operatorType.Pow, right);
			          }
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = retval.etype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "power"


	public static class atom_expr_return extends ParserRuleReturnScope {
		public expr etype;
		public Token lparen = null;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "atom_expr"
	// Python.g:1868:1: atom_expr returns [expr etype, Token lparen = null] : ( AWAIT atom (t+= trailer[$atom.start, $atom.tree] )* | atom (t+= trailer[$atom.start, $atom.tree] )* | AWAIT );
	public final PythonParser.atom_expr_return atom_expr() throws RecognitionException {
		PythonParser.atom_expr_return retval = new PythonParser.atom_expr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token AWAIT258=null;
		Token AWAIT261=null;
		List<Object> list_t=null;
		ParserRuleReturnScope atom259 =null;
		ParserRuleReturnScope atom260 =null;
		RuleReturnScope t = null;
		PythonTree AWAIT258_tree=null;
		PythonTree AWAIT261_tree=null;

		try {
			// Python.g:1873:5: ( AWAIT atom (t+= trailer[$atom.start, $atom.tree] )* | atom (t+= trailer[$atom.start, $atom.tree] )* | AWAIT )
			int alt129=3;
			int LA129_0 = input.LA(1);
			if ( (LA129_0==AWAIT) ) {
				int LA129_1 = input.LA(2);
				if ( (LA129_1==COMPLEX||(LA129_1 >= DOLLER && LA129_1 <= DOT)||LA129_1==FLOAT||(LA129_1 >= LBRACK && LA129_1 <= LCURLY)||(LA129_1 >= LONGINT && LA129_1 <= LPAREN)||LA129_1==NAME||LA129_1==STRING) ) {
					alt129=1;
				}
				else if ( (LA129_1==EOF||(LA129_1 >= ALT_NOTEQUAL && LA129_1 <= AND)||LA129_1==AS||LA129_1==ASSIGN||(LA129_1 >= AT && LA129_1 <= ATEQUAL)||(LA129_1 >= CIRCUMFLEX && LA129_1 <= CIRCUMFLEXEQUAL)||(LA129_1 >= COLON && LA129_1 <= COMMA)||(LA129_1 >= DOUBLESLASH && LA129_1 <= DOUBLESTAREQUAL)||LA129_1==EQUAL||(LA129_1 >= FOR && LA129_1 <= FROM)||(LA129_1 >= GREATER && LA129_1 <= IF)||LA129_1==IN||LA129_1==IS||(LA129_1 >= LEFTSHIFT && LA129_1 <= LESSEQUAL)||(LA129_1 >= MINUS && LA129_1 <= MINUSEQUAL)||LA129_1==NEWLINE||(LA129_1 >= NOT && LA129_1 <= ORELSE)||(LA129_1 >= PERCENT && LA129_1 <= PLUSEQUAL)||(LA129_1 >= RBRACK && LA129_1 <= RCURLY)||(LA129_1 >= RIGHTSHIFT && LA129_1 <= STAREQUAL)||(LA129_1 >= VBAR && LA129_1 <= VBAREQUAL)) ) {
					alt129=3;
				}

				else {
					if (state.backtracking>0) {state.failed=true; return retval;}
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 129, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA129_0==COMPLEX||(LA129_0 >= DOLLER && LA129_0 <= DOT)||LA129_0==FLOAT||(LA129_0 >= LBRACK && LA129_0 <= LCURLY)||(LA129_0 >= LONGINT && LA129_0 <= LPAREN)||LA129_0==NAME||LA129_0==STRING) ) {
				alt129=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 129, 0, input);
				throw nvae;
			}

			switch (alt129) {
				case 1 :
					// Python.g:1873:7: AWAIT atom (t+= trailer[$atom.start, $atom.tree] )*
					{
					root_0 = (PythonTree)adaptor.nil();


					AWAIT258=(Token)match(input,AWAIT,FOLLOW_AWAIT_in_atom_expr6650); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AWAIT258_tree = (PythonTree)adaptor.create(AWAIT258);
					adaptor.addChild(root_0, AWAIT258_tree);
					}

					pushFollow(FOLLOW_atom_in_atom_expr6652);
					atom259=atom();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atom259.getTree());

					// Python.g:1873:18: (t+= trailer[$atom.start, $atom.tree] )*
					loop127:
					while (true) {
						int alt127=2;
						int LA127_0 = input.LA(1);
						if ( (LA127_0==DOT||LA127_0==LBRACK||LA127_0==LPAREN) ) {
							alt127=1;
						}

						switch (alt127) {
						case 1 :
							// Python.g:1873:19: t+= trailer[$atom.start, $atom.tree]
							{
							pushFollow(FOLLOW_trailer_in_atom_expr6657);
							t=trailer((atom259!=null?(atom259.start):null), (atom259!=null?((PythonTree)atom259.getTree()):null));
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

							if (list_t==null) list_t=new ArrayList<Object>();
							list_t.add(t.getTree());
							}
							break;

						default :
							break loop127;
						}
					}

					if ( state.backtracking==0 ) {
					          retval.lparen = (atom259!=null?((PythonParser.atom_return)atom259).lparen:null);
					          //XXX: This could be better.
					          retval.etype = actions.castExpr((atom259!=null?((PythonTree)atom259.getTree()):null));
					          if (list_t != null) {
					              for(Object o : list_t) {
					                  actions.recurseSetContext(retval.etype, expr_contextType.Load);
					                  if (o instanceof Call) {
					                      Call c = (Call)o;
					                      c.setCharStartIndex(retval.etype.getCharStartIndex());
					                      c.setFunc((PyObject)retval.etype);
					                      retval.etype = c;
					                  } else if (o instanceof Subscript) {
					                      Subscript c = (Subscript)o;
					                      c.setCharStartIndex(retval.etype.getCharStartIndex());
					                      c.setValue((PyObject)retval.etype);
					                      retval.etype = c;
					                  } else if (o instanceof Attribute) {
					                      Attribute c = (Attribute)o;
					                      c.setCharStartIndex(retval.etype.getCharStartIndex());
					                      c.setValue((PyObject)retval.etype);
					                      retval.etype = c;
					                  }
					              }
					          }
					          retval.etype = new Await(AWAIT258, retval.etype);
					      }
					}
					break;
				case 2 :
					// Python.g:1901:7: atom (t+= trailer[$atom.start, $atom.tree] )*
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_atom_in_atom_expr6676);
					atom260=atom();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, atom260.getTree());

					// Python.g:1901:12: (t+= trailer[$atom.start, $atom.tree] )*
					loop128:
					while (true) {
						int alt128=2;
						int LA128_0 = input.LA(1);
						if ( (LA128_0==DOT||LA128_0==LBRACK||LA128_0==LPAREN) ) {
							alt128=1;
						}

						switch (alt128) {
						case 1 :
							// Python.g:1901:13: t+= trailer[$atom.start, $atom.tree]
							{
							pushFollow(FOLLOW_trailer_in_atom_expr6681);
							t=trailer((atom260!=null?(atom260.start):null), (atom260!=null?((PythonTree)atom260.getTree()):null));
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

							if (list_t==null) list_t=new ArrayList<Object>();
							list_t.add(t.getTree());
							}
							break;

						default :
							break loop128;
						}
					}

					if ( state.backtracking==0 ) {
					          retval.lparen = (atom260!=null?((PythonParser.atom_return)atom260).lparen:null);
					          //XXX: This could be better.
					          retval.etype = actions.castExpr((atom260!=null?((PythonTree)atom260.getTree()):null));
					          if (list_t != null) {
					              for(Object o : list_t) {
					                  actions.recurseSetContext(retval.etype, expr_contextType.Load);
					                  if (o instanceof Call) {
					                      Call c = (Call)o;
					                      c.setFunc((PyObject)retval.etype);
					                      retval.etype = c;
					                  } else if (o instanceof Subscript) {
					                      Subscript c = (Subscript)o;
					                      c.setValue((PyObject)retval.etype);
					                      retval.etype = c;
					                  } else if (o instanceof Attribute) {
					                      Attribute c = (Attribute)o;
					                      c.setCharStartIndex(retval.etype.getCharStartIndex());
					                      c.setValue((PyObject)retval.etype);
					                      retval.etype = c;
					                  }
					              }
					          }
					      }
					}
					break;
				case 3 :
					// Python.g:1927:7: AWAIT
					{
					root_0 = (PythonTree)adaptor.nil();


					AWAIT261=(Token)match(input,AWAIT,FOLLOW_AWAIT_in_atom_expr6705); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					AWAIT261_tree = (PythonTree)adaptor.create(AWAIT261);
					adaptor.addChild(root_0, AWAIT261_tree);
					}

					if ( state.backtracking==0 ) {
					          retval.etype = new Name(AWAIT261, (AWAIT261!=null?AWAIT261.getText():null), expr_contextType.Load);
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = retval.etype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom_expr"


	public static class atom_return extends ParserRuleReturnScope {
		public Token lparen = null;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "atom"
	// Python.g:1938:1: atom returns [Token lparen = null] : ( LPAREN ( yield_expr | testlist_comp[$LPAREN] -> testlist_comp |) RPAREN | LBRACK ( testlist_comp[$LBRACK] -> testlist_comp |) RBRACK | LCURLY ( dictorsetmaker[$LCURLY] -> dictorsetmaker |) RCURLY | NAME | LONGINT | FLOAT | COMPLEX |d= DOT DOT DOT | DOLLER INT | (S+= STRING )+ );
	public final PythonParser.atom_return atom() throws RecognitionException {
		PythonParser.atom_return retval = new PythonParser.atom_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token d=null;
		Token LPAREN262=null;
		Token RPAREN265=null;
		Token LBRACK266=null;
		Token RBRACK268=null;
		Token LCURLY269=null;
		Token RCURLY271=null;
		Token NAME272=null;
		Token LONGINT273=null;
		Token FLOAT274=null;
		Token COMPLEX275=null;
		Token DOT276=null;
		Token DOT277=null;
		Token DOLLER278=null;
		Token INT279=null;
		Token S=null;
		List<Object> list_S=null;
		ParserRuleReturnScope yield_expr263 =null;
		ParserRuleReturnScope testlist_comp264 =null;
		ParserRuleReturnScope testlist_comp267 =null;
		ParserRuleReturnScope dictorsetmaker270 =null;

		PythonTree d_tree=null;
		PythonTree LPAREN262_tree=null;
		PythonTree RPAREN265_tree=null;
		PythonTree LBRACK266_tree=null;
		PythonTree RBRACK268_tree=null;
		PythonTree LCURLY269_tree=null;
		PythonTree RCURLY271_tree=null;
		PythonTree NAME272_tree=null;
		PythonTree LONGINT273_tree=null;
		PythonTree FLOAT274_tree=null;
		PythonTree COMPLEX275_tree=null;
		PythonTree DOT276_tree=null;
		PythonTree DOT277_tree=null;
		PythonTree DOLLER278_tree=null;
		PythonTree INT279_tree=null;
		PythonTree S_tree=null;
		RewriteRuleTokenStream stream_RBRACK=new RewriteRuleTokenStream(adaptor,"token RBRACK");
		RewriteRuleTokenStream stream_LBRACK=new RewriteRuleTokenStream(adaptor,"token LBRACK");
		RewriteRuleTokenStream stream_LPAREN=new RewriteRuleTokenStream(adaptor,"token LPAREN");
		RewriteRuleTokenStream stream_LCURLY=new RewriteRuleTokenStream(adaptor,"token LCURLY");
		RewriteRuleTokenStream stream_RPAREN=new RewriteRuleTokenStream(adaptor,"token RPAREN");
		RewriteRuleTokenStream stream_RCURLY=new RewriteRuleTokenStream(adaptor,"token RCURLY");
		RewriteRuleSubtreeStream stream_testlist_comp=new RewriteRuleSubtreeStream(adaptor,"rule testlist_comp");
		RewriteRuleSubtreeStream stream_yield_expr=new RewriteRuleSubtreeStream(adaptor,"rule yield_expr");
		RewriteRuleSubtreeStream stream_dictorsetmaker=new RewriteRuleSubtreeStream(adaptor,"rule dictorsetmaker");


		    expr etype = null;

		try {
			// Python.g:1948:5: ( LPAREN ( yield_expr | testlist_comp[$LPAREN] -> testlist_comp |) RPAREN | LBRACK ( testlist_comp[$LBRACK] -> testlist_comp |) RBRACK | LCURLY ( dictorsetmaker[$LCURLY] -> dictorsetmaker |) RCURLY | NAME | LONGINT | FLOAT | COMPLEX |d= DOT DOT DOT | DOLLER INT | (S+= STRING )+ )
			int alt134=10;
			switch ( input.LA(1) ) {
			case LPAREN:
				{
				alt134=1;
				}
				break;
			case LBRACK:
				{
				alt134=2;
				}
				break;
			case LCURLY:
				{
				alt134=3;
				}
				break;
			case NAME:
				{
				alt134=4;
				}
				break;
			case LONGINT:
				{
				alt134=5;
				}
				break;
			case FLOAT:
				{
				alt134=6;
				}
				break;
			case COMPLEX:
				{
				alt134=7;
				}
				break;
			case DOT:
				{
				alt134=8;
				}
				break;
			case DOLLER:
				{
				alt134=9;
				}
				break;
			case STRING:
				{
				alt134=10;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 134, 0, input);
				throw nvae;
			}
			switch (alt134) {
				case 1 :
					// Python.g:1948:7: LPAREN ( yield_expr | testlist_comp[$LPAREN] -> testlist_comp |) RPAREN
					{
					LPAREN262=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_atom6753); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LPAREN.add(LPAREN262);

					if ( state.backtracking==0 ) {
					          retval.lparen = LPAREN262;
					      }
					// Python.g:1952:7: ( yield_expr | testlist_comp[$LPAREN] -> testlist_comp |)
					int alt130=3;
					switch ( input.LA(1) ) {
					case YIELD:
						{
						alt130=1;
						}
						break;
					case AWAIT:
					case COMPLEX:
					case DOLLER:
					case DOT:
					case FLOAT:
					case LAMBDA:
					case LBRACK:
					case LCURLY:
					case LONGINT:
					case LPAREN:
					case MINUS:
					case NAME:
					case NOT:
					case PLUS:
					case STAR:
					case STRING:
					case TILDE:
						{
						alt130=2;
						}
						break;
					case RPAREN:
						{
						alt130=3;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 130, 0, input);
						throw nvae;
					}
					switch (alt130) {
						case 1 :
							// Python.g:1952:9: yield_expr
							{
							pushFollow(FOLLOW_yield_expr_in_atom6771);
							yield_expr263=yield_expr();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_yield_expr.add(yield_expr263.getTree());
							if ( state.backtracking==0 ) {
							            etype = (yield_expr263!=null?((PythonParser.yield_expr_return)yield_expr263).etype:null);
							        }
							}
							break;
						case 2 :
							// Python.g:1956:9: testlist_comp[$LPAREN]
							{
							pushFollow(FOLLOW_testlist_comp_in_atom6791);
							testlist_comp264=testlist_comp(LPAREN262);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_testlist_comp.add(testlist_comp264.getTree());
							// AST REWRITE
							// elements: testlist_comp
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							if ( state.backtracking==0 ) {
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (PythonTree)adaptor.nil();
							// 1957:6: -> testlist_comp
							{
								adaptor.addChild(root_0, stream_testlist_comp.nextTree());
							}


							retval.tree = root_0;
							}

							}
							break;
						case 3 :
							// Python.g:1959:9: 
							{
							if ( state.backtracking==0 ) {
							            etype = new Tuple(LPAREN262, new ArrayList<expr>(), expr_stack.peek().ctype);
							        }
							}
							break;

					}

					RPAREN265=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_atom6835); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RPAREN.add(RPAREN265);

					}
					break;
				case 2 :
					// Python.g:1964:7: LBRACK ( testlist_comp[$LBRACK] -> testlist_comp |) RBRACK
					{
					LBRACK266=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_atom6843); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LBRACK.add(LBRACK266);

					// Python.g:1965:7: ( testlist_comp[$LBRACK] -> testlist_comp |)
					int alt131=2;
					int LA131_0 = input.LA(1);
					if ( (LA131_0==AWAIT||LA131_0==COMPLEX||(LA131_0 >= DOLLER && LA131_0 <= DOT)||LA131_0==FLOAT||(LA131_0 >= LAMBDA && LA131_0 <= LCURLY)||(LA131_0 >= LONGINT && LA131_0 <= MINUS)||LA131_0==NAME||LA131_0==NOT||LA131_0==PLUS||LA131_0==STAR||(LA131_0 >= STRING && LA131_0 <= TILDE)) ) {
						alt131=1;
					}
					else if ( (LA131_0==RBRACK) ) {
						alt131=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 131, 0, input);
						throw nvae;
					}

					switch (alt131) {
						case 1 :
							// Python.g:1965:8: testlist_comp[$LBRACK]
							{
							pushFollow(FOLLOW_testlist_comp_in_atom6852);
							testlist_comp267=testlist_comp(LBRACK266);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_testlist_comp.add(testlist_comp267.getTree());
							// AST REWRITE
							// elements: testlist_comp
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							if ( state.backtracking==0 ) {
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (PythonTree)adaptor.nil();
							// 1966:6: -> testlist_comp
							{
								adaptor.addChild(root_0, stream_testlist_comp.nextTree());
							}


							retval.tree = root_0;
							}

							}
							break;
						case 2 :
							// Python.g:1968:8: 
							{
							if ( state.backtracking==0 ) {
							           etype = new org.python.antlr.ast.List(LBRACK266, new ArrayList<expr>(), expr_stack.peek().ctype);
							       }
							}
							break;

					}

					RBRACK268=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_atom6895); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RBRACK.add(RBRACK268);

					}
					break;
				case 3 :
					// Python.g:1973:7: LCURLY ( dictorsetmaker[$LCURLY] -> dictorsetmaker |) RCURLY
					{
					LCURLY269=(Token)match(input,LCURLY,FOLLOW_LCURLY_in_atom6903); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_LCURLY.add(LCURLY269);

					// Python.g:1974:8: ( dictorsetmaker[$LCURLY] -> dictorsetmaker |)
					int alt132=2;
					int LA132_0 = input.LA(1);
					if ( (LA132_0==AWAIT||LA132_0==COMPLEX||(LA132_0 >= DOLLER && LA132_0 <= DOT)||LA132_0==DOUBLESTAR||LA132_0==FLOAT||(LA132_0 >= LAMBDA && LA132_0 <= LCURLY)||(LA132_0 >= LONGINT && LA132_0 <= MINUS)||LA132_0==NAME||LA132_0==NOT||LA132_0==PLUS||LA132_0==STAR||(LA132_0 >= STRING && LA132_0 <= TILDE)) ) {
						alt132=1;
					}
					else if ( (LA132_0==RCURLY) ) {
						alt132=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 132, 0, input);
						throw nvae;
					}

					switch (alt132) {
						case 1 :
							// Python.g:1974:9: dictorsetmaker[$LCURLY]
							{
							pushFollow(FOLLOW_dictorsetmaker_in_atom6913);
							dictorsetmaker270=dictorsetmaker(LCURLY269);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) stream_dictorsetmaker.add(dictorsetmaker270.getTree());
							// AST REWRITE
							// elements: dictorsetmaker
							// token labels: 
							// rule labels: retval
							// token list labels: 
							// rule list labels: 
							// wildcard labels: 
							if ( state.backtracking==0 ) {
							retval.tree = root_0;
							RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

							root_0 = (PythonTree)adaptor.nil();
							// 1975:7: -> dictorsetmaker
							{
								adaptor.addChild(root_0, stream_dictorsetmaker.nextTree());
							}


							retval.tree = root_0;
							}

							}
							break;
						case 2 :
							// Python.g:1977:9: 
							{
							if ( state.backtracking==0 ) {
							            etype = new Dict(LCURLY269, new ArrayList<expr>(), new ArrayList<expr>());
							        }
							}
							break;

					}

					RCURLY271=(Token)match(input,RCURLY,FOLLOW_RCURLY_in_atom6961); if (state.failed) return retval; 
					if ( state.backtracking==0 ) stream_RCURLY.add(RCURLY271);

					}
					break;
				case 4 :
					// Python.g:1982:8: NAME
					{
					root_0 = (PythonTree)adaptor.nil();


					NAME272=(Token)match(input,NAME,FOLLOW_NAME_in_atom6970); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					NAME272_tree = (PythonTree)adaptor.create(NAME272);
					adaptor.addChild(root_0, NAME272_tree);
					}

					if ( state.backtracking==0 ) {
					            etype = new Name(NAME272, (NAME272!=null?NAME272.getText():null), expr_stack.peek().ctype);
					       }
					}
					break;
				case 5 :
					// Python.g:1986:8: LONGINT
					{
					root_0 = (PythonTree)adaptor.nil();


					LONGINT273=(Token)match(input,LONGINT,FOLLOW_LONGINT_in_atom6988); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LONGINT273_tree = (PythonTree)adaptor.create(LONGINT273);
					adaptor.addChild(root_0, LONGINT273_tree);
					}

					if ( state.backtracking==0 ) {
					           etype = new Num(LONGINT273, actions.makeInt(LONGINT273));
					       }
					}
					break;
				case 6 :
					// Python.g:1990:8: FLOAT
					{
					root_0 = (PythonTree)adaptor.nil();


					FLOAT274=(Token)match(input,FLOAT,FOLLOW_FLOAT_in_atom7006); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FLOAT274_tree = (PythonTree)adaptor.create(FLOAT274);
					adaptor.addChild(root_0, FLOAT274_tree);
					}

					if ( state.backtracking==0 ) {
					           etype = new Num(FLOAT274, actions.makeFloat(FLOAT274));
					       }
					}
					break;
				case 7 :
					// Python.g:1994:8: COMPLEX
					{
					root_0 = (PythonTree)adaptor.nil();


					COMPLEX275=(Token)match(input,COMPLEX,FOLLOW_COMPLEX_in_atom7024); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMPLEX275_tree = (PythonTree)adaptor.create(COMPLEX275);
					adaptor.addChild(root_0, COMPLEX275_tree);
					}

					if ( state.backtracking==0 ) {
					           etype = new Num(COMPLEX275, actions.makeComplex(COMPLEX275));
					       }
					}
					break;
				case 8 :
					// Python.g:1998:8: d= DOT DOT DOT
					{
					root_0 = (PythonTree)adaptor.nil();


					d=(Token)match(input,DOT,FOLLOW_DOT_in_atom7044); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					d_tree = (PythonTree)adaptor.create(d);
					adaptor.addChild(root_0, d_tree);
					}

					DOT276=(Token)match(input,DOT,FOLLOW_DOT_in_atom7046); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOT276_tree = (PythonTree)adaptor.create(DOT276);
					adaptor.addChild(root_0, DOT276_tree);
					}

					DOT277=(Token)match(input,DOT,FOLLOW_DOT_in_atom7048); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOT277_tree = (PythonTree)adaptor.create(DOT277);
					adaptor.addChild(root_0, DOT277_tree);
					}

					if ( state.backtracking==0 ) {
					          etype = new Ellipsis(d);
					       }
					}
					break;
				case 9 :
					// Python.g:2002:8: DOLLER INT
					{
					root_0 = (PythonTree)adaptor.nil();


					DOLLER278=(Token)match(input,DOLLER,FOLLOW_DOLLER_in_atom7066); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOLLER278_tree = (PythonTree)adaptor.create(DOLLER278);
					adaptor.addChild(root_0, DOLLER278_tree);
					}

					INT279=(Token)match(input,INT,FOLLOW_INT_in_atom7068); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					INT279_tree = (PythonTree)adaptor.create(INT279);
					adaptor.addChild(root_0, INT279_tree);
					}

					if ( state.backtracking==0 ) {
					          etype = new Hole(INT279, actions.makeInt(INT279));
					       }
					}
					break;
				case 10 :
					// Python.g:2006:8: (S+= STRING )+
					{
					root_0 = (PythonTree)adaptor.nil();


					// Python.g:2006:8: (S+= STRING )+
					int cnt133=0;
					loop133:
					while (true) {
						int alt133=2;
						int LA133_0 = input.LA(1);
						if ( (LA133_0==STRING) ) {
							alt133=1;
						}

						switch (alt133) {
						case 1 :
							// Python.g:2006:9: S+= STRING
							{
							S=(Token)match(input,STRING,FOLLOW_STRING_in_atom7089); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							S_tree = (PythonTree)adaptor.create(S);
							adaptor.addChild(root_0, S_tree);
							}

							if (list_S==null) list_S=new ArrayList<Object>();
							list_S.add(S);
							}
							break;

						default :
							if ( cnt133 >= 1 ) break loop133;
							if (state.backtracking>0) {state.failed=true; return retval;}
							EarlyExitException eee = new EarlyExitException(133, input);
							throw eee;
						}
						cnt133++;
					}

					if ( state.backtracking==0 ) {
					           Object str = actions.extractStrings(list_S, encoding, unicodeLiterals);
					           if (str instanceof String) {
					               etype = new Bytes(actions.extractStringToken(list_S), (String) str);
					           } else {
					               etype = new Str(actions.extractStringToken(list_S), str);
					           }
					       }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   if (etype != null) {
			       retval.tree = etype;
			   }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom"


	public static class testlist_comp_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "testlist_comp"
	// Python.g:2018:1: testlist_comp[Token lpar] :t+= test_or_star_expr[$expr::ctype] ( ( options {k=2; } :c1= COMMA t+= test_or_star_expr[$expr::ctype] )* (c2= COMMA )? {...}?|| ( comp_for[gens] ) ) ;
	public final PythonParser.testlist_comp_return testlist_comp(Token lpar) throws RecognitionException {
		PythonParser.testlist_comp_return retval = new PythonParser.testlist_comp_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token c1=null;
		Token c2=null;
		List<Object> list_t=null;
		ParserRuleReturnScope comp_for280 =null;
		RuleReturnScope t = null;
		PythonTree c1_tree=null;
		PythonTree c2_tree=null;


		    expr etype = null;
		    List gens = new ArrayList();

		try {
			// Python.g:2028:5: (t+= test_or_star_expr[$expr::ctype] ( ( options {k=2; } :c1= COMMA t+= test_or_star_expr[$expr::ctype] )* (c2= COMMA )? {...}?|| ( comp_for[gens] ) ) )
			// Python.g:2028:7: t+= test_or_star_expr[$expr::ctype] ( ( options {k=2; } :c1= COMMA t+= test_or_star_expr[$expr::ctype] )* (c2= COMMA )? {...}?|| ( comp_for[gens] ) )
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_test_or_star_expr_in_testlist_comp7132);
			t=test_or_star_expr(expr_stack.peek().ctype);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

			if (list_t==null) list_t=new ArrayList<Object>();
			list_t.add(t.getTree());
			// Python.g:2029:9: ( ( options {k=2; } :c1= COMMA t+= test_or_star_expr[$expr::ctype] )* (c2= COMMA )? {...}?|| ( comp_for[gens] ) )
			int alt137=3;
			switch ( input.LA(1) ) {
			case COMMA:
				{
				alt137=1;
				}
				break;
			case RPAREN:
				{
				int LA137_2 = input.LA(2);
				if ( (( c1 != null || c2 != null )) ) {
					alt137=1;
				}
				else if ( (true) ) {
					alt137=2;
				}

				}
				break;
			case RBRACK:
				{
				int LA137_3 = input.LA(2);
				if ( (( c1 != null || c2 != null )) ) {
					alt137=1;
				}
				else if ( (true) ) {
					alt137=2;
				}

				}
				break;
			case FOR:
				{
				alt137=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 137, 0, input);
				throw nvae;
			}
			switch (alt137) {
				case 1 :
					// Python.g:2029:11: ( options {k=2; } :c1= COMMA t+= test_or_star_expr[$expr::ctype] )* (c2= COMMA )? {...}?
					{
					// Python.g:2029:11: ( options {k=2; } :c1= COMMA t+= test_or_star_expr[$expr::ctype] )*
					loop135:
					while (true) {
						int alt135=2;
						int LA135_0 = input.LA(1);
						if ( (LA135_0==COMMA) ) {
							int LA135_1 = input.LA(2);
							if ( (LA135_1==AWAIT||LA135_1==COMPLEX||(LA135_1 >= DOLLER && LA135_1 <= DOT)||LA135_1==FLOAT||(LA135_1 >= LAMBDA && LA135_1 <= LCURLY)||(LA135_1 >= LONGINT && LA135_1 <= MINUS)||LA135_1==NAME||LA135_1==NOT||LA135_1==PLUS||LA135_1==STAR||(LA135_1 >= STRING && LA135_1 <= TILDE)) ) {
								alt135=1;
							}

						}

						switch (alt135) {
						case 1 :
							// Python.g:2029:28: c1= COMMA t+= test_or_star_expr[$expr::ctype]
							{
							c1=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist_comp7156); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							c1_tree = (PythonTree)adaptor.create(c1);
							adaptor.addChild(root_0, c1_tree);
							}

							pushFollow(FOLLOW_test_or_star_expr_in_testlist_comp7160);
							t=test_or_star_expr(expr_stack.peek().ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

							if (list_t==null) list_t=new ArrayList<Object>();
							list_t.add(t.getTree());
							}
							break;

						default :
							break loop135;
						}
					}

					// Python.g:2029:74: (c2= COMMA )?
					int alt136=2;
					int LA136_0 = input.LA(1);
					if ( (LA136_0==COMMA) ) {
						alt136=1;
					}
					switch (alt136) {
						case 1 :
							// Python.g:2029:75: c2= COMMA
							{
							c2=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist_comp7168); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							c2_tree = (PythonTree)adaptor.create(c2);
							adaptor.addChild(root_0, c2_tree);
							}

							}
							break;

					}

					if ( !(( c1 != null || c2 != null )) ) {
						if (state.backtracking>0) {state.failed=true; return retval;}
						throw new FailedPredicateException(input, "testlist_comp", " $c1 != null || $c2 != null ");
					}
					if ( state.backtracking==0 ) {
					               if (lpar.getText().equals("(")) {
					                  etype = new Tuple(lpar, actions.castExprs(list_t), expr_stack.peek().ctype);
					               } else {
					                  etype = new org.python.antlr.ast.List(lpar, actions.castExprs(list_t), expr_stack.peek().ctype);
					               }
					           }
					}
					break;
				case 2 :
					// Python.g:2039:12: 
					{
					if ( state.backtracking==0 ) {
					               if (lpar.getText().equals("[")) {
					                  etype = new org.python.antlr.ast.List(lpar, actions.castExprs(list_t), expr_stack.peek().ctype);
					               }
					           }
					}
					break;
				case 3 :
					// Python.g:2044:11: ( comp_for[gens] )
					{
					// Python.g:2044:11: ( comp_for[gens] )
					// Python.g:2044:12: comp_for[gens]
					{
					pushFollow(FOLLOW_comp_for_in_testlist_comp7230);
					comp_for280=comp_for(gens);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_for280.getTree());

					if ( state.backtracking==0 ) {
					               Collections.reverse(gens);
					               List<comprehension> c = gens;
					               expr e = actions.castExpr(list_t.get(0));
					               if (e instanceof Context) {
					                   ((Context)e).setContext(expr_contextType.Load);
					               }

					               if (lpar.getText().equals("(")) {
					                  etype = new GeneratorExp((retval.start), e, c);
					               } else {
					                  etype = new ListComp((retval.start), e, c);
					               }
					           }
					}

					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (etype != null) {
			        retval.tree = etype;
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "testlist_comp"


	public static class lambdef_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "lambdef"
	// Python.g:2064:1: lambdef : LAMBDA ( varargslist )? COLON test[expr_contextType.Load] ;
	public final PythonParser.lambdef_return lambdef() throws RecognitionException {
		PythonParser.lambdef_return retval = new PythonParser.lambdef_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token LAMBDA281=null;
		Token COLON283=null;
		ParserRuleReturnScope varargslist282 =null;
		ParserRuleReturnScope test284 =null;

		PythonTree LAMBDA281_tree=null;
		PythonTree COLON283_tree=null;


		    expr etype = null;

		try {
			// Python.g:2071:5: ( LAMBDA ( varargslist )? COLON test[expr_contextType.Load] )
			// Python.g:2071:7: LAMBDA ( varargslist )? COLON test[expr_contextType.Load]
			{
			root_0 = (PythonTree)adaptor.nil();


			LAMBDA281=(Token)match(input,LAMBDA,FOLLOW_LAMBDA_in_lambdef7294); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			LAMBDA281_tree = (PythonTree)adaptor.create(LAMBDA281);
			adaptor.addChild(root_0, LAMBDA281_tree);
			}

			// Python.g:2071:14: ( varargslist )?
			int alt138=2;
			int LA138_0 = input.LA(1);
			if ( (LA138_0==DOUBLESTAR||LA138_0==NAME||LA138_0==STAR) ) {
				alt138=1;
			}
			switch (alt138) {
				case 1 :
					// Python.g:2071:15: varargslist
					{
					pushFollow(FOLLOW_varargslist_in_lambdef7297);
					varargslist282=varargslist();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, varargslist282.getTree());

					}
					break;

			}

			COLON283=(Token)match(input,COLON,FOLLOW_COLON_in_lambdef7301); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON283_tree = (PythonTree)adaptor.create(COLON283);
			adaptor.addChild(root_0, COLON283_tree);
			}

			pushFollow(FOLLOW_test_in_lambdef7303);
			test284=test(expr_contextType.Load);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, test284.getTree());

			if ( state.backtracking==0 ) {
			          arguments a = (varargslist282!=null?((PythonParser.varargslist_return)varargslist282).args:null);
			          if (a == null) {
			              a = new arguments(LAMBDA281, new ArrayList<arg>(), (arg)null,
			              new ArrayList<arg>(), new ArrayList<expr>(), (arg)null, new ArrayList<expr>());
			          }
			          etype = new Lambda(LAMBDA281, a, actions.castExpr((test284!=null?((PythonTree)test284.getTree()):null)));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = etype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "lambdef"


	public static class lambdef_nocond_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "lambdef_nocond"
	// Python.g:2083:1: lambdef_nocond : LAMBDA ( varargslist )? COLON test_nocond[expr_contextType.Load] ;
	public final PythonParser.lambdef_nocond_return lambdef_nocond() throws RecognitionException {
		PythonParser.lambdef_nocond_return retval = new PythonParser.lambdef_nocond_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token LAMBDA285=null;
		Token COLON287=null;
		ParserRuleReturnScope varargslist286 =null;
		ParserRuleReturnScope test_nocond288 =null;

		PythonTree LAMBDA285_tree=null;
		PythonTree COLON287_tree=null;


		    expr etype = null;

		try {
			// Python.g:2090:5: ( LAMBDA ( varargslist )? COLON test_nocond[expr_contextType.Load] )
			// Python.g:2090:7: LAMBDA ( varargslist )? COLON test_nocond[expr_contextType.Load]
			{
			root_0 = (PythonTree)adaptor.nil();


			LAMBDA285=(Token)match(input,LAMBDA,FOLLOW_LAMBDA_in_lambdef_nocond7340); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			LAMBDA285_tree = (PythonTree)adaptor.create(LAMBDA285);
			adaptor.addChild(root_0, LAMBDA285_tree);
			}

			// Python.g:2090:14: ( varargslist )?
			int alt139=2;
			int LA139_0 = input.LA(1);
			if ( (LA139_0==DOUBLESTAR||LA139_0==NAME||LA139_0==STAR) ) {
				alt139=1;
			}
			switch (alt139) {
				case 1 :
					// Python.g:2090:15: varargslist
					{
					pushFollow(FOLLOW_varargslist_in_lambdef_nocond7343);
					varargslist286=varargslist();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, varargslist286.getTree());

					}
					break;

			}

			COLON287=(Token)match(input,COLON,FOLLOW_COLON_in_lambdef_nocond7347); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON287_tree = (PythonTree)adaptor.create(COLON287);
			adaptor.addChild(root_0, COLON287_tree);
			}

			pushFollow(FOLLOW_test_nocond_in_lambdef_nocond7349);
			test_nocond288=test_nocond(expr_contextType.Load);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, test_nocond288.getTree());

			if ( state.backtracking==0 ) {
			          arguments a = (varargslist286!=null?((PythonParser.varargslist_return)varargslist286).args:null);
			          if (a == null) {
			              a = new arguments(LAMBDA285, new ArrayList<arg>(), (arg)null, new ArrayList<arg>(), new ArrayList<expr>(), (arg) null, new ArrayList<expr>());
			          }
			          etype = new Lambda(LAMBDA285, a, actions.castExpr((test_nocond288!=null?((PythonTree)test_nocond288.getTree()):null)));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = etype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "lambdef_nocond"


	public static class trailer_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "trailer"
	// Python.g:2102:1: trailer[Token begin, PythonTree ptree] : ( LPAREN ( arglist |) RPAREN | LBRACK subscriptlist[$begin] RBRACK | DOT attr );
	public final PythonParser.trailer_return trailer(Token begin, PythonTree ptree) throws RecognitionException {
		PythonParser.trailer_return retval = new PythonParser.trailer_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token LPAREN289=null;
		Token RPAREN291=null;
		Token LBRACK292=null;
		Token RBRACK294=null;
		Token DOT295=null;
		ParserRuleReturnScope arglist290 =null;
		ParserRuleReturnScope subscriptlist293 =null;
		ParserRuleReturnScope attr296 =null;

		PythonTree LPAREN289_tree=null;
		PythonTree RPAREN291_tree=null;
		PythonTree LBRACK292_tree=null;
		PythonTree RBRACK294_tree=null;
		PythonTree DOT295_tree=null;


		    expr etype = null;
		    Token end = null;

		try {
			// Python.g:2113:5: ( LPAREN ( arglist |) RPAREN | LBRACK subscriptlist[$begin] RBRACK | DOT attr )
			int alt141=3;
			switch ( input.LA(1) ) {
			case LPAREN:
				{
				alt141=1;
				}
				break;
			case LBRACK:
				{
				alt141=2;
				}
				break;
			case DOT:
				{
				alt141=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 141, 0, input);
				throw nvae;
			}
			switch (alt141) {
				case 1 :
					// Python.g:2113:7: LPAREN ( arglist |) RPAREN
					{
					root_0 = (PythonTree)adaptor.nil();


					LPAREN289=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_trailer7389); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LPAREN289_tree = (PythonTree)adaptor.create(LPAREN289);
					adaptor.addChild(root_0, LPAREN289_tree);
					}

					// Python.g:2114:7: ( arglist |)
					int alt140=2;
					int LA140_0 = input.LA(1);
					if ( (LA140_0==AWAIT||LA140_0==COMPLEX||(LA140_0 >= DOLLER && LA140_0 <= DOT)||LA140_0==DOUBLESTAR||LA140_0==FLOAT||(LA140_0 >= LAMBDA && LA140_0 <= LCURLY)||(LA140_0 >= LONGINT && LA140_0 <= MINUS)||LA140_0==NAME||LA140_0==NOT||LA140_0==PLUS||LA140_0==STAR||(LA140_0 >= STRING && LA140_0 <= TILDE)) ) {
						alt140=1;
					}
					else if ( (LA140_0==RPAREN) ) {
						alt140=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 140, 0, input);
						throw nvae;
					}

					switch (alt140) {
						case 1 :
							// Python.g:2114:8: arglist
							{
							pushFollow(FOLLOW_arglist_in_trailer7398);
							arglist290=arglist();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, arglist290.getTree());

							if ( state.backtracking==0 ) {
							           etype = new Call(begin, actions.castExpr(ptree), actions.castExprs((arglist290!=null?((PythonParser.arglist_return)arglist290).args:null)),
							             actions.makeKeywords((arglist290!=null?((PythonParser.arglist_return)arglist290).keywords:null)));
							       }
							}
							break;
						case 2 :
							// Python.g:2120:8: 
							{
							if ( state.backtracking==0 ) {
							           etype = new Call(begin, actions.castExpr(ptree), new ArrayList<expr>(), new ArrayList<keyword>());
							       }
							}
							break;

					}

					RPAREN291=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_trailer7440); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RPAREN291_tree = (PythonTree)adaptor.create(RPAREN291);
					adaptor.addChild(root_0, RPAREN291_tree);
					}

					if ( state.backtracking==0 ) {
					            end = RPAREN291;
					      }
					}
					break;
				case 2 :
					// Python.g:2128:7: LBRACK subscriptlist[$begin] RBRACK
					{
					root_0 = (PythonTree)adaptor.nil();


					LBRACK292=(Token)match(input,LBRACK,FOLLOW_LBRACK_in_trailer7456); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LBRACK292_tree = (PythonTree)adaptor.create(LBRACK292);
					adaptor.addChild(root_0, LBRACK292_tree);
					}

					pushFollow(FOLLOW_subscriptlist_in_trailer7458);
					subscriptlist293=subscriptlist(begin);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, subscriptlist293.getTree());

					RBRACK294=(Token)match(input,RBRACK,FOLLOW_RBRACK_in_trailer7461); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RBRACK294_tree = (PythonTree)adaptor.create(RBRACK294);
					adaptor.addChild(root_0, RBRACK294_tree);
					}

					if ( state.backtracking==0 ) {
					          etype = new Subscript(begin, actions.castExpr(ptree), actions.castSlice((subscriptlist293!=null?((PythonTree)subscriptlist293.getTree()):null)), expr_stack.peek().ctype);
					          end = RBRACK294;
					      }
					}
					break;
				case 3 :
					// Python.g:2133:7: DOT attr
					{
					root_0 = (PythonTree)adaptor.nil();


					DOT295=(Token)match(input,DOT,FOLLOW_DOT_in_trailer7477); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOT295_tree = (PythonTree)adaptor.create(DOT295);
					adaptor.addChild(root_0, DOT295_tree);
					}

					pushFollow(FOLLOW_attr_in_trailer7479);
					attr296=attr();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, attr296.getTree());

					if ( state.backtracking==0 ) {
					          etype = new Attribute(begin, actions.castExpr(ptree), new Name((attr296!=null?((PythonTree)attr296.getTree()):null), (attr296!=null?input.toString(attr296.start,attr296.stop):null), expr_contextType.Load), expr_stack.peek().ctype);
					          end = (attr296!=null?((PythonTree)attr296.getTree()):null).getToken(); 
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (etype != null) {
			        etype.setCharStopIndex(((org.antlr.runtime.CommonToken)end).getStopIndex() + 1);
			        retval.tree = etype;
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "trailer"


	public static class subscriptlist_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "subscriptlist"
	// Python.g:2141:1: subscriptlist[Token begin] :sub+= subscript ( options {greedy=true; } :c1= COMMA sub+= subscript )* (c2= COMMA )? ;
	public final PythonParser.subscriptlist_return subscriptlist(Token begin) throws RecognitionException {
		PythonParser.subscriptlist_return retval = new PythonParser.subscriptlist_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token c1=null;
		Token c2=null;
		List<Object> list_sub=null;
		RuleReturnScope sub = null;
		PythonTree c1_tree=null;
		PythonTree c2_tree=null;


		    slice sltype = null;

		try {
			// Python.g:2148:5: (sub+= subscript ( options {greedy=true; } :c1= COMMA sub+= subscript )* (c2= COMMA )? )
			// Python.g:2148:7: sub+= subscript ( options {greedy=true; } :c1= COMMA sub+= subscript )* (c2= COMMA )?
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_subscript_in_subscriptlist7518);
			sub=subscript();
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, sub.getTree());

			if (list_sub==null) list_sub=new ArrayList<Object>();
			list_sub.add(sub.getTree());
			// Python.g:2148:22: ( options {greedy=true; } :c1= COMMA sub+= subscript )*
			loop142:
			while (true) {
				int alt142=2;
				int LA142_0 = input.LA(1);
				if ( (LA142_0==COMMA) ) {
					int LA142_1 = input.LA(2);
					if ( (LA142_1==AWAIT||LA142_1==COLON||LA142_1==COMPLEX||(LA142_1 >= DOLLER && LA142_1 <= DOT)||LA142_1==FLOAT||(LA142_1 >= LAMBDA && LA142_1 <= LCURLY)||(LA142_1 >= LONGINT && LA142_1 <= MINUS)||LA142_1==NAME||LA142_1==NOT||LA142_1==PLUS||(LA142_1 >= STRING && LA142_1 <= TILDE)) ) {
						alt142=1;
					}

				}

				switch (alt142) {
				case 1 :
					// Python.g:2148:46: c1= COMMA sub+= subscript
					{
					c1=(Token)match(input,COMMA,FOLLOW_COMMA_in_subscriptlist7530); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					c1_tree = (PythonTree)adaptor.create(c1);
					adaptor.addChild(root_0, c1_tree);
					}

					pushFollow(FOLLOW_subscript_in_subscriptlist7534);
					sub=subscript();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, sub.getTree());

					if (list_sub==null) list_sub=new ArrayList<Object>();
					list_sub.add(sub.getTree());
					}
					break;

				default :
					break loop142;
				}
			}

			// Python.g:2148:72: (c2= COMMA )?
			int alt143=2;
			int LA143_0 = input.LA(1);
			if ( (LA143_0==COMMA) ) {
				alt143=1;
			}
			switch (alt143) {
				case 1 :
					// Python.g:2148:73: c2= COMMA
					{
					c2=(Token)match(input,COMMA,FOLLOW_COMMA_in_subscriptlist7541); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					c2_tree = (PythonTree)adaptor.create(c2);
					adaptor.addChild(root_0, c2_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          sltype = actions.makeSliceType(begin, c1, c2, list_sub);
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = sltype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subscriptlist"


	public static class subscript_return extends ParserRuleReturnScope {
		public slice sltype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "subscript"
	// Python.g:2155:1: subscript returns [slice sltype] : ( ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )? | ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )? | test[expr_contextType.Load] );
	public final PythonParser.subscript_return subscript() throws RecognitionException {
		PythonParser.subscript_return retval = new PythonParser.subscript_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token c1=null;
		Token c2=null;
		ParserRuleReturnScope lower =null;
		ParserRuleReturnScope upper1 =null;
		ParserRuleReturnScope upper2 =null;
		ParserRuleReturnScope sliceop297 =null;
		ParserRuleReturnScope sliceop298 =null;
		ParserRuleReturnScope test299 =null;

		PythonTree c1_tree=null;
		PythonTree c2_tree=null;

		try {
			// Python.g:2160:5: ( ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )? | ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )? | test[expr_contextType.Load] )
			int alt149=3;
			int LA149_0 = input.LA(1);
			if ( (LA149_0==NOT) ) {
				int LA149_1 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==PLUS) ) {
				int LA149_2 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==MINUS) ) {
				int LA149_3 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==TILDE) ) {
				int LA149_4 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==AWAIT) ) {
				int LA149_5 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==LPAREN) ) {
				int LA149_6 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==LBRACK) ) {
				int LA149_7 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==LCURLY) ) {
				int LA149_8 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==NAME) ) {
				int LA149_9 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==LONGINT) ) {
				int LA149_10 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==FLOAT) ) {
				int LA149_11 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==COMPLEX) ) {
				int LA149_12 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==DOT) ) {
				int LA149_13 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==DOLLER) ) {
				int LA149_14 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==STRING) ) {
				int LA149_15 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==LAMBDA) ) {
				int LA149_16 = input.LA(2);
				if ( (synpred7_Python()) ) {
					alt149=1;
				}
				else if ( (true) ) {
					alt149=3;
				}

			}
			else if ( (LA149_0==COLON) && (synpred8_Python())) {
				alt149=2;
			}

			switch (alt149) {
				case 1 :
					// Python.g:2160:7: ( test[null] COLON )=>lower= test[expr_contextType.Load] (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )?
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_test_in_subscript7596);
					lower=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, lower.getTree());

					// Python.g:2161:41: (c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )? )?
					int alt146=2;
					int LA146_0 = input.LA(1);
					if ( (LA146_0==COLON) ) {
						alt146=1;
					}
					switch (alt146) {
						case 1 :
							// Python.g:2161:42: c1= COLON (upper1= test[expr_contextType.Load] )? ( sliceop )?
							{
							c1=(Token)match(input,COLON,FOLLOW_COLON_in_subscript7602); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							c1_tree = (PythonTree)adaptor.create(c1);
							adaptor.addChild(root_0, c1_tree);
							}

							// Python.g:2161:51: (upper1= test[expr_contextType.Load] )?
							int alt144=2;
							int LA144_0 = input.LA(1);
							if ( (LA144_0==AWAIT||LA144_0==COMPLEX||(LA144_0 >= DOLLER && LA144_0 <= DOT)||LA144_0==FLOAT||(LA144_0 >= LAMBDA && LA144_0 <= LCURLY)||(LA144_0 >= LONGINT && LA144_0 <= MINUS)||LA144_0==NAME||LA144_0==NOT||LA144_0==PLUS||(LA144_0 >= STRING && LA144_0 <= TILDE)) ) {
								alt144=1;
							}
							switch (alt144) {
								case 1 :
									// Python.g:2161:52: upper1= test[expr_contextType.Load]
									{
									pushFollow(FOLLOW_test_in_subscript7607);
									upper1=test(expr_contextType.Load);
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, upper1.getTree());

									}
									break;

							}

							// Python.g:2161:89: ( sliceop )?
							int alt145=2;
							int LA145_0 = input.LA(1);
							if ( (LA145_0==COLON) ) {
								alt145=1;
							}
							switch (alt145) {
								case 1 :
									// Python.g:2161:90: sliceop
									{
									pushFollow(FOLLOW_sliceop_in_subscript7613);
									sliceop297=sliceop();
									state._fsp--;
									if (state.failed) return retval;
									if ( state.backtracking==0 ) adaptor.addChild(root_0, sliceop297.getTree());

									}
									break;

							}

							}
							break;

					}

					if ( state.backtracking==0 ) {
					          retval.sltype = actions.makeSubscript((lower!=null?((PythonTree)lower.getTree()):null), c1, (upper1!=null?((PythonTree)upper1.getTree()):null), (sliceop297!=null?((PythonTree)sliceop297.getTree()):null));
					      }
					}
					break;
				case 2 :
					// Python.g:2165:7: ( COLON )=>c2= COLON (upper2= test[expr_contextType.Load] )? ( sliceop )?
					{
					root_0 = (PythonTree)adaptor.nil();


					c2=(Token)match(input,COLON,FOLLOW_COLON_in_subscript7644); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					c2_tree = (PythonTree)adaptor.create(c2);
					adaptor.addChild(root_0, c2_tree);
					}

					// Python.g:2166:16: (upper2= test[expr_contextType.Load] )?
					int alt147=2;
					int LA147_0 = input.LA(1);
					if ( (LA147_0==AWAIT||LA147_0==COMPLEX||(LA147_0 >= DOLLER && LA147_0 <= DOT)||LA147_0==FLOAT||(LA147_0 >= LAMBDA && LA147_0 <= LCURLY)||(LA147_0 >= LONGINT && LA147_0 <= MINUS)||LA147_0==NAME||LA147_0==NOT||LA147_0==PLUS||(LA147_0 >= STRING && LA147_0 <= TILDE)) ) {
						alt147=1;
					}
					switch (alt147) {
						case 1 :
							// Python.g:2166:17: upper2= test[expr_contextType.Load]
							{
							pushFollow(FOLLOW_test_in_subscript7649);
							upper2=test(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, upper2.getTree());

							}
							break;

					}

					// Python.g:2166:54: ( sliceop )?
					int alt148=2;
					int LA148_0 = input.LA(1);
					if ( (LA148_0==COLON) ) {
						alt148=1;
					}
					switch (alt148) {
						case 1 :
							// Python.g:2166:55: sliceop
							{
							pushFollow(FOLLOW_sliceop_in_subscript7655);
							sliceop298=sliceop();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, sliceop298.getTree());

							}
							break;

					}

					if ( state.backtracking==0 ) {
					          retval.sltype = actions.makeSubscript(null, c2, (upper2!=null?((PythonTree)upper2.getTree()):null), (sliceop298!=null?((PythonTree)sliceop298.getTree()):null));
					      }
					}
					break;
				case 3 :
					// Python.g:2170:7: test[expr_contextType.Load]
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_test_in_subscript7673);
					test299=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, test299.getTree());

					if ( state.backtracking==0 ) {
					          retval.sltype = new Index((test299!=null?(test299.start):null), actions.castExpr((test299!=null?((PythonTree)test299.getTree()):null)));
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    retval.tree = retval.sltype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "subscript"


	public static class sliceop_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "sliceop"
	// Python.g:2177:1: sliceop : COLON ( test[expr_contextType.Load] -> test |) ;
	public final PythonParser.sliceop_return sliceop() throws RecognitionException {
		PythonParser.sliceop_return retval = new PythonParser.sliceop_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COLON300=null;
		ParserRuleReturnScope test301 =null;

		PythonTree COLON300_tree=null;
		RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
		RewriteRuleSubtreeStream stream_test=new RewriteRuleSubtreeStream(adaptor,"rule test");


		    expr etype = null;

		try {
			// Python.g:2186:5: ( COLON ( test[expr_contextType.Load] -> test |) )
			// Python.g:2186:7: COLON ( test[expr_contextType.Load] -> test |)
			{
			COLON300=(Token)match(input,COLON,FOLLOW_COLON_in_sliceop7710); if (state.failed) return retval; 
			if ( state.backtracking==0 ) stream_COLON.add(COLON300);

			// Python.g:2187:6: ( test[expr_contextType.Load] -> test |)
			int alt150=2;
			int LA150_0 = input.LA(1);
			if ( (LA150_0==AWAIT||LA150_0==COMPLEX||(LA150_0 >= DOLLER && LA150_0 <= DOT)||LA150_0==FLOAT||(LA150_0 >= LAMBDA && LA150_0 <= LCURLY)||(LA150_0 >= LONGINT && LA150_0 <= MINUS)||LA150_0==NAME||LA150_0==NOT||LA150_0==PLUS||(LA150_0 >= STRING && LA150_0 <= TILDE)) ) {
				alt150=1;
			}
			else if ( (LA150_0==COMMA||LA150_0==RBRACK) ) {
				alt150=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 150, 0, input);
				throw nvae;
			}

			switch (alt150) {
				case 1 :
					// Python.g:2187:7: test[expr_contextType.Load]
					{
					pushFollow(FOLLOW_test_in_sliceop7718);
					test301=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) stream_test.add(test301.getTree());
					// AST REWRITE
					// elements: test
					// token labels: 
					// rule labels: retval
					// token list labels: 
					// rule list labels: 
					// wildcard labels: 
					if ( state.backtracking==0 ) {
					retval.tree = root_0;
					RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.getTree():null);

					root_0 = (PythonTree)adaptor.nil();
					// 2188:5: -> test
					{
						adaptor.addChild(root_0, stream_test.nextTree());
					}


					retval.tree = root_0;
					}

					}
					break;
				case 2 :
					// Python.g:2190:8: 
					{
					if ( state.backtracking==0 ) {
					           etype = new Name(COLON300, "None", expr_contextType.Load);
					       }
					}
					break;

			}

			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (etype != null) {
			        retval.tree = etype;
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "sliceop"


	public static class exprlist_return extends ParserRuleReturnScope {
		public expr etype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "exprlist"
	// Python.g:2197:1: exprlist[expr_contextType ctype] returns [expr etype] : ( ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )? | expr[ctype] );
	public final PythonParser.exprlist_return exprlist(expr_contextType ctype) throws RecognitionException {
		PythonParser.exprlist_return retval = new PythonParser.exprlist_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COMMA302=null;
		Token COMMA303=null;
		List<Object> list_e=null;
		ParserRuleReturnScope expr304 =null;
		RuleReturnScope e = null;
		PythonTree COMMA302_tree=null;
		PythonTree COMMA303_tree=null;

		try {
			// Python.g:2199:5: ( ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )? | expr[ctype] )
			int alt153=2;
			switch ( input.LA(1) ) {
			case PLUS:
				{
				int LA153_1 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case MINUS:
				{
				int LA153_2 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case TILDE:
				{
				int LA153_3 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case AWAIT:
				{
				int LA153_4 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case LPAREN:
				{
				int LA153_5 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case LBRACK:
				{
				int LA153_6 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case LCURLY:
				{
				int LA153_7 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case NAME:
				{
				int LA153_8 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case LONGINT:
				{
				int LA153_9 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case FLOAT:
				{
				int LA153_10 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case COMPLEX:
				{
				int LA153_11 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case DOT:
				{
				int LA153_12 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case DOLLER:
				{
				int LA153_13 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			case STRING:
				{
				int LA153_14 = input.LA(2);
				if ( (synpred9_Python()) ) {
					alt153=1;
				}
				else if ( (true) ) {
					alt153=2;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 153, 0, input);
				throw nvae;
			}
			switch (alt153) {
				case 1 :
					// Python.g:2199:7: ( expr[null] COMMA )=>e+= expr[ctype] ( options {k=2; } : COMMA e+= expr[ctype] )* ( COMMA )?
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_expr_in_exprlist7789);
					e=expr(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());

					if (list_e==null) list_e=new ArrayList<Object>();
					list_e.add(e.getTree());
					// Python.g:2199:44: ( options {k=2; } : COMMA e+= expr[ctype] )*
					loop151:
					while (true) {
						int alt151=2;
						int LA151_0 = input.LA(1);
						if ( (LA151_0==COMMA) ) {
							int LA151_1 = input.LA(2);
							if ( (LA151_1==AWAIT||LA151_1==COMPLEX||(LA151_1 >= DOLLER && LA151_1 <= DOT)||LA151_1==FLOAT||(LA151_1 >= LBRACK && LA151_1 <= LCURLY)||(LA151_1 >= LONGINT && LA151_1 <= MINUS)||LA151_1==NAME||LA151_1==PLUS||(LA151_1 >= STRING && LA151_1 <= TILDE)) ) {
								alt151=1;
							}

						}

						switch (alt151) {
						case 1 :
							// Python.g:2199:61: COMMA e+= expr[ctype]
							{
							COMMA302=(Token)match(input,COMMA,FOLLOW_COMMA_in_exprlist7801); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA302_tree = (PythonTree)adaptor.create(COMMA302);
							adaptor.addChild(root_0, COMMA302_tree);
							}

							pushFollow(FOLLOW_expr_in_exprlist7805);
							e=expr(ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());

							if (list_e==null) list_e=new ArrayList<Object>();
							list_e.add(e.getTree());
							}
							break;

						default :
							break loop151;
						}
					}

					// Python.g:2199:84: ( COMMA )?
					int alt152=2;
					int LA152_0 = input.LA(1);
					if ( (LA152_0==COMMA) ) {
						alt152=1;
					}
					switch (alt152) {
						case 1 :
							// Python.g:2199:85: COMMA
							{
							COMMA303=(Token)match(input,COMMA,FOLLOW_COMMA_in_exprlist7811); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA303_tree = (PythonTree)adaptor.create(COMMA303);
							adaptor.addChild(root_0, COMMA303_tree);
							}

							}
							break;

					}

					if ( state.backtracking==0 ) {
					           retval.etype = new Tuple((retval.start), actions.castExprs(list_e), ctype);
					       }
					}
					break;
				case 2 :
					// Python.g:2203:7: expr[ctype]
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_expr_in_exprlist7830);
					expr304=expr(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, expr304.getTree());

					if ( state.backtracking==0 ) {
					        retval.etype = actions.castExpr((expr304!=null?((PythonTree)expr304.getTree()):null));
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "exprlist"


	public static class del_list_return extends ParserRuleReturnScope {
		public List<expr> etypes;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "del_list"
	// Python.g:2211:1: del_list returns [List<expr> etypes] :e+= expr[expr_contextType.Del] ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )* ( COMMA )? ;
	public final PythonParser.del_list_return del_list() throws RecognitionException {
		PythonParser.del_list_return retval = new PythonParser.del_list_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COMMA305=null;
		Token COMMA306=null;
		List<Object> list_e=null;
		RuleReturnScope e = null;
		PythonTree COMMA305_tree=null;
		PythonTree COMMA306_tree=null;

		try {
			// Python.g:2213:5: (e+= expr[expr_contextType.Del] ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )* ( COMMA )? )
			// Python.g:2213:7: e+= expr[expr_contextType.Del] ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )* ( COMMA )?
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_expr_in_del_list7868);
			e=expr(expr_contextType.Del);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());

			if (list_e==null) list_e=new ArrayList<Object>();
			list_e.add(e.getTree());
			// Python.g:2213:37: ( options {k=2; } : COMMA e+= expr[expr_contextType.Del] )*
			loop154:
			while (true) {
				int alt154=2;
				int LA154_0 = input.LA(1);
				if ( (LA154_0==COMMA) ) {
					int LA154_1 = input.LA(2);
					if ( (LA154_1==AWAIT||LA154_1==COMPLEX||(LA154_1 >= DOLLER && LA154_1 <= DOT)||LA154_1==FLOAT||(LA154_1 >= LBRACK && LA154_1 <= LCURLY)||(LA154_1 >= LONGINT && LA154_1 <= MINUS)||LA154_1==NAME||LA154_1==PLUS||(LA154_1 >= STRING && LA154_1 <= TILDE)) ) {
						alt154=1;
					}

				}

				switch (alt154) {
				case 1 :
					// Python.g:2213:54: COMMA e+= expr[expr_contextType.Del]
					{
					COMMA305=(Token)match(input,COMMA,FOLLOW_COMMA_in_del_list7880); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA305_tree = (PythonTree)adaptor.create(COMMA305);
					adaptor.addChild(root_0, COMMA305_tree);
					}

					pushFollow(FOLLOW_expr_in_del_list7884);
					e=expr(expr_contextType.Del);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, e.getTree());

					if (list_e==null) list_e=new ArrayList<Object>();
					list_e.add(e.getTree());
					}
					break;

				default :
					break loop154;
				}
			}

			// Python.g:2213:92: ( COMMA )?
			int alt155=2;
			int LA155_0 = input.LA(1);
			if ( (LA155_0==COMMA) ) {
				alt155=1;
			}
			switch (alt155) {
				case 1 :
					// Python.g:2213:93: COMMA
					{
					COMMA306=(Token)match(input,COMMA,FOLLOW_COMMA_in_del_list7890); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA306_tree = (PythonTree)adaptor.create(COMMA306);
					adaptor.addChild(root_0, COMMA306_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          retval.etypes = actions.makeDeleteList(list_e);
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "del_list"


	public static class testlist_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "testlist"
	// Python.g:2220:1: testlist[expr_contextType ctype] : ( ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )? | test[ctype] );
	public final PythonParser.testlist_return testlist(expr_contextType ctype) throws RecognitionException {
		PythonParser.testlist_return retval = new PythonParser.testlist_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COMMA307=null;
		Token COMMA308=null;
		List<Object> list_t=null;
		ParserRuleReturnScope test309 =null;
		RuleReturnScope t = null;
		PythonTree COMMA307_tree=null;
		PythonTree COMMA308_tree=null;


		    expr etype = null;

		try {
			// Python.g:2229:5: ( ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )? | test[ctype] )
			int alt158=2;
			switch ( input.LA(1) ) {
			case NOT:
				{
				int LA158_1 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case PLUS:
				{
				int LA158_2 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case MINUS:
				{
				int LA158_3 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case TILDE:
				{
				int LA158_4 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case AWAIT:
				{
				int LA158_5 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case LPAREN:
				{
				int LA158_6 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case LBRACK:
				{
				int LA158_7 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case LCURLY:
				{
				int LA158_8 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case NAME:
				{
				int LA158_9 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case LONGINT:
				{
				int LA158_10 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case FLOAT:
				{
				int LA158_11 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case COMPLEX:
				{
				int LA158_12 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case DOT:
				{
				int LA158_13 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case DOLLER:
				{
				int LA158_14 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case STRING:
				{
				int LA158_15 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			case LAMBDA:
				{
				int LA158_16 = input.LA(2);
				if ( (synpred10_Python()) ) {
					alt158=1;
				}
				else if ( (true) ) {
					alt158=2;
				}

				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 158, 0, input);
				throw nvae;
			}
			switch (alt158) {
				case 1 :
					// Python.g:2229:7: ( test[null] COMMA )=>t+= test[ctype] ( options {k=2; } : COMMA t+= test[ctype] )* ( COMMA )?
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_test_in_testlist7943);
					t=test(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

					if (list_t==null) list_t=new ArrayList<Object>();
					list_t.add(t.getTree());
					// Python.g:2230:22: ( options {k=2; } : COMMA t+= test[ctype] )*
					loop156:
					while (true) {
						int alt156=2;
						int LA156_0 = input.LA(1);
						if ( (LA156_0==COMMA) ) {
							int LA156_1 = input.LA(2);
							if ( (LA156_1==AWAIT||LA156_1==COMPLEX||(LA156_1 >= DOLLER && LA156_1 <= DOT)||LA156_1==FLOAT||(LA156_1 >= LAMBDA && LA156_1 <= LCURLY)||(LA156_1 >= LONGINT && LA156_1 <= MINUS)||LA156_1==NAME||LA156_1==NOT||LA156_1==PLUS||(LA156_1 >= STRING && LA156_1 <= TILDE)) ) {
								alt156=1;
							}

						}

						switch (alt156) {
						case 1 :
							// Python.g:2230:39: COMMA t+= test[ctype]
							{
							COMMA307=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist7955); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA307_tree = (PythonTree)adaptor.create(COMMA307);
							adaptor.addChild(root_0, COMMA307_tree);
							}

							pushFollow(FOLLOW_test_in_testlist7959);
							t=test(ctype);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, t.getTree());

							if (list_t==null) list_t=new ArrayList<Object>();
							list_t.add(t.getTree());
							}
							break;

						default :
							break loop156;
						}
					}

					// Python.g:2230:62: ( COMMA )?
					int alt157=2;
					int LA157_0 = input.LA(1);
					if ( (LA157_0==COMMA) ) {
						alt157=1;
					}
					switch (alt157) {
						case 1 :
							// Python.g:2230:63: COMMA
							{
							COMMA308=(Token)match(input,COMMA,FOLLOW_COMMA_in_testlist7965); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COMMA308_tree = (PythonTree)adaptor.create(COMMA308);
							adaptor.addChild(root_0, COMMA308_tree);
							}

							}
							break;

					}

					if ( state.backtracking==0 ) {
					          etype = new Tuple((retval.start), actions.castExprs(list_t), ctype);
					      }
					}
					break;
				case 2 :
					// Python.g:2234:7: test[ctype]
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_test_in_testlist7983);
					test309=test(ctype);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, test309.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (etype != null) {
			        retval.tree = etype;
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "testlist"


	public static class dictorsetmaker_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "dictorsetmaker"
	// Python.g:2241:1: dictorsetmaker[Token lcurly] : ( ( test[expr_contextType.Load] COLON | DOUBLESTAR )=> (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] ) ( comp_for[gens] | ( options {k=2; } : COMMA (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] ) )* ( COMMA )? ) | (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] ) ( comp_for[gens] | ( COMMA (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] ) )* ( COMMA )? ) );
	public final PythonParser.dictorsetmaker_return dictorsetmaker(Token lcurly) throws RecognitionException {
		PythonParser.dictorsetmaker_return retval = new PythonParser.dictorsetmaker_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COLON310=null;
		Token DOUBLESTAR311=null;
		Token COMMA313=null;
		Token COLON314=null;
		Token DOUBLESTAR315=null;
		Token COMMA316=null;
		Token COMMA318=null;
		Token COMMA319=null;
		List<Object> list_k=null;
		List<Object> list_v=null;
		List<Object> list_uv=null;
		List<Object> list_s=null;
		ParserRuleReturnScope comp_for312 =null;
		ParserRuleReturnScope comp_for317 =null;
		RuleReturnScope k = null;
		RuleReturnScope v = null;
		RuleReturnScope uv = null;
		RuleReturnScope s = null;
		PythonTree COLON310_tree=null;
		PythonTree DOUBLESTAR311_tree=null;
		PythonTree COMMA313_tree=null;
		PythonTree COLON314_tree=null;
		PythonTree DOUBLESTAR315_tree=null;
		PythonTree COMMA316_tree=null;
		PythonTree COMMA318_tree=null;
		PythonTree COMMA319_tree=null;


		    List gens = new ArrayList();
		    expr etype = null;

		try {
			// Python.g:2251:5: ( ( test[expr_contextType.Load] COLON | DOUBLESTAR )=> (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] ) ( comp_for[gens] | ( options {k=2; } : COMMA (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] ) )* ( COMMA )? ) | (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] ) ( comp_for[gens] | ( COMMA (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] ) )* ( COMMA )? ) )
			int alt169=2;
			int LA169_0 = input.LA(1);
			if ( (LA169_0==NOT) ) {
				int LA169_1 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==PLUS) ) {
				int LA169_2 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==MINUS) ) {
				int LA169_3 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==TILDE) ) {
				int LA169_4 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==AWAIT) ) {
				int LA169_5 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==LPAREN) ) {
				int LA169_6 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==LBRACK) ) {
				int LA169_7 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==LCURLY) ) {
				int LA169_8 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==NAME) ) {
				int LA169_9 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==LONGINT) ) {
				int LA169_10 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==FLOAT) ) {
				int LA169_11 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==COMPLEX) ) {
				int LA169_12 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==DOT) ) {
				int LA169_13 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==DOLLER) ) {
				int LA169_14 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==STRING) ) {
				int LA169_15 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==LAMBDA) ) {
				int LA169_16 = input.LA(2);
				if ( (synpred11_Python()) ) {
					alt169=1;
				}
				else if ( (true) ) {
					alt169=2;
				}

			}
			else if ( (LA169_0==DOUBLESTAR) && (synpred11_Python())) {
				alt169=1;
			}
			else if ( (LA169_0==STAR) ) {
				alt169=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 169, 0, input);
				throw nvae;
			}

			switch (alt169) {
				case 1 :
					// Python.g:2251:7: ( test[expr_contextType.Load] COLON | DOUBLESTAR )=> (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] ) ( comp_for[gens] | ( options {k=2; } : COMMA (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] ) )* ( COMMA )? )
					{
					root_0 = (PythonTree)adaptor.nil();


					// Python.g:2251:59: (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] )
					int alt159=2;
					int LA159_0 = input.LA(1);
					if ( (LA159_0==AWAIT||LA159_0==COMPLEX||(LA159_0 >= DOLLER && LA159_0 <= DOT)||LA159_0==FLOAT||(LA159_0 >= LAMBDA && LA159_0 <= LCURLY)||(LA159_0 >= LONGINT && LA159_0 <= MINUS)||LA159_0==NAME||LA159_0==NOT||LA159_0==PLUS||(LA159_0 >= STRING && LA159_0 <= TILDE)) ) {
						alt159=1;
					}
					else if ( (LA159_0==DOUBLESTAR) ) {
						alt159=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 159, 0, input);
						throw nvae;
					}

					switch (alt159) {
						case 1 :
							// Python.g:2251:60: k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load]
							{
							pushFollow(FOLLOW_test_in_dictorsetmaker8032);
							k=test(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

							if (list_k==null) list_k=new ArrayList<Object>();
							list_k.add(k.getTree());
							COLON310=(Token)match(input,COLON,FOLLOW_COLON_in_dictorsetmaker8035); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							COLON310_tree = (PythonTree)adaptor.create(COLON310);
							adaptor.addChild(root_0, COLON310_tree);
							}

							pushFollow(FOLLOW_test_in_dictorsetmaker8039);
							v=test(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, v.getTree());

							if (list_v==null) list_v=new ArrayList<Object>();
							list_v.add(v.getTree());
							}
							break;
						case 2 :
							// Python.g:2251:130: DOUBLESTAR uv+= expr[expr_contextType.Load]
							{
							DOUBLESTAR311=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_dictorsetmaker8044); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							DOUBLESTAR311_tree = (PythonTree)adaptor.create(DOUBLESTAR311);
							adaptor.addChild(root_0, DOUBLESTAR311_tree);
							}

							pushFollow(FOLLOW_expr_in_dictorsetmaker8048);
							uv=expr(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, uv.getTree());

							if (list_uv==null) list_uv=new ArrayList<Object>();
							list_uv.add(uv.getTree());
							}
							break;

					}

					// Python.g:2252:10: ( comp_for[gens] | ( options {k=2; } : COMMA (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] ) )* ( COMMA )? )
					int alt163=2;
					int LA163_0 = input.LA(1);
					if ( (LA163_0==FOR) ) {
						alt163=1;
					}
					else if ( (LA163_0==COMMA||LA163_0==RCURLY) ) {
						alt163=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 163, 0, input);
						throw nvae;
					}

					switch (alt163) {
						case 1 :
							// Python.g:2252:12: comp_for[gens]
							{
							pushFollow(FOLLOW_comp_for_in_dictorsetmaker8063);
							comp_for312=comp_for(gens);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_for312.getTree());

							if ( state.backtracking==0 ) {
							              Collections.reverse(gens);
							              List<comprehension> c = gens;
							              etype = new DictComp((retval.start), actions.castExpr(list_k.get(0)), actions.castExpr(list_v.get(0)), c);
							           }
							}
							break;
						case 2 :
							// Python.g:2258:12: ( options {k=2; } : COMMA (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] ) )* ( COMMA )?
							{
							// Python.g:2258:12: ( options {k=2; } : COMMA (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] ) )*
							loop161:
							while (true) {
								int alt161=2;
								int LA161_0 = input.LA(1);
								if ( (LA161_0==COMMA) ) {
									int LA161_1 = input.LA(2);
									if ( (LA161_1==AWAIT||LA161_1==COMPLEX||(LA161_1 >= DOLLER && LA161_1 <= DOT)||LA161_1==DOUBLESTAR||LA161_1==FLOAT||(LA161_1 >= LAMBDA && LA161_1 <= LCURLY)||(LA161_1 >= LONGINT && LA161_1 <= MINUS)||LA161_1==NAME||LA161_1==NOT||LA161_1==PLUS||(LA161_1 >= STRING && LA161_1 <= TILDE)) ) {
										alt161=1;
									}

								}

								switch (alt161) {
								case 1 :
									// Python.g:2258:28: COMMA (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] )
									{
									COMMA313=(Token)match(input,COMMA,FOLLOW_COMMA_in_dictorsetmaker8098); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									COMMA313_tree = (PythonTree)adaptor.create(COMMA313);
									adaptor.addChild(root_0, COMMA313_tree);
									}

									// Python.g:2258:34: (k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load] | DOUBLESTAR uv+= expr[expr_contextType.Load] )
									int alt160=2;
									int LA160_0 = input.LA(1);
									if ( (LA160_0==AWAIT||LA160_0==COMPLEX||(LA160_0 >= DOLLER && LA160_0 <= DOT)||LA160_0==FLOAT||(LA160_0 >= LAMBDA && LA160_0 <= LCURLY)||(LA160_0 >= LONGINT && LA160_0 <= MINUS)||LA160_0==NAME||LA160_0==NOT||LA160_0==PLUS||(LA160_0 >= STRING && LA160_0 <= TILDE)) ) {
										alt160=1;
									}
									else if ( (LA160_0==DOUBLESTAR) ) {
										alt160=2;
									}

									else {
										if (state.backtracking>0) {state.failed=true; return retval;}
										NoViableAltException nvae =
											new NoViableAltException("", 160, 0, input);
										throw nvae;
									}

									switch (alt160) {
										case 1 :
											// Python.g:2258:35: k+= test[expr_contextType.Load] COLON v+= test[expr_contextType.Load]
											{
											pushFollow(FOLLOW_test_in_dictorsetmaker8103);
											k=test(expr_contextType.Load);
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

											if (list_k==null) list_k=new ArrayList<Object>();
											list_k.add(k.getTree());
											COLON314=(Token)match(input,COLON,FOLLOW_COLON_in_dictorsetmaker8106); if (state.failed) return retval;
											if ( state.backtracking==0 ) {
											COLON314_tree = (PythonTree)adaptor.create(COLON314);
											adaptor.addChild(root_0, COLON314_tree);
											}

											pushFollow(FOLLOW_test_in_dictorsetmaker8110);
											v=test(expr_contextType.Load);
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, v.getTree());

											if (list_v==null) list_v=new ArrayList<Object>();
											list_v.add(v.getTree());
											}
											break;
										case 2 :
											// Python.g:2258:105: DOUBLESTAR uv+= expr[expr_contextType.Load]
											{
											DOUBLESTAR315=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_dictorsetmaker8115); if (state.failed) return retval;
											if ( state.backtracking==0 ) {
											DOUBLESTAR315_tree = (PythonTree)adaptor.create(DOUBLESTAR315);
											adaptor.addChild(root_0, DOUBLESTAR315_tree);
											}

											pushFollow(FOLLOW_expr_in_dictorsetmaker8119);
											uv=expr(expr_contextType.Load);
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, uv.getTree());

											if (list_uv==null) list_uv=new ArrayList<Object>();
											list_uv.add(uv.getTree());
											}
											break;

									}

									}
									break;

								default :
									break loop161;
								}
							}

							if ( state.backtracking==0 ) {
							              etype = new Dict(lcurly, actions.castExprs(list_k), actions.castExprs(list_v, list_uv));
							           }
							// Python.g:2262:10: ( COMMA )?
							int alt162=2;
							int LA162_0 = input.LA(1);
							if ( (LA162_0==COMMA) ) {
								alt162=1;
							}
							switch (alt162) {
								case 1 :
									// Python.g:2262:11: COMMA
									{
									COMMA316=(Token)match(input,COMMA,FOLLOW_COMMA_in_dictorsetmaker8148); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									COMMA316_tree = (PythonTree)adaptor.create(COMMA316);
									adaptor.addChild(root_0, COMMA316_tree);
									}

									}
									break;

							}

							}
							break;

					}

					}
					break;
				case 2 :
					// Python.g:2263:7: (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] ) ( comp_for[gens] | ( COMMA (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] ) )* ( COMMA )? )
					{
					root_0 = (PythonTree)adaptor.nil();


					// Python.g:2263:7: (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] )
					int alt164=2;
					int LA164_0 = input.LA(1);
					if ( (LA164_0==AWAIT||LA164_0==COMPLEX||(LA164_0 >= DOLLER && LA164_0 <= DOT)||LA164_0==FLOAT||(LA164_0 >= LAMBDA && LA164_0 <= LCURLY)||(LA164_0 >= LONGINT && LA164_0 <= MINUS)||LA164_0==NAME||LA164_0==NOT||LA164_0==PLUS||(LA164_0 >= STRING && LA164_0 <= TILDE)) ) {
						alt164=1;
					}
					else if ( (LA164_0==STAR) ) {
						alt164=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 164, 0, input);
						throw nvae;
					}

					switch (alt164) {
						case 1 :
							// Python.g:2263:8: k+= test[expr_contextType.Load]
							{
							pushFollow(FOLLOW_test_in_dictorsetmaker8162);
							k=test(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

							if (list_k==null) list_k=new ArrayList<Object>();
							list_k.add(k.getTree());
							}
							break;
						case 2 :
							// Python.g:2263:41: s+= star_expr[expr_contextType.Load]
							{
							pushFollow(FOLLOW_star_expr_in_dictorsetmaker8169);
							s=star_expr(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());

							if (list_s==null) list_s=new ArrayList<Object>();
							list_s.add(s.getTree());
							}
							break;

					}

					// Python.g:2264:10: ( comp_for[gens] | ( COMMA (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] ) )* ( COMMA )? )
					int alt168=2;
					int LA168_0 = input.LA(1);
					if ( (LA168_0==FOR) ) {
						alt168=1;
					}
					else if ( (LA168_0==COMMA||LA168_0==RCURLY) ) {
						alt168=2;
					}

					else {
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 168, 0, input);
						throw nvae;
					}

					switch (alt168) {
						case 1 :
							// Python.g:2264:12: comp_for[gens]
							{
							pushFollow(FOLLOW_comp_for_in_dictorsetmaker8184);
							comp_for317=comp_for(gens);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_for317.getTree());

							if ( state.backtracking==0 ) {
							               Collections.reverse(gens);
							               List<comprehension> c = gens;
							               expr e = actions.castExpr(list_k.get(0));
							               if (e instanceof Context) {
							                   ((Context)e).setContext(expr_contextType.Load);
							               }
							               etype = new SetComp(lcurly, actions.castExpr(list_k.get(0)), c);
							           }
							}
							break;
						case 2 :
							// Python.g:2274:12: ( COMMA (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] ) )* ( COMMA )?
							{
							// Python.g:2274:12: ( COMMA (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] ) )*
							loop166:
							while (true) {
								int alt166=2;
								int LA166_0 = input.LA(1);
								if ( (LA166_0==COMMA) ) {
									int LA166_1 = input.LA(2);
									if ( (LA166_1==AWAIT||LA166_1==COMPLEX||(LA166_1 >= DOLLER && LA166_1 <= DOT)||LA166_1==FLOAT||(LA166_1 >= LAMBDA && LA166_1 <= LCURLY)||(LA166_1 >= LONGINT && LA166_1 <= MINUS)||LA166_1==NAME||LA166_1==NOT||LA166_1==PLUS||LA166_1==STAR||(LA166_1 >= STRING && LA166_1 <= TILDE)) ) {
										alt166=1;
									}

								}

								switch (alt166) {
								case 1 :
									// Python.g:2274:13: COMMA (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] )
									{
									COMMA318=(Token)match(input,COMMA,FOLLOW_COMMA_in_dictorsetmaker8212); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									COMMA318_tree = (PythonTree)adaptor.create(COMMA318);
									adaptor.addChild(root_0, COMMA318_tree);
									}

									// Python.g:2274:19: (k+= test[expr_contextType.Load] |s+= star_expr[expr_contextType.Load] )
									int alt165=2;
									int LA165_0 = input.LA(1);
									if ( (LA165_0==AWAIT||LA165_0==COMPLEX||(LA165_0 >= DOLLER && LA165_0 <= DOT)||LA165_0==FLOAT||(LA165_0 >= LAMBDA && LA165_0 <= LCURLY)||(LA165_0 >= LONGINT && LA165_0 <= MINUS)||LA165_0==NAME||LA165_0==NOT||LA165_0==PLUS||(LA165_0 >= STRING && LA165_0 <= TILDE)) ) {
										alt165=1;
									}
									else if ( (LA165_0==STAR) ) {
										alt165=2;
									}

									else {
										if (state.backtracking>0) {state.failed=true; return retval;}
										NoViableAltException nvae =
											new NoViableAltException("", 165, 0, input);
										throw nvae;
									}

									switch (alt165) {
										case 1 :
											// Python.g:2274:20: k+= test[expr_contextType.Load]
											{
											pushFollow(FOLLOW_test_in_dictorsetmaker8217);
											k=test(expr_contextType.Load);
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

											if (list_k==null) list_k=new ArrayList<Object>();
											list_k.add(k.getTree());
											}
											break;
										case 2 :
											// Python.g:2274:53: s+= star_expr[expr_contextType.Load]
											{
											pushFollow(FOLLOW_star_expr_in_dictorsetmaker8224);
											s=star_expr(expr_contextType.Load);
											state._fsp--;
											if (state.failed) return retval;
											if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());

											if (list_s==null) list_s=new ArrayList<Object>();
											list_s.add(s.getTree());
											}
											break;

									}

									}
									break;

								default :
									break loop166;
								}
							}

							if ( state.backtracking==0 ) {
							               etype = new Set(lcurly, actions.castExprs(list_k, list_s));
							           }
							// Python.g:2278:10: ( COMMA )?
							int alt167=2;
							int LA167_0 = input.LA(1);
							if ( (LA167_0==COMMA) ) {
								alt167=1;
							}
							switch (alt167) {
								case 1 :
									// Python.g:2278:11: COMMA
									{
									COMMA319=(Token)match(input,COMMA,FOLLOW_COMMA_in_dictorsetmaker8253); if (state.failed) return retval;
									if ( state.backtracking==0 ) {
									COMMA319_tree = (PythonTree)adaptor.create(COMMA319);
									adaptor.addChild(root_0, COMMA319_tree);
									}

									}
									break;

							}

							}
							break;

					}

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (etype != null) {
			        retval.tree = etype;
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "dictorsetmaker"


	public static class classdef_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "classdef"
	// Python.g:2282:1: classdef : CLASS NAME ( LPAREN ( arglist )? RPAREN )? COLON suite[false] ;
	public final PythonParser.classdef_return classdef() throws RecognitionException {
		PythonParser.classdef_return retval = new PythonParser.classdef_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token CLASS320=null;
		Token NAME321=null;
		Token LPAREN322=null;
		Token RPAREN324=null;
		Token COLON325=null;
		ParserRuleReturnScope arglist323 =null;
		ParserRuleReturnScope suite326 =null;

		PythonTree CLASS320_tree=null;
		PythonTree NAME321_tree=null;
		PythonTree LPAREN322_tree=null;
		PythonTree RPAREN324_tree=null;
		PythonTree COLON325_tree=null;


		    stmt stype = null;

		try {
			// Python.g:2289:5: ( CLASS NAME ( LPAREN ( arglist )? RPAREN )? COLON suite[false] )
			// Python.g:2289:7: CLASS NAME ( LPAREN ( arglist )? RPAREN )? COLON suite[false]
			{
			root_0 = (PythonTree)adaptor.nil();


			CLASS320=(Token)match(input,CLASS,FOLLOW_CLASS_in_classdef8284); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			CLASS320_tree = (PythonTree)adaptor.create(CLASS320);
			adaptor.addChild(root_0, CLASS320_tree);
			}

			NAME321=(Token)match(input,NAME,FOLLOW_NAME_in_classdef8286); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			NAME321_tree = (PythonTree)adaptor.create(NAME321);
			adaptor.addChild(root_0, NAME321_tree);
			}

			// Python.g:2289:18: ( LPAREN ( arglist )? RPAREN )?
			int alt171=2;
			int LA171_0 = input.LA(1);
			if ( (LA171_0==LPAREN) ) {
				alt171=1;
			}
			switch (alt171) {
				case 1 :
					// Python.g:2289:19: LPAREN ( arglist )? RPAREN
					{
					LPAREN322=(Token)match(input,LPAREN,FOLLOW_LPAREN_in_classdef8289); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					LPAREN322_tree = (PythonTree)adaptor.create(LPAREN322);
					adaptor.addChild(root_0, LPAREN322_tree);
					}

					// Python.g:2289:26: ( arglist )?
					int alt170=2;
					int LA170_0 = input.LA(1);
					if ( (LA170_0==AWAIT||LA170_0==COMPLEX||(LA170_0 >= DOLLER && LA170_0 <= DOT)||LA170_0==DOUBLESTAR||LA170_0==FLOAT||(LA170_0 >= LAMBDA && LA170_0 <= LCURLY)||(LA170_0 >= LONGINT && LA170_0 <= MINUS)||LA170_0==NAME||LA170_0==NOT||LA170_0==PLUS||LA170_0==STAR||(LA170_0 >= STRING && LA170_0 <= TILDE)) ) {
						alt170=1;
					}
					switch (alt170) {
						case 1 :
							// Python.g:2289:26: arglist
							{
							pushFollow(FOLLOW_arglist_in_classdef8291);
							arglist323=arglist();
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, arglist323.getTree());

							}
							break;

					}

					RPAREN324=(Token)match(input,RPAREN,FOLLOW_RPAREN_in_classdef8294); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					RPAREN324_tree = (PythonTree)adaptor.create(RPAREN324);
					adaptor.addChild(root_0, RPAREN324_tree);
					}

					}
					break;

			}

			COLON325=(Token)match(input,COLON,FOLLOW_COLON_in_classdef8298); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			COLON325_tree = (PythonTree)adaptor.create(COLON325);
			adaptor.addChild(root_0, COLON325_tree);
			}

			pushFollow(FOLLOW_suite_in_classdef8300);
			suite326=suite(false);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, suite326.getTree());

			if ( state.backtracking==0 ) {
			          stype = actions.makeClass(CLASS320, NAME321,
			                                    (arglist323!=null?((PythonParser.arglist_return)arglist323).args:null),
			                                    (arglist323!=null?((PythonParser.arglist_return)arglist323).keywords:null),
			                                    (suite326!=null?((PythonParser.suite_return)suite326).stypes:null));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			   retval.tree = stype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "classdef"


	public static class arglist_return extends ParserRuleReturnScope {
		public List args;
		public List keywords;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "arglist"
	// Python.g:2299:1: arglist returns [List args, List keywords] : argument[args, keywords, gens, first] ( COMMA argument[args, keywords, gens, first] )* ( COMMA )? ;
	public final PythonParser.arglist_return arglist() throws RecognitionException {
		PythonParser.arglist_return retval = new PythonParser.arglist_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token COMMA328=null;
		Token COMMA330=null;
		ParserRuleReturnScope argument327 =null;
		ParserRuleReturnScope argument329 =null;

		PythonTree COMMA328_tree=null;
		PythonTree COMMA330_tree=null;


		    List args = new ArrayList();
		    List keywords = new ArrayList();
		    List gens = new ArrayList();
		    boolean first = true;

		try {
			// Python.g:2307:5: ( argument[args, keywords, gens, first] ( COMMA argument[args, keywords, gens, first] )* ( COMMA )? )
			// Python.g:2307:7: argument[args, keywords, gens, first] ( COMMA argument[args, keywords, gens, first] )* ( COMMA )?
			{
			root_0 = (PythonTree)adaptor.nil();


			pushFollow(FOLLOW_argument_in_arglist8340);
			argument327=argument(args, keywords, gens, first);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, argument327.getTree());

			// Python.g:2307:45: ( COMMA argument[args, keywords, gens, first] )*
			loop172:
			while (true) {
				int alt172=2;
				int LA172_0 = input.LA(1);
				if ( (LA172_0==COMMA) ) {
					int LA172_1 = input.LA(2);
					if ( (LA172_1==AWAIT||LA172_1==COMPLEX||(LA172_1 >= DOLLER && LA172_1 <= DOT)||LA172_1==DOUBLESTAR||LA172_1==FLOAT||(LA172_1 >= LAMBDA && LA172_1 <= LCURLY)||(LA172_1 >= LONGINT && LA172_1 <= MINUS)||LA172_1==NAME||LA172_1==NOT||LA172_1==PLUS||LA172_1==STAR||(LA172_1 >= STRING && LA172_1 <= TILDE)) ) {
						alt172=1;
					}

				}

				switch (alt172) {
				case 1 :
					// Python.g:2307:46: COMMA argument[args, keywords, gens, first]
					{
					COMMA328=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist8344); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA328_tree = (PythonTree)adaptor.create(COMMA328);
					adaptor.addChild(root_0, COMMA328_tree);
					}

					pushFollow(FOLLOW_argument_in_arglist8346);
					argument329=argument(args, keywords, gens, first);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, argument329.getTree());

					}
					break;

				default :
					break loop172;
				}
			}

			// Python.g:2307:92: ( COMMA )?
			int alt173=2;
			int LA173_0 = input.LA(1);
			if ( (LA173_0==COMMA) ) {
				alt173=1;
			}
			switch (alt173) {
				case 1 :
					// Python.g:2307:92: COMMA
					{
					COMMA330=(Token)match(input,COMMA,FOLLOW_COMMA_in_arglist8351); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					COMMA330_tree = (PythonTree)adaptor.create(COMMA330);
					adaptor.addChild(root_0, COMMA330_tree);
					}

					}
					break;

			}

			if ( state.backtracking==0 ) {
			        retval.args = args;
			        retval.keywords = keywords;
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "arglist"


	public static class argument_return extends ParserRuleReturnScope {
		public boolean genarg;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "argument"
	// Python.g:2318:1: argument[List args, List keywords, List gens, boolean first] returns [boolean genarg] : (t1= test[expr_contextType.Load] ( comp_for[$gens] | ASSIGN t2= test[expr_contextType.Load] |) | STAR s= test[expr_contextType.Load] | DOUBLESTAR k= test[expr_contextType.Load] );
	public final PythonParser.argument_return argument(List args, List keywords, List gens, boolean first) throws RecognitionException {
		PythonParser.argument_return retval = new PythonParser.argument_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token ASSIGN332=null;
		Token STAR333=null;
		Token DOUBLESTAR334=null;
		ParserRuleReturnScope t1 =null;
		ParserRuleReturnScope t2 =null;
		ParserRuleReturnScope s =null;
		ParserRuleReturnScope k =null;
		ParserRuleReturnScope comp_for331 =null;

		PythonTree ASSIGN332_tree=null;
		PythonTree STAR333_tree=null;
		PythonTree DOUBLESTAR334_tree=null;

		try {
			// Python.g:2320:5: (t1= test[expr_contextType.Load] ( comp_for[$gens] | ASSIGN t2= test[expr_contextType.Load] |) | STAR s= test[expr_contextType.Load] | DOUBLESTAR k= test[expr_contextType.Load] )
			int alt175=3;
			switch ( input.LA(1) ) {
			case AWAIT:
			case COMPLEX:
			case DOLLER:
			case DOT:
			case FLOAT:
			case LAMBDA:
			case LBRACK:
			case LCURLY:
			case LONGINT:
			case LPAREN:
			case MINUS:
			case NAME:
			case NOT:
			case PLUS:
			case STRING:
			case TILDE:
				{
				alt175=1;
				}
				break;
			case STAR:
				{
				alt175=2;
				}
				break;
			case DOUBLESTAR:
				{
				alt175=3;
				}
				break;
			default:
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 175, 0, input);
				throw nvae;
			}
			switch (alt175) {
				case 1 :
					// Python.g:2320:7: t1= test[expr_contextType.Load] ( comp_for[$gens] | ASSIGN t2= test[expr_contextType.Load] |)
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_test_in_argument8393);
					t1=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, t1.getTree());

					// Python.g:2321:7: ( comp_for[$gens] | ASSIGN t2= test[expr_contextType.Load] |)
					int alt174=3;
					switch ( input.LA(1) ) {
					case FOR:
						{
						alt174=1;
						}
						break;
					case ASSIGN:
						{
						alt174=2;
						}
						break;
					case COMMA:
					case RPAREN:
						{
						alt174=3;
						}
						break;
					default:
						if (state.backtracking>0) {state.failed=true; return retval;}
						NoViableAltException nvae =
							new NoViableAltException("", 174, 0, input);
						throw nvae;
					}
					switch (alt174) {
						case 1 :
							// Python.g:2321:9: comp_for[$gens]
							{
							pushFollow(FOLLOW_comp_for_in_argument8404);
							comp_for331=comp_for(gens);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_for331.getTree());

							if ( state.backtracking==0 ) {
							            if (!first) {
							                actions.errorGenExpNotSoleArg((comp_for331!=null?((PythonTree)comp_for331.getTree()):null));
							            }
							            first = false;
							            retval.genarg = true;
							            Collections.reverse(gens);
							            List<comprehension> c = gens;
							            args.add(new GeneratorExp((t1!=null?(t1.start):null), actions.castExpr((t1!=null?((PythonTree)t1.getTree()):null)), c));
							        }
							}
							break;
						case 2 :
							// Python.g:2332:9: ASSIGN t2= test[expr_contextType.Load]
							{
							ASSIGN332=(Token)match(input,ASSIGN,FOLLOW_ASSIGN_in_argument8425); if (state.failed) return retval;
							if ( state.backtracking==0 ) {
							ASSIGN332_tree = (PythonTree)adaptor.create(ASSIGN332);
							adaptor.addChild(root_0, ASSIGN332_tree);
							}

							pushFollow(FOLLOW_test_in_argument8429);
							t2=test(expr_contextType.Load);
							state._fsp--;
							if (state.failed) return retval;
							if ( state.backtracking==0 ) adaptor.addChild(root_0, t2.getTree());

							if ( state.backtracking==0 ) {
							            expr newkey = actions.castExpr((t1!=null?((PythonTree)t1.getTree()):null));
							            //Loop through all current keys and fail on duplicate.
							            for(Object o: keywords) {
							                List list = (List)o;
							                Object oldkey = list.get(0);
							                if (oldkey instanceof Name && newkey instanceof Name) {
							                    if (((Name)oldkey).getId().equals(((Name)newkey).getId())) {
							                        errorHandler.error("keyword arguments repeated", (t1!=null?((PythonTree)t1.getTree()):null));
							                    }
							                }
							            }
							            List<expr> exprs = new ArrayList<expr>();
							            exprs.add(newkey);
							            exprs.add(actions.castExpr((t2!=null?((PythonTree)t2.getTree()):null)));
							            keywords.add(exprs);

							        }
							}
							break;
						case 3 :
							// Python.g:2352:9: 
							{
							if ( state.backtracking==0 ) {
							            if (!keywords.isEmpty()) {
							              actions.errorPositionalArgFollowsKeywordArg((t1!=null?((PythonTree)t1.getTree()):null));
							            }
							            args.add((t1!=null?((PythonTree)t1.getTree()):null));
							        }
							}
							break;

					}

					}
					break;
				case 2 :
					// Python.g:2359:7: STAR s= test[expr_contextType.Load]
					{
					root_0 = (PythonTree)adaptor.nil();


					STAR333=(Token)match(input,STAR,FOLLOW_STAR_in_argument8474); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					STAR333_tree = (PythonTree)adaptor.create(STAR333);
					adaptor.addChild(root_0, STAR333_tree);
					}

					pushFollow(FOLLOW_test_in_argument8478);
					s=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, s.getTree());

					if ( state.backtracking==0 ) {
					            expr etype = new Starred(STAR333, actions.castExpr((s!=null?((PythonTree)s.getTree()):null)), expr_contextType.Load);
					            args.add(etype);
					        }
					}
					break;
				case 3 :
					// Python.g:2365:7: DOUBLESTAR k= test[expr_contextType.Load]
					{
					root_0 = (PythonTree)adaptor.nil();


					DOUBLESTAR334=(Token)match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_argument8498); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					DOUBLESTAR334_tree = (PythonTree)adaptor.create(DOUBLESTAR334);
					adaptor.addChild(root_0, DOUBLESTAR334_tree);
					}

					pushFollow(FOLLOW_test_in_argument8502);
					k=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, k.getTree());

					if ( state.backtracking==0 ) {
					          List<expr> exprs = new ArrayList<>();
					          exprs.add(null);
					          exprs.add(actions.castExpr((k!=null?((PythonTree)k.getTree()):null)));
					          keywords.add(exprs);
					      }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "argument"


	public static class comp_iter_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "comp_iter"
	// Python.g:2375:1: comp_iter[List gens, List ifs] : ( comp_for[gens] | comp_if[gens, ifs] );
	public final PythonParser.comp_iter_return comp_iter(List gens, List ifs) throws RecognitionException {
		PythonParser.comp_iter_return retval = new PythonParser.comp_iter_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		ParserRuleReturnScope comp_for335 =null;
		ParserRuleReturnScope comp_if336 =null;


		try {
			// Python.g:2376:5: ( comp_for[gens] | comp_if[gens, ifs] )
			int alt176=2;
			int LA176_0 = input.LA(1);
			if ( (LA176_0==FOR) ) {
				alt176=1;
			}
			else if ( (LA176_0==IF) ) {
				alt176=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 176, 0, input);
				throw nvae;
			}

			switch (alt176) {
				case 1 :
					// Python.g:2376:7: comp_for[gens]
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_comp_for_in_comp_iter8531);
					comp_for335=comp_for(gens);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_for335.getTree());

					}
					break;
				case 2 :
					// Python.g:2377:7: comp_if[gens, ifs]
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_comp_if_in_comp_iter8540);
					comp_if336=comp_if(gens, ifs);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_if336.getTree());

					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comp_iter"


	public static class comp_for_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "comp_for"
	// Python.g:2381:1: comp_for[List gens] : FOR exprlist[expr_contextType.Store] IN or_test[expr_contextType.Load] ( comp_iter[gens, ifs] )? ;
	public final PythonParser.comp_for_return comp_for(List gens) throws RecognitionException {
		PythonParser.comp_for_return retval = new PythonParser.comp_for_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token FOR337=null;
		Token IN339=null;
		ParserRuleReturnScope exprlist338 =null;
		ParserRuleReturnScope or_test340 =null;
		ParserRuleReturnScope comp_iter341 =null;

		PythonTree FOR337_tree=null;
		PythonTree IN339_tree=null;


		    List ifs = new ArrayList();

		try {
			// Python.g:2385:5: ( FOR exprlist[expr_contextType.Store] IN or_test[expr_contextType.Load] ( comp_iter[gens, ifs] )? )
			// Python.g:2385:7: FOR exprlist[expr_contextType.Store] IN or_test[expr_contextType.Load] ( comp_iter[gens, ifs] )?
			{
			root_0 = (PythonTree)adaptor.nil();


			FOR337=(Token)match(input,FOR,FOLLOW_FOR_in_comp_for8566); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			FOR337_tree = (PythonTree)adaptor.create(FOR337);
			adaptor.addChild(root_0, FOR337_tree);
			}

			pushFollow(FOLLOW_exprlist_in_comp_for8568);
			exprlist338=exprlist(expr_contextType.Store);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, exprlist338.getTree());

			IN339=(Token)match(input,IN,FOLLOW_IN_in_comp_for8571); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			IN339_tree = (PythonTree)adaptor.create(IN339);
			adaptor.addChild(root_0, IN339_tree);
			}

			pushFollow(FOLLOW_or_test_in_comp_for8573);
			or_test340=or_test(expr_contextType.Load);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, or_test340.getTree());

			// Python.g:2385:78: ( comp_iter[gens, ifs] )?
			int alt177=2;
			int LA177_0 = input.LA(1);
			if ( (LA177_0==FOR||LA177_0==IF) ) {
				alt177=1;
			}
			switch (alt177) {
				case 1 :
					// Python.g:2385:78: comp_iter[gens, ifs]
					{
					pushFollow(FOLLOW_comp_iter_in_comp_for8576);
					comp_iter341=comp_iter(gens, ifs);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_iter341.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          Collections.reverse(ifs);
			          gens.add(new comprehension(FOR337, (exprlist338!=null?((PythonParser.exprlist_return)exprlist338).etype:null), actions.castExpr((or_test340!=null?((PythonTree)or_test340.getTree()):null)), ifs));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comp_for"


	public static class comp_if_return extends ParserRuleReturnScope {
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "comp_if"
	// Python.g:2393:1: comp_if[List gens, List ifs] : IF test_nocond[expr_contextType.Load] ( comp_iter[gens, ifs] )? ;
	public final PythonParser.comp_if_return comp_if(List gens, List ifs) throws RecognitionException {
		PythonParser.comp_if_return retval = new PythonParser.comp_if_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token IF342=null;
		ParserRuleReturnScope test_nocond343 =null;
		ParserRuleReturnScope comp_iter344 =null;

		PythonTree IF342_tree=null;

		try {
			// Python.g:2394:5: ( IF test_nocond[expr_contextType.Load] ( comp_iter[gens, ifs] )? )
			// Python.g:2394:7: IF test_nocond[expr_contextType.Load] ( comp_iter[gens, ifs] )?
			{
			root_0 = (PythonTree)adaptor.nil();


			IF342=(Token)match(input,IF,FOLLOW_IF_in_comp_if8605); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			IF342_tree = (PythonTree)adaptor.create(IF342);
			adaptor.addChild(root_0, IF342_tree);
			}

			pushFollow(FOLLOW_test_nocond_in_comp_if8607);
			test_nocond343=test_nocond(expr_contextType.Load);
			state._fsp--;
			if (state.failed) return retval;
			if ( state.backtracking==0 ) adaptor.addChild(root_0, test_nocond343.getTree());

			// Python.g:2394:45: ( comp_iter[gens, ifs] )?
			int alt178=2;
			int LA178_0 = input.LA(1);
			if ( (LA178_0==FOR||LA178_0==IF) ) {
				alt178=1;
			}
			switch (alt178) {
				case 1 :
					// Python.g:2394:45: comp_iter[gens, ifs]
					{
					pushFollow(FOLLOW_comp_iter_in_comp_if8610);
					comp_iter344=comp_iter(gens, ifs);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, comp_iter344.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			        ifs.add(actions.castExpr((test_nocond343!=null?((PythonTree)test_nocond343.getTree()):null)));
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "comp_if"


	public static class yield_expr_return extends ParserRuleReturnScope {
		public expr etype;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "yield_expr"
	// Python.g:2401:1: yield_expr returns [expr etype] : YIELD ( yield_arg )? ;
	public final PythonParser.yield_expr_return yield_expr() throws RecognitionException {
		PythonParser.yield_expr_return retval = new PythonParser.yield_expr_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token YIELD345=null;
		ParserRuleReturnScope yield_arg346 =null;

		PythonTree YIELD345_tree=null;

		try {
			// Python.g:2407:5: ( YIELD ( yield_arg )? )
			// Python.g:2407:7: YIELD ( yield_arg )?
			{
			root_0 = (PythonTree)adaptor.nil();


			YIELD345=(Token)match(input,YIELD,FOLLOW_YIELD_in_yield_expr8651); if (state.failed) return retval;
			if ( state.backtracking==0 ) {
			YIELD345_tree = (PythonTree)adaptor.create(YIELD345);
			adaptor.addChild(root_0, YIELD345_tree);
			}

			// Python.g:2407:13: ( yield_arg )?
			int alt179=2;
			int LA179_0 = input.LA(1);
			if ( (LA179_0==AWAIT||LA179_0==COMPLEX||(LA179_0 >= DOLLER && LA179_0 <= DOT)||LA179_0==FLOAT||LA179_0==FROM||(LA179_0 >= LAMBDA && LA179_0 <= LCURLY)||(LA179_0 >= LONGINT && LA179_0 <= MINUS)||LA179_0==NAME||LA179_0==NOT||LA179_0==PLUS||(LA179_0 >= STRING && LA179_0 <= TILDE)) ) {
				alt179=1;
			}
			switch (alt179) {
				case 1 :
					// Python.g:2407:13: yield_arg
					{
					pushFollow(FOLLOW_yield_arg_in_yield_expr8653);
					yield_arg346=yield_arg();
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, yield_arg346.getTree());

					}
					break;

			}

			if ( state.backtracking==0 ) {
			          if (!(yield_arg346!=null?((PythonParser.yield_arg_return)yield_arg346).isYieldFrom:false)) {
			              retval.etype = new Yield(YIELD345, actions.castExpr((yield_arg346!=null?((PythonTree)yield_arg346.getTree()):null)));
			          } else {
			              retval.etype = new YieldFrom(YIELD345, actions.castExpr((yield_arg346!=null?((PythonTree)yield_arg346.getTree()):null)));
			          }
			      }
			}

			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    //needed for y2+=yield_expr
			    retval.tree = retval.etype;
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "yield_expr"


	public static class yield_arg_return extends ParserRuleReturnScope {
		public expr etype;
		public boolean isYieldFrom;
		PythonTree tree;
		@Override
		public PythonTree getTree() { return tree; }
	};


	// $ANTLR start "yield_arg"
	// Python.g:2418:1: yield_arg returns [expr etype, boolean isYieldFrom] : ( FROM test[expr_contextType.Load] | testlist[expr_contextType.Load] );
	public final PythonParser.yield_arg_return yield_arg() throws RecognitionException {
		PythonParser.yield_arg_return retval = new PythonParser.yield_arg_return();
		retval.start = input.LT(1);

		PythonTree root_0 = null;

		Token FROM347=null;
		ParserRuleReturnScope test348 =null;
		ParserRuleReturnScope testlist349 =null;

		PythonTree FROM347_tree=null;


		    expr etype = null;

		try {
			// Python.g:2428:5: ( FROM test[expr_contextType.Load] | testlist[expr_contextType.Load] )
			int alt180=2;
			int LA180_0 = input.LA(1);
			if ( (LA180_0==FROM) ) {
				alt180=1;
			}
			else if ( (LA180_0==AWAIT||LA180_0==COMPLEX||(LA180_0 >= DOLLER && LA180_0 <= DOT)||LA180_0==FLOAT||(LA180_0 >= LAMBDA && LA180_0 <= LCURLY)||(LA180_0 >= LONGINT && LA180_0 <= MINUS)||LA180_0==NAME||LA180_0==NOT||LA180_0==PLUS||(LA180_0 >= STRING && LA180_0 <= TILDE)) ) {
				alt180=2;
			}

			else {
				if (state.backtracking>0) {state.failed=true; return retval;}
				NoViableAltException nvae =
					new NoViableAltException("", 180, 0, input);
				throw nvae;
			}

			switch (alt180) {
				case 1 :
					// Python.g:2428:7: FROM test[expr_contextType.Load]
					{
					root_0 = (PythonTree)adaptor.nil();


					FROM347=(Token)match(input,FROM,FOLLOW_FROM_in_yield_arg8698); if (state.failed) return retval;
					if ( state.backtracking==0 ) {
					FROM347_tree = (PythonTree)adaptor.create(FROM347);
					adaptor.addChild(root_0, FROM347_tree);
					}

					pushFollow(FOLLOW_test_in_yield_arg8700);
					test348=test(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, test348.getTree());

					if ( state.backtracking==0 ) {
					        etype = actions.castExpr((test348!=null?((PythonTree)test348.getTree()):null));
					        retval.isYieldFrom = true;
					    }
					}
					break;
				case 2 :
					// Python.g:2432:7: testlist[expr_contextType.Load]
					{
					root_0 = (PythonTree)adaptor.nil();


					pushFollow(FOLLOW_testlist_in_yield_arg8711);
					testlist349=testlist(expr_contextType.Load);
					state._fsp--;
					if (state.failed) return retval;
					if ( state.backtracking==0 ) adaptor.addChild(root_0, testlist349.getTree());

					if ( state.backtracking==0 ) {
					        etype = actions.castExpr((testlist349!=null?((PythonTree)testlist349.getTree()):null));
					        retval.isYieldFrom = false;
					    }
					}
					break;

			}
			retval.stop = input.LT(-1);

			if ( state.backtracking==0 ) {
			retval.tree = (PythonTree)adaptor.rulePostProcessing(root_0);
			adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
			}
			if ( state.backtracking==0 ) {
			    if (etype != null) {
			        retval.tree = etype;
			    }
			}
		}

		catch (RecognitionException re) {
		    reportError(re);
		    errorHandler.recover(this, input,re);
		    retval.tree = (PythonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);
		}

		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "yield_arg"

	// $ANTLR start synpred1_Python
	public final void synpred1_Python_fragment() throws RecognitionException {
		// Python.g:785:8: ( testlist_star_expr[null] augassign )
		// Python.g:785:9: testlist_star_expr[null] augassign
		{
		pushFollow(FOLLOW_testlist_star_expr_in_synpred1_Python2177);
		testlist_star_expr(null);
		state._fsp--;
		if (state.failed) return;

		pushFollow(FOLLOW_augassign_in_synpred1_Python2180);
		augassign();
		state._fsp--;
		if (state.failed) return;

		}

	}
	// $ANTLR end synpred1_Python

	// $ANTLR start synpred2_Python
	public final void synpred2_Python_fragment() throws RecognitionException {
		// Python.g:799:7: ( testlist_star_expr[null] ':' test[null] ASSIGN )
		// Python.g:799:8: testlist_star_expr[null] ':' test[null] ASSIGN
		{
		pushFollow(FOLLOW_testlist_star_expr_in_synpred2_Python2296);
		testlist_star_expr(null);
		state._fsp--;
		if (state.failed) return;

		match(input,COLON,FOLLOW_COLON_in_synpred2_Python2299); if (state.failed) return;

		pushFollow(FOLLOW_test_in_synpred2_Python2301);
		test(null);
		state._fsp--;
		if (state.failed) return;

		match(input,ASSIGN,FOLLOW_ASSIGN_in_synpred2_Python2304); if (state.failed) return;

		}

	}
	// $ANTLR end synpred2_Python

	// $ANTLR start synpred3_Python
	public final void synpred3_Python_fragment() throws RecognitionException {
		// Python.g:814:7: ( testlist_star_expr[null] ASSIGN )
		// Python.g:814:8: testlist_star_expr[null] ASSIGN
		{
		pushFollow(FOLLOW_testlist_star_expr_in_synpred3_Python2443);
		testlist_star_expr(null);
		state._fsp--;
		if (state.failed) return;

		match(input,ASSIGN,FOLLOW_ASSIGN_in_synpred3_Python2446); if (state.failed) return;

		}

	}
	// $ANTLR end synpred3_Python

	// $ANTLR start synpred4_Python
	public final void synpred4_Python_fragment() throws RecognitionException {
		// Python.g:847:7: ( ( test[null] | star_expr[null] ) COMMA )
		// Python.g:847:8: ( test[null] | star_expr[null] ) COMMA
		{
		// Python.g:847:8: ( test[null] | star_expr[null] )
		int alt181=2;
		int LA181_0 = input.LA(1);
		if ( (LA181_0==AWAIT||LA181_0==COMPLEX||(LA181_0 >= DOLLER && LA181_0 <= DOT)||LA181_0==FLOAT||(LA181_0 >= LAMBDA && LA181_0 <= LCURLY)||(LA181_0 >= LONGINT && LA181_0 <= MINUS)||LA181_0==NAME||LA181_0==NOT||LA181_0==PLUS||(LA181_0 >= STRING && LA181_0 <= TILDE)) ) {
			alt181=1;
		}
		else if ( (LA181_0==STAR) ) {
			alt181=2;
		}

		else {
			if (state.backtracking>0) {state.failed=true; return;}
			NoViableAltException nvae =
				new NoViableAltException("", 181, 0, input);
			throw nvae;
		}

		switch (alt181) {
			case 1 :
				// Python.g:847:9: test[null]
				{
				pushFollow(FOLLOW_test_in_synpred4_Python2628);
				test(null);
				state._fsp--;
				if (state.failed) return;

				}
				break;
			case 2 :
				// Python.g:847:22: star_expr[null]
				{
				pushFollow(FOLLOW_star_expr_in_synpred4_Python2633);
				star_expr(null);
				state._fsp--;
				if (state.failed) return;

				}
				break;

		}

		match(input,COMMA,FOLLOW_COMMA_in_synpred4_Python2637); if (state.failed) return;

		}

	}
	// $ANTLR end synpred4_Python

	// $ANTLR start synpred5_Python
	public final void synpred5_Python_fragment() throws RecognitionException {
		// Python.g:1445:9: ( IF or_test[null] ORELSE )
		// Python.g:1445:10: IF or_test[null] ORELSE
		{
		match(input,IF,FOLLOW_IF_in_synpred5_Python4845); if (state.failed) return;

		pushFollow(FOLLOW_or_test_in_synpred5_Python4847);
		or_test(null);
		state._fsp--;
		if (state.failed) return;

		match(input,ORELSE,FOLLOW_ORELSE_in_synpred5_Python4850); if (state.failed) return;

		}

	}
	// $ANTLR end synpred5_Python

	// $ANTLR start synpred6_Python
	public final void synpred6_Python_fragment() throws RecognitionException {
		// Python.g:1466:9: ( IF or_test[null] ORELSE )
		// Python.g:1466:10: IF or_test[null] ORELSE
		{
		match(input,IF,FOLLOW_IF_in_synpred6_Python4954); if (state.failed) return;

		pushFollow(FOLLOW_or_test_in_synpred6_Python4956);
		or_test(null);
		state._fsp--;
		if (state.failed) return;

		match(input,ORELSE,FOLLOW_ORELSE_in_synpred6_Python4959); if (state.failed) return;

		}

	}
	// $ANTLR end synpred6_Python

	// $ANTLR start synpred7_Python
	public final void synpred7_Python_fragment() throws RecognitionException {
		// Python.g:2160:7: ( test[null] COLON )
		// Python.g:2160:8: test[null] COLON
		{
		pushFollow(FOLLOW_test_in_synpred7_Python7583);
		test(null);
		state._fsp--;
		if (state.failed) return;

		match(input,COLON,FOLLOW_COLON_in_synpred7_Python7586); if (state.failed) return;

		}

	}
	// $ANTLR end synpred7_Python

	// $ANTLR start synpred8_Python
	public final void synpred8_Python_fragment() throws RecognitionException {
		// Python.g:2165:7: ( COLON )
		// Python.g:2165:8: COLON
		{
		match(input,COLON,FOLLOW_COLON_in_synpred8_Python7634); if (state.failed) return;

		}

	}
	// $ANTLR end synpred8_Python

	// $ANTLR start synpred9_Python
	public final void synpred9_Python_fragment() throws RecognitionException {
		// Python.g:2199:7: ( expr[null] COMMA )
		// Python.g:2199:8: expr[null] COMMA
		{
		pushFollow(FOLLOW_expr_in_synpred9_Python7779);
		expr(null);
		state._fsp--;
		if (state.failed) return;

		match(input,COMMA,FOLLOW_COMMA_in_synpred9_Python7782); if (state.failed) return;

		}

	}
	// $ANTLR end synpred9_Python

	// $ANTLR start synpred10_Python
	public final void synpred10_Python_fragment() throws RecognitionException {
		// Python.g:2229:7: ( test[null] COMMA )
		// Python.g:2229:8: test[null] COMMA
		{
		pushFollow(FOLLOW_test_in_synpred10_Python7930);
		test(null);
		state._fsp--;
		if (state.failed) return;

		match(input,COMMA,FOLLOW_COMMA_in_synpred10_Python7933); if (state.failed) return;

		}

	}
	// $ANTLR end synpred10_Python

	// $ANTLR start synpred11_Python
	public final void synpred11_Python_fragment() throws RecognitionException {
		// Python.g:2251:7: ( test[expr_contextType.Load] COLON | DOUBLESTAR )
		int alt182=2;
		int LA182_0 = input.LA(1);
		if ( (LA182_0==AWAIT||LA182_0==COMPLEX||(LA182_0 >= DOLLER && LA182_0 <= DOT)||LA182_0==FLOAT||(LA182_0 >= LAMBDA && LA182_0 <= LCURLY)||(LA182_0 >= LONGINT && LA182_0 <= MINUS)||LA182_0==NAME||LA182_0==NOT||LA182_0==PLUS||(LA182_0 >= STRING && LA182_0 <= TILDE)) ) {
			alt182=1;
		}
		else if ( (LA182_0==DOUBLESTAR) ) {
			alt182=2;
		}

		else {
			if (state.backtracking>0) {state.failed=true; return;}
			NoViableAltException nvae =
				new NoViableAltException("", 182, 0, input);
			throw nvae;
		}

		switch (alt182) {
			case 1 :
				// Python.g:2251:8: test[expr_contextType.Load] COLON
				{
				pushFollow(FOLLOW_test_in_synpred11_Python8017);
				test(expr_contextType.Load);
				state._fsp--;
				if (state.failed) return;

				match(input,COLON,FOLLOW_COLON_in_synpred11_Python8020); if (state.failed) return;

				}
				break;
			case 2 :
				// Python.g:2251:44: DOUBLESTAR
				{
				match(input,DOUBLESTAR,FOLLOW_DOUBLESTAR_in_synpred11_Python8024); if (state.failed) return;

				}
				break;

		}
	}
	// $ANTLR end synpred11_Python

	// Delegated rules

	public final boolean synpred1_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred1_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred3_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred3_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred10_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred10_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred11_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred11_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred2_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred2_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred4_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred4_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred5_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred5_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred6_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred6_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred7_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred7_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred8_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred8_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}
	public final boolean synpred9_Python() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred9_Python_fragment(); // can never throw exception
		} catch (RecognitionException re) {
			System.err.println("impossible: "+re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed=false;
		return success;
	}


	protected DFA70 dfa70 = new DFA70(this);
	static final String DFA70_eotS =
		"\4\uffff";
	static final String DFA70_eofS =
		"\4\uffff";
	static final String DFA70_minS =
		"\2\40\2\uffff";
	static final String DFA70_maxS =
		"\2\105\2\uffff";
	static final String DFA70_acceptS =
		"\2\uffff\1\1\1\2";
	static final String DFA70_specialS =
		"\4\uffff}>";
	static final String[] DFA70_transitionS = {
			"\1\1\44\uffff\1\2",
			"\1\1\22\uffff\1\3\21\uffff\1\2",
			"",
			""
	};

	static final short[] DFA70_eot = DFA.unpackEncodedString(DFA70_eotS);
	static final short[] DFA70_eof = DFA.unpackEncodedString(DFA70_eofS);
	static final char[] DFA70_min = DFA.unpackEncodedStringToUnsignedChars(DFA70_minS);
	static final char[] DFA70_max = DFA.unpackEncodedStringToUnsignedChars(DFA70_maxS);
	static final short[] DFA70_accept = DFA.unpackEncodedString(DFA70_acceptS);
	static final short[] DFA70_special = DFA.unpackEncodedString(DFA70_specialS);
	static final short[][] DFA70_transition;

	static {
		int numStates = DFA70_transitionS.length;
		DFA70_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA70_transition[i] = DFA.unpackEncodedString(DFA70_transitionS[i]);
		}
	}

	protected class DFA70 extends DFA {

		public DFA70(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 70;
			this.eot = DFA70_eot;
			this.eof = DFA70_eof;
			this.min = DFA70_min;
			this.max = DFA70_max;
			this.accept = DFA70_accept;
			this.special = DFA70_special;
			this.transition = DFA70_transition;
		}
		@Override
		public String getDescription() {
			return "1066:12: ( (d+= DOT )* dotted_name | (d+= DOT )+ )";
		}
	}

	public static final BitSet FOLLOW_NEWLINE_in_single_input118 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_EOF_in_single_input121 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simple_stmt_in_single_input137 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_NEWLINE_in_single_input139 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_EOF_in_single_input142 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_compound_stmt_in_single_input158 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_NEWLINE_in_single_input160 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_EOF_in_single_input163 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEWLINE_in_file_input215 = new BitSet(new long[]{0x070CF001B312B400L,0x00000164681291EEL});
	public static final BitSet FOLLOW_stmt_in_file_input225 = new BitSet(new long[]{0x070CF001B312B400L,0x00000164681291EEL});
	public static final BitSet FOLLOW_EOF_in_file_input244 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEADING_WS_in_eval_input298 = new BitSet(new long[]{0x0700100181008000L,0x000000006000816EL});
	public static final BitSet FOLLOW_NEWLINE_in_eval_input302 = new BitSet(new long[]{0x0700100181008000L,0x000000006000816EL});
	public static final BitSet FOLLOW_testlist_in_eval_input306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_NEWLINE_in_eval_input310 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_EOF_in_eval_input314 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NAME_in_dotted_attr366 = new BitSet(new long[]{0x0000000100000002L});
	public static final BitSet FOLLOW_DOT_in_dotted_attr377 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_dotted_attr381 = new BitSet(new long[]{0x0000000100000002L});
	public static final BitSet FOLLOW_NAME_in_name_or_print445 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ASYNC_in_name_or_print455 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AWAIT_in_name_or_print465 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AT_in_decorator783 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_dotted_attr_in_decorator785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000044L});
	public static final BitSet FOLLOW_LPAREN_in_decorator793 = new BitSet(new long[]{0x0700100981008000L,0x000000006880812EL});
	public static final BitSet FOLLOW_arglist_in_decorator803 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RPAREN_in_decorator847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_NEWLINE_in_decorator869 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_decorator_in_decorators897 = new BitSet(new long[]{0x0000000000002002L});
	public static final BitSet FOLLOW_ASYNC_in_async_funcdef935 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_funcdef_in_async_funcdef937 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_decorators_in_decorated972 = new BitSet(new long[]{0x0000000010101000L});
	public static final BitSet FOLLOW_classdef_in_decorated984 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_funcdef_in_decorated1006 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_async_funcdef_in_decorated1028 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DEF_in_funcdef1077 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000020L});
	public static final BitSet FOLLOW_name_or_print_in_funcdef1079 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
	public static final BitSet FOLLOW_parameters_in_funcdef1081 = new BitSet(new long[]{0x0000000000200100L});
	public static final BitSet FOLLOW_ARROW_in_funcdef1084 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_funcdef1086 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_funcdef1091 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_funcdef1093 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_parameters1126 = new BitSet(new long[]{0x0000000800009000L,0x0000000008800020L});
	public static final BitSet FOLLOW_typedargslist_in_parameters1135 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RPAREN_in_parameters1179 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tfpdef_in_tdefparameter1212 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_ASSIGN_in_tdefparameter1215 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_tdefparameter1217 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vfpdef_in_vdefparameter1261 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_ASSIGN_in_vdefparameter1265 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_vdefparameter1267 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tdefparameter_in_typedargslist1315 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_typedargslist1326 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tdefparameter_in_typedargslist1330 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_typedargslist1342 = new BitSet(new long[]{0x0000000800000002L,0x0000000008000000L});
	public static final BitSet FOLLOW_STAR_in_typedargslist1355 = new BitSet(new long[]{0x0000000000409002L,0x0000000000000020L});
	public static final BitSet FOLLOW_tfpdef_in_typedargslist1360 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_typedargslist1365 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tdefparameter_in_typedargslist1369 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_typedargslist1380 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tdefparameter_in_typedargslist1384 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_typedargslist1392 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_typedargslist1394 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tfpdef_in_typedargslist1398 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_typedargslist1414 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tfpdef_in_typedargslist1418 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STAR_in_typedargslist1456 = new BitSet(new long[]{0x0000000000409002L,0x0000000000000020L});
	public static final BitSet FOLLOW_tfpdef_in_typedargslist1461 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_typedargslist1466 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tdefparameter_in_typedargslist1470 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_typedargslist1481 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tdefparameter_in_typedargslist1485 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_typedargslist1493 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_typedargslist1495 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tfpdef_in_typedargslist1499 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_typedargslist1517 = new BitSet(new long[]{0x0000000000009000L,0x0000000000000020L});
	public static final BitSet FOLLOW_tfpdef_in_typedargslist1521 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_name_or_print_in_tfpdef1561 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COLON_in_tfpdef1564 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_tfpdef1566 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_vdefparameter_in_varargslist1615 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_varargslist1626 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_vdefparameter_in_varargslist1630 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_varargslist1642 = new BitSet(new long[]{0x0000000800000002L,0x0000000008000000L});
	public static final BitSet FOLLOW_STAR_in_varargslist1655 = new BitSet(new long[]{0x0000000000400002L,0x0000000000000020L});
	public static final BitSet FOLLOW_vfpdef_in_varargslist1660 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_varargslist1666 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_vdefparameter_in_varargslist1670 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_varargslist1681 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_vdefparameter_in_varargslist1685 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_varargslist1693 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1695 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_vfpdef_in_varargslist1699 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1716 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_vfpdef_in_varargslist1720 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STAR_in_varargslist1759 = new BitSet(new long[]{0x0000000000400002L,0x0000000000000020L});
	public static final BitSet FOLLOW_vfpdef_in_varargslist1764 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_varargslist1770 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_vdefparameter_in_varargslist1774 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_varargslist1785 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_vdefparameter_in_varargslist1789 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_varargslist1797 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1799 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_vfpdef_in_varargslist1803 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_varargslist1822 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_vfpdef_in_varargslist1826 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NAME_in_vfpdef1864 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simple_stmt_in_stmt1898 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_compound_stmt_in_stmt1914 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_small_stmt_in_simple_stmt1950 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000040L});
	public static final BitSet FOLLOW_SEMI_in_simple_stmt1960 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291AEL});
	public static final BitSet FOLLOW_small_stmt_in_simple_stmt1964 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000040L});
	public static final BitSet FOLLOW_SEMI_in_simple_stmt1969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
	public static final BitSet FOLLOW_NEWLINE_in_simple_stmt1973 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_stmt_in_small_stmt1996 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_del_stmt_in_small_stmt2011 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_pass_stmt_in_small_stmt2026 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_flow_stmt_in_small_stmt2041 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_import_stmt_in_small_stmt2056 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_global_stmt_in_small_stmt2071 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_nonlocal_stmt_in_small_stmt2086 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assert_stmt_in_small_stmt2101 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STAR_in_star_expr2141 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_expr_in_star_expr2143 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_testlist_star_expr_in_expr_stmt2187 = new BitSet(new long[]{0x2000001400084040L,0x0000001014414010L});
	public static final BitSet FOLLOW_augassign_in_expr_stmt2203 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_yield_expr_in_expr_stmt2207 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_augassign_in_expr_stmt2247 = new BitSet(new long[]{0x0700100181008000L,0x000000006800812EL});
	public static final BitSet FOLLOW_testlist_star_expr_in_expr_stmt2251 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_testlist_star_expr_in_expr_stmt2311 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_expr_stmt2314 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_expr_stmt2316 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_ASSIGN_in_expr_stmt2343 = new BitSet(new long[]{0x0700100181008000L,0x000000006800812EL});
	public static final BitSet FOLLOW_testlist_star_expr_in_expr_stmt2347 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_ASSIGN_in_expr_stmt2392 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_yield_expr_in_expr_stmt2396 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_testlist_star_expr_in_expr_stmt2453 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_ASSIGN_in_expr_stmt2480 = new BitSet(new long[]{0x0700100181008000L,0x000000006800812EL});
	public static final BitSet FOLLOW_testlist_star_expr_in_expr_stmt2484 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_ASSIGN_in_expr_stmt2529 = new BitSet(new long[]{0x0000000000000000L,0x0000010000000000L});
	public static final BitSet FOLLOW_yield_expr_in_expr_stmt2533 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_testlist_star_expr_in_expr_stmt2581 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_or_star_expr_in_testlist_star_expr2655 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_testlist_star_expr2667 = new BitSet(new long[]{0x0700100181008000L,0x000000006800812EL});
	public static final BitSet FOLLOW_test_or_star_expr_in_testlist_star_expr2672 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_testlist_star_expr2679 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_testlist_star_expr2698 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_star_expr_in_testlist_star_expr2707 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_test_or_star_expr2728 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_star_expr_in_test_or_star_expr2737 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PLUSEQUAL_in_augassign2765 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUSEQUAL_in_augassign2783 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STAREQUAL_in_augassign2801 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ATEQUAL_in_augassign2819 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SLASHEQUAL_in_augassign2837 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PERCENTEQUAL_in_augassign2855 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AMPEREQUAL_in_augassign2873 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VBAREQUAL_in_augassign2891 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CIRCUMFLEXEQUAL_in_augassign2909 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTSHIFTEQUAL_in_augassign2927 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RIGHTSHIFTEQUAL_in_augassign2945 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLESTAREQUAL_in_augassign2963 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLESLASHEQUAL_in_augassign2981 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DELETE_in_del_stmt3019 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_del_list_in_del_stmt3021 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PASS_in_pass_stmt3057 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_break_stmt_in_flow_stmt3083 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_continue_stmt_in_flow_stmt3091 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_return_stmt_in_flow_stmt3099 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_raise_stmt_in_flow_stmt3107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_yield_stmt_in_flow_stmt3115 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BREAK_in_break_stmt3143 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CONTINUE_in_continue_stmt3179 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RETURN_in_return_stmt3215 = new BitSet(new long[]{0x0700100181008002L,0x000000006000812EL});
	public static final BitSet FOLLOW_testlist_in_return_stmt3224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_yield_expr_in_yield_stmt3289 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RAISE_in_raise_stmt3325 = new BitSet(new long[]{0x0700100181008002L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_raise_stmt3330 = new BitSet(new long[]{0x0000400000000002L});
	public static final BitSet FOLLOW_FROM_in_raise_stmt3334 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_raise_stmt3338 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_import_name_in_import_stmt3367 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_import_from_in_import_stmt3375 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IMPORT_in_import_name3403 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_dotted_as_names_in_import_name3405 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FROM_in_import_from3442 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_DOT_in_import_from3447 = new BitSet(new long[]{0x0000000100000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_dotted_name_in_import_from3450 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_DOT_in_import_from3456 = new BitSet(new long[]{0x0008000100000000L});
	public static final BitSet FOLLOW_IMPORT_in_import_from3460 = new BitSet(new long[]{0x0000000000000000L,0x0000000008000024L});
	public static final BitSet FOLLOW_STAR_in_import_from3471 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_import_as_names_in_import_from3496 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_import_from3519 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_import_as_names_in_import_from3523 = new BitSet(new long[]{0x0000000000400000L,0x0000000000800000L});
	public static final BitSet FOLLOW_COMMA_in_import_from3525 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RPAREN_in_import_from3528 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_import_as_name_in_import_as_names3577 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_import_as_names3580 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_import_as_name_in_import_as_names3585 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_NAME_in_import_as_name3626 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_AS_in_import_as_name3629 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_import_as_name3633 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dotted_name_in_dotted_as_name3673 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_AS_in_dotted_as_name3676 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_dotted_as_name3680 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_dotted_as_name_in_dotted_as_names3716 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_dotted_as_names3719 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_dotted_as_name_in_dotted_as_names3724 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_NAME_in_dotted_name3758 = new BitSet(new long[]{0x0000000100000002L});
	public static final BitSet FOLLOW_DOT_in_dotted_name3761 = new BitSet(new long[]{0x019CEB2032129680L,0x0000016400121DA0L});
	public static final BitSet FOLLOW_attr_in_dotted_name3765 = new BitSet(new long[]{0x0000000100000002L});
	public static final BitSet FOLLOW_GLOBAL_in_global_stmt3801 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_global_stmt3805 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_global_stmt3808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_global_stmt3812 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_NONLOCAL_in_nonlocal_stmt3850 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_nonlocal_stmt3854 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_nonlocal_stmt3857 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_nonlocal_stmt3861 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_ASSERT_in_assert_stmt3899 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_assert_stmt3903 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_assert_stmt3907 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_assert_stmt3911 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_if_stmt_in_compound_stmt3940 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_while_stmt_in_compound_stmt3948 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_for_stmt_in_compound_stmt3956 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_try_stmt_in_compound_stmt3964 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_with_stmt_in_compound_stmt3972 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_funcdef_in_compound_stmt3980 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_classdef_in_compound_stmt3988 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_decorated_in_compound_stmt3996 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_async_stmt_in_compound_stmt4004 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ASYNC_in_async_stmt4032 = new BitSet(new long[]{0x0000200010000000L,0x0000004000000000L});
	public static final BitSet FOLLOW_funcdef_in_async_stmt4044 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_with_stmt_in_async_stmt4066 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_for_stmt_in_async_stmt4088 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_if_stmt4136 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_if_stmt4138 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_if_stmt4141 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_if_stmt4145 = new BitSet(new long[]{0x0000002000000002L,0x0000000000000800L});
	public static final BitSet FOLLOW_elif_clause_in_if_stmt4148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_else_clause_in_elif_clause4193 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ELIF_in_elif_clause4209 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_elif_clause4211 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_elif_clause4214 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_elif_clause4216 = new BitSet(new long[]{0x0000002000000002L,0x0000000000000800L});
	public static final BitSet FOLLOW_elif_clause_in_elif_clause4228 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ORELSE_in_else_clause4288 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_else_clause4290 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_else_clause4294 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WHILE_in_while_stmt4331 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_while_stmt4333 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_while_stmt4336 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_while_stmt4340 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
	public static final BitSet FOLLOW_ORELSE_in_while_stmt4344 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_while_stmt4346 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_while_stmt4350 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FOR_in_for_stmt4389 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_exprlist_in_for_stmt4391 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_IN_in_for_stmt4394 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_testlist_in_for_stmt4396 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_for_stmt4399 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_for_stmt4403 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000800L});
	public static final BitSet FOLLOW_ORELSE_in_for_stmt4415 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_for_stmt4417 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_for_stmt4421 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TRY_in_try_stmt4464 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_try_stmt4466 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_try_stmt4470 = new BitSet(new long[]{0x0000090000000000L});
	public static final BitSet FOLLOW_except_clause_in_try_stmt4483 = new BitSet(new long[]{0x0000090000000002L,0x0000000000000800L});
	public static final BitSet FOLLOW_ORELSE_in_try_stmt4487 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_try_stmt4489 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_try_stmt4493 = new BitSet(new long[]{0x0000080000000002L});
	public static final BitSet FOLLOW_FINALLY_in_try_stmt4499 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_try_stmt4501 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_try_stmt4505 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FINALLY_in_try_stmt4528 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_try_stmt4530 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_try_stmt4534 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_WITH_in_with_stmt4587 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_with_item_in_with_stmt4591 = new BitSet(new long[]{0x0000000000600000L});
	public static final BitSet FOLLOW_COMMA_in_with_stmt4601 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_with_item_in_with_stmt4605 = new BitSet(new long[]{0x0000000000600000L});
	public static final BitSet FOLLOW_COLON_in_with_stmt4609 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_with_stmt4611 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_with_item4646 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_AS_in_with_item4650 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_expr_in_with_item4652 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EXCEPT_in_except_clause4691 = new BitSet(new long[]{0x0700100181208000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_except_clause4696 = new BitSet(new long[]{0x0000000000200200L});
	public static final BitSet FOLLOW_AS_in_except_clause4700 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_except_clause4702 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_except_clause4708 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_except_clause4710 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_simple_stmt_in_suite4756 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NEWLINE_in_suite4772 = new BitSet(new long[]{0x0020000000000000L});
	public static final BitSet FOLLOW_INDENT_in_suite4774 = new BitSet(new long[]{0x070CF001B312B400L,0x00000164681291AEL});
	public static final BitSet FOLLOW_stmt_in_suite4783 = new BitSet(new long[]{0x070CF001BB12B400L,0x00000164681291AEL});
	public static final BitSet FOLLOW_DEDENT_in_suite4803 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_test_in_test4833 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_IF_in_test4855 = new BitSet(new long[]{0x0600100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_or_test_in_test4859 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_ORELSE_in_test4862 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_test4866 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_lambdef_in_test4911 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_test_in_test_nocond4942 = new BitSet(new long[]{0x0004000000000002L});
	public static final BitSet FOLLOW_IF_in_test_nocond4964 = new BitSet(new long[]{0x0600100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_or_test_in_test_nocond4968 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_ORELSE_in_test_nocond4971 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_test_nocond4975 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_lambdef_nocond_in_test_nocond5020 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_and_test_in_or_test5056 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
	public static final BitSet FOLLOW_OR_in_or_test5072 = new BitSet(new long[]{0x0600100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_and_test_in_or_test5076 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
	public static final BitSet FOLLOW_not_test_in_and_test5157 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_AND_in_and_test5173 = new BitSet(new long[]{0x0600100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_not_test_in_and_test5177 = new BitSet(new long[]{0x0000000000000082L});
	public static final BitSet FOLLOW_NOT_in_not_test5261 = new BitSet(new long[]{0x0600100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_not_test_in_not_test5265 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_comparison_in_not_test5282 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_comparison5331 = new BitSet(new long[]{0xC093004000000012L,0x0000000000000300L});
	public static final BitSet FOLLOW_comp_op_in_comparison5345 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_expr_in_comparison5349 = new BitSet(new long[]{0xC093004000000012L,0x0000000000000300L});
	public static final BitSet FOLLOW_LESS_in_comp_op5430 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GREATER_in_comp_op5446 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_EQUAL_in_comp_op5462 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_GREATEREQUAL_in_comp_op5478 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LESSEQUAL_in_comp_op5494 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ALT_NOTEQUAL_in_comp_op5510 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOTEQUAL_in_comp_op5526 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IN_in_comp_op5542 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NOT_in_comp_op5558 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_IN_in_comp_op5560 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IS_in_comp_op5576 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IS_in_comp_op5592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
	public static final BitSet FOLLOW_NOT_in_comp_op5594 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_xor_expr_in_expr5646 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
	public static final BitSet FOLLOW_VBAR_in_expr5661 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_xor_expr_in_expr5665 = new BitSet(new long[]{0x0000000000000002L,0x0000000800000000L});
	public static final BitSet FOLLOW_and_expr_in_xor_expr5744 = new BitSet(new long[]{0x0000000000040002L});
	public static final BitSet FOLLOW_CIRCUMFLEX_in_xor_expr5759 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_and_expr_in_xor_expr5763 = new BitSet(new long[]{0x0000000000040002L});
	public static final BitSet FOLLOW_shift_expr_in_and_expr5841 = new BitSet(new long[]{0x0000000000000022L});
	public static final BitSet FOLLOW_AMPER_in_and_expr5856 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_shift_expr_in_and_expr5860 = new BitSet(new long[]{0x0000000000000022L});
	public static final BitSet FOLLOW_arith_expr_in_shift_expr5943 = new BitSet(new long[]{0x1000000000000002L,0x0000000000200000L});
	public static final BitSet FOLLOW_shift_op_in_shift_expr5957 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_arith_expr_in_shift_expr5961 = new BitSet(new long[]{0x1000000000000002L,0x0000000000200000L});
	public static final BitSet FOLLOW_LEFTSHIFT_in_shift_op6045 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_RIGHTSHIFT_in_shift_op6061 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_term_in_arith_expr6107 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008008L});
	public static final BitSet FOLLOW_arith_op_in_arith_expr6120 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_term_in_arith_expr6124 = new BitSet(new long[]{0x0000000000000002L,0x0000000000008008L});
	public static final BitSet FOLLOW_PLUS_in_arith_op6232 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_arith_op6248 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_factor_in_term6294 = new BitSet(new long[]{0x0000000200002002L,0x000000000A002000L});
	public static final BitSet FOLLOW_term_op_in_term6307 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_factor_in_term6311 = new BitSet(new long[]{0x0000000200002002L,0x000000000A002000L});
	public static final BitSet FOLLOW_STAR_in_term_op6393 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AT_in_term_op6409 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_SLASH_in_term_op6425 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PERCENT_in_term_op6441 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLESLASH_in_term_op6457 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PLUS_in_factor6496 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_factor_in_factor6500 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MINUS_in_factor6516 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_factor_in_factor6520 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TILDE_in_factor6536 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_factor_in_factor6540 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_power_in_factor6556 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_atom_expr_in_power6595 = new BitSet(new long[]{0x0000000800000002L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_power6607 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_factor_in_power6609 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_AWAIT_in_atom_expr6650 = new BitSet(new long[]{0x0600100181000000L,0x0000000020000026L});
	public static final BitSet FOLLOW_atom_in_atom_expr6652 = new BitSet(new long[]{0x0200000100000002L,0x0000000000000004L});
	public static final BitSet FOLLOW_trailer_in_atom_expr6657 = new BitSet(new long[]{0x0200000100000002L,0x0000000000000004L});
	public static final BitSet FOLLOW_atom_in_atom_expr6676 = new BitSet(new long[]{0x0200000100000002L,0x0000000000000004L});
	public static final BitSet FOLLOW_trailer_in_atom_expr6681 = new BitSet(new long[]{0x0200000100000002L,0x0000000000000004L});
	public static final BitSet FOLLOW_AWAIT_in_atom_expr6705 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_atom6753 = new BitSet(new long[]{0x0700100181008000L,0x000001006880812EL});
	public static final BitSet FOLLOW_yield_expr_in_atom6771 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_testlist_comp_in_atom6791 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RPAREN_in_atom6835 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LBRACK_in_atom6843 = new BitSet(new long[]{0x0700100181008000L,0x000000006804812EL});
	public static final BitSet FOLLOW_testlist_comp_in_atom6852 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_RBRACK_in_atom6895 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LCURLY_in_atom6903 = new BitSet(new long[]{0x0700100981008000L,0x000000006808812EL});
	public static final BitSet FOLLOW_dictorsetmaker_in_atom6913 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
	public static final BitSet FOLLOW_RCURLY_in_atom6961 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_NAME_in_atom6970 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LONGINT_in_atom6988 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_in_atom7006 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMPLEX_in_atom7024 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_atom7044 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_DOT_in_atom7046 = new BitSet(new long[]{0x0000000100000000L});
	public static final BitSet FOLLOW_DOT_in_atom7048 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOLLER_in_atom7066 = new BitSet(new long[]{0x0040000000000000L});
	public static final BitSet FOLLOW_INT_in_atom7068 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_in_atom7089 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
	public static final BitSet FOLLOW_test_or_star_expr_in_testlist_comp7132 = new BitSet(new long[]{0x0000200000400002L});
	public static final BitSet FOLLOW_COMMA_in_testlist_comp7156 = new BitSet(new long[]{0x0700100181008000L,0x000000006800812EL});
	public static final BitSet FOLLOW_test_or_star_expr_in_testlist_comp7160 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_testlist_comp7168 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_comp_for_in_testlist_comp7230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LAMBDA_in_lambdef7294 = new BitSet(new long[]{0x0000000800200000L,0x0000000008000020L});
	public static final BitSet FOLLOW_varargslist_in_lambdef7297 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_lambdef7301 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_lambdef7303 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LAMBDA_in_lambdef_nocond7340 = new BitSet(new long[]{0x0000000800200000L,0x0000000008000020L});
	public static final BitSet FOLLOW_varargslist_in_lambdef_nocond7343 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_lambdef_nocond7347 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_nocond_in_lambdef_nocond7349 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LPAREN_in_trailer7389 = new BitSet(new long[]{0x0700100981008000L,0x000000006880812EL});
	public static final BitSet FOLLOW_arglist_in_trailer7398 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RPAREN_in_trailer7440 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LBRACK_in_trailer7456 = new BitSet(new long[]{0x0700100181208000L,0x000000006000812EL});
	public static final BitSet FOLLOW_subscriptlist_in_trailer7458 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
	public static final BitSet FOLLOW_RBRACK_in_trailer7461 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOT_in_trailer7477 = new BitSet(new long[]{0x019CEB2032129680L,0x0000016400121DA0L});
	public static final BitSet FOLLOW_attr_in_trailer7479 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_subscript_in_subscriptlist7518 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_subscriptlist7530 = new BitSet(new long[]{0x0700100181208000L,0x000000006000812EL});
	public static final BitSet FOLLOW_subscript_in_subscriptlist7534 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_subscriptlist7541 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_subscript7596 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_COLON_in_subscript7602 = new BitSet(new long[]{0x0700100181208002L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_subscript7607 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_sliceop_in_subscript7613 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_subscript7644 = new BitSet(new long[]{0x0700100181208002L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_subscript7649 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_sliceop_in_subscript7655 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_subscript7673 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_sliceop7710 = new BitSet(new long[]{0x0700100181008002L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_sliceop7718 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_exprlist7789 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_exprlist7801 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_expr_in_exprlist7805 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_exprlist7811 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_exprlist7830 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_del_list7868 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_del_list7880 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_expr_in_del_list7884 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_del_list7890 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_testlist7943 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_testlist7955 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_testlist7959 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_testlist7965 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_testlist7983 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_dictorsetmaker8032 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_dictorsetmaker8035 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_dictorsetmaker8039 = new BitSet(new long[]{0x0000200000400002L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_dictorsetmaker8044 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_expr_in_dictorsetmaker8048 = new BitSet(new long[]{0x0000200000400002L});
	public static final BitSet FOLLOW_comp_for_in_dictorsetmaker8063 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_dictorsetmaker8098 = new BitSet(new long[]{0x0700100981008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_dictorsetmaker8103 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_dictorsetmaker8106 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_dictorsetmaker8110 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_dictorsetmaker8115 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_expr_in_dictorsetmaker8119 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_dictorsetmaker8148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_dictorsetmaker8162 = new BitSet(new long[]{0x0000200000400002L});
	public static final BitSet FOLLOW_star_expr_in_dictorsetmaker8169 = new BitSet(new long[]{0x0000200000400002L});
	public static final BitSet FOLLOW_comp_for_in_dictorsetmaker8184 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COMMA_in_dictorsetmaker8212 = new BitSet(new long[]{0x0700100181008000L,0x000000006800812EL});
	public static final BitSet FOLLOW_test_in_dictorsetmaker8217 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_star_expr_in_dictorsetmaker8224 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_dictorsetmaker8253 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CLASS_in_classdef8284 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
	public static final BitSet FOLLOW_NAME_in_classdef8286 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000004L});
	public static final BitSet FOLLOW_LPAREN_in_classdef8289 = new BitSet(new long[]{0x0700100981008000L,0x000000006880812EL});
	public static final BitSet FOLLOW_arglist_in_classdef8291 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
	public static final BitSet FOLLOW_RPAREN_in_classdef8294 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_classdef8298 = new BitSet(new long[]{0x0708D001A3028400L,0x00000100681291EEL});
	public static final BitSet FOLLOW_suite_in_classdef8300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_argument_in_arglist8340 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_arglist8344 = new BitSet(new long[]{0x0700100981008000L,0x000000006800812EL});
	public static final BitSet FOLLOW_argument_in_arglist8346 = new BitSet(new long[]{0x0000000000400002L});
	public static final BitSet FOLLOW_COMMA_in_arglist8351 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_argument8393 = new BitSet(new long[]{0x0000200000000802L});
	public static final BitSet FOLLOW_comp_for_in_argument8404 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ASSIGN_in_argument8425 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_argument8429 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STAR_in_argument8474 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_argument8478 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_argument8498 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_argument8502 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_comp_for_in_comp_iter8531 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_comp_if_in_comp_iter8540 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FOR_in_comp_for8566 = new BitSet(new long[]{0x0600100181008000L,0x000000006000802EL});
	public static final BitSet FOLLOW_exprlist_in_comp_for8568 = new BitSet(new long[]{0x0010000000000000L});
	public static final BitSet FOLLOW_IN_in_comp_for8571 = new BitSet(new long[]{0x0600100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_or_test_in_comp_for8573 = new BitSet(new long[]{0x0004200000000002L});
	public static final BitSet FOLLOW_comp_iter_in_comp_for8576 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_comp_if8605 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_nocond_in_comp_if8607 = new BitSet(new long[]{0x0004200000000002L});
	public static final BitSet FOLLOW_comp_iter_in_comp_if8610 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_YIELD_in_yield_expr8651 = new BitSet(new long[]{0x0700500181008002L,0x000000006000812EL});
	public static final BitSet FOLLOW_yield_arg_in_yield_expr8653 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FROM_in_yield_arg8698 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_yield_arg8700 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_testlist_in_yield_arg8711 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_testlist_star_expr_in_synpred1_Python2177 = new BitSet(new long[]{0x2000001400084040L,0x0000001014414010L});
	public static final BitSet FOLLOW_augassign_in_synpred1_Python2180 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_testlist_star_expr_in_synpred2_Python2296 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_synpred2_Python2299 = new BitSet(new long[]{0x0700100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_test_in_synpred2_Python2301 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ASSIGN_in_synpred2_Python2304 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_testlist_star_expr_in_synpred3_Python2443 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ASSIGN_in_synpred3_Python2446 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_synpred4_Python2628 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_star_expr_in_synpred4_Python2633 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_COMMA_in_synpred4_Python2637 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_synpred5_Python4845 = new BitSet(new long[]{0x0600100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_or_test_in_synpred5_Python4847 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_ORELSE_in_synpred5_Python4850 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_synpred6_Python4954 = new BitSet(new long[]{0x0600100181008000L,0x000000006000812EL});
	public static final BitSet FOLLOW_or_test_in_synpred6_Python4956 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
	public static final BitSet FOLLOW_ORELSE_in_synpred6_Python4959 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_synpred7_Python7583 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_synpred7_Python7586 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_COLON_in_synpred8_Python7634 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_expr_in_synpred9_Python7779 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_COMMA_in_synpred9_Python7782 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_synpred10_Python7930 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_COMMA_in_synpred10_Python7933 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_test_in_synpred11_Python8017 = new BitSet(new long[]{0x0000000000200000L});
	public static final BitSet FOLLOW_COLON_in_synpred11_Python8020 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_DOUBLESTAR_in_synpred11_Python8024 = new BitSet(new long[]{0x0000000000000002L});
}
