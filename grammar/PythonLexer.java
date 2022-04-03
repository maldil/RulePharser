// $ANTLR 3.5.2 Python.g 2022-03-28 22:38:01

package org.python.antlr;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class PythonLexer extends Lexer {
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

	/** Handles context-sensitive lexing of implicit line joining such as
	 *  the case where newline is ignored in cases like this:
	 *  a = [3,
	 *       4]
	 */
	int implicitLineJoiningLevel = 0;
	int startPos=-1;

	//For use in partial parsing.
	public boolean eofWhileNested = false;
	public boolean partial = false;
	public boolean single = false;

	//If you want to use another error recovery mechanism change this
	//and the same one in the parser.
	private ErrorHandler errorHandler;

	    public void setErrorHandler(ErrorHandler eh) {
	        this.errorHandler = eh;
	    }

	    /**
	     *  Taken directly from antlr's Lexer.java -- needs to be re-integrated every time
	     *  we upgrade from Antlr (need to consider a Lexer subclass, though the issue would
	     *  remain).
	     */
	    public Token nextToken() {
	        startPos = getCharPositionInLine();
	        while (true) {
	            state.token = null;
	            state.channel = Token.DEFAULT_CHANNEL;
	            state.tokenStartCharIndex = input.index();
	            state.tokenStartCharPositionInLine = input.getCharPositionInLine();
	            state.tokenStartLine = input.getLine();
	            state.text = null;
	            if ( input.LA(1)==CharStream.EOF ) {
	                if (implicitLineJoiningLevel > 0) {
	                    eofWhileNested = true;
	                }
	                return getEOFToken();
	            }
	            try {
	                mTokens();
	                if ( state.token==null ) {
	                    emit();
	                }
	                else if ( state.token==Token.SKIP_TOKEN ) {
	                    continue;
	                }
	                return state.token;
	            } catch (NoViableAltException nva) {
	                reportError(nva);
	                errorHandler.recover(this, nva); // throw out current char and try again
	            } catch (FailedPredicateException fp) {
	                //XXX: added this for failed STRINGPART -- the FailedPredicateException
	                //     hides a NoViableAltException.  This should be the only
	                //     FailedPredicateException that gets thrown by the lexer.
	                reportError(fp);
	                errorHandler.recover(this, fp); // throw out current char and try again
	            } catch (RecognitionException re) {
	                reportError(re);
	                // match() routine has already called recover()
	            }
	        }
	    }

	    public Token getEOFToken() {
	        Token eof = new CommonToken(input,Token.EOF,
	            Token.DEFAULT_CHANNEL,
	            input.index(),input.index());
	        eof.setLine(getLine());
	        eof.setCharPositionInLine(getCharPositionInLine());
	        return eof;
	    }

	    @Override
	    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
	        //Do nothing. We will handle error display elsewhere.
	    }



	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public PythonLexer() {} 
	public PythonLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public PythonLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "Python.g"; }

	// $ANTLR start "AS"
	public final void mAS() throws RecognitionException {
		try {
			int _type = AS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2439:11: ( 'as' )
			// Python.g:2439:13: 'as'
			{
			match("as"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AS"

	// $ANTLR start "ASSERT"
	public final void mASSERT() throws RecognitionException {
		try {
			int _type = ASSERT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2440:11: ( 'assert' )
			// Python.g:2440:13: 'assert'
			{
			match("assert"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASSERT"

	// $ANTLR start "ASYNC"
	public final void mASYNC() throws RecognitionException {
		try {
			int _type = ASYNC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2441:11: ( 'async' )
			// Python.g:2441:13: 'async'
			{
			match("async"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASYNC"

	// $ANTLR start "AWAIT"
	public final void mAWAIT() throws RecognitionException {
		try {
			int _type = AWAIT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2442:11: ( 'await' )
			// Python.g:2442:13: 'await'
			{
			match("await"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AWAIT"

	// $ANTLR start "BREAK"
	public final void mBREAK() throws RecognitionException {
		try {
			int _type = BREAK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2443:11: ( 'break' )
			// Python.g:2443:13: 'break'
			{
			match("break"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BREAK"

	// $ANTLR start "CLASS"
	public final void mCLASS() throws RecognitionException {
		try {
			int _type = CLASS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2444:11: ( 'class' )
			// Python.g:2444:13: 'class'
			{
			match("class"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CLASS"

	// $ANTLR start "CONTINUE"
	public final void mCONTINUE() throws RecognitionException {
		try {
			int _type = CONTINUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2445:11: ( 'continue' )
			// Python.g:2445:13: 'continue'
			{
			match("continue"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONTINUE"

	// $ANTLR start "DEF"
	public final void mDEF() throws RecognitionException {
		try {
			int _type = DEF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2446:11: ( 'def' )
			// Python.g:2446:13: 'def'
			{
			match("def"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DEF"

	// $ANTLR start "DELETE"
	public final void mDELETE() throws RecognitionException {
		try {
			int _type = DELETE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2447:11: ( 'del' )
			// Python.g:2447:13: 'del'
			{
			match("del"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DELETE"

	// $ANTLR start "ELIF"
	public final void mELIF() throws RecognitionException {
		try {
			int _type = ELIF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2448:11: ( 'elif' )
			// Python.g:2448:13: 'elif'
			{
			match("elif"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELIF"

	// $ANTLR start "EXCEPT"
	public final void mEXCEPT() throws RecognitionException {
		try {
			int _type = EXCEPT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2449:11: ( 'except' )
			// Python.g:2449:13: 'except'
			{
			match("except"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXCEPT"

	// $ANTLR start "EXEC"
	public final void mEXEC() throws RecognitionException {
		try {
			int _type = EXEC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2450:11: ( 'exec1' )
			// Python.g:2450:13: 'exec1'
			{
			match("exec1"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EXEC"

	// $ANTLR start "FINALLY"
	public final void mFINALLY() throws RecognitionException {
		try {
			int _type = FINALLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2451:11: ( 'finally' )
			// Python.g:2451:13: 'finally'
			{
			match("finally"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FINALLY"

	// $ANTLR start "FROM"
	public final void mFROM() throws RecognitionException {
		try {
			int _type = FROM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2452:11: ( 'from' )
			// Python.g:2452:13: 'from'
			{
			match("from"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FROM"

	// $ANTLR start "FOR"
	public final void mFOR() throws RecognitionException {
		try {
			int _type = FOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2453:11: ( 'for' )
			// Python.g:2453:13: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FOR"

	// $ANTLR start "GLOBAL"
	public final void mGLOBAL() throws RecognitionException {
		try {
			int _type = GLOBAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2454:11: ( 'global' )
			// Python.g:2454:13: 'global'
			{
			match("global"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GLOBAL"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2455:11: ( 'if' )
			// Python.g:2455:13: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "IMPORT"
	public final void mIMPORT() throws RecognitionException {
		try {
			int _type = IMPORT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2456:11: ( 'import' )
			// Python.g:2456:13: 'import'
			{
			match("import"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IMPORT"

	// $ANTLR start "IN"
	public final void mIN() throws RecognitionException {
		try {
			int _type = IN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2457:11: ( 'in' )
			// Python.g:2457:13: 'in'
			{
			match("in"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IN"

	// $ANTLR start "IS"
	public final void mIS() throws RecognitionException {
		try {
			int _type = IS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2458:11: ( 'is' )
			// Python.g:2458:13: 'is'
			{
			match("is"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IS"

	// $ANTLR start "LAMBDA"
	public final void mLAMBDA() throws RecognitionException {
		try {
			int _type = LAMBDA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2459:11: ( 'lambda' )
			// Python.g:2459:13: 'lambda'
			{
			match("lambda"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LAMBDA"

	// $ANTLR start "NONLOCAL"
	public final void mNONLOCAL() throws RecognitionException {
		try {
			int _type = NONLOCAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2460:11: ( 'nonlocal' )
			// Python.g:2460:13: 'nonlocal'
			{
			match("nonlocal"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NONLOCAL"

	// $ANTLR start "ORELSE"
	public final void mORELSE() throws RecognitionException {
		try {
			int _type = ORELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2461:11: ( 'else' )
			// Python.g:2461:13: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ORELSE"

	// $ANTLR start "PASS"
	public final void mPASS() throws RecognitionException {
		try {
			int _type = PASS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2462:11: ( 'pass' )
			// Python.g:2462:13: 'pass'
			{
			match("pass"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PASS"

	// $ANTLR start "RAISE"
	public final void mRAISE() throws RecognitionException {
		try {
			int _type = RAISE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2463:11: ( 'raise' )
			// Python.g:2463:13: 'raise'
			{
			match("raise"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RAISE"

	// $ANTLR start "RETURN"
	public final void mRETURN() throws RecognitionException {
		try {
			int _type = RETURN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2464:11: ( 'return' )
			// Python.g:2464:13: 'return'
			{
			match("return"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RETURN"

	// $ANTLR start "TRY"
	public final void mTRY() throws RecognitionException {
		try {
			int _type = TRY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2465:11: ( 'try' )
			// Python.g:2465:13: 'try'
			{
			match("try"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRY"

	// $ANTLR start "WHILE"
	public final void mWHILE() throws RecognitionException {
		try {
			int _type = WHILE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2466:11: ( 'while' )
			// Python.g:2466:13: 'while'
			{
			match("while"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WHILE"

	// $ANTLR start "WITH"
	public final void mWITH() throws RecognitionException {
		try {
			int _type = WITH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2467:11: ( 'with' )
			// Python.g:2467:13: 'with'
			{
			match("with"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WITH"

	// $ANTLR start "YIELD"
	public final void mYIELD() throws RecognitionException {
		try {
			int _type = YIELD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2468:11: ( 'yield' )
			// Python.g:2468:13: 'yield'
			{
			match("yield"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "YIELD"

	// $ANTLR start "LPAREN"
	public final void mLPAREN() throws RecognitionException {
		try {
			int _type = LPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2470:11: ( '(' )
			// Python.g:2470:13: '('
			{
			match('('); 
			implicitLineJoiningLevel++;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LPAREN"

	// $ANTLR start "RPAREN"
	public final void mRPAREN() throws RecognitionException {
		try {
			int _type = RPAREN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2472:11: ( ')' )
			// Python.g:2472:13: ')'
			{
			match(')'); 
			implicitLineJoiningLevel--;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RPAREN"

	// $ANTLR start "LBRACK"
	public final void mLBRACK() throws RecognitionException {
		try {
			int _type = LBRACK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2474:11: ( '[' )
			// Python.g:2474:13: '['
			{
			match('['); 
			implicitLineJoiningLevel++;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LBRACK"

	// $ANTLR start "RBRACK"
	public final void mRBRACK() throws RecognitionException {
		try {
			int _type = RBRACK;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2476:11: ( ']' )
			// Python.g:2476:13: ']'
			{
			match(']'); 
			implicitLineJoiningLevel--;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RBRACK"

	// $ANTLR start "COLON"
	public final void mCOLON() throws RecognitionException {
		try {
			int _type = COLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2478:11: ( ':' )
			// Python.g:2478:13: ':'
			{
			match(':'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COLON"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2480:10: ( ',' )
			// Python.g:2480:12: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "SEMI"
	public final void mSEMI() throws RecognitionException {
		try {
			int _type = SEMI;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2482:9: ( ';' )
			// Python.g:2482:11: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMI"

	// $ANTLR start "PLUS"
	public final void mPLUS() throws RecognitionException {
		try {
			int _type = PLUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2484:9: ( '+' )
			// Python.g:2484:11: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUS"

	// $ANTLR start "MINUS"
	public final void mMINUS() throws RecognitionException {
		try {
			int _type = MINUS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2486:10: ( '-' )
			// Python.g:2486:12: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUS"

	// $ANTLR start "STAR"
	public final void mSTAR() throws RecognitionException {
		try {
			int _type = STAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2488:9: ( '*' )
			// Python.g:2488:11: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STAR"

	// $ANTLR start "SLASH"
	public final void mSLASH() throws RecognitionException {
		try {
			int _type = SLASH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2490:10: ( '/' )
			// Python.g:2490:12: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SLASH"

	// $ANTLR start "VBAR"
	public final void mVBAR() throws RecognitionException {
		try {
			int _type = VBAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2492:9: ( '|' )
			// Python.g:2492:11: '|'
			{
			match('|'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VBAR"

	// $ANTLR start "AMPER"
	public final void mAMPER() throws RecognitionException {
		try {
			int _type = AMPER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2494:10: ( '&' )
			// Python.g:2494:12: '&'
			{
			match('&'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AMPER"

	// $ANTLR start "LESS"
	public final void mLESS() throws RecognitionException {
		try {
			int _type = LESS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2496:9: ( '<' )
			// Python.g:2496:11: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LESS"

	// $ANTLR start "GREATER"
	public final void mGREATER() throws RecognitionException {
		try {
			int _type = GREATER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2498:12: ( '>' )
			// Python.g:2498:14: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GREATER"

	// $ANTLR start "ASSIGN"
	public final void mASSIGN() throws RecognitionException {
		try {
			int _type = ASSIGN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2500:11: ( '=' )
			// Python.g:2500:13: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASSIGN"

	// $ANTLR start "PERCENT"
	public final void mPERCENT() throws RecognitionException {
		try {
			int _type = PERCENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2502:12: ( '%' )
			// Python.g:2502:14: '%'
			{
			match('%'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PERCENT"

	// $ANTLR start "BACKQUOTE"
	public final void mBACKQUOTE() throws RecognitionException {
		try {
			int _type = BACKQUOTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2504:14: ( '`' )
			// Python.g:2504:16: '`'
			{
			match('`'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BACKQUOTE"

	// $ANTLR start "LCURLY"
	public final void mLCURLY() throws RecognitionException {
		try {
			int _type = LCURLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2506:11: ( '{' )
			// Python.g:2506:13: '{'
			{
			match('{'); 
			implicitLineJoiningLevel++;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LCURLY"

	// $ANTLR start "RCURLY"
	public final void mRCURLY() throws RecognitionException {
		try {
			int _type = RCURLY;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2508:11: ( '}' )
			// Python.g:2508:13: '}'
			{
			match('}'); 
			implicitLineJoiningLevel--;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RCURLY"

	// $ANTLR start "CIRCUMFLEX"
	public final void mCIRCUMFLEX() throws RecognitionException {
		try {
			int _type = CIRCUMFLEX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2510:15: ( '^' )
			// Python.g:2510:17: '^'
			{
			match('^'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CIRCUMFLEX"

	// $ANTLR start "DOLLER"
	public final void mDOLLER() throws RecognitionException {
		try {
			int _type = DOLLER;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2512:9: ( '$' )
			// Python.g:2512:11: '$'
			{
			match('$'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOLLER"

	// $ANTLR start "TILDE"
	public final void mTILDE() throws RecognitionException {
		try {
			int _type = TILDE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2514:10: ( '~' )
			// Python.g:2514:12: '~'
			{
			match('~'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TILDE"

	// $ANTLR start "EQUAL"
	public final void mEQUAL() throws RecognitionException {
		try {
			int _type = EQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2516:10: ( '==' )
			// Python.g:2516:12: '=='
			{
			match("=="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EQUAL"

	// $ANTLR start "NOTEQUAL"
	public final void mNOTEQUAL() throws RecognitionException {
		try {
			int _type = NOTEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2518:13: ( '!=' )
			// Python.g:2518:15: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOTEQUAL"

	// $ANTLR start "ALT_NOTEQUAL"
	public final void mALT_NOTEQUAL() throws RecognitionException {
		try {
			int _type = ALT_NOTEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2520:13: ( '<>' )
			// Python.g:2520:15: '<>'
			{
			match("<>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ALT_NOTEQUAL"

	// $ANTLR start "LESSEQUAL"
	public final void mLESSEQUAL() throws RecognitionException {
		try {
			int _type = LESSEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2522:14: ( '<=' )
			// Python.g:2522:16: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LESSEQUAL"

	// $ANTLR start "LEFTSHIFT"
	public final void mLEFTSHIFT() throws RecognitionException {
		try {
			int _type = LEFTSHIFT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2524:14: ( '<<' )
			// Python.g:2524:16: '<<'
			{
			match("<<"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEFTSHIFT"

	// $ANTLR start "GREATEREQUAL"
	public final void mGREATEREQUAL() throws RecognitionException {
		try {
			int _type = GREATEREQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2526:17: ( '>=' )
			// Python.g:2526:19: '>='
			{
			match(">="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "GREATEREQUAL"

	// $ANTLR start "RIGHTSHIFT"
	public final void mRIGHTSHIFT() throws RecognitionException {
		try {
			int _type = RIGHTSHIFT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2528:15: ( '>>' )
			// Python.g:2528:17: '>>'
			{
			match(">>"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RIGHTSHIFT"

	// $ANTLR start "PLUSEQUAL"
	public final void mPLUSEQUAL() throws RecognitionException {
		try {
			int _type = PLUSEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2530:14: ( '+=' )
			// Python.g:2530:16: '+='
			{
			match("+="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PLUSEQUAL"

	// $ANTLR start "MINUSEQUAL"
	public final void mMINUSEQUAL() throws RecognitionException {
		try {
			int _type = MINUSEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2532:15: ( '-=' )
			// Python.g:2532:17: '-='
			{
			match("-="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MINUSEQUAL"

	// $ANTLR start "DOUBLESTAR"
	public final void mDOUBLESTAR() throws RecognitionException {
		try {
			int _type = DOUBLESTAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2534:15: ( '**' )
			// Python.g:2534:17: '**'
			{
			match("**"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLESTAR"

	// $ANTLR start "STAREQUAL"
	public final void mSTAREQUAL() throws RecognitionException {
		try {
			int _type = STAREQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2536:14: ( '*=' )
			// Python.g:2536:16: '*='
			{
			match("*="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STAREQUAL"

	// $ANTLR start "ATEQUAL"
	public final void mATEQUAL() throws RecognitionException {
		try {
			int _type = ATEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2538:13: ( '@=' )
			// Python.g:2538:15: '@='
			{
			match("@="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ATEQUAL"

	// $ANTLR start "DOUBLESLASH"
	public final void mDOUBLESLASH() throws RecognitionException {
		try {
			int _type = DOUBLESLASH;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2540:16: ( '//' )
			// Python.g:2540:18: '//'
			{
			match("//"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLESLASH"

	// $ANTLR start "SLASHEQUAL"
	public final void mSLASHEQUAL() throws RecognitionException {
		try {
			int _type = SLASHEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2542:15: ( '/=' )
			// Python.g:2542:17: '/='
			{
			match("/="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SLASHEQUAL"

	// $ANTLR start "VBAREQUAL"
	public final void mVBAREQUAL() throws RecognitionException {
		try {
			int _type = VBAREQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2544:14: ( '|=' )
			// Python.g:2544:16: '|='
			{
			match("|="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VBAREQUAL"

	// $ANTLR start "PERCENTEQUAL"
	public final void mPERCENTEQUAL() throws RecognitionException {
		try {
			int _type = PERCENTEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2546:17: ( '%=' )
			// Python.g:2546:19: '%='
			{
			match("%="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PERCENTEQUAL"

	// $ANTLR start "AMPEREQUAL"
	public final void mAMPEREQUAL() throws RecognitionException {
		try {
			int _type = AMPEREQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2548:15: ( '&=' )
			// Python.g:2548:17: '&='
			{
			match("&="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AMPEREQUAL"

	// $ANTLR start "CIRCUMFLEXEQUAL"
	public final void mCIRCUMFLEXEQUAL() throws RecognitionException {
		try {
			int _type = CIRCUMFLEXEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2550:20: ( '^=' )
			// Python.g:2550:22: '^='
			{
			match("^="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CIRCUMFLEXEQUAL"

	// $ANTLR start "LEFTSHIFTEQUAL"
	public final void mLEFTSHIFTEQUAL() throws RecognitionException {
		try {
			int _type = LEFTSHIFTEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2552:19: ( '<<=' )
			// Python.g:2552:21: '<<='
			{
			match("<<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEFTSHIFTEQUAL"

	// $ANTLR start "RIGHTSHIFTEQUAL"
	public final void mRIGHTSHIFTEQUAL() throws RecognitionException {
		try {
			int _type = RIGHTSHIFTEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2554:20: ( '>>=' )
			// Python.g:2554:22: '>>='
			{
			match(">>="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "RIGHTSHIFTEQUAL"

	// $ANTLR start "DOUBLESTAREQUAL"
	public final void mDOUBLESTAREQUAL() throws RecognitionException {
		try {
			int _type = DOUBLESTAREQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2556:20: ( '**=' )
			// Python.g:2556:22: '**='
			{
			match("**="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLESTAREQUAL"

	// $ANTLR start "DOUBLESLASHEQUAL"
	public final void mDOUBLESLASHEQUAL() throws RecognitionException {
		try {
			int _type = DOUBLESLASHEQUAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2558:21: ( '//=' )
			// Python.g:2558:23: '//='
			{
			match("//="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLESLASHEQUAL"

	// $ANTLR start "ARROW"
	public final void mARROW() throws RecognitionException {
		try {
			int _type = ARROW;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2560:7: ( '->' )
			// Python.g:2560:9: '->'
			{
			match("->"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ARROW"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2562:5: ( '.' )
			// Python.g:2562:7: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "AT"
	public final void mAT() throws RecognitionException {
		try {
			int _type = AT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2564:4: ( '@' )
			// Python.g:2564:6: '@'
			{
			match('@'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AT"

	// $ANTLR start "AND"
	public final void mAND() throws RecognitionException {
		try {
			int _type = AND;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2566:5: ( 'and' )
			// Python.g:2566:7: 'and'
			{
			match("and"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "AND"

	// $ANTLR start "OR"
	public final void mOR() throws RecognitionException {
		try {
			int _type = OR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2568:4: ( 'or' )
			// Python.g:2568:6: 'or'
			{
			match("or"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "OR"

	// $ANTLR start "NOT"
	public final void mNOT() throws RecognitionException {
		try {
			int _type = NOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2570:5: ( 'not' )
			// Python.g:2570:7: 'not'
			{
			match("not"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NOT"

	// $ANTLR start "FLOAT"
	public final void mFLOAT() throws RecognitionException {
		try {
			int _type = FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2573:5: ( '.' DIGITS ( Exponent )? | DIGITS '.' Exponent | DIGITS ( '.' ( DIGITS ( Exponent )? )? | Exponent ) )
			int alt5=3;
			alt5 = dfa5.predict(input);
			switch (alt5) {
				case 1 :
					// Python.g:2573:9: '.' DIGITS ( Exponent )?
					{
					match('.'); 
					mDIGITS(); 

					// Python.g:2573:20: ( Exponent )?
					int alt1=2;
					int LA1_0 = input.LA(1);
					if ( (LA1_0=='E'||LA1_0=='e') ) {
						alt1=1;
					}
					switch (alt1) {
						case 1 :
							// Python.g:2573:21: Exponent
							{
							mExponent(); 

							}
							break;

					}

					}
					break;
				case 2 :
					// Python.g:2574:9: DIGITS '.' Exponent
					{
					mDIGITS(); 

					match('.'); 
					mExponent(); 

					}
					break;
				case 3 :
					// Python.g:2575:9: DIGITS ( '.' ( DIGITS ( Exponent )? )? | Exponent )
					{
					mDIGITS(); 

					// Python.g:2575:16: ( '.' ( DIGITS ( Exponent )? )? | Exponent )
					int alt4=2;
					int LA4_0 = input.LA(1);
					if ( (LA4_0=='.') ) {
						alt4=1;
					}
					else if ( (LA4_0=='E'||LA4_0=='e') ) {
						alt4=2;
					}

					else {
						NoViableAltException nvae =
							new NoViableAltException("", 4, 0, input);
						throw nvae;
					}

					switch (alt4) {
						case 1 :
							// Python.g:2575:17: '.' ( DIGITS ( Exponent )? )?
							{
							match('.'); 
							// Python.g:2575:21: ( DIGITS ( Exponent )? )?
							int alt3=2;
							int LA3_0 = input.LA(1);
							if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
								alt3=1;
							}
							switch (alt3) {
								case 1 :
									// Python.g:2575:22: DIGITS ( Exponent )?
									{
									mDIGITS(); 

									// Python.g:2575:29: ( Exponent )?
									int alt2=2;
									int LA2_0 = input.LA(1);
									if ( (LA2_0=='E'||LA2_0=='e') ) {
										alt2=1;
									}
									switch (alt2) {
										case 1 :
											// Python.g:2575:30: Exponent
											{
											mExponent(); 

											}
											break;

									}

									}
									break;

							}

							}
							break;
						case 2 :
							// Python.g:2575:45: Exponent
							{
							mExponent(); 

							}
							break;

					}

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT"

	// $ANTLR start "LONGINT"
	public final void mLONGINT() throws RecognitionException {
		try {
			int _type = LONGINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2579:5: ( INT ( 'l' | 'L' ) )
			// Python.g:2579:9: INT ( 'l' | 'L' )
			{
			mINT(); 

			if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LONGINT"

	// $ANTLR start "Exponent"
	public final void mExponent() throws RecognitionException {
		try {
			// Python.g:2585:5: ( ( 'e' | 'E' ) ( '+' | '-' )? DIGITS )
			// Python.g:2585:10: ( 'e' | 'E' ) ( '+' | '-' )? DIGITS
			{
			if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// Python.g:2585:22: ( '+' | '-' )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0=='+'||LA6_0=='-') ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// Python.g:
					{
					if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}

			mDIGITS(); 

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Exponent"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2587:5: ( '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ( '_' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ )* | '0' ( 'o' | 'O' ) ( '0' .. '7' )+ ( '_' ( '0' .. '7' )+ )* | '0' ( 'b' | 'B' ) ( '0' .. '1' )+ ( '_' ( '0' .. '1' )+ )* | ( '0' .. '9' )+ ( '_' ( '0' .. '9' )+ )* )
			int alt19=4;
			int LA19_0 = input.LA(1);
			if ( (LA19_0=='0') ) {
				switch ( input.LA(2) ) {
				case 'X':
				case 'x':
					{
					alt19=1;
					}
					break;
				case 'O':
				case 'o':
					{
					alt19=2;
					}
					break;
				case 'B':
				case 'b':
					{
					alt19=3;
					}
					break;
				default:
					alt19=4;
				}
			}
			else if ( ((LA19_0 >= '1' && LA19_0 <= '9')) ) {
				alt19=4;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 19, 0, input);
				throw nvae;
			}

			switch (alt19) {
				case 1 :
					// Python.g:2588:9: '0' ( 'x' | 'X' ) ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ ( '_' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ )*
					{
					match('0'); 
					if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// Python.g:2588:25: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
					int cnt7=0;
					loop7:
					while (true) {
						int alt7=2;
						int LA7_0 = input.LA(1);
						if ( ((LA7_0 >= '0' && LA7_0 <= '9')||(LA7_0 >= 'A' && LA7_0 <= 'F')||(LA7_0 >= 'a' && LA7_0 <= 'f')) ) {
							alt7=1;
						}

						switch (alt7) {
						case 1 :
							// Python.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt7 >= 1 ) break loop7;
							EarlyExitException eee = new EarlyExitException(7, input);
							throw eee;
						}
						cnt7++;
					}

					// Python.g:2588:67: ( '_' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+ )*
					loop9:
					while (true) {
						int alt9=2;
						int LA9_0 = input.LA(1);
						if ( (LA9_0=='_') ) {
							alt9=1;
						}

						switch (alt9) {
						case 1 :
							// Python.g:2588:68: '_' ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
							{
							match('_'); 
							// Python.g:2588:72: ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' )+
							int cnt8=0;
							loop8:
							while (true) {
								int alt8=2;
								int LA8_0 = input.LA(1);
								if ( ((LA8_0 >= '0' && LA8_0 <= '9')||(LA8_0 >= 'A' && LA8_0 <= 'F')||(LA8_0 >= 'a' && LA8_0 <= 'f')) ) {
									alt8=1;
								}

								switch (alt8) {
								case 1 :
									// Python.g:
									{
									if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
										input.consume();
									}
									else {
										MismatchedSetException mse = new MismatchedSetException(null,input);
										recover(mse);
										throw mse;
									}
									}
									break;

								default :
									if ( cnt8 >= 1 ) break loop8;
									EarlyExitException eee = new EarlyExitException(8, input);
									throw eee;
								}
								cnt8++;
							}

							}
							break;

						default :
							break loop9;
						}
					}

					}
					break;
				case 2 :
					// Python.g:2590:9: '0' ( 'o' | 'O' ) ( '0' .. '7' )+ ( '_' ( '0' .. '7' )+ )*
					{
					match('0'); 
					if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// Python.g:2590:25: ( '0' .. '7' )+
					int cnt10=0;
					loop10:
					while (true) {
						int alt10=2;
						int LA10_0 = input.LA(1);
						if ( ((LA10_0 >= '0' && LA10_0 <= '7')) ) {
							alt10=1;
						}

						switch (alt10) {
						case 1 :
							// Python.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt10 >= 1 ) break loop10;
							EarlyExitException eee = new EarlyExitException(10, input);
							throw eee;
						}
						cnt10++;
					}

					// Python.g:2590:41: ( '_' ( '0' .. '7' )+ )*
					loop12:
					while (true) {
						int alt12=2;
						int LA12_0 = input.LA(1);
						if ( (LA12_0=='_') ) {
							alt12=1;
						}

						switch (alt12) {
						case 1 :
							// Python.g:2590:42: '_' ( '0' .. '7' )+
							{
							match('_'); 
							// Python.g:2590:46: ( '0' .. '7' )+
							int cnt11=0;
							loop11:
							while (true) {
								int alt11=2;
								int LA11_0 = input.LA(1);
								if ( ((LA11_0 >= '0' && LA11_0 <= '7')) ) {
									alt11=1;
								}

								switch (alt11) {
								case 1 :
									// Python.g:
									{
									if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
										input.consume();
									}
									else {
										MismatchedSetException mse = new MismatchedSetException(null,input);
										recover(mse);
										throw mse;
									}
									}
									break;

								default :
									if ( cnt11 >= 1 ) break loop11;
									EarlyExitException eee = new EarlyExitException(11, input);
									throw eee;
								}
								cnt11++;
							}

							}
							break;

						default :
							break loop12;
						}
					}

					}
					break;
				case 3 :
					// Python.g:2592:9: '0' ( 'b' | 'B' ) ( '0' .. '1' )+ ( '_' ( '0' .. '1' )+ )*
					{
					match('0'); 
					if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					// Python.g:2592:25: ( '0' .. '1' )+
					int cnt13=0;
					loop13:
					while (true) {
						int alt13=2;
						int LA13_0 = input.LA(1);
						if ( ((LA13_0 >= '0' && LA13_0 <= '1')) ) {
							alt13=1;
						}

						switch (alt13) {
						case 1 :
							// Python.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '1') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt13 >= 1 ) break loop13;
							EarlyExitException eee = new EarlyExitException(13, input);
							throw eee;
						}
						cnt13++;
					}

					// Python.g:2592:41: ( '_' ( '0' .. '1' )+ )*
					loop15:
					while (true) {
						int alt15=2;
						int LA15_0 = input.LA(1);
						if ( (LA15_0=='_') ) {
							alt15=1;
						}

						switch (alt15) {
						case 1 :
							// Python.g:2592:42: '_' ( '0' .. '1' )+
							{
							match('_'); 
							// Python.g:2592:46: ( '0' .. '1' )+
							int cnt14=0;
							loop14:
							while (true) {
								int alt14=2;
								int LA14_0 = input.LA(1);
								if ( ((LA14_0 >= '0' && LA14_0 <= '1')) ) {
									alt14=1;
								}

								switch (alt14) {
								case 1 :
									// Python.g:
									{
									if ( (input.LA(1) >= '0' && input.LA(1) <= '1') ) {
										input.consume();
									}
									else {
										MismatchedSetException mse = new MismatchedSetException(null,input);
										recover(mse);
										throw mse;
									}
									}
									break;

								default :
									if ( cnt14 >= 1 ) break loop14;
									EarlyExitException eee = new EarlyExitException(14, input);
									throw eee;
								}
								cnt14++;
							}

							}
							break;

						default :
							break loop15;
						}
					}

					}
					break;
				case 4 :
					// Python.g:2594:9: ( '0' .. '9' )+ ( '_' ( '0' .. '9' )+ )*
					{
					// Python.g:2594:9: ( '0' .. '9' )+
					int cnt16=0;
					loop16:
					while (true) {
						int alt16=2;
						int LA16_0 = input.LA(1);
						if ( ((LA16_0 >= '0' && LA16_0 <= '9')) ) {
							alt16=1;
						}

						switch (alt16) {
						case 1 :
							// Python.g:
							{
							if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt16 >= 1 ) break loop16;
							EarlyExitException eee = new EarlyExitException(16, input);
							throw eee;
						}
						cnt16++;
					}

					// Python.g:2594:25: ( '_' ( '0' .. '9' )+ )*
					loop18:
					while (true) {
						int alt18=2;
						int LA18_0 = input.LA(1);
						if ( (LA18_0=='_') ) {
							alt18=1;
						}

						switch (alt18) {
						case 1 :
							// Python.g:2594:26: '_' ( '0' .. '9' )+
							{
							match('_'); 
							// Python.g:2594:30: ( '0' .. '9' )+
							int cnt17=0;
							loop17:
							while (true) {
								int alt17=2;
								int LA17_0 = input.LA(1);
								if ( ((LA17_0 >= '0' && LA17_0 <= '9')) ) {
									alt17=1;
								}

								switch (alt17) {
								case 1 :
									// Python.g:
									{
									if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
										input.consume();
									}
									else {
										MismatchedSetException mse = new MismatchedSetException(null,input);
										recover(mse);
										throw mse;
									}
									}
									break;

								default :
									if ( cnt17 >= 1 ) break loop17;
									EarlyExitException eee = new EarlyExitException(17, input);
									throw eee;
								}
								cnt17++;
							}

							}
							break;

						default :
							break loop18;
						}
					}

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "COMPLEX"
	public final void mCOMPLEX() throws RecognitionException {
		try {
			int _type = COMPLEX;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2598:5: ( ( DIGITS )+ ( 'j' | 'J' ) | FLOAT ( 'j' | 'J' ) )
			int alt21=2;
			alt21 = dfa21.predict(input);
			switch (alt21) {
				case 1 :
					// Python.g:2598:9: ( DIGITS )+ ( 'j' | 'J' )
					{
					// Python.g:2598:9: ( DIGITS )+
					int cnt20=0;
					loop20:
					while (true) {
						int alt20=2;
						int LA20_0 = input.LA(1);
						if ( ((LA20_0 >= '0' && LA20_0 <= '9')) ) {
							alt20=1;
						}

						switch (alt20) {
						case 1 :
							// Python.g:2598:9: DIGITS
							{
							mDIGITS(); 

							}
							break;

						default :
							if ( cnt20 >= 1 ) break loop20;
							EarlyExitException eee = new EarlyExitException(20, input);
							throw eee;
						}
						cnt20++;
					}

					if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;
				case 2 :
					// Python.g:2599:9: FLOAT ( 'j' | 'J' )
					{
					mFLOAT(); 

					if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMPLEX"

	// $ANTLR start "DIGITS"
	public final void mDIGITS() throws RecognitionException {
		try {
			// Python.g:2604:8: ( ( '0' .. '9' )+ )
			// Python.g:2604:10: ( '0' .. '9' )+
			{
			// Python.g:2604:10: ( '0' .. '9' )+
			int cnt22=0;
			loop22:
			while (true) {
				int alt22=2;
				int LA22_0 = input.LA(1);
				if ( ((LA22_0 >= '0' && LA22_0 <= '9')) ) {
					alt22=1;
				}

				switch (alt22) {
				case 1 :
					// Python.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt22 >= 1 ) break loop22;
					EarlyExitException eee = new EarlyExitException(22, input);
					throw eee;
				}
				cnt22++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DIGITS"

	// $ANTLR start "LETTER"
	public final void mLETTER() throws RecognitionException {
		try {
			// Python.g:2607:8: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u00C0' .. '\\u00D6' | '\\u00D8' .. '\\u00F6' | '\\u00F8' .. '\\u00FF' | '\\u0100' .. '\\uFFFE' | '_' )
			// Python.g:
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z')||(input.LA(1) >= '\u00C0' && input.LA(1) <= '\u00D6')||(input.LA(1) >= '\u00D8' && input.LA(1) <= '\u00F6')||(input.LA(1) >= '\u00F8' && input.LA(1) <= '\uFFFE') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LETTER"

	// $ANTLR start "NAME"
	public final void mNAME() throws RecognitionException {
		try {
			int _type = NAME;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2609:6: ( LETTER ( LETTER | DIGITS )* )
			// Python.g:2609:8: LETTER ( LETTER | DIGITS )*
			{
			mLETTER(); 

			// Python.g:2609:15: ( LETTER | DIGITS )*
			loop23:
			while (true) {
				int alt23=3;
				int LA23_0 = input.LA(1);
				if ( ((LA23_0 >= 'A' && LA23_0 <= 'Z')||LA23_0=='_'||(LA23_0 >= 'a' && LA23_0 <= 'z')||(LA23_0 >= '\u00C0' && LA23_0 <= '\u00D6')||(LA23_0 >= '\u00D8' && LA23_0 <= '\u00F6')||(LA23_0 >= '\u00F8' && LA23_0 <= '\uFFFE')) ) {
					alt23=1;
				}
				else if ( ((LA23_0 >= '0' && LA23_0 <= '9')) ) {
					alt23=2;
				}

				switch (alt23) {
				case 1 :
					// Python.g:2609:17: LETTER
					{
					mLETTER(); 

					}
					break;
				case 2 :
					// Python.g:2609:26: DIGITS
					{
					mDIGITS(); 

					}
					break;

				default :
					break loop23;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NAME"

	// $ANTLR start "STRING"
	public final void mSTRING() throws RecognitionException {
		try {
			int _type = STRING;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2619:5: ( ( 'f' | 'r' | 'u' | 'b' | 'ur' | 'br' | 'R' | 'U' | 'B' | 'UR' | 'BR' | 'uR' | 'Ur' | 'Br' | 'bR' | 'rb' | 'rB' | 'Rb' | 'RB' )? ( '\\'\\'\\'' ( options {greedy=false; } : TRIAPOS )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : TRIQUOTE )* '\"\"\"' | '\"' ( ESC |~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC |~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' ) )
			// Python.g:2619:9: ( 'f' | 'r' | 'u' | 'b' | 'ur' | 'br' | 'R' | 'U' | 'B' | 'UR' | 'BR' | 'uR' | 'Ur' | 'Br' | 'bR' | 'rb' | 'rB' | 'Rb' | 'RB' )? ( '\\'\\'\\'' ( options {greedy=false; } : TRIAPOS )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : TRIQUOTE )* '\"\"\"' | '\"' ( ESC |~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC |~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )
			{
			// Python.g:2619:9: ( 'f' | 'r' | 'u' | 'b' | 'ur' | 'br' | 'R' | 'U' | 'B' | 'UR' | 'BR' | 'uR' | 'Ur' | 'Br' | 'bR' | 'rb' | 'rB' | 'Rb' | 'RB' )?
			int alt24=20;
			switch ( input.LA(1) ) {
				case 'f':
					{
					alt24=1;
					}
					break;
				case 'r':
					{
					switch ( input.LA(2) ) {
						case 'b':
							{
							alt24=16;
							}
							break;
						case 'B':
							{
							alt24=17;
							}
							break;
						case '\"':
						case '\'':
							{
							alt24=2;
							}
							break;
					}
					}
					break;
				case 'u':
					{
					switch ( input.LA(2) ) {
						case 'r':
							{
							alt24=5;
							}
							break;
						case 'R':
							{
							alt24=12;
							}
							break;
						case '\"':
						case '\'':
							{
							alt24=3;
							}
							break;
					}
					}
					break;
				case 'b':
					{
					switch ( input.LA(2) ) {
						case 'r':
							{
							alt24=6;
							}
							break;
						case 'R':
							{
							alt24=15;
							}
							break;
						case '\"':
						case '\'':
							{
							alt24=4;
							}
							break;
					}
					}
					break;
				case 'R':
					{
					switch ( input.LA(2) ) {
						case 'b':
							{
							alt24=18;
							}
							break;
						case 'B':
							{
							alt24=19;
							}
							break;
						case '\"':
						case '\'':
							{
							alt24=7;
							}
							break;
					}
					}
					break;
				case 'U':
					{
					switch ( input.LA(2) ) {
						case 'R':
							{
							alt24=10;
							}
							break;
						case 'r':
							{
							alt24=13;
							}
							break;
						case '\"':
						case '\'':
							{
							alt24=8;
							}
							break;
					}
					}
					break;
				case 'B':
					{
					switch ( input.LA(2) ) {
						case 'R':
							{
							alt24=11;
							}
							break;
						case 'r':
							{
							alt24=14;
							}
							break;
						case '\"':
						case '\'':
							{
							alt24=9;
							}
							break;
					}
					}
					break;
			}
			switch (alt24) {
				case 1 :
					// Python.g:2619:10: 'f'
					{
					match('f'); 
					}
					break;
				case 2 :
					// Python.g:2619:14: 'r'
					{
					match('r'); 
					}
					break;
				case 3 :
					// Python.g:2619:18: 'u'
					{
					match('u'); 
					}
					break;
				case 4 :
					// Python.g:2619:22: 'b'
					{
					match('b'); 
					}
					break;
				case 5 :
					// Python.g:2619:26: 'ur'
					{
					match("ur"); 

					}
					break;
				case 6 :
					// Python.g:2619:31: 'br'
					{
					match("br"); 

					}
					break;
				case 7 :
					// Python.g:2619:36: 'R'
					{
					match('R'); 
					}
					break;
				case 8 :
					// Python.g:2619:40: 'U'
					{
					match('U'); 
					}
					break;
				case 9 :
					// Python.g:2619:44: 'B'
					{
					match('B'); 
					}
					break;
				case 10 :
					// Python.g:2619:48: 'UR'
					{
					match("UR"); 

					}
					break;
				case 11 :
					// Python.g:2619:53: 'BR'
					{
					match("BR"); 

					}
					break;
				case 12 :
					// Python.g:2619:58: 'uR'
					{
					match("uR"); 

					}
					break;
				case 13 :
					// Python.g:2619:63: 'Ur'
					{
					match("Ur"); 

					}
					break;
				case 14 :
					// Python.g:2619:68: 'Br'
					{
					match("Br"); 

					}
					break;
				case 15 :
					// Python.g:2619:73: 'bR'
					{
					match("bR"); 

					}
					break;
				case 16 :
					// Python.g:2619:78: 'rb'
					{
					match("rb"); 

					}
					break;
				case 17 :
					// Python.g:2619:83: 'rB'
					{
					match("rB"); 

					}
					break;
				case 18 :
					// Python.g:2619:88: 'Rb'
					{
					match("Rb"); 

					}
					break;
				case 19 :
					// Python.g:2619:93: 'RB'
					{
					match("RB"); 

					}
					break;

			}

			// Python.g:2620:9: ( '\\'\\'\\'' ( options {greedy=false; } : TRIAPOS )* '\\'\\'\\'' | '\"\"\"' ( options {greedy=false; } : TRIQUOTE )* '\"\"\"' | '\"' ( ESC |~ ( '\\\\' | '\\n' | '\"' ) )* '\"' | '\\'' ( ESC |~ ( '\\\\' | '\\n' | '\\'' ) )* '\\'' )
			int alt29=4;
			int LA29_0 = input.LA(1);
			if ( (LA29_0=='\'') ) {
				int LA29_1 = input.LA(2);
				if ( (LA29_1=='\'') ) {
					int LA29_3 = input.LA(3);
					if ( (LA29_3=='\'') ) {
						alt29=1;
					}

					else {
						alt29=4;
					}

				}
				else if ( ((LA29_1 >= '\u0000' && LA29_1 <= '\t')||(LA29_1 >= '\u000B' && LA29_1 <= '&')||(LA29_1 >= '(' && LA29_1 <= '\uFFFF')) ) {
					alt29=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 29, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA29_0=='\"') ) {
				int LA29_2 = input.LA(2);
				if ( (LA29_2=='\"') ) {
					int LA29_5 = input.LA(3);
					if ( (LA29_5=='\"') ) {
						alt29=2;
					}

					else {
						alt29=3;
					}

				}
				else if ( ((LA29_2 >= '\u0000' && LA29_2 <= '\t')||(LA29_2 >= '\u000B' && LA29_2 <= '!')||(LA29_2 >= '#' && LA29_2 <= '\uFFFF')) ) {
					alt29=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 29, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 29, 0, input);
				throw nvae;
			}

			switch (alt29) {
				case 1 :
					// Python.g:2620:13: '\\'\\'\\'' ( options {greedy=false; } : TRIAPOS )* '\\'\\'\\''
					{
					match("'''"); 

					// Python.g:2620:22: ( options {greedy=false; } : TRIAPOS )*
					loop25:
					while (true) {
						int alt25=2;
						int LA25_0 = input.LA(1);
						if ( (LA25_0=='\'') ) {
							int LA25_1 = input.LA(2);
							if ( (LA25_1=='\'') ) {
								int LA25_3 = input.LA(3);
								if ( (LA25_3=='\'') ) {
									alt25=2;
								}
								else if ( ((LA25_3 >= '\u0000' && LA25_3 <= '&')||(LA25_3 >= '(' && LA25_3 <= '\uFFFF')) ) {
									alt25=1;
								}

							}
							else if ( ((LA25_1 >= '\u0000' && LA25_1 <= '&')||(LA25_1 >= '(' && LA25_1 <= '\uFFFF')) ) {
								alt25=1;
							}

						}
						else if ( ((LA25_0 >= '\u0000' && LA25_0 <= '&')||(LA25_0 >= '(' && LA25_0 <= '\uFFFF')) ) {
							alt25=1;
						}

						switch (alt25) {
						case 1 :
							// Python.g:2620:47: TRIAPOS
							{
							mTRIAPOS(); 

							}
							break;

						default :
							break loop25;
						}
					}

					match("'''"); 

					}
					break;
				case 2 :
					// Python.g:2621:13: '\"\"\"' ( options {greedy=false; } : TRIQUOTE )* '\"\"\"'
					{
					match("\"\"\""); 

					// Python.g:2621:19: ( options {greedy=false; } : TRIQUOTE )*
					loop26:
					while (true) {
						int alt26=2;
						int LA26_0 = input.LA(1);
						if ( (LA26_0=='\"') ) {
							int LA26_1 = input.LA(2);
							if ( (LA26_1=='\"') ) {
								int LA26_3 = input.LA(3);
								if ( (LA26_3=='\"') ) {
									alt26=2;
								}
								else if ( ((LA26_3 >= '\u0000' && LA26_3 <= '!')||(LA26_3 >= '#' && LA26_3 <= '\uFFFF')) ) {
									alt26=1;
								}

							}
							else if ( ((LA26_1 >= '\u0000' && LA26_1 <= '!')||(LA26_1 >= '#' && LA26_1 <= '\uFFFF')) ) {
								alt26=1;
							}

						}
						else if ( ((LA26_0 >= '\u0000' && LA26_0 <= '!')||(LA26_0 >= '#' && LA26_0 <= '\uFFFF')) ) {
							alt26=1;
						}

						switch (alt26) {
						case 1 :
							// Python.g:2621:44: TRIQUOTE
							{
							mTRIQUOTE(); 

							}
							break;

						default :
							break loop26;
						}
					}

					match("\"\"\""); 

					}
					break;
				case 3 :
					// Python.g:2622:13: '\"' ( ESC |~ ( '\\\\' | '\\n' | '\"' ) )* '\"'
					{
					match('\"'); 
					// Python.g:2622:17: ( ESC |~ ( '\\\\' | '\\n' | '\"' ) )*
					loop27:
					while (true) {
						int alt27=3;
						int LA27_0 = input.LA(1);
						if ( (LA27_0=='\\') ) {
							alt27=1;
						}
						else if ( ((LA27_0 >= '\u0000' && LA27_0 <= '\t')||(LA27_0 >= '\u000B' && LA27_0 <= '!')||(LA27_0 >= '#' && LA27_0 <= '[')||(LA27_0 >= ']' && LA27_0 <= '\uFFFF')) ) {
							alt27=2;
						}

						switch (alt27) {
						case 1 :
							// Python.g:2622:18: ESC
							{
							mESC(); 

							}
							break;
						case 2 :
							// Python.g:2622:22: ~ ( '\\\\' | '\\n' | '\"' )
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop27;
						}
					}

					match('\"'); 
					}
					break;
				case 4 :
					// Python.g:2623:13: '\\'' ( ESC |~ ( '\\\\' | '\\n' | '\\'' ) )* '\\''
					{
					match('\''); 
					// Python.g:2623:18: ( ESC |~ ( '\\\\' | '\\n' | '\\'' ) )*
					loop28:
					while (true) {
						int alt28=3;
						int LA28_0 = input.LA(1);
						if ( (LA28_0=='\\') ) {
							alt28=1;
						}
						else if ( ((LA28_0 >= '\u0000' && LA28_0 <= '\t')||(LA28_0 >= '\u000B' && LA28_0 <= '&')||(LA28_0 >= '(' && LA28_0 <= '[')||(LA28_0 >= ']' && LA28_0 <= '\uFFFF')) ) {
							alt28=2;
						}

						switch (alt28) {
						case 1 :
							// Python.g:2623:19: ESC
							{
							mESC(); 

							}
							break;
						case 2 :
							// Python.g:2623:23: ~ ( '\\\\' | '\\n' | '\\'' )
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop28;
						}
					}

					match('\''); 
					}
					break;

			}


			           if (state.tokenStartLine != input.getLine()) {
			               state.tokenStartLine = input.getLine();
			               state.tokenStartCharPositionInLine = -2;
			           }
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING"

	// $ANTLR start "TRIQUOTE"
	public final void mTRIQUOTE() throws RecognitionException {
		try {
			// Python.g:2634:5: ( ( '\"' )? ( '\"' )? ( ESC |~ ( '\\\\' | '\"' ) )+ )
			// Python.g:2634:7: ( '\"' )? ( '\"' )? ( ESC |~ ( '\\\\' | '\"' ) )+
			{
			// Python.g:2634:7: ( '\"' )?
			int alt30=2;
			int LA30_0 = input.LA(1);
			if ( (LA30_0=='\"') ) {
				alt30=1;
			}
			switch (alt30) {
				case 1 :
					// Python.g:2634:7: '\"'
					{
					match('\"'); 
					}
					break;

			}

			// Python.g:2634:12: ( '\"' )?
			int alt31=2;
			int LA31_0 = input.LA(1);
			if ( (LA31_0=='\"') ) {
				alt31=1;
			}
			switch (alt31) {
				case 1 :
					// Python.g:2634:12: '\"'
					{
					match('\"'); 
					}
					break;

			}

			// Python.g:2634:17: ( ESC |~ ( '\\\\' | '\"' ) )+
			int cnt32=0;
			loop32:
			while (true) {
				int alt32=3;
				int LA32_0 = input.LA(1);
				if ( (LA32_0=='\\') ) {
					alt32=1;
				}
				else if ( ((LA32_0 >= '\u0000' && LA32_0 <= '!')||(LA32_0 >= '#' && LA32_0 <= '[')||(LA32_0 >= ']' && LA32_0 <= '\uFFFF')) ) {
					alt32=2;
				}

				switch (alt32) {
				case 1 :
					// Python.g:2634:18: ESC
					{
					mESC(); 

					}
					break;
				case 2 :
					// Python.g:2634:22: ~ ( '\\\\' | '\"' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt32 >= 1 ) break loop32;
					EarlyExitException eee = new EarlyExitException(32, input);
					throw eee;
				}
				cnt32++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRIQUOTE"

	// $ANTLR start "TRIAPOS"
	public final void mTRIAPOS() throws RecognitionException {
		try {
			// Python.g:2640:5: ( ( '\\'' )? ( '\\'' )? ( ESC |~ ( '\\\\' | '\\'' ) )+ )
			// Python.g:2640:7: ( '\\'' )? ( '\\'' )? ( ESC |~ ( '\\\\' | '\\'' ) )+
			{
			// Python.g:2640:7: ( '\\'' )?
			int alt33=2;
			int LA33_0 = input.LA(1);
			if ( (LA33_0=='\'') ) {
				alt33=1;
			}
			switch (alt33) {
				case 1 :
					// Python.g:2640:7: '\\''
					{
					match('\''); 
					}
					break;

			}

			// Python.g:2640:13: ( '\\'' )?
			int alt34=2;
			int LA34_0 = input.LA(1);
			if ( (LA34_0=='\'') ) {
				alt34=1;
			}
			switch (alt34) {
				case 1 :
					// Python.g:2640:13: '\\''
					{
					match('\''); 
					}
					break;

			}

			// Python.g:2640:19: ( ESC |~ ( '\\\\' | '\\'' ) )+
			int cnt35=0;
			loop35:
			while (true) {
				int alt35=3;
				int LA35_0 = input.LA(1);
				if ( (LA35_0=='\\') ) {
					alt35=1;
				}
				else if ( ((LA35_0 >= '\u0000' && LA35_0 <= '&')||(LA35_0 >= '(' && LA35_0 <= '[')||(LA35_0 >= ']' && LA35_0 <= '\uFFFF')) ) {
					alt35=2;
				}

				switch (alt35) {
				case 1 :
					// Python.g:2640:20: ESC
					{
					mESC(); 

					}
					break;
				case 2 :
					// Python.g:2640:24: ~ ( '\\\\' | '\\'' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt35 >= 1 ) break loop35;
					EarlyExitException eee = new EarlyExitException(35, input);
					throw eee;
				}
				cnt35++;
			}

			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TRIAPOS"

	// $ANTLR start "ESC"
	public final void mESC() throws RecognitionException {
		try {
			// Python.g:2644:5: ( '\\\\' . )
			// Python.g:2644:10: '\\\\' .
			{
			match('\\'); 
			matchAny(); 
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ESC"

	// $ANTLR start "CONTINUED_LINE"
	public final void mCONTINUED_LINE() throws RecognitionException {
		try {
			int _type = CONTINUED_LINE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			CommonToken nl=null;

			// Python.g:2655:5: ( '\\\\' ( '\\r' )? '\\n' ( ' ' | '\\t' )* ( COMMENT |nl= NEWLINE |) )
			// Python.g:2655:10: '\\\\' ( '\\r' )? '\\n' ( ' ' | '\\t' )* ( COMMENT |nl= NEWLINE |)
			{
			match('\\'); 
			// Python.g:2655:15: ( '\\r' )?
			int alt36=2;
			int LA36_0 = input.LA(1);
			if ( (LA36_0=='\r') ) {
				alt36=1;
			}
			switch (alt36) {
				case 1 :
					// Python.g:2655:16: '\\r'
					{
					match('\r'); 
					}
					break;

			}

			match('\n'); 
			// Python.g:2655:28: ( ' ' | '\\t' )*
			loop37:
			while (true) {
				int alt37=2;
				int LA37_0 = input.LA(1);
				if ( (LA37_0=='\t'||LA37_0==' ') ) {
					alt37=1;
				}

				switch (alt37) {
				case 1 :
					// Python.g:
					{
					if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop37;
				}
			}

			 _channel=HIDDEN; 
			// Python.g:2656:10: ( COMMENT |nl= NEWLINE |)
			int alt38=3;
			int LA38_0 = input.LA(1);
			if ( (LA38_0=='\t'||LA38_0==' ') && ((startPos==0))) {
				alt38=1;
			}
			else if ( (LA38_0=='#') ) {
				alt38=1;
			}
			else if ( (LA38_0=='\n'||(LA38_0 >= '\f' && LA38_0 <= '\r')) ) {
				alt38=2;
			}

			else {
				alt38=3;
			}

			switch (alt38) {
				case 1 :
					// Python.g:2656:12: COMMENT
					{
					mCOMMENT(); 

					}
					break;
				case 2 :
					// Python.g:2657:12: nl= NEWLINE
					{
					int nlStart1769 = getCharIndex();
					int nlStartLine1769 = getLine();
					int nlStartCharPos1769 = getCharPositionInLine();
					mNEWLINE(); 
					nl = new CommonToken(input, Token.INVALID_TOKEN_TYPE, Token.DEFAULT_CHANNEL, nlStart1769, getCharIndex()-1);
					nl.setLine(nlStartLine1769);
					nl.setCharPositionInLine(nlStartCharPos1769);


					               emit(new CommonToken(NEWLINE,nl.getText()));
					           
					}
					break;
				case 3 :
					// Python.g:2662:10: 
					{
					}
					break;

			}


			               if (input.LA(1) == -1) {
			                   throw new ParseException("unexpected character after line continuation character");
			               }
			           
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONTINUED_LINE"

	// $ANTLR start "NEWLINE"
	public final void mNEWLINE() throws RecognitionException {
		try {
			int _type = NEWLINE;
			int _channel = DEFAULT_TOKEN_CHANNEL;

			    int newlines = 0;

			// Python.g:2681:5: ( ( ( '\\u000C' )? ( '\\r' )? '\\n' )+ )
			// Python.g:2681:9: ( ( '\\u000C' )? ( '\\r' )? '\\n' )+
			{
			// Python.g:2681:9: ( ( '\\u000C' )? ( '\\r' )? '\\n' )+
			int cnt41=0;
			loop41:
			while (true) {
				int alt41=2;
				int LA41_0 = input.LA(1);
				if ( (LA41_0=='\n'||(LA41_0 >= '\f' && LA41_0 <= '\r')) ) {
					alt41=1;
				}

				switch (alt41) {
				case 1 :
					// Python.g:2681:10: ( '\\u000C' )? ( '\\r' )? '\\n'
					{
					// Python.g:2681:10: ( '\\u000C' )?
					int alt39=2;
					int LA39_0 = input.LA(1);
					if ( (LA39_0=='\f') ) {
						alt39=1;
					}
					switch (alt39) {
						case 1 :
							// Python.g:2681:11: '\\u000C'
							{
							match('\f'); 
							}
							break;

					}

					// Python.g:2681:21: ( '\\r' )?
					int alt40=2;
					int LA40_0 = input.LA(1);
					if ( (LA40_0=='\r') ) {
						alt40=1;
					}
					switch (alt40) {
						case 1 :
							// Python.g:2681:22: '\\r'
							{
							match('\r'); 
							}
							break;

					}

					match('\n'); 
					newlines++; 
					}
					break;

				default :
					if ( cnt41 >= 1 ) break loop41;
					EarlyExitException eee = new EarlyExitException(41, input);
					throw eee;
				}
				cnt41++;
			}


			         if ( startPos==0 || implicitLineJoiningLevel>0 )
			            _channel=HIDDEN;
			        
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NEWLINE"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// Python.g:2681:5: ({...}? => ( ' ' | '\\t' | '\\u000C' )+ )
			// Python.g:2681:10: {...}? => ( ' ' | '\\t' | '\\u000C' )+
			{
			if ( !((startPos>0)) ) {
				throw new FailedPredicateException(input, "WS", "startPos>0");
			}
			// Python.g:2681:26: ( ' ' | '\\t' | '\\u000C' )+
			int cnt42=0;
			loop42:
			while (true) {
				int alt42=2;
				int LA42_0 = input.LA(1);
				if ( (LA42_0=='\t'||LA42_0=='\f'||LA42_0==' ') ) {
					alt42=1;
				}

				switch (alt42) {
				case 1 :
					// Python.g:
					{
					if ( input.LA(1)=='\t'||input.LA(1)=='\f'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt42 >= 1 ) break loop42;
					EarlyExitException eee = new EarlyExitException(42, input);
					throw eee;
				}
				cnt42++;
			}

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "LEADING_WS"
	public final void mLEADING_WS() throws RecognitionException {
		try {
			int _type = LEADING_WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;

			    int spaces = 0;
			    int newlines = 0;

			// Python.g:2701:5: ({...}? => ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* ) )
			// Python.g:2701:9: {...}? => ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )
			{
			if ( !((startPos==0)) ) {
				throw new FailedPredicateException(input, "LEADING_WS", "startPos==0");
			}
			// Python.g:2702:9: ({...}? ( ' ' | '\\t' )+ | ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )* )
			int alt47=2;
			int LA47_0 = input.LA(1);
			if ( (LA47_0==' ') ) {
				int LA47_1 = input.LA(2);
				if ( ((implicitLineJoiningLevel>0)) ) {
					alt47=1;
				}
				else if ( (true) ) {
					alt47=2;
				}

			}
			else if ( (LA47_0=='\t') ) {
				int LA47_2 = input.LA(2);
				if ( ((implicitLineJoiningLevel>0)) ) {
					alt47=1;
				}
				else if ( (true) ) {
					alt47=2;
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 47, 0, input);
				throw nvae;
			}

			switch (alt47) {
				case 1 :
					// Python.g:2702:13: {...}? ( ' ' | '\\t' )+
					{
					if ( !((implicitLineJoiningLevel>0)) ) {
						throw new FailedPredicateException(input, "LEADING_WS", "implicitLineJoiningLevel>0");
					}
					// Python.g:2702:43: ( ' ' | '\\t' )+
					int cnt43=0;
					loop43:
					while (true) {
						int alt43=2;
						int LA43_0 = input.LA(1);
						if ( (LA43_0=='\t'||LA43_0==' ') ) {
							alt43=1;
						}

						switch (alt43) {
						case 1 :
							// Python.g:
							{
							if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							if ( cnt43 >= 1 ) break loop43;
							EarlyExitException eee = new EarlyExitException(43, input);
							throw eee;
						}
						cnt43++;
					}

					_channel=HIDDEN;
					}
					break;
				case 2 :
					// Python.g:2703:14: ( ' ' | '\\t' )+ ( ( '\\r' )? '\\n' )*
					{
					// Python.g:2703:14: ( ' ' | '\\t' )+
					int cnt44=0;
					loop44:
					while (true) {
						int alt44=3;
						int LA44_0 = input.LA(1);
						if ( (LA44_0==' ') ) {
							alt44=1;
						}
						else if ( (LA44_0=='\t') ) {
							alt44=2;
						}

						switch (alt44) {
						case 1 :
							// Python.g:2703:20: ' '
							{
							match(' '); 
							 spaces++; 
							}
							break;
						case 2 :
							// Python.g:2704:19: '\\t'
							{
							match('\t'); 
							 spaces += 8; spaces -= (spaces % 8); 
							}
							break;

						default :
							if ( cnt44 >= 1 ) break loop44;
							EarlyExitException eee = new EarlyExitException(44, input);
							throw eee;
						}
						cnt44++;
					}

					// Python.g:2706:14: ( ( '\\r' )? '\\n' )*
					loop46:
					while (true) {
						int alt46=2;
						int LA46_0 = input.LA(1);
						if ( (LA46_0=='\n'||LA46_0=='\r') ) {
							alt46=1;
						}

						switch (alt46) {
						case 1 :
							// Python.g:2706:16: ( '\\r' )? '\\n'
							{
							// Python.g:2706:16: ( '\\r' )?
							int alt45=2;
							int LA45_0 = input.LA(1);
							if ( (LA45_0=='\r') ) {
								alt45=1;
							}
							switch (alt45) {
								case 1 :
									// Python.g:2706:17: '\\r'
									{
									match('\r'); 
									}
									break;

							}

							match('\n'); 
							newlines++; 
							}
							break;

						default :
							break loop46;
						}
					}


					                   if (input.LA(1) != -1 || newlines == 0) {
					                       // make a string of n spaces where n is column number - 1
					                       char[] indentation = new char[spaces];
					                       for (int i=0; i<spaces; i++) {
					                           indentation[i] = ' ';
					                       }
					                       CommonToken c = new CommonToken(LEADING_WS,new String(indentation));
					                       c.setLine(input.getLine());
					                       c.setCharPositionInLine(input.getCharPositionInLine());
					                       c.setStartIndex(input.index() - 1);
					                       c.setStopIndex(input.index() - 1);
					                       emit(c);
					                       // kill trailing newline if present and then ignore
					                       if (newlines != 0) {
					                           if (state.token!=null) {
					                               state.token.setChannel(HIDDEN);
					                           } else {
					                               _channel=HIDDEN;
					                           }
					                       }
					                   } else if (this.single && newlines == 1) {
					                       // This is here for this case in interactive mode:
					                       //
					                       // def foo():
					                       //   print 1
					                       //   <spaces but no code>
					                       //
					                       // The above would complete in interactive mode instead
					                       // of giving ... to wait for more input.
					                       //
					                       throw new ParseException("Trailing space in single mode.");
					                   } else {
					                       // make a string of n newlines
					                       char[] nls = new char[newlines];
					                       for (int i=0; i<newlines; i++) {
					                           nls[i] = '\n';
					                       }
					                       CommonToken c = new CommonToken(NEWLINE,new String(nls));
					                       c.setLine(input.getLine());
					                       c.setCharPositionInLine(input.getCharPositionInLine());
					                       c.setStartIndex(input.index() - 1);
					                       c.setStopIndex(input.index() - 1);
					                       emit(c);
					                   }
					                
					}
					break;

			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "LEADING_WS"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;

			    _channel=HIDDEN;

			// Python.g:2788:5: ({...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+ | '#' (~ '\\n' )* )
			int alt52=2;
			alt52 = dfa52.predict(input);
			switch (alt52) {
				case 1 :
					// Python.g:2788:10: {...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+
					{
					if ( !((startPos==0)) ) {
						throw new FailedPredicateException(input, "COMMENT", "startPos==0");
					}
					// Python.g:2788:27: ( ' ' | '\\t' )*
					loop48:
					while (true) {
						int alt48=2;
						int LA48_0 = input.LA(1);
						if ( (LA48_0=='\t'||LA48_0==' ') ) {
							alt48=1;
						}

						switch (alt48) {
						case 1 :
							// Python.g:
							{
							if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop48;
						}
					}

					match('#'); 
					// Python.g:2788:43: (~ '\\n' )*
					loop49:
					while (true) {
						int alt49=2;
						int LA49_0 = input.LA(1);
						if ( ((LA49_0 >= '\u0000' && LA49_0 <= '\t')||(LA49_0 >= '\u000B' && LA49_0 <= '\uFFFF')) ) {
							alt49=1;
						}

						switch (alt49) {
						case 1 :
							// Python.g:
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop49;
						}
					}

					// Python.g:2788:52: ( '\\n' )+
					int cnt50=0;
					loop50:
					while (true) {
						int alt50=2;
						int LA50_0 = input.LA(1);
						if ( (LA50_0=='\n') ) {
							alt50=1;
						}

						switch (alt50) {
						case 1 :
							// Python.g:2788:52: '\\n'
							{
							match('\n'); 
							}
							break;

						default :
							if ( cnt50 >= 1 ) break loop50;
							EarlyExitException eee = new EarlyExitException(50, input);
							throw eee;
						}
						cnt50++;
					}

					}
					break;
				case 2 :
					// Python.g:2789:10: '#' (~ '\\n' )*
					{
					match('#'); 
					// Python.g:2789:14: (~ '\\n' )*
					loop51:
					while (true) {
						int alt51=2;
						int LA51_0 = input.LA(1);
						if ( ((LA51_0 >= '\u0000' && LA51_0 <= '\t')||(LA51_0 >= '\u000B' && LA51_0 <= '\uFFFF')) ) {
							alt51=1;
						}

						switch (alt51) {
						case 1 :
							// Python.g:
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop51;
						}
					}

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	@Override
	public void mTokens() throws RecognitionException {
		// Python.g:1:8: ( AS | ASSERT | ASYNC | AWAIT | BREAK | CLASS | CONTINUE | DEF | DELETE | ELIF | EXCEPT | EXEC | FINALLY | FROM | FOR | GLOBAL | IF | IMPORT | IN | IS | LAMBDA | NONLOCAL | ORELSE | PASS | RAISE | RETURN | TRY | WHILE | WITH | YIELD | LPAREN | RPAREN | LBRACK | RBRACK | COLON | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | VBAR | AMPER | LESS | GREATER | ASSIGN | PERCENT | BACKQUOTE | LCURLY | RCURLY | CIRCUMFLEX | DOLLER | TILDE | EQUAL | NOTEQUAL | ALT_NOTEQUAL | LESSEQUAL | LEFTSHIFT | GREATEREQUAL | RIGHTSHIFT | PLUSEQUAL | MINUSEQUAL | DOUBLESTAR | STAREQUAL | ATEQUAL | DOUBLESLASH | SLASHEQUAL | VBAREQUAL | PERCENTEQUAL | AMPEREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL | ARROW | DOT | AT | AND | OR | NOT | FLOAT | LONGINT | INT | COMPLEX | NAME | STRING | CONTINUED_LINE | NEWLINE | WS | LEADING_WS | COMMENT )
		int alt53=92;
		alt53 = dfa53.predict(input);
		switch (alt53) {
			case 1 :
				// Python.g:1:10: AS
				{
				mAS(); 

				}
				break;
			case 2 :
				// Python.g:1:13: ASSERT
				{
				mASSERT(); 

				}
				break;
			case 3 :
				// Python.g:1:20: ASYNC
				{
				mASYNC(); 

				}
				break;
			case 4 :
				// Python.g:1:26: AWAIT
				{
				mAWAIT(); 

				}
				break;
			case 5 :
				// Python.g:1:32: BREAK
				{
				mBREAK(); 

				}
				break;
			case 6 :
				// Python.g:1:38: CLASS
				{
				mCLASS(); 

				}
				break;
			case 7 :
				// Python.g:1:44: CONTINUE
				{
				mCONTINUE(); 

				}
				break;
			case 8 :
				// Python.g:1:53: DEF
				{
				mDEF(); 

				}
				break;
			case 9 :
				// Python.g:1:57: DELETE
				{
				mDELETE(); 

				}
				break;
			case 10 :
				// Python.g:1:64: ELIF
				{
				mELIF(); 

				}
				break;
			case 11 :
				// Python.g:1:69: EXCEPT
				{
				mEXCEPT(); 

				}
				break;
			case 12 :
				// Python.g:1:76: EXEC
				{
				mEXEC(); 

				}
				break;
			case 13 :
				// Python.g:1:81: FINALLY
				{
				mFINALLY(); 

				}
				break;
			case 14 :
				// Python.g:1:89: FROM
				{
				mFROM(); 

				}
				break;
			case 15 :
				// Python.g:1:94: FOR
				{
				mFOR(); 

				}
				break;
			case 16 :
				// Python.g:1:98: GLOBAL
				{
				mGLOBAL(); 

				}
				break;
			case 17 :
				// Python.g:1:105: IF
				{
				mIF(); 

				}
				break;
			case 18 :
				// Python.g:1:108: IMPORT
				{
				mIMPORT(); 

				}
				break;
			case 19 :
				// Python.g:1:115: IN
				{
				mIN(); 

				}
				break;
			case 20 :
				// Python.g:1:118: IS
				{
				mIS(); 

				}
				break;
			case 21 :
				// Python.g:1:121: LAMBDA
				{
				mLAMBDA(); 

				}
				break;
			case 22 :
				// Python.g:1:128: NONLOCAL
				{
				mNONLOCAL(); 

				}
				break;
			case 23 :
				// Python.g:1:137: ORELSE
				{
				mORELSE(); 

				}
				break;
			case 24 :
				// Python.g:1:144: PASS
				{
				mPASS(); 

				}
				break;
			case 25 :
				// Python.g:1:149: RAISE
				{
				mRAISE(); 

				}
				break;
			case 26 :
				// Python.g:1:155: RETURN
				{
				mRETURN(); 

				}
				break;
			case 27 :
				// Python.g:1:162: TRY
				{
				mTRY(); 

				}
				break;
			case 28 :
				// Python.g:1:166: WHILE
				{
				mWHILE(); 

				}
				break;
			case 29 :
				// Python.g:1:172: WITH
				{
				mWITH(); 

				}
				break;
			case 30 :
				// Python.g:1:177: YIELD
				{
				mYIELD(); 

				}
				break;
			case 31 :
				// Python.g:1:183: LPAREN
				{
				mLPAREN(); 

				}
				break;
			case 32 :
				// Python.g:1:190: RPAREN
				{
				mRPAREN(); 

				}
				break;
			case 33 :
				// Python.g:1:197: LBRACK
				{
				mLBRACK(); 

				}
				break;
			case 34 :
				// Python.g:1:204: RBRACK
				{
				mRBRACK(); 

				}
				break;
			case 35 :
				// Python.g:1:211: COLON
				{
				mCOLON(); 

				}
				break;
			case 36 :
				// Python.g:1:217: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 37 :
				// Python.g:1:223: SEMI
				{
				mSEMI(); 

				}
				break;
			case 38 :
				// Python.g:1:228: PLUS
				{
				mPLUS(); 

				}
				break;
			case 39 :
				// Python.g:1:233: MINUS
				{
				mMINUS(); 

				}
				break;
			case 40 :
				// Python.g:1:239: STAR
				{
				mSTAR(); 

				}
				break;
			case 41 :
				// Python.g:1:244: SLASH
				{
				mSLASH(); 

				}
				break;
			case 42 :
				// Python.g:1:250: VBAR
				{
				mVBAR(); 

				}
				break;
			case 43 :
				// Python.g:1:255: AMPER
				{
				mAMPER(); 

				}
				break;
			case 44 :
				// Python.g:1:261: LESS
				{
				mLESS(); 

				}
				break;
			case 45 :
				// Python.g:1:266: GREATER
				{
				mGREATER(); 

				}
				break;
			case 46 :
				// Python.g:1:274: ASSIGN
				{
				mASSIGN(); 

				}
				break;
			case 47 :
				// Python.g:1:281: PERCENT
				{
				mPERCENT(); 

				}
				break;
			case 48 :
				// Python.g:1:289: BACKQUOTE
				{
				mBACKQUOTE(); 

				}
				break;
			case 49 :
				// Python.g:1:299: LCURLY
				{
				mLCURLY(); 

				}
				break;
			case 50 :
				// Python.g:1:306: RCURLY
				{
				mRCURLY(); 

				}
				break;
			case 51 :
				// Python.g:1:313: CIRCUMFLEX
				{
				mCIRCUMFLEX(); 

				}
				break;
			case 52 :
				// Python.g:1:324: DOLLER
				{
				mDOLLER(); 

				}
				break;
			case 53 :
				// Python.g:1:331: TILDE
				{
				mTILDE(); 

				}
				break;
			case 54 :
				// Python.g:1:337: EQUAL
				{
				mEQUAL(); 

				}
				break;
			case 55 :
				// Python.g:1:343: NOTEQUAL
				{
				mNOTEQUAL(); 

				}
				break;
			case 56 :
				// Python.g:1:352: ALT_NOTEQUAL
				{
				mALT_NOTEQUAL(); 

				}
				break;
			case 57 :
				// Python.g:1:365: LESSEQUAL
				{
				mLESSEQUAL(); 

				}
				break;
			case 58 :
				// Python.g:1:375: LEFTSHIFT
				{
				mLEFTSHIFT(); 

				}
				break;
			case 59 :
				// Python.g:1:385: GREATEREQUAL
				{
				mGREATEREQUAL(); 

				}
				break;
			case 60 :
				// Python.g:1:398: RIGHTSHIFT
				{
				mRIGHTSHIFT(); 

				}
				break;
			case 61 :
				// Python.g:1:409: PLUSEQUAL
				{
				mPLUSEQUAL(); 

				}
				break;
			case 62 :
				// Python.g:1:419: MINUSEQUAL
				{
				mMINUSEQUAL(); 

				}
				break;
			case 63 :
				// Python.g:1:430: DOUBLESTAR
				{
				mDOUBLESTAR(); 

				}
				break;
			case 64 :
				// Python.g:1:441: STAREQUAL
				{
				mSTAREQUAL(); 

				}
				break;
			case 65 :
				// Python.g:1:451: ATEQUAL
				{
				mATEQUAL(); 

				}
				break;
			case 66 :
				// Python.g:1:459: DOUBLESLASH
				{
				mDOUBLESLASH(); 

				}
				break;
			case 67 :
				// Python.g:1:471: SLASHEQUAL
				{
				mSLASHEQUAL(); 

				}
				break;
			case 68 :
				// Python.g:1:482: VBAREQUAL
				{
				mVBAREQUAL(); 

				}
				break;
			case 69 :
				// Python.g:1:492: PERCENTEQUAL
				{
				mPERCENTEQUAL(); 

				}
				break;
			case 70 :
				// Python.g:1:505: AMPEREQUAL
				{
				mAMPEREQUAL(); 

				}
				break;
			case 71 :
				// Python.g:1:516: CIRCUMFLEXEQUAL
				{
				mCIRCUMFLEXEQUAL(); 

				}
				break;
			case 72 :
				// Python.g:1:532: LEFTSHIFTEQUAL
				{
				mLEFTSHIFTEQUAL(); 

				}
				break;
			case 73 :
				// Python.g:1:547: RIGHTSHIFTEQUAL
				{
				mRIGHTSHIFTEQUAL(); 

				}
				break;
			case 74 :
				// Python.g:1:563: DOUBLESTAREQUAL
				{
				mDOUBLESTAREQUAL(); 

				}
				break;
			case 75 :
				// Python.g:1:579: DOUBLESLASHEQUAL
				{
				mDOUBLESLASHEQUAL(); 

				}
				break;
			case 76 :
				// Python.g:1:596: ARROW
				{
				mARROW(); 

				}
				break;
			case 77 :
				// Python.g:1:602: DOT
				{
				mDOT(); 

				}
				break;
			case 78 :
				// Python.g:1:606: AT
				{
				mAT(); 

				}
				break;
			case 79 :
				// Python.g:1:609: AND
				{
				mAND(); 

				}
				break;
			case 80 :
				// Python.g:1:613: OR
				{
				mOR(); 

				}
				break;
			case 81 :
				// Python.g:1:616: NOT
				{
				mNOT(); 

				}
				break;
			case 82 :
				// Python.g:1:620: FLOAT
				{
				mFLOAT(); 

				}
				break;
			case 83 :
				// Python.g:1:626: LONGINT
				{
				mLONGINT(); 

				}
				break;
			case 84 :
				// Python.g:1:634: INT
				{
				mINT(); 

				}
				break;
			case 85 :
				// Python.g:1:638: COMPLEX
				{
				mCOMPLEX(); 

				}
				break;
			case 86 :
				// Python.g:1:646: NAME
				{
				mNAME(); 

				}
				break;
			case 87 :
				// Python.g:1:651: STRING
				{
				mSTRING(); 

				}
				break;
			case 88 :
				// Python.g:1:658: CONTINUED_LINE
				{
				mCONTINUED_LINE(); 

				}
				break;
			case 89 :
				// Python.g:1:673: NEWLINE
				{
				mNEWLINE(); 

				}
				break;
			case 90 :
				// Python.g:1:681: WS
				{
				mWS(); 

				}
				break;
			case 91 :
				// Python.g:1:684: LEADING_WS
				{
				mLEADING_WS(); 

				}
				break;
			case 92 :
				// Python.g:1:695: COMMENT
				{
				mCOMMENT(); 

				}
				break;

		}
	}


	protected DFA5 dfa5 = new DFA5(this);
	protected DFA21 dfa21 = new DFA21(this);
	protected DFA52 dfa52 = new DFA52(this);
	protected DFA53 dfa53 = new DFA53(this);
	static final String DFA5_eotS =
		"\3\uffff\1\4\2\uffff";
	static final String DFA5_eofS =
		"\6\uffff";
	static final String DFA5_minS =
		"\1\56\1\uffff\1\56\1\105\2\uffff";
	static final String DFA5_maxS =
		"\1\71\1\uffff\2\145\2\uffff";
	static final String DFA5_acceptS =
		"\1\uffff\1\1\2\uffff\1\3\1\2";
	static final String DFA5_specialS =
		"\6\uffff}>";
	static final String[] DFA5_transitionS = {
			"\1\1\1\uffff\12\2",
			"",
			"\1\3\1\uffff\12\2\13\uffff\1\4\37\uffff\1\4",
			"\1\5\37\uffff\1\5",
			"",
			""
	};

	static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
	static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
	static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
	static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
	static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
	static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
	static final short[][] DFA5_transition;

	static {
		int numStates = DFA5_transitionS.length;
		DFA5_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
		}
	}

	protected class DFA5 extends DFA {

		public DFA5(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 5;
			this.eot = DFA5_eot;
			this.eof = DFA5_eof;
			this.min = DFA5_min;
			this.max = DFA5_max;
			this.accept = DFA5_accept;
			this.special = DFA5_special;
			this.transition = DFA5_transition;
		}
		@Override
		public String getDescription() {
			return "2572:1: FLOAT : ( '.' DIGITS ( Exponent )? | DIGITS '.' Exponent | DIGITS ( '.' ( DIGITS ( Exponent )? )? | Exponent ) );";
		}
	}

	static final String DFA21_eotS =
		"\4\uffff";
	static final String DFA21_eofS =
		"\4\uffff";
	static final String DFA21_minS =
		"\2\56\2\uffff";
	static final String DFA21_maxS =
		"\1\71\1\152\2\uffff";
	static final String DFA21_acceptS =
		"\2\uffff\1\2\1\1";
	static final String DFA21_specialS =
		"\4\uffff}>";
	static final String[] DFA21_transitionS = {
			"\1\2\1\uffff\12\1",
			"\1\2\1\uffff\12\1\13\uffff\1\2\4\uffff\1\3\32\uffff\1\2\4\uffff\1\3",
			"",
			""
	};

	static final short[] DFA21_eot = DFA.unpackEncodedString(DFA21_eotS);
	static final short[] DFA21_eof = DFA.unpackEncodedString(DFA21_eofS);
	static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars(DFA21_minS);
	static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars(DFA21_maxS);
	static final short[] DFA21_accept = DFA.unpackEncodedString(DFA21_acceptS);
	static final short[] DFA21_special = DFA.unpackEncodedString(DFA21_specialS);
	static final short[][] DFA21_transition;

	static {
		int numStates = DFA21_transitionS.length;
		DFA21_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA21_transition[i] = DFA.unpackEncodedString(DFA21_transitionS[i]);
		}
	}

	protected class DFA21 extends DFA {

		public DFA21(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 21;
			this.eot = DFA21_eot;
			this.eof = DFA21_eof;
			this.min = DFA21_min;
			this.max = DFA21_max;
			this.accept = DFA21_accept;
			this.special = DFA21_special;
			this.transition = DFA21_transition;
		}
		@Override
		public String getDescription() {
			return "2597:1: COMPLEX : ( ( DIGITS )+ ( 'j' | 'J' ) | FLOAT ( 'j' | 'J' ) );";
		}
	}

	static final String DFA52_eotS =
		"\2\uffff\2\4\1\uffff";
	static final String DFA52_eofS =
		"\5\uffff";
	static final String DFA52_minS =
		"\1\11\1\uffff\2\0\1\uffff";
	static final String DFA52_maxS =
		"\1\43\1\uffff\2\uffff\1\uffff";
	static final String DFA52_acceptS =
		"\1\uffff\1\1\2\uffff\1\2";
	static final String DFA52_specialS =
		"\1\1\1\uffff\1\0\1\2\1\uffff}>";
	static final String[] DFA52_transitionS = {
			"\1\1\26\uffff\1\1\2\uffff\1\2",
			"",
			"\12\3\1\1\ufff5\3",
			"\12\3\1\1\ufff5\3",
			""
	};

	static final short[] DFA52_eot = DFA.unpackEncodedString(DFA52_eotS);
	static final short[] DFA52_eof = DFA.unpackEncodedString(DFA52_eofS);
	static final char[] DFA52_min = DFA.unpackEncodedStringToUnsignedChars(DFA52_minS);
	static final char[] DFA52_max = DFA.unpackEncodedStringToUnsignedChars(DFA52_maxS);
	static final short[] DFA52_accept = DFA.unpackEncodedString(DFA52_acceptS);
	static final short[] DFA52_special = DFA.unpackEncodedString(DFA52_specialS);
	static final short[][] DFA52_transition;

	static {
		int numStates = DFA52_transitionS.length;
		DFA52_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA52_transition[i] = DFA.unpackEncodedString(DFA52_transitionS[i]);
		}
	}

	protected class DFA52 extends DFA {

		public DFA52(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 52;
			this.eot = DFA52_eot;
			this.eof = DFA52_eof;
			this.min = DFA52_min;
			this.max = DFA52_max;
			this.accept = DFA52_accept;
			this.special = DFA52_special;
			this.transition = DFA52_transition;
		}
		@Override
		public String getDescription() {
			return "2784:1: COMMENT : ({...}? => ( ' ' | '\\t' )* '#' (~ '\\n' )* ( '\\n' )+ | '#' (~ '\\n' )* );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA52_2 = input.LA(1);
						 
						int index52_2 = input.index();
						input.rewind();
						s = -1;
						if ( ((LA52_2 >= '\u0000' && LA52_2 <= '\t')||(LA52_2 >= '\u000B' && LA52_2 <= '\uFFFF')) ) {s = 3;}
						else if ( (LA52_2=='\n') && ((startPos==0))) {s = 1;}
						else s = 4;
						 
						input.seek(index52_2);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA52_0 = input.LA(1);
						 
						int index52_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA52_0=='\t'||LA52_0==' ') && ((startPos==0))) {s = 1;}
						else if ( (LA52_0=='#') ) {s = 2;}
						 
						input.seek(index52_0);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA52_3 = input.LA(1);
						 
						int index52_3 = input.index();
						input.rewind();
						s = -1;
						if ( (LA52_3=='\n') && ((startPos==0))) {s = 1;}
						else if ( ((LA52_3 >= '\u0000' && LA52_3 <= '\t')||(LA52_3 >= '\u000B' && LA52_3 <= '\uFFFF')) ) {s = 3;}
						else s = 4;
						 
						input.seek(index52_3);
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 52, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	static final String DFA53_eotS =
		"\1\uffff\17\61\7\uffff\1\127\1\132\1\135\1\140\1\142\1\144\1\150\1\153"+
		"\1\155\1\157\3\uffff\1\161\3\uffff\1\163\1\164\1\61\2\172\4\61\3\uffff"+
		"\1\u0088\1\uffff\2\u0089\1\uffff\1\u008e\15\61\1\u009e\1\61\1\u00a0\1"+
		"\u00a1\13\61\5\uffff\1\u00ad\2\uffff\1\u00af\10\uffff\1\u00b1\2\uffff"+
		"\1\u00b3\12\uffff\1\u00b4\1\u00b6\4\uffff\1\u00b4\4\uffff\10\61\4\uffff"+
		"\2\61\1\uffff\1\61\1\u00c2\3\61\1\u00c6\1\u00c7\6\61\1\u00ce\1\61\1\uffff"+
		"\1\61\2\uffff\2\61\1\u00d3\3\61\1\u00d7\3\61\13\uffff\3\172\1\uffff\1"+
		"\u00b4\1\uffff\1\u00b4\1\172\3\61\1\uffff\3\61\2\uffff\1\u00e9\1\u00ea"+
		"\3\61\1\u00ee\1\uffff\4\61\1\uffff\1\u00f3\2\61\1\uffff\1\61\1\u00f7\1"+
		"\61\1\uffff\1\u00b4\4\uffff\1\u00b4\1\uffff\1\61\1\u00ff\1\u0100\1\u0101"+
		"\1\u0102\1\61\2\uffff\1\61\1\u0105\1\61\1\uffff\4\61\1\uffff\1\u010b\1"+
		"\61\1\u010d\1\uffff\1\u010e\3\172\1\uffff\1\u00b4\1\u010f\4\uffff\1\61"+
		"\1\u0111\1\uffff\1\61\1\u0113\1\u0114\1\u0115\1\61\1\uffff\1\u0117\3\uffff"+
		"\1\61\1\uffff\1\u0119\3\uffff\1\61\1\uffff\1\u011b\1\uffff\1\u011c\2\uffff";
	static final String DFA53_eofS =
		"\u011d\uffff";
	static final String DFA53_minS =
		"\1\11\1\156\1\42\1\154\1\145\1\154\1\42\1\154\1\146\1\141\1\157\1\141"+
		"\1\42\1\162\1\150\1\151\7\uffff\2\75\1\52\1\57\2\75\1\74\3\75\3\uffff"+
		"\1\75\3\uffff\1\75\1\60\1\162\2\56\4\42\3\uffff\1\12\1\uffff\2\11\1\uffff"+
		"\1\60\1\141\1\144\2\42\1\141\1\156\1\146\1\151\1\143\1\156\1\157\1\162"+
		"\1\157\1\60\1\160\2\60\1\155\1\156\1\163\1\151\1\164\2\42\1\171\1\151"+
		"\1\164\1\145\5\uffff\1\75\2\uffff\1\75\10\uffff\1\75\2\uffff\1\75\12\uffff"+
		"\5\60\1\uffff\1\60\1\53\1\60\2\uffff\10\42\1\uffff\1\0\2\uffff\1\145\1"+
		"\156\1\uffff\1\151\1\60\1\141\1\163\1\164\2\60\1\146\2\145\1\143\1\141"+
		"\1\155\1\60\1\142\1\uffff\1\157\2\uffff\1\142\1\154\1\60\2\163\1\165\1"+
		"\60\1\154\1\150\1\154\11\uffff\1\53\1\uffff\3\60\1\53\4\60\1\162\1\143"+
		"\1\164\1\uffff\1\153\1\163\1\151\2\uffff\2\60\1\160\1\61\1\154\1\60\1"+
		"\uffff\1\141\1\162\1\144\1\157\1\uffff\1\60\1\145\1\162\1\uffff\1\145"+
		"\1\60\1\144\7\60\1\53\1\164\4\60\1\156\2\uffff\1\164\1\60\1\154\1\uffff"+
		"\1\154\1\164\1\141\1\143\1\uffff\1\60\1\156\1\60\1\uffff\7\60\4\uffff"+
		"\1\165\1\60\1\uffff\1\171\3\60\1\141\1\uffff\1\60\3\uffff\1\145\1\uffff"+
		"\1\60\3\uffff\1\154\1\uffff\1\60\1\uffff\1\60\2\uffff";
	static final String DFA53_maxS =
		"\1\ufffe\1\167\1\162\1\157\1\145\1\170\1\162\1\154\1\163\1\141\1\157\1"+
		"\141\1\145\1\162\2\151\7\uffff\1\75\1\76\4\75\2\76\2\75\3\uffff\1\75\3"+
		"\uffff\1\75\1\71\1\162\1\170\1\154\1\162\1\142\2\162\3\uffff\1\15\1\uffff"+
		"\2\43\1\uffff\1\ufffe\1\141\1\144\1\145\1\47\1\141\1\156\1\154\1\163\1"+
		"\145\1\156\1\157\1\162\1\157\1\ufffe\1\160\2\ufffe\1\155\1\164\1\163\1"+
		"\151\1\164\2\47\1\171\1\151\1\164\1\145\5\uffff\1\75\2\uffff\1\75\10\uffff"+
		"\1\75\2\uffff\1\75\12\uffff\1\152\1\ufffe\1\146\1\67\1\61\1\uffff\1\152"+
		"\2\71\2\uffff\10\47\1\uffff\1\0\2\uffff\1\145\1\156\1\uffff\1\151\1\ufffe"+
		"\1\141\1\163\1\164\2\ufffe\1\146\2\145\1\143\1\141\1\155\1\ufffe\1\142"+
		"\1\uffff\1\157\2\uffff\1\142\1\154\1\ufffe\2\163\1\165\1\ufffe\1\154\1"+
		"\150\1\154\11\uffff\1\71\1\uffff\3\154\1\71\1\152\1\71\1\152\1\154\1\162"+
		"\1\143\1\164\1\uffff\1\153\1\163\1\151\2\uffff\2\ufffe\1\160\1\61\1\154"+
		"\1\ufffe\1\uffff\1\141\1\162\1\144\1\157\1\uffff\1\ufffe\1\145\1\162\1"+
		"\uffff\1\145\1\ufffe\1\144\1\71\1\152\1\146\1\67\1\61\1\71\1\152\1\71"+
		"\1\164\4\ufffe\1\156\2\uffff\1\164\1\ufffe\1\154\1\uffff\1\154\1\164\1"+
		"\141\1\143\1\uffff\1\ufffe\1\156\1\ufffe\1\uffff\1\ufffe\3\154\1\71\1"+
		"\152\1\ufffe\4\uffff\1\165\1\ufffe\1\uffff\1\171\3\ufffe\1\141\1\uffff"+
		"\1\ufffe\3\uffff\1\145\1\uffff\1\ufffe\3\uffff\1\154\1\uffff\1\ufffe\1"+
		"\uffff\1\ufffe\2\uffff";
	static final String DFA53_acceptS =
		"\20\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\12\uffff\1\60\1\61\1\62\1"+
		"\uffff\1\64\1\65\1\67\11\uffff\1\126\1\127\1\130\1\uffff\1\131\2\uffff"+
		"\1\134\35\uffff\1\75\1\46\1\76\1\114\1\47\1\uffff\1\100\1\50\1\uffff\1"+
		"\103\1\51\1\104\1\52\1\106\1\53\1\70\1\71\1\uffff\1\54\1\73\1\uffff\1"+
		"\55\1\66\1\56\1\105\1\57\1\107\1\63\1\101\1\116\1\115\5\uffff\1\124\3"+
		"\uffff\1\123\1\125\10\uffff\1\132\1\uffff\1\133\1\134\2\uffff\1\1\17\uffff"+
		"\1\21\1\uffff\1\23\1\24\12\uffff\1\112\1\77\1\113\1\102\1\110\1\72\1\111"+
		"\1\74\1\122\1\uffff\1\120\13\uffff\1\117\3\uffff\1\10\1\11\6\uffff\1\17"+
		"\4\uffff\1\121\3\uffff\1\33\21\uffff\1\12\1\27\3\uffff\1\16\4\uffff\1"+
		"\30\3\uffff\1\35\7\uffff\1\3\1\4\1\5\1\6\2\uffff\1\14\5\uffff\1\31\1\uffff"+
		"\1\34\1\36\1\2\1\uffff\1\13\1\uffff\1\20\1\22\1\25\1\uffff\1\32\1\uffff"+
		"\1\15\1\uffff\1\7\1\26";
	static final String DFA53_specialS =
		"\1\3\63\uffff\1\4\1\uffff\1\1\1\2\121\uffff\1\0\u0093\uffff}>";
	static final String[] DFA53_transitionS = {
			"\1\67\1\65\1\uffff\1\64\1\65\22\uffff\1\66\1\47\1\62\1\70\1\45\1\40\1"+
			"\34\1\62\1\20\1\21\1\31\1\27\1\25\1\30\1\51\1\32\1\53\11\54\1\24\1\26"+
			"\1\35\1\37\1\36\1\uffff\1\50\1\61\1\60\17\61\1\56\2\61\1\57\5\61\1\22"+
			"\1\63\1\23\1\44\1\61\1\41\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\61\1\10\2\61"+
			"\1\11\1\61\1\12\1\52\1\13\1\61\1\14\1\61\1\15\1\55\1\61\1\16\1\61\1\17"+
			"\1\61\1\42\1\33\1\43\1\46\101\uffff\27\61\1\uffff\37\61\1\uffff\uff07"+
			"\61",
			"\1\73\4\uffff\1\71\3\uffff\1\72",
			"\1\62\4\uffff\1\62\52\uffff\1\75\37\uffff\1\74",
			"\1\76\2\uffff\1\77",
			"\1\100",
			"\1\101\13\uffff\1\102",
			"\1\62\4\uffff\1\62\101\uffff\1\103\5\uffff\1\105\2\uffff\1\104",
			"\1\106",
			"\1\107\6\uffff\1\110\1\111\4\uffff\1\112",
			"\1\113",
			"\1\114",
			"\1\115",
			"\1\62\4\uffff\1\62\32\uffff\1\121\36\uffff\1\116\1\120\2\uffff\1\117",
			"\1\122",
			"\1\123\1\124",
			"\1\125",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\126",
			"\1\130\1\131",
			"\1\133\22\uffff\1\134",
			"\1\136\15\uffff\1\137",
			"\1\141",
			"\1\143",
			"\1\147\1\146\1\145",
			"\1\151\1\152",
			"\1\154",
			"\1\156",
			"",
			"",
			"",
			"\1\160",
			"",
			"",
			"",
			"\1\162",
			"\12\165",
			"\1\166",
			"\1\173\1\uffff\12\54\10\uffff\1\171\2\uffff\1\174\4\uffff\1\177\1\uffff"+
			"\1\176\2\uffff\1\170\10\uffff\1\167\6\uffff\1\175\2\uffff\1\171\2\uffff"+
			"\1\174\4\uffff\1\177\1\uffff\1\176\2\uffff\1\170\10\uffff\1\167",
			"\1\173\1\uffff\12\54\13\uffff\1\174\4\uffff\1\177\1\uffff\1\176\22\uffff"+
			"\1\175\5\uffff\1\174\4\uffff\1\177\1\uffff\1\176",
			"\1\62\4\uffff\1\62\52\uffff\1\u0081\37\uffff\1\u0080",
			"\1\62\4\uffff\1\62\32\uffff\1\u0083\37\uffff\1\u0082",
			"\1\62\4\uffff\1\62\52\uffff\1\u0084\37\uffff\1\u0085",
			"\1\62\4\uffff\1\62\52\uffff\1\u0086\37\uffff\1\u0087",
			"",
			"",
			"",
			"\1\65\2\uffff\1\65",
			"",
			"\1\67\1\u008a\1\uffff\1\u0088\1\u008a\22\uffff\1\66\2\uffff\1\u008b",
			"\1\67\1\u008a\1\uffff\1\u0088\1\u008a\22\uffff\1\66\2\uffff\1\u008b",
			"",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\22\61\1\u008c\5\61\1\u008d"+
			"\1\61\105\uffff\27\61\1\uffff\37\61\1\uffff\uff07\61",
			"\1\u008f",
			"\1\u0090",
			"\1\62\4\uffff\1\62\75\uffff\1\u0091",
			"\1\62\4\uffff\1\62",
			"\1\u0092",
			"\1\u0093",
			"\1\u0094\5\uffff\1\u0095",
			"\1\u0096\11\uffff\1\u0097",
			"\1\u0098\1\uffff\1\u0099",
			"\1\u009a",
			"\1\u009b",
			"\1\u009c",
			"\1\u009d",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u009f",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u00a2",
			"\1\u00a3\5\uffff\1\u00a4",
			"\1\u00a5",
			"\1\u00a6",
			"\1\u00a7",
			"\1\62\4\uffff\1\62",
			"\1\62\4\uffff\1\62",
			"\1\u00a8",
			"\1\u00a9",
			"\1\u00aa",
			"\1\u00ab",
			"",
			"",
			"",
			"",
			"",
			"\1\u00ac",
			"",
			"",
			"\1\u00ae",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u00b0",
			"",
			"",
			"\1\u00b2",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\12\165\13\uffff\1\u00b5\4\uffff\1\177\32\uffff\1\u00b5\4\uffff\1\177",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\12\u00b7\7\uffff\6\u00b7\32\uffff\6\u00b7",
			"\10\u00b8",
			"\2\u00b9",
			"",
			"\12\u00bb\13\uffff\1\u00ba\4\uffff\1\177\32\uffff\1\u00ba\4\uffff\1"+
			"\177",
			"\1\u00bc\1\uffff\1\u00bc\2\uffff\12\u00bd",
			"\12\u00be",
			"",
			"",
			"\1\62\4\uffff\1\62",
			"\1\62\4\uffff\1\62",
			"\1\62\4\uffff\1\62",
			"\1\62\4\uffff\1\62",
			"\1\62\4\uffff\1\62",
			"\1\62\4\uffff\1\62",
			"\1\62\4\uffff\1\62",
			"\1\62\4\uffff\1\62",
			"",
			"\1\uffff",
			"",
			"",
			"\1\u00bf",
			"\1\u00c0",
			"",
			"\1\u00c1",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u00c3",
			"\1\u00c4",
			"\1\u00c5",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u00c8",
			"\1\u00c9",
			"\1\u00ca",
			"\1\u00cb",
			"\1\u00cc",
			"\1\u00cd",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u00cf",
			"",
			"\1\u00d0",
			"",
			"",
			"\1\u00d1",
			"\1\u00d2",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u00d4",
			"\1\u00d5",
			"\1\u00d6",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u00d8",
			"\1\u00d9",
			"\1\u00da",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\u00db\1\uffff\1\u00db\2\uffff\12\u00dc",
			"",
			"\12\u00b7\7\uffff\6\u00b7\5\uffff\1\176\22\uffff\1\u00dd\1\uffff\6\u00b7"+
			"\5\uffff\1\176",
			"\10\u00b8\24\uffff\1\176\22\uffff\1\u00de\14\uffff\1\176",
			"\2\u00b9\32\uffff\1\176\22\uffff\1\u00df\14\uffff\1\176",
			"\1\u00e0\1\uffff\1\u00e0\2\uffff\12\u00e1",
			"\12\u00bb\13\uffff\1\u00e2\4\uffff\1\177\32\uffff\1\u00e2\4\uffff\1"+
			"\177",
			"\12\u00bd",
			"\12\u00bd\20\uffff\1\177\37\uffff\1\177",
			"\12\u00be\22\uffff\1\176\22\uffff\1\175\14\uffff\1\176",
			"\1\u00e3",
			"\1\u00e4",
			"\1\u00e5",
			"",
			"\1\u00e6",
			"\1\u00e7",
			"\1\u00e8",
			"",
			"",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u00eb",
			"\1\u00ec",
			"\1\u00ed",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"",
			"\1\u00ef",
			"\1\u00f0",
			"\1\u00f1",
			"\1\u00f2",
			"",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u00f4",
			"\1\u00f5",
			"",
			"\1\u00f6",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u00f8",
			"\12\u00dc",
			"\12\u00dc\20\uffff\1\177\37\uffff\1\177",
			"\12\u00f9\7\uffff\6\u00f9\32\uffff\6\u00f9",
			"\10\u00fa",
			"\2\u00fb",
			"\12\u00e1",
			"\12\u00e1\20\uffff\1\177\37\uffff\1\177",
			"\1\u00fc\1\uffff\1\u00fc\2\uffff\12\u00fd",
			"\1\u00fe",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u0103",
			"",
			"",
			"\1\u0104",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u0106",
			"",
			"\1\u0107",
			"\1\u0108",
			"\1\u0109",
			"\1\u010a",
			"",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u010c",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\12\u00f9\7\uffff\6\u00f9\5\uffff\1\176\22\uffff\1\u00dd\1\uffff\6\u00f9"+
			"\5\uffff\1\176",
			"\10\u00fa\24\uffff\1\176\22\uffff\1\u00de\14\uffff\1\176",
			"\2\u00fb\32\uffff\1\176\22\uffff\1\u00df\14\uffff\1\176",
			"\12\u00fd",
			"\12\u00fd\20\uffff\1\177\37\uffff\1\177",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"",
			"",
			"",
			"",
			"\1\u0110",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"",
			"\1\u0112",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"\1\u0116",
			"",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"",
			"",
			"",
			"\1\u0118",
			"",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"",
			"",
			"",
			"\1\u011a",
			"",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"",
			"\12\61\7\uffff\32\61\4\uffff\1\61\1\uffff\32\61\105\uffff\27\61\1\uffff"+
			"\37\61\1\uffff\uff07\61",
			"",
			""
	};

	static final short[] DFA53_eot = DFA.unpackEncodedString(DFA53_eotS);
	static final short[] DFA53_eof = DFA.unpackEncodedString(DFA53_eofS);
	static final char[] DFA53_min = DFA.unpackEncodedStringToUnsignedChars(DFA53_minS);
	static final char[] DFA53_max = DFA.unpackEncodedStringToUnsignedChars(DFA53_maxS);
	static final short[] DFA53_accept = DFA.unpackEncodedString(DFA53_acceptS);
	static final short[] DFA53_special = DFA.unpackEncodedString(DFA53_specialS);
	static final short[][] DFA53_transition;

	static {
		int numStates = DFA53_transitionS.length;
		DFA53_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA53_transition[i] = DFA.unpackEncodedString(DFA53_transitionS[i]);
		}
	}

	protected class DFA53 extends DFA {

		public DFA53(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 53;
			this.eot = DFA53_eot;
			this.eof = DFA53_eof;
			this.min = DFA53_min;
			this.max = DFA53_max;
			this.accept = DFA53_accept;
			this.special = DFA53_special;
			this.transition = DFA53_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( AS | ASSERT | ASYNC | AWAIT | BREAK | CLASS | CONTINUE | DEF | DELETE | ELIF | EXCEPT | EXEC | FINALLY | FROM | FOR | GLOBAL | IF | IMPORT | IN | IS | LAMBDA | NONLOCAL | ORELSE | PASS | RAISE | RETURN | TRY | WHILE | WITH | YIELD | LPAREN | RPAREN | LBRACK | RBRACK | COLON | COMMA | SEMI | PLUS | MINUS | STAR | SLASH | VBAR | AMPER | LESS | GREATER | ASSIGN | PERCENT | BACKQUOTE | LCURLY | RCURLY | CIRCUMFLEX | DOLLER | TILDE | EQUAL | NOTEQUAL | ALT_NOTEQUAL | LESSEQUAL | LEFTSHIFT | GREATEREQUAL | RIGHTSHIFT | PLUSEQUAL | MINUSEQUAL | DOUBLESTAR | STAREQUAL | ATEQUAL | DOUBLESLASH | SLASHEQUAL | VBAREQUAL | PERCENTEQUAL | AMPEREQUAL | CIRCUMFLEXEQUAL | LEFTSHIFTEQUAL | RIGHTSHIFTEQUAL | DOUBLESTAREQUAL | DOUBLESLASHEQUAL | ARROW | DOT | AT | AND | OR | NOT | FLOAT | LONGINT | INT | COMPLEX | NAME | STRING | CONTINUED_LINE | NEWLINE | WS | LEADING_WS | COMMENT );";
		}
		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch ( s ) {
					case 0 : 
						int LA53_137 = input.LA(1);
						 
						int index53_137 = input.index();
						input.rewind();
						s = -1;
						if ( ((startPos>0)) ) {s = 136;}
						else if ( ((startPos==0)) ) {s = 138;}
						 
						input.seek(index53_137);
						if ( s>=0 ) return s;
						break;

					case 1 : 
						int LA53_54 = input.LA(1);
						 
						int index53_54 = input.index();
						input.rewind();
						s = -1;
						if ( (LA53_54==' ') && (((startPos==0)||(startPos>0)))) {s = 54;}
						else if ( (LA53_54=='\f') && ((startPos>0))) {s = 136;}
						else if ( (LA53_54=='\n'||LA53_54=='\r') && ((startPos==0))) {s = 138;}
						else if ( (LA53_54=='\t') && (((startPos==0)||(startPos>0)))) {s = 55;}
						else if ( (LA53_54=='#') && ((startPos==0))) {s = 139;}
						else s = 137;
						 
						input.seek(index53_54);
						if ( s>=0 ) return s;
						break;

					case 2 : 
						int LA53_55 = input.LA(1);
						 
						int index53_55 = input.index();
						input.rewind();
						s = -1;
						if ( (LA53_55==' ') && (((startPos==0)||(startPos>0)))) {s = 54;}
						else if ( (LA53_55=='\f') && ((startPos>0))) {s = 136;}
						else if ( (LA53_55=='\n'||LA53_55=='\r') && ((startPos==0))) {s = 138;}
						else if ( (LA53_55=='\t') && (((startPos==0)||(startPos>0)))) {s = 55;}
						else if ( (LA53_55=='#') && ((startPos==0))) {s = 139;}
						else s = 137;
						 
						input.seek(index53_55);
						if ( s>=0 ) return s;
						break;

					case 3 : 
						int LA53_0 = input.LA(1);
						 
						int index53_0 = input.index();
						input.rewind();
						s = -1;
						if ( (LA53_0=='a') ) {s = 1;}
						else if ( (LA53_0=='b') ) {s = 2;}
						else if ( (LA53_0=='c') ) {s = 3;}
						else if ( (LA53_0=='d') ) {s = 4;}
						else if ( (LA53_0=='e') ) {s = 5;}
						else if ( (LA53_0=='f') ) {s = 6;}
						else if ( (LA53_0=='g') ) {s = 7;}
						else if ( (LA53_0=='i') ) {s = 8;}
						else if ( (LA53_0=='l') ) {s = 9;}
						else if ( (LA53_0=='n') ) {s = 10;}
						else if ( (LA53_0=='p') ) {s = 11;}
						else if ( (LA53_0=='r') ) {s = 12;}
						else if ( (LA53_0=='t') ) {s = 13;}
						else if ( (LA53_0=='w') ) {s = 14;}
						else if ( (LA53_0=='y') ) {s = 15;}
						else if ( (LA53_0=='(') ) {s = 16;}
						else if ( (LA53_0==')') ) {s = 17;}
						else if ( (LA53_0=='[') ) {s = 18;}
						else if ( (LA53_0==']') ) {s = 19;}
						else if ( (LA53_0==':') ) {s = 20;}
						else if ( (LA53_0==',') ) {s = 21;}
						else if ( (LA53_0==';') ) {s = 22;}
						else if ( (LA53_0=='+') ) {s = 23;}
						else if ( (LA53_0=='-') ) {s = 24;}
						else if ( (LA53_0=='*') ) {s = 25;}
						else if ( (LA53_0=='/') ) {s = 26;}
						else if ( (LA53_0=='|') ) {s = 27;}
						else if ( (LA53_0=='&') ) {s = 28;}
						else if ( (LA53_0=='<') ) {s = 29;}
						else if ( (LA53_0=='>') ) {s = 30;}
						else if ( (LA53_0=='=') ) {s = 31;}
						else if ( (LA53_0=='%') ) {s = 32;}
						else if ( (LA53_0=='`') ) {s = 33;}
						else if ( (LA53_0=='{') ) {s = 34;}
						else if ( (LA53_0=='}') ) {s = 35;}
						else if ( (LA53_0=='^') ) {s = 36;}
						else if ( (LA53_0=='$') ) {s = 37;}
						else if ( (LA53_0=='~') ) {s = 38;}
						else if ( (LA53_0=='!') ) {s = 39;}
						else if ( (LA53_0=='@') ) {s = 40;}
						else if ( (LA53_0=='.') ) {s = 41;}
						else if ( (LA53_0=='o') ) {s = 42;}
						else if ( (LA53_0=='0') ) {s = 43;}
						else if ( ((LA53_0 >= '1' && LA53_0 <= '9')) ) {s = 44;}
						else if ( (LA53_0=='u') ) {s = 45;}
						else if ( (LA53_0=='R') ) {s = 46;}
						else if ( (LA53_0=='U') ) {s = 47;}
						else if ( (LA53_0=='B') ) {s = 48;}
						else if ( (LA53_0=='A'||(LA53_0 >= 'C' && LA53_0 <= 'Q')||(LA53_0 >= 'S' && LA53_0 <= 'T')||(LA53_0 >= 'V' && LA53_0 <= 'Z')||LA53_0=='_'||LA53_0=='h'||(LA53_0 >= 'j' && LA53_0 <= 'k')||LA53_0=='m'||LA53_0=='q'||LA53_0=='s'||LA53_0=='v'||LA53_0=='x'||LA53_0=='z'||(LA53_0 >= '\u00C0' && LA53_0 <= '\u00D6')||(LA53_0 >= '\u00D8' && LA53_0 <= '\u00F6')||(LA53_0 >= '\u00F8' && LA53_0 <= '\uFFFE')) ) {s = 49;}
						else if ( (LA53_0=='\"'||LA53_0=='\'') ) {s = 50;}
						else if ( (LA53_0=='\\') ) {s = 51;}
						else if ( (LA53_0=='\f') ) {s = 52;}
						else if ( (LA53_0=='\n'||LA53_0=='\r') ) {s = 53;}
						else if ( (LA53_0==' ') && (((startPos==0)||(startPos>0)))) {s = 54;}
						else if ( (LA53_0=='\t') && (((startPos==0)||(startPos>0)))) {s = 55;}
						else if ( (LA53_0=='#') ) {s = 56;}
						 
						input.seek(index53_0);
						if ( s>=0 ) return s;
						break;

					case 4 : 
						int LA53_52 = input.LA(1);
						 
						int index53_52 = input.index();
						input.rewind();
						s = -1;
						if ( (LA53_52=='\n'||LA53_52=='\r') ) {s = 53;}
						else s = 136;
						 
						input.seek(index53_52);
						if ( s>=0 ) return s;
						break;
			}
			NoViableAltException nvae =
				new NoViableAltException(getDescription(), 53, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}
