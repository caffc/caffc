// Generated from caffc.g4 by ANTLR 4.13.1
package com.germaniumhq.caffc.generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class caffcParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		CHAR=39, STRING=40, F_STRING=41, SIMPLE_ESCAPE=42, HEX_ESCAPE=43, OCTAL_ESCAPE=44, 
		COMMENT=45, BLOCK_COMMENT=46, WS=47, AS=48, AND=49, BREAK=50, CASE=51, 
		CATCH=52, CLASS=53, CONTINUE=54, DEFAULT=55, ELSE=56, EXTENDS=57, FINALLY=58, 
		FOR=59, IF=60, IFDEF=61, IMPLEMENTS=62, IN=63, INSTANCEOF=64, INTERFACE=65, 
		IS=66, MODULE=67, NEW=68, NOT=69, FALSE=70, NULL=71, OR=72, TRUE=73, RETURN=74, 
		STATIC=75, SWITCH=76, TAG=77, THROW=78, TRY=79, USE=80, VAR=81, VOID=82, 
		WHILE=83, YIELD=84, U8=85, I8=86, U16=87, I16=88, U32=89, I32=90, U64=91, 
		I64=92, F32=93, F64=94, NATIVE=95, CURLY_OPEN=96, CURLY_CLOSE=97, FN=98, 
		SHARP=99, STAR=100, PIPE=101, ELLIPSIS=102, DOT=103, ID=104, NUMBER=105, 
		DigitSequence=106;
	public static final int
		RULE_compilationUnit = 0, RULE_module = 1, RULE_useStatement = 2, RULE_compileBlock = 3, 
		RULE_compileBlockPlain = 4, RULE_use = 5, RULE_use_alias = 6, RULE_nativeBlock = 7, 
		RULE_function = 8, RULE_functionBlock = 9, RULE_functionBodyItem = 10, 
		RULE_sharpSwitchUnit = 11, RULE_sharpCaseUnit = 12, RULE_sharpSwitchMethod = 13, 
		RULE_sharpCaseMethod = 14, RULE_sharpIfdefUnit = 15, RULE_sharpIfdefMethod = 16, 
		RULE_returnType = 17, RULE_classDefinition = 18, RULE_interfaceDefinition = 19, 
		RULE_name = 20, RULE_interfaceImplementations = 21, RULE_interfaceImplementation = 22, 
		RULE_interfaceStatements = 23, RULE_functionDeclaration = 24, RULE_tagDefinition = 25, 
		RULE_fieldDeclaration = 26, RULE_statement = 27, RULE_throwStatement = 28, 
		RULE_block = 29, RULE_whileBlock = 30, RULE_forBlock = 31, RULE_ifBlock = 32, 
		RULE_switchBlock = 33, RULE_switchCaseBody = 34, RULE_switchBranch = 35, 
		RULE_tryCatchBlock = 36, RULE_catchBlock = 37, RULE_finallyBlock = 38, 
		RULE_return = 39, RULE_decoratorCall = 40, RULE_controlFlow = 41, RULE_variableDeclarations = 42, 
		RULE_variableDeclaration = 43, RULE_varType = 44, RULE_tags = 45, RULE_tag = 46, 
		RULE_tagParam = 47, RULE_constExpression = 48, RULE_fqdn = 49, RULE_expression = 50, 
		RULE_fStringInner = 51, RULE_fStringPrimary = 52, RULE_fStringIndexExpr = 53, 
		RULE_shiftOp = 54, RULE_assignExpression = 55, RULE_expressionTuple = 56, 
		RULE_callArgumentList = 57, RULE_callArgument = 58, RULE_extend = 59, 
		RULE_parameterDefinitions = 60, RULE_parameterDefinition = 61, RULE_typeName = 62, 
		RULE_newType = 63, RULE_primitiveTypeName = 64, RULE_classType = 65, RULE_genericsDeclarations = 66, 
		RULE_genericDeclaration = 67, RULE_genericsInstantiations = 68, RULE_functionType = 69, 
		RULE_namedTypeTuple = 70;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilationUnit", "module", "useStatement", "compileBlock", "compileBlockPlain", 
			"use", "use_alias", "nativeBlock", "function", "functionBlock", "functionBodyItem", 
			"sharpSwitchUnit", "sharpCaseUnit", "sharpSwitchMethod", "sharpCaseMethod", 
			"sharpIfdefUnit", "sharpIfdefMethod", "returnType", "classDefinition", 
			"interfaceDefinition", "name", "interfaceImplementations", "interfaceImplementation", 
			"interfaceStatements", "functionDeclaration", "tagDefinition", "fieldDeclaration", 
			"statement", "throwStatement", "block", "whileBlock", "forBlock", "ifBlock", 
			"switchBlock", "switchCaseBody", "switchBranch", "tryCatchBlock", "catchBlock", 
			"finallyBlock", "return", "decoratorCall", "controlFlow", "variableDeclarations", 
			"variableDeclaration", "varType", "tags", "tag", "tagParam", "constExpression", 
			"fqdn", "expression", "fStringInner", "fStringPrimary", "fStringIndexExpr", 
			"shiftOp", "assignExpression", "expressionTuple", "callArgumentList", 
			"callArgument", "extend", "parameterDefinitions", "parameterDefinition", 
			"typeName", "newType", "primitiveTypeName", "classType", "genericsDeclarations", 
			"genericDeclaration", "genericsInstantiations", "functionType", "namedTypeTuple"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'->'", "':'", "';'", "'@'", "'='", "'['", 
			"']'", "'!'", "'~'", "'-'", "'%'", "'/'", "'+'", "'<'", "'<='", "'>='", 
			"'>'", "'=='", "'!='", "'&'", "'^'", "'?'", "'?:'", "'<<'", "'<<='", 
			"'>>='", "'&='", "'|='", "'^='", "'*='", "'/='", "'+='", "'-='", "'ptr'", 
			"'bool'", null, null, null, null, null, null, null, null, null, "'as'", 
			"'and'", "'break'", "'case'", "'catch'", "'class'", "'continue'", "'default'", 
			"'else'", "'extends'", "'finally'", "'for'", "'if'", "'ifdef'", "'implements'", 
			"'in'", "'instanceof'", "'interface'", "'is'", "'module'", "'new'", "'not'", 
			"'false'", "'null'", "'or'", "'true'", "'return'", "'static'", "'switch'", 
			"'tag'", "'throw'", "'try'", "'use'", "'var'", "'void'", "'while'", "'yield'", 
			"'u8'", "'i8'", "'u16'", "'i16'", "'u32'", "'i32'", "'u64'", "'i64'", 
			"'f32'", "'f64'", null, "'{'", "'}'", "'fn'", "'#'", "'*'", "'|'", "'...'", 
			"'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "CHAR", "STRING", "F_STRING", "SIMPLE_ESCAPE", "HEX_ESCAPE", 
			"OCTAL_ESCAPE", "COMMENT", "BLOCK_COMMENT", "WS", "AS", "AND", "BREAK", 
			"CASE", "CATCH", "CLASS", "CONTINUE", "DEFAULT", "ELSE", "EXTENDS", "FINALLY", 
			"FOR", "IF", "IFDEF", "IMPLEMENTS", "IN", "INSTANCEOF", "INTERFACE", 
			"IS", "MODULE", "NEW", "NOT", "FALSE", "NULL", "OR", "TRUE", "RETURN", 
			"STATIC", "SWITCH", "TAG", "THROW", "TRY", "USE", "VAR", "VOID", "WHILE", 
			"YIELD", "U8", "I8", "U16", "I16", "U32", "I32", "U64", "I64", "F32", 
			"F64", "NATIVE", "CURLY_OPEN", "CURLY_CLOSE", "FN", "SHARP", "STAR", 
			"PIPE", "ELLIPSIS", "DOT", "ID", "NUMBER", "DigitSequence"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "caffc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public caffcParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompilationUnitContext extends ParserRuleContext {
		public ModuleContext module() {
			return getRuleContext(ModuleContext.class,0);
		}
		public List<UseStatementContext> useStatement() {
			return getRuleContexts(UseStatementContext.class);
		}
		public UseStatementContext useStatement(int i) {
			return getRuleContext(UseStatementContext.class,i);
		}
		public List<CompileBlockContext> compileBlock() {
			return getRuleContexts(CompileBlockContext.class);
		}
		public CompileBlockContext compileBlock(int i) {
			return getRuleContext(CompileBlockContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			module();
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==USE) {
				{
				{
				setState(143);
				useStatement();
				}
				}
				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9007611571601408L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 577672123393L) != 0)) {
				{
				{
				setState(149);
				compileBlock();
				}
				}
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModuleContext extends ParserRuleContext {
		public TerminalNode MODULE() { return getToken(caffcParser.MODULE, 0); }
		public FqdnContext fqdn() {
			return getRuleContext(FqdnContext.class,0);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_module);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(MODULE);
			setState(156);
			fqdn(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UseStatementContext extends ParserRuleContext {
		public UseContext use() {
			return getRuleContext(UseContext.class,0);
		}
		public Use_aliasContext use_alias() {
			return getRuleContext(Use_aliasContext.class,0);
		}
		public UseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useStatement; }
	}

	public final UseStatementContext useStatement() throws RecognitionException {
		UseStatementContext _localctx = new UseStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_useStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			use();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(159);
				use_alias();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompileBlockContext extends ParserRuleContext {
		public NativeBlockContext nativeBlock() {
			return getRuleContext(NativeBlockContext.class,0);
		}
		public TagDefinitionContext tagDefinition() {
			return getRuleContext(TagDefinitionContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ClassDefinitionContext classDefinition() {
			return getRuleContext(ClassDefinitionContext.class,0);
		}
		public InterfaceDefinitionContext interfaceDefinition() {
			return getRuleContext(InterfaceDefinitionContext.class,0);
		}
		public VariableDeclarationsContext variableDeclarations() {
			return getRuleContext(VariableDeclarationsContext.class,0);
		}
		public SharpSwitchUnitContext sharpSwitchUnit() {
			return getRuleContext(SharpSwitchUnitContext.class,0);
		}
		public SharpIfdefUnitContext sharpIfdefUnit() {
			return getRuleContext(SharpIfdefUnitContext.class,0);
		}
		public CompileBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compileBlock; }
	}

	public final CompileBlockContext compileBlock() throws RecognitionException {
		CompileBlockContext _localctx = new CompileBlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_compileBlock);
		try {
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				nativeBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				tagDefinition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				function();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(165);
				classDefinition();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(166);
				interfaceDefinition();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(167);
				variableDeclarations();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(168);
				sharpSwitchUnit();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(169);
				sharpIfdefUnit();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompileBlockPlainContext extends ParserRuleContext {
		public NativeBlockContext nativeBlock() {
			return getRuleContext(NativeBlockContext.class,0);
		}
		public TagDefinitionContext tagDefinition() {
			return getRuleContext(TagDefinitionContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ClassDefinitionContext classDefinition() {
			return getRuleContext(ClassDefinitionContext.class,0);
		}
		public InterfaceDefinitionContext interfaceDefinition() {
			return getRuleContext(InterfaceDefinitionContext.class,0);
		}
		public VariableDeclarationsContext variableDeclarations() {
			return getRuleContext(VariableDeclarationsContext.class,0);
		}
		public CompileBlockPlainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compileBlockPlain; }
	}

	public final CompileBlockPlainContext compileBlockPlain() throws RecognitionException {
		CompileBlockPlainContext _localctx = new CompileBlockPlainContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_compileBlockPlain);
		try {
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(172);
				nativeBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				tagDefinition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(174);
				function();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(175);
				classDefinition();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(176);
				interfaceDefinition();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(177);
				variableDeclarations();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UseContext extends ParserRuleContext {
		public TerminalNode USE() { return getToken(caffcParser.USE, 0); }
		public FqdnContext fqdn() {
			return getRuleContext(FqdnContext.class,0);
		}
		public UseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use; }
	}

	public final UseContext use() throws RecognitionException {
		UseContext _localctx = new UseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_use);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(USE);
			setState(181);
			fqdn(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Use_aliasContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(caffcParser.AS, 0); }
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public Use_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use_alias; }
	}

	public final Use_aliasContext use_alias() throws RecognitionException {
		Use_aliasContext _localctx = new Use_aliasContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_use_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(AS);
			setState(184);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NativeBlockContext extends ParserRuleContext {
		public TerminalNode NATIVE() { return getToken(caffcParser.NATIVE, 0); }
		public NativeBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nativeBlock; }
	}

	public final NativeBlockContext nativeBlock() throws RecognitionException {
		NativeBlockContext _localctx = new NativeBlockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_nativeBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			match(NATIVE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public ExtendContext extend() {
			return getRuleContext(ExtendContext.class,0);
		}
		public FunctionBlockContext functionBlock() {
			return getRuleContext(FunctionBlockContext.class,0);
		}
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public TerminalNode STATIC() { return getToken(caffcParser.STATIC, 0); }
		public GenericsDeclarationsContext genericsDeclarations() {
			return getRuleContext(GenericsDeclarationsContext.class,0);
		}
		public ParameterDefinitionsContext parameterDefinitions() {
			return getRuleContext(ParameterDefinitionsContext.class,0);
		}
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_function);
		int _la;
		try {
			setState(235);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(188);
					tags();
					}
				}

				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(191);
					match(STATIC);
					}
				}

				setState(194);
				match(ID);
				setState(196);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16) {
					{
					setState(195);
					genericsDeclarations();
					}
				}

				setState(198);
				match(T__0);
				setState(199);
				extend();
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(200);
					match(T__1);
					setState(201);
					parameterDefinitions();
					}
				}

				setState(204);
				match(T__2);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(205);
					match(T__3);
					setState(207);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__36 || _la==T__37 || ((((_la - 82)) & ~0x3f) == 0 && ((1L << (_la - 82)) & 4268025L) != 0)) {
						{
						setState(206);
						returnType();
						}
					}

					}
				}

				setState(211);
				functionBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(213);
					tags();
					}
				}

				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(216);
					match(STATIC);
					}
				}

				setState(219);
				match(ID);
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16) {
					{
					setState(220);
					genericsDeclarations();
					}
				}

				setState(223);
				match(T__0);
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__36 || _la==T__37 || ((((_la - 85)) & ~0x3f) == 0 && ((1L << (_la - 85)) & 680959L) != 0)) {
					{
					setState(224);
					parameterDefinitions();
					}
				}

				setState(227);
				match(T__2);
				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(228);
					match(T__3);
					setState(230);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__36 || _la==T__37 || ((((_la - 82)) & ~0x3f) == 0 && ((1L << (_la - 82)) & 4268025L) != 0)) {
						{
						setState(229);
						returnType();
						}
					}

					}
				}

				setState(234);
				functionBlock();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionBlockContext extends ParserRuleContext {
		public TerminalNode CURLY_OPEN() { return getToken(caffcParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(caffcParser.CURLY_CLOSE, 0); }
		public List<FunctionBodyItemContext> functionBodyItem() {
			return getRuleContexts(FunctionBodyItemContext.class);
		}
		public FunctionBodyItemContext functionBodyItem(int i) {
			return getRuleContext(FunctionBodyItemContext.class,i);
		}
		public FunctionBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBlock; }
	}

	public final FunctionBlockContext functionBlock() throws RecognitionException {
		FunctionBlockContext _localctx = new FunctionBlockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_functionBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(CURLY_OPEN);
			setState(241);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1748526815934167042L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 209648004591L) != 0)) {
				{
				{
				setState(238);
				functionBodyItem();
				}
				}
				setState(243);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(244);
			match(CURLY_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionBodyItemContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public SharpSwitchMethodContext sharpSwitchMethod() {
			return getRuleContext(SharpSwitchMethodContext.class,0);
		}
		public SharpIfdefMethodContext sharpIfdefMethod() {
			return getRuleContext(SharpIfdefMethodContext.class,0);
		}
		public FunctionBodyItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionBodyItem; }
	}

	public final FunctionBodyItemContext functionBodyItem() throws RecognitionException {
		FunctionBodyItemContext _localctx = new FunctionBodyItemContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_functionBodyItem);
		try {
			setState(249);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(246);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				sharpSwitchMethod();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(248);
				sharpIfdefMethod();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SharpSwitchUnitContext extends ParserRuleContext {
		public TerminalNode SHARP() { return getToken(caffcParser.SHARP, 0); }
		public TerminalNode SWITCH() { return getToken(caffcParser.SWITCH, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(caffcParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(caffcParser.CURLY_CLOSE, 0); }
		public List<SharpCaseUnitContext> sharpCaseUnit() {
			return getRuleContexts(SharpCaseUnitContext.class);
		}
		public SharpCaseUnitContext sharpCaseUnit(int i) {
			return getRuleContext(SharpCaseUnitContext.class,i);
		}
		public SharpSwitchUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sharpSwitchUnit; }
	}

	public final SharpSwitchUnitContext sharpSwitchUnit() throws RecognitionException {
		SharpSwitchUnitContext _localctx = new SharpSwitchUnitContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_sharpSwitchUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(SHARP);
			setState(252);
			match(SWITCH);
			setState(253);
			match(CURLY_OPEN);
			setState(255); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(254);
				sharpCaseUnit();
				}
				}
				setState(257); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SHARP );
			setState(259);
			match(CURLY_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SharpCaseUnitContext extends ParserRuleContext {
		public TerminalNode SHARP() { return getToken(caffcParser.SHARP, 0); }
		public TerminalNode CASE() { return getToken(caffcParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CURLY_OPEN() { return getToken(caffcParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(caffcParser.CURLY_CLOSE, 0); }
		public List<CompileBlockPlainContext> compileBlockPlain() {
			return getRuleContexts(CompileBlockPlainContext.class);
		}
		public CompileBlockPlainContext compileBlockPlain(int i) {
			return getRuleContext(CompileBlockPlainContext.class,i);
		}
		public TerminalNode DEFAULT() { return getToken(caffcParser.DEFAULT, 0); }
		public SharpCaseUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sharpCaseUnit; }
	}

	public final SharpCaseUnitContext sharpCaseUnit() throws RecognitionException {
		SharpCaseUnitContext _localctx = new SharpCaseUnitContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sharpCaseUnit);
		int _la;
		try {
			setState(285);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(261);
				match(SHARP);
				setState(262);
				match(CASE);
				setState(263);
				expression(0);
				setState(264);
				match(T__4);
				setState(265);
				match(CURLY_OPEN);
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9007611571601408L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 577672123393L) != 0)) {
					{
					{
					setState(266);
					compileBlockPlain();
					}
					}
					setState(271);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(272);
				match(CURLY_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				match(SHARP);
				setState(275);
				match(DEFAULT);
				setState(276);
				match(T__4);
				setState(277);
				match(CURLY_OPEN);
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9007611571601408L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 577672123393L) != 0)) {
					{
					{
					setState(278);
					compileBlockPlain();
					}
					}
					setState(283);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(284);
				match(CURLY_CLOSE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SharpSwitchMethodContext extends ParserRuleContext {
		public TerminalNode SHARP() { return getToken(caffcParser.SHARP, 0); }
		public TerminalNode SWITCH() { return getToken(caffcParser.SWITCH, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(caffcParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(caffcParser.CURLY_CLOSE, 0); }
		public List<SharpCaseMethodContext> sharpCaseMethod() {
			return getRuleContexts(SharpCaseMethodContext.class);
		}
		public SharpCaseMethodContext sharpCaseMethod(int i) {
			return getRuleContext(SharpCaseMethodContext.class,i);
		}
		public SharpSwitchMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sharpSwitchMethod; }
	}

	public final SharpSwitchMethodContext sharpSwitchMethod() throws RecognitionException {
		SharpSwitchMethodContext _localctx = new SharpSwitchMethodContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_sharpSwitchMethod);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(SHARP);
			setState(288);
			match(SWITCH);
			setState(289);
			match(CURLY_OPEN);
			setState(291); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(290);
				sharpCaseMethod();
				}
				}
				setState(293); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SHARP );
			setState(295);
			match(CURLY_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SharpCaseMethodContext extends ParserRuleContext {
		public TerminalNode SHARP() { return getToken(caffcParser.SHARP, 0); }
		public TerminalNode CASE() { return getToken(caffcParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CURLY_OPEN() { return getToken(caffcParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(caffcParser.CURLY_CLOSE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode DEFAULT() { return getToken(caffcParser.DEFAULT, 0); }
		public SharpCaseMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sharpCaseMethod; }
	}

	public final SharpCaseMethodContext sharpCaseMethod() throws RecognitionException {
		SharpCaseMethodContext _localctx = new SharpCaseMethodContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_sharpCaseMethod);
		int _la;
		try {
			setState(321);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(297);
				match(SHARP);
				setState(298);
				match(CASE);
				setState(299);
				expression(0);
				setState(300);
				match(T__4);
				setState(301);
				match(CURLY_OPEN);
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1748526815934167042L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 209648004591L) != 0)) {
					{
					{
					setState(302);
					statement();
					}
					}
					setState(307);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(308);
				match(CURLY_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(310);
				match(SHARP);
				setState(311);
				match(DEFAULT);
				setState(312);
				match(T__4);
				setState(313);
				match(CURLY_OPEN);
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1748526815934167042L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 209648004591L) != 0)) {
					{
					{
					setState(314);
					statement();
					}
					}
					setState(319);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(320);
				match(CURLY_CLOSE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SharpIfdefUnitContext extends ParserRuleContext {
		public CompileBlockPlainContext compileBlockPlain;
		public List<CompileBlockPlainContext> thenBlocks = new ArrayList<CompileBlockPlainContext>();
		public List<CompileBlockPlainContext> elseBlocks = new ArrayList<CompileBlockPlainContext>();
		public List<TerminalNode> SHARP() { return getTokens(caffcParser.SHARP); }
		public TerminalNode SHARP(int i) {
			return getToken(caffcParser.SHARP, i);
		}
		public TerminalNode IFDEF() { return getToken(caffcParser.IFDEF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> CURLY_OPEN() { return getTokens(caffcParser.CURLY_OPEN); }
		public TerminalNode CURLY_OPEN(int i) {
			return getToken(caffcParser.CURLY_OPEN, i);
		}
		public List<TerminalNode> CURLY_CLOSE() { return getTokens(caffcParser.CURLY_CLOSE); }
		public TerminalNode CURLY_CLOSE(int i) {
			return getToken(caffcParser.CURLY_CLOSE, i);
		}
		public TerminalNode ELSE() { return getToken(caffcParser.ELSE, 0); }
		public List<CompileBlockPlainContext> compileBlockPlain() {
			return getRuleContexts(CompileBlockPlainContext.class);
		}
		public CompileBlockPlainContext compileBlockPlain(int i) {
			return getRuleContext(CompileBlockPlainContext.class,i);
		}
		public SharpIfdefUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sharpIfdefUnit; }
	}

	public final SharpIfdefUnitContext sharpIfdefUnit() throws RecognitionException {
		SharpIfdefUnitContext _localctx = new SharpIfdefUnitContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_sharpIfdefUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			match(SHARP);
			setState(324);
			match(IFDEF);
			setState(325);
			expression(0);
			setState(326);
			match(CURLY_OPEN);
			setState(330);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9007611571601408L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 577672123393L) != 0)) {
				{
				{
				setState(327);
				((SharpIfdefUnitContext)_localctx).compileBlockPlain = compileBlockPlain();
				((SharpIfdefUnitContext)_localctx).thenBlocks.add(((SharpIfdefUnitContext)_localctx).compileBlockPlain);
				}
				}
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(333);
			match(CURLY_CLOSE);
			setState(344);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(334);
				match(SHARP);
				setState(335);
				match(ELSE);
				setState(336);
				match(CURLY_OPEN);
				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 9007611571601408L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 577672123393L) != 0)) {
					{
					{
					setState(337);
					((SharpIfdefUnitContext)_localctx).compileBlockPlain = compileBlockPlain();
					((SharpIfdefUnitContext)_localctx).elseBlocks.add(((SharpIfdefUnitContext)_localctx).compileBlockPlain);
					}
					}
					setState(342);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(343);
				match(CURLY_CLOSE);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SharpIfdefMethodContext extends ParserRuleContext {
		public StatementContext statement;
		public List<StatementContext> thenStatements = new ArrayList<StatementContext>();
		public List<StatementContext> elseStatements = new ArrayList<StatementContext>();
		public List<TerminalNode> SHARP() { return getTokens(caffcParser.SHARP); }
		public TerminalNode SHARP(int i) {
			return getToken(caffcParser.SHARP, i);
		}
		public TerminalNode IFDEF() { return getToken(caffcParser.IFDEF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> CURLY_OPEN() { return getTokens(caffcParser.CURLY_OPEN); }
		public TerminalNode CURLY_OPEN(int i) {
			return getToken(caffcParser.CURLY_OPEN, i);
		}
		public List<TerminalNode> CURLY_CLOSE() { return getTokens(caffcParser.CURLY_CLOSE); }
		public TerminalNode CURLY_CLOSE(int i) {
			return getToken(caffcParser.CURLY_CLOSE, i);
		}
		public TerminalNode ELSE() { return getToken(caffcParser.ELSE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SharpIfdefMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sharpIfdefMethod; }
	}

	public final SharpIfdefMethodContext sharpIfdefMethod() throws RecognitionException {
		SharpIfdefMethodContext _localctx = new SharpIfdefMethodContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_sharpIfdefMethod);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(SHARP);
			setState(347);
			match(IFDEF);
			setState(348);
			expression(0);
			setState(349);
			match(CURLY_OPEN);
			setState(353);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1748526815934167042L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 209648004591L) != 0)) {
				{
				{
				setState(350);
				((SharpIfdefMethodContext)_localctx).statement = statement();
				((SharpIfdefMethodContext)_localctx).thenStatements.add(((SharpIfdefMethodContext)_localctx).statement);
				}
				}
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(356);
			match(CURLY_CLOSE);
			setState(367);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(357);
				match(SHARP);
				setState(358);
				match(ELSE);
				setState(359);
				match(CURLY_OPEN);
				setState(363);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1748526815934167042L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 209648004591L) != 0)) {
					{
					{
					setState(360);
					((SharpIfdefMethodContext)_localctx).statement = statement();
					((SharpIfdefMethodContext)_localctx).elseStatements.add(((SharpIfdefMethodContext)_localctx).statement);
					}
					}
					setState(365);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(366);
				match(CURLY_CLOSE);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnTypeContext extends ParserRuleContext {
		public NamedTypeTupleContext namedTypeTuple() {
			return getRuleContext(NamedTypeTupleContext.class,0);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode VOID() { return getToken(caffcParser.VOID, 0); }
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_returnType);
		try {
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(369);
				namedTypeTuple();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(370);
				typeName(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(371);
				match(VOID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDefinitionContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(caffcParser.CLASS, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode CURLY_OPEN() { return getToken(caffcParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(caffcParser.CURLY_CLOSE, 0); }
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public GenericsDeclarationsContext genericsDeclarations() {
			return getRuleContext(GenericsDeclarationsContext.class,0);
		}
		public TerminalNode IMPLEMENTS() { return getToken(caffcParser.IMPLEMENTS, 0); }
		public InterfaceImplementationsContext interfaceImplementations() {
			return getRuleContext(InterfaceImplementationsContext.class,0);
		}
		public List<FieldDeclarationContext> fieldDeclaration() {
			return getRuleContexts(FieldDeclarationContext.class);
		}
		public FieldDeclarationContext fieldDeclaration(int i) {
			return getRuleContext(FieldDeclarationContext.class,i);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public ClassDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDefinition; }
	}

	public final ClassDefinitionContext classDefinition() throws RecognitionException {
		ClassDefinitionContext _localctx = new ClassDefinitionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_classDefinition);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(374);
				tags();
				}
			}

			setState(377);
			match(CLASS);
			setState(378);
			name();
			setState(380);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(379);
				genericsDeclarations();
				}
			}

			setState(384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(382);
				match(IMPLEMENTS);
				setState(383);
				interfaceImplementations();
				}
			}

			setState(386);
			match(CURLY_OPEN);
			setState(390);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(387);
					fieldDeclaration();
					}
					} 
				}
				setState(392);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			}
			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & 553648129L) != 0)) {
				{
				{
				setState(393);
				function();
				}
				}
				setState(398);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(399);
			match(CURLY_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceDefinitionContext extends ParserRuleContext {
		public TerminalNode INTERFACE() { return getToken(caffcParser.INTERFACE, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode CURLY_OPEN() { return getToken(caffcParser.CURLY_OPEN, 0); }
		public InterfaceStatementsContext interfaceStatements() {
			return getRuleContext(InterfaceStatementsContext.class,0);
		}
		public TerminalNode CURLY_CLOSE() { return getToken(caffcParser.CURLY_CLOSE, 0); }
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public GenericsDeclarationsContext genericsDeclarations() {
			return getRuleContext(GenericsDeclarationsContext.class,0);
		}
		public TerminalNode EXTENDS() { return getToken(caffcParser.EXTENDS, 0); }
		public InterfaceImplementationsContext interfaceImplementations() {
			return getRuleContext(InterfaceImplementationsContext.class,0);
		}
		public InterfaceDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDefinition; }
	}

	public final InterfaceDefinitionContext interfaceDefinition() throws RecognitionException {
		InterfaceDefinitionContext _localctx = new InterfaceDefinitionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_interfaceDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(401);
				tags();
				}
			}

			setState(404);
			match(INTERFACE);
			setState(405);
			name();
			setState(407);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(406);
				genericsDeclarations();
				}
			}

			setState(411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(409);
				match(EXTENDS);
				setState(410);
				interfaceImplementations();
				}
			}

			setState(413);
			match(CURLY_OPEN);
			setState(414);
			interfaceStatements();
			setState(415);
			match(CURLY_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public TerminalNode FN() { return getToken(caffcParser.FN, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			_la = _input.LA(1);
			if ( !(_la==FN || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceImplementationsContext extends ParserRuleContext {
		public List<InterfaceImplementationContext> interfaceImplementation() {
			return getRuleContexts(InterfaceImplementationContext.class);
		}
		public InterfaceImplementationContext interfaceImplementation(int i) {
			return getRuleContext(InterfaceImplementationContext.class,i);
		}
		public InterfaceImplementationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceImplementations; }
	}

	public final InterfaceImplementationsContext interfaceImplementations() throws RecognitionException {
		InterfaceImplementationsContext _localctx = new InterfaceImplementationsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_interfaceImplementations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
			interfaceImplementation();
			setState(424);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(420);
				match(T__1);
				setState(421);
				interfaceImplementation();
				}
				}
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceImplementationContext extends ParserRuleContext {
		public FqdnContext fqdn() {
			return getRuleContext(FqdnContext.class,0);
		}
		public GenericsInstantiationsContext genericsInstantiations() {
			return getRuleContext(GenericsInstantiationsContext.class,0);
		}
		public InterfaceImplementationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceImplementation; }
	}

	public final InterfaceImplementationContext interfaceImplementation() throws RecognitionException {
		InterfaceImplementationContext _localctx = new InterfaceImplementationContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_interfaceImplementation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(427);
			fqdn(0);
			setState(429);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__16) {
				{
				setState(428);
				genericsInstantiations();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceStatementsContext extends ParserRuleContext {
		public List<FunctionDeclarationContext> functionDeclaration() {
			return getRuleContexts(FunctionDeclarationContext.class);
		}
		public FunctionDeclarationContext functionDeclaration(int i) {
			return getRuleContext(FunctionDeclarationContext.class,i);
		}
		public InterfaceStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceStatements; }
	}

	public final InterfaceStatementsContext interfaceStatements() throws RecognitionException {
		InterfaceStatementsContext _localctx = new InterfaceStatementsContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_interfaceStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==ID) {
				{
				{
				setState(431);
				functionDeclaration();
				}
				}
				setState(436);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public ExtendContext extend() {
			return getRuleContext(ExtendContext.class,0);
		}
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public GenericsDeclarationsContext genericsDeclarations() {
			return getRuleContext(GenericsDeclarationsContext.class,0);
		}
		public ParameterDefinitionsContext parameterDefinitions() {
			return getRuleContext(ParameterDefinitionsContext.class,0);
		}
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_functionDeclaration);
		int _la;
		try {
			setState(475);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(438);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(437);
					tags();
					}
				}

				setState(440);
				match(ID);
				setState(442);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16) {
					{
					setState(441);
					genericsDeclarations();
					}
				}

				setState(444);
				match(T__0);
				setState(445);
				extend();
				setState(448);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(446);
					match(T__1);
					setState(447);
					parameterDefinitions();
					}
				}

				setState(450);
				match(T__2);
				setState(455);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(451);
					match(T__3);
					setState(453);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
					case 1:
						{
						setState(452);
						returnType();
						}
						break;
					}
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(458);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(457);
					tags();
					}
				}

				setState(460);
				match(ID);
				setState(462);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__16) {
					{
					setState(461);
					genericsDeclarations();
					}
				}

				setState(464);
				match(T__0);
				setState(466);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__36 || _la==T__37 || ((((_la - 85)) & ~0x3f) == 0 && ((1L << (_la - 85)) & 680959L) != 0)) {
					{
					setState(465);
					parameterDefinitions();
					}
				}

				setState(468);
				match(T__2);
				setState(473);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(469);
					match(T__3);
					setState(471);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
					case 1:
						{
						setState(470);
						returnType();
						}
						break;
					}
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TagDefinitionContext extends ParserRuleContext {
		public TerminalNode TAG() { return getToken(caffcParser.TAG, 0); }
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(caffcParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(caffcParser.CURLY_CLOSE, 0); }
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public List<FieldDeclarationContext> fieldDeclaration() {
			return getRuleContexts(FieldDeclarationContext.class);
		}
		public FieldDeclarationContext fieldDeclaration(int i) {
			return getRuleContext(FieldDeclarationContext.class,i);
		}
		public TagDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagDefinition; }
	}

	public final TagDefinitionContext tagDefinition() throws RecognitionException {
		TagDefinitionContext _localctx = new TagDefinitionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_tagDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(478);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(477);
				tags();
				}
			}

			setState(480);
			match(TAG);
			setState(481);
			match(ID);
			setState(482);
			match(CURLY_OPEN);
			setState(486);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__36 || _la==T__37 || ((((_la - 85)) & ~0x3f) == 0 && ((1L << (_la - 85)) & 549887L) != 0)) {
				{
				{
				setState(483);
				fieldDeclaration();
				}
				}
				setState(488);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(489);
			match(CURLY_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FieldDeclarationContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(caffcParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(caffcParser.ID, i);
		}
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclaration; }
	}

	public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
		FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_fieldDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(491);
				tags();
				}
			}

			setState(494);
			typeName(0);
			setState(495);
			match(ID);
			setState(500);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(496);
				match(T__1);
				setState(497);
				match(ID);
				}
				}
				setState(502);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public VariableDeclarationsContext variableDeclarations() {
			return getRuleContext(VariableDeclarationsContext.class,0);
		}
		public ReturnContext return_() {
			return getRuleContext(ReturnContext.class,0);
		}
		public ControlFlowContext controlFlow() {
			return getRuleContext(ControlFlowContext.class,0);
		}
		public NativeBlockContext nativeBlock() {
			return getRuleContext(NativeBlockContext.class,0);
		}
		public WhileBlockContext whileBlock() {
			return getRuleContext(WhileBlockContext.class,0);
		}
		public ForBlockContext forBlock() {
			return getRuleContext(ForBlockContext.class,0);
		}
		public IfBlockContext ifBlock() {
			return getRuleContext(IfBlockContext.class,0);
		}
		public SwitchBlockContext switchBlock() {
			return getRuleContext(SwitchBlockContext.class,0);
		}
		public TryCatchBlockContext tryCatchBlock() {
			return getRuleContext(TryCatchBlockContext.class,0);
		}
		public ThrowStatementContext throwStatement() {
			return getRuleContext(ThrowStatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignExpressionContext assignExpression() {
			return getRuleContext(AssignExpressionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_statement);
		try {
			setState(516);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(503);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(504);
				variableDeclarations();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(505);
				return_();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(506);
				controlFlow();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(507);
				nativeBlock();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(508);
				whileBlock();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(509);
				forBlock();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(510);
				ifBlock();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(511);
				switchBlock();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(512);
				tryCatchBlock();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(513);
				throwStatement();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(514);
				expression(0);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(515);
				assignExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ThrowStatementContext extends ParserRuleContext {
		public TerminalNode THROW() { return getToken(caffcParser.THROW, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwStatement; }
	}

	public final ThrowStatementContext throwStatement() throws RecognitionException {
		ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518);
			match(THROW);
			setState(519);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public TerminalNode CURLY_OPEN() { return getToken(caffcParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(caffcParser.CURLY_CLOSE, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(521);
			match(CURLY_OPEN);
			setState(525);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1748526815934167042L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 209648004591L) != 0)) {
				{
				{
				setState(522);
				statement();
				}
				}
				setState(527);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(528);
			match(CURLY_CLOSE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileBlockContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(caffcParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileBlock; }
	}

	public final WhileBlockContext whileBlock() throws RecognitionException {
		WhileBlockContext _localctx = new WhileBlockContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_whileBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			match(WHILE);
			setState(531);
			expression(0);
			setState(532);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForBlockContext extends ParserRuleContext {
		public AssignExpressionContext initExpression;
		public ExpressionContext conditionExpression;
		public ExpressionContext incrementExpression;
		public AssignExpressionContext incrementAssignExpression;
		public Token variableName;
		public TerminalNode FOR() { return getToken(caffcParser.FOR, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public VariableDeclarationsContext variableDeclarations() {
			return getRuleContext(VariableDeclarationsContext.class,0);
		}
		public List<AssignExpressionContext> assignExpression() {
			return getRuleContexts(AssignExpressionContext.class);
		}
		public AssignExpressionContext assignExpression(int i) {
			return getRuleContext(AssignExpressionContext.class,i);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode IN() { return getToken(caffcParser.IN, 0); }
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public ForBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forBlock; }
	}

	public final ForBlockContext forBlock() throws RecognitionException {
		ForBlockContext _localctx = new ForBlockContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_forBlock);
		try {
			setState(555);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(534);
				match(FOR);
				setState(537);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
				case 1:
					{
					setState(535);
					((ForBlockContext)_localctx).initExpression = assignExpression();
					}
					break;
				case 2:
					{
					setState(536);
					variableDeclarations();
					}
					break;
				}
				setState(539);
				match(T__5);
				setState(540);
				((ForBlockContext)_localctx).conditionExpression = expression(0);
				setState(541);
				match(T__5);
				setState(544);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
				case 1:
					{
					setState(542);
					((ForBlockContext)_localctx).incrementExpression = expression(0);
					}
					break;
				case 2:
					{
					setState(543);
					((ForBlockContext)_localctx).incrementAssignExpression = assignExpression();
					}
					break;
				}
				setState(546);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(548);
				match(FOR);
				setState(549);
				typeName(0);
				setState(550);
				((ForBlockContext)_localctx).variableName = match(ID);
				setState(551);
				match(IN);
				setState(552);
				expression(0);
				setState(553);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfBlockContext extends ParserRuleContext {
		public BlockContext trueBlock;
		public BlockContext falseBlock;
		public TerminalNode IF() { return getToken(caffcParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnContext return_() {
			return getRuleContext(ReturnContext.class,0);
		}
		public ControlFlowContext controlFlow() {
			return getRuleContext(ControlFlowContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(caffcParser.ELSE, 0); }
		public IfBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBlock; }
	}

	public final IfBlockContext ifBlock() throws RecognitionException {
		IfBlockContext _localctx = new IfBlockContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_ifBlock);
		try {
			setState(570);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(557);
				match(IF);
				setState(558);
				expression(0);
				setState(562);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CURLY_OPEN:
					{
					setState(559);
					((IfBlockContext)_localctx).trueBlock = block();
					}
					break;
				case RETURN:
					{
					setState(560);
					return_();
					}
					break;
				case BREAK:
				case CONTINUE:
					{
					setState(561);
					controlFlow();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(564);
				match(IF);
				setState(565);
				expression(0);
				setState(566);
				((IfBlockContext)_localctx).trueBlock = block();
				setState(567);
				match(ELSE);
				setState(568);
				((IfBlockContext)_localctx).falseBlock = block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchBlockContext extends ParserRuleContext {
		public TerminalNode SWITCH() { return getToken(caffcParser.SWITCH, 0); }
		public TerminalNode CURLY_OPEN() { return getToken(caffcParser.CURLY_OPEN, 0); }
		public TerminalNode CURLY_CLOSE() { return getToken(caffcParser.CURLY_CLOSE, 0); }
		public List<SwitchBranchContext> switchBranch() {
			return getRuleContexts(SwitchBranchContext.class);
		}
		public SwitchBranchContext switchBranch(int i) {
			return getRuleContext(SwitchBranchContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SwitchBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlock; }
	}

	public final SwitchBlockContext switchBlock() throws RecognitionException {
		SwitchBlockContext _localctx = new SwitchBlockContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_switchBlock);
		int _la;
		try {
			setState(591);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(572);
				match(SWITCH);
				setState(573);
				match(CURLY_OPEN);
				setState(575); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(574);
					switchBranch();
					}
					}
					setState(577); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CASE || _la==DEFAULT );
				setState(579);
				match(CURLY_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(581);
				match(SWITCH);
				setState(582);
				expression(0);
				setState(583);
				match(CURLY_OPEN);
				setState(585); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(584);
					switchBranch();
					}
					}
					setState(587); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CASE || _la==DEFAULT );
				setState(589);
				match(CURLY_CLOSE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchCaseBodyContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ReturnContext return_() {
			return getRuleContext(ReturnContext.class,0);
		}
		public ControlFlowContext controlFlow() {
			return getRuleContext(ControlFlowContext.class,0);
		}
		public SwitchCaseBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchCaseBody; }
	}

	public final SwitchCaseBodyContext switchCaseBody() throws RecognitionException {
		SwitchCaseBodyContext _localctx = new SwitchCaseBodyContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_switchCaseBody);
		try {
			setState(596);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CURLY_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(593);
				block();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 2);
				{
				setState(594);
				return_();
				}
				break;
			case BREAK:
			case CONTINUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(595);
				controlFlow();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchBranchContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(caffcParser.CASE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SwitchCaseBodyContext switchCaseBody() {
			return getRuleContext(SwitchCaseBodyContext.class,0);
		}
		public TerminalNode DEFAULT() { return getToken(caffcParser.DEFAULT, 0); }
		public SwitchBranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBranch; }
	}

	public final SwitchBranchContext switchBranch() throws RecognitionException {
		SwitchBranchContext _localctx = new SwitchBranchContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_switchBranch);
		try {
			setState(606);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(598);
				match(CASE);
				setState(599);
				expression(0);
				setState(600);
				match(T__4);
				setState(601);
				switchCaseBody();
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(603);
				match(DEFAULT);
				setState(604);
				match(T__4);
				setState(605);
				switchCaseBody();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TryCatchBlockContext extends ParserRuleContext {
		public TerminalNode TRY() { return getToken(caffcParser.TRY, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<CatchBlockContext> catchBlock() {
			return getRuleContexts(CatchBlockContext.class);
		}
		public CatchBlockContext catchBlock(int i) {
			return getRuleContext(CatchBlockContext.class,i);
		}
		public FinallyBlockContext finallyBlock() {
			return getRuleContext(FinallyBlockContext.class,0);
		}
		public TerminalNode FINALLY() { return getToken(caffcParser.FINALLY, 0); }
		public TryCatchBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryCatchBlock; }
	}

	public final TryCatchBlockContext tryCatchBlock() throws RecognitionException {
		TryCatchBlockContext _localctx = new TryCatchBlockContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_tryCatchBlock);
		int _la;
		try {
			setState(624);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(608);
				match(TRY);
				setState(609);
				block();
				setState(613);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CATCH) {
					{
					{
					setState(610);
					catchBlock();
					}
					}
					setState(615);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(617);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(616);
					finallyBlock();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(619);
				match(TRY);
				setState(620);
				block();
				setState(621);
				match(FINALLY);
				setState(622);
				block();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CatchBlockContext extends ParserRuleContext {
		public TerminalNode CATCH() { return getToken(caffcParser.CATCH, 0); }
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CatchBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchBlock; }
	}

	public final CatchBlockContext catchBlock() throws RecognitionException {
		CatchBlockContext _localctx = new CatchBlockContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_catchBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(626);
			match(CATCH);
			setState(627);
			match(T__0);
			setState(628);
			classType();
			setState(629);
			match(ID);
			setState(630);
			match(T__2);
			setState(631);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FinallyBlockContext extends ParserRuleContext {
		public TerminalNode FINALLY() { return getToken(caffcParser.FINALLY, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FinallyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyBlock; }
	}

	public final FinallyBlockContext finallyBlock() throws RecognitionException {
		FinallyBlockContext _localctx = new FinallyBlockContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_finallyBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(633);
			match(FINALLY);
			setState(634);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(caffcParser.RETURN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return; }
	}

	public final ReturnContext return_() throws RecognitionException {
		ReturnContext _localctx = new ReturnContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_return);
		int _la;
		try {
			setState(646);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(636);
				match(RETURN);
				setState(637);
				expression(0);
				setState(642);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(638);
					match(T__1);
					setState(639);
					expression(0);
					}
					}
					setState(644);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(645);
				match(RETURN);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DecoratorCallContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public DecoratorCallContext decoratorCall() {
			return getRuleContext(DecoratorCallContext.class,0);
		}
		public DecoratorCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decoratorCall; }
	}

	public final DecoratorCallContext decoratorCall() throws RecognitionException {
		DecoratorCallContext _localctx = new DecoratorCallContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_decoratorCall);
		try {
			setState(656);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(648);
				match(T__6);
				setState(649);
				expression(0);
				setState(650);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(652);
				match(T__6);
				setState(653);
				expression(0);
				setState(654);
				decoratorCall();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ControlFlowContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(caffcParser.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(caffcParser.CONTINUE, 0); }
		public ControlFlowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_controlFlow; }
	}

	public final ControlFlowContext controlFlow() throws RecognitionException {
		ControlFlowContext _localctx = new ControlFlowContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_controlFlow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(658);
			_la = _input.LA(1);
			if ( !(_la==BREAK || _la==CONTINUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationsContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public VariableDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarations; }
	}

	public final VariableDeclarationsContext variableDeclarations() throws RecognitionException {
		VariableDeclarationsContext _localctx = new VariableDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_variableDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(660);
			varType();
			setState(661);
			variableDeclaration();
			setState(666);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(662);
				match(T__1);
				setState(663);
				variableDeclaration();
				}
				}
				setState(668);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(669);
			match(ID);
			setState(672);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(670);
				match(T__7);
				setState(671);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarTypeContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(caffcParser.VAR, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public VarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varType; }
	}

	public final VarTypeContext varType() throws RecognitionException {
		VarTypeContext _localctx = new VarTypeContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_varType);
		try {
			setState(676);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(674);
				match(VAR);
				}
				break;
			case T__36:
			case T__37:
			case U8:
			case I8:
			case U16:
			case I16:
			case U32:
			case I32:
			case U64:
			case I64:
			case F32:
			case F64:
			case FN:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(675);
				typeName(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TagsContext extends ParserRuleContext {
		public List<TagContext> tag() {
			return getRuleContexts(TagContext.class);
		}
		public TagContext tag(int i) {
			return getRuleContext(TagContext.class,i);
		}
		public TagsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tags; }
	}

	public final TagsContext tags() throws RecognitionException {
		TagsContext _localctx = new TagsContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_tags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(679); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(678);
				tag();
				}
				}
				setState(681); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SHARP );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TagContext extends ParserRuleContext {
		public TerminalNode SHARP() { return getToken(caffcParser.SHARP, 0); }
		public FqdnContext fqdn() {
			return getRuleContext(FqdnContext.class,0);
		}
		public List<TagParamContext> tagParam() {
			return getRuleContexts(TagParamContext.class);
		}
		public TagParamContext tagParam(int i) {
			return getRuleContext(TagParamContext.class,i);
		}
		public TagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tag; }
	}

	public final TagContext tag() throws RecognitionException {
		TagContext _localctx = new TagContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_tag);
		int _la;
		try {
			setState(698);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(683);
				match(SHARP);
				setState(684);
				fqdn(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(685);
				match(SHARP);
				setState(686);
				fqdn(0);
				setState(687);
				match(T__0);
				setState(688);
				tagParam();
				setState(693);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(689);
					match(T__1);
					setState(690);
					tagParam();
					}
					}
					setState(695);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(696);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TagParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public ConstExpressionContext constExpression() {
			return getRuleContext(ConstExpressionContext.class,0);
		}
		public TagParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tagParam; }
	}

	public final TagParamContext tagParam() throws RecognitionException {
		TagParamContext _localctx = new TagParamContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_tagParam);
		try {
			setState(704);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(700);
				match(ID);
				setState(701);
				match(T__7);
				setState(702);
				constExpression();
				}
				break;
			case CHAR:
			case STRING:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(703);
				constExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstExpressionContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(caffcParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(caffcParser.STRING, 0); }
		public TerminalNode CHAR() { return getToken(caffcParser.CHAR, 0); }
		public ConstExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constExpression; }
	}

	public final ConstExpressionContext constExpression() throws RecognitionException {
		ConstExpressionContext _localctx = new ConstExpressionContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_constExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(706);
			_la = _input.LA(1);
			if ( !(_la==CHAR || _la==STRING || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FqdnContext extends ParserRuleContext {
		public FqdnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fqdn; }
	 
		public FqdnContext() { }
		public void copyFrom(FqdnContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FqdnMultipleFnContext extends FqdnContext {
		public FqdnContext fqdn() {
			return getRuleContext(FqdnContext.class,0);
		}
		public TerminalNode DOT() { return getToken(caffcParser.DOT, 0); }
		public TerminalNode FN() { return getToken(caffcParser.FN, 0); }
		public FqdnMultipleFnContext(FqdnContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FqdnMultipleContext extends FqdnContext {
		public FqdnContext fqdn() {
			return getRuleContext(FqdnContext.class,0);
		}
		public TerminalNode DOT() { return getToken(caffcParser.DOT, 0); }
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public FqdnMultipleContext(FqdnContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FqdnFnContext extends FqdnContext {
		public TerminalNode FN() { return getToken(caffcParser.FN, 0); }
		public FqdnFnContext(FqdnContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FqdnIdContext extends FqdnContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public FqdnIdContext(FqdnContext ctx) { copyFrom(ctx); }
	}

	public final FqdnContext fqdn() throws RecognitionException {
		return fqdn(0);
	}

	private FqdnContext fqdn(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FqdnContext _localctx = new FqdnContext(_ctx, _parentState);
		FqdnContext _prevctx = _localctx;
		int _startState = 98;
		enterRecursionRule(_localctx, 98, RULE_fqdn, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(711);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new FqdnIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(709);
				match(ID);
				}
				break;
			case FN:
				{
				_localctx = new FqdnFnContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(710);
				match(FN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(721);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(719);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
					case 1:
						{
						_localctx = new FqdnMultipleContext(new FqdnContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_fqdn);
						setState(713);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(714);
						match(DOT);
						setState(715);
						match(ID);
						}
						break;
					case 2:
						{
						_localctx = new FqdnMultipleFnContext(new FqdnContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_fqdn);
						setState(716);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(717);
						match(DOT);
						setState(718);
						match(FN);
						}
						break;
					}
					} 
				}
				setState(723);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExTrueContext extends ExpressionContext {
		public TerminalNode TRUE() { return getToken(caffcParser.TRUE, 0); }
		public ExTrueContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExNewObjectContext extends ExpressionContext {
		public TerminalNode NEW() { return getToken(caffcParser.NEW, 0); }
		public NewTypeContext newType() {
			return getRuleContext(NewTypeContext.class,0);
		}
		public CallArgumentListContext callArgumentList() {
			return getRuleContext(CallArgumentListContext.class,0);
		}
		public ExNewObjectContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExBitNotContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExBitNotContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExNullContext extends ExpressionContext {
		public TerminalNode NULL() { return getToken(caffcParser.NULL, 0); }
		public ExNullContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExBitAndContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExBitAndContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExFnCallContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GenericsInstantiationsContext genericsInstantiations() {
			return getRuleContext(GenericsInstantiationsContext.class,0);
		}
		public CallArgumentListContext callArgumentList() {
			return getRuleContext(CallArgumentListContext.class,0);
		}
		public ExFnCallContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExFalseContext extends ExpressionContext {
		public TerminalNode FALSE() { return getToken(caffcParser.FALSE, 0); }
		public ExFalseContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExCastContext extends ExpressionContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExCastContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExStringContext extends ExpressionContext {
		public TerminalNode STRING() { return getToken(caffcParser.STRING, 0); }
		public ExStringContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExUnaryMinusContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExUnaryMinusContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExInstanceOfContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public TerminalNode INSTANCEOF() { return getToken(caffcParser.INSTANCEOF, 0); }
		public NewTypeContext newType() {
			return getRuleContext(NewTypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(caffcParser.NOT, 0); }
		public ExInstanceOfContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExTernaryContext extends ExpressionContext {
		public ExpressionContext checkExpression;
		public ExpressionContext trueExpression;
		public ExpressionContext falseExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExTernaryContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExBitOrContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public TerminalNode PIPE() { return getToken(caffcParser.PIPE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExBitOrContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExLtLteGtGteContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExLtLteGtGteContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExRangeAccessContext extends ExpressionContext {
		public ExpressionContext arrayExpression;
		public ExpressionContext startExpression;
		public ExpressionContext endExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExRangeAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExBoolNotContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NOT() { return getToken(caffcParser.NOT, 0); }
		public ExBoolNotContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExAddSubContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExAddSubContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExIndexAccessContext extends ExpressionContext {
		public ExpressionContext arraryExpression;
		public ExpressionContext indexExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExIndexAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExNumberContext extends ExpressionContext {
		public TerminalNode NUMBER() { return getToken(caffcParser.NUMBER, 0); }
		public ExNumberContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExNewArrayContext extends ExpressionContext {
		public TerminalNode NEW() { return getToken(caffcParser.NEW, 0); }
		public NewTypeContext newType() {
			return getRuleContext(NewTypeContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExNewArrayContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExBoolAndContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public TerminalNode AND() { return getToken(caffcParser.AND, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExBoolAndContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExFStringContext extends ExpressionContext {
		public TerminalNode F_STRING() { return getToken(caffcParser.F_STRING, 0); }
		public ExFStringContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExBoolOrContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public TerminalNode OR() { return getToken(caffcParser.OR, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExBoolOrContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExIdContext extends ExpressionContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public ExIdContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExEqNeqContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExEqNeqContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExCharContext extends ExpressionContext {
		public TerminalNode CHAR() { return getToken(caffcParser.CHAR, 0); }
		public ExCharContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExParensContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExParensContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExMulModContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode STAR() { return getToken(caffcParser.STAR, 0); }
		public ExMulModContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExShiftContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public ShiftOpContext shiftOp() {
			return getRuleContext(ShiftOpContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExShiftContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExDotAccessContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(caffcParser.DOT, 0); }
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public ExDotAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExBitXorContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExBitXorContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExDivContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExDivContext(ExpressionContext ctx) { copyFrom(ctx); }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 100;
		enterRecursionRule(_localctx, 100, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(766);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				{
				_localctx = new ExNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(725);
				match(NUMBER);
				}
				break;
			case 2:
				{
				_localctx = new ExStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(726);
				match(STRING);
				}
				break;
			case 3:
				{
				_localctx = new ExFStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(727);
				match(F_STRING);
				}
				break;
			case 4:
				{
				_localctx = new ExCharContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(728);
				match(CHAR);
				}
				break;
			case 5:
				{
				_localctx = new ExIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(729);
				match(ID);
				}
				break;
			case 6:
				{
				_localctx = new ExNullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(730);
				match(NULL);
				}
				break;
			case 7:
				{
				_localctx = new ExTrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(731);
				match(TRUE);
				}
				break;
			case 8:
				{
				_localctx = new ExFalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(732);
				match(FALSE);
				}
				break;
			case 9:
				{
				_localctx = new ExNewObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(733);
				match(NEW);
				setState(734);
				newType(0);
				setState(735);
				match(T__0);
				setState(737);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3848290711554L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 206158430255L) != 0)) {
					{
					setState(736);
					callArgumentList();
					}
				}

				setState(739);
				match(T__2);
				}
				break;
			case 10:
				{
				_localctx = new ExNewArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(741);
				match(NEW);
				setState(742);
				newType(0);
				setState(747); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(743);
						match(T__8);
						setState(744);
						expression(0);
						setState(745);
						match(T__9);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(749); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 11:
				{
				_localctx = new ExCastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(751);
				match(T__0);
				setState(752);
				typeName(0);
				setState(753);
				match(T__2);
				setState(754);
				expression(18);
				}
				break;
			case 12:
				{
				_localctx = new ExParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(756);
				match(T__0);
				setState(757);
				expression(0);
				setState(758);
				match(T__2);
				}
				break;
			case 13:
				{
				_localctx = new ExBoolNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(760);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(761);
				expression(15);
				}
				break;
			case 14:
				{
				_localctx = new ExBitNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(762);
				match(T__11);
				setState(763);
				expression(14);
				}
				break;
			case 15:
				{
				_localctx = new ExUnaryMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(764);
				match(T__12);
				setState(765);
				expression(13);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(846);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(844);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
					case 1:
						{
						_localctx = new ExMulModContext(new ExpressionContext(_parentctx, _parentState));
						((ExMulModContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(768);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(769);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==STAR) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(770);
						((ExMulModContext)_localctx).rightExpression = expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ExDivContext(new ExpressionContext(_parentctx, _parentState));
						((ExDivContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(771);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(772);
						match(T__14);
						setState(773);
						((ExDivContext)_localctx).rightExpression = expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExAddSubContext(new ExpressionContext(_parentctx, _parentState));
						((ExAddSubContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(774);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(775);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__15) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(776);
						((ExAddSubContext)_localctx).rightExpression = expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExShiftContext(new ExpressionContext(_parentctx, _parentState));
						((ExShiftContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(777);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(778);
						shiftOp();
						setState(779);
						((ExShiftContext)_localctx).rightExpression = expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExLtLteGtGteContext(new ExpressionContext(_parentctx, _parentState));
						((ExLtLteGtGteContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(781);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(782);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1966080L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(783);
						((ExLtLteGtGteContext)_localctx).rightExpression = expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExEqNeqContext(new ExpressionContext(_parentctx, _parentState));
						((ExEqNeqContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(784);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(785);
						_la = _input.LA(1);
						if ( !(_la==T__20 || _la==T__21) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(786);
						((ExEqNeqContext)_localctx).rightExpression = expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExBitAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitAndContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(787);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(788);
						match(T__22);
						setState(789);
						((ExBitAndContext)_localctx).rightExpression = expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExBitXorContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitXorContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(790);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(791);
						match(T__23);
						setState(792);
						((ExBitXorContext)_localctx).rightExpression = expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExBitOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitOrContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(793);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(794);
						match(PIPE);
						setState(795);
						((ExBitOrContext)_localctx).rightExpression = expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExBoolAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExBoolAndContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(796);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(797);
						match(AND);
						setState(798);
						((ExBoolAndContext)_localctx).rightExpression = expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExBoolOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExBoolOrContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(799);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(800);
						match(OR);
						setState(801);
						((ExBoolOrContext)_localctx).rightExpression = expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExTernaryContext(new ExpressionContext(_parentctx, _parentState));
						((ExTernaryContext)_localctx).checkExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(802);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(808);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__24:
							{
							setState(803);
							match(T__24);
							setState(804);
							((ExTernaryContext)_localctx).trueExpression = expression(0);
							setState(805);
							match(T__4);
							}
							break;
						case T__25:
							{
							setState(807);
							match(T__25);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(810);
						((ExTernaryContext)_localctx).falseExpression = expression(2);
						}
						break;
					case 13:
						{
						_localctx = new ExDotAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(811);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(812);
						match(DOT);
						setState(813);
						match(ID);
						}
						break;
					case 14:
						{
						_localctx = new ExFnCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(814);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(816);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__16) {
							{
							setState(815);
							genericsInstantiations();
							}
						}

						setState(818);
						match(T__0);
						setState(820);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3848290711554L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 206158430255L) != 0)) {
							{
							setState(819);
							callArgumentList();
							}
						}

						setState(822);
						match(T__2);
						}
						break;
					case 15:
						{
						_localctx = new ExIndexAccessContext(new ExpressionContext(_parentctx, _parentState));
						((ExIndexAccessContext)_localctx).arraryExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(823);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(824);
						match(T__8);
						setState(825);
						((ExIndexAccessContext)_localctx).indexExpression = expression(0);
						setState(826);
						match(T__9);
						}
						break;
					case 16:
						{
						_localctx = new ExRangeAccessContext(new ExpressionContext(_parentctx, _parentState));
						((ExRangeAccessContext)_localctx).arrayExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(828);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(829);
						match(T__8);
						setState(831);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3848290711554L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 206158430255L) != 0)) {
							{
							setState(830);
							((ExRangeAccessContext)_localctx).startExpression = expression(0);
							}
						}

						setState(833);
						match(T__4);
						setState(835);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3848290711554L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 206158430255L) != 0)) {
							{
							setState(834);
							((ExRangeAccessContext)_localctx).endExpression = expression(0);
							}
						}

						setState(837);
						match(T__9);
						}
						break;
					case 17:
						{
						_localctx = new ExInstanceOfContext(new ExpressionContext(_parentctx, _parentState));
						((ExInstanceOfContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(838);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(840);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(839);
							match(NOT);
							}
						}

						setState(842);
						match(INSTANCEOF);
						setState(843);
						newType(0);
						}
						break;
					}
					} 
				}
				setState(848);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FStringInnerContext extends ParserRuleContext {
		public FStringPrimaryContext fStringPrimary() {
			return getRuleContext(FStringPrimaryContext.class,0);
		}
		public TerminalNode EOF() { return getToken(caffcParser.EOF, 0); }
		public FStringInnerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fStringInner; }
	}

	public final FStringInnerContext fStringInner() throws RecognitionException {
		FStringInnerContext _localctx = new FStringInnerContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_fStringInner);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(849);
			fStringPrimary(0);
			setState(850);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FStringPrimaryContext extends ParserRuleContext {
		public FStringPrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fStringPrimary; }
	 
		public FStringPrimaryContext() { }
		public void copyFrom(FStringPrimaryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FStrDotContext extends FStringPrimaryContext {
		public FStringPrimaryContext fStringPrimary() {
			return getRuleContext(FStringPrimaryContext.class,0);
		}
		public TerminalNode DOT() { return getToken(caffcParser.DOT, 0); }
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public FStrDotContext(FStringPrimaryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FStrRangeContext extends FStringPrimaryContext {
		public FStringIndexExprContext fStringStart;
		public FStringIndexExprContext fStringEnd;
		public FStringPrimaryContext fStringPrimary() {
			return getRuleContext(FStringPrimaryContext.class,0);
		}
		public List<FStringIndexExprContext> fStringIndexExpr() {
			return getRuleContexts(FStringIndexExprContext.class);
		}
		public FStringIndexExprContext fStringIndexExpr(int i) {
			return getRuleContext(FStringIndexExprContext.class,i);
		}
		public FStrRangeContext(FStringPrimaryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FStrIndexContext extends FStringPrimaryContext {
		public FStringPrimaryContext fStringPrimary() {
			return getRuleContext(FStringPrimaryContext.class,0);
		}
		public FStringIndexExprContext fStringIndexExpr() {
			return getRuleContext(FStringIndexExprContext.class,0);
		}
		public FStrIndexContext(FStringPrimaryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FStrIdContext extends FStringPrimaryContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public FStrIdContext(FStringPrimaryContext ctx) { copyFrom(ctx); }
	}

	public final FStringPrimaryContext fStringPrimary() throws RecognitionException {
		return fStringPrimary(0);
	}

	private FStringPrimaryContext fStringPrimary(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FStringPrimaryContext _localctx = new FStringPrimaryContext(_ctx, _parentState);
		FStringPrimaryContext _prevctx = _localctx;
		int _startState = 104;
		enterRecursionRule(_localctx, 104, RULE_fStringPrimary, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new FStrIdContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(853);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(875);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,103,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(873);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
					case 1:
						{
						_localctx = new FStrDotContext(new FStringPrimaryContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_fStringPrimary);
						setState(855);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(856);
						match(DOT);
						setState(857);
						match(ID);
						}
						break;
					case 2:
						{
						_localctx = new FStrIndexContext(new FStringPrimaryContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_fStringPrimary);
						setState(858);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(859);
						match(T__8);
						setState(860);
						fStringIndexExpr();
						setState(861);
						match(T__9);
						}
						break;
					case 3:
						{
						_localctx = new FStrRangeContext(new FStringPrimaryContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_fStringPrimary);
						setState(863);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(864);
						match(T__8);
						setState(866);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ID || _la==NUMBER) {
							{
							setState(865);
							((FStrRangeContext)_localctx).fStringStart = fStringIndexExpr();
							}
						}

						setState(868);
						match(T__4);
						setState(870);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==ID || _la==NUMBER) {
							{
							setState(869);
							((FStrRangeContext)_localctx).fStringEnd = fStringIndexExpr();
							}
						}

						setState(872);
						match(T__9);
						}
						break;
					}
					} 
				}
				setState(877);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,103,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FStringIndexExprContext extends ParserRuleContext {
		public FStringIndexExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fStringIndexExpr; }
	 
		public FStringIndexExprContext() { }
		public void copyFrom(FStringIndexExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FStrIndexNumberContext extends FStringIndexExprContext {
		public TerminalNode NUMBER() { return getToken(caffcParser.NUMBER, 0); }
		public FStrIndexNumberContext(FStringIndexExprContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FStrIndexPrimaryContext extends FStringIndexExprContext {
		public FStringPrimaryContext fStringPrimary() {
			return getRuleContext(FStringPrimaryContext.class,0);
		}
		public FStrIndexPrimaryContext(FStringIndexExprContext ctx) { copyFrom(ctx); }
	}

	public final FStringIndexExprContext fStringIndexExpr() throws RecognitionException {
		FStringIndexExprContext _localctx = new FStringIndexExprContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_fStringIndexExpr);
		try {
			setState(880);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				_localctx = new FStrIndexNumberContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(878);
				match(NUMBER);
				}
				break;
			case ID:
				_localctx = new FStrIndexPrimaryContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(879);
				fStringPrimary(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ShiftOpContext extends ParserRuleContext {
		public ShiftOpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shiftOp; }
	}

	public final ShiftOpContext shiftOp() throws RecognitionException {
		ShiftOpContext _localctx = new ShiftOpContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_shiftOp);
		try {
			setState(885);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
				enterOuterAlt(_localctx, 1);
				{
				setState(882);
				match(T__26);
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				setState(883);
				match(T__19);
				setState(884);
				match(T__19);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignExpressionContext extends ParserRuleContext {
		public AssignExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignExpression; }
	 
		public AssignExpressionContext() { }
		public void copyFrom(AssignExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExAssignContext extends AssignExpressionContext {
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExAssignContext(AssignExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExOpAssignContext extends AssignExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExOpAssignContext(AssignExpressionContext ctx) { copyFrom(ctx); }
	}

	public final AssignExpressionContext assignExpression() throws RecognitionException {
		AssignExpressionContext _localctx = new AssignExpressionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_assignExpression);
		int _la;
		try {
			setState(902);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
			case 1:
				_localctx = new ExAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(887);
				expression(0);
				setState(892);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(888);
					match(T__1);
					setState(889);
					expression(0);
					}
					}
					setState(894);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(895);
				match(T__7);
				setState(896);
				((ExAssignContext)_localctx).rightExpression = expression(0);
				}
				break;
			case 2:
				_localctx = new ExOpAssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(898);
				((ExOpAssignContext)_localctx).leftExpression = expression(0);
				setState(899);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 137170518016L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(900);
				((ExOpAssignContext)_localctx).rightExpression = expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionTupleContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionTupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionTuple; }
	}

	public final ExpressionTupleContext expressionTuple() throws RecognitionException {
		ExpressionTupleContext _localctx = new ExpressionTupleContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_expressionTuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(904);
			expression(0);
			setState(909);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(905);
				match(T__1);
				setState(906);
				expression(0);
				}
				}
				setState(911);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallArgumentListContext extends ParserRuleContext {
		public List<CallArgumentContext> callArgument() {
			return getRuleContexts(CallArgumentContext.class);
		}
		public CallArgumentContext callArgument(int i) {
			return getRuleContext(CallArgumentContext.class,i);
		}
		public CallArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callArgumentList; }
	}

	public final CallArgumentListContext callArgumentList() throws RecognitionException {
		CallArgumentListContext _localctx = new CallArgumentListContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_callArgumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(912);
			callArgument();
			setState(917);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(913);
				match(T__1);
				setState(914);
				callArgument();
				}
				}
				setState(919);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CallArgumentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CallArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callArgument; }
	}

	public final CallArgumentContext callArgument() throws RecognitionException {
		CallArgumentContext _localctx = new CallArgumentContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_callArgument);
		try {
			setState(924);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(920);
				match(ID);
				setState(921);
				match(T__7);
				setState(922);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(923);
				expression(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExtendContext extends ParserRuleContext {
		public TerminalNode EXTENDS() { return getToken(caffcParser.EXTENDS, 0); }
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public ExtendContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_extend; }
	}

	public final ExtendContext extend() throws RecognitionException {
		ExtendContext _localctx = new ExtendContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_extend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(926);
			match(EXTENDS);
			setState(927);
			classType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterDefinitionsContext extends ParserRuleContext {
		public List<ParameterDefinitionContext> parameterDefinition() {
			return getRuleContexts(ParameterDefinitionContext.class);
		}
		public ParameterDefinitionContext parameterDefinition(int i) {
			return getRuleContext(ParameterDefinitionContext.class,i);
		}
		public TerminalNode ELLIPSIS() { return getToken(caffcParser.ELLIPSIS, 0); }
		public ParameterDefinitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDefinitions; }
	}

	public final ParameterDefinitionsContext parameterDefinitions() throws RecognitionException {
		ParameterDefinitionsContext _localctx = new ParameterDefinitionsContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_parameterDefinitions);
		int _la;
		try {
			setState(957);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__36:
			case T__37:
			case U8:
			case I8:
			case U16:
			case I16:
			case U32:
			case I32:
			case U64:
			case I64:
			case F32:
			case F64:
			case FN:
			case SHARP:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(929);
				parameterDefinition();
				setState(934);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(930);
					match(T__1);
					setState(931);
					parameterDefinition();
					}
					}
					setState(936);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(946);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELLIPSIS) {
					{
					setState(937);
					match(ELLIPSIS);
					setState(938);
					parameterDefinition();
					setState(943);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1) {
						{
						{
						setState(939);
						match(T__1);
						setState(940);
						parameterDefinition();
						}
						}
						setState(945);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				}
				break;
			case ELLIPSIS:
				enterOuterAlt(_localctx, 2);
				{
				setState(948);
				match(ELLIPSIS);
				setState(949);
				parameterDefinition();
				setState(954);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(950);
					match(T__1);
					setState(951);
					parameterDefinition();
					}
					}
					setState(956);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterDefinitionContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public TerminalNode ELLIPSIS() { return getToken(caffcParser.ELLIPSIS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParameterDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDefinition; }
	}

	public final ParameterDefinitionContext parameterDefinition() throws RecognitionException {
		ParameterDefinitionContext _localctx = new ParameterDefinitionContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_parameterDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(960);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(959);
				tags();
				}
			}

			setState(962);
			typeName(0);
			setState(963);
			match(ID);
			setState(965);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,117,_ctx) ) {
			case 1:
				{
				setState(964);
				match(ELLIPSIS);
				}
				break;
			}
			setState(969);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(967);
				match(T__7);
				setState(968);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeNameContext extends ParserRuleContext {
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
	 
		public TypeNameContext() { }
		public void copyFrom(TypeNameContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeArrayContext extends TypeNameContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TypeArrayContext(TypeNameContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeClassContext extends TypeNameContext {
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public TypeClassContext(TypeNameContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypePrimitiveContext extends TypeNameContext {
		public PrimitiveTypeNameContext primitiveTypeName() {
			return getRuleContext(PrimitiveTypeNameContext.class,0);
		}
		public TypePrimitiveContext(TypeNameContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TypeFunctionContext extends TypeNameContext {
		public FunctionTypeContext functionType() {
			return getRuleContext(FunctionTypeContext.class,0);
		}
		public TypeFunctionContext(TypeNameContext ctx) { copyFrom(ctx); }
	}

	public final TypeNameContext typeName() throws RecognitionException {
		return typeName(0);
	}

	private TypeNameContext typeName(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeNameContext _localctx = new TypeNameContext(_ctx, _parentState);
		TypeNameContext _prevctx = _localctx;
		int _startState = 124;
		enterRecursionRule(_localctx, 124, RULE_typeName, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(975);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
			case 1:
				{
				_localctx = new TypeClassContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(972);
				classType();
				}
				break;
			case 2:
				{
				_localctx = new TypePrimitiveContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(973);
				primitiveTypeName();
				}
				break;
			case 3:
				{
				_localctx = new TypeFunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(974);
				functionType();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(986);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,121,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeArrayContext(new TypeNameContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_typeName);
					setState(977);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(980); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(978);
							match(T__8);
							setState(979);
							match(T__9);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(982); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,120,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(988);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,121,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NewTypeContext extends ParserRuleContext {
		public NewTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newType; }
	 
		public NewTypeContext() { }
		public void copyFrom(NewTypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewTypePrimitiveContext extends NewTypeContext {
		public PrimitiveTypeNameContext primitiveTypeName() {
			return getRuleContext(PrimitiveTypeNameContext.class,0);
		}
		public NewTypePrimitiveContext(NewTypeContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewTypeArrayContext extends NewTypeContext {
		public NewTypeContext newType() {
			return getRuleContext(NewTypeContext.class,0);
		}
		public NewTypeArrayContext(NewTypeContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewTypeClassContext extends NewTypeContext {
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public NewTypeClassContext(NewTypeContext ctx) { copyFrom(ctx); }
	}

	public final NewTypeContext newType() throws RecognitionException {
		return newType(0);
	}

	private NewTypeContext newType(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		NewTypeContext _localctx = new NewTypeContext(_ctx, _parentState);
		NewTypeContext _prevctx = _localctx;
		int _startState = 126;
		enterRecursionRule(_localctx, 126, RULE_newType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(992);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FN:
			case ID:
				{
				_localctx = new NewTypeClassContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(990);
				classType();
				}
				break;
			case T__36:
			case T__37:
			case U8:
			case I8:
			case U16:
			case I16:
			case U32:
			case I32:
			case U64:
			case I64:
			case F32:
			case F64:
				{
				_localctx = new NewTypePrimitiveContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(991);
				primitiveTypeName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(1003);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,124,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NewTypeArrayContext(new NewTypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_newType);
					setState(994);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(997); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(995);
							match(T__8);
							setState(996);
							match(T__9);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(999); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,123,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(1005);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,124,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimitiveTypeNameContext extends ParserRuleContext {
		public TerminalNode U8() { return getToken(caffcParser.U8, 0); }
		public TerminalNode I8() { return getToken(caffcParser.I8, 0); }
		public TerminalNode U16() { return getToken(caffcParser.U16, 0); }
		public TerminalNode I16() { return getToken(caffcParser.I16, 0); }
		public TerminalNode U32() { return getToken(caffcParser.U32, 0); }
		public TerminalNode I32() { return getToken(caffcParser.I32, 0); }
		public TerminalNode U64() { return getToken(caffcParser.U64, 0); }
		public TerminalNode I64() { return getToken(caffcParser.I64, 0); }
		public TerminalNode F32() { return getToken(caffcParser.F32, 0); }
		public TerminalNode F64() { return getToken(caffcParser.F64, 0); }
		public PrimitiveTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveTypeName; }
	}

	public final PrimitiveTypeNameContext primitiveTypeName() throws RecognitionException {
		PrimitiveTypeNameContext _localctx = new PrimitiveTypeNameContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_primitiveTypeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1006);
			_la = _input.LA(1);
			if ( !(((((_la - 37)) & ~0x3f) == 0 && ((1L << (_la - 37)) & 287948901175001091L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassTypeContext extends ParserRuleContext {
		public FqdnContext fqdn() {
			return getRuleContext(FqdnContext.class,0);
		}
		public GenericsInstantiationsContext genericsInstantiations() {
			return getRuleContext(GenericsInstantiationsContext.class,0);
		}
		public ClassTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classType; }
	}

	public final ClassTypeContext classType() throws RecognitionException {
		ClassTypeContext _localctx = new ClassTypeContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1008);
			fqdn(0);
			setState(1010);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,125,_ctx) ) {
			case 1:
				{
				setState(1009);
				genericsInstantiations();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GenericsDeclarationsContext extends ParserRuleContext {
		public List<GenericDeclarationContext> genericDeclaration() {
			return getRuleContexts(GenericDeclarationContext.class);
		}
		public GenericDeclarationContext genericDeclaration(int i) {
			return getRuleContext(GenericDeclarationContext.class,i);
		}
		public GenericsDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericsDeclarations; }
	}

	public final GenericsDeclarationsContext genericsDeclarations() throws RecognitionException {
		GenericsDeclarationsContext _localctx = new GenericsDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_genericsDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1012);
			match(T__16);
			setState(1013);
			genericDeclaration();
			setState(1018);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(1014);
				match(T__1);
				setState(1015);
				genericDeclaration();
				}
				}
				setState(1020);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1021);
			match(T__19);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GenericDeclarationContext extends ParserRuleContext {
		public GenericDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericDeclaration; }
	 
		public GenericDeclarationContext() { }
		public void copyFrom(GenericDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GenericDeclarationRestrictedContext extends GenericDeclarationContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public TerminalNode IS() { return getToken(caffcParser.IS, 0); }
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public GenericDeclarationRestrictedContext(GenericDeclarationContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GenericDeclarationIdContext extends GenericDeclarationContext {
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public GenericDeclarationIdContext(GenericDeclarationContext ctx) { copyFrom(ctx); }
	}

	public final GenericDeclarationContext genericDeclaration() throws RecognitionException {
		GenericDeclarationContext _localctx = new GenericDeclarationContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_genericDeclaration);
		try {
			setState(1027);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
			case 1:
				_localctx = new GenericDeclarationIdContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1023);
				match(ID);
				}
				break;
			case 2:
				_localctx = new GenericDeclarationRestrictedContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1024);
				match(ID);
				setState(1025);
				match(IS);
				setState(1026);
				typeName(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GenericsInstantiationsContext extends ParserRuleContext {
		public GenericsInstantiationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericsInstantiations; }
	 
		public GenericsInstantiationsContext() { }
		public void copyFrom(GenericsInstantiationsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GenericsInstantiationTypeContext extends GenericsInstantiationsContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public GenericsInstantiationTypeContext(GenericsInstantiationsContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GenericsInstantiationEmptyContext extends GenericsInstantiationsContext {
		public GenericsInstantiationEmptyContext(GenericsInstantiationsContext ctx) { copyFrom(ctx); }
	}

	public final GenericsInstantiationsContext genericsInstantiations() throws RecognitionException {
		GenericsInstantiationsContext _localctx = new GenericsInstantiationsContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_genericsInstantiations);
		int _la;
		try {
			setState(1042);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,129,_ctx) ) {
			case 1:
				_localctx = new GenericsInstantiationEmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1029);
				match(T__16);
				setState(1030);
				match(T__19);
				}
				break;
			case 2:
				_localctx = new GenericsInstantiationTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1031);
				match(T__16);
				setState(1032);
				typeName(0);
				setState(1037);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(1033);
					match(T__1);
					setState(1034);
					typeName(0);
					}
					}
					setState(1039);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1040);
				match(T__19);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionTypeContext extends ParserRuleContext {
		public TerminalNode FN() { return getToken(caffcParser.FN, 0); }
		public GenericsInstantiationsContext genericsInstantiations() {
			return getRuleContext(GenericsInstantiationsContext.class,0);
		}
		public FunctionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionType; }
	}

	public final FunctionTypeContext functionType() throws RecognitionException {
		FunctionTypeContext _localctx = new FunctionTypeContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_functionType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1044);
			match(FN);
			setState(1046);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,130,_ctx) ) {
			case 1:
				{
				setState(1045);
				genericsInstantiations();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NamedTypeTupleContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(caffcParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(caffcParser.ID, i);
		}
		public NamedTypeTupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedTypeTuple; }
	}

	public final NamedTypeTupleContext namedTypeTuple() throws RecognitionException {
		NamedTypeTupleContext _localctx = new NamedTypeTupleContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_namedTypeTuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1048);
			typeName(0);
			setState(1049);
			match(ID);
			setState(1056);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(1050);
				match(T__1);
				setState(1051);
				typeName(0);
				setState(1052);
				match(ID);
				}
				}
				setState(1058);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 49:
			return fqdn_sempred((FqdnContext)_localctx, predIndex);
		case 50:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 52:
			return fStringPrimary_sempred((FStringPrimaryContext)_localctx, predIndex);
		case 62:
			return typeName_sempred((TypeNameContext)_localctx, predIndex);
		case 63:
			return newType_sempred((NewTypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean fqdn_sempred(FqdnContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 12);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 10);
		case 5:
			return precpred(_ctx, 9);
		case 6:
			return precpred(_ctx, 8);
		case 7:
			return precpred(_ctx, 7);
		case 8:
			return precpred(_ctx, 6);
		case 9:
			return precpred(_ctx, 5);
		case 10:
			return precpred(_ctx, 4);
		case 11:
			return precpred(_ctx, 3);
		case 12:
			return precpred(_ctx, 2);
		case 13:
			return precpred(_ctx, 1);
		case 14:
			return precpred(_ctx, 24);
		case 15:
			return precpred(_ctx, 21);
		case 16:
			return precpred(_ctx, 20);
		case 17:
			return precpred(_ctx, 19);
		case 18:
			return precpred(_ctx, 16);
		}
		return true;
	}
	private boolean fStringPrimary_sempred(FStringPrimaryContext _localctx, int predIndex) {
		switch (predIndex) {
		case 19:
			return precpred(_ctx, 3);
		case 20:
			return precpred(_ctx, 2);
		case 21:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean typeName_sempred(TypeNameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 22:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean newType_sempred(NewTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 23:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001j\u0424\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007E\u0002"+
		"F\u0007F\u0001\u0000\u0001\u0000\u0005\u0000\u0091\b\u0000\n\u0000\f\u0000"+
		"\u0094\t\u0000\u0001\u0000\u0005\u0000\u0097\b\u0000\n\u0000\f\u0000\u009a"+
		"\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0003"+
		"\u0002\u00a1\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u00ab\b\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004\u00b3\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0003\b\u00be\b\b\u0001"+
		"\b\u0003\b\u00c1\b\b\u0001\b\u0001\b\u0003\b\u00c5\b\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0003\b\u00cb\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u00d0"+
		"\b\b\u0003\b\u00d2\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u00d7\b\b\u0001"+
		"\b\u0003\b\u00da\b\b\u0001\b\u0001\b\u0003\b\u00de\b\b\u0001\b\u0001\b"+
		"\u0003\b\u00e2\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u00e7\b\b\u0003\b\u00e9"+
		"\b\b\u0001\b\u0003\b\u00ec\b\b\u0001\t\u0001\t\u0005\t\u00f0\b\t\n\t\f"+
		"\t\u00f3\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0003\n\u00fa\b\n"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0004\u000b\u0100\b\u000b"+
		"\u000b\u000b\f\u000b\u0101\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0005\f\u010c\b\f\n\f\f\f\u010f\t\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u0118\b\f\n\f"+
		"\f\f\u011b\t\f\u0001\f\u0003\f\u011e\b\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0004\r\u0124\b\r\u000b\r\f\r\u0125\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0130"+
		"\b\u000e\n\u000e\f\u000e\u0133\t\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u013c\b\u000e"+
		"\n\u000e\f\u000e\u013f\t\u000e\u0001\u000e\u0003\u000e\u0142\b\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u0149"+
		"\b\u000f\n\u000f\f\u000f\u014c\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0005\u000f\u0153\b\u000f\n\u000f\f\u000f\u0156"+
		"\t\u000f\u0001\u000f\u0003\u000f\u0159\b\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0160\b\u0010\n\u0010"+
		"\f\u0010\u0163\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u016a\b\u0010\n\u0010\f\u0010\u016d\t\u0010\u0001"+
		"\u0010\u0003\u0010\u0170\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u0175\b\u0011\u0001\u0012\u0003\u0012\u0178\b\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0003\u0012\u017d\b\u0012\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u0181\b\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0185\b"+
		"\u0012\n\u0012\f\u0012\u0188\t\u0012\u0001\u0012\u0005\u0012\u018b\b\u0012"+
		"\n\u0012\f\u0012\u018e\t\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0003"+
		"\u0013\u0193\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u0198"+
		"\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u019c\b\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0005\u0015\u01a7\b\u0015\n\u0015\f\u0015\u01aa"+
		"\t\u0015\u0001\u0016\u0001\u0016\u0003\u0016\u01ae\b\u0016\u0001\u0017"+
		"\u0005\u0017\u01b1\b\u0017\n\u0017\f\u0017\u01b4\t\u0017\u0001\u0018\u0003"+
		"\u0018\u01b7\b\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01bb\b\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01c1\b\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01c6\b\u0018\u0003\u0018"+
		"\u01c8\b\u0018\u0001\u0018\u0003\u0018\u01cb\b\u0018\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u01cf\b\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01d3"+
		"\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01d8\b\u0018"+
		"\u0003\u0018\u01da\b\u0018\u0003\u0018\u01dc\b\u0018\u0001\u0019\u0003"+
		"\u0019\u01df\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0005"+
		"\u0019\u01e5\b\u0019\n\u0019\f\u0019\u01e8\t\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u001a\u0003\u001a\u01ed\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0005\u001a\u01f3\b\u001a\n\u001a\f\u001a\u01f6\t\u001a\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0003\u001b\u0205\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001d\u0001\u001d\u0005\u001d\u020c\b\u001d\n\u001d\f\u001d\u020f\t\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u021a\b\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0221\b\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u022c\b\u001f\u0001 "+
		"\u0001 \u0001 \u0001 \u0001 \u0003 \u0233\b \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0003 \u023b\b \u0001!\u0001!\u0001!\u0004!\u0240\b!\u000b"+
		"!\f!\u0241\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0004!\u024a\b!\u000b"+
		"!\f!\u024b\u0001!\u0001!\u0003!\u0250\b!\u0001\"\u0001\"\u0001\"\u0003"+
		"\"\u0255\b\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0003"+
		"#\u025f\b#\u0001$\u0001$\u0001$\u0005$\u0264\b$\n$\f$\u0267\t$\u0001$"+
		"\u0003$\u026a\b$\u0001$\u0001$\u0001$\u0001$\u0001$\u0003$\u0271\b$\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0005\'\u0281\b\'\n\'\f\'\u0284\t\'\u0001\'"+
		"\u0003\'\u0287\b\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0003(\u0291\b(\u0001)\u0001)\u0001*\u0001*\u0001*\u0001*\u0005*\u0299"+
		"\b*\n*\f*\u029c\t*\u0001+\u0001+\u0001+\u0003+\u02a1\b+\u0001,\u0001,"+
		"\u0003,\u02a5\b,\u0001-\u0004-\u02a8\b-\u000b-\f-\u02a9\u0001.\u0001."+
		"\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0005.\u02b4\b.\n.\f.\u02b7"+
		"\t.\u0001.\u0001.\u0003.\u02bb\b.\u0001/\u0001/\u0001/\u0001/\u0003/\u02c1"+
		"\b/\u00010\u00010\u00011\u00011\u00011\u00031\u02c8\b1\u00011\u00011\u0001"+
		"1\u00011\u00011\u00011\u00051\u02d0\b1\n1\f1\u02d3\t1\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00032\u02e2\b2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00042\u02ec\b2\u000b2\f2\u02ed\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0003"+
		"2\u02ff\b2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00032\u0329\b2\u00012\u00012\u00012\u00012\u00012\u00012\u0003"+
		"2\u0331\b2\u00012\u00012\u00032\u0335\b2\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00032\u0340\b2\u00012\u00012\u00032\u0344"+
		"\b2\u00012\u00012\u00012\u00032\u0349\b2\u00012\u00012\u00052\u034d\b"+
		"2\n2\f2\u0350\t2\u00013\u00013\u00013\u00014\u00014\u00014\u00014\u0001"+
		"4\u00014\u00014\u00014\u00014\u00014\u00014\u00014\u00014\u00014\u0003"+
		"4\u0363\b4\u00014\u00014\u00034\u0367\b4\u00014\u00054\u036a\b4\n4\f4"+
		"\u036d\t4\u00015\u00015\u00035\u0371\b5\u00016\u00016\u00016\u00036\u0376"+
		"\b6\u00017\u00017\u00017\u00057\u037b\b7\n7\f7\u037e\t7\u00017\u00017"+
		"\u00017\u00017\u00017\u00017\u00017\u00037\u0387\b7\u00018\u00018\u0001"+
		"8\u00058\u038c\b8\n8\f8\u038f\t8\u00019\u00019\u00019\u00059\u0394\b9"+
		"\n9\f9\u0397\t9\u0001:\u0001:\u0001:\u0001:\u0003:\u039d\b:\u0001;\u0001"+
		";\u0001;\u0001<\u0001<\u0001<\u0005<\u03a5\b<\n<\f<\u03a8\t<\u0001<\u0001"+
		"<\u0001<\u0001<\u0005<\u03ae\b<\n<\f<\u03b1\t<\u0003<\u03b3\b<\u0001<"+
		"\u0001<\u0001<\u0001<\u0005<\u03b9\b<\n<\f<\u03bc\t<\u0003<\u03be\b<\u0001"+
		"=\u0003=\u03c1\b=\u0001=\u0001=\u0001=\u0003=\u03c6\b=\u0001=\u0001=\u0003"+
		"=\u03ca\b=\u0001>\u0001>\u0001>\u0001>\u0003>\u03d0\b>\u0001>\u0001>\u0001"+
		">\u0004>\u03d5\b>\u000b>\f>\u03d6\u0005>\u03d9\b>\n>\f>\u03dc\t>\u0001"+
		"?\u0001?\u0001?\u0003?\u03e1\b?\u0001?\u0001?\u0001?\u0004?\u03e6\b?\u000b"+
		"?\f?\u03e7\u0005?\u03ea\b?\n?\f?\u03ed\t?\u0001@\u0001@\u0001A\u0001A"+
		"\u0003A\u03f3\bA\u0001B\u0001B\u0001B\u0001B\u0005B\u03f9\bB\nB\fB\u03fc"+
		"\tB\u0001B\u0001B\u0001C\u0001C\u0001C\u0001C\u0003C\u0404\bC\u0001D\u0001"+
		"D\u0001D\u0001D\u0001D\u0001D\u0005D\u040c\bD\nD\fD\u040f\tD\u0001D\u0001"+
		"D\u0003D\u0413\bD\u0001E\u0001E\u0003E\u0417\bE\u0001F\u0001F\u0001F\u0001"+
		"F\u0001F\u0001F\u0005F\u041f\bF\nF\fF\u0422\tF\u0001F\u0000\u0005bdh|"+
		"~G\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082"+
		"\u0084\u0086\u0088\u008a\u008c\u0000\n\u0002\u0000bbhh\u0002\u0000226"+
		"6\u0002\u0000\'(ii\u0002\u0000\u000b\u000bEE\u0002\u0000\u000e\u000ed"+
		"d\u0002\u0000\r\r\u0010\u0010\u0001\u0000\u0011\u0014\u0001\u0000\u0015"+
		"\u0016\u0001\u0000\u001c$\u0002\u0000%&U^\u0497\u0000\u008e\u0001\u0000"+
		"\u0000\u0000\u0002\u009b\u0001\u0000\u0000\u0000\u0004\u009e\u0001\u0000"+
		"\u0000\u0000\u0006\u00aa\u0001\u0000\u0000\u0000\b\u00b2\u0001\u0000\u0000"+
		"\u0000\n\u00b4\u0001\u0000\u0000\u0000\f\u00b7\u0001\u0000\u0000\u0000"+
		"\u000e\u00ba\u0001\u0000\u0000\u0000\u0010\u00eb\u0001\u0000\u0000\u0000"+
		"\u0012\u00ed\u0001\u0000\u0000\u0000\u0014\u00f9\u0001\u0000\u0000\u0000"+
		"\u0016\u00fb\u0001\u0000\u0000\u0000\u0018\u011d\u0001\u0000\u0000\u0000"+
		"\u001a\u011f\u0001\u0000\u0000\u0000\u001c\u0141\u0001\u0000\u0000\u0000"+
		"\u001e\u0143\u0001\u0000\u0000\u0000 \u015a\u0001\u0000\u0000\u0000\""+
		"\u0174\u0001\u0000\u0000\u0000$\u0177\u0001\u0000\u0000\u0000&\u0192\u0001"+
		"\u0000\u0000\u0000(\u01a1\u0001\u0000\u0000\u0000*\u01a3\u0001\u0000\u0000"+
		"\u0000,\u01ab\u0001\u0000\u0000\u0000.\u01b2\u0001\u0000\u0000\u00000"+
		"\u01db\u0001\u0000\u0000\u00002\u01de\u0001\u0000\u0000\u00004\u01ec\u0001"+
		"\u0000\u0000\u00006\u0204\u0001\u0000\u0000\u00008\u0206\u0001\u0000\u0000"+
		"\u0000:\u0209\u0001\u0000\u0000\u0000<\u0212\u0001\u0000\u0000\u0000>"+
		"\u022b\u0001\u0000\u0000\u0000@\u023a\u0001\u0000\u0000\u0000B\u024f\u0001"+
		"\u0000\u0000\u0000D\u0254\u0001\u0000\u0000\u0000F\u025e\u0001\u0000\u0000"+
		"\u0000H\u0270\u0001\u0000\u0000\u0000J\u0272\u0001\u0000\u0000\u0000L"+
		"\u0279\u0001\u0000\u0000\u0000N\u0286\u0001\u0000\u0000\u0000P\u0290\u0001"+
		"\u0000\u0000\u0000R\u0292\u0001\u0000\u0000\u0000T\u0294\u0001\u0000\u0000"+
		"\u0000V\u029d\u0001\u0000\u0000\u0000X\u02a4\u0001\u0000\u0000\u0000Z"+
		"\u02a7\u0001\u0000\u0000\u0000\\\u02ba\u0001\u0000\u0000\u0000^\u02c0"+
		"\u0001\u0000\u0000\u0000`\u02c2\u0001\u0000\u0000\u0000b\u02c7\u0001\u0000"+
		"\u0000\u0000d\u02fe\u0001\u0000\u0000\u0000f\u0351\u0001\u0000\u0000\u0000"+
		"h\u0354\u0001\u0000\u0000\u0000j\u0370\u0001\u0000\u0000\u0000l\u0375"+
		"\u0001\u0000\u0000\u0000n\u0386\u0001\u0000\u0000\u0000p\u0388\u0001\u0000"+
		"\u0000\u0000r\u0390\u0001\u0000\u0000\u0000t\u039c\u0001\u0000\u0000\u0000"+
		"v\u039e\u0001\u0000\u0000\u0000x\u03bd\u0001\u0000\u0000\u0000z\u03c0"+
		"\u0001\u0000\u0000\u0000|\u03cf\u0001\u0000\u0000\u0000~\u03e0\u0001\u0000"+
		"\u0000\u0000\u0080\u03ee\u0001\u0000\u0000\u0000\u0082\u03f0\u0001\u0000"+
		"\u0000\u0000\u0084\u03f4\u0001\u0000\u0000\u0000\u0086\u0403\u0001\u0000"+
		"\u0000\u0000\u0088\u0412\u0001\u0000\u0000\u0000\u008a\u0414\u0001\u0000"+
		"\u0000\u0000\u008c\u0418\u0001\u0000\u0000\u0000\u008e\u0092\u0003\u0002"+
		"\u0001\u0000\u008f\u0091\u0003\u0004\u0002\u0000\u0090\u008f\u0001\u0000"+
		"\u0000\u0000\u0091\u0094\u0001\u0000\u0000\u0000\u0092\u0090\u0001\u0000"+
		"\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0098\u0001\u0000"+
		"\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0095\u0097\u0003\u0006"+
		"\u0003\u0000\u0096\u0095\u0001\u0000\u0000\u0000\u0097\u009a\u0001\u0000"+
		"\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000"+
		"\u0000\u0000\u0099\u0001\u0001\u0000\u0000\u0000\u009a\u0098\u0001\u0000"+
		"\u0000\u0000\u009b\u009c\u0005C\u0000\u0000\u009c\u009d\u0003b1\u0000"+
		"\u009d\u0003\u0001\u0000\u0000\u0000\u009e\u00a0\u0003\n\u0005\u0000\u009f"+
		"\u00a1\u0003\f\u0006\u0000\u00a0\u009f\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a1\u0005\u0001\u0000\u0000\u0000\u00a2\u00ab"+
		"\u0003\u000e\u0007\u0000\u00a3\u00ab\u00032\u0019\u0000\u00a4\u00ab\u0003"+
		"\u0010\b\u0000\u00a5\u00ab\u0003$\u0012\u0000\u00a6\u00ab\u0003&\u0013"+
		"\u0000\u00a7\u00ab\u0003T*\u0000\u00a8\u00ab\u0003\u0016\u000b\u0000\u00a9"+
		"\u00ab\u0003\u001e\u000f\u0000\u00aa\u00a2\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a3\u0001\u0000\u0000\u0000\u00aa\u00a4\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a5\u0001\u0000\u0000\u0000\u00aa\u00a6\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a7\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a9\u0001\u0000\u0000\u0000\u00ab\u0007\u0001\u0000\u0000\u0000\u00ac"+
		"\u00b3\u0003\u000e\u0007\u0000\u00ad\u00b3\u00032\u0019\u0000\u00ae\u00b3"+
		"\u0003\u0010\b\u0000\u00af\u00b3\u0003$\u0012\u0000\u00b0\u00b3\u0003"+
		"&\u0013\u0000\u00b1\u00b3\u0003T*\u0000\u00b2\u00ac\u0001\u0000\u0000"+
		"\u0000\u00b2\u00ad\u0001\u0000\u0000\u0000\u00b2\u00ae\u0001\u0000\u0000"+
		"\u0000\u00b2\u00af\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b3\t\u0001\u0000\u0000\u0000"+
		"\u00b4\u00b5\u0005P\u0000\u0000\u00b5\u00b6\u0003b1\u0000\u00b6\u000b"+
		"\u0001\u0000\u0000\u0000\u00b7\u00b8\u00050\u0000\u0000\u00b8\u00b9\u0005"+
		"h\u0000\u0000\u00b9\r\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005_\u0000"+
		"\u0000\u00bb\u000f\u0001\u0000\u0000\u0000\u00bc\u00be\u0003Z-\u0000\u00bd"+
		"\u00bc\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000\u00be"+
		"\u00c0\u0001\u0000\u0000\u0000\u00bf\u00c1\u0005K\u0000\u0000\u00c0\u00bf"+
		"\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c2\u00c4\u0005h\u0000\u0000\u00c3\u00c5\u0003"+
		"\u0084B\u0000\u00c4\u00c3\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6\u00c7\u0005\u0001"+
		"\u0000\u0000\u00c7\u00ca\u0003v;\u0000\u00c8\u00c9\u0005\u0002\u0000\u0000"+
		"\u00c9\u00cb\u0003x<\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cb"+
		"\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc\u00d1"+
		"\u0005\u0003\u0000\u0000\u00cd\u00cf\u0005\u0004\u0000\u0000\u00ce\u00d0"+
		"\u0003\"\u0011\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d0\u00d2\u0001\u0000\u0000\u0000\u00d1\u00cd\u0001"+
		"\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001"+
		"\u0000\u0000\u0000\u00d3\u00d4\u0003\u0012\t\u0000\u00d4\u00ec\u0001\u0000"+
		"\u0000\u0000\u00d5\u00d7\u0003Z-\u0000\u00d6\u00d5\u0001\u0000\u0000\u0000"+
		"\u00d6\u00d7\u0001\u0000\u0000\u0000\u00d7\u00d9\u0001\u0000\u0000\u0000"+
		"\u00d8\u00da\u0005K\u0000\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00d9"+
		"\u00da\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db"+
		"\u00dd\u0005h\u0000\u0000\u00dc\u00de\u0003\u0084B\u0000\u00dd\u00dc\u0001"+
		"\u0000\u0000\u0000\u00dd\u00de\u0001\u0000\u0000\u0000\u00de\u00df\u0001"+
		"\u0000\u0000\u0000\u00df\u00e1\u0005\u0001\u0000\u0000\u00e0\u00e2\u0003"+
		"x<\u0000\u00e1\u00e0\u0001\u0000\u0000\u0000\u00e1\u00e2\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e8\u0005\u0003\u0000"+
		"\u0000\u00e4\u00e6\u0005\u0004\u0000\u0000\u00e5\u00e7\u0003\"\u0011\u0000"+
		"\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000\u0000"+
		"\u00e7\u00e9\u0001\u0000\u0000\u0000\u00e8\u00e4\u0001\u0000\u0000\u0000"+
		"\u00e8\u00e9\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000"+
		"\u00ea\u00ec\u0003\u0012\t\u0000\u00eb\u00bd\u0001\u0000\u0000\u0000\u00eb"+
		"\u00d6\u0001\u0000\u0000\u0000\u00ec\u0011\u0001\u0000\u0000\u0000\u00ed"+
		"\u00f1\u0005`\u0000\u0000\u00ee\u00f0\u0003\u0014\n\u0000\u00ef\u00ee"+
		"\u0001\u0000\u0000\u0000\u00f0\u00f3\u0001\u0000\u0000\u0000\u00f1\u00ef"+
		"\u0001\u0000\u0000\u0000\u00f1\u00f2\u0001\u0000\u0000\u0000\u00f2\u00f4"+
		"\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000\u00f4\u00f5"+
		"\u0005a\u0000\u0000\u00f5\u0013\u0001\u0000\u0000\u0000\u00f6\u00fa\u0003"+
		"6\u001b\u0000\u00f7\u00fa\u0003\u001a\r\u0000\u00f8\u00fa\u0003 \u0010"+
		"\u0000\u00f9\u00f6\u0001\u0000\u0000\u0000\u00f9\u00f7\u0001\u0000\u0000"+
		"\u0000\u00f9\u00f8\u0001\u0000\u0000\u0000\u00fa\u0015\u0001\u0000\u0000"+
		"\u0000\u00fb\u00fc\u0005c\u0000\u0000\u00fc\u00fd\u0005L\u0000\u0000\u00fd"+
		"\u00ff\u0005`\u0000\u0000\u00fe\u0100\u0003\u0018\f\u0000\u00ff\u00fe"+
		"\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u00ff"+
		"\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000\u0000\u0000\u0102\u0103"+
		"\u0001\u0000\u0000\u0000\u0103\u0104\u0005a\u0000\u0000\u0104\u0017\u0001"+
		"\u0000\u0000\u0000\u0105\u0106\u0005c\u0000\u0000\u0106\u0107\u00053\u0000"+
		"\u0000\u0107\u0108\u0003d2\u0000\u0108\u0109\u0005\u0005\u0000\u0000\u0109"+
		"\u010d\u0005`\u0000\u0000\u010a\u010c\u0003\b\u0004\u0000\u010b\u010a"+
		"\u0001\u0000\u0000\u0000\u010c\u010f\u0001\u0000\u0000\u0000\u010d\u010b"+
		"\u0001\u0000\u0000\u0000\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u0110"+
		"\u0001\u0000\u0000\u0000\u010f\u010d\u0001\u0000\u0000\u0000\u0110\u0111"+
		"\u0005a\u0000\u0000\u0111\u011e\u0001\u0000\u0000\u0000\u0112\u0113\u0005"+
		"c\u0000\u0000\u0113\u0114\u00057\u0000\u0000\u0114\u0115\u0005\u0005\u0000"+
		"\u0000\u0115\u0119\u0005`\u0000\u0000\u0116\u0118\u0003\b\u0004\u0000"+
		"\u0117\u0116\u0001\u0000\u0000\u0000\u0118\u011b\u0001\u0000\u0000\u0000"+
		"\u0119\u0117\u0001\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000"+
		"\u011a\u011c\u0001\u0000\u0000\u0000\u011b\u0119\u0001\u0000\u0000\u0000"+
		"\u011c\u011e\u0005a\u0000\u0000\u011d\u0105\u0001\u0000\u0000\u0000\u011d"+
		"\u0112\u0001\u0000\u0000\u0000\u011e\u0019\u0001\u0000\u0000\u0000\u011f"+
		"\u0120\u0005c\u0000\u0000\u0120\u0121\u0005L\u0000\u0000\u0121\u0123\u0005"+
		"`\u0000\u0000\u0122\u0124\u0003\u001c\u000e\u0000\u0123\u0122\u0001\u0000"+
		"\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000\u0125\u0123\u0001\u0000"+
		"\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126\u0127\u0001\u0000"+
		"\u0000\u0000\u0127\u0128\u0005a\u0000\u0000\u0128\u001b\u0001\u0000\u0000"+
		"\u0000\u0129\u012a\u0005c\u0000\u0000\u012a\u012b\u00053\u0000\u0000\u012b"+
		"\u012c\u0003d2\u0000\u012c\u012d\u0005\u0005\u0000\u0000\u012d\u0131\u0005"+
		"`\u0000\u0000\u012e\u0130\u00036\u001b\u0000\u012f\u012e\u0001\u0000\u0000"+
		"\u0000\u0130\u0133\u0001\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000"+
		"\u0000\u0131\u0132\u0001\u0000\u0000\u0000\u0132\u0134\u0001\u0000\u0000"+
		"\u0000\u0133\u0131\u0001\u0000\u0000\u0000\u0134\u0135\u0005a\u0000\u0000"+
		"\u0135\u0142\u0001\u0000\u0000\u0000\u0136\u0137\u0005c\u0000\u0000\u0137"+
		"\u0138\u00057\u0000\u0000\u0138\u0139\u0005\u0005\u0000\u0000\u0139\u013d"+
		"\u0005`\u0000\u0000\u013a\u013c\u00036\u001b\u0000\u013b\u013a\u0001\u0000"+
		"\u0000\u0000\u013c\u013f\u0001\u0000\u0000\u0000\u013d\u013b\u0001\u0000"+
		"\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u0140\u0001\u0000"+
		"\u0000\u0000\u013f\u013d\u0001\u0000\u0000\u0000\u0140\u0142\u0005a\u0000"+
		"\u0000\u0141\u0129\u0001\u0000\u0000\u0000\u0141\u0136\u0001\u0000\u0000"+
		"\u0000\u0142\u001d\u0001\u0000\u0000\u0000\u0143\u0144\u0005c\u0000\u0000"+
		"\u0144\u0145\u0005=\u0000\u0000\u0145\u0146\u0003d2\u0000\u0146\u014a"+
		"\u0005`\u0000\u0000\u0147\u0149\u0003\b\u0004\u0000\u0148\u0147\u0001"+
		"\u0000\u0000\u0000\u0149\u014c\u0001\u0000\u0000\u0000\u014a\u0148\u0001"+
		"\u0000\u0000\u0000\u014a\u014b\u0001\u0000\u0000\u0000\u014b\u014d\u0001"+
		"\u0000\u0000\u0000\u014c\u014a\u0001\u0000\u0000\u0000\u014d\u0158\u0005"+
		"a\u0000\u0000\u014e\u014f\u0005c\u0000\u0000\u014f\u0150\u00058\u0000"+
		"\u0000\u0150\u0154\u0005`\u0000\u0000\u0151\u0153\u0003\b\u0004\u0000"+
		"\u0152\u0151\u0001\u0000\u0000\u0000\u0153\u0156\u0001\u0000\u0000\u0000"+
		"\u0154\u0152\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000\u0000\u0000"+
		"\u0155\u0157\u0001\u0000\u0000\u0000\u0156\u0154\u0001\u0000\u0000\u0000"+
		"\u0157\u0159\u0005a\u0000\u0000\u0158\u014e\u0001\u0000\u0000\u0000\u0158"+
		"\u0159\u0001\u0000\u0000\u0000\u0159\u001f\u0001\u0000\u0000\u0000\u015a"+
		"\u015b\u0005c\u0000\u0000\u015b\u015c\u0005=\u0000\u0000\u015c\u015d\u0003"+
		"d2\u0000\u015d\u0161\u0005`\u0000\u0000\u015e\u0160\u00036\u001b\u0000"+
		"\u015f\u015e\u0001\u0000\u0000\u0000\u0160\u0163\u0001\u0000\u0000\u0000"+
		"\u0161\u015f\u0001\u0000\u0000\u0000\u0161\u0162\u0001\u0000\u0000\u0000"+
		"\u0162\u0164\u0001\u0000\u0000\u0000\u0163\u0161\u0001\u0000\u0000\u0000"+
		"\u0164\u016f\u0005a\u0000\u0000\u0165\u0166\u0005c\u0000\u0000\u0166\u0167"+
		"\u00058\u0000\u0000\u0167\u016b\u0005`\u0000\u0000\u0168\u016a\u00036"+
		"\u001b\u0000\u0169\u0168\u0001\u0000\u0000\u0000\u016a\u016d\u0001\u0000"+
		"\u0000\u0000\u016b\u0169\u0001\u0000\u0000\u0000\u016b\u016c\u0001\u0000"+
		"\u0000\u0000\u016c\u016e\u0001\u0000\u0000\u0000\u016d\u016b\u0001\u0000"+
		"\u0000\u0000\u016e\u0170\u0005a\u0000\u0000\u016f\u0165\u0001\u0000\u0000"+
		"\u0000\u016f\u0170\u0001\u0000\u0000\u0000\u0170!\u0001\u0000\u0000\u0000"+
		"\u0171\u0175\u0003\u008cF\u0000\u0172\u0175\u0003|>\u0000\u0173\u0175"+
		"\u0005R\u0000\u0000\u0174\u0171\u0001\u0000\u0000\u0000\u0174\u0172\u0001"+
		"\u0000\u0000\u0000\u0174\u0173\u0001\u0000\u0000\u0000\u0175#\u0001\u0000"+
		"\u0000\u0000\u0176\u0178\u0003Z-\u0000\u0177\u0176\u0001\u0000\u0000\u0000"+
		"\u0177\u0178\u0001\u0000\u0000\u0000\u0178\u0179\u0001\u0000\u0000\u0000"+
		"\u0179\u017a\u00055\u0000\u0000\u017a\u017c\u0003(\u0014\u0000\u017b\u017d"+
		"\u0003\u0084B\u0000\u017c\u017b\u0001\u0000\u0000\u0000\u017c\u017d\u0001"+
		"\u0000\u0000\u0000\u017d\u0180\u0001\u0000\u0000\u0000\u017e\u017f\u0005"+
		">\u0000\u0000\u017f\u0181\u0003*\u0015\u0000\u0180\u017e\u0001\u0000\u0000"+
		"\u0000\u0180\u0181\u0001\u0000\u0000\u0000\u0181\u0182\u0001\u0000\u0000"+
		"\u0000\u0182\u0186\u0005`\u0000\u0000\u0183\u0185\u00034\u001a\u0000\u0184"+
		"\u0183\u0001\u0000\u0000\u0000\u0185\u0188\u0001\u0000\u0000\u0000\u0186"+
		"\u0184\u0001\u0000\u0000\u0000\u0186\u0187\u0001\u0000\u0000\u0000\u0187"+
		"\u018c\u0001\u0000\u0000\u0000\u0188\u0186\u0001\u0000\u0000\u0000\u0189"+
		"\u018b\u0003\u0010\b\u0000\u018a\u0189\u0001\u0000\u0000\u0000\u018b\u018e"+
		"\u0001\u0000\u0000\u0000\u018c\u018a\u0001\u0000\u0000\u0000\u018c\u018d"+
		"\u0001\u0000\u0000\u0000\u018d\u018f\u0001\u0000\u0000\u0000\u018e\u018c"+
		"\u0001\u0000\u0000\u0000\u018f\u0190\u0005a\u0000\u0000\u0190%\u0001\u0000"+
		"\u0000\u0000\u0191\u0193\u0003Z-\u0000\u0192\u0191\u0001\u0000\u0000\u0000"+
		"\u0192\u0193\u0001\u0000\u0000\u0000\u0193\u0194\u0001\u0000\u0000\u0000"+
		"\u0194\u0195\u0005A\u0000\u0000\u0195\u0197\u0003(\u0014\u0000\u0196\u0198"+
		"\u0003\u0084B\u0000\u0197\u0196\u0001\u0000\u0000\u0000\u0197\u0198\u0001"+
		"\u0000\u0000\u0000\u0198\u019b\u0001\u0000\u0000\u0000\u0199\u019a\u0005"+
		"9\u0000\u0000\u019a\u019c\u0003*\u0015\u0000\u019b\u0199\u0001\u0000\u0000"+
		"\u0000\u019b\u019c\u0001\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000"+
		"\u0000\u019d\u019e\u0005`\u0000\u0000\u019e\u019f\u0003.\u0017\u0000\u019f"+
		"\u01a0\u0005a\u0000\u0000\u01a0\'\u0001\u0000\u0000\u0000\u01a1\u01a2"+
		"\u0007\u0000\u0000\u0000\u01a2)\u0001\u0000\u0000\u0000\u01a3\u01a8\u0003"+
		",\u0016\u0000\u01a4\u01a5\u0005\u0002\u0000\u0000\u01a5\u01a7\u0003,\u0016"+
		"\u0000\u01a6\u01a4\u0001\u0000\u0000\u0000\u01a7\u01aa\u0001\u0000\u0000"+
		"\u0000\u01a8\u01a6\u0001\u0000\u0000\u0000\u01a8\u01a9\u0001\u0000\u0000"+
		"\u0000\u01a9+\u0001\u0000\u0000\u0000\u01aa\u01a8\u0001\u0000\u0000\u0000"+
		"\u01ab\u01ad\u0003b1\u0000\u01ac\u01ae\u0003\u0088D\u0000\u01ad\u01ac"+
		"\u0001\u0000\u0000\u0000\u01ad\u01ae\u0001\u0000\u0000\u0000\u01ae-\u0001"+
		"\u0000\u0000\u0000\u01af\u01b1\u00030\u0018\u0000\u01b0\u01af\u0001\u0000"+
		"\u0000\u0000\u01b1\u01b4\u0001\u0000\u0000\u0000\u01b2\u01b0\u0001\u0000"+
		"\u0000\u0000\u01b2\u01b3\u0001\u0000\u0000\u0000\u01b3/\u0001\u0000\u0000"+
		"\u0000\u01b4\u01b2\u0001\u0000\u0000\u0000\u01b5\u01b7\u0003Z-\u0000\u01b6"+
		"\u01b5\u0001\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7"+
		"\u01b8\u0001\u0000\u0000\u0000\u01b8\u01ba\u0005h\u0000\u0000\u01b9\u01bb"+
		"\u0003\u0084B\u0000\u01ba\u01b9\u0001\u0000\u0000\u0000\u01ba\u01bb\u0001"+
		"\u0000\u0000\u0000\u01bb\u01bc\u0001\u0000\u0000\u0000\u01bc\u01bd\u0005"+
		"\u0001\u0000\u0000\u01bd\u01c0\u0003v;\u0000\u01be\u01bf\u0005\u0002\u0000"+
		"\u0000\u01bf\u01c1\u0003x<\u0000\u01c0\u01be\u0001\u0000\u0000\u0000\u01c0"+
		"\u01c1\u0001\u0000\u0000\u0000\u01c1\u01c2\u0001\u0000\u0000\u0000\u01c2"+
		"\u01c7\u0005\u0003\u0000\u0000\u01c3\u01c5\u0005\u0004\u0000\u0000\u01c4"+
		"\u01c6\u0003\"\u0011\u0000\u01c5\u01c4\u0001\u0000\u0000\u0000\u01c5\u01c6"+
		"\u0001\u0000\u0000\u0000\u01c6\u01c8\u0001\u0000\u0000\u0000\u01c7\u01c3"+
		"\u0001\u0000\u0000\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8\u01dc"+
		"\u0001\u0000\u0000\u0000\u01c9\u01cb\u0003Z-\u0000\u01ca\u01c9\u0001\u0000"+
		"\u0000\u0000\u01ca\u01cb\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001\u0000"+
		"\u0000\u0000\u01cc\u01ce\u0005h\u0000\u0000\u01cd\u01cf\u0003\u0084B\u0000"+
		"\u01ce\u01cd\u0001\u0000\u0000\u0000\u01ce\u01cf\u0001\u0000\u0000\u0000"+
		"\u01cf\u01d0\u0001\u0000\u0000\u0000\u01d0\u01d2\u0005\u0001\u0000\u0000"+
		"\u01d1\u01d3\u0003x<\u0000\u01d2\u01d1\u0001\u0000\u0000\u0000\u01d2\u01d3"+
		"\u0001\u0000\u0000\u0000\u01d3\u01d4\u0001\u0000\u0000\u0000\u01d4\u01d9"+
		"\u0005\u0003\u0000\u0000\u01d5\u01d7\u0005\u0004\u0000\u0000\u01d6\u01d8"+
		"\u0003\"\u0011\u0000\u01d7\u01d6\u0001\u0000\u0000\u0000\u01d7\u01d8\u0001"+
		"\u0000\u0000\u0000\u01d8\u01da\u0001\u0000\u0000\u0000\u01d9\u01d5\u0001"+
		"\u0000\u0000\u0000\u01d9\u01da\u0001\u0000\u0000\u0000\u01da\u01dc\u0001"+
		"\u0000\u0000\u0000\u01db\u01b6\u0001\u0000\u0000\u0000\u01db\u01ca\u0001"+
		"\u0000\u0000\u0000\u01dc1\u0001\u0000\u0000\u0000\u01dd\u01df\u0003Z-"+
		"\u0000\u01de\u01dd\u0001\u0000\u0000\u0000\u01de\u01df\u0001\u0000\u0000"+
		"\u0000\u01df\u01e0\u0001\u0000\u0000\u0000\u01e0\u01e1\u0005M\u0000\u0000"+
		"\u01e1\u01e2\u0005h\u0000\u0000\u01e2\u01e6\u0005`\u0000\u0000\u01e3\u01e5"+
		"\u00034\u001a\u0000\u01e4\u01e3\u0001\u0000\u0000\u0000\u01e5\u01e8\u0001"+
		"\u0000\u0000\u0000\u01e6\u01e4\u0001\u0000\u0000\u0000\u01e6\u01e7\u0001"+
		"\u0000\u0000\u0000\u01e7\u01e9\u0001\u0000\u0000\u0000\u01e8\u01e6\u0001"+
		"\u0000\u0000\u0000\u01e9\u01ea\u0005a\u0000\u0000\u01ea3\u0001\u0000\u0000"+
		"\u0000\u01eb\u01ed\u0003Z-\u0000\u01ec\u01eb\u0001\u0000\u0000\u0000\u01ec"+
		"\u01ed\u0001\u0000\u0000\u0000\u01ed\u01ee\u0001\u0000\u0000\u0000\u01ee"+
		"\u01ef\u0003|>\u0000\u01ef\u01f4\u0005h\u0000\u0000\u01f0\u01f1\u0005"+
		"\u0002\u0000\u0000\u01f1\u01f3\u0005h\u0000\u0000\u01f2\u01f0\u0001\u0000"+
		"\u0000\u0000\u01f3\u01f6\u0001\u0000\u0000\u0000\u01f4\u01f2\u0001\u0000"+
		"\u0000\u0000\u01f4\u01f5\u0001\u0000\u0000\u0000\u01f55\u0001\u0000\u0000"+
		"\u0000\u01f6\u01f4\u0001\u0000\u0000\u0000\u01f7\u0205\u0003\u0010\b\u0000"+
		"\u01f8\u0205\u0003T*\u0000\u01f9\u0205\u0003N\'\u0000\u01fa\u0205\u0003"+
		"R)\u0000\u01fb\u0205\u0003\u000e\u0007\u0000\u01fc\u0205\u0003<\u001e"+
		"\u0000\u01fd\u0205\u0003>\u001f\u0000\u01fe\u0205\u0003@ \u0000\u01ff"+
		"\u0205\u0003B!\u0000\u0200\u0205\u0003H$\u0000\u0201\u0205\u00038\u001c"+
		"\u0000\u0202\u0205\u0003d2\u0000\u0203\u0205\u0003n7\u0000\u0204\u01f7"+
		"\u0001\u0000\u0000\u0000\u0204\u01f8\u0001\u0000\u0000\u0000\u0204\u01f9"+
		"\u0001\u0000\u0000\u0000\u0204\u01fa\u0001\u0000\u0000\u0000\u0204\u01fb"+
		"\u0001\u0000\u0000\u0000\u0204\u01fc\u0001\u0000\u0000\u0000\u0204\u01fd"+
		"\u0001\u0000\u0000\u0000\u0204\u01fe\u0001\u0000\u0000\u0000\u0204\u01ff"+
		"\u0001\u0000\u0000\u0000\u0204\u0200\u0001\u0000\u0000\u0000\u0204\u0201"+
		"\u0001\u0000\u0000\u0000\u0204\u0202\u0001\u0000\u0000\u0000\u0204\u0203"+
		"\u0001\u0000\u0000\u0000\u02057\u0001\u0000\u0000\u0000\u0206\u0207\u0005"+
		"N\u0000\u0000\u0207\u0208\u0003d2\u0000\u02089\u0001\u0000\u0000\u0000"+
		"\u0209\u020d\u0005`\u0000\u0000\u020a\u020c\u00036\u001b\u0000\u020b\u020a"+
		"\u0001\u0000\u0000\u0000\u020c\u020f\u0001\u0000\u0000\u0000\u020d\u020b"+
		"\u0001\u0000\u0000\u0000\u020d\u020e\u0001\u0000\u0000\u0000\u020e\u0210"+
		"\u0001\u0000\u0000\u0000\u020f\u020d\u0001\u0000\u0000\u0000\u0210\u0211"+
		"\u0005a\u0000\u0000\u0211;\u0001\u0000\u0000\u0000\u0212\u0213\u0005S"+
		"\u0000\u0000\u0213\u0214\u0003d2\u0000\u0214\u0215\u0003:\u001d\u0000"+
		"\u0215=\u0001\u0000\u0000\u0000\u0216\u0219\u0005;\u0000\u0000\u0217\u021a"+
		"\u0003n7\u0000\u0218\u021a\u0003T*\u0000\u0219\u0217\u0001\u0000\u0000"+
		"\u0000\u0219\u0218\u0001\u0000\u0000\u0000\u021a\u021b\u0001\u0000\u0000"+
		"\u0000\u021b\u021c\u0005\u0006\u0000\u0000\u021c\u021d\u0003d2\u0000\u021d"+
		"\u0220\u0005\u0006\u0000\u0000\u021e\u0221\u0003d2\u0000\u021f\u0221\u0003"+
		"n7\u0000\u0220\u021e\u0001\u0000\u0000\u0000\u0220\u021f\u0001\u0000\u0000"+
		"\u0000\u0221\u0222\u0001\u0000\u0000\u0000\u0222\u0223\u0003:\u001d\u0000"+
		"\u0223\u022c\u0001\u0000\u0000\u0000\u0224\u0225\u0005;\u0000\u0000\u0225"+
		"\u0226\u0003|>\u0000\u0226\u0227\u0005h\u0000\u0000\u0227\u0228\u0005"+
		"?\u0000\u0000\u0228\u0229\u0003d2\u0000\u0229\u022a\u0003:\u001d\u0000"+
		"\u022a\u022c\u0001\u0000\u0000\u0000\u022b\u0216\u0001\u0000\u0000\u0000"+
		"\u022b\u0224\u0001\u0000\u0000\u0000\u022c?\u0001\u0000\u0000\u0000\u022d"+
		"\u022e\u0005<\u0000\u0000\u022e\u0232\u0003d2\u0000\u022f\u0233\u0003"+
		":\u001d\u0000\u0230\u0233\u0003N\'\u0000\u0231\u0233\u0003R)\u0000\u0232"+
		"\u022f\u0001\u0000\u0000\u0000\u0232\u0230\u0001\u0000\u0000\u0000\u0232"+
		"\u0231\u0001\u0000\u0000\u0000\u0233\u023b\u0001\u0000\u0000\u0000\u0234"+
		"\u0235\u0005<\u0000\u0000\u0235\u0236\u0003d2\u0000\u0236\u0237\u0003"+
		":\u001d\u0000\u0237\u0238\u00058\u0000\u0000\u0238\u0239\u0003:\u001d"+
		"\u0000\u0239\u023b\u0001\u0000\u0000\u0000\u023a\u022d\u0001\u0000\u0000"+
		"\u0000\u023a\u0234\u0001\u0000\u0000\u0000\u023bA\u0001\u0000\u0000\u0000"+
		"\u023c\u023d\u0005L\u0000\u0000\u023d\u023f\u0005`\u0000\u0000\u023e\u0240"+
		"\u0003F#\u0000\u023f\u023e\u0001\u0000\u0000\u0000\u0240\u0241\u0001\u0000"+
		"\u0000\u0000\u0241\u023f\u0001\u0000\u0000\u0000\u0241\u0242\u0001\u0000"+
		"\u0000\u0000\u0242\u0243\u0001\u0000\u0000\u0000\u0243\u0244\u0005a\u0000"+
		"\u0000\u0244\u0250\u0001\u0000\u0000\u0000\u0245\u0246\u0005L\u0000\u0000"+
		"\u0246\u0247\u0003d2\u0000\u0247\u0249\u0005`\u0000\u0000\u0248\u024a"+
		"\u0003F#\u0000\u0249\u0248\u0001\u0000\u0000\u0000\u024a\u024b\u0001\u0000"+
		"\u0000\u0000\u024b\u0249\u0001\u0000\u0000\u0000\u024b\u024c\u0001\u0000"+
		"\u0000\u0000\u024c\u024d\u0001\u0000\u0000\u0000\u024d\u024e\u0005a\u0000"+
		"\u0000\u024e\u0250\u0001\u0000\u0000\u0000\u024f\u023c\u0001\u0000\u0000"+
		"\u0000\u024f\u0245\u0001\u0000\u0000\u0000\u0250C\u0001\u0000\u0000\u0000"+
		"\u0251\u0255\u0003:\u001d\u0000\u0252\u0255\u0003N\'\u0000\u0253\u0255"+
		"\u0003R)\u0000\u0254\u0251\u0001\u0000\u0000\u0000\u0254\u0252\u0001\u0000"+
		"\u0000\u0000\u0254\u0253\u0001\u0000\u0000\u0000\u0255E\u0001\u0000\u0000"+
		"\u0000\u0256\u0257\u00053\u0000\u0000\u0257\u0258\u0003d2\u0000\u0258"+
		"\u0259\u0005\u0005\u0000\u0000\u0259\u025a\u0003D\"\u0000\u025a\u025f"+
		"\u0001\u0000\u0000\u0000\u025b\u025c\u00057\u0000\u0000\u025c\u025d\u0005"+
		"\u0005\u0000\u0000\u025d\u025f\u0003D\"\u0000\u025e\u0256\u0001\u0000"+
		"\u0000\u0000\u025e\u025b\u0001\u0000\u0000\u0000\u025fG\u0001\u0000\u0000"+
		"\u0000\u0260\u0261\u0005O\u0000\u0000\u0261\u0265\u0003:\u001d\u0000\u0262"+
		"\u0264\u0003J%\u0000\u0263\u0262\u0001\u0000\u0000\u0000\u0264\u0267\u0001"+
		"\u0000\u0000\u0000\u0265\u0263\u0001\u0000\u0000\u0000\u0265\u0266\u0001"+
		"\u0000\u0000\u0000\u0266\u0269\u0001\u0000\u0000\u0000\u0267\u0265\u0001"+
		"\u0000\u0000\u0000\u0268\u026a\u0003L&\u0000\u0269\u0268\u0001\u0000\u0000"+
		"\u0000\u0269\u026a\u0001\u0000\u0000\u0000\u026a\u0271\u0001\u0000\u0000"+
		"\u0000\u026b\u026c\u0005O\u0000\u0000\u026c\u026d\u0003:\u001d\u0000\u026d"+
		"\u026e\u0005:\u0000\u0000\u026e\u026f\u0003:\u001d\u0000\u026f\u0271\u0001"+
		"\u0000\u0000\u0000\u0270\u0260\u0001\u0000\u0000\u0000\u0270\u026b\u0001"+
		"\u0000\u0000\u0000\u0271I\u0001\u0000\u0000\u0000\u0272\u0273\u00054\u0000"+
		"\u0000\u0273\u0274\u0005\u0001\u0000\u0000\u0274\u0275\u0003\u0082A\u0000"+
		"\u0275\u0276\u0005h\u0000\u0000\u0276\u0277\u0005\u0003\u0000\u0000\u0277"+
		"\u0278\u0003:\u001d\u0000\u0278K\u0001\u0000\u0000\u0000\u0279\u027a\u0005"+
		":\u0000\u0000\u027a\u027b\u0003:\u001d\u0000\u027bM\u0001\u0000\u0000"+
		"\u0000\u027c\u027d\u0005J\u0000\u0000\u027d\u0282\u0003d2\u0000\u027e"+
		"\u027f\u0005\u0002\u0000\u0000\u027f\u0281\u0003d2\u0000\u0280\u027e\u0001"+
		"\u0000\u0000\u0000\u0281\u0284\u0001\u0000\u0000\u0000\u0282\u0280\u0001"+
		"\u0000\u0000\u0000\u0282\u0283\u0001\u0000\u0000\u0000\u0283\u0287\u0001"+
		"\u0000\u0000\u0000\u0284\u0282\u0001\u0000\u0000\u0000\u0285\u0287\u0005"+
		"J\u0000\u0000\u0286\u027c\u0001\u0000\u0000\u0000\u0286\u0285\u0001\u0000"+
		"\u0000\u0000\u0287O\u0001\u0000\u0000\u0000\u0288\u0289\u0005\u0007\u0000"+
		"\u0000\u0289\u028a\u0003d2\u0000\u028a\u028b\u0003\u0010\b\u0000\u028b"+
		"\u0291\u0001\u0000\u0000\u0000\u028c\u028d\u0005\u0007\u0000\u0000\u028d"+
		"\u028e\u0003d2\u0000\u028e\u028f\u0003P(\u0000\u028f\u0291\u0001\u0000"+
		"\u0000\u0000\u0290\u0288\u0001\u0000\u0000\u0000\u0290\u028c\u0001\u0000"+
		"\u0000\u0000\u0291Q\u0001\u0000\u0000\u0000\u0292\u0293\u0007\u0001\u0000"+
		"\u0000\u0293S\u0001\u0000\u0000\u0000\u0294\u0295\u0003X,\u0000\u0295"+
		"\u029a\u0003V+\u0000\u0296\u0297\u0005\u0002\u0000\u0000\u0297\u0299\u0003"+
		"V+\u0000\u0298\u0296\u0001\u0000\u0000\u0000\u0299\u029c\u0001\u0000\u0000"+
		"\u0000\u029a\u0298\u0001\u0000\u0000\u0000\u029a\u029b\u0001\u0000\u0000"+
		"\u0000\u029bU\u0001\u0000\u0000\u0000\u029c\u029a\u0001\u0000\u0000\u0000"+
		"\u029d\u02a0\u0005h\u0000\u0000\u029e\u029f\u0005\b\u0000\u0000\u029f"+
		"\u02a1\u0003d2\u0000\u02a0\u029e\u0001\u0000\u0000\u0000\u02a0\u02a1\u0001"+
		"\u0000\u0000\u0000\u02a1W\u0001\u0000\u0000\u0000\u02a2\u02a5\u0005Q\u0000"+
		"\u0000\u02a3\u02a5\u0003|>\u0000\u02a4\u02a2\u0001\u0000\u0000\u0000\u02a4"+
		"\u02a3\u0001\u0000\u0000\u0000\u02a5Y\u0001\u0000\u0000\u0000\u02a6\u02a8"+
		"\u0003\\.\u0000\u02a7\u02a6\u0001\u0000\u0000\u0000\u02a8\u02a9\u0001"+
		"\u0000\u0000\u0000\u02a9\u02a7\u0001\u0000\u0000\u0000\u02a9\u02aa\u0001"+
		"\u0000\u0000\u0000\u02aa[\u0001\u0000\u0000\u0000\u02ab\u02ac\u0005c\u0000"+
		"\u0000\u02ac\u02bb\u0003b1\u0000\u02ad\u02ae\u0005c\u0000\u0000\u02ae"+
		"\u02af\u0003b1\u0000\u02af\u02b0\u0005\u0001\u0000\u0000\u02b0\u02b5\u0003"+
		"^/\u0000\u02b1\u02b2\u0005\u0002\u0000\u0000\u02b2\u02b4\u0003^/\u0000"+
		"\u02b3\u02b1\u0001\u0000\u0000\u0000\u02b4\u02b7\u0001\u0000\u0000\u0000"+
		"\u02b5\u02b3\u0001\u0000\u0000\u0000\u02b5\u02b6\u0001\u0000\u0000\u0000"+
		"\u02b6\u02b8\u0001\u0000\u0000\u0000\u02b7\u02b5\u0001\u0000\u0000\u0000"+
		"\u02b8\u02b9\u0005\u0003\u0000\u0000\u02b9\u02bb\u0001\u0000\u0000\u0000"+
		"\u02ba\u02ab\u0001\u0000\u0000\u0000\u02ba\u02ad\u0001\u0000\u0000\u0000"+
		"\u02bb]\u0001\u0000\u0000\u0000\u02bc\u02bd\u0005h\u0000\u0000\u02bd\u02be"+
		"\u0005\b\u0000\u0000\u02be\u02c1\u0003`0\u0000\u02bf\u02c1\u0003`0\u0000"+
		"\u02c0\u02bc\u0001\u0000\u0000\u0000\u02c0\u02bf\u0001\u0000\u0000\u0000"+
		"\u02c1_\u0001\u0000\u0000\u0000\u02c2\u02c3\u0007\u0002\u0000\u0000\u02c3"+
		"a\u0001\u0000\u0000\u0000\u02c4\u02c5\u00061\uffff\uffff\u0000\u02c5\u02c8"+
		"\u0005h\u0000\u0000\u02c6\u02c8\u0005b\u0000\u0000\u02c7\u02c4\u0001\u0000"+
		"\u0000\u0000\u02c7\u02c6\u0001\u0000\u0000\u0000\u02c8\u02d1\u0001\u0000"+
		"\u0000\u0000\u02c9\u02ca\n\u0002\u0000\u0000\u02ca\u02cb\u0005g\u0000"+
		"\u0000\u02cb\u02d0\u0005h\u0000\u0000\u02cc\u02cd\n\u0001\u0000\u0000"+
		"\u02cd\u02ce\u0005g\u0000\u0000\u02ce\u02d0\u0005b\u0000\u0000\u02cf\u02c9"+
		"\u0001\u0000\u0000\u0000\u02cf\u02cc\u0001\u0000\u0000\u0000\u02d0\u02d3"+
		"\u0001\u0000\u0000\u0000\u02d1\u02cf\u0001\u0000\u0000\u0000\u02d1\u02d2"+
		"\u0001\u0000\u0000\u0000\u02d2c\u0001\u0000\u0000\u0000\u02d3\u02d1\u0001"+
		"\u0000\u0000\u0000\u02d4\u02d5\u00062\uffff\uffff\u0000\u02d5\u02ff\u0005"+
		"i\u0000\u0000\u02d6\u02ff\u0005(\u0000\u0000\u02d7\u02ff\u0005)\u0000"+
		"\u0000\u02d8\u02ff\u0005\'\u0000\u0000\u02d9\u02ff\u0005h\u0000\u0000"+
		"\u02da\u02ff\u0005G\u0000\u0000\u02db\u02ff\u0005I\u0000\u0000\u02dc\u02ff"+
		"\u0005F\u0000\u0000\u02dd\u02de\u0005D\u0000\u0000\u02de\u02df\u0003~"+
		"?\u0000\u02df\u02e1\u0005\u0001\u0000\u0000\u02e0\u02e2\u0003r9\u0000"+
		"\u02e1\u02e0\u0001\u0000\u0000\u0000\u02e1\u02e2\u0001\u0000\u0000\u0000"+
		"\u02e2\u02e3\u0001\u0000\u0000\u0000\u02e3\u02e4\u0005\u0003\u0000\u0000"+
		"\u02e4\u02ff\u0001\u0000\u0000\u0000\u02e5\u02e6\u0005D\u0000\u0000\u02e6"+
		"\u02eb\u0003~?\u0000\u02e7\u02e8\u0005\t\u0000\u0000\u02e8\u02e9\u0003"+
		"d2\u0000\u02e9\u02ea\u0005\n\u0000\u0000\u02ea\u02ec\u0001\u0000\u0000"+
		"\u0000\u02eb\u02e7\u0001\u0000\u0000\u0000\u02ec\u02ed\u0001\u0000\u0000"+
		"\u0000\u02ed\u02eb\u0001\u0000\u0000\u0000\u02ed\u02ee\u0001\u0000\u0000"+
		"\u0000\u02ee\u02ff\u0001\u0000\u0000\u0000\u02ef\u02f0\u0005\u0001\u0000"+
		"\u0000\u02f0\u02f1\u0003|>\u0000\u02f1\u02f2\u0005\u0003\u0000\u0000\u02f2"+
		"\u02f3\u0003d2\u0012\u02f3\u02ff\u0001\u0000\u0000\u0000\u02f4\u02f5\u0005"+
		"\u0001\u0000\u0000\u02f5\u02f6\u0003d2\u0000\u02f6\u02f7\u0005\u0003\u0000"+
		"\u0000\u02f7\u02ff\u0001\u0000\u0000\u0000\u02f8\u02f9\u0007\u0003\u0000"+
		"\u0000\u02f9\u02ff\u0003d2\u000f\u02fa\u02fb\u0005\f\u0000\u0000\u02fb"+
		"\u02ff\u0003d2\u000e\u02fc\u02fd\u0005\r\u0000\u0000\u02fd\u02ff\u0003"+
		"d2\r\u02fe\u02d4\u0001\u0000\u0000\u0000\u02fe\u02d6\u0001\u0000\u0000"+
		"\u0000\u02fe\u02d7\u0001\u0000\u0000\u0000\u02fe\u02d8\u0001\u0000\u0000"+
		"\u0000\u02fe\u02d9\u0001\u0000\u0000\u0000\u02fe\u02da\u0001\u0000\u0000"+
		"\u0000\u02fe\u02db\u0001\u0000\u0000\u0000\u02fe\u02dc\u0001\u0000\u0000"+
		"\u0000\u02fe\u02dd\u0001\u0000\u0000\u0000\u02fe\u02e5\u0001\u0000\u0000"+
		"\u0000\u02fe\u02ef\u0001\u0000\u0000\u0000\u02fe\u02f4\u0001\u0000\u0000"+
		"\u0000\u02fe\u02f8\u0001\u0000\u0000\u0000\u02fe\u02fa\u0001\u0000\u0000"+
		"\u0000\u02fe\u02fc\u0001\u0000\u0000\u0000\u02ff\u034e\u0001\u0000\u0000"+
		"\u0000\u0300\u0301\n\f\u0000\u0000\u0301\u0302\u0007\u0004\u0000\u0000"+
		"\u0302\u034d\u0003d2\r\u0303\u0304\n\u000b\u0000\u0000\u0304\u0305\u0005"+
		"\u000f\u0000\u0000\u0305\u034d\u0003d2\f\u0306\u0307\n\n\u0000\u0000\u0307"+
		"\u0308\u0007\u0005\u0000\u0000\u0308\u034d\u0003d2\u000b\u0309\u030a\n"+
		"\t\u0000\u0000\u030a\u030b\u0003l6\u0000\u030b\u030c\u0003d2\n\u030c\u034d"+
		"\u0001\u0000\u0000\u0000\u030d\u030e\n\b\u0000\u0000\u030e\u030f\u0007"+
		"\u0006\u0000\u0000\u030f\u034d\u0003d2\t\u0310\u0311\n\u0007\u0000\u0000"+
		"\u0311\u0312\u0007\u0007\u0000\u0000\u0312\u034d\u0003d2\b\u0313\u0314"+
		"\n\u0006\u0000\u0000\u0314\u0315\u0005\u0017\u0000\u0000\u0315\u034d\u0003"+
		"d2\u0007\u0316\u0317\n\u0005\u0000\u0000\u0317\u0318\u0005\u0018\u0000"+
		"\u0000\u0318\u034d\u0003d2\u0006\u0319\u031a\n\u0004\u0000\u0000\u031a"+
		"\u031b\u0005e\u0000\u0000\u031b\u034d\u0003d2\u0005\u031c\u031d\n\u0003"+
		"\u0000\u0000\u031d\u031e\u00051\u0000\u0000\u031e\u034d\u0003d2\u0004"+
		"\u031f\u0320\n\u0002\u0000\u0000\u0320\u0321\u0005H\u0000\u0000\u0321"+
		"\u034d\u0003d2\u0003\u0322\u0328\n\u0001\u0000\u0000\u0323\u0324\u0005"+
		"\u0019\u0000\u0000\u0324\u0325\u0003d2\u0000\u0325\u0326\u0005\u0005\u0000"+
		"\u0000\u0326\u0329\u0001\u0000\u0000\u0000\u0327\u0329\u0005\u001a\u0000"+
		"\u0000\u0328\u0323\u0001\u0000\u0000\u0000\u0328\u0327\u0001\u0000\u0000"+
		"\u0000\u0329\u032a\u0001\u0000\u0000\u0000\u032a\u034d\u0003d2\u0002\u032b"+
		"\u032c\n\u0018\u0000\u0000\u032c\u032d\u0005g\u0000\u0000\u032d\u034d"+
		"\u0005h\u0000\u0000\u032e\u0330\n\u0015\u0000\u0000\u032f\u0331\u0003"+
		"\u0088D\u0000\u0330\u032f\u0001\u0000\u0000\u0000\u0330\u0331\u0001\u0000"+
		"\u0000\u0000\u0331\u0332\u0001\u0000\u0000\u0000\u0332\u0334\u0005\u0001"+
		"\u0000\u0000\u0333\u0335\u0003r9\u0000\u0334\u0333\u0001\u0000\u0000\u0000"+
		"\u0334\u0335\u0001\u0000\u0000\u0000\u0335\u0336\u0001\u0000\u0000\u0000"+
		"\u0336\u034d\u0005\u0003\u0000\u0000\u0337\u0338\n\u0014\u0000\u0000\u0338"+
		"\u0339\u0005\t\u0000\u0000\u0339\u033a\u0003d2\u0000\u033a\u033b\u0005"+
		"\n\u0000\u0000\u033b\u034d\u0001\u0000\u0000\u0000\u033c\u033d\n\u0013"+
		"\u0000\u0000\u033d\u033f\u0005\t\u0000\u0000\u033e\u0340\u0003d2\u0000"+
		"\u033f\u033e\u0001\u0000\u0000\u0000\u033f\u0340\u0001\u0000\u0000\u0000"+
		"\u0340\u0341\u0001\u0000\u0000\u0000\u0341\u0343\u0005\u0005\u0000\u0000"+
		"\u0342\u0344\u0003d2\u0000\u0343\u0342\u0001\u0000\u0000\u0000\u0343\u0344"+
		"\u0001\u0000\u0000\u0000\u0344\u0345\u0001\u0000\u0000\u0000\u0345\u034d"+
		"\u0005\n\u0000\u0000\u0346\u0348\n\u0010\u0000\u0000\u0347\u0349\u0005"+
		"E\u0000\u0000\u0348\u0347\u0001\u0000\u0000\u0000\u0348\u0349\u0001\u0000"+
		"\u0000\u0000\u0349\u034a\u0001\u0000\u0000\u0000\u034a\u034b\u0005@\u0000"+
		"\u0000\u034b\u034d\u0003~?\u0000\u034c\u0300\u0001\u0000\u0000\u0000\u034c"+
		"\u0303\u0001\u0000\u0000\u0000\u034c\u0306\u0001\u0000\u0000\u0000\u034c"+
		"\u0309\u0001\u0000\u0000\u0000\u034c\u030d\u0001\u0000\u0000\u0000\u034c"+
		"\u0310\u0001\u0000\u0000\u0000\u034c\u0313\u0001\u0000\u0000\u0000\u034c"+
		"\u0316\u0001\u0000\u0000\u0000\u034c\u0319\u0001\u0000\u0000\u0000\u034c"+
		"\u031c\u0001\u0000\u0000\u0000\u034c\u031f\u0001\u0000\u0000\u0000\u034c"+
		"\u0322\u0001\u0000\u0000\u0000\u034c\u032b\u0001\u0000\u0000\u0000\u034c"+
		"\u032e\u0001\u0000\u0000\u0000\u034c\u0337\u0001\u0000\u0000\u0000\u034c"+
		"\u033c\u0001\u0000\u0000\u0000\u034c\u0346\u0001\u0000\u0000\u0000\u034d"+
		"\u0350\u0001\u0000\u0000\u0000\u034e\u034c\u0001\u0000\u0000\u0000\u034e"+
		"\u034f\u0001\u0000\u0000\u0000\u034fe\u0001\u0000\u0000\u0000\u0350\u034e"+
		"\u0001\u0000\u0000\u0000\u0351\u0352\u0003h4\u0000\u0352\u0353\u0005\u0000"+
		"\u0000\u0001\u0353g\u0001\u0000\u0000\u0000\u0354\u0355\u00064\uffff\uffff"+
		"\u0000\u0355\u0356\u0005h\u0000\u0000\u0356\u036b\u0001\u0000\u0000\u0000"+
		"\u0357\u0358\n\u0003\u0000\u0000\u0358\u0359\u0005g\u0000\u0000\u0359"+
		"\u036a\u0005h\u0000\u0000\u035a\u035b\n\u0002\u0000\u0000\u035b\u035c"+
		"\u0005\t\u0000\u0000\u035c\u035d\u0003j5\u0000\u035d\u035e\u0005\n\u0000"+
		"\u0000\u035e\u036a\u0001\u0000\u0000\u0000\u035f\u0360\n\u0001\u0000\u0000"+
		"\u0360\u0362\u0005\t\u0000\u0000\u0361\u0363\u0003j5\u0000\u0362\u0361"+
		"\u0001\u0000\u0000\u0000\u0362\u0363\u0001\u0000\u0000\u0000\u0363\u0364"+
		"\u0001\u0000\u0000\u0000\u0364\u0366\u0005\u0005\u0000\u0000\u0365\u0367"+
		"\u0003j5\u0000\u0366\u0365\u0001\u0000\u0000\u0000\u0366\u0367\u0001\u0000"+
		"\u0000\u0000\u0367\u0368\u0001\u0000\u0000\u0000\u0368\u036a\u0005\n\u0000"+
		"\u0000\u0369\u0357\u0001\u0000\u0000\u0000\u0369\u035a\u0001\u0000\u0000"+
		"\u0000\u0369\u035f\u0001\u0000\u0000\u0000\u036a\u036d\u0001\u0000\u0000"+
		"\u0000\u036b\u0369\u0001\u0000\u0000\u0000\u036b\u036c\u0001\u0000\u0000"+
		"\u0000\u036ci\u0001\u0000\u0000\u0000\u036d\u036b\u0001\u0000\u0000\u0000"+
		"\u036e\u0371\u0005i\u0000\u0000\u036f\u0371\u0003h4\u0000\u0370\u036e"+
		"\u0001\u0000\u0000\u0000\u0370\u036f\u0001\u0000\u0000\u0000\u0371k\u0001"+
		"\u0000\u0000\u0000\u0372\u0376\u0005\u001b\u0000\u0000\u0373\u0374\u0005"+
		"\u0014\u0000\u0000\u0374\u0376\u0005\u0014\u0000\u0000\u0375\u0372\u0001"+
		"\u0000\u0000\u0000\u0375\u0373\u0001\u0000\u0000\u0000\u0376m\u0001\u0000"+
		"\u0000\u0000\u0377\u037c\u0003d2\u0000\u0378\u0379\u0005\u0002\u0000\u0000"+
		"\u0379\u037b\u0003d2\u0000\u037a\u0378\u0001\u0000\u0000\u0000\u037b\u037e"+
		"\u0001\u0000\u0000\u0000\u037c\u037a\u0001\u0000\u0000\u0000\u037c\u037d"+
		"\u0001\u0000\u0000\u0000\u037d\u037f\u0001\u0000\u0000\u0000\u037e\u037c"+
		"\u0001\u0000\u0000\u0000\u037f\u0380\u0005\b\u0000\u0000\u0380\u0381\u0003"+
		"d2\u0000\u0381\u0387\u0001\u0000\u0000\u0000\u0382\u0383\u0003d2\u0000"+
		"\u0383\u0384\u0007\b\u0000\u0000\u0384\u0385\u0003d2\u0000\u0385\u0387"+
		"\u0001\u0000\u0000\u0000\u0386\u0377\u0001\u0000\u0000\u0000\u0386\u0382"+
		"\u0001\u0000\u0000\u0000\u0387o\u0001\u0000\u0000\u0000\u0388\u038d\u0003"+
		"d2\u0000\u0389\u038a\u0005\u0002\u0000\u0000\u038a\u038c\u0003d2\u0000"+
		"\u038b\u0389\u0001\u0000\u0000\u0000\u038c\u038f\u0001\u0000\u0000\u0000"+
		"\u038d\u038b\u0001\u0000\u0000\u0000\u038d\u038e\u0001\u0000\u0000\u0000"+
		"\u038eq\u0001\u0000\u0000\u0000\u038f\u038d\u0001\u0000\u0000\u0000\u0390"+
		"\u0395\u0003t:\u0000\u0391\u0392\u0005\u0002\u0000\u0000\u0392\u0394\u0003"+
		"t:\u0000\u0393\u0391\u0001\u0000\u0000\u0000\u0394\u0397\u0001\u0000\u0000"+
		"\u0000\u0395\u0393\u0001\u0000\u0000\u0000\u0395\u0396\u0001\u0000\u0000"+
		"\u0000\u0396s\u0001\u0000\u0000\u0000\u0397\u0395\u0001\u0000\u0000\u0000"+
		"\u0398\u0399\u0005h\u0000\u0000\u0399\u039a\u0005\b\u0000\u0000\u039a"+
		"\u039d\u0003d2\u0000\u039b\u039d\u0003d2\u0000\u039c\u0398\u0001\u0000"+
		"\u0000\u0000\u039c\u039b\u0001\u0000\u0000\u0000\u039du\u0001\u0000\u0000"+
		"\u0000\u039e\u039f\u00059\u0000\u0000\u039f\u03a0\u0003\u0082A\u0000\u03a0"+
		"w\u0001\u0000\u0000\u0000\u03a1\u03a6\u0003z=\u0000\u03a2\u03a3\u0005"+
		"\u0002\u0000\u0000\u03a3\u03a5\u0003z=\u0000\u03a4\u03a2\u0001\u0000\u0000"+
		"\u0000\u03a5\u03a8\u0001\u0000\u0000\u0000\u03a6\u03a4\u0001\u0000\u0000"+
		"\u0000\u03a6\u03a7\u0001\u0000\u0000\u0000\u03a7\u03b2\u0001\u0000\u0000"+
		"\u0000\u03a8\u03a6\u0001\u0000\u0000\u0000\u03a9\u03aa\u0005f\u0000\u0000"+
		"\u03aa\u03af\u0003z=\u0000\u03ab\u03ac\u0005\u0002\u0000\u0000\u03ac\u03ae"+
		"\u0003z=\u0000\u03ad\u03ab\u0001\u0000\u0000\u0000\u03ae\u03b1\u0001\u0000"+
		"\u0000\u0000\u03af\u03ad\u0001\u0000\u0000\u0000\u03af\u03b0\u0001\u0000"+
		"\u0000\u0000\u03b0\u03b3\u0001\u0000\u0000\u0000\u03b1\u03af\u0001\u0000"+
		"\u0000\u0000\u03b2\u03a9\u0001\u0000\u0000\u0000\u03b2\u03b3\u0001\u0000"+
		"\u0000\u0000\u03b3\u03be\u0001\u0000\u0000\u0000\u03b4\u03b5\u0005f\u0000"+
		"\u0000\u03b5\u03ba\u0003z=\u0000\u03b6\u03b7\u0005\u0002\u0000\u0000\u03b7"+
		"\u03b9\u0003z=\u0000\u03b8\u03b6\u0001\u0000\u0000\u0000\u03b9\u03bc\u0001"+
		"\u0000\u0000\u0000\u03ba\u03b8\u0001\u0000\u0000\u0000\u03ba\u03bb\u0001"+
		"\u0000\u0000\u0000\u03bb\u03be\u0001\u0000\u0000\u0000\u03bc\u03ba\u0001"+
		"\u0000\u0000\u0000\u03bd\u03a1\u0001\u0000\u0000\u0000\u03bd\u03b4\u0001"+
		"\u0000\u0000\u0000\u03bey\u0001\u0000\u0000\u0000\u03bf\u03c1\u0003Z-"+
		"\u0000\u03c0\u03bf\u0001\u0000\u0000\u0000\u03c0\u03c1\u0001\u0000\u0000"+
		"\u0000\u03c1\u03c2\u0001\u0000\u0000\u0000\u03c2\u03c3\u0003|>\u0000\u03c3"+
		"\u03c5\u0005h\u0000\u0000\u03c4\u03c6\u0005f\u0000\u0000\u03c5\u03c4\u0001"+
		"\u0000\u0000\u0000\u03c5\u03c6\u0001\u0000\u0000\u0000\u03c6\u03c9\u0001"+
		"\u0000\u0000\u0000\u03c7\u03c8\u0005\b\u0000\u0000\u03c8\u03ca\u0003d"+
		"2\u0000\u03c9\u03c7\u0001\u0000\u0000\u0000\u03c9\u03ca\u0001\u0000\u0000"+
		"\u0000\u03ca{\u0001\u0000\u0000\u0000\u03cb\u03cc\u0006>\uffff\uffff\u0000"+
		"\u03cc\u03d0\u0003\u0082A\u0000\u03cd\u03d0\u0003\u0080@\u0000\u03ce\u03d0"+
		"\u0003\u008aE\u0000\u03cf\u03cb\u0001\u0000\u0000\u0000\u03cf\u03cd\u0001"+
		"\u0000\u0000\u0000\u03cf\u03ce\u0001\u0000\u0000\u0000\u03d0\u03da\u0001"+
		"\u0000\u0000\u0000\u03d1\u03d4\n\u0001\u0000\u0000\u03d2\u03d3\u0005\t"+
		"\u0000\u0000\u03d3\u03d5\u0005\n\u0000\u0000\u03d4\u03d2\u0001\u0000\u0000"+
		"\u0000\u03d5\u03d6\u0001\u0000\u0000\u0000\u03d6\u03d4\u0001\u0000\u0000"+
		"\u0000\u03d6\u03d7\u0001\u0000\u0000\u0000\u03d7\u03d9\u0001\u0000\u0000"+
		"\u0000\u03d8\u03d1\u0001\u0000\u0000\u0000\u03d9\u03dc\u0001\u0000\u0000"+
		"\u0000\u03da\u03d8\u0001\u0000\u0000\u0000\u03da\u03db\u0001\u0000\u0000"+
		"\u0000\u03db}\u0001\u0000\u0000\u0000\u03dc\u03da\u0001\u0000\u0000\u0000"+
		"\u03dd\u03de\u0006?\uffff\uffff\u0000\u03de\u03e1\u0003\u0082A\u0000\u03df"+
		"\u03e1\u0003\u0080@\u0000\u03e0\u03dd\u0001\u0000\u0000\u0000\u03e0\u03df"+
		"\u0001\u0000\u0000\u0000\u03e1\u03eb\u0001\u0000\u0000\u0000\u03e2\u03e5"+
		"\n\u0001\u0000\u0000\u03e3\u03e4\u0005\t\u0000\u0000\u03e4\u03e6\u0005"+
		"\n\u0000\u0000\u03e5\u03e3\u0001\u0000\u0000\u0000\u03e6\u03e7\u0001\u0000"+
		"\u0000\u0000\u03e7\u03e5\u0001\u0000\u0000\u0000\u03e7\u03e8\u0001\u0000"+
		"\u0000\u0000\u03e8\u03ea\u0001\u0000\u0000\u0000\u03e9\u03e2\u0001\u0000"+
		"\u0000\u0000\u03ea\u03ed\u0001\u0000\u0000\u0000\u03eb\u03e9\u0001\u0000"+
		"\u0000\u0000\u03eb\u03ec\u0001\u0000\u0000\u0000\u03ec\u007f\u0001\u0000"+
		"\u0000\u0000\u03ed\u03eb\u0001\u0000\u0000\u0000\u03ee\u03ef\u0007\t\u0000"+
		"\u0000\u03ef\u0081\u0001\u0000\u0000\u0000\u03f0\u03f2\u0003b1\u0000\u03f1"+
		"\u03f3\u0003\u0088D\u0000\u03f2\u03f1\u0001\u0000\u0000\u0000\u03f2\u03f3"+
		"\u0001\u0000\u0000\u0000\u03f3\u0083\u0001\u0000\u0000\u0000\u03f4\u03f5"+
		"\u0005\u0011\u0000\u0000\u03f5\u03fa\u0003\u0086C\u0000\u03f6\u03f7\u0005"+
		"\u0002\u0000\u0000\u03f7\u03f9\u0003\u0086C\u0000\u03f8\u03f6\u0001\u0000"+
		"\u0000\u0000\u03f9\u03fc\u0001\u0000\u0000\u0000\u03fa\u03f8\u0001\u0000"+
		"\u0000\u0000\u03fa\u03fb\u0001\u0000\u0000\u0000\u03fb\u03fd\u0001\u0000"+
		"\u0000\u0000\u03fc\u03fa\u0001\u0000\u0000\u0000\u03fd\u03fe\u0005\u0014"+
		"\u0000\u0000\u03fe\u0085\u0001\u0000\u0000\u0000\u03ff\u0404\u0005h\u0000"+
		"\u0000\u0400\u0401\u0005h\u0000\u0000\u0401\u0402\u0005B\u0000\u0000\u0402"+
		"\u0404\u0003|>\u0000\u0403\u03ff\u0001\u0000\u0000\u0000\u0403\u0400\u0001"+
		"\u0000\u0000\u0000\u0404\u0087\u0001\u0000\u0000\u0000\u0405\u0406\u0005"+
		"\u0011\u0000\u0000\u0406\u0413\u0005\u0014\u0000\u0000\u0407\u0408\u0005"+
		"\u0011\u0000\u0000\u0408\u040d\u0003|>\u0000\u0409\u040a\u0005\u0002\u0000"+
		"\u0000\u040a\u040c\u0003|>\u0000\u040b\u0409\u0001\u0000\u0000\u0000\u040c"+
		"\u040f\u0001\u0000\u0000\u0000\u040d\u040b\u0001\u0000\u0000\u0000\u040d"+
		"\u040e\u0001\u0000\u0000\u0000\u040e\u0410\u0001\u0000\u0000\u0000\u040f"+
		"\u040d\u0001\u0000\u0000\u0000\u0410\u0411\u0005\u0014\u0000\u0000\u0411"+
		"\u0413\u0001\u0000\u0000\u0000\u0412\u0405\u0001\u0000\u0000\u0000\u0412"+
		"\u0407\u0001\u0000\u0000\u0000\u0413\u0089\u0001\u0000\u0000\u0000\u0414"+
		"\u0416\u0005b\u0000\u0000\u0415\u0417\u0003\u0088D\u0000\u0416\u0415\u0001"+
		"\u0000\u0000\u0000\u0416\u0417\u0001\u0000\u0000\u0000\u0417\u008b\u0001"+
		"\u0000\u0000\u0000\u0418\u0419\u0003|>\u0000\u0419\u0420\u0005h\u0000"+
		"\u0000\u041a\u041b\u0005\u0002\u0000\u0000\u041b\u041c\u0003|>\u0000\u041c"+
		"\u041d\u0005h\u0000\u0000\u041d\u041f\u0001\u0000\u0000\u0000\u041e\u041a"+
		"\u0001\u0000\u0000\u0000\u041f\u0422\u0001\u0000\u0000\u0000\u0420\u041e"+
		"\u0001\u0000\u0000\u0000\u0420\u0421\u0001\u0000\u0000\u0000\u0421\u008d"+
		"\u0001\u0000\u0000\u0000\u0422\u0420\u0001\u0000\u0000\u0000\u0084\u0092"+
		"\u0098\u00a0\u00aa\u00b2\u00bd\u00c0\u00c4\u00ca\u00cf\u00d1\u00d6\u00d9"+
		"\u00dd\u00e1\u00e6\u00e8\u00eb\u00f1\u00f9\u0101\u010d\u0119\u011d\u0125"+
		"\u0131\u013d\u0141\u014a\u0154\u0158\u0161\u016b\u016f\u0174\u0177\u017c"+
		"\u0180\u0186\u018c\u0192\u0197\u019b\u01a8\u01ad\u01b2\u01b6\u01ba\u01c0"+
		"\u01c5\u01c7\u01ca\u01ce\u01d2\u01d7\u01d9\u01db\u01de\u01e6\u01ec\u01f4"+
		"\u0204\u020d\u0219\u0220\u022b\u0232\u023a\u0241\u024b\u024f\u0254\u025e"+
		"\u0265\u0269\u0270\u0282\u0286\u0290\u029a\u02a0\u02a4\u02a9\u02b5\u02ba"+
		"\u02c0\u02c7\u02cf\u02d1\u02e1\u02ed\u02fe\u0328\u0330\u0334\u033f\u0343"+
		"\u0348\u034c\u034e\u0362\u0366\u0369\u036b\u0370\u0375\u037c\u0386\u038d"+
		"\u0395\u039c\u03a6\u03af\u03b2\u03ba\u03bd\u03c0\u03c5\u03c9\u03cf\u03d6"+
		"\u03da\u03e0\u03e7\u03eb\u03f2\u03fa\u0403\u040d\u0412\u0416\u0420";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}