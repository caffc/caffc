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
		T__38=39, T__39=40, CHAR=41, STRING=42, SIMPLE_ESCAPE=43, HEX_ESCAPE=44, 
		OCTAL_ESCAPE=45, COMMENT=46, BLOCK_COMMENT=47, WS=48, AS=49, AND=50, BREAK=51, 
		CASE=52, CATCH=53, CLASS=54, CONTINUE=55, DEFAULT=56, ELSE=57, EXTENDS=58, 
		FINALLY=59, FOR=60, IF=61, IFDEF=62, IMPLEMENTS=63, IN=64, INSTANCEOF=65, 
		INTERFACE=66, IS=67, MODULE=68, NEW=69, NOT=70, FALSE=71, NULL=72, OR=73, 
		TRUE=74, RETURN=75, STATIC=76, SWITCH=77, TAG=78, THROW=79, TRY=80, USE=81, 
		VAR=82, VOID=83, WHILE=84, YIELD=85, U8=86, I8=87, U16=88, I16=89, U32=90, 
		I32=91, U64=92, I64=93, F32=94, F64=95, NATIVE=96, CURLY_OPEN=97, CURLY_CLOSE=98, 
		FN=99, SHARP=100, STAR=101, DOT=102, ID=103, NUMBER=104, DigitSequence=105;
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
		RULE_assignExpression = 51, RULE_expressionTuple = 52, RULE_extend = 53, 
		RULE_parameterDefinitions = 54, RULE_parameterDefinition = 55, RULE_typeName = 56, 
		RULE_newType = 57, RULE_primitiveTypeName = 58, RULE_classType = 59, RULE_genericsDeclarations = 60, 
		RULE_genericDeclaration = 61, RULE_genericsInstantiations = 62, RULE_functionType = 63, 
		RULE_namedTypeTuple = 64;
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
			"fqdn", "expression", "assignExpression", "expressionTuple", "extend", 
			"parameterDefinitions", "parameterDefinition", "typeName", "newType", 
			"primitiveTypeName", "classType", "genericsDeclarations", "genericDeclaration", 
			"genericsInstantiations", "functionType", "namedTypeTuple"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'->'", "':'", "';'", "'@'", "'='", "'['", 
			"']'", "'!'", "'~'", "'-'", "'%'", "'/'", "'+'", "'<<'", "'>>'", "'<'", 
			"'<='", "'>='", "'>'", "'=='", "'!='", "'&'", "'^'", "'|'", "'?'", "'?:'", 
			"'<<='", "'>>='", "'&='", "'|='", "'^='", "'*='", "'/='", "'+='", "'-='", 
			"'ptr'", "'bool'", null, null, null, null, null, null, null, null, "'as'", 
			"'and'", "'break'", "'case'", "'catch'", "'class'", "'continue'", "'default'", 
			"'else'", "'extends'", "'finally'", "'for'", "'if'", "'ifdef'", "'implements'", 
			"'in'", "'instanceof'", "'interface'", "'is'", "'module'", "'new'", "'not'", 
			"'false'", "'null'", "'or'", "'true'", "'return'", "'static'", "'switch'", 
			"'tag'", "'throw'", "'try'", "'use'", "'var'", "'void'", "'while'", "'yield'", 
			"'u8'", "'i8'", "'u16'", "'i16'", "'u32'", "'i32'", "'u64'", "'i64'", 
			"'f32'", "'f64'", null, "'{'", "'}'", "'fn'", "'#'", "'*'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "CHAR", "STRING", "SIMPLE_ESCAPE", "HEX_ESCAPE", 
			"OCTAL_ESCAPE", "COMMENT", "BLOCK_COMMENT", "WS", "AS", "AND", "BREAK", 
			"CASE", "CATCH", "CLASS", "CONTINUE", "DEFAULT", "ELSE", "EXTENDS", "FINALLY", 
			"FOR", "IF", "IFDEF", "IMPLEMENTS", "IN", "INSTANCEOF", "INTERFACE", 
			"IS", "MODULE", "NEW", "NOT", "FALSE", "NULL", "OR", "TRUE", "RETURN", 
			"STATIC", "SWITCH", "TAG", "THROW", "TRY", "USE", "VAR", "VOID", "WHILE", 
			"YIELD", "U8", "I8", "U16", "I16", "U32", "I32", "U64", "I64", "F32", 
			"F64", "NATIVE", "CURLY_OPEN", "CURLY_CLOSE", "FN", "SHARP", "STAR", 
			"DOT", "ID", "NUMBER", "DigitSequence"
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
			setState(130);
			module();
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==USE) {
				{
				{
				setState(131);
				useStatement();
				}
				}
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18016047776923648L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 165355262977L) != 0)) {
				{
				{
				setState(137);
				compileBlock();
				}
				}
				setState(142);
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
			setState(143);
			match(MODULE);
			setState(144);
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
			setState(146);
			use();
			setState(148);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(147);
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
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				nativeBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				tagDefinition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				function();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(153);
				classDefinition();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(154);
				interfaceDefinition();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(155);
				variableDeclarations();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(156);
				sharpSwitchUnit();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(157);
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
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				nativeBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				tagDefinition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(162);
				function();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				classDefinition();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(164);
				interfaceDefinition();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(165);
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
			setState(168);
			match(USE);
			setState(169);
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
			setState(171);
			match(AS);
			setState(172);
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
			setState(174);
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
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(176);
					tags();
					}
				}

				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(179);
					match(STATIC);
					}
				}

				setState(182);
				match(ID);
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(183);
					genericsDeclarations();
					}
				}

				setState(186);
				match(T__0);
				setState(187);
				extend();
				setState(190);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(188);
					match(T__1);
					setState(189);
					parameterDefinitions();
					}
				}

				setState(192);
				match(T__2);
				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(193);
					match(T__3);
					setState(195);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__38 || _la==T__39 || ((((_la - 83)) & ~0x3f) == 0 && ((1L << (_la - 83)) & 1122297L) != 0)) {
						{
						setState(194);
						returnType();
						}
					}

					}
				}

				setState(199);
				functionBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(201);
					tags();
					}
				}

				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(204);
					match(STATIC);
					}
				}

				setState(207);
				match(ID);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(208);
					genericsDeclarations();
					}
				}

				setState(211);
				match(T__0);
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__38 || _la==T__39 || ((((_la - 86)) & ~0x3f) == 0 && ((1L << (_la - 86)) & 156671L) != 0)) {
					{
					setState(212);
					parameterDefinitions();
					}
				}

				setState(215);
				match(T__2);
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(216);
					match(T__3);
					setState(218);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__38 || _la==T__39 || ((((_la - 83)) & ~0x3f) == 0 && ((1L << (_la - 83)) & 1122297L) != 0)) {
						{
						setState(217);
						returnType();
						}
					}

					}
				}

				setState(222);
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
			setState(225);
			match(CURLY_OPEN);
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3497053356990412802L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 55029181935L) != 0)) {
				{
				{
				setState(226);
				functionBodyItem();
				}
				}
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(232);
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
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(235);
				sharpSwitchMethod();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(236);
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
			setState(239);
			match(SHARP);
			setState(240);
			match(SWITCH);
			setState(241);
			match(CURLY_OPEN);
			setState(243); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(242);
				sharpCaseUnit();
				}
				}
				setState(245); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SHARP );
			setState(247);
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
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				match(SHARP);
				setState(250);
				match(CASE);
				setState(251);
				expression(0);
				setState(252);
				match(T__4);
				setState(253);
				match(CURLY_OPEN);
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18016047776923648L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 165355262977L) != 0)) {
					{
					{
					setState(254);
					compileBlockPlain();
					}
					}
					setState(259);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(260);
				match(CURLY_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(262);
				match(SHARP);
				setState(263);
				match(DEFAULT);
				setState(264);
				match(T__4);
				setState(265);
				match(CURLY_OPEN);
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18016047776923648L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 165355262977L) != 0)) {
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
			setState(275);
			match(SHARP);
			setState(276);
			match(SWITCH);
			setState(277);
			match(CURLY_OPEN);
			setState(279); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(278);
				sharpCaseMethod();
				}
				}
				setState(281); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SHARP );
			setState(283);
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
			setState(309);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(285);
				match(SHARP);
				setState(286);
				match(CASE);
				setState(287);
				expression(0);
				setState(288);
				match(T__4);
				setState(289);
				match(CURLY_OPEN);
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3497053356990412802L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 55029181935L) != 0)) {
					{
					{
					setState(290);
					statement();
					}
					}
					setState(295);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(296);
				match(CURLY_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(298);
				match(SHARP);
				setState(299);
				match(DEFAULT);
				setState(300);
				match(T__4);
				setState(301);
				match(CURLY_OPEN);
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3497053356990412802L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 55029181935L) != 0)) {
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
			setState(311);
			match(SHARP);
			setState(312);
			match(IFDEF);
			setState(313);
			expression(0);
			setState(314);
			match(CURLY_OPEN);
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18016047776923648L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 165355262977L) != 0)) {
				{
				{
				setState(315);
				((SharpIfdefUnitContext)_localctx).compileBlockPlain = compileBlockPlain();
				((SharpIfdefUnitContext)_localctx).thenBlocks.add(((SharpIfdefUnitContext)_localctx).compileBlockPlain);
				}
				}
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(321);
			match(CURLY_CLOSE);
			setState(332);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(322);
				match(SHARP);
				setState(323);
				match(ELSE);
				setState(324);
				match(CURLY_OPEN);
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18016047776923648L) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & 165355262977L) != 0)) {
					{
					{
					setState(325);
					((SharpIfdefUnitContext)_localctx).compileBlockPlain = compileBlockPlain();
					((SharpIfdefUnitContext)_localctx).elseBlocks.add(((SharpIfdefUnitContext)_localctx).compileBlockPlain);
					}
					}
					setState(330);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(331);
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
			setState(334);
			match(SHARP);
			setState(335);
			match(IFDEF);
			setState(336);
			expression(0);
			setState(337);
			match(CURLY_OPEN);
			setState(341);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3497053356990412802L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 55029181935L) != 0)) {
				{
				{
				setState(338);
				((SharpIfdefMethodContext)_localctx).statement = statement();
				((SharpIfdefMethodContext)_localctx).thenStatements.add(((SharpIfdefMethodContext)_localctx).statement);
				}
				}
				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(344);
			match(CURLY_CLOSE);
			setState(355);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(345);
				match(SHARP);
				setState(346);
				match(ELSE);
				setState(347);
				match(CURLY_OPEN);
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3497053356990412802L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 55029181935L) != 0)) {
					{
					{
					setState(348);
					((SharpIfdefMethodContext)_localctx).statement = statement();
					((SharpIfdefMethodContext)_localctx).elseStatements.add(((SharpIfdefMethodContext)_localctx).statement);
					}
					}
					setState(353);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(354);
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
			setState(360);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(357);
				namedTypeTuple();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(358);
				typeName(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(359);
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
			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(362);
				tags();
				}
			}

			setState(365);
			match(CLASS);
			setState(366);
			name();
			setState(368);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(367);
				genericsDeclarations();
				}
			}

			setState(372);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(370);
				match(IMPLEMENTS);
				setState(371);
				interfaceImplementations();
				}
			}

			setState(374);
			match(CURLY_OPEN);
			setState(378);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(375);
					fieldDeclaration();
					}
					} 
				}
				setState(380);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			}
			setState(384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 76)) & ~0x3f) == 0 && ((1L << (_la - 76)) & 150994945L) != 0)) {
				{
				{
				setState(381);
				function();
				}
				}
				setState(386);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(387);
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
			setState(390);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(389);
				tags();
				}
			}

			setState(392);
			match(INTERFACE);
			setState(393);
			name();
			setState(395);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(394);
				genericsDeclarations();
				}
			}

			setState(399);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(397);
				match(EXTENDS);
				setState(398);
				interfaceImplementations();
				}
			}

			setState(401);
			match(CURLY_OPEN);
			setState(402);
			interfaceStatements();
			setState(403);
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
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
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
			setState(407);
			interfaceImplementation();
			setState(412);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(408);
				match(T__1);
				setState(409);
				interfaceImplementation();
				}
				}
				setState(414);
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
			setState(415);
			fqdn(0);
			setState(417);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(416);
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
			setState(422);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==ID) {
				{
				{
				setState(419);
				functionDeclaration();
				}
				}
				setState(424);
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
			setState(463);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(426);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(425);
					tags();
					}
				}

				setState(428);
				match(ID);
				setState(430);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(429);
					genericsDeclarations();
					}
				}

				setState(432);
				match(T__0);
				setState(433);
				extend();
				setState(436);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(434);
					match(T__1);
					setState(435);
					parameterDefinitions();
					}
				}

				setState(438);
				match(T__2);
				setState(443);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(439);
					match(T__3);
					setState(441);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
					case 1:
						{
						setState(440);
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
				setState(446);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(445);
					tags();
					}
				}

				setState(448);
				match(ID);
				setState(450);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(449);
					genericsDeclarations();
					}
				}

				setState(452);
				match(T__0);
				setState(454);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__38 || _la==T__39 || ((((_la - 86)) & ~0x3f) == 0 && ((1L << (_la - 86)) & 156671L) != 0)) {
					{
					setState(453);
					parameterDefinitions();
					}
				}

				setState(456);
				match(T__2);
				setState(461);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(457);
					match(T__3);
					setState(459);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
					case 1:
						{
						setState(458);
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
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(465);
				tags();
				}
			}

			setState(468);
			match(TAG);
			setState(469);
			match(ID);
			setState(470);
			match(CURLY_OPEN);
			setState(474);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__38 || _la==T__39 || ((((_la - 86)) & ~0x3f) == 0 && ((1L << (_la - 86)) & 156671L) != 0)) {
				{
				{
				setState(471);
				fieldDeclaration();
				}
				}
				setState(476);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(477);
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
			setState(480);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(479);
				tags();
				}
			}

			setState(482);
			typeName(0);
			setState(483);
			match(ID);
			setState(488);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(484);
				match(T__1);
				setState(485);
				match(ID);
				}
				}
				setState(490);
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
			setState(504);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(491);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(492);
				variableDeclarations();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(493);
				return_();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(494);
				controlFlow();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(495);
				nativeBlock();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(496);
				whileBlock();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(497);
				forBlock();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(498);
				ifBlock();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(499);
				switchBlock();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(500);
				tryCatchBlock();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(501);
				throwStatement();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(502);
				expression(0);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(503);
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
			setState(506);
			match(THROW);
			setState(507);
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
			setState(509);
			match(CURLY_OPEN);
			setState(513);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3497053356990412802L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 55029181935L) != 0)) {
				{
				{
				setState(510);
				statement();
				}
				}
				setState(515);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(516);
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
			setState(518);
			match(WHILE);
			setState(519);
			expression(0);
			setState(520);
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
			setState(543);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(522);
				match(FOR);
				setState(525);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
				case 1:
					{
					setState(523);
					((ForBlockContext)_localctx).initExpression = assignExpression();
					}
					break;
				case 2:
					{
					setState(524);
					variableDeclarations();
					}
					break;
				}
				setState(527);
				match(T__5);
				setState(528);
				((ForBlockContext)_localctx).conditionExpression = expression(0);
				setState(529);
				match(T__5);
				setState(532);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
				case 1:
					{
					setState(530);
					((ForBlockContext)_localctx).incrementExpression = expression(0);
					}
					break;
				case 2:
					{
					setState(531);
					((ForBlockContext)_localctx).incrementAssignExpression = assignExpression();
					}
					break;
				}
				setState(534);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(536);
				match(FOR);
				setState(537);
				typeName(0);
				setState(538);
				((ForBlockContext)_localctx).variableName = match(ID);
				setState(539);
				match(IN);
				setState(540);
				expression(0);
				setState(541);
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
			setState(558);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(545);
				match(IF);
				setState(546);
				expression(0);
				setState(550);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CURLY_OPEN:
					{
					setState(547);
					((IfBlockContext)_localctx).trueBlock = block();
					}
					break;
				case RETURN:
					{
					setState(548);
					return_();
					}
					break;
				case BREAK:
				case CONTINUE:
					{
					setState(549);
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
				setState(552);
				match(IF);
				setState(553);
				expression(0);
				setState(554);
				((IfBlockContext)_localctx).trueBlock = block();
				setState(555);
				match(ELSE);
				setState(556);
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
			setState(579);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(560);
				match(SWITCH);
				setState(561);
				match(CURLY_OPEN);
				setState(563); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(562);
					switchBranch();
					}
					}
					setState(565); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CASE || _la==DEFAULT );
				setState(567);
				match(CURLY_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(569);
				match(SWITCH);
				setState(570);
				expression(0);
				setState(571);
				match(CURLY_OPEN);
				setState(573); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(572);
					switchBranch();
					}
					}
					setState(575); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CASE || _la==DEFAULT );
				setState(577);
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
			setState(584);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CURLY_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(581);
				block();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 2);
				{
				setState(582);
				return_();
				}
				break;
			case BREAK:
			case CONTINUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(583);
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
			setState(594);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(586);
				match(CASE);
				setState(587);
				expression(0);
				setState(588);
				match(T__4);
				setState(589);
				switchCaseBody();
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(591);
				match(DEFAULT);
				setState(592);
				match(T__4);
				setState(593);
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
			setState(612);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(596);
				match(TRY);
				setState(597);
				block();
				setState(601);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CATCH) {
					{
					{
					setState(598);
					catchBlock();
					}
					}
					setState(603);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(605);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(604);
					finallyBlock();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(607);
				match(TRY);
				setState(608);
				block();
				setState(609);
				match(FINALLY);
				setState(610);
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
			setState(614);
			match(CATCH);
			setState(615);
			match(T__0);
			setState(616);
			classType();
			setState(617);
			match(ID);
			setState(618);
			match(T__2);
			setState(619);
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
			setState(621);
			match(FINALLY);
			setState(622);
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
			setState(634);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(624);
				match(RETURN);
				setState(625);
				expression(0);
				setState(630);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(626);
					match(T__1);
					setState(627);
					expression(0);
					}
					}
					setState(632);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(633);
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
			setState(644);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(636);
				match(T__6);
				setState(637);
				expression(0);
				setState(638);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(640);
				match(T__6);
				setState(641);
				expression(0);
				setState(642);
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
			setState(646);
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
			setState(648);
			varType();
			setState(649);
			variableDeclaration();
			setState(654);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(650);
				match(T__1);
				setState(651);
				variableDeclaration();
				}
				}
				setState(656);
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
			setState(657);
			match(ID);
			setState(660);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(658);
				match(T__7);
				setState(659);
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
			setState(664);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(662);
				match(VAR);
				}
				break;
			case T__38:
			case T__39:
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
				setState(663);
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
			setState(667); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(666);
				tag();
				}
				}
				setState(669); 
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
			setState(686);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(671);
				match(SHARP);
				setState(672);
				fqdn(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(673);
				match(SHARP);
				setState(674);
				fqdn(0);
				setState(675);
				match(T__0);
				setState(676);
				tagParam();
				setState(681);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(677);
					match(T__1);
					setState(678);
					tagParam();
					}
					}
					setState(683);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(684);
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
			setState(692);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(688);
				match(ID);
				setState(689);
				match(T__7);
				setState(690);
				constExpression();
				}
				break;
			case CHAR:
			case STRING:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(691);
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
			setState(694);
			_la = _input.LA(1);
			if ( !(((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & -9223372036854775805L) != 0)) ) {
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
	public static class FqdnMultipleContext extends FqdnContext {
		public FqdnContext fqdn() {
			return getRuleContext(FqdnContext.class,0);
		}
		public TerminalNode DOT() { return getToken(caffcParser.DOT, 0); }
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public FqdnMultipleContext(FqdnContext ctx) { copyFrom(ctx); }
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
			{
			_localctx = new FqdnIdContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(697);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(704);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FqdnMultipleContext(new FqdnContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_fqdn);
					setState(699);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(700);
					match(DOT);
					setState(701);
					match(ID);
					}
					} 
				}
				setState(706);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
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
		public ExpressionTupleContext expressionTuple() {
			return getRuleContext(ExpressionTupleContext.class,0);
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
		public ExpressionTupleContext expressionTuple() {
			return getRuleContext(ExpressionTupleContext.class,0);
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
			setState(748);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				{
				_localctx = new ExNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(708);
				match(NUMBER);
				}
				break;
			case 2:
				{
				_localctx = new ExStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(709);
				match(STRING);
				}
				break;
			case 3:
				{
				_localctx = new ExCharContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(710);
				match(CHAR);
				}
				break;
			case 4:
				{
				_localctx = new ExIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(711);
				match(ID);
				}
				break;
			case 5:
				{
				_localctx = new ExNullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(712);
				match(NULL);
				}
				break;
			case 6:
				{
				_localctx = new ExTrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(713);
				match(TRUE);
				}
				break;
			case 7:
				{
				_localctx = new ExFalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(714);
				match(FALSE);
				}
				break;
			case 8:
				{
				_localctx = new ExNewObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(715);
				match(NEW);
				setState(716);
				newType(0);
				setState(717);
				match(T__0);
				setState(719);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6597069780994L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 51539607599L) != 0)) {
					{
					setState(718);
					expressionTuple();
					}
				}

				setState(721);
				match(T__2);
				}
				break;
			case 9:
				{
				_localctx = new ExNewArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(723);
				match(NEW);
				setState(724);
				newType(0);
				setState(729); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(725);
						match(T__8);
						setState(726);
						expression(0);
						setState(727);
						match(T__9);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(731); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 10:
				{
				_localctx = new ExCastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(733);
				match(T__0);
				setState(734);
				typeName(0);
				setState(735);
				match(T__2);
				setState(736);
				expression(18);
				}
				break;
			case 11:
				{
				_localctx = new ExParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(738);
				match(T__0);
				setState(739);
				expression(0);
				setState(740);
				match(T__2);
				}
				break;
			case 12:
				{
				_localctx = new ExBoolNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(742);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(743);
				expression(15);
				}
				break;
			case 13:
				{
				_localctx = new ExBitNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(744);
				match(T__11);
				setState(745);
				expression(14);
				}
				break;
			case 14:
				{
				_localctx = new ExUnaryMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(746);
				match(T__12);
				setState(747);
				expression(13);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(817);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(815);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
					case 1:
						{
						_localctx = new ExMulModContext(new ExpressionContext(_parentctx, _parentState));
						((ExMulModContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(750);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(751);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==STAR) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(752);
						((ExMulModContext)_localctx).rightExpression = expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ExDivContext(new ExpressionContext(_parentctx, _parentState));
						((ExDivContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(753);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(754);
						match(T__14);
						setState(755);
						((ExDivContext)_localctx).rightExpression = expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExAddSubContext(new ExpressionContext(_parentctx, _parentState));
						((ExAddSubContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(756);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(757);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__15) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(758);
						((ExAddSubContext)_localctx).rightExpression = expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExShiftContext(new ExpressionContext(_parentctx, _parentState));
						((ExShiftContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(759);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(760);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__17) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(761);
						((ExShiftContext)_localctx).rightExpression = expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExLtLteGtGteContext(new ExpressionContext(_parentctx, _parentState));
						((ExLtLteGtGteContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(762);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(763);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7864320L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(764);
						((ExLtLteGtGteContext)_localctx).rightExpression = expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExEqNeqContext(new ExpressionContext(_parentctx, _parentState));
						((ExEqNeqContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(765);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(766);
						_la = _input.LA(1);
						if ( !(_la==T__22 || _la==T__23) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(767);
						((ExEqNeqContext)_localctx).rightExpression = expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExBitAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitAndContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(768);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(769);
						match(T__24);
						setState(770);
						((ExBitAndContext)_localctx).rightExpression = expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExBitXorContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitXorContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(771);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(772);
						match(T__25);
						setState(773);
						((ExBitXorContext)_localctx).rightExpression = expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExBitOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitOrContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(774);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(775);
						match(T__26);
						setState(776);
						((ExBitOrContext)_localctx).rightExpression = expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExBoolAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExBoolAndContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(777);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(778);
						match(AND);
						setState(779);
						((ExBoolAndContext)_localctx).rightExpression = expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExBoolOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExBoolOrContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(780);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(781);
						match(OR);
						setState(782);
						((ExBoolOrContext)_localctx).rightExpression = expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExTernaryContext(new ExpressionContext(_parentctx, _parentState));
						((ExTernaryContext)_localctx).checkExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(783);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(789);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__27:
							{
							setState(784);
							match(T__27);
							setState(785);
							((ExTernaryContext)_localctx).trueExpression = expression(0);
							setState(786);
							match(T__4);
							}
							break;
						case T__28:
							{
							setState(788);
							match(T__28);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(791);
						((ExTernaryContext)_localctx).falseExpression = expression(2);
						}
						break;
					case 13:
						{
						_localctx = new ExDotAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(792);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(793);
						match(DOT);
						setState(794);
						match(ID);
						}
						break;
					case 14:
						{
						_localctx = new ExFnCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(795);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(797);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__18) {
							{
							setState(796);
							genericsInstantiations();
							}
						}

						setState(799);
						match(T__0);
						setState(801);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6597069780994L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 51539607599L) != 0)) {
							{
							setState(800);
							expressionTuple();
							}
						}

						setState(803);
						match(T__2);
						}
						break;
					case 15:
						{
						_localctx = new ExIndexAccessContext(new ExpressionContext(_parentctx, _parentState));
						((ExIndexAccessContext)_localctx).arraryExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(804);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(805);
						match(T__8);
						setState(806);
						((ExIndexAccessContext)_localctx).indexExpression = expression(0);
						setState(807);
						match(T__9);
						}
						break;
					case 16:
						{
						_localctx = new ExInstanceOfContext(new ExpressionContext(_parentctx, _parentState));
						((ExInstanceOfContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(809);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(811);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(810);
							match(NOT);
							}
						}

						setState(813);
						match(INSTANCEOF);
						setState(814);
						newType(0);
						}
						break;
					}
					} 
				}
				setState(819);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
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
		enterRule(_localctx, 102, RULE_assignExpression);
		int _la;
		try {
			setState(835);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
			case 1:
				_localctx = new ExAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(820);
				expression(0);
				setState(825);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(821);
					match(T__1);
					setState(822);
					expression(0);
					}
					}
					setState(827);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(828);
				match(T__7);
				setState(829);
				((ExAssignContext)_localctx).rightExpression = expression(0);
				}
				break;
			case 2:
				_localctx = new ExOpAssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(831);
				((ExOpAssignContext)_localctx).leftExpression = expression(0);
				setState(832);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 548682072064L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(833);
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
		enterRule(_localctx, 104, RULE_expressionTuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(837);
			expression(0);
			setState(842);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(838);
				match(T__1);
				setState(839);
				expression(0);
				}
				}
				setState(844);
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
		enterRule(_localctx, 106, RULE_extend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(845);
			match(EXTENDS);
			setState(846);
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
		public ParameterDefinitionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDefinitions; }
	}

	public final ParameterDefinitionsContext parameterDefinitions() throws RecognitionException {
		ParameterDefinitionsContext _localctx = new ParameterDefinitionsContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_parameterDefinitions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(848);
			parameterDefinition();
			setState(853);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(849);
				match(T__1);
				setState(850);
				parameterDefinition();
				}
				}
				setState(855);
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
	public static class ParameterDefinitionContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public TagsContext tags() {
			return getRuleContext(TagsContext.class,0);
		}
		public TerminalNode STAR() { return getToken(caffcParser.STAR, 0); }
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
		enterRule(_localctx, 110, RULE_parameterDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(857);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(856);
				tags();
				}
			}

			setState(859);
			typeName(0);
			setState(860);
			match(ID);
			setState(862);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(861);
				match(STAR);
				}
			}

			setState(866);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(864);
				match(T__7);
				setState(865);
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
		int _startState = 112;
		enterRecursionRule(_localctx, 112, RULE_typeName, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(872);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new TypeClassContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(869);
				classType();
				}
				break;
			case T__38:
			case T__39:
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
				_localctx = new TypePrimitiveContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(870);
				primitiveTypeName();
				}
				break;
			case FN:
				{
				_localctx = new TypeFunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(871);
				functionType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(883);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeArrayContext(new TypeNameContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_typeName);
					setState(874);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(877); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(875);
							match(T__8);
							setState(876);
							match(T__9);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(879); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,104,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(885);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
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
		int _startState = 114;
		enterRecursionRule(_localctx, 114, RULE_newType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(889);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new NewTypeClassContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(887);
				classType();
				}
				break;
			case T__38:
			case T__39:
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
				setState(888);
				primitiveTypeName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(900);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,108,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NewTypeArrayContext(new NewTypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_newType);
					setState(891);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(894); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(892);
							match(T__8);
							setState(893);
							match(T__9);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(896); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,107,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(902);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,108,_ctx);
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
		enterRule(_localctx, 116, RULE_primitiveTypeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(903);
			_la = _input.LA(1);
			if ( !(((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & 143974450587500547L) != 0)) ) {
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
		enterRule(_localctx, 118, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(905);
			fqdn(0);
			setState(907);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				{
				setState(906);
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
		enterRule(_localctx, 120, RULE_genericsDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(909);
			match(T__18);
			setState(910);
			genericDeclaration();
			setState(915);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(911);
				match(T__1);
				setState(912);
				genericDeclaration();
				}
				}
				setState(917);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(918);
			match(T__21);
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
		enterRule(_localctx, 122, RULE_genericDeclaration);
		try {
			setState(924);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
			case 1:
				_localctx = new GenericDeclarationIdContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(920);
				match(ID);
				}
				break;
			case 2:
				_localctx = new GenericDeclarationRestrictedContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(921);
				match(ID);
				setState(922);
				match(IS);
				setState(923);
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
		enterRule(_localctx, 124, RULE_genericsInstantiations);
		int _la;
		try {
			setState(939);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
			case 1:
				_localctx = new GenericsInstantiationEmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(926);
				match(T__18);
				setState(927);
				match(T__21);
				}
				break;
			case 2:
				_localctx = new GenericsInstantiationTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(928);
				match(T__18);
				setState(929);
				typeName(0);
				setState(934);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(930);
					match(T__1);
					setState(931);
					typeName(0);
					}
					}
					setState(936);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(937);
				match(T__21);
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
		enterRule(_localctx, 126, RULE_functionType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(941);
			match(FN);
			setState(943);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
			case 1:
				{
				setState(942);
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
		enterRule(_localctx, 128, RULE_namedTypeTuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(945);
			typeName(0);
			setState(946);
			match(ID);
			setState(953);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(947);
				match(T__1);
				setState(948);
				typeName(0);
				setState(949);
				match(ID);
				}
				}
				setState(955);
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
		case 56:
			return typeName_sempred((TypeNameContext)_localctx, predIndex);
		case 57:
			return newType_sempred((NewTypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean fqdn_sempred(FqdnContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 12);
		case 2:
			return precpred(_ctx, 11);
		case 3:
			return precpred(_ctx, 10);
		case 4:
			return precpred(_ctx, 9);
		case 5:
			return precpred(_ctx, 8);
		case 6:
			return precpred(_ctx, 7);
		case 7:
			return precpred(_ctx, 6);
		case 8:
			return precpred(_ctx, 5);
		case 9:
			return precpred(_ctx, 4);
		case 10:
			return precpred(_ctx, 3);
		case 11:
			return precpred(_ctx, 2);
		case 12:
			return precpred(_ctx, 1);
		case 13:
			return precpred(_ctx, 23);
		case 14:
			return precpred(_ctx, 20);
		case 15:
			return precpred(_ctx, 19);
		case 16:
			return precpred(_ctx, 16);
		}
		return true;
	}
	private boolean typeName_sempred(TypeNameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 17:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean newType_sempred(NewTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 18:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001i\u03bd\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0001"+
		"\u0000\u0001\u0000\u0005\u0000\u0085\b\u0000\n\u0000\f\u0000\u0088\t\u0000"+
		"\u0001\u0000\u0005\u0000\u008b\b\u0000\n\u0000\f\u0000\u008e\t\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0003\u0002\u0095"+
		"\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u009f\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00a7"+
		"\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\b\u0003\b\u00b2\b\b\u0001\b\u0003"+
		"\b\u00b5\b\b\u0001\b\u0001\b\u0003\b\u00b9\b\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0003\b\u00bf\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u00c4\b\b\u0003"+
		"\b\u00c6\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u00cb\b\b\u0001\b\u0003\b"+
		"\u00ce\b\b\u0001\b\u0001\b\u0003\b\u00d2\b\b\u0001\b\u0001\b\u0003\b\u00d6"+
		"\b\b\u0001\b\u0001\b\u0001\b\u0003\b\u00db\b\b\u0003\b\u00dd\b\b\u0001"+
		"\b\u0003\b\u00e0\b\b\u0001\t\u0001\t\u0005\t\u00e4\b\t\n\t\f\t\u00e7\t"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0003\n\u00ee\b\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0004\u000b\u00f4\b\u000b\u000b\u000b"+
		"\f\u000b\u00f5\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0005\f\u0100\b\f\n\f\f\f\u0103\t\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u010c\b\f\n\f\f\f\u010f\t\f"+
		"\u0001\f\u0003\f\u0112\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0004\r\u0118"+
		"\b\r\u000b\r\f\r\u0119\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0124\b\u000e\n\u000e"+
		"\f\u000e\u0127\t\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0130\b\u000e\n\u000e"+
		"\f\u000e\u0133\t\u000e\u0001\u000e\u0003\u000e\u0136\b\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u013d\b\u000f"+
		"\n\u000f\f\u000f\u0140\t\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0005\u000f\u0147\b\u000f\n\u000f\f\u000f\u014a\t\u000f"+
		"\u0001\u000f\u0003\u000f\u014d\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0005\u0010\u0154\b\u0010\n\u0010\f\u0010\u0157"+
		"\t\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005"+
		"\u0010\u015e\b\u0010\n\u0010\f\u0010\u0161\t\u0010\u0001\u0010\u0003\u0010"+
		"\u0164\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0169\b"+
		"\u0011\u0001\u0012\u0003\u0012\u016c\b\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u0171\b\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0175"+
		"\b\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0179\b\u0012\n\u0012\f\u0012"+
		"\u017c\t\u0012\u0001\u0012\u0005\u0012\u017f\b\u0012\n\u0012\f\u0012\u0182"+
		"\t\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0003\u0013\u0187\b\u0013"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u018c\b\u0013\u0001\u0013"+
		"\u0001\u0013\u0003\u0013\u0190\b\u0013\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0005\u0015\u019b\b\u0015\n\u0015\f\u0015\u019e\t\u0015\u0001\u0016\u0001"+
		"\u0016\u0003\u0016\u01a2\b\u0016\u0001\u0017\u0005\u0017\u01a5\b\u0017"+
		"\n\u0017\f\u0017\u01a8\t\u0017\u0001\u0018\u0003\u0018\u01ab\b\u0018\u0001"+
		"\u0018\u0001\u0018\u0003\u0018\u01af\b\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0003\u0018\u01b5\b\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u01ba\b\u0018\u0003\u0018\u01bc\b\u0018\u0001\u0018"+
		"\u0003\u0018\u01bf\b\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01c3\b"+
		"\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u01c7\b\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0003\u0018\u01cc\b\u0018\u0003\u0018\u01ce\b\u0018"+
		"\u0003\u0018\u01d0\b\u0018\u0001\u0019\u0003\u0019\u01d3\b\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0005\u0019\u01d9\b\u0019\n"+
		"\u0019\f\u0019\u01dc\t\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0003"+
		"\u001a\u01e1\b\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0005"+
		"\u001a\u01e7\b\u001a\n\u001a\f\u001a\u01ea\t\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b"+
		"\u01f9\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d"+
		"\u0005\u001d\u0200\b\u001d\n\u001d\f\u001d\u0203\t\u001d\u0001\u001d\u0001"+
		"\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0003\u001f\u020e\b\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0215\b\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0003\u001f\u0220\b\u001f\u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0003 \u0227\b \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0003"+
		" \u022f\b \u0001!\u0001!\u0001!\u0004!\u0234\b!\u000b!\f!\u0235\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0004!\u023e\b!\u000b!\f!\u023f\u0001"+
		"!\u0001!\u0003!\u0244\b!\u0001\"\u0001\"\u0001\"\u0003\"\u0249\b\"\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0003#\u0253\b#\u0001"+
		"$\u0001$\u0001$\u0005$\u0258\b$\n$\f$\u025b\t$\u0001$\u0003$\u025e\b$"+
		"\u0001$\u0001$\u0001$\u0001$\u0001$\u0003$\u0265\b$\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0005\'\u0275\b\'\n\'\f\'\u0278\t\'\u0001\'\u0003\'\u027b\b"+
		"\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0003(\u0285"+
		"\b(\u0001)\u0001)\u0001*\u0001*\u0001*\u0001*\u0005*\u028d\b*\n*\f*\u0290"+
		"\t*\u0001+\u0001+\u0001+\u0003+\u0295\b+\u0001,\u0001,\u0003,\u0299\b"+
		",\u0001-\u0004-\u029c\b-\u000b-\f-\u029d\u0001.\u0001.\u0001.\u0001.\u0001"+
		".\u0001.\u0001.\u0001.\u0005.\u02a8\b.\n.\f.\u02ab\t.\u0001.\u0001.\u0003"+
		".\u02af\b.\u0001/\u0001/\u0001/\u0001/\u0003/\u02b5\b/\u00010\u00010\u0001"+
		"1\u00011\u00011\u00011\u00011\u00011\u00051\u02bf\b1\n1\f1\u02c2\t1\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00032\u02d0\b2\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00042\u02da\b2\u000b2\f2\u02db\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00032\u02ed\b2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00032\u0316\b2\u00012\u00012\u00012\u00012\u00012\u00012\u0003"+
		"2\u031e\b2\u00012\u00012\u00032\u0322\b2\u00012\u00012\u00012\u00012\u0001"+
		"2\u00012\u00012\u00012\u00032\u032c\b2\u00012\u00012\u00052\u0330\b2\n"+
		"2\f2\u0333\t2\u00013\u00013\u00013\u00053\u0338\b3\n3\f3\u033b\t3\u0001"+
		"3\u00013\u00013\u00013\u00013\u00013\u00013\u00033\u0344\b3\u00014\u0001"+
		"4\u00014\u00054\u0349\b4\n4\f4\u034c\t4\u00015\u00015\u00015\u00016\u0001"+
		"6\u00016\u00056\u0354\b6\n6\f6\u0357\t6\u00017\u00037\u035a\b7\u00017"+
		"\u00017\u00017\u00037\u035f\b7\u00017\u00017\u00037\u0363\b7\u00018\u0001"+
		"8\u00018\u00018\u00038\u0369\b8\u00018\u00018\u00018\u00048\u036e\b8\u000b"+
		"8\f8\u036f\u00058\u0372\b8\n8\f8\u0375\t8\u00019\u00019\u00019\u00039"+
		"\u037a\b9\u00019\u00019\u00019\u00049\u037f\b9\u000b9\f9\u0380\u00059"+
		"\u0383\b9\n9\f9\u0386\t9\u0001:\u0001:\u0001;\u0001;\u0003;\u038c\b;\u0001"+
		"<\u0001<\u0001<\u0001<\u0005<\u0392\b<\n<\f<\u0395\t<\u0001<\u0001<\u0001"+
		"=\u0001=\u0001=\u0001=\u0003=\u039d\b=\u0001>\u0001>\u0001>\u0001>\u0001"+
		">\u0001>\u0005>\u03a5\b>\n>\f>\u03a8\t>\u0001>\u0001>\u0003>\u03ac\b>"+
		"\u0001?\u0001?\u0003?\u03b0\b?\u0001@\u0001@\u0001@\u0001@\u0001@\u0001"+
		"@\u0005@\u03b8\b@\n@\f@\u03bb\t@\u0001@\u0000\u0004bdprA\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \""+
		"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0000\n\u0002\u0000"+
		"3377\u0002\u0000)*hh\u0002\u0000\u000b\u000bFF\u0002\u0000\u000e\u000e"+
		"ee\u0002\u0000\r\r\u0010\u0010\u0001\u0000\u0011\u0012\u0001\u0000\u0013"+
		"\u0016\u0001\u0000\u0017\u0018\u0001\u0000\u001e&\u0002\u0000\'(V_\u0423"+
		"\u0000\u0082\u0001\u0000\u0000\u0000\u0002\u008f\u0001\u0000\u0000\u0000"+
		"\u0004\u0092\u0001\u0000\u0000\u0000\u0006\u009e\u0001\u0000\u0000\u0000"+
		"\b\u00a6\u0001\u0000\u0000\u0000\n\u00a8\u0001\u0000\u0000\u0000\f\u00ab"+
		"\u0001\u0000\u0000\u0000\u000e\u00ae\u0001\u0000\u0000\u0000\u0010\u00df"+
		"\u0001\u0000\u0000\u0000\u0012\u00e1\u0001\u0000\u0000\u0000\u0014\u00ed"+
		"\u0001\u0000\u0000\u0000\u0016\u00ef\u0001\u0000\u0000\u0000\u0018\u0111"+
		"\u0001\u0000\u0000\u0000\u001a\u0113\u0001\u0000\u0000\u0000\u001c\u0135"+
		"\u0001\u0000\u0000\u0000\u001e\u0137\u0001\u0000\u0000\u0000 \u014e\u0001"+
		"\u0000\u0000\u0000\"\u0168\u0001\u0000\u0000\u0000$\u016b\u0001\u0000"+
		"\u0000\u0000&\u0186\u0001\u0000\u0000\u0000(\u0195\u0001\u0000\u0000\u0000"+
		"*\u0197\u0001\u0000\u0000\u0000,\u019f\u0001\u0000\u0000\u0000.\u01a6"+
		"\u0001\u0000\u0000\u00000\u01cf\u0001\u0000\u0000\u00002\u01d2\u0001\u0000"+
		"\u0000\u00004\u01e0\u0001\u0000\u0000\u00006\u01f8\u0001\u0000\u0000\u0000"+
		"8\u01fa\u0001\u0000\u0000\u0000:\u01fd\u0001\u0000\u0000\u0000<\u0206"+
		"\u0001\u0000\u0000\u0000>\u021f\u0001\u0000\u0000\u0000@\u022e\u0001\u0000"+
		"\u0000\u0000B\u0243\u0001\u0000\u0000\u0000D\u0248\u0001\u0000\u0000\u0000"+
		"F\u0252\u0001\u0000\u0000\u0000H\u0264\u0001\u0000\u0000\u0000J\u0266"+
		"\u0001\u0000\u0000\u0000L\u026d\u0001\u0000\u0000\u0000N\u027a\u0001\u0000"+
		"\u0000\u0000P\u0284\u0001\u0000\u0000\u0000R\u0286\u0001\u0000\u0000\u0000"+
		"T\u0288\u0001\u0000\u0000\u0000V\u0291\u0001\u0000\u0000\u0000X\u0298"+
		"\u0001\u0000\u0000\u0000Z\u029b\u0001\u0000\u0000\u0000\\\u02ae\u0001"+
		"\u0000\u0000\u0000^\u02b4\u0001\u0000\u0000\u0000`\u02b6\u0001\u0000\u0000"+
		"\u0000b\u02b8\u0001\u0000\u0000\u0000d\u02ec\u0001\u0000\u0000\u0000f"+
		"\u0343\u0001\u0000\u0000\u0000h\u0345\u0001\u0000\u0000\u0000j\u034d\u0001"+
		"\u0000\u0000\u0000l\u0350\u0001\u0000\u0000\u0000n\u0359\u0001\u0000\u0000"+
		"\u0000p\u0368\u0001\u0000\u0000\u0000r\u0379\u0001\u0000\u0000\u0000t"+
		"\u0387\u0001\u0000\u0000\u0000v\u0389\u0001\u0000\u0000\u0000x\u038d\u0001"+
		"\u0000\u0000\u0000z\u039c\u0001\u0000\u0000\u0000|\u03ab\u0001\u0000\u0000"+
		"\u0000~\u03ad\u0001\u0000\u0000\u0000\u0080\u03b1\u0001\u0000\u0000\u0000"+
		"\u0082\u0086\u0003\u0002\u0001\u0000\u0083\u0085\u0003\u0004\u0002\u0000"+
		"\u0084\u0083\u0001\u0000\u0000\u0000\u0085\u0088\u0001\u0000\u0000\u0000"+
		"\u0086\u0084\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000"+
		"\u0087\u008c\u0001\u0000\u0000\u0000\u0088\u0086\u0001\u0000\u0000\u0000"+
		"\u0089\u008b\u0003\u0006\u0003\u0000\u008a\u0089\u0001\u0000\u0000\u0000"+
		"\u008b\u008e\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000"+
		"\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u0001\u0001\u0000\u0000\u0000"+
		"\u008e\u008c\u0001\u0000\u0000\u0000\u008f\u0090\u0005D\u0000\u0000\u0090"+
		"\u0091\u0003b1\u0000\u0091\u0003\u0001\u0000\u0000\u0000\u0092\u0094\u0003"+
		"\n\u0005\u0000\u0093\u0095\u0003\f\u0006\u0000\u0094\u0093\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u0005\u0001\u0000"+
		"\u0000\u0000\u0096\u009f\u0003\u000e\u0007\u0000\u0097\u009f\u00032\u0019"+
		"\u0000\u0098\u009f\u0003\u0010\b\u0000\u0099\u009f\u0003$\u0012\u0000"+
		"\u009a\u009f\u0003&\u0013\u0000\u009b\u009f\u0003T*\u0000\u009c\u009f"+
		"\u0003\u0016\u000b\u0000\u009d\u009f\u0003\u001e\u000f\u0000\u009e\u0096"+
		"\u0001\u0000\u0000\u0000\u009e\u0097\u0001\u0000\u0000\u0000\u009e\u0098"+
		"\u0001\u0000\u0000\u0000\u009e\u0099\u0001\u0000\u0000\u0000\u009e\u009a"+
		"\u0001\u0000\u0000\u0000\u009e\u009b\u0001\u0000\u0000\u0000\u009e\u009c"+
		"\u0001\u0000\u0000\u0000\u009e\u009d\u0001\u0000\u0000\u0000\u009f\u0007"+
		"\u0001\u0000\u0000\u0000\u00a0\u00a7\u0003\u000e\u0007\u0000\u00a1\u00a7"+
		"\u00032\u0019\u0000\u00a2\u00a7\u0003\u0010\b\u0000\u00a3\u00a7\u0003"+
		"$\u0012\u0000\u00a4\u00a7\u0003&\u0013\u0000\u00a5\u00a7\u0003T*\u0000"+
		"\u00a6\u00a0\u0001\u0000\u0000\u0000\u00a6\u00a1\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a2\u0001\u0000\u0000\u0000\u00a6\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a4\u0001\u0000\u0000\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000"+
		"\u00a7\t\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005Q\u0000\u0000\u00a9"+
		"\u00aa\u0003b1\u0000\u00aa\u000b\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005"+
		"1\u0000\u0000\u00ac\u00ad\u0005g\u0000\u0000\u00ad\r\u0001\u0000\u0000"+
		"\u0000\u00ae\u00af\u0005`\u0000\u0000\u00af\u000f\u0001\u0000\u0000\u0000"+
		"\u00b0\u00b2\u0003Z-\u0000\u00b1\u00b0\u0001\u0000\u0000\u0000\u00b1\u00b2"+
		"\u0001\u0000\u0000\u0000\u00b2\u00b4\u0001\u0000\u0000\u0000\u00b3\u00b5"+
		"\u0005L\u0000\u0000\u00b4\u00b3\u0001\u0000\u0000\u0000\u00b4\u00b5\u0001"+
		"\u0000\u0000\u0000\u00b5\u00b6\u0001\u0000\u0000\u0000\u00b6\u00b8\u0005"+
		"g\u0000\u0000\u00b7\u00b9\u0003x<\u0000\u00b8\u00b7\u0001\u0000\u0000"+
		"\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000"+
		"\u0000\u00ba\u00bb\u0005\u0001\u0000\u0000\u00bb\u00be\u0003j5\u0000\u00bc"+
		"\u00bd\u0005\u0002\u0000\u0000\u00bd\u00bf\u0003l6\u0000\u00be\u00bc\u0001"+
		"\u0000\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001"+
		"\u0000\u0000\u0000\u00c0\u00c5\u0005\u0003\u0000\u0000\u00c1\u00c3\u0005"+
		"\u0004\u0000\u0000\u00c2\u00c4\u0003\"\u0011\u0000\u00c3\u00c2\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c4\u0001\u0000\u0000\u0000\u00c4\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c1\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c8\u0003\u0012"+
		"\t\u0000\u00c8\u00e0\u0001\u0000\u0000\u0000\u00c9\u00cb\u0003Z-\u0000"+
		"\u00ca\u00c9\u0001\u0000\u0000\u0000\u00ca\u00cb\u0001\u0000\u0000\u0000"+
		"\u00cb\u00cd\u0001\u0000\u0000\u0000\u00cc\u00ce\u0005L\u0000\u0000\u00cd"+
		"\u00cc\u0001\u0000\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce"+
		"\u00cf\u0001\u0000\u0000\u0000\u00cf\u00d1\u0005g\u0000\u0000\u00d0\u00d2"+
		"\u0003x<\u0000\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u00d5\u0005\u0001"+
		"\u0000\u0000\u00d4\u00d6\u0003l6\u0000\u00d5\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d7\u0001\u0000\u0000\u0000"+
		"\u00d7\u00dc\u0005\u0003\u0000\u0000\u00d8\u00da\u0005\u0004\u0000\u0000"+
		"\u00d9\u00db\u0003\"\u0011\u0000\u00da\u00d9\u0001\u0000\u0000\u0000\u00da"+
		"\u00db\u0001\u0000\u0000\u0000\u00db\u00dd\u0001\u0000\u0000\u0000\u00dc"+
		"\u00d8\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd"+
		"\u00de\u0001\u0000\u0000\u0000\u00de\u00e0\u0003\u0012\t\u0000\u00df\u00b1"+
		"\u0001\u0000\u0000\u0000\u00df\u00ca\u0001\u0000\u0000\u0000\u00e0\u0011"+
		"\u0001\u0000\u0000\u0000\u00e1\u00e5\u0005a\u0000\u0000\u00e2\u00e4\u0003"+
		"\u0014\n\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e4\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e5\u00e6\u0001\u0000"+
		"\u0000\u0000\u00e6\u00e8\u0001\u0000\u0000\u0000\u00e7\u00e5\u0001\u0000"+
		"\u0000\u0000\u00e8\u00e9\u0005b\u0000\u0000\u00e9\u0013\u0001\u0000\u0000"+
		"\u0000\u00ea\u00ee\u00036\u001b\u0000\u00eb\u00ee\u0003\u001a\r\u0000"+
		"\u00ec\u00ee\u0003 \u0010\u0000\u00ed\u00ea\u0001\u0000\u0000\u0000\u00ed"+
		"\u00eb\u0001\u0000\u0000\u0000\u00ed\u00ec\u0001\u0000\u0000\u0000\u00ee"+
		"\u0015\u0001\u0000\u0000\u0000\u00ef\u00f0\u0005d\u0000\u0000\u00f0\u00f1"+
		"\u0005M\u0000\u0000\u00f1\u00f3\u0005a\u0000\u0000\u00f2\u00f4\u0003\u0018"+
		"\f\u0000\u00f3\u00f2\u0001\u0000\u0000\u0000\u00f4\u00f5\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f5\u00f6\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\u00f8\u0005b\u0000\u0000"+
		"\u00f8\u0017\u0001\u0000\u0000\u0000\u00f9\u00fa\u0005d\u0000\u0000\u00fa"+
		"\u00fb\u00054\u0000\u0000\u00fb\u00fc\u0003d2\u0000\u00fc\u00fd\u0005"+
		"\u0005\u0000\u0000\u00fd\u0101\u0005a\u0000\u0000\u00fe\u0100\u0003\b"+
		"\u0004\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000\u0100\u0103\u0001\u0000"+
		"\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101\u0102\u0001\u0000"+
		"\u0000\u0000\u0102\u0104\u0001\u0000\u0000\u0000\u0103\u0101\u0001\u0000"+
		"\u0000\u0000\u0104\u0105\u0005b\u0000\u0000\u0105\u0112\u0001\u0000\u0000"+
		"\u0000\u0106\u0107\u0005d\u0000\u0000\u0107\u0108\u00058\u0000\u0000\u0108"+
		"\u0109\u0005\u0005\u0000\u0000\u0109\u010d\u0005a\u0000\u0000\u010a\u010c"+
		"\u0003\b\u0004\u0000\u010b\u010a\u0001\u0000\u0000\u0000\u010c\u010f\u0001"+
		"\u0000\u0000\u0000\u010d\u010b\u0001\u0000\u0000\u0000\u010d\u010e\u0001"+
		"\u0000\u0000\u0000\u010e\u0110\u0001\u0000\u0000\u0000\u010f\u010d\u0001"+
		"\u0000\u0000\u0000\u0110\u0112\u0005b\u0000\u0000\u0111\u00f9\u0001\u0000"+
		"\u0000\u0000\u0111\u0106\u0001\u0000\u0000\u0000\u0112\u0019\u0001\u0000"+
		"\u0000\u0000\u0113\u0114\u0005d\u0000\u0000\u0114\u0115\u0005M\u0000\u0000"+
		"\u0115\u0117\u0005a\u0000\u0000\u0116\u0118\u0003\u001c\u000e\u0000\u0117"+
		"\u0116\u0001\u0000\u0000\u0000\u0118\u0119\u0001\u0000\u0000\u0000\u0119"+
		"\u0117\u0001\u0000\u0000\u0000\u0119\u011a\u0001\u0000\u0000\u0000\u011a"+
		"\u011b\u0001\u0000\u0000\u0000\u011b\u011c\u0005b\u0000\u0000\u011c\u001b"+
		"\u0001\u0000\u0000\u0000\u011d\u011e\u0005d\u0000\u0000\u011e\u011f\u0005"+
		"4\u0000\u0000\u011f\u0120\u0003d2\u0000\u0120\u0121\u0005\u0005\u0000"+
		"\u0000\u0121\u0125\u0005a\u0000\u0000\u0122\u0124\u00036\u001b\u0000\u0123"+
		"\u0122\u0001\u0000\u0000\u0000\u0124\u0127\u0001\u0000\u0000\u0000\u0125"+
		"\u0123\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000\u0000\u0000\u0126"+
		"\u0128\u0001\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000\u0000\u0128"+
		"\u0129\u0005b\u0000\u0000\u0129\u0136\u0001\u0000\u0000\u0000\u012a\u012b"+
		"\u0005d\u0000\u0000\u012b\u012c\u00058\u0000\u0000\u012c\u012d\u0005\u0005"+
		"\u0000\u0000\u012d\u0131\u0005a\u0000\u0000\u012e\u0130\u00036\u001b\u0000"+
		"\u012f\u012e\u0001\u0000\u0000\u0000\u0130\u0133\u0001\u0000\u0000\u0000"+
		"\u0131\u012f\u0001\u0000\u0000\u0000\u0131\u0132\u0001\u0000\u0000\u0000"+
		"\u0132\u0134\u0001\u0000\u0000\u0000\u0133\u0131\u0001\u0000\u0000\u0000"+
		"\u0134\u0136\u0005b\u0000\u0000\u0135\u011d\u0001\u0000\u0000\u0000\u0135"+
		"\u012a\u0001\u0000\u0000\u0000\u0136\u001d\u0001\u0000\u0000\u0000\u0137"+
		"\u0138\u0005d\u0000\u0000\u0138\u0139\u0005>\u0000\u0000\u0139\u013a\u0003"+
		"d2\u0000\u013a\u013e\u0005a\u0000\u0000\u013b\u013d\u0003\b\u0004\u0000"+
		"\u013c\u013b\u0001\u0000\u0000\u0000\u013d\u0140\u0001\u0000\u0000\u0000"+
		"\u013e\u013c\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000"+
		"\u013f\u0141\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000"+
		"\u0141\u014c\u0005b\u0000\u0000\u0142\u0143\u0005d\u0000\u0000\u0143\u0144"+
		"\u00059\u0000\u0000\u0144\u0148\u0005a\u0000\u0000\u0145\u0147\u0003\b"+
		"\u0004\u0000\u0146\u0145\u0001\u0000\u0000\u0000\u0147\u014a\u0001\u0000"+
		"\u0000\u0000\u0148\u0146\u0001\u0000\u0000\u0000\u0148\u0149\u0001\u0000"+
		"\u0000\u0000\u0149\u014b\u0001\u0000\u0000\u0000\u014a\u0148\u0001\u0000"+
		"\u0000\u0000\u014b\u014d\u0005b\u0000\u0000\u014c\u0142\u0001\u0000\u0000"+
		"\u0000\u014c\u014d\u0001\u0000\u0000\u0000\u014d\u001f\u0001\u0000\u0000"+
		"\u0000\u014e\u014f\u0005d\u0000\u0000\u014f\u0150\u0005>\u0000\u0000\u0150"+
		"\u0151\u0003d2\u0000\u0151\u0155\u0005a\u0000\u0000\u0152\u0154\u0003"+
		"6\u001b\u0000\u0153\u0152\u0001\u0000\u0000\u0000\u0154\u0157\u0001\u0000"+
		"\u0000\u0000\u0155\u0153\u0001\u0000\u0000\u0000\u0155\u0156\u0001\u0000"+
		"\u0000\u0000\u0156\u0158\u0001\u0000\u0000\u0000\u0157\u0155\u0001\u0000"+
		"\u0000\u0000\u0158\u0163\u0005b\u0000\u0000\u0159\u015a\u0005d\u0000\u0000"+
		"\u015a\u015b\u00059\u0000\u0000\u015b\u015f\u0005a\u0000\u0000\u015c\u015e"+
		"\u00036\u001b\u0000\u015d\u015c\u0001\u0000\u0000\u0000\u015e\u0161\u0001"+
		"\u0000\u0000\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u015f\u0160\u0001"+
		"\u0000\u0000\u0000\u0160\u0162\u0001\u0000\u0000\u0000\u0161\u015f\u0001"+
		"\u0000\u0000\u0000\u0162\u0164\u0005b\u0000\u0000\u0163\u0159\u0001\u0000"+
		"\u0000\u0000\u0163\u0164\u0001\u0000\u0000\u0000\u0164!\u0001\u0000\u0000"+
		"\u0000\u0165\u0169\u0003\u0080@\u0000\u0166\u0169\u0003p8\u0000\u0167"+
		"\u0169\u0005S\u0000\u0000\u0168\u0165\u0001\u0000\u0000\u0000\u0168\u0166"+
		"\u0001\u0000\u0000\u0000\u0168\u0167\u0001\u0000\u0000\u0000\u0169#\u0001"+
		"\u0000\u0000\u0000\u016a\u016c\u0003Z-\u0000\u016b\u016a\u0001\u0000\u0000"+
		"\u0000\u016b\u016c\u0001\u0000\u0000\u0000\u016c\u016d\u0001\u0000\u0000"+
		"\u0000\u016d\u016e\u00056\u0000\u0000\u016e\u0170\u0003(\u0014\u0000\u016f"+
		"\u0171\u0003x<\u0000\u0170\u016f\u0001\u0000\u0000\u0000\u0170\u0171\u0001"+
		"\u0000\u0000\u0000\u0171\u0174\u0001\u0000\u0000\u0000\u0172\u0173\u0005"+
		"?\u0000\u0000\u0173\u0175\u0003*\u0015\u0000\u0174\u0172\u0001\u0000\u0000"+
		"\u0000\u0174\u0175\u0001\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000"+
		"\u0000\u0176\u017a\u0005a\u0000\u0000\u0177\u0179\u00034\u001a\u0000\u0178"+
		"\u0177\u0001\u0000\u0000\u0000\u0179\u017c\u0001\u0000\u0000\u0000\u017a"+
		"\u0178\u0001\u0000\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000\u017b"+
		"\u0180\u0001\u0000\u0000\u0000\u017c\u017a\u0001\u0000\u0000\u0000\u017d"+
		"\u017f\u0003\u0010\b\u0000\u017e\u017d\u0001\u0000\u0000\u0000\u017f\u0182"+
		"\u0001\u0000\u0000\u0000\u0180\u017e\u0001\u0000\u0000\u0000\u0180\u0181"+
		"\u0001\u0000\u0000\u0000\u0181\u0183\u0001\u0000\u0000\u0000\u0182\u0180"+
		"\u0001\u0000\u0000\u0000\u0183\u0184\u0005b\u0000\u0000\u0184%\u0001\u0000"+
		"\u0000\u0000\u0185\u0187\u0003Z-\u0000\u0186\u0185\u0001\u0000\u0000\u0000"+
		"\u0186\u0187\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000\u0000\u0000"+
		"\u0188\u0189\u0005B\u0000\u0000\u0189\u018b\u0003(\u0014\u0000\u018a\u018c"+
		"\u0003x<\u0000\u018b\u018a\u0001\u0000\u0000\u0000\u018b\u018c\u0001\u0000"+
		"\u0000\u0000\u018c\u018f\u0001\u0000\u0000\u0000\u018d\u018e\u0005:\u0000"+
		"\u0000\u018e\u0190\u0003*\u0015\u0000\u018f\u018d\u0001\u0000\u0000\u0000"+
		"\u018f\u0190\u0001\u0000\u0000\u0000\u0190\u0191\u0001\u0000\u0000\u0000"+
		"\u0191\u0192\u0005a\u0000\u0000\u0192\u0193\u0003.\u0017\u0000\u0193\u0194"+
		"\u0005b\u0000\u0000\u0194\'\u0001\u0000\u0000\u0000\u0195\u0196\u0005"+
		"g\u0000\u0000\u0196)\u0001\u0000\u0000\u0000\u0197\u019c\u0003,\u0016"+
		"\u0000\u0198\u0199\u0005\u0002\u0000\u0000\u0199\u019b\u0003,\u0016\u0000"+
		"\u019a\u0198\u0001\u0000\u0000\u0000\u019b\u019e\u0001\u0000\u0000\u0000"+
		"\u019c\u019a\u0001\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000\u0000"+
		"\u019d+\u0001\u0000\u0000\u0000\u019e\u019c\u0001\u0000\u0000\u0000\u019f"+
		"\u01a1\u0003b1\u0000\u01a0\u01a2\u0003|>\u0000\u01a1\u01a0\u0001\u0000"+
		"\u0000\u0000\u01a1\u01a2\u0001\u0000\u0000\u0000\u01a2-\u0001\u0000\u0000"+
		"\u0000\u01a3\u01a5\u00030\u0018\u0000\u01a4\u01a3\u0001\u0000\u0000\u0000"+
		"\u01a5\u01a8\u0001\u0000\u0000\u0000\u01a6\u01a4\u0001\u0000\u0000\u0000"+
		"\u01a6\u01a7\u0001\u0000\u0000\u0000\u01a7/\u0001\u0000\u0000\u0000\u01a8"+
		"\u01a6\u0001\u0000\u0000\u0000\u01a9\u01ab\u0003Z-\u0000\u01aa\u01a9\u0001"+
		"\u0000\u0000\u0000\u01aa\u01ab\u0001\u0000\u0000\u0000\u01ab\u01ac\u0001"+
		"\u0000\u0000\u0000\u01ac\u01ae\u0005g\u0000\u0000\u01ad\u01af\u0003x<"+
		"\u0000\u01ae\u01ad\u0001\u0000\u0000\u0000\u01ae\u01af\u0001\u0000\u0000"+
		"\u0000\u01af\u01b0\u0001\u0000\u0000\u0000\u01b0\u01b1\u0005\u0001\u0000"+
		"\u0000\u01b1\u01b4\u0003j5\u0000\u01b2\u01b3\u0005\u0002\u0000\u0000\u01b3"+
		"\u01b5\u0003l6\u0000\u01b4\u01b2\u0001\u0000\u0000\u0000\u01b4\u01b5\u0001"+
		"\u0000\u0000\u0000\u01b5\u01b6\u0001\u0000\u0000\u0000\u01b6\u01bb\u0005"+
		"\u0003\u0000\u0000\u01b7\u01b9\u0005\u0004\u0000\u0000\u01b8\u01ba\u0003"+
		"\"\u0011\u0000\u01b9\u01b8\u0001\u0000\u0000\u0000\u01b9\u01ba\u0001\u0000"+
		"\u0000\u0000\u01ba\u01bc\u0001\u0000\u0000\u0000\u01bb\u01b7\u0001\u0000"+
		"\u0000\u0000\u01bb\u01bc\u0001\u0000\u0000\u0000\u01bc\u01d0\u0001\u0000"+
		"\u0000\u0000\u01bd\u01bf\u0003Z-\u0000\u01be\u01bd\u0001\u0000\u0000\u0000"+
		"\u01be\u01bf\u0001\u0000\u0000\u0000\u01bf\u01c0\u0001\u0000\u0000\u0000"+
		"\u01c0\u01c2\u0005g\u0000\u0000\u01c1\u01c3\u0003x<\u0000\u01c2\u01c1"+
		"\u0001\u0000\u0000\u0000\u01c2\u01c3\u0001\u0000\u0000\u0000\u01c3\u01c4"+
		"\u0001\u0000\u0000\u0000\u01c4\u01c6\u0005\u0001\u0000\u0000\u01c5\u01c7"+
		"\u0003l6\u0000\u01c6\u01c5\u0001\u0000\u0000\u0000\u01c6\u01c7\u0001\u0000"+
		"\u0000\u0000\u01c7\u01c8\u0001\u0000\u0000\u0000\u01c8\u01cd\u0005\u0003"+
		"\u0000\u0000\u01c9\u01cb\u0005\u0004\u0000\u0000\u01ca\u01cc\u0003\"\u0011"+
		"\u0000\u01cb\u01ca\u0001\u0000\u0000\u0000\u01cb\u01cc\u0001\u0000\u0000"+
		"\u0000\u01cc\u01ce\u0001\u0000\u0000\u0000\u01cd\u01c9\u0001\u0000\u0000"+
		"\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce\u01d0\u0001\u0000\u0000"+
		"\u0000\u01cf\u01aa\u0001\u0000\u0000\u0000\u01cf\u01be\u0001\u0000\u0000"+
		"\u0000\u01d01\u0001\u0000\u0000\u0000\u01d1\u01d3\u0003Z-\u0000\u01d2"+
		"\u01d1\u0001\u0000\u0000\u0000\u01d2\u01d3\u0001\u0000\u0000\u0000\u01d3"+
		"\u01d4\u0001\u0000\u0000\u0000\u01d4\u01d5\u0005N\u0000\u0000\u01d5\u01d6"+
		"\u0005g\u0000\u0000\u01d6\u01da\u0005a\u0000\u0000\u01d7\u01d9\u00034"+
		"\u001a\u0000\u01d8\u01d7\u0001\u0000\u0000\u0000\u01d9\u01dc\u0001\u0000"+
		"\u0000\u0000\u01da\u01d8\u0001\u0000\u0000\u0000\u01da\u01db\u0001\u0000"+
		"\u0000\u0000\u01db\u01dd\u0001\u0000\u0000\u0000\u01dc\u01da\u0001\u0000"+
		"\u0000\u0000\u01dd\u01de\u0005b\u0000\u0000\u01de3\u0001\u0000\u0000\u0000"+
		"\u01df\u01e1\u0003Z-\u0000\u01e0\u01df\u0001\u0000\u0000\u0000\u01e0\u01e1"+
		"\u0001\u0000\u0000\u0000\u01e1\u01e2\u0001\u0000\u0000\u0000\u01e2\u01e3"+
		"\u0003p8\u0000\u01e3\u01e8\u0005g\u0000\u0000\u01e4\u01e5\u0005\u0002"+
		"\u0000\u0000\u01e5\u01e7\u0005g\u0000\u0000\u01e6\u01e4\u0001\u0000\u0000"+
		"\u0000\u01e7\u01ea\u0001\u0000\u0000\u0000\u01e8\u01e6\u0001\u0000\u0000"+
		"\u0000\u01e8\u01e9\u0001\u0000\u0000\u0000\u01e95\u0001\u0000\u0000\u0000"+
		"\u01ea\u01e8\u0001\u0000\u0000\u0000\u01eb\u01f9\u0003\u0010\b\u0000\u01ec"+
		"\u01f9\u0003T*\u0000\u01ed\u01f9\u0003N\'\u0000\u01ee\u01f9\u0003R)\u0000"+
		"\u01ef\u01f9\u0003\u000e\u0007\u0000\u01f0\u01f9\u0003<\u001e\u0000\u01f1"+
		"\u01f9\u0003>\u001f\u0000\u01f2\u01f9\u0003@ \u0000\u01f3\u01f9\u0003"+
		"B!\u0000\u01f4\u01f9\u0003H$\u0000\u01f5\u01f9\u00038\u001c\u0000\u01f6"+
		"\u01f9\u0003d2\u0000\u01f7\u01f9\u0003f3\u0000\u01f8\u01eb\u0001\u0000"+
		"\u0000\u0000\u01f8\u01ec\u0001\u0000\u0000\u0000\u01f8\u01ed\u0001\u0000"+
		"\u0000\u0000\u01f8\u01ee\u0001\u0000\u0000\u0000\u01f8\u01ef\u0001\u0000"+
		"\u0000\u0000\u01f8\u01f0\u0001\u0000\u0000\u0000\u01f8\u01f1\u0001\u0000"+
		"\u0000\u0000\u01f8\u01f2\u0001\u0000\u0000\u0000\u01f8\u01f3\u0001\u0000"+
		"\u0000\u0000\u01f8\u01f4\u0001\u0000\u0000\u0000\u01f8\u01f5\u0001\u0000"+
		"\u0000\u0000\u01f8\u01f6\u0001\u0000\u0000\u0000\u01f8\u01f7\u0001\u0000"+
		"\u0000\u0000\u01f97\u0001\u0000\u0000\u0000\u01fa\u01fb\u0005O\u0000\u0000"+
		"\u01fb\u01fc\u0003d2\u0000\u01fc9\u0001\u0000\u0000\u0000\u01fd\u0201"+
		"\u0005a\u0000\u0000\u01fe\u0200\u00036\u001b\u0000\u01ff\u01fe\u0001\u0000"+
		"\u0000\u0000\u0200\u0203\u0001\u0000\u0000\u0000\u0201\u01ff\u0001\u0000"+
		"\u0000\u0000\u0201\u0202\u0001\u0000\u0000\u0000\u0202\u0204\u0001\u0000"+
		"\u0000\u0000\u0203\u0201\u0001\u0000\u0000\u0000\u0204\u0205\u0005b\u0000"+
		"\u0000\u0205;\u0001\u0000\u0000\u0000\u0206\u0207\u0005T\u0000\u0000\u0207"+
		"\u0208\u0003d2\u0000\u0208\u0209\u0003:\u001d\u0000\u0209=\u0001\u0000"+
		"\u0000\u0000\u020a\u020d\u0005<\u0000\u0000\u020b\u020e\u0003f3\u0000"+
		"\u020c\u020e\u0003T*\u0000\u020d\u020b\u0001\u0000\u0000\u0000\u020d\u020c"+
		"\u0001\u0000\u0000\u0000\u020e\u020f\u0001\u0000\u0000\u0000\u020f\u0210"+
		"\u0005\u0006\u0000\u0000\u0210\u0211\u0003d2\u0000\u0211\u0214\u0005\u0006"+
		"\u0000\u0000\u0212\u0215\u0003d2\u0000\u0213\u0215\u0003f3\u0000\u0214"+
		"\u0212\u0001\u0000\u0000\u0000\u0214\u0213\u0001\u0000\u0000\u0000\u0215"+
		"\u0216\u0001\u0000\u0000\u0000\u0216\u0217\u0003:\u001d\u0000\u0217\u0220"+
		"\u0001\u0000\u0000\u0000\u0218\u0219\u0005<\u0000\u0000\u0219\u021a\u0003"+
		"p8\u0000\u021a\u021b\u0005g\u0000\u0000\u021b\u021c\u0005@\u0000\u0000"+
		"\u021c\u021d\u0003d2\u0000\u021d\u021e\u0003:\u001d\u0000\u021e\u0220"+
		"\u0001\u0000\u0000\u0000\u021f\u020a\u0001\u0000\u0000\u0000\u021f\u0218"+
		"\u0001\u0000\u0000\u0000\u0220?\u0001\u0000\u0000\u0000\u0221\u0222\u0005"+
		"=\u0000\u0000\u0222\u0226\u0003d2\u0000\u0223\u0227\u0003:\u001d\u0000"+
		"\u0224\u0227\u0003N\'\u0000\u0225\u0227\u0003R)\u0000\u0226\u0223\u0001"+
		"\u0000\u0000\u0000\u0226\u0224\u0001\u0000\u0000\u0000\u0226\u0225\u0001"+
		"\u0000\u0000\u0000\u0227\u022f\u0001\u0000\u0000\u0000\u0228\u0229\u0005"+
		"=\u0000\u0000\u0229\u022a\u0003d2\u0000\u022a\u022b\u0003:\u001d\u0000"+
		"\u022b\u022c\u00059\u0000\u0000\u022c\u022d\u0003:\u001d\u0000\u022d\u022f"+
		"\u0001\u0000\u0000\u0000\u022e\u0221\u0001\u0000\u0000\u0000\u022e\u0228"+
		"\u0001\u0000\u0000\u0000\u022fA\u0001\u0000\u0000\u0000\u0230\u0231\u0005"+
		"M\u0000\u0000\u0231\u0233\u0005a\u0000\u0000\u0232\u0234\u0003F#\u0000"+
		"\u0233\u0232\u0001\u0000\u0000\u0000\u0234\u0235\u0001\u0000\u0000\u0000"+
		"\u0235\u0233\u0001\u0000\u0000\u0000\u0235\u0236\u0001\u0000\u0000\u0000"+
		"\u0236\u0237\u0001\u0000\u0000\u0000\u0237\u0238\u0005b\u0000\u0000\u0238"+
		"\u0244\u0001\u0000\u0000\u0000\u0239\u023a\u0005M\u0000\u0000\u023a\u023b"+
		"\u0003d2\u0000\u023b\u023d\u0005a\u0000\u0000\u023c\u023e\u0003F#\u0000"+
		"\u023d\u023c\u0001\u0000\u0000\u0000\u023e\u023f\u0001\u0000\u0000\u0000"+
		"\u023f\u023d\u0001\u0000\u0000\u0000\u023f\u0240\u0001\u0000\u0000\u0000"+
		"\u0240\u0241\u0001\u0000\u0000\u0000\u0241\u0242\u0005b\u0000\u0000\u0242"+
		"\u0244\u0001\u0000\u0000\u0000\u0243\u0230\u0001\u0000\u0000\u0000\u0243"+
		"\u0239\u0001\u0000\u0000\u0000\u0244C\u0001\u0000\u0000\u0000\u0245\u0249"+
		"\u0003:\u001d\u0000\u0246\u0249\u0003N\'\u0000\u0247\u0249\u0003R)\u0000"+
		"\u0248\u0245\u0001\u0000\u0000\u0000\u0248\u0246\u0001\u0000\u0000\u0000"+
		"\u0248\u0247\u0001\u0000\u0000\u0000\u0249E\u0001\u0000\u0000\u0000\u024a"+
		"\u024b\u00054\u0000\u0000\u024b\u024c\u0003d2\u0000\u024c\u024d\u0005"+
		"\u0005\u0000\u0000\u024d\u024e\u0003D\"\u0000\u024e\u0253\u0001\u0000"+
		"\u0000\u0000\u024f\u0250\u00058\u0000\u0000\u0250\u0251\u0005\u0005\u0000"+
		"\u0000\u0251\u0253\u0003D\"\u0000\u0252\u024a\u0001\u0000\u0000\u0000"+
		"\u0252\u024f\u0001\u0000\u0000\u0000\u0253G\u0001\u0000\u0000\u0000\u0254"+
		"\u0255\u0005P\u0000\u0000\u0255\u0259\u0003:\u001d\u0000\u0256\u0258\u0003"+
		"J%\u0000\u0257\u0256\u0001\u0000\u0000\u0000\u0258\u025b\u0001\u0000\u0000"+
		"\u0000\u0259\u0257\u0001\u0000\u0000\u0000\u0259\u025a\u0001\u0000\u0000"+
		"\u0000\u025a\u025d\u0001\u0000\u0000\u0000\u025b\u0259\u0001\u0000\u0000"+
		"\u0000\u025c\u025e\u0003L&\u0000\u025d\u025c\u0001\u0000\u0000\u0000\u025d"+
		"\u025e\u0001\u0000\u0000\u0000\u025e\u0265\u0001\u0000\u0000\u0000\u025f"+
		"\u0260\u0005P\u0000\u0000\u0260\u0261\u0003:\u001d\u0000\u0261\u0262\u0005"+
		";\u0000\u0000\u0262\u0263\u0003:\u001d\u0000\u0263\u0265\u0001\u0000\u0000"+
		"\u0000\u0264\u0254\u0001\u0000\u0000\u0000\u0264\u025f\u0001\u0000\u0000"+
		"\u0000\u0265I\u0001\u0000\u0000\u0000\u0266\u0267\u00055\u0000\u0000\u0267"+
		"\u0268\u0005\u0001\u0000\u0000\u0268\u0269\u0003v;\u0000\u0269\u026a\u0005"+
		"g\u0000\u0000\u026a\u026b\u0005\u0003\u0000\u0000\u026b\u026c\u0003:\u001d"+
		"\u0000\u026cK\u0001\u0000\u0000\u0000\u026d\u026e\u0005;\u0000\u0000\u026e"+
		"\u026f\u0003:\u001d\u0000\u026fM\u0001\u0000\u0000\u0000\u0270\u0271\u0005"+
		"K\u0000\u0000\u0271\u0276\u0003d2\u0000\u0272\u0273\u0005\u0002\u0000"+
		"\u0000\u0273\u0275\u0003d2\u0000\u0274\u0272\u0001\u0000\u0000\u0000\u0275"+
		"\u0278\u0001\u0000\u0000\u0000\u0276\u0274\u0001\u0000\u0000\u0000\u0276"+
		"\u0277\u0001\u0000\u0000\u0000\u0277\u027b\u0001\u0000\u0000\u0000\u0278"+
		"\u0276\u0001\u0000\u0000\u0000\u0279\u027b\u0005K\u0000\u0000\u027a\u0270"+
		"\u0001\u0000\u0000\u0000\u027a\u0279\u0001\u0000\u0000\u0000\u027bO\u0001"+
		"\u0000\u0000\u0000\u027c\u027d\u0005\u0007\u0000\u0000\u027d\u027e\u0003"+
		"d2\u0000\u027e\u027f\u0003\u0010\b\u0000\u027f\u0285\u0001\u0000\u0000"+
		"\u0000\u0280\u0281\u0005\u0007\u0000\u0000\u0281\u0282\u0003d2\u0000\u0282"+
		"\u0283\u0003P(\u0000\u0283\u0285\u0001\u0000\u0000\u0000\u0284\u027c\u0001"+
		"\u0000\u0000\u0000\u0284\u0280\u0001\u0000\u0000\u0000\u0285Q\u0001\u0000"+
		"\u0000\u0000\u0286\u0287\u0007\u0000\u0000\u0000\u0287S\u0001\u0000\u0000"+
		"\u0000\u0288\u0289\u0003X,\u0000\u0289\u028e\u0003V+\u0000\u028a\u028b"+
		"\u0005\u0002\u0000\u0000\u028b\u028d\u0003V+\u0000\u028c\u028a\u0001\u0000"+
		"\u0000\u0000\u028d\u0290\u0001\u0000\u0000\u0000\u028e\u028c\u0001\u0000"+
		"\u0000\u0000\u028e\u028f\u0001\u0000\u0000\u0000\u028fU\u0001\u0000\u0000"+
		"\u0000\u0290\u028e\u0001\u0000\u0000\u0000\u0291\u0294\u0005g\u0000\u0000"+
		"\u0292\u0293\u0005\b\u0000\u0000\u0293\u0295\u0003d2\u0000\u0294\u0292"+
		"\u0001\u0000\u0000\u0000\u0294\u0295\u0001\u0000\u0000\u0000\u0295W\u0001"+
		"\u0000\u0000\u0000\u0296\u0299\u0005R\u0000\u0000\u0297\u0299\u0003p8"+
		"\u0000\u0298\u0296\u0001\u0000\u0000\u0000\u0298\u0297\u0001\u0000\u0000"+
		"\u0000\u0299Y\u0001\u0000\u0000\u0000\u029a\u029c\u0003\\.\u0000\u029b"+
		"\u029a\u0001\u0000\u0000\u0000\u029c\u029d\u0001\u0000\u0000\u0000\u029d"+
		"\u029b\u0001\u0000\u0000\u0000\u029d\u029e\u0001\u0000\u0000\u0000\u029e"+
		"[\u0001\u0000\u0000\u0000\u029f\u02a0\u0005d\u0000\u0000\u02a0\u02af\u0003"+
		"b1\u0000\u02a1\u02a2\u0005d\u0000\u0000\u02a2\u02a3\u0003b1\u0000\u02a3"+
		"\u02a4\u0005\u0001\u0000\u0000\u02a4\u02a9\u0003^/\u0000\u02a5\u02a6\u0005"+
		"\u0002\u0000\u0000\u02a6\u02a8\u0003^/\u0000\u02a7\u02a5\u0001\u0000\u0000"+
		"\u0000\u02a8\u02ab\u0001\u0000\u0000\u0000\u02a9\u02a7\u0001\u0000\u0000"+
		"\u0000\u02a9\u02aa\u0001\u0000\u0000\u0000\u02aa\u02ac\u0001\u0000\u0000"+
		"\u0000\u02ab\u02a9\u0001\u0000\u0000\u0000\u02ac\u02ad\u0005\u0003\u0000"+
		"\u0000\u02ad\u02af\u0001\u0000\u0000\u0000\u02ae\u029f\u0001\u0000\u0000"+
		"\u0000\u02ae\u02a1\u0001\u0000\u0000\u0000\u02af]\u0001\u0000\u0000\u0000"+
		"\u02b0\u02b1\u0005g\u0000\u0000\u02b1\u02b2\u0005\b\u0000\u0000\u02b2"+
		"\u02b5\u0003`0\u0000\u02b3\u02b5\u0003`0\u0000\u02b4\u02b0\u0001\u0000"+
		"\u0000\u0000\u02b4\u02b3\u0001\u0000\u0000\u0000\u02b5_\u0001\u0000\u0000"+
		"\u0000\u02b6\u02b7\u0007\u0001\u0000\u0000\u02b7a\u0001\u0000\u0000\u0000"+
		"\u02b8\u02b9\u00061\uffff\uffff\u0000\u02b9\u02ba\u0005g\u0000\u0000\u02ba"+
		"\u02c0\u0001\u0000\u0000\u0000\u02bb\u02bc\n\u0001\u0000\u0000\u02bc\u02bd"+
		"\u0005f\u0000\u0000\u02bd\u02bf\u0005g\u0000\u0000\u02be\u02bb\u0001\u0000"+
		"\u0000\u0000\u02bf\u02c2\u0001\u0000\u0000\u0000\u02c0\u02be\u0001\u0000"+
		"\u0000\u0000\u02c0\u02c1\u0001\u0000\u0000\u0000\u02c1c\u0001\u0000\u0000"+
		"\u0000\u02c2\u02c0\u0001\u0000\u0000\u0000\u02c3\u02c4\u00062\uffff\uffff"+
		"\u0000\u02c4\u02ed\u0005h\u0000\u0000\u02c5\u02ed\u0005*\u0000\u0000\u02c6"+
		"\u02ed\u0005)\u0000\u0000\u02c7\u02ed\u0005g\u0000\u0000\u02c8\u02ed\u0005"+
		"H\u0000\u0000\u02c9\u02ed\u0005J\u0000\u0000\u02ca\u02ed\u0005G\u0000"+
		"\u0000\u02cb\u02cc\u0005E\u0000\u0000\u02cc\u02cd\u0003r9\u0000\u02cd"+
		"\u02cf\u0005\u0001\u0000\u0000\u02ce\u02d0\u0003h4\u0000\u02cf\u02ce\u0001"+
		"\u0000\u0000\u0000\u02cf\u02d0\u0001\u0000\u0000\u0000\u02d0\u02d1\u0001"+
		"\u0000\u0000\u0000\u02d1\u02d2\u0005\u0003\u0000\u0000\u02d2\u02ed\u0001"+
		"\u0000\u0000\u0000\u02d3\u02d4\u0005E\u0000\u0000\u02d4\u02d9\u0003r9"+
		"\u0000\u02d5\u02d6\u0005\t\u0000\u0000\u02d6\u02d7\u0003d2\u0000\u02d7"+
		"\u02d8\u0005\n\u0000\u0000\u02d8\u02da\u0001\u0000\u0000\u0000\u02d9\u02d5"+
		"\u0001\u0000\u0000\u0000\u02da\u02db\u0001\u0000\u0000\u0000\u02db\u02d9"+
		"\u0001\u0000\u0000\u0000\u02db\u02dc\u0001\u0000\u0000\u0000\u02dc\u02ed"+
		"\u0001\u0000\u0000\u0000\u02dd\u02de\u0005\u0001\u0000\u0000\u02de\u02df"+
		"\u0003p8\u0000\u02df\u02e0\u0005\u0003\u0000\u0000\u02e0\u02e1\u0003d"+
		"2\u0012\u02e1\u02ed\u0001\u0000\u0000\u0000\u02e2\u02e3\u0005\u0001\u0000"+
		"\u0000\u02e3\u02e4\u0003d2\u0000\u02e4\u02e5\u0005\u0003\u0000\u0000\u02e5"+
		"\u02ed\u0001\u0000\u0000\u0000\u02e6\u02e7\u0007\u0002\u0000\u0000\u02e7"+
		"\u02ed\u0003d2\u000f\u02e8\u02e9\u0005\f\u0000\u0000\u02e9\u02ed\u0003"+
		"d2\u000e\u02ea\u02eb\u0005\r\u0000\u0000\u02eb\u02ed\u0003d2\r\u02ec\u02c3"+
		"\u0001\u0000\u0000\u0000\u02ec\u02c5\u0001\u0000\u0000\u0000\u02ec\u02c6"+
		"\u0001\u0000\u0000\u0000\u02ec\u02c7\u0001\u0000\u0000\u0000\u02ec\u02c8"+
		"\u0001\u0000\u0000\u0000\u02ec\u02c9\u0001\u0000\u0000\u0000\u02ec\u02ca"+
		"\u0001\u0000\u0000\u0000\u02ec\u02cb\u0001\u0000\u0000\u0000\u02ec\u02d3"+
		"\u0001\u0000\u0000\u0000\u02ec\u02dd\u0001\u0000\u0000\u0000\u02ec\u02e2"+
		"\u0001\u0000\u0000\u0000\u02ec\u02e6\u0001\u0000\u0000\u0000\u02ec\u02e8"+
		"\u0001\u0000\u0000\u0000\u02ec\u02ea\u0001\u0000\u0000\u0000\u02ed\u0331"+
		"\u0001\u0000\u0000\u0000\u02ee\u02ef\n\f\u0000\u0000\u02ef\u02f0\u0007"+
		"\u0003\u0000\u0000\u02f0\u0330\u0003d2\r\u02f1\u02f2\n\u000b\u0000\u0000"+
		"\u02f2\u02f3\u0005\u000f\u0000\u0000\u02f3\u0330\u0003d2\f\u02f4\u02f5"+
		"\n\n\u0000\u0000\u02f5\u02f6\u0007\u0004\u0000\u0000\u02f6\u0330\u0003"+
		"d2\u000b\u02f7\u02f8\n\t\u0000\u0000\u02f8\u02f9\u0007\u0005\u0000\u0000"+
		"\u02f9\u0330\u0003d2\n\u02fa\u02fb\n\b\u0000\u0000\u02fb\u02fc\u0007\u0006"+
		"\u0000\u0000\u02fc\u0330\u0003d2\t\u02fd\u02fe\n\u0007\u0000\u0000\u02fe"+
		"\u02ff\u0007\u0007\u0000\u0000\u02ff\u0330\u0003d2\b\u0300\u0301\n\u0006"+
		"\u0000\u0000\u0301\u0302\u0005\u0019\u0000\u0000\u0302\u0330\u0003d2\u0007"+
		"\u0303\u0304\n\u0005\u0000\u0000\u0304\u0305\u0005\u001a\u0000\u0000\u0305"+
		"\u0330\u0003d2\u0006\u0306\u0307\n\u0004\u0000\u0000\u0307\u0308\u0005"+
		"\u001b\u0000\u0000\u0308\u0330\u0003d2\u0005\u0309\u030a\n\u0003\u0000"+
		"\u0000\u030a\u030b\u00052\u0000\u0000\u030b\u0330\u0003d2\u0004\u030c"+
		"\u030d\n\u0002\u0000\u0000\u030d\u030e\u0005I\u0000\u0000\u030e\u0330"+
		"\u0003d2\u0003\u030f\u0315\n\u0001\u0000\u0000\u0310\u0311\u0005\u001c"+
		"\u0000\u0000\u0311\u0312\u0003d2\u0000\u0312\u0313\u0005\u0005\u0000\u0000"+
		"\u0313\u0316\u0001\u0000\u0000\u0000\u0314\u0316\u0005\u001d\u0000\u0000"+
		"\u0315\u0310\u0001\u0000\u0000\u0000\u0315\u0314\u0001\u0000\u0000\u0000"+
		"\u0316\u0317\u0001\u0000\u0000\u0000\u0317\u0330\u0003d2\u0002\u0318\u0319"+
		"\n\u0017\u0000\u0000\u0319\u031a\u0005f\u0000\u0000\u031a\u0330\u0005"+
		"g\u0000\u0000\u031b\u031d\n\u0014\u0000\u0000\u031c\u031e\u0003|>\u0000"+
		"\u031d\u031c\u0001\u0000\u0000\u0000\u031d\u031e\u0001\u0000\u0000\u0000"+
		"\u031e\u031f\u0001\u0000\u0000\u0000\u031f\u0321\u0005\u0001\u0000\u0000"+
		"\u0320\u0322\u0003h4\u0000\u0321\u0320\u0001\u0000\u0000\u0000\u0321\u0322"+
		"\u0001\u0000\u0000\u0000\u0322\u0323\u0001\u0000\u0000\u0000\u0323\u0330"+
		"\u0005\u0003\u0000\u0000\u0324\u0325\n\u0013\u0000\u0000\u0325\u0326\u0005"+
		"\t\u0000\u0000\u0326\u0327\u0003d2\u0000\u0327\u0328\u0005\n\u0000\u0000"+
		"\u0328\u0330\u0001\u0000\u0000\u0000\u0329\u032b\n\u0010\u0000\u0000\u032a"+
		"\u032c\u0005F\u0000\u0000\u032b\u032a\u0001\u0000\u0000\u0000\u032b\u032c"+
		"\u0001\u0000\u0000\u0000\u032c\u032d\u0001\u0000\u0000\u0000\u032d\u032e"+
		"\u0005A\u0000\u0000\u032e\u0330\u0003r9\u0000\u032f\u02ee\u0001\u0000"+
		"\u0000\u0000\u032f\u02f1\u0001\u0000\u0000\u0000\u032f\u02f4\u0001\u0000"+
		"\u0000\u0000\u032f\u02f7\u0001\u0000\u0000\u0000\u032f\u02fa\u0001\u0000"+
		"\u0000\u0000\u032f\u02fd\u0001\u0000\u0000\u0000\u032f\u0300\u0001\u0000"+
		"\u0000\u0000\u032f\u0303\u0001\u0000\u0000\u0000\u032f\u0306\u0001\u0000"+
		"\u0000\u0000\u032f\u0309\u0001\u0000\u0000\u0000\u032f\u030c\u0001\u0000"+
		"\u0000\u0000\u032f\u030f\u0001\u0000\u0000\u0000\u032f\u0318\u0001\u0000"+
		"\u0000\u0000\u032f\u031b\u0001\u0000\u0000\u0000\u032f\u0324\u0001\u0000"+
		"\u0000\u0000\u032f\u0329\u0001\u0000\u0000\u0000\u0330\u0333\u0001\u0000"+
		"\u0000\u0000\u0331\u032f\u0001\u0000\u0000\u0000\u0331\u0332\u0001\u0000"+
		"\u0000\u0000\u0332e\u0001\u0000\u0000\u0000\u0333\u0331\u0001\u0000\u0000"+
		"\u0000\u0334\u0339\u0003d2\u0000\u0335\u0336\u0005\u0002\u0000\u0000\u0336"+
		"\u0338\u0003d2\u0000\u0337\u0335\u0001\u0000\u0000\u0000\u0338\u033b\u0001"+
		"\u0000\u0000\u0000\u0339\u0337\u0001\u0000\u0000\u0000\u0339\u033a\u0001"+
		"\u0000\u0000\u0000\u033a\u033c\u0001\u0000\u0000\u0000\u033b\u0339\u0001"+
		"\u0000\u0000\u0000\u033c\u033d\u0005\b\u0000\u0000\u033d\u033e\u0003d"+
		"2\u0000\u033e\u0344\u0001\u0000\u0000\u0000\u033f\u0340\u0003d2\u0000"+
		"\u0340\u0341\u0007\b\u0000\u0000\u0341\u0342\u0003d2\u0000\u0342\u0344"+
		"\u0001\u0000\u0000\u0000\u0343\u0334\u0001\u0000\u0000\u0000\u0343\u033f"+
		"\u0001\u0000\u0000\u0000\u0344g\u0001\u0000\u0000\u0000\u0345\u034a\u0003"+
		"d2\u0000\u0346\u0347\u0005\u0002\u0000\u0000\u0347\u0349\u0003d2\u0000"+
		"\u0348\u0346\u0001\u0000\u0000\u0000\u0349\u034c\u0001\u0000\u0000\u0000"+
		"\u034a\u0348\u0001\u0000\u0000\u0000\u034a\u034b\u0001\u0000\u0000\u0000"+
		"\u034bi\u0001\u0000\u0000\u0000\u034c\u034a\u0001\u0000\u0000\u0000\u034d"+
		"\u034e\u0005:\u0000\u0000\u034e\u034f\u0003v;\u0000\u034fk\u0001\u0000"+
		"\u0000\u0000\u0350\u0355\u0003n7\u0000\u0351\u0352\u0005\u0002\u0000\u0000"+
		"\u0352\u0354\u0003n7\u0000\u0353\u0351\u0001\u0000\u0000\u0000\u0354\u0357"+
		"\u0001\u0000\u0000\u0000\u0355\u0353\u0001\u0000\u0000\u0000\u0355\u0356"+
		"\u0001\u0000\u0000\u0000\u0356m\u0001\u0000\u0000\u0000\u0357\u0355\u0001"+
		"\u0000\u0000\u0000\u0358\u035a\u0003Z-\u0000\u0359\u0358\u0001\u0000\u0000"+
		"\u0000\u0359\u035a\u0001\u0000\u0000\u0000\u035a\u035b\u0001\u0000\u0000"+
		"\u0000\u035b\u035c\u0003p8\u0000\u035c\u035e\u0005g\u0000\u0000\u035d"+
		"\u035f\u0005e\u0000\u0000\u035e\u035d\u0001\u0000\u0000\u0000\u035e\u035f"+
		"\u0001\u0000\u0000\u0000\u035f\u0362\u0001\u0000\u0000\u0000\u0360\u0361"+
		"\u0005\b\u0000\u0000\u0361\u0363\u0003d2\u0000\u0362\u0360\u0001\u0000"+
		"\u0000\u0000\u0362\u0363\u0001\u0000\u0000\u0000\u0363o\u0001\u0000\u0000"+
		"\u0000\u0364\u0365\u00068\uffff\uffff\u0000\u0365\u0369\u0003v;\u0000"+
		"\u0366\u0369\u0003t:\u0000\u0367\u0369\u0003~?\u0000\u0368\u0364\u0001"+
		"\u0000\u0000\u0000\u0368\u0366\u0001\u0000\u0000\u0000\u0368\u0367\u0001"+
		"\u0000\u0000\u0000\u0369\u0373\u0001\u0000\u0000\u0000\u036a\u036d\n\u0001"+
		"\u0000\u0000\u036b\u036c\u0005\t\u0000\u0000\u036c\u036e\u0005\n\u0000"+
		"\u0000\u036d\u036b\u0001\u0000\u0000\u0000\u036e\u036f\u0001\u0000\u0000"+
		"\u0000\u036f\u036d\u0001\u0000\u0000\u0000\u036f\u0370\u0001\u0000\u0000"+
		"\u0000\u0370\u0372\u0001\u0000\u0000\u0000\u0371\u036a\u0001\u0000\u0000"+
		"\u0000\u0372\u0375\u0001\u0000\u0000\u0000\u0373\u0371\u0001\u0000\u0000"+
		"\u0000\u0373\u0374\u0001\u0000\u0000\u0000\u0374q\u0001\u0000\u0000\u0000"+
		"\u0375\u0373\u0001\u0000\u0000\u0000\u0376\u0377\u00069\uffff\uffff\u0000"+
		"\u0377\u037a\u0003v;\u0000\u0378\u037a\u0003t:\u0000\u0379\u0376\u0001"+
		"\u0000\u0000\u0000\u0379\u0378\u0001\u0000\u0000\u0000\u037a\u0384\u0001"+
		"\u0000\u0000\u0000\u037b\u037e\n\u0001\u0000\u0000\u037c\u037d\u0005\t"+
		"\u0000\u0000\u037d\u037f\u0005\n\u0000\u0000\u037e\u037c\u0001\u0000\u0000"+
		"\u0000\u037f\u0380\u0001\u0000\u0000\u0000\u0380\u037e\u0001\u0000\u0000"+
		"\u0000\u0380\u0381\u0001\u0000\u0000\u0000\u0381\u0383\u0001\u0000\u0000"+
		"\u0000\u0382\u037b\u0001\u0000\u0000\u0000\u0383\u0386\u0001\u0000\u0000"+
		"\u0000\u0384\u0382\u0001\u0000\u0000\u0000\u0384\u0385\u0001\u0000\u0000"+
		"\u0000\u0385s\u0001\u0000\u0000\u0000\u0386\u0384\u0001\u0000\u0000\u0000"+
		"\u0387\u0388\u0007\t\u0000\u0000\u0388u\u0001\u0000\u0000\u0000\u0389"+
		"\u038b\u0003b1\u0000\u038a\u038c\u0003|>\u0000\u038b\u038a\u0001\u0000"+
		"\u0000\u0000\u038b\u038c\u0001\u0000\u0000\u0000\u038cw\u0001\u0000\u0000"+
		"\u0000\u038d\u038e\u0005\u0013\u0000\u0000\u038e\u0393\u0003z=\u0000\u038f"+
		"\u0390\u0005\u0002\u0000\u0000\u0390\u0392\u0003z=\u0000\u0391\u038f\u0001"+
		"\u0000\u0000\u0000\u0392\u0395\u0001\u0000\u0000\u0000\u0393\u0391\u0001"+
		"\u0000\u0000\u0000\u0393\u0394\u0001\u0000\u0000\u0000\u0394\u0396\u0001"+
		"\u0000\u0000\u0000\u0395\u0393\u0001\u0000\u0000\u0000\u0396\u0397\u0005"+
		"\u0016\u0000\u0000\u0397y\u0001\u0000\u0000\u0000\u0398\u039d\u0005g\u0000"+
		"\u0000\u0399\u039a\u0005g\u0000\u0000\u039a\u039b\u0005C\u0000\u0000\u039b"+
		"\u039d\u0003p8\u0000\u039c\u0398\u0001\u0000\u0000\u0000\u039c\u0399\u0001"+
		"\u0000\u0000\u0000\u039d{\u0001\u0000\u0000\u0000\u039e\u039f\u0005\u0013"+
		"\u0000\u0000\u039f\u03ac\u0005\u0016\u0000\u0000\u03a0\u03a1\u0005\u0013"+
		"\u0000\u0000\u03a1\u03a6\u0003p8\u0000\u03a2\u03a3\u0005\u0002\u0000\u0000"+
		"\u03a3\u03a5\u0003p8\u0000\u03a4\u03a2\u0001\u0000\u0000\u0000\u03a5\u03a8"+
		"\u0001\u0000\u0000\u0000\u03a6\u03a4\u0001\u0000\u0000\u0000\u03a6\u03a7"+
		"\u0001\u0000\u0000\u0000\u03a7\u03a9\u0001\u0000\u0000\u0000\u03a8\u03a6"+
		"\u0001\u0000\u0000\u0000\u03a9\u03aa\u0005\u0016\u0000\u0000\u03aa\u03ac"+
		"\u0001\u0000\u0000\u0000\u03ab\u039e\u0001\u0000\u0000\u0000\u03ab\u03a0"+
		"\u0001\u0000\u0000\u0000\u03ac}\u0001\u0000\u0000\u0000\u03ad\u03af\u0005"+
		"c\u0000\u0000\u03ae\u03b0\u0003|>\u0000\u03af\u03ae\u0001\u0000\u0000"+
		"\u0000\u03af\u03b0\u0001\u0000\u0000\u0000\u03b0\u007f\u0001\u0000\u0000"+
		"\u0000\u03b1\u03b2\u0003p8\u0000\u03b2\u03b9\u0005g\u0000\u0000\u03b3"+
		"\u03b4\u0005\u0002\u0000\u0000\u03b4\u03b5\u0003p8\u0000\u03b5\u03b6\u0005"+
		"g\u0000\u0000\u03b6\u03b8\u0001\u0000\u0000\u0000\u03b7\u03b3\u0001\u0000"+
		"\u0000\u0000\u03b8\u03bb\u0001\u0000\u0000\u0000\u03b9\u03b7\u0001\u0000"+
		"\u0000\u0000\u03b9\u03ba\u0001\u0000\u0000\u0000\u03ba\u0081\u0001\u0000"+
		"\u0000\u0000\u03bb\u03b9\u0001\u0000\u0000\u0000t\u0086\u008c\u0094\u009e"+
		"\u00a6\u00b1\u00b4\u00b8\u00be\u00c3\u00c5\u00ca\u00cd\u00d1\u00d5\u00da"+
		"\u00dc\u00df\u00e5\u00ed\u00f5\u0101\u010d\u0111\u0119\u0125\u0131\u0135"+
		"\u013e\u0148\u014c\u0155\u015f\u0163\u0168\u016b\u0170\u0174\u017a\u0180"+
		"\u0186\u018b\u018f\u019c\u01a1\u01a6\u01aa\u01ae\u01b4\u01b9\u01bb\u01be"+
		"\u01c2\u01c6\u01cb\u01cd\u01cf\u01d2\u01da\u01e0\u01e8\u01f8\u0201\u020d"+
		"\u0214\u021f\u0226\u022e\u0235\u023f\u0243\u0248\u0252\u0259\u025d\u0264"+
		"\u0276\u027a\u0284\u028e\u0294\u0298\u029d\u02a9\u02ae\u02b4\u02c0\u02cf"+
		"\u02db\u02ec\u0315\u031d\u0321\u032b\u032f\u0331\u0339\u0343\u034a\u0355"+
		"\u0359\u035e\u0362\u0368\u036f\u0373\u0379\u0380\u0384\u038b\u0393\u039c"+
		"\u03a6\u03ab\u03af\u03b9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}