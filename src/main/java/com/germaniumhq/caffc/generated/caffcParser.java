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
		FINALLY=59, FOR=60, IF=61, IMPLEMENTS=62, IN=63, INSTANCEOF=64, INTERFACE=65, 
		IS=66, MODULE=67, NEW=68, NOT=69, FALSE=70, NULL=71, OR=72, TRUE=73, RETURN=74, 
		STATIC=75, SWITCH=76, TAG=77, THROW=78, TRY=79, USE=80, VAR=81, VOID=82, 
		WHILE=83, YIELD=84, U8=85, I8=86, U16=87, I16=88, U32=89, I32=90, U64=91, 
		I64=92, F32=93, F64=94, NATIVE=95, CURLY_OPEN=96, CURLY_CLOSE=97, FN=98, 
		SHARP=99, STAR=100, DOT=101, ID=102, NUMBER=103, DigitSequence=104;
	public static final int
		RULE_compilationUnit = 0, RULE_module = 1, RULE_useStatement = 2, RULE_compileBlock = 3, 
		RULE_use = 4, RULE_use_alias = 5, RULE_nativeBlock = 6, RULE_function = 7, 
		RULE_returnType = 8, RULE_classDefinition = 9, RULE_interfaceDefinition = 10, 
		RULE_name = 11, RULE_interfaceImplementations = 12, RULE_interfaceImplementation = 13, 
		RULE_interfaceStatements = 14, RULE_functionDeclaration = 15, RULE_tagDefinition = 16, 
		RULE_fieldDeclaration = 17, RULE_statement = 18, RULE_throwStatement = 19, 
		RULE_block = 20, RULE_whileBlock = 21, RULE_forBlock = 22, RULE_ifBlock = 23, 
		RULE_switchBlock = 24, RULE_switchCaseBody = 25, RULE_switchBranch = 26, 
		RULE_tryCatchBlock = 27, RULE_catchBlock = 28, RULE_finallyBlock = 29, 
		RULE_return = 30, RULE_decoratorCall = 31, RULE_controlFlow = 32, RULE_variableDeclarations = 33, 
		RULE_variableDeclaration = 34, RULE_varType = 35, RULE_tags = 36, RULE_tag = 37, 
		RULE_tagParam = 38, RULE_constExpression = 39, RULE_fqdn = 40, RULE_expression = 41, 
		RULE_assignExpression = 42, RULE_expressionTuple = 43, RULE_extend = 44, 
		RULE_parameterDefinitions = 45, RULE_parameterDefinition = 46, RULE_typeName = 47, 
		RULE_newType = 48, RULE_primitiveTypeName = 49, RULE_classType = 50, RULE_genericsDeclarations = 51, 
		RULE_genericDeclaration = 52, RULE_genericsInstantiations = 53, RULE_functionType = 54, 
		RULE_namedTypeTuple = 55;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilationUnit", "module", "useStatement", "compileBlock", "use", "use_alias", 
			"nativeBlock", "function", "returnType", "classDefinition", "interfaceDefinition", 
			"name", "interfaceImplementations", "interfaceImplementation", "interfaceStatements", 
			"functionDeclaration", "tagDefinition", "fieldDeclaration", "statement", 
			"throwStatement", "block", "whileBlock", "forBlock", "ifBlock", "switchBlock", 
			"switchCaseBody", "switchBranch", "tryCatchBlock", "catchBlock", "finallyBlock", 
			"return", "decoratorCall", "controlFlow", "variableDeclarations", "variableDeclaration", 
			"varType", "tags", "tag", "tagParam", "constExpression", "fqdn", "expression", 
			"assignExpression", "expressionTuple", "extend", "parameterDefinitions", 
			"parameterDefinition", "typeName", "newType", "primitiveTypeName", "classType", 
			"genericsDeclarations", "genericDeclaration", "genericsInstantiations", 
			"functionType", "namedTypeTuple"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'->'", "';'", "':'", "'@'", "'='", "'['", 
			"']'", "'!'", "'~'", "'-'", "'%'", "'/'", "'+'", "'<<'", "'>>'", "'<'", 
			"'<='", "'>='", "'>'", "'=='", "'!='", "'&'", "'^'", "'|'", "'?'", "'?:'", 
			"'<<='", "'>>='", "'&='", "'|='", "'^='", "'*='", "'/='", "'+='", "'-='", 
			"'ptr'", "'bool'", null, null, null, null, null, null, null, null, "'as'", 
			"'and'", "'break'", "'case'", "'catch'", "'class'", "'continue'", "'default'", 
			"'else'", "'extends'", "'finally'", "'for'", "'if'", "'implements'", 
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
			"FOR", "IF", "IMPLEMENTS", "IN", "INSTANCEOF", "INTERFACE", "IS", "MODULE", 
			"NEW", "NOT", "FALSE", "NULL", "OR", "TRUE", "RETURN", "STATIC", "SWITCH", 
			"TAG", "THROW", "TRY", "USE", "VAR", "VOID", "WHILE", "YIELD", "U8", 
			"I8", "U16", "I16", "U32", "I32", "U64", "I64", "F32", "F64", "NATIVE", 
			"CURLY_OPEN", "CURLY_CLOSE", "FN", "SHARP", "STAR", "DOT", "ID", "NUMBER", 
			"DigitSequence"
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
			setState(112);
			module();
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==USE) {
				{
				{
				setState(113);
				useStatement();
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(122);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & -7349940218901790717L) != 0)) {
				{
				{
				setState(119);
				compileBlock();
				}
				}
				setState(124);
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
			setState(125);
			match(MODULE);
			setState(126);
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
			setState(128);
			use();
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(129);
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
		public CompileBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compileBlock; }
	}

	public final CompileBlockContext compileBlock() throws RecognitionException {
		CompileBlockContext _localctx = new CompileBlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_compileBlock);
		try {
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				nativeBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				tagDefinition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(134);
				function();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(135);
				classDefinition();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(136);
				interfaceDefinition();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(137);
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
		enterRule(_localctx, 8, RULE_use);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(USE);
			setState(141);
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
		enterRule(_localctx, 10, RULE_use_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(AS);
			setState(144);
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
		enterRule(_localctx, 12, RULE_nativeBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
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
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
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
		enterRule(_localctx, 14, RULE_function);
		int _la;
		try {
			setState(195);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(148);
					tags();
					}
				}

				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(151);
					match(STATIC);
					}
				}

				setState(154);
				match(ID);
				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(155);
					genericsDeclarations();
					}
				}

				setState(158);
				match(T__0);
				setState(159);
				extend();
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(160);
					match(T__1);
					setState(161);
					parameterDefinitions();
					}
				}

				setState(164);
				match(T__2);
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(165);
					match(T__3);
					setState(167);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & -8574915263164579837L) != 0)) {
						{
						setState(166);
						returnType();
						}
					}

					}
				}

				setState(171);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(173);
					tags();
					}
				}

				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(176);
					match(STATIC);
					}
				}

				setState(179);
				match(ID);
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(180);
					genericsDeclarations();
					}
				}

				setState(183);
				match(T__0);
				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & -7422002554650755069L) != 0)) {
					{
					setState(184);
					parameterDefinitions();
					}
				}

				setState(187);
				match(T__2);
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(188);
					match(T__3);
					setState(190);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & -8574915263164579837L) != 0)) {
						{
						setState(189);
						returnType();
						}
					}

					}
				}

				setState(194);
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
		enterRule(_localctx, 16, RULE_returnType);
		try {
			setState(200);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(197);
				namedTypeTuple();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(198);
				typeName(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(199);
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
		enterRule(_localctx, 18, RULE_classDefinition);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(202);
				tags();
				}
			}

			setState(205);
			match(CLASS);
			setState(206);
			name();
			setState(208);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(207);
				genericsDeclarations();
				}
			}

			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(210);
				match(IMPLEMENTS);
				setState(211);
				interfaceImplementations();
				}
			}

			setState(214);
			match(CURLY_OPEN);
			setState(218);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(215);
					fieldDeclaration();
					}
					} 
				}
				setState(220);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 75)) & ~0x3f) == 0 && ((1L << (_la - 75)) & 150994945L) != 0)) {
				{
				{
				setState(221);
				function();
				}
				}
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(227);
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
		enterRule(_localctx, 20, RULE_interfaceDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(229);
				tags();
				}
			}

			setState(232);
			match(INTERFACE);
			setState(233);
			name();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(234);
				genericsDeclarations();
				}
			}

			setState(239);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(237);
				match(EXTENDS);
				setState(238);
				interfaceImplementations();
				}
			}

			setState(241);
			match(CURLY_OPEN);
			setState(242);
			interfaceStatements();
			setState(243);
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
		enterRule(_localctx, 22, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
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
		enterRule(_localctx, 24, RULE_interfaceImplementations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			interfaceImplementation();
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(248);
				match(T__1);
				setState(249);
				interfaceImplementation();
				}
				}
				setState(254);
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
		enterRule(_localctx, 26, RULE_interfaceImplementation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			fqdn(0);
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__18) {
				{
				setState(256);
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
		enterRule(_localctx, 28, RULE_interfaceStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==ID) {
				{
				{
				setState(259);
				functionDeclaration();
				}
				}
				setState(264);
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
		enterRule(_localctx, 30, RULE_functionDeclaration);
		int _la;
		try {
			setState(303);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(265);
					tags();
					}
				}

				setState(268);
				match(ID);
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(269);
					genericsDeclarations();
					}
				}

				setState(272);
				match(T__0);
				setState(273);
				extend();
				setState(276);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(274);
					match(T__1);
					setState(275);
					parameterDefinitions();
					}
				}

				setState(278);
				match(T__2);
				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(279);
					match(T__3);
					setState(281);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
					case 1:
						{
						setState(280);
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
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(285);
					tags();
					}
				}

				setState(288);
				match(ID);
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__18) {
					{
					setState(289);
					genericsDeclarations();
					}
				}

				setState(292);
				match(T__0);
				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & -7422002554650755069L) != 0)) {
					{
					setState(293);
					parameterDefinitions();
					}
				}

				setState(296);
				match(T__2);
				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(297);
					match(T__3);
					setState(299);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
					case 1:
						{
						setState(298);
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
		enterRule(_localctx, 32, RULE_tagDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(305);
				tags();
				}
			}

			setState(308);
			match(TAG);
			setState(309);
			match(ID);
			setState(310);
			match(CURLY_OPEN);
			setState(314);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & -7422002554650755069L) != 0)) {
				{
				{
				setState(311);
				fieldDeclaration();
				}
				}
				setState(316);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(317);
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
		enterRule(_localctx, 34, RULE_fieldDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(319);
				tags();
				}
			}

			setState(322);
			typeName(0);
			setState(323);
			match(ID);
			setState(328);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(324);
				match(T__1);
				setState(325);
				match(ID);
				}
				}
				setState(330);
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
		enterRule(_localctx, 36, RULE_statement);
		try {
			setState(344);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(331);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(332);
				variableDeclarations();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(333);
				return_();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(334);
				controlFlow();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(335);
				nativeBlock();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(336);
				whileBlock();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(337);
				forBlock();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(338);
				ifBlock();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(339);
				switchBlock();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(340);
				tryCatchBlock();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(341);
				throwStatement();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(342);
				expression(0);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(343);
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
		enterRule(_localctx, 38, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(THROW);
			setState(347);
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
		enterRule(_localctx, 40, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			match(CURLY_OPEN);
			setState(353);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3497053356990412802L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 55029181935L) != 0)) {
				{
				{
				setState(350);
				statement();
				}
				}
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(356);
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
		enterRule(_localctx, 42, RULE_whileBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			match(WHILE);
			setState(359);
			expression(0);
			setState(360);
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
		enterRule(_localctx, 44, RULE_forBlock);
		try {
			setState(383);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				match(FOR);
				setState(365);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(363);
					((ForBlockContext)_localctx).initExpression = assignExpression();
					}
					break;
				case 2:
					{
					setState(364);
					variableDeclarations();
					}
					break;
				}
				setState(367);
				match(T__4);
				setState(368);
				((ForBlockContext)_localctx).conditionExpression = expression(0);
				setState(369);
				match(T__4);
				setState(372);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(370);
					((ForBlockContext)_localctx).incrementExpression = expression(0);
					}
					break;
				case 2:
					{
					setState(371);
					((ForBlockContext)_localctx).incrementAssignExpression = assignExpression();
					}
					break;
				}
				setState(374);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(376);
				match(FOR);
				setState(377);
				typeName(0);
				setState(378);
				((ForBlockContext)_localctx).variableName = match(ID);
				setState(379);
				match(IN);
				setState(380);
				expression(0);
				setState(381);
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
		enterRule(_localctx, 46, RULE_ifBlock);
		try {
			setState(398);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(385);
				match(IF);
				setState(386);
				expression(0);
				setState(390);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CURLY_OPEN:
					{
					setState(387);
					((IfBlockContext)_localctx).trueBlock = block();
					}
					break;
				case RETURN:
					{
					setState(388);
					return_();
					}
					break;
				case BREAK:
				case CONTINUE:
					{
					setState(389);
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
				setState(392);
				match(IF);
				setState(393);
				expression(0);
				setState(394);
				((IfBlockContext)_localctx).trueBlock = block();
				setState(395);
				match(ELSE);
				setState(396);
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
		enterRule(_localctx, 48, RULE_switchBlock);
		int _la;
		try {
			setState(419);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(400);
				match(SWITCH);
				setState(401);
				match(CURLY_OPEN);
				setState(403); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(402);
					switchBranch();
					}
					}
					setState(405); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CASE || _la==DEFAULT );
				setState(407);
				match(CURLY_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(409);
				match(SWITCH);
				setState(410);
				expression(0);
				setState(411);
				match(CURLY_OPEN);
				setState(413); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(412);
					switchBranch();
					}
					}
					setState(415); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CASE || _la==DEFAULT );
				setState(417);
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
		enterRule(_localctx, 50, RULE_switchCaseBody);
		try {
			setState(424);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CURLY_OPEN:
				enterOuterAlt(_localctx, 1);
				{
				setState(421);
				block();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 2);
				{
				setState(422);
				return_();
				}
				break;
			case BREAK:
			case CONTINUE:
				enterOuterAlt(_localctx, 3);
				{
				setState(423);
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
		enterRule(_localctx, 52, RULE_switchBranch);
		try {
			setState(434);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CASE:
				enterOuterAlt(_localctx, 1);
				{
				setState(426);
				match(CASE);
				setState(427);
				expression(0);
				setState(428);
				match(T__5);
				setState(429);
				switchCaseBody();
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(431);
				match(DEFAULT);
				setState(432);
				match(T__5);
				setState(433);
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
		enterRule(_localctx, 54, RULE_tryCatchBlock);
		int _la;
		try {
			setState(452);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(436);
				match(TRY);
				setState(437);
				block();
				setState(441);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CATCH) {
					{
					{
					setState(438);
					catchBlock();
					}
					}
					setState(443);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(445);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(444);
					finallyBlock();
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(447);
				match(TRY);
				setState(448);
				block();
				setState(449);
				match(FINALLY);
				setState(450);
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
		enterRule(_localctx, 56, RULE_catchBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(454);
			match(CATCH);
			setState(455);
			match(T__0);
			setState(456);
			classType();
			setState(457);
			match(ID);
			setState(458);
			match(T__2);
			setState(459);
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
		enterRule(_localctx, 58, RULE_finallyBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			match(FINALLY);
			setState(462);
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
		enterRule(_localctx, 60, RULE_return);
		int _la;
		try {
			setState(474);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(464);
				match(RETURN);
				setState(465);
				expression(0);
				setState(470);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(466);
					match(T__1);
					setState(467);
					expression(0);
					}
					}
					setState(472);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(473);
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
		enterRule(_localctx, 62, RULE_decoratorCall);
		try {
			setState(484);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(476);
				match(T__6);
				setState(477);
				expression(0);
				setState(478);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(480);
				match(T__6);
				setState(481);
				expression(0);
				setState(482);
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
		enterRule(_localctx, 64, RULE_controlFlow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
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
		enterRule(_localctx, 66, RULE_variableDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488);
			varType();
			setState(489);
			variableDeclaration();
			setState(494);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(490);
				match(T__1);
				setState(491);
				variableDeclaration();
				}
				}
				setState(496);
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
		enterRule(_localctx, 68, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(497);
			match(ID);
			setState(500);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(498);
				match(T__7);
				setState(499);
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
		enterRule(_localctx, 70, RULE_varType);
		try {
			setState(504);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(502);
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
				setState(503);
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
		enterRule(_localctx, 72, RULE_tags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(507); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(506);
				tag();
				}
				}
				setState(509); 
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
		enterRule(_localctx, 74, RULE_tag);
		int _la;
		try {
			setState(526);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(511);
				match(SHARP);
				setState(512);
				fqdn(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(513);
				match(SHARP);
				setState(514);
				fqdn(0);
				setState(515);
				match(T__0);
				setState(516);
				tagParam();
				setState(521);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(517);
					match(T__1);
					setState(518);
					tagParam();
					}
					}
					setState(523);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(524);
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
		enterRule(_localctx, 76, RULE_tagParam);
		try {
			setState(532);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(528);
				match(ID);
				setState(529);
				match(T__7);
				setState(530);
				constExpression();
				}
				break;
			case CHAR:
			case STRING:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(531);
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
		enterRule(_localctx, 78, RULE_constExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534);
			_la = _input.LA(1);
			if ( !(((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 4611686018427387907L) != 0)) ) {
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
		int _startState = 80;
		enterRecursionRule(_localctx, 80, RULE_fqdn, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new FqdnIdContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(537);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(544);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FqdnMultipleContext(new FqdnContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_fqdn);
					setState(539);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(540);
					match(DOT);
					setState(541);
					match(ID);
					}
					} 
				}
				setState(546);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
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
		int _startState = 82;
		enterRecursionRule(_localctx, 82, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(588);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				{
				_localctx = new ExNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(548);
				match(NUMBER);
				}
				break;
			case 2:
				{
				_localctx = new ExStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(549);
				match(STRING);
				}
				break;
			case 3:
				{
				_localctx = new ExCharContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(550);
				match(CHAR);
				}
				break;
			case 4:
				{
				_localctx = new ExIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(551);
				match(ID);
				}
				break;
			case 5:
				{
				_localctx = new ExNullContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(552);
				match(NULL);
				}
				break;
			case 6:
				{
				_localctx = new ExTrueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(553);
				match(TRUE);
				}
				break;
			case 7:
				{
				_localctx = new ExFalseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(554);
				match(FALSE);
				}
				break;
			case 8:
				{
				_localctx = new ExNewObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(555);
				match(NEW);
				setState(556);
				newType(0);
				setState(557);
				match(T__0);
				setState(559);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6597069780994L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 51539607599L) != 0)) {
					{
					setState(558);
					expressionTuple();
					}
				}

				setState(561);
				match(T__2);
				}
				break;
			case 9:
				{
				_localctx = new ExNewArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(563);
				match(NEW);
				setState(564);
				newType(0);
				setState(569); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(565);
						match(T__8);
						setState(566);
						expression(0);
						setState(567);
						match(T__9);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(571); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 10:
				{
				_localctx = new ExCastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(573);
				match(T__0);
				setState(574);
				typeName(0);
				setState(575);
				match(T__2);
				setState(576);
				expression(18);
				}
				break;
			case 11:
				{
				_localctx = new ExParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(578);
				match(T__0);
				setState(579);
				expression(0);
				setState(580);
				match(T__2);
				}
				break;
			case 12:
				{
				_localctx = new ExBoolNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(582);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(583);
				expression(15);
				}
				break;
			case 13:
				{
				_localctx = new ExBitNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(584);
				match(T__11);
				setState(585);
				expression(14);
				}
				break;
			case 14:
				{
				_localctx = new ExUnaryMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(586);
				match(T__12);
				setState(587);
				expression(13);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(657);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(655);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
					case 1:
						{
						_localctx = new ExMulModContext(new ExpressionContext(_parentctx, _parentState));
						((ExMulModContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(590);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(591);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==STAR) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(592);
						((ExMulModContext)_localctx).rightExpression = expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ExDivContext(new ExpressionContext(_parentctx, _parentState));
						((ExDivContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(593);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(594);
						match(T__14);
						setState(595);
						((ExDivContext)_localctx).rightExpression = expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExAddSubContext(new ExpressionContext(_parentctx, _parentState));
						((ExAddSubContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(596);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(597);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__15) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(598);
						((ExAddSubContext)_localctx).rightExpression = expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExShiftContext(new ExpressionContext(_parentctx, _parentState));
						((ExShiftContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(599);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(600);
						_la = _input.LA(1);
						if ( !(_la==T__16 || _la==T__17) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(601);
						((ExShiftContext)_localctx).rightExpression = expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExLtLteGtGteContext(new ExpressionContext(_parentctx, _parentState));
						((ExLtLteGtGteContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(602);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(603);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 7864320L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(604);
						((ExLtLteGtGteContext)_localctx).rightExpression = expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExEqNeqContext(new ExpressionContext(_parentctx, _parentState));
						((ExEqNeqContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(605);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(606);
						_la = _input.LA(1);
						if ( !(_la==T__22 || _la==T__23) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(607);
						((ExEqNeqContext)_localctx).rightExpression = expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExBitAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitAndContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(608);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(609);
						match(T__24);
						setState(610);
						((ExBitAndContext)_localctx).rightExpression = expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExBitXorContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitXorContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(611);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(612);
						match(T__25);
						setState(613);
						((ExBitXorContext)_localctx).rightExpression = expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExBitOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitOrContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(614);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(615);
						match(T__26);
						setState(616);
						((ExBitOrContext)_localctx).rightExpression = expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExBoolAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExBoolAndContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(617);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(618);
						match(AND);
						setState(619);
						((ExBoolAndContext)_localctx).rightExpression = expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExBoolOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExBoolOrContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(620);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(621);
						match(OR);
						setState(622);
						((ExBoolOrContext)_localctx).rightExpression = expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExTernaryContext(new ExpressionContext(_parentctx, _parentState));
						((ExTernaryContext)_localctx).checkExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(623);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(629);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__27:
							{
							setState(624);
							match(T__27);
							setState(625);
							((ExTernaryContext)_localctx).trueExpression = expression(0);
							setState(626);
							match(T__5);
							}
							break;
						case T__28:
							{
							setState(628);
							match(T__28);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(631);
						((ExTernaryContext)_localctx).falseExpression = expression(2);
						}
						break;
					case 13:
						{
						_localctx = new ExDotAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(632);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(633);
						match(DOT);
						setState(634);
						match(ID);
						}
						break;
					case 14:
						{
						_localctx = new ExFnCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(635);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(637);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__18) {
							{
							setState(636);
							genericsInstantiations();
							}
						}

						setState(639);
						match(T__0);
						setState(641);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6597069780994L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 51539607599L) != 0)) {
							{
							setState(640);
							expressionTuple();
							}
						}

						setState(643);
						match(T__2);
						}
						break;
					case 15:
						{
						_localctx = new ExIndexAccessContext(new ExpressionContext(_parentctx, _parentState));
						((ExIndexAccessContext)_localctx).arraryExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(644);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(645);
						match(T__8);
						setState(646);
						((ExIndexAccessContext)_localctx).indexExpression = expression(0);
						setState(647);
						match(T__9);
						}
						break;
					case 16:
						{
						_localctx = new ExInstanceOfContext(new ExpressionContext(_parentctx, _parentState));
						((ExInstanceOfContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(649);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(651);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NOT) {
							{
							setState(650);
							match(NOT);
							}
						}

						setState(653);
						match(INSTANCEOF);
						setState(654);
						newType(0);
						}
						break;
					}
					} 
				}
				setState(659);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
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
		enterRule(_localctx, 84, RULE_assignExpression);
		int _la;
		try {
			setState(675);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				_localctx = new ExAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(660);
				expression(0);
				setState(665);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(661);
					match(T__1);
					setState(662);
					expression(0);
					}
					}
					setState(667);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(668);
				match(T__7);
				setState(669);
				((ExAssignContext)_localctx).rightExpression = expression(0);
				}
				break;
			case 2:
				_localctx = new ExOpAssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(671);
				((ExOpAssignContext)_localctx).leftExpression = expression(0);
				setState(672);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 548682072064L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(673);
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
		enterRule(_localctx, 86, RULE_expressionTuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(677);
			expression(0);
			setState(682);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(678);
				match(T__1);
				setState(679);
				expression(0);
				}
				}
				setState(684);
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
		enterRule(_localctx, 88, RULE_extend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(685);
			match(EXTENDS);
			setState(686);
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
		enterRule(_localctx, 90, RULE_parameterDefinitions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(688);
			parameterDefinition();
			setState(693);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(689);
				match(T__1);
				setState(690);
				parameterDefinition();
				}
				}
				setState(695);
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
		enterRule(_localctx, 92, RULE_parameterDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(697);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(696);
				tags();
				}
			}

			setState(699);
			typeName(0);
			setState(700);
			match(ID);
			setState(702);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(701);
				match(STAR);
				}
			}

			setState(706);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(704);
				match(T__7);
				setState(705);
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
		int _startState = 94;
		enterRecursionRule(_localctx, 94, RULE_typeName, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(712);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new TypeClassContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(709);
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
				setState(710);
				primitiveTypeName();
				}
				break;
			case FN:
				{
				_localctx = new TypeFunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(711);
				functionType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(723);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeArrayContext(new TypeNameContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_typeName);
					setState(714);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(717); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(715);
							match(T__8);
							setState(716);
							match(T__9);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(719); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(725);
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
		int _startState = 96;
		enterRecursionRule(_localctx, 96, RULE_newType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(729);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new NewTypeClassContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(727);
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
				setState(728);
				primitiveTypeName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(740);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NewTypeArrayContext(new NewTypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_newType);
					setState(731);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(734); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(732);
							match(T__8);
							setState(733);
							match(T__9);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(736); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(742);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
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
		enterRule(_localctx, 98, RULE_primitiveTypeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(743);
			_la = _input.LA(1);
			if ( !(((((_la - 39)) & ~0x3f) == 0 && ((1L << (_la - 39)) & 71987225293750275L) != 0)) ) {
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
		enterRule(_localctx, 100, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(745);
			fqdn(0);
			setState(747);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(746);
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
		enterRule(_localctx, 102, RULE_genericsDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(749);
			match(T__18);
			setState(750);
			genericDeclaration();
			setState(755);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(751);
				match(T__1);
				setState(752);
				genericDeclaration();
				}
				}
				setState(757);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(758);
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
		enterRule(_localctx, 104, RULE_genericDeclaration);
		try {
			setState(764);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				_localctx = new GenericDeclarationIdContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(760);
				match(ID);
				}
				break;
			case 2:
				_localctx = new GenericDeclarationRestrictedContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(761);
				match(ID);
				setState(762);
				match(IS);
				setState(763);
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
		enterRule(_localctx, 106, RULE_genericsInstantiations);
		int _la;
		try {
			setState(779);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
			case 1:
				_localctx = new GenericsInstantiationEmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(766);
				match(T__18);
				setState(767);
				match(T__21);
				}
				break;
			case 2:
				_localctx = new GenericsInstantiationTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(768);
				match(T__18);
				setState(769);
				typeName(0);
				setState(774);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(770);
					match(T__1);
					setState(771);
					typeName(0);
					}
					}
					setState(776);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(777);
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
		enterRule(_localctx, 108, RULE_functionType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(781);
			match(FN);
			setState(783);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
			case 1:
				{
				setState(782);
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
		enterRule(_localctx, 110, RULE_namedTypeTuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(785);
			typeName(0);
			setState(786);
			match(ID);
			setState(793);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(787);
				match(T__1);
				setState(788);
				typeName(0);
				setState(789);
				match(ID);
				}
				}
				setState(795);
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
		case 40:
			return fqdn_sempred((FqdnContext)_localctx, predIndex);
		case 41:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 47:
			return typeName_sempred((TypeNameContext)_localctx, predIndex);
		case 48:
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
		"\u0004\u0001h\u031d\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"7\u00077\u0001\u0000\u0001\u0000\u0005\u0000s\b\u0000\n\u0000\f\u0000"+
		"v\t\u0000\u0001\u0000\u0005\u0000y\b\u0000\n\u0000\f\u0000|\t\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0003\u0002\u0083"+
		"\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003\u008b\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0003"+
		"\u0007\u0096\b\u0007\u0001\u0007\u0003\u0007\u0099\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u009d\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u00a3\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u00a8\b\u0007\u0003\u0007\u00aa\b\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u00af\b\u0007\u0001\u0007\u0003\u0007\u00b2"+
		"\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00b6\b\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u00ba\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u00bf\b\u0007\u0003\u0007\u00c1\b\u0007\u0001\u0007\u0003"+
		"\u0007\u00c4\b\u0007\u0001\b\u0001\b\u0001\b\u0003\b\u00c9\b\b\u0001\t"+
		"\u0003\t\u00cc\b\t\u0001\t\u0001\t\u0001\t\u0003\t\u00d1\b\t\u0001\t\u0001"+
		"\t\u0003\t\u00d5\b\t\u0001\t\u0001\t\u0005\t\u00d9\b\t\n\t\f\t\u00dc\t"+
		"\t\u0001\t\u0005\t\u00df\b\t\n\t\f\t\u00e2\t\t\u0001\t\u0001\t\u0001\n"+
		"\u0003\n\u00e7\b\n\u0001\n\u0001\n\u0001\n\u0003\n\u00ec\b\n\u0001\n\u0001"+
		"\n\u0003\n\u00f0\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u00fb\b\f\n\f\f\f\u00fe\t\f\u0001"+
		"\r\u0001\r\u0003\r\u0102\b\r\u0001\u000e\u0005\u000e\u0105\b\u000e\n\u000e"+
		"\f\u000e\u0108\t\u000e\u0001\u000f\u0003\u000f\u010b\b\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u010f\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u0115\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u011a\b\u000f\u0003\u000f\u011c\b\u000f\u0001\u000f\u0003"+
		"\u000f\u011f\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0123\b\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u0127\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u012c\b\u000f\u0003\u000f\u012e\b\u000f\u0003"+
		"\u000f\u0130\b\u000f\u0001\u0010\u0003\u0010\u0133\b\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0139\b\u0010\n\u0010"+
		"\f\u0010\u013c\t\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0003\u0011"+
		"\u0141\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011"+
		"\u0147\b\u0011\n\u0011\f\u0011\u014a\t\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0159"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0005"+
		"\u0014\u0160\b\u0014\n\u0014\f\u0014\u0163\t\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0003\u0016\u016e\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0003\u0016\u0175\b\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0003\u0016\u0180\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0003\u0017\u0187\b\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u018f\b\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0004\u0018\u0194\b\u0018\u000b\u0018"+
		"\f\u0018\u0195\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0004\u0018\u019e\b\u0018\u000b\u0018\f\u0018\u019f\u0001"+
		"\u0018\u0001\u0018\u0003\u0018\u01a4\b\u0018\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0003\u0019\u01a9\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a\u01b3"+
		"\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u01b8\b\u001b"+
		"\n\u001b\f\u001b\u01bb\t\u001b\u0001\u001b\u0003\u001b\u01be\b\u001b\u0001"+
		"\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0003\u001b\u01c5"+
		"\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u01d5\b\u001e\n\u001e\f\u001e"+
		"\u01d8\t\u001e\u0001\u001e\u0003\u001e\u01db\b\u001e\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0003\u001f\u01e5\b\u001f\u0001 \u0001 \u0001!\u0001!\u0001!\u0001"+
		"!\u0005!\u01ed\b!\n!\f!\u01f0\t!\u0001\"\u0001\"\u0001\"\u0003\"\u01f5"+
		"\b\"\u0001#\u0001#\u0003#\u01f9\b#\u0001$\u0004$\u01fc\b$\u000b$\f$\u01fd"+
		"\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0005%\u0208"+
		"\b%\n%\f%\u020b\t%\u0001%\u0001%\u0003%\u020f\b%\u0001&\u0001&\u0001&"+
		"\u0001&\u0003&\u0215\b&\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0005(\u021f\b(\n(\f(\u0222\t(\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0003)\u0230\b)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0004)\u023a\b)\u000b"+
		")\f)\u023b\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0003)\u024d\b)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0003)\u0276\b)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0003)\u027e\b)\u0001)\u0001)\u0003"+
		")\u0282\b)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0003"+
		")\u028c\b)\u0001)\u0001)\u0005)\u0290\b)\n)\f)\u0293\t)\u0001*\u0001*"+
		"\u0001*\u0005*\u0298\b*\n*\f*\u029b\t*\u0001*\u0001*\u0001*\u0001*\u0001"+
		"*\u0001*\u0001*\u0003*\u02a4\b*\u0001+\u0001+\u0001+\u0005+\u02a9\b+\n"+
		"+\f+\u02ac\t+\u0001,\u0001,\u0001,\u0001-\u0001-\u0001-\u0005-\u02b4\b"+
		"-\n-\f-\u02b7\t-\u0001.\u0003.\u02ba\b.\u0001.\u0001.\u0001.\u0003.\u02bf"+
		"\b.\u0001.\u0001.\u0003.\u02c3\b.\u0001/\u0001/\u0001/\u0001/\u0003/\u02c9"+
		"\b/\u0001/\u0001/\u0001/\u0004/\u02ce\b/\u000b/\f/\u02cf\u0005/\u02d2"+
		"\b/\n/\f/\u02d5\t/\u00010\u00010\u00010\u00030\u02da\b0\u00010\u00010"+
		"\u00010\u00040\u02df\b0\u000b0\f0\u02e0\u00050\u02e3\b0\n0\f0\u02e6\t"+
		"0\u00011\u00011\u00012\u00012\u00032\u02ec\b2\u00013\u00013\u00013\u0001"+
		"3\u00053\u02f2\b3\n3\f3\u02f5\t3\u00013\u00013\u00014\u00014\u00014\u0001"+
		"4\u00034\u02fd\b4\u00015\u00015\u00015\u00015\u00015\u00015\u00055\u0305"+
		"\b5\n5\f5\u0308\t5\u00015\u00015\u00035\u030c\b5\u00016\u00016\u00036"+
		"\u0310\b6\u00017\u00017\u00017\u00017\u00017\u00017\u00057\u0318\b7\n"+
		"7\f7\u031b\t7\u00017\u0000\u0004PR^`8\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF"+
		"HJLNPRTVXZ\\^`bdfhjln\u0000\n\u0002\u00003377\u0002\u0000)*gg\u0002\u0000"+
		"\u000b\u000bEE\u0002\u0000\u000e\u000edd\u0002\u0000\r\r\u0010\u0010\u0001"+
		"\u0000\u0011\u0012\u0001\u0000\u0013\u0016\u0001\u0000\u0017\u0018\u0001"+
		"\u0000\u001e&\u0002\u0000\'(U^\u0374\u0000p\u0001\u0000\u0000\u0000\u0002"+
		"}\u0001\u0000\u0000\u0000\u0004\u0080\u0001\u0000\u0000\u0000\u0006\u008a"+
		"\u0001\u0000\u0000\u0000\b\u008c\u0001\u0000\u0000\u0000\n\u008f\u0001"+
		"\u0000\u0000\u0000\f\u0092\u0001\u0000\u0000\u0000\u000e\u00c3\u0001\u0000"+
		"\u0000\u0000\u0010\u00c8\u0001\u0000\u0000\u0000\u0012\u00cb\u0001\u0000"+
		"\u0000\u0000\u0014\u00e6\u0001\u0000\u0000\u0000\u0016\u00f5\u0001\u0000"+
		"\u0000\u0000\u0018\u00f7\u0001\u0000\u0000\u0000\u001a\u00ff\u0001\u0000"+
		"\u0000\u0000\u001c\u0106\u0001\u0000\u0000\u0000\u001e\u012f\u0001\u0000"+
		"\u0000\u0000 \u0132\u0001\u0000\u0000\u0000\"\u0140\u0001\u0000\u0000"+
		"\u0000$\u0158\u0001\u0000\u0000\u0000&\u015a\u0001\u0000\u0000\u0000("+
		"\u015d\u0001\u0000\u0000\u0000*\u0166\u0001\u0000\u0000\u0000,\u017f\u0001"+
		"\u0000\u0000\u0000.\u018e\u0001\u0000\u0000\u00000\u01a3\u0001\u0000\u0000"+
		"\u00002\u01a8\u0001\u0000\u0000\u00004\u01b2\u0001\u0000\u0000\u00006"+
		"\u01c4\u0001\u0000\u0000\u00008\u01c6\u0001\u0000\u0000\u0000:\u01cd\u0001"+
		"\u0000\u0000\u0000<\u01da\u0001\u0000\u0000\u0000>\u01e4\u0001\u0000\u0000"+
		"\u0000@\u01e6\u0001\u0000\u0000\u0000B\u01e8\u0001\u0000\u0000\u0000D"+
		"\u01f1\u0001\u0000\u0000\u0000F\u01f8\u0001\u0000\u0000\u0000H\u01fb\u0001"+
		"\u0000\u0000\u0000J\u020e\u0001\u0000\u0000\u0000L\u0214\u0001\u0000\u0000"+
		"\u0000N\u0216\u0001\u0000\u0000\u0000P\u0218\u0001\u0000\u0000\u0000R"+
		"\u024c\u0001\u0000\u0000\u0000T\u02a3\u0001\u0000\u0000\u0000V\u02a5\u0001"+
		"\u0000\u0000\u0000X\u02ad\u0001\u0000\u0000\u0000Z\u02b0\u0001\u0000\u0000"+
		"\u0000\\\u02b9\u0001\u0000\u0000\u0000^\u02c8\u0001\u0000\u0000\u0000"+
		"`\u02d9\u0001\u0000\u0000\u0000b\u02e7\u0001\u0000\u0000\u0000d\u02e9"+
		"\u0001\u0000\u0000\u0000f\u02ed\u0001\u0000\u0000\u0000h\u02fc\u0001\u0000"+
		"\u0000\u0000j\u030b\u0001\u0000\u0000\u0000l\u030d\u0001\u0000\u0000\u0000"+
		"n\u0311\u0001\u0000\u0000\u0000pt\u0003\u0002\u0001\u0000qs\u0003\u0004"+
		"\u0002\u0000rq\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000tr\u0001"+
		"\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uz\u0001\u0000\u0000\u0000"+
		"vt\u0001\u0000\u0000\u0000wy\u0003\u0006\u0003\u0000xw\u0001\u0000\u0000"+
		"\u0000y|\u0001\u0000\u0000\u0000zx\u0001\u0000\u0000\u0000z{\u0001\u0000"+
		"\u0000\u0000{\u0001\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000"+
		"}~\u0005C\u0000\u0000~\u007f\u0003P(\u0000\u007f\u0003\u0001\u0000\u0000"+
		"\u0000\u0080\u0082\u0003\b\u0004\u0000\u0081\u0083\u0003\n\u0005\u0000"+
		"\u0082\u0081\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000"+
		"\u0083\u0005\u0001\u0000\u0000\u0000\u0084\u008b\u0003\f\u0006\u0000\u0085"+
		"\u008b\u0003 \u0010\u0000\u0086\u008b\u0003\u000e\u0007\u0000\u0087\u008b"+
		"\u0003\u0012\t\u0000\u0088\u008b\u0003\u0014\n\u0000\u0089\u008b\u0003"+
		"B!\u0000\u008a\u0084\u0001\u0000\u0000\u0000\u008a\u0085\u0001\u0000\u0000"+
		"\u0000\u008a\u0086\u0001\u0000\u0000\u0000\u008a\u0087\u0001\u0000\u0000"+
		"\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u0089\u0001\u0000\u0000"+
		"\u0000\u008b\u0007\u0001\u0000\u0000\u0000\u008c\u008d\u0005P\u0000\u0000"+
		"\u008d\u008e\u0003P(\u0000\u008e\t\u0001\u0000\u0000\u0000\u008f\u0090"+
		"\u00051\u0000\u0000\u0090\u0091\u0005f\u0000\u0000\u0091\u000b\u0001\u0000"+
		"\u0000\u0000\u0092\u0093\u0005_\u0000\u0000\u0093\r\u0001\u0000\u0000"+
		"\u0000\u0094\u0096\u0003H$\u0000\u0095\u0094\u0001\u0000\u0000\u0000\u0095"+
		"\u0096\u0001\u0000\u0000\u0000\u0096\u0098\u0001\u0000\u0000\u0000\u0097"+
		"\u0099\u0005K\u0000\u0000\u0098\u0097\u0001\u0000\u0000\u0000\u0098\u0099"+
		"\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009c"+
		"\u0005f\u0000\u0000\u009b\u009d\u0003f3\u0000\u009c\u009b\u0001\u0000"+
		"\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009e\u0001\u0000"+
		"\u0000\u0000\u009e\u009f\u0005\u0001\u0000\u0000\u009f\u00a2\u0003X,\u0000"+
		"\u00a0\u00a1\u0005\u0002\u0000\u0000\u00a1\u00a3\u0003Z-\u0000\u00a2\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a4"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a9\u0005\u0003\u0000\u0000\u00a5\u00a7"+
		"\u0005\u0004\u0000\u0000\u00a6\u00a8\u0003\u0010\b\u0000\u00a7\u00a6\u0001"+
		"\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00aa\u0001"+
		"\u0000\u0000\u0000\u00a9\u00a5\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001"+
		"\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u0003"+
		"(\u0014\u0000\u00ac\u00c4\u0001\u0000\u0000\u0000\u00ad\u00af\u0003H$"+
		"\u0000\u00ae\u00ad\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000"+
		"\u0000\u00af\u00b1\u0001\u0000\u0000\u0000\u00b0\u00b2\u0005K\u0000\u0000"+
		"\u00b1\u00b0\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b5\u0005f\u0000\u0000\u00b4"+
		"\u00b6\u0003f3\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b5\u00b6\u0001"+
		"\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00b9\u0005"+
		"\u0001\u0000\u0000\u00b8\u00ba\u0003Z-\u0000\u00b9\u00b8\u0001\u0000\u0000"+
		"\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000"+
		"\u0000\u00bb\u00c0\u0005\u0003\u0000\u0000\u00bc\u00be\u0005\u0004\u0000"+
		"\u0000\u00bd\u00bf\u0003\u0010\b\u0000\u00be\u00bd\u0001\u0000\u0000\u0000"+
		"\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c0\u00bc\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c4\u0003(\u0014\u0000\u00c3"+
		"\u0095\u0001\u0000\u0000\u0000\u00c3\u00ae\u0001\u0000\u0000\u0000\u00c4"+
		"\u000f\u0001\u0000\u0000\u0000\u00c5\u00c9\u0003n7\u0000\u00c6\u00c9\u0003"+
		"^/\u0000\u00c7\u00c9\u0005R\u0000\u0000\u00c8\u00c5\u0001\u0000\u0000"+
		"\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c7\u0001\u0000\u0000"+
		"\u0000\u00c9\u0011\u0001\u0000\u0000\u0000\u00ca\u00cc\u0003H$\u0000\u00cb"+
		"\u00ca\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000\u00cc"+
		"\u00cd\u0001\u0000\u0000\u0000\u00cd\u00ce\u00056\u0000\u0000\u00ce\u00d0"+
		"\u0003\u0016\u000b\u0000\u00cf\u00d1\u0003f3\u0000\u00d0\u00cf\u0001\u0000"+
		"\u0000\u0000\u00d0\u00d1\u0001\u0000\u0000\u0000\u00d1\u00d4\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\u0005>\u0000\u0000\u00d3\u00d5\u0003\u0018\f"+
		"\u0000\u00d4\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00da\u0005`\u0000\u0000"+
		"\u00d7\u00d9\u0003\"\u0011\u0000\u00d8\u00d7\u0001\u0000\u0000\u0000\u00d9"+
		"\u00dc\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000\u0000\u0000\u00da"+
		"\u00db\u0001\u0000\u0000\u0000\u00db\u00e0\u0001\u0000\u0000\u0000\u00dc"+
		"\u00da\u0001\u0000\u0000\u0000\u00dd\u00df\u0003\u000e\u0007\u0000\u00de"+
		"\u00dd\u0001\u0000\u0000\u0000\u00df\u00e2\u0001\u0000\u0000\u0000\u00e0"+
		"\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000\u0000\u00e1"+
		"\u00e3\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e4\u0005a\u0000\u0000\u00e4\u0013\u0001\u0000\u0000\u0000\u00e5\u00e7"+
		"\u0003H$\u0000\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000"+
		"\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u00e9\u0005A\u0000"+
		"\u0000\u00e9\u00eb\u0003\u0016\u000b\u0000\u00ea\u00ec\u0003f3\u0000\u00eb"+
		"\u00ea\u0001\u0000\u0000\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec"+
		"\u00ef\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005:\u0000\u0000\u00ee\u00f0"+
		"\u0003\u0018\f\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001"+
		"\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000\u0000\u00f1\u00f2\u0005"+
		"`\u0000\u0000\u00f2\u00f3\u0003\u001c\u000e\u0000\u00f3\u00f4\u0005a\u0000"+
		"\u0000\u00f4\u0015\u0001\u0000\u0000\u0000\u00f5\u00f6\u0005f\u0000\u0000"+
		"\u00f6\u0017\u0001\u0000\u0000\u0000\u00f7\u00fc\u0003\u001a\r\u0000\u00f8"+
		"\u00f9\u0005\u0002\u0000\u0000\u00f9\u00fb\u0003\u001a\r\u0000\u00fa\u00f8"+
		"\u0001\u0000\u0000\u0000\u00fb\u00fe\u0001\u0000\u0000\u0000\u00fc\u00fa"+
		"\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u0019"+
		"\u0001\u0000\u0000\u0000\u00fe\u00fc\u0001\u0000\u0000\u0000\u00ff\u0101"+
		"\u0003P(\u0000\u0100\u0102\u0003j5\u0000\u0101\u0100\u0001\u0000\u0000"+
		"\u0000\u0101\u0102\u0001\u0000\u0000\u0000\u0102\u001b\u0001\u0000\u0000"+
		"\u0000\u0103\u0105\u0003\u001e\u000f\u0000\u0104\u0103\u0001\u0000\u0000"+
		"\u0000\u0105\u0108\u0001\u0000\u0000\u0000\u0106\u0104\u0001\u0000\u0000"+
		"\u0000\u0106\u0107\u0001\u0000\u0000\u0000\u0107\u001d\u0001\u0000\u0000"+
		"\u0000\u0108\u0106\u0001\u0000\u0000\u0000\u0109\u010b\u0003H$\u0000\u010a"+
		"\u0109\u0001\u0000\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b"+
		"\u010c\u0001\u0000\u0000\u0000\u010c\u010e\u0005f\u0000\u0000\u010d\u010f"+
		"\u0003f3\u0000\u010e\u010d\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000"+
		"\u0000\u0000\u010f\u0110\u0001\u0000\u0000\u0000\u0110\u0111\u0005\u0001"+
		"\u0000\u0000\u0111\u0114\u0003X,\u0000\u0112\u0113\u0005\u0002\u0000\u0000"+
		"\u0113\u0115\u0003Z-\u0000\u0114\u0112\u0001\u0000\u0000\u0000\u0114\u0115"+
		"\u0001\u0000\u0000\u0000\u0115\u0116\u0001\u0000\u0000\u0000\u0116\u011b"+
		"\u0005\u0003\u0000\u0000\u0117\u0119\u0005\u0004\u0000\u0000\u0118\u011a"+
		"\u0003\u0010\b\u0000\u0119\u0118\u0001\u0000\u0000\u0000\u0119\u011a\u0001"+
		"\u0000\u0000\u0000\u011a\u011c\u0001\u0000\u0000\u0000\u011b\u0117\u0001"+
		"\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c\u0130\u0001"+
		"\u0000\u0000\u0000\u011d\u011f\u0003H$\u0000\u011e\u011d\u0001\u0000\u0000"+
		"\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f\u0120\u0001\u0000\u0000"+
		"\u0000\u0120\u0122\u0005f\u0000\u0000\u0121\u0123\u0003f3\u0000\u0122"+
		"\u0121\u0001\u0000\u0000\u0000\u0122\u0123\u0001\u0000\u0000\u0000\u0123"+
		"\u0124\u0001\u0000\u0000\u0000\u0124\u0126\u0005\u0001\u0000\u0000\u0125"+
		"\u0127\u0003Z-\u0000\u0126\u0125\u0001\u0000\u0000\u0000\u0126\u0127\u0001"+
		"\u0000\u0000\u0000\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u012d\u0005"+
		"\u0003\u0000\u0000\u0129\u012b\u0005\u0004\u0000\u0000\u012a\u012c\u0003"+
		"\u0010\b\u0000\u012b\u012a\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000"+
		"\u0000\u0000\u012c\u012e\u0001\u0000\u0000\u0000\u012d\u0129\u0001\u0000"+
		"\u0000\u0000\u012d\u012e\u0001\u0000\u0000\u0000\u012e\u0130\u0001\u0000"+
		"\u0000\u0000\u012f\u010a\u0001\u0000\u0000\u0000\u012f\u011e\u0001\u0000"+
		"\u0000\u0000\u0130\u001f\u0001\u0000\u0000\u0000\u0131\u0133\u0003H$\u0000"+
		"\u0132\u0131\u0001\u0000\u0000\u0000\u0132\u0133\u0001\u0000\u0000\u0000"+
		"\u0133\u0134\u0001\u0000\u0000\u0000\u0134\u0135\u0005M\u0000\u0000\u0135"+
		"\u0136\u0005f\u0000\u0000\u0136\u013a\u0005`\u0000\u0000\u0137\u0139\u0003"+
		"\"\u0011\u0000\u0138\u0137\u0001\u0000\u0000\u0000\u0139\u013c\u0001\u0000"+
		"\u0000\u0000\u013a\u0138\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000"+
		"\u0000\u0000\u013b\u013d\u0001\u0000\u0000\u0000\u013c\u013a\u0001\u0000"+
		"\u0000\u0000\u013d\u013e\u0005a\u0000\u0000\u013e!\u0001\u0000\u0000\u0000"+
		"\u013f\u0141\u0003H$\u0000\u0140\u013f\u0001\u0000\u0000\u0000\u0140\u0141"+
		"\u0001\u0000\u0000\u0000\u0141\u0142\u0001\u0000\u0000\u0000\u0142\u0143"+
		"\u0003^/\u0000\u0143\u0148\u0005f\u0000\u0000\u0144\u0145\u0005\u0002"+
		"\u0000\u0000\u0145\u0147\u0005f\u0000\u0000\u0146\u0144\u0001\u0000\u0000"+
		"\u0000\u0147\u014a\u0001\u0000\u0000\u0000\u0148\u0146\u0001\u0000\u0000"+
		"\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u0149#\u0001\u0000\u0000\u0000"+
		"\u014a\u0148\u0001\u0000\u0000\u0000\u014b\u0159\u0003\u000e\u0007\u0000"+
		"\u014c\u0159\u0003B!\u0000\u014d\u0159\u0003<\u001e\u0000\u014e\u0159"+
		"\u0003@ \u0000\u014f\u0159\u0003\f\u0006\u0000\u0150\u0159\u0003*\u0015"+
		"\u0000\u0151\u0159\u0003,\u0016\u0000\u0152\u0159\u0003.\u0017\u0000\u0153"+
		"\u0159\u00030\u0018\u0000\u0154\u0159\u00036\u001b\u0000\u0155\u0159\u0003"+
		"&\u0013\u0000\u0156\u0159\u0003R)\u0000\u0157\u0159\u0003T*\u0000\u0158"+
		"\u014b\u0001\u0000\u0000\u0000\u0158\u014c\u0001\u0000\u0000\u0000\u0158"+
		"\u014d\u0001\u0000\u0000\u0000\u0158\u014e\u0001\u0000\u0000\u0000\u0158"+
		"\u014f\u0001\u0000\u0000\u0000\u0158\u0150\u0001\u0000\u0000\u0000\u0158"+
		"\u0151\u0001\u0000\u0000\u0000\u0158\u0152\u0001\u0000\u0000\u0000\u0158"+
		"\u0153\u0001\u0000\u0000\u0000\u0158\u0154\u0001\u0000\u0000\u0000\u0158"+
		"\u0155\u0001\u0000\u0000\u0000\u0158\u0156\u0001\u0000\u0000\u0000\u0158"+
		"\u0157\u0001\u0000\u0000\u0000\u0159%\u0001\u0000\u0000\u0000\u015a\u015b"+
		"\u0005N\u0000\u0000\u015b\u015c\u0003R)\u0000\u015c\'\u0001\u0000\u0000"+
		"\u0000\u015d\u0161\u0005`\u0000\u0000\u015e\u0160\u0003$\u0012\u0000\u015f"+
		"\u015e\u0001\u0000\u0000\u0000\u0160\u0163\u0001\u0000\u0000\u0000\u0161"+
		"\u015f\u0001\u0000\u0000\u0000\u0161\u0162\u0001\u0000\u0000\u0000\u0162"+
		"\u0164\u0001\u0000\u0000\u0000\u0163\u0161\u0001\u0000\u0000\u0000\u0164"+
		"\u0165\u0005a\u0000\u0000\u0165)\u0001\u0000\u0000\u0000\u0166\u0167\u0005"+
		"S\u0000\u0000\u0167\u0168\u0003R)\u0000\u0168\u0169\u0003(\u0014\u0000"+
		"\u0169+\u0001\u0000\u0000\u0000\u016a\u016d\u0005<\u0000\u0000\u016b\u016e"+
		"\u0003T*\u0000\u016c\u016e\u0003B!\u0000\u016d\u016b\u0001\u0000\u0000"+
		"\u0000\u016d\u016c\u0001\u0000\u0000\u0000\u016e\u016f\u0001\u0000\u0000"+
		"\u0000\u016f\u0170\u0005\u0005\u0000\u0000\u0170\u0171\u0003R)\u0000\u0171"+
		"\u0174\u0005\u0005\u0000\u0000\u0172\u0175\u0003R)\u0000\u0173\u0175\u0003"+
		"T*\u0000\u0174\u0172\u0001\u0000\u0000\u0000\u0174\u0173\u0001\u0000\u0000"+
		"\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176\u0177\u0003(\u0014\u0000"+
		"\u0177\u0180\u0001\u0000\u0000\u0000\u0178\u0179\u0005<\u0000\u0000\u0179"+
		"\u017a\u0003^/\u0000\u017a\u017b\u0005f\u0000\u0000\u017b\u017c\u0005"+
		"?\u0000\u0000\u017c\u017d\u0003R)\u0000\u017d\u017e\u0003(\u0014\u0000"+
		"\u017e\u0180\u0001\u0000\u0000\u0000\u017f\u016a\u0001\u0000\u0000\u0000"+
		"\u017f\u0178\u0001\u0000\u0000\u0000\u0180-\u0001\u0000\u0000\u0000\u0181"+
		"\u0182\u0005=\u0000\u0000\u0182\u0186\u0003R)\u0000\u0183\u0187\u0003"+
		"(\u0014\u0000\u0184\u0187\u0003<\u001e\u0000\u0185\u0187\u0003@ \u0000"+
		"\u0186\u0183\u0001\u0000\u0000\u0000\u0186\u0184\u0001\u0000\u0000\u0000"+
		"\u0186\u0185\u0001\u0000\u0000\u0000\u0187\u018f\u0001\u0000\u0000\u0000"+
		"\u0188\u0189\u0005=\u0000\u0000\u0189\u018a\u0003R)\u0000\u018a\u018b"+
		"\u0003(\u0014\u0000\u018b\u018c\u00059\u0000\u0000\u018c\u018d\u0003("+
		"\u0014\u0000\u018d\u018f\u0001\u0000\u0000\u0000\u018e\u0181\u0001\u0000"+
		"\u0000\u0000\u018e\u0188\u0001\u0000\u0000\u0000\u018f/\u0001\u0000\u0000"+
		"\u0000\u0190\u0191\u0005L\u0000\u0000\u0191\u0193\u0005`\u0000\u0000\u0192"+
		"\u0194\u00034\u001a\u0000\u0193\u0192\u0001\u0000\u0000\u0000\u0194\u0195"+
		"\u0001\u0000\u0000\u0000\u0195\u0193\u0001\u0000\u0000\u0000\u0195\u0196"+
		"\u0001\u0000\u0000\u0000\u0196\u0197\u0001\u0000\u0000\u0000\u0197\u0198"+
		"\u0005a\u0000\u0000\u0198\u01a4\u0001\u0000\u0000\u0000\u0199\u019a\u0005"+
		"L\u0000\u0000\u019a\u019b\u0003R)\u0000\u019b\u019d\u0005`\u0000\u0000"+
		"\u019c\u019e\u00034\u001a\u0000\u019d\u019c\u0001\u0000\u0000\u0000\u019e"+
		"\u019f\u0001\u0000\u0000\u0000\u019f\u019d\u0001\u0000\u0000\u0000\u019f"+
		"\u01a0\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001\u0000\u0000\u0000\u01a1"+
		"\u01a2\u0005a\u0000\u0000\u01a2\u01a4\u0001\u0000\u0000\u0000\u01a3\u0190"+
		"\u0001\u0000\u0000\u0000\u01a3\u0199\u0001\u0000\u0000\u0000\u01a41\u0001"+
		"\u0000\u0000\u0000\u01a5\u01a9\u0003(\u0014\u0000\u01a6\u01a9\u0003<\u001e"+
		"\u0000\u01a7\u01a9\u0003@ \u0000\u01a8\u01a5\u0001\u0000\u0000\u0000\u01a8"+
		"\u01a6\u0001\u0000\u0000\u0000\u01a8\u01a7\u0001\u0000\u0000\u0000\u01a9"+
		"3\u0001\u0000\u0000\u0000\u01aa\u01ab\u00054\u0000\u0000\u01ab\u01ac\u0003"+
		"R)\u0000\u01ac\u01ad\u0005\u0006\u0000\u0000\u01ad\u01ae\u00032\u0019"+
		"\u0000\u01ae\u01b3\u0001\u0000\u0000\u0000\u01af\u01b0\u00058\u0000\u0000"+
		"\u01b0\u01b1\u0005\u0006\u0000\u0000\u01b1\u01b3\u00032\u0019\u0000\u01b2"+
		"\u01aa\u0001\u0000\u0000\u0000\u01b2\u01af\u0001\u0000\u0000\u0000\u01b3"+
		"5\u0001\u0000\u0000\u0000\u01b4\u01b5\u0005O\u0000\u0000\u01b5\u01b9\u0003"+
		"(\u0014\u0000\u01b6\u01b8\u00038\u001c\u0000\u01b7\u01b6\u0001\u0000\u0000"+
		"\u0000\u01b8\u01bb\u0001\u0000\u0000\u0000\u01b9\u01b7\u0001\u0000\u0000"+
		"\u0000\u01b9\u01ba\u0001\u0000\u0000\u0000\u01ba\u01bd\u0001\u0000\u0000"+
		"\u0000\u01bb\u01b9\u0001\u0000\u0000\u0000\u01bc\u01be\u0003:\u001d\u0000"+
		"\u01bd\u01bc\u0001\u0000\u0000\u0000\u01bd\u01be\u0001\u0000\u0000\u0000"+
		"\u01be\u01c5\u0001\u0000\u0000\u0000\u01bf\u01c0\u0005O\u0000\u0000\u01c0"+
		"\u01c1\u0003(\u0014\u0000\u01c1\u01c2\u0005;\u0000\u0000\u01c2\u01c3\u0003"+
		"(\u0014\u0000\u01c3\u01c5\u0001\u0000\u0000\u0000\u01c4\u01b4\u0001\u0000"+
		"\u0000\u0000\u01c4\u01bf\u0001\u0000\u0000\u0000\u01c57\u0001\u0000\u0000"+
		"\u0000\u01c6\u01c7\u00055\u0000\u0000\u01c7\u01c8\u0005\u0001\u0000\u0000"+
		"\u01c8\u01c9\u0003d2\u0000\u01c9\u01ca\u0005f\u0000\u0000\u01ca\u01cb"+
		"\u0005\u0003\u0000\u0000\u01cb\u01cc\u0003(\u0014\u0000\u01cc9\u0001\u0000"+
		"\u0000\u0000\u01cd\u01ce\u0005;\u0000\u0000\u01ce\u01cf\u0003(\u0014\u0000"+
		"\u01cf;\u0001\u0000\u0000\u0000\u01d0\u01d1\u0005J\u0000\u0000\u01d1\u01d6"+
		"\u0003R)\u0000\u01d2\u01d3\u0005\u0002\u0000\u0000\u01d3\u01d5\u0003R"+
		")\u0000\u01d4\u01d2\u0001\u0000\u0000\u0000\u01d5\u01d8\u0001\u0000\u0000"+
		"\u0000\u01d6\u01d4\u0001\u0000\u0000\u0000\u01d6\u01d7\u0001\u0000\u0000"+
		"\u0000\u01d7\u01db\u0001\u0000\u0000\u0000\u01d8\u01d6\u0001\u0000\u0000"+
		"\u0000\u01d9\u01db\u0005J\u0000\u0000\u01da\u01d0\u0001\u0000\u0000\u0000"+
		"\u01da\u01d9\u0001\u0000\u0000\u0000\u01db=\u0001\u0000\u0000\u0000\u01dc"+
		"\u01dd\u0005\u0007\u0000\u0000\u01dd\u01de\u0003R)\u0000\u01de\u01df\u0003"+
		"\u000e\u0007\u0000\u01df\u01e5\u0001\u0000\u0000\u0000\u01e0\u01e1\u0005"+
		"\u0007\u0000\u0000\u01e1\u01e2\u0003R)\u0000\u01e2\u01e3\u0003>\u001f"+
		"\u0000\u01e3\u01e5\u0001\u0000\u0000\u0000\u01e4\u01dc\u0001\u0000\u0000"+
		"\u0000\u01e4\u01e0\u0001\u0000\u0000\u0000\u01e5?\u0001\u0000\u0000\u0000"+
		"\u01e6\u01e7\u0007\u0000\u0000\u0000\u01e7A\u0001\u0000\u0000\u0000\u01e8"+
		"\u01e9\u0003F#\u0000\u01e9\u01ee\u0003D\"\u0000\u01ea\u01eb\u0005\u0002"+
		"\u0000\u0000\u01eb\u01ed\u0003D\"\u0000\u01ec\u01ea\u0001\u0000\u0000"+
		"\u0000\u01ed\u01f0\u0001\u0000\u0000\u0000\u01ee\u01ec\u0001\u0000\u0000"+
		"\u0000\u01ee\u01ef\u0001\u0000\u0000\u0000\u01efC\u0001\u0000\u0000\u0000"+
		"\u01f0\u01ee\u0001\u0000\u0000\u0000\u01f1\u01f4\u0005f\u0000\u0000\u01f2"+
		"\u01f3\u0005\b\u0000\u0000\u01f3\u01f5\u0003R)\u0000\u01f4\u01f2\u0001"+
		"\u0000\u0000\u0000\u01f4\u01f5\u0001\u0000\u0000\u0000\u01f5E\u0001\u0000"+
		"\u0000\u0000\u01f6\u01f9\u0005Q\u0000\u0000\u01f7\u01f9\u0003^/\u0000"+
		"\u01f8\u01f6\u0001\u0000\u0000\u0000\u01f8\u01f7\u0001\u0000\u0000\u0000"+
		"\u01f9G\u0001\u0000\u0000\u0000\u01fa\u01fc\u0003J%\u0000\u01fb\u01fa"+
		"\u0001\u0000\u0000\u0000\u01fc\u01fd\u0001\u0000\u0000\u0000\u01fd\u01fb"+
		"\u0001\u0000\u0000\u0000\u01fd\u01fe\u0001\u0000\u0000\u0000\u01feI\u0001"+
		"\u0000\u0000\u0000\u01ff\u0200\u0005c\u0000\u0000\u0200\u020f\u0003P("+
		"\u0000\u0201\u0202\u0005c\u0000\u0000\u0202\u0203\u0003P(\u0000\u0203"+
		"\u0204\u0005\u0001\u0000\u0000\u0204\u0209\u0003L&\u0000\u0205\u0206\u0005"+
		"\u0002\u0000\u0000\u0206\u0208\u0003L&\u0000\u0207\u0205\u0001\u0000\u0000"+
		"\u0000\u0208\u020b\u0001\u0000\u0000\u0000\u0209\u0207\u0001\u0000\u0000"+
		"\u0000\u0209\u020a\u0001\u0000\u0000\u0000\u020a\u020c\u0001\u0000\u0000"+
		"\u0000\u020b\u0209\u0001\u0000\u0000\u0000\u020c\u020d\u0005\u0003\u0000"+
		"\u0000\u020d\u020f\u0001\u0000\u0000\u0000\u020e\u01ff\u0001\u0000\u0000"+
		"\u0000\u020e\u0201\u0001\u0000\u0000\u0000\u020fK\u0001\u0000\u0000\u0000"+
		"\u0210\u0211\u0005f\u0000\u0000\u0211\u0212\u0005\b\u0000\u0000\u0212"+
		"\u0215\u0003N\'\u0000\u0213\u0215\u0003N\'\u0000\u0214\u0210\u0001\u0000"+
		"\u0000\u0000\u0214\u0213\u0001\u0000\u0000\u0000\u0215M\u0001\u0000\u0000"+
		"\u0000\u0216\u0217\u0007\u0001\u0000\u0000\u0217O\u0001\u0000\u0000\u0000"+
		"\u0218\u0219\u0006(\uffff\uffff\u0000\u0219\u021a\u0005f\u0000\u0000\u021a"+
		"\u0220\u0001\u0000\u0000\u0000\u021b\u021c\n\u0001\u0000\u0000\u021c\u021d"+
		"\u0005e\u0000\u0000\u021d\u021f\u0005f\u0000\u0000\u021e\u021b\u0001\u0000"+
		"\u0000\u0000\u021f\u0222\u0001\u0000\u0000\u0000\u0220\u021e\u0001\u0000"+
		"\u0000\u0000\u0220\u0221\u0001\u0000\u0000\u0000\u0221Q\u0001\u0000\u0000"+
		"\u0000\u0222\u0220\u0001\u0000\u0000\u0000\u0223\u0224\u0006)\uffff\uffff"+
		"\u0000\u0224\u024d\u0005g\u0000\u0000\u0225\u024d\u0005*\u0000\u0000\u0226"+
		"\u024d\u0005)\u0000\u0000\u0227\u024d\u0005f\u0000\u0000\u0228\u024d\u0005"+
		"G\u0000\u0000\u0229\u024d\u0005I\u0000\u0000\u022a\u024d\u0005F\u0000"+
		"\u0000\u022b\u022c\u0005D\u0000\u0000\u022c\u022d\u0003`0\u0000\u022d"+
		"\u022f\u0005\u0001\u0000\u0000\u022e\u0230\u0003V+\u0000\u022f\u022e\u0001"+
		"\u0000\u0000\u0000\u022f\u0230\u0001\u0000\u0000\u0000\u0230\u0231\u0001"+
		"\u0000\u0000\u0000\u0231\u0232\u0005\u0003\u0000\u0000\u0232\u024d\u0001"+
		"\u0000\u0000\u0000\u0233\u0234\u0005D\u0000\u0000\u0234\u0239\u0003`0"+
		"\u0000\u0235\u0236\u0005\t\u0000\u0000\u0236\u0237\u0003R)\u0000\u0237"+
		"\u0238\u0005\n\u0000\u0000\u0238\u023a\u0001\u0000\u0000\u0000\u0239\u0235"+
		"\u0001\u0000\u0000\u0000\u023a\u023b\u0001\u0000\u0000\u0000\u023b\u0239"+
		"\u0001\u0000\u0000\u0000\u023b\u023c\u0001\u0000\u0000\u0000\u023c\u024d"+
		"\u0001\u0000\u0000\u0000\u023d\u023e\u0005\u0001\u0000\u0000\u023e\u023f"+
		"\u0003^/\u0000\u023f\u0240\u0005\u0003\u0000\u0000\u0240\u0241\u0003R"+
		")\u0012\u0241\u024d\u0001\u0000\u0000\u0000\u0242\u0243\u0005\u0001\u0000"+
		"\u0000\u0243\u0244\u0003R)\u0000\u0244\u0245\u0005\u0003\u0000\u0000\u0245"+
		"\u024d\u0001\u0000\u0000\u0000\u0246\u0247\u0007\u0002\u0000\u0000\u0247"+
		"\u024d\u0003R)\u000f\u0248\u0249\u0005\f\u0000\u0000\u0249\u024d\u0003"+
		"R)\u000e\u024a\u024b\u0005\r\u0000\u0000\u024b\u024d\u0003R)\r\u024c\u0223"+
		"\u0001\u0000\u0000\u0000\u024c\u0225\u0001\u0000\u0000\u0000\u024c\u0226"+
		"\u0001\u0000\u0000\u0000\u024c\u0227\u0001\u0000\u0000\u0000\u024c\u0228"+
		"\u0001\u0000\u0000\u0000\u024c\u0229\u0001\u0000\u0000\u0000\u024c\u022a"+
		"\u0001\u0000\u0000\u0000\u024c\u022b\u0001\u0000\u0000\u0000\u024c\u0233"+
		"\u0001\u0000\u0000\u0000\u024c\u023d\u0001\u0000\u0000\u0000\u024c\u0242"+
		"\u0001\u0000\u0000\u0000\u024c\u0246\u0001\u0000\u0000\u0000\u024c\u0248"+
		"\u0001\u0000\u0000\u0000\u024c\u024a\u0001\u0000\u0000\u0000\u024d\u0291"+
		"\u0001\u0000\u0000\u0000\u024e\u024f\n\f\u0000\u0000\u024f\u0250\u0007"+
		"\u0003\u0000\u0000\u0250\u0290\u0003R)\r\u0251\u0252\n\u000b\u0000\u0000"+
		"\u0252\u0253\u0005\u000f\u0000\u0000\u0253\u0290\u0003R)\f\u0254\u0255"+
		"\n\n\u0000\u0000\u0255\u0256\u0007\u0004\u0000\u0000\u0256\u0290\u0003"+
		"R)\u000b\u0257\u0258\n\t\u0000\u0000\u0258\u0259\u0007\u0005\u0000\u0000"+
		"\u0259\u0290\u0003R)\n\u025a\u025b\n\b\u0000\u0000\u025b\u025c\u0007\u0006"+
		"\u0000\u0000\u025c\u0290\u0003R)\t\u025d\u025e\n\u0007\u0000\u0000\u025e"+
		"\u025f\u0007\u0007\u0000\u0000\u025f\u0290\u0003R)\b\u0260\u0261\n\u0006"+
		"\u0000\u0000\u0261\u0262\u0005\u0019\u0000\u0000\u0262\u0290\u0003R)\u0007"+
		"\u0263\u0264\n\u0005\u0000\u0000\u0264\u0265\u0005\u001a\u0000\u0000\u0265"+
		"\u0290\u0003R)\u0006\u0266\u0267\n\u0004\u0000\u0000\u0267\u0268\u0005"+
		"\u001b\u0000\u0000\u0268\u0290\u0003R)\u0005\u0269\u026a\n\u0003\u0000"+
		"\u0000\u026a\u026b\u00052\u0000\u0000\u026b\u0290\u0003R)\u0004\u026c"+
		"\u026d\n\u0002\u0000\u0000\u026d\u026e\u0005H\u0000\u0000\u026e\u0290"+
		"\u0003R)\u0003\u026f\u0275\n\u0001\u0000\u0000\u0270\u0271\u0005\u001c"+
		"\u0000\u0000\u0271\u0272\u0003R)\u0000\u0272\u0273\u0005\u0006\u0000\u0000"+
		"\u0273\u0276\u0001\u0000\u0000\u0000\u0274\u0276\u0005\u001d\u0000\u0000"+
		"\u0275\u0270\u0001\u0000\u0000\u0000\u0275\u0274\u0001\u0000\u0000\u0000"+
		"\u0276\u0277\u0001\u0000\u0000\u0000\u0277\u0290\u0003R)\u0002\u0278\u0279"+
		"\n\u0017\u0000\u0000\u0279\u027a\u0005e\u0000\u0000\u027a\u0290\u0005"+
		"f\u0000\u0000\u027b\u027d\n\u0014\u0000\u0000\u027c\u027e\u0003j5\u0000"+
		"\u027d\u027c\u0001\u0000\u0000\u0000\u027d\u027e\u0001\u0000\u0000\u0000"+
		"\u027e\u027f\u0001\u0000\u0000\u0000\u027f\u0281\u0005\u0001\u0000\u0000"+
		"\u0280\u0282\u0003V+\u0000\u0281\u0280\u0001\u0000\u0000\u0000\u0281\u0282"+
		"\u0001\u0000\u0000\u0000\u0282\u0283\u0001\u0000\u0000\u0000\u0283\u0290"+
		"\u0005\u0003\u0000\u0000\u0284\u0285\n\u0013\u0000\u0000\u0285\u0286\u0005"+
		"\t\u0000\u0000\u0286\u0287\u0003R)\u0000\u0287\u0288\u0005\n\u0000\u0000"+
		"\u0288\u0290\u0001\u0000\u0000\u0000\u0289\u028b\n\u0010\u0000\u0000\u028a"+
		"\u028c\u0005E\u0000\u0000\u028b\u028a\u0001\u0000\u0000\u0000\u028b\u028c"+
		"\u0001\u0000\u0000\u0000\u028c\u028d\u0001\u0000\u0000\u0000\u028d\u028e"+
		"\u0005@\u0000\u0000\u028e\u0290\u0003`0\u0000\u028f\u024e\u0001\u0000"+
		"\u0000\u0000\u028f\u0251\u0001\u0000\u0000\u0000\u028f\u0254\u0001\u0000"+
		"\u0000\u0000\u028f\u0257\u0001\u0000\u0000\u0000\u028f\u025a\u0001\u0000"+
		"\u0000\u0000\u028f\u025d\u0001\u0000\u0000\u0000\u028f\u0260\u0001\u0000"+
		"\u0000\u0000\u028f\u0263\u0001\u0000\u0000\u0000\u028f\u0266\u0001\u0000"+
		"\u0000\u0000\u028f\u0269\u0001\u0000\u0000\u0000\u028f\u026c\u0001\u0000"+
		"\u0000\u0000\u028f\u026f\u0001\u0000\u0000\u0000\u028f\u0278\u0001\u0000"+
		"\u0000\u0000\u028f\u027b\u0001\u0000\u0000\u0000\u028f\u0284\u0001\u0000"+
		"\u0000\u0000\u028f\u0289\u0001\u0000\u0000\u0000\u0290\u0293\u0001\u0000"+
		"\u0000\u0000\u0291\u028f\u0001\u0000\u0000\u0000\u0291\u0292\u0001\u0000"+
		"\u0000\u0000\u0292S\u0001\u0000\u0000\u0000\u0293\u0291\u0001\u0000\u0000"+
		"\u0000\u0294\u0299\u0003R)\u0000\u0295\u0296\u0005\u0002\u0000\u0000\u0296"+
		"\u0298\u0003R)\u0000\u0297\u0295\u0001\u0000\u0000\u0000\u0298\u029b\u0001"+
		"\u0000\u0000\u0000\u0299\u0297\u0001\u0000\u0000\u0000\u0299\u029a\u0001"+
		"\u0000\u0000\u0000\u029a\u029c\u0001\u0000\u0000\u0000\u029b\u0299\u0001"+
		"\u0000\u0000\u0000\u029c\u029d\u0005\b\u0000\u0000\u029d\u029e\u0003R"+
		")\u0000\u029e\u02a4\u0001\u0000\u0000\u0000\u029f\u02a0\u0003R)\u0000"+
		"\u02a0\u02a1\u0007\b\u0000\u0000\u02a1\u02a2\u0003R)\u0000\u02a2\u02a4"+
		"\u0001\u0000\u0000\u0000\u02a3\u0294\u0001\u0000\u0000\u0000\u02a3\u029f"+
		"\u0001\u0000\u0000\u0000\u02a4U\u0001\u0000\u0000\u0000\u02a5\u02aa\u0003"+
		"R)\u0000\u02a6\u02a7\u0005\u0002\u0000\u0000\u02a7\u02a9\u0003R)\u0000"+
		"\u02a8\u02a6\u0001\u0000\u0000\u0000\u02a9\u02ac\u0001\u0000\u0000\u0000"+
		"\u02aa\u02a8\u0001\u0000\u0000\u0000\u02aa\u02ab\u0001\u0000\u0000\u0000"+
		"\u02abW\u0001\u0000\u0000\u0000\u02ac\u02aa\u0001\u0000\u0000\u0000\u02ad"+
		"\u02ae\u0005:\u0000\u0000\u02ae\u02af\u0003d2\u0000\u02afY\u0001\u0000"+
		"\u0000\u0000\u02b0\u02b5\u0003\\.\u0000\u02b1\u02b2\u0005\u0002\u0000"+
		"\u0000\u02b2\u02b4\u0003\\.\u0000\u02b3\u02b1\u0001\u0000\u0000\u0000"+
		"\u02b4\u02b7\u0001\u0000\u0000\u0000\u02b5\u02b3\u0001\u0000\u0000\u0000"+
		"\u02b5\u02b6\u0001\u0000\u0000\u0000\u02b6[\u0001\u0000\u0000\u0000\u02b7"+
		"\u02b5\u0001\u0000\u0000\u0000\u02b8\u02ba\u0003H$\u0000\u02b9\u02b8\u0001"+
		"\u0000\u0000\u0000\u02b9\u02ba\u0001\u0000\u0000\u0000\u02ba\u02bb\u0001"+
		"\u0000\u0000\u0000\u02bb\u02bc\u0003^/\u0000\u02bc\u02be\u0005f\u0000"+
		"\u0000\u02bd\u02bf\u0005d\u0000\u0000\u02be\u02bd\u0001\u0000\u0000\u0000"+
		"\u02be\u02bf\u0001\u0000\u0000\u0000\u02bf\u02c2\u0001\u0000\u0000\u0000"+
		"\u02c0\u02c1\u0005\b\u0000\u0000\u02c1\u02c3\u0003R)\u0000\u02c2\u02c0"+
		"\u0001\u0000\u0000\u0000\u02c2\u02c3\u0001\u0000\u0000\u0000\u02c3]\u0001"+
		"\u0000\u0000\u0000\u02c4\u02c5\u0006/\uffff\uffff\u0000\u02c5\u02c9\u0003"+
		"d2\u0000\u02c6\u02c9\u0003b1\u0000\u02c7\u02c9\u0003l6\u0000\u02c8\u02c4"+
		"\u0001\u0000\u0000\u0000\u02c8\u02c6\u0001\u0000\u0000\u0000\u02c8\u02c7"+
		"\u0001\u0000\u0000\u0000\u02c9\u02d3\u0001\u0000\u0000\u0000\u02ca\u02cd"+
		"\n\u0001\u0000\u0000\u02cb\u02cc\u0005\t\u0000\u0000\u02cc\u02ce\u0005"+
		"\n\u0000\u0000\u02cd\u02cb\u0001\u0000\u0000\u0000\u02ce\u02cf\u0001\u0000"+
		"\u0000\u0000\u02cf\u02cd\u0001\u0000\u0000\u0000\u02cf\u02d0\u0001\u0000"+
		"\u0000\u0000\u02d0\u02d2\u0001\u0000\u0000\u0000\u02d1\u02ca\u0001\u0000"+
		"\u0000\u0000\u02d2\u02d5\u0001\u0000\u0000\u0000\u02d3\u02d1\u0001\u0000"+
		"\u0000\u0000\u02d3\u02d4\u0001\u0000\u0000\u0000\u02d4_\u0001\u0000\u0000"+
		"\u0000\u02d5\u02d3\u0001\u0000\u0000\u0000\u02d6\u02d7\u00060\uffff\uffff"+
		"\u0000\u02d7\u02da\u0003d2\u0000\u02d8\u02da\u0003b1\u0000\u02d9\u02d6"+
		"\u0001\u0000\u0000\u0000\u02d9\u02d8\u0001\u0000\u0000\u0000\u02da\u02e4"+
		"\u0001\u0000\u0000\u0000\u02db\u02de\n\u0001\u0000\u0000\u02dc\u02dd\u0005"+
		"\t\u0000\u0000\u02dd\u02df\u0005\n\u0000\u0000\u02de\u02dc\u0001\u0000"+
		"\u0000\u0000\u02df\u02e0\u0001\u0000\u0000\u0000\u02e0\u02de\u0001\u0000"+
		"\u0000\u0000\u02e0\u02e1\u0001\u0000\u0000\u0000\u02e1\u02e3\u0001\u0000"+
		"\u0000\u0000\u02e2\u02db\u0001\u0000\u0000\u0000\u02e3\u02e6\u0001\u0000"+
		"\u0000\u0000\u02e4\u02e2\u0001\u0000\u0000\u0000\u02e4\u02e5\u0001\u0000"+
		"\u0000\u0000\u02e5a\u0001\u0000\u0000\u0000\u02e6\u02e4\u0001\u0000\u0000"+
		"\u0000\u02e7\u02e8\u0007\t\u0000\u0000\u02e8c\u0001\u0000\u0000\u0000"+
		"\u02e9\u02eb\u0003P(\u0000\u02ea\u02ec\u0003j5\u0000\u02eb\u02ea\u0001"+
		"\u0000\u0000\u0000\u02eb\u02ec\u0001\u0000\u0000\u0000\u02ece\u0001\u0000"+
		"\u0000\u0000\u02ed\u02ee\u0005\u0013\u0000\u0000\u02ee\u02f3\u0003h4\u0000"+
		"\u02ef\u02f0\u0005\u0002\u0000\u0000\u02f0\u02f2\u0003h4\u0000\u02f1\u02ef"+
		"\u0001\u0000\u0000\u0000\u02f2\u02f5\u0001\u0000\u0000\u0000\u02f3\u02f1"+
		"\u0001\u0000\u0000\u0000\u02f3\u02f4\u0001\u0000\u0000\u0000\u02f4\u02f6"+
		"\u0001\u0000\u0000\u0000\u02f5\u02f3\u0001\u0000\u0000\u0000\u02f6\u02f7"+
		"\u0005\u0016\u0000\u0000\u02f7g\u0001\u0000\u0000\u0000\u02f8\u02fd\u0005"+
		"f\u0000\u0000\u02f9\u02fa\u0005f\u0000\u0000\u02fa\u02fb\u0005B\u0000"+
		"\u0000\u02fb\u02fd\u0003^/\u0000\u02fc\u02f8\u0001\u0000\u0000\u0000\u02fc"+
		"\u02f9\u0001\u0000\u0000\u0000\u02fdi\u0001\u0000\u0000\u0000\u02fe\u02ff"+
		"\u0005\u0013\u0000\u0000\u02ff\u030c\u0005\u0016\u0000\u0000\u0300\u0301"+
		"\u0005\u0013\u0000\u0000\u0301\u0306\u0003^/\u0000\u0302\u0303\u0005\u0002"+
		"\u0000\u0000\u0303\u0305\u0003^/\u0000\u0304\u0302\u0001\u0000\u0000\u0000"+
		"\u0305\u0308\u0001\u0000\u0000\u0000\u0306\u0304\u0001\u0000\u0000\u0000"+
		"\u0306\u0307\u0001\u0000\u0000\u0000\u0307\u0309\u0001\u0000\u0000\u0000"+
		"\u0308\u0306\u0001\u0000\u0000\u0000\u0309\u030a\u0005\u0016\u0000\u0000"+
		"\u030a\u030c\u0001\u0000\u0000\u0000\u030b\u02fe\u0001\u0000\u0000\u0000"+
		"\u030b\u0300\u0001\u0000\u0000\u0000\u030ck\u0001\u0000\u0000\u0000\u030d"+
		"\u030f\u0005b\u0000\u0000\u030e\u0310\u0003j5\u0000\u030f\u030e\u0001"+
		"\u0000\u0000\u0000\u030f\u0310\u0001\u0000\u0000\u0000\u0310m\u0001\u0000"+
		"\u0000\u0000\u0311\u0312\u0003^/\u0000\u0312\u0319\u0005f\u0000\u0000"+
		"\u0313\u0314\u0005\u0002\u0000\u0000\u0314\u0315\u0003^/\u0000\u0315\u0316"+
		"\u0005f\u0000\u0000\u0316\u0318\u0001\u0000\u0000\u0000\u0317\u0313\u0001"+
		"\u0000\u0000\u0000\u0318\u031b\u0001\u0000\u0000\u0000\u0319\u0317\u0001"+
		"\u0000\u0000\u0000\u0319\u031a\u0001\u0000\u0000\u0000\u031ao\u0001\u0000"+
		"\u0000\u0000\u031b\u0319\u0001\u0000\u0000\u0000ctz\u0082\u008a\u0095"+
		"\u0098\u009c\u00a2\u00a7\u00a9\u00ae\u00b1\u00b5\u00b9\u00be\u00c0\u00c3"+
		"\u00c8\u00cb\u00d0\u00d4\u00da\u00e0\u00e6\u00eb\u00ef\u00fc\u0101\u0106"+
		"\u010a\u010e\u0114\u0119\u011b\u011e\u0122\u0126\u012b\u012d\u012f\u0132"+
		"\u013a\u0140\u0148\u0158\u0161\u016d\u0174\u017f\u0186\u018e\u0195\u019f"+
		"\u01a3\u01a8\u01b2\u01b9\u01bd\u01c4\u01d6\u01da\u01e4\u01ee\u01f4\u01f8"+
		"\u01fd\u0209\u020e\u0214\u0220\u022f\u023b\u024c\u0275\u027d\u0281\u028b"+
		"\u028f\u0291\u0299\u02a3\u02aa\u02b5\u02b9\u02be\u02c2\u02c8\u02cf\u02d3"+
		"\u02d9\u02e0\u02e4\u02eb\u02f3\u02fc\u0306\u030b\u030f\u0319";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}