// Generated from /home/raptor/projects/caffc/caffc.g4 by ANTLR 4.13.2
package com.germaniumhq.caffc.generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class caffcParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, STRING=43, COMMENT=44, BLOCK_COMMENT=45, 
		WS=46, AS=47, AND=48, BREAK=49, CATCH=50, CLASS=51, CONTINUE=52, ELSE=53, 
		EXTENDS=54, FINALLY=55, FOR=56, IF=57, IMPLEMENTS=58, IN=59, INTERFACE=60, 
		IS=61, MODULE=62, NEW=63, NOT=64, OR=65, RETURN=66, STATIC=67, TAG=68, 
		THROW=69, TRY=70, USE=71, VAR=72, VOID=73, WHILE=74, YIELD=75, U8=76, 
		I8=77, U16=78, I16=79, U32=80, I32=81, U64=82, I64=83, F32=84, F64=85, 
		NATIVE=86, FN=87, SHARP=88, STAR=89, DOT=90, ID=91, NUMBER=92, DigitSequence=93;
	public static final int
		RULE_compilationUnit = 0, RULE_module = 1, RULE_useStatement = 2, RULE_compileBlock = 3, 
		RULE_use = 4, RULE_use_alias = 5, RULE_nativeBlock = 6, RULE_function = 7, 
		RULE_returnType = 8, RULE_classDefinition = 9, RULE_interfaceDefinition = 10, 
		RULE_name = 11, RULE_interfaceImplementations = 12, RULE_interfaceImplementation = 13, 
		RULE_interfaceStatements = 14, RULE_functionDeclaration = 15, RULE_tagDefinition = 16, 
		RULE_fieldDeclaration = 17, RULE_statement = 18, RULE_block = 19, RULE_whileBlock = 20, 
		RULE_forBlock = 21, RULE_ifBlock = 22, RULE_return = 23, RULE_decoratorCall = 24, 
		RULE_controlFlow = 25, RULE_variableDeclarations = 26, RULE_variableDeclaration = 27, 
		RULE_varType = 28, RULE_tags = 29, RULE_tag = 30, RULE_tagParam = 31, 
		RULE_constExpression = 32, RULE_fqdn = 33, RULE_expression = 34, RULE_assignExpression = 35, 
		RULE_expressionTuple = 36, RULE_extend = 37, RULE_parameterDefinitions = 38, 
		RULE_parameterDefinition = 39, RULE_typeName = 40, RULE_newType = 41, 
		RULE_primitiveTypeName = 42, RULE_classType = 43, RULE_genericsDeclarations = 44, 
		RULE_genericDeclaration = 45, RULE_genericsInstantiations = 46, RULE_functionType = 47, 
		RULE_namedTypeTuple = 48;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilationUnit", "module", "useStatement", "compileBlock", "use", "use_alias", 
			"nativeBlock", "function", "returnType", "classDefinition", "interfaceDefinition", 
			"name", "interfaceImplementations", "interfaceImplementation", "interfaceStatements", 
			"functionDeclaration", "tagDefinition", "fieldDeclaration", "statement", 
			"block", "whileBlock", "forBlock", "ifBlock", "return", "decoratorCall", 
			"controlFlow", "variableDeclarations", "variableDeclaration", "varType", 
			"tags", "tag", "tagParam", "constExpression", "fqdn", "expression", "assignExpression", 
			"expressionTuple", "extend", "parameterDefinitions", "parameterDefinition", 
			"typeName", "newType", "primitiveTypeName", "classType", "genericsDeclarations", 
			"genericDeclaration", "genericsInstantiations", "functionType", "namedTypeTuple"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "','", "')'", "'->'", "'{'", "'}'", "';'", "'@'", "'='", 
			"'['", "']'", "'!'", "'~'", "'-'", "'%'", "'/'", "'+'", "'<<'", "'>>'", 
			"'<'", "'<='", "'>='", "'>'", "'=='", "'!='", "'&'", "'^'", "'|'", "'?'", 
			"':'", "'?:'", "'<<='", "'>>='", "'&='", "'|='", "'^='", "'*='", "'/='", 
			"'+='", "'-='", "'ptr'", "'bool'", null, null, null, null, "'as'", "'and'", 
			"'break'", "'catch'", "'class'", "'continue'", "'else'", "'extends'", 
			"'finally'", "'for'", "'if'", "'implements'", "'in'", "'interface'", 
			"'is'", "'module'", "'new'", "'not'", "'or'", "'return'", "'static'", 
			"'tag'", "'throw'", "'try'", "'use'", "'var'", "'void'", "'while'", "'yield'", 
			"'u8'", "'i8'", "'u16'", "'i16'", "'u32'", "'i32'", "'u64'", "'i64'", 
			"'f32'", "'f64'", null, "'fn'", "'#'", "'*'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "STRING", "COMMENT", "BLOCK_COMMENT", 
			"WS", "AS", "AND", "BREAK", "CATCH", "CLASS", "CONTINUE", "ELSE", "EXTENDS", 
			"FINALLY", "FOR", "IF", "IMPLEMENTS", "IN", "INTERFACE", "IS", "MODULE", 
			"NEW", "NOT", "OR", "RETURN", "STATIC", "TAG", "THROW", "TRY", "USE", 
			"VAR", "VOID", "WHILE", "YIELD", "U8", "I8", "U16", "I16", "U32", "I32", 
			"U64", "I64", "F32", "F64", "NATIVE", "FN", "SHARP", "STAR", "DOT", "ID", 
			"NUMBER", "DigitSequence"
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
			setState(98);
			module();
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==USE) {
				{
				{
				setState(99);
				useStatement();
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 51)) & ~0x3f) == 0 && ((1L << (_la - 51)) & 1271310516737L) != 0)) {
				{
				{
				setState(105);
				compileBlock();
				}
				}
				setState(110);
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
			setState(111);
			match(MODULE);
			setState(112);
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
			setState(114);
			use();
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(115);
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
		public CompileBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compileBlock; }
	}

	public final CompileBlockContext compileBlock() throws RecognitionException {
		CompileBlockContext _localctx = new CompileBlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_compileBlock);
		try {
			setState(123);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				nativeBlock();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				tagDefinition();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(120);
				function();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(121);
				classDefinition();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(122);
				interfaceDefinition();
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
			setState(125);
			match(USE);
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
			setState(128);
			match(AS);
			setState(129);
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
			setState(131);
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
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(133);
					tags();
					}
				}

				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(136);
					match(STATIC);
					}
				}

				setState(139);
				match(ID);
				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__19) {
					{
					setState(140);
					genericsDeclarations();
					}
				}

				setState(143);
				match(T__0);
				setState(144);
				extend();
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(145);
					match(T__1);
					setState(146);
					parameterDefinitions();
					}
				}

				setState(149);
				match(T__2);
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(150);
					match(T__3);
					setState(152);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 1231422958338051L) != 0)) {
						{
						setState(151);
						returnType();
						}
					}

					}
				}

				setState(156);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(158);
					tags();
					}
				}

				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(161);
					match(STATIC);
					}
				}

				setState(164);
				match(ID);
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__19) {
					{
					setState(165);
					genericsDeclarations();
					}
				}

				setState(168);
				match(T__0);
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 1372156151726083L) != 0)) {
					{
					setState(169);
					parameterDefinitions();
					}
				}

				setState(172);
				match(T__2);
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(173);
					match(T__3);
					setState(175);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 1231422958338051L) != 0)) {
						{
						setState(174);
						returnType();
						}
					}

					}
				}

				setState(179);
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
			setState(185);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(182);
				namedTypeTuple();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(183);
				typeName(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(184);
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
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(187);
				tags();
				}
			}

			setState(190);
			match(CLASS);
			setState(191);
			name();
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(192);
				genericsDeclarations();
				}
			}

			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(195);
				match(IMPLEMENTS);
				setState(196);
				interfaceImplementations();
				}
			}

			setState(199);
			match(T__4);
			setState(203);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(200);
					fieldDeclaration();
					}
					} 
				}
				setState(205);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & 18874369L) != 0)) {
				{
				{
				setState(206);
				function();
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(212);
			match(T__5);
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
		public InterfaceStatementsContext interfaceStatements() {
			return getRuleContext(InterfaceStatementsContext.class,0);
		}
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
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(214);
				tags();
				}
			}

			setState(217);
			match(INTERFACE);
			setState(218);
			name();
			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(219);
				genericsDeclarations();
				}
			}

			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(222);
				match(EXTENDS);
				setState(223);
				interfaceImplementations();
				}
			}

			setState(226);
			match(T__4);
			setState(227);
			interfaceStatements();
			setState(228);
			match(T__5);
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
			setState(230);
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
			setState(232);
			interfaceImplementation();
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(233);
				match(T__1);
				setState(234);
				interfaceImplementation();
				}
				}
				setState(239);
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
			setState(240);
			fqdn(0);
			setState(242);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(241);
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
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==ID) {
				{
				{
				setState(244);
				functionDeclaration();
				}
				}
				setState(249);
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
			setState(288);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(250);
					tags();
					}
				}

				setState(253);
				match(ID);
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__19) {
					{
					setState(254);
					genericsDeclarations();
					}
				}

				setState(257);
				match(T__0);
				setState(258);
				extend();
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(259);
					match(T__1);
					setState(260);
					parameterDefinitions();
					}
				}

				setState(263);
				match(T__2);
				setState(268);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(264);
					match(T__3);
					setState(266);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
					case 1:
						{
						setState(265);
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
				setState(271);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(270);
					tags();
					}
				}

				setState(273);
				match(ID);
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__19) {
					{
					setState(274);
					genericsDeclarations();
					}
				}

				setState(277);
				match(T__0);
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 1372156151726083L) != 0)) {
					{
					setState(278);
					parameterDefinitions();
					}
				}

				setState(281);
				match(T__2);
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(282);
					match(T__3);
					setState(284);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
					case 1:
						{
						setState(283);
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
			setState(291);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(290);
				tags();
				}
			}

			setState(293);
			match(TAG);
			setState(294);
			match(ID);
			setState(295);
			match(T__4);
			setState(299);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 1231418663370755L) != 0)) {
				{
				{
				setState(296);
				fieldDeclaration();
				}
				}
				setState(301);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(302);
			match(T__5);
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
			setState(304);
			typeName(0);
			setState(305);
			match(ID);
			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(306);
				match(T__1);
				setState(307);
				match(ID);
				}
				}
				setState(312);
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
			setState(323);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(314);
				variableDeclarations();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(315);
				return_();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(316);
				controlFlow();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(317);
				nativeBlock();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(318);
				whileBlock();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(319);
				forBlock();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(320);
				ifBlock();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(321);
				expression(0);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(322);
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
	public static class BlockContext extends ParserRuleContext {
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
		enterRule(_localctx, 38, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			match(T__4);
			setState(329);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9002117311997382654L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 436204813L) != 0)) {
				{
				{
				setState(326);
				statement();
				}
				}
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(332);
			match(T__5);
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
		enterRule(_localctx, 40, RULE_whileBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			match(WHILE);
			setState(335);
			expression(0);
			setState(336);
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
		public ForBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forBlock; }
	}

	public final ForBlockContext forBlock() throws RecognitionException {
		ForBlockContext _localctx = new ForBlockContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_forBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			match(FOR);
			setState(341);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(339);
				((ForBlockContext)_localctx).initExpression = assignExpression();
				}
				break;
			case 2:
				{
				setState(340);
				variableDeclarations();
				}
				break;
			}
			setState(343);
			match(T__6);
			setState(344);
			((ForBlockContext)_localctx).conditionExpression = expression(0);
			setState(345);
			match(T__6);
			setState(348);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(346);
				((ForBlockContext)_localctx).incrementExpression = expression(0);
				}
				break;
			case 2:
				{
				setState(347);
				((ForBlockContext)_localctx).incrementAssignExpression = assignExpression();
				}
				break;
			}
			setState(350);
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
		enterRule(_localctx, 44, RULE_ifBlock);
		try {
			setState(365);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(352);
				match(IF);
				setState(353);
				expression(0);
				setState(357);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
					{
					setState(354);
					((IfBlockContext)_localctx).trueBlock = block();
					}
					break;
				case RETURN:
					{
					setState(355);
					return_();
					}
					break;
				case BREAK:
				case CONTINUE:
					{
					setState(356);
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
				setState(359);
				match(IF);
				setState(360);
				expression(0);
				setState(361);
				((IfBlockContext)_localctx).trueBlock = block();
				setState(362);
				match(ELSE);
				setState(363);
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
		enterRule(_localctx, 46, RULE_return);
		int _la;
		try {
			setState(377);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(367);
				match(RETURN);
				setState(368);
				expression(0);
				setState(373);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(369);
					match(T__1);
					setState(370);
					expression(0);
					}
					}
					setState(375);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(376);
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
		enterRule(_localctx, 48, RULE_decoratorCall);
		try {
			setState(387);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(379);
				match(T__7);
				setState(380);
				expression(0);
				setState(381);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(383);
				match(T__7);
				setState(384);
				expression(0);
				setState(385);
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
		enterRule(_localctx, 50, RULE_controlFlow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(389);
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
		enterRule(_localctx, 52, RULE_variableDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			varType();
			setState(392);
			variableDeclaration();
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(393);
				match(T__1);
				setState(394);
				variableDeclaration();
				}
				}
				setState(399);
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
		enterRule(_localctx, 54, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			match(ID);
			setState(403);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(401);
				match(T__8);
				setState(402);
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
		enterRule(_localctx, 56, RULE_varType);
		try {
			setState(407);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(405);
				match(VAR);
				}
				break;
			case T__40:
			case T__41:
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
				setState(406);
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
		enterRule(_localctx, 58, RULE_tags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(409);
				tag();
				}
				}
				setState(412); 
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
		enterRule(_localctx, 60, RULE_tag);
		int _la;
		try {
			setState(429);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(414);
				match(SHARP);
				setState(415);
				fqdn(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(416);
				match(SHARP);
				setState(417);
				fqdn(0);
				setState(418);
				match(T__0);
				setState(419);
				tagParam();
				setState(424);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(420);
					match(T__1);
					setState(421);
					tagParam();
					}
					}
					setState(426);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(427);
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
		enterRule(_localctx, 62, RULE_tagParam);
		try {
			setState(435);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(431);
				match(ID);
				setState(432);
				match(T__8);
				setState(433);
				constExpression();
				}
				break;
			case STRING:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(434);
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
		public ConstExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constExpression; }
	}

	public final ConstExpressionContext constExpression() throws RecognitionException {
		ConstExpressionContext _localctx = new ConstExpressionContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_constExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==NUMBER) ) {
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
		int _startState = 66;
		enterRecursionRule(_localctx, 66, RULE_fqdn, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new FqdnIdContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(440);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(447);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FqdnMultipleContext(new FqdnContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_fqdn);
					setState(442);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(443);
					match(DOT);
					setState(444);
					match(ID);
					}
					} 
				}
				setState(449);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
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
		int _startState = 68;
		enterRecursionRule(_localctx, 68, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				{
				_localctx = new ExNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(451);
				match(NUMBER);
				}
				break;
			case 2:
				{
				_localctx = new ExStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(452);
				match(STRING);
				}
				break;
			case 3:
				{
				_localctx = new ExIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(453);
				match(ID);
				}
				break;
			case 4:
				{
				_localctx = new ExNewObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(454);
				match(NEW);
				setState(455);
				newType(0);
				setState(456);
				match(T__0);
				setState(458);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9223363240761724926L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 402653185L) != 0)) {
					{
					setState(457);
					expressionTuple();
					}
				}

				setState(460);
				match(T__2);
				}
				break;
			case 5:
				{
				_localctx = new ExNewArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(462);
				match(NEW);
				setState(463);
				newType(0);
				setState(468); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(464);
						match(T__9);
						setState(465);
						expression(0);
						setState(466);
						match(T__10);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(470); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 6:
				{
				_localctx = new ExCastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(472);
				match(T__0);
				setState(473);
				typeName(0);
				setState(474);
				match(T__2);
				setState(475);
				expression(17);
				}
				break;
			case 7:
				{
				_localctx = new ExParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(477);
				match(T__0);
				setState(478);
				expression(0);
				setState(479);
				match(T__2);
				}
				break;
			case 8:
				{
				_localctx = new ExBoolNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(481);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(482);
				expression(15);
				}
				break;
			case 9:
				{
				_localctx = new ExBitNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(483);
				match(T__12);
				setState(484);
				expression(14);
				}
				break;
			case 10:
				{
				_localctx = new ExUnaryMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(485);
				match(T__13);
				setState(486);
				expression(13);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(550);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(548);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
					case 1:
						{
						_localctx = new ExMulModContext(new ExpressionContext(_parentctx, _parentState));
						((ExMulModContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(489);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(490);
						_la = _input.LA(1);
						if ( !(_la==T__14 || _la==STAR) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(491);
						((ExMulModContext)_localctx).rightExpression = expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ExDivContext(new ExpressionContext(_parentctx, _parentState));
						((ExDivContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(492);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(493);
						match(T__15);
						setState(494);
						((ExDivContext)_localctx).rightExpression = expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExAddSubContext(new ExpressionContext(_parentctx, _parentState));
						((ExAddSubContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(495);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(496);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==T__16) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(497);
						((ExAddSubContext)_localctx).rightExpression = expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExShiftContext(new ExpressionContext(_parentctx, _parentState));
						((ExShiftContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(498);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(499);
						_la = _input.LA(1);
						if ( !(_la==T__17 || _la==T__18) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(500);
						((ExShiftContext)_localctx).rightExpression = expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExLtLteGtGteContext(new ExpressionContext(_parentctx, _parentState));
						((ExLtLteGtGteContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(501);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(502);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15728640L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(503);
						((ExLtLteGtGteContext)_localctx).rightExpression = expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExEqNeqContext(new ExpressionContext(_parentctx, _parentState));
						((ExEqNeqContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(504);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(505);
						_la = _input.LA(1);
						if ( !(_la==T__23 || _la==T__24) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(506);
						((ExEqNeqContext)_localctx).rightExpression = expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExBitAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitAndContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(507);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(508);
						match(T__25);
						setState(509);
						((ExBitAndContext)_localctx).rightExpression = expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExBitXorContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitXorContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(510);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(511);
						match(T__26);
						setState(512);
						((ExBitXorContext)_localctx).rightExpression = expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExBitOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitOrContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(513);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(514);
						match(T__27);
						setState(515);
						((ExBitOrContext)_localctx).rightExpression = expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExBoolAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExBoolAndContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(516);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(517);
						match(AND);
						setState(518);
						((ExBoolAndContext)_localctx).rightExpression = expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExBoolOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExBoolOrContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(519);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(520);
						match(OR);
						setState(521);
						((ExBoolOrContext)_localctx).rightExpression = expression(3);
						}
						break;
					case 12:
						{
						_localctx = new ExTernaryContext(new ExpressionContext(_parentctx, _parentState));
						((ExTernaryContext)_localctx).checkExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(522);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(528);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__28:
							{
							setState(523);
							match(T__28);
							setState(524);
							((ExTernaryContext)_localctx).trueExpression = expression(0);
							setState(525);
							match(T__29);
							}
							break;
						case T__30:
							{
							setState(527);
							match(T__30);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(530);
						((ExTernaryContext)_localctx).falseExpression = expression(2);
						}
						break;
					case 13:
						{
						_localctx = new ExDotAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(531);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(532);
						match(DOT);
						setState(533);
						match(ID);
						}
						break;
					case 14:
						{
						_localctx = new ExFnCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(534);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(536);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__19) {
							{
							setState(535);
							genericsInstantiations();
							}
						}

						setState(538);
						match(T__0);
						setState(540);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9223363240761724926L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 402653185L) != 0)) {
							{
							setState(539);
							expressionTuple();
							}
						}

						setState(542);
						match(T__2);
						}
						break;
					case 15:
						{
						_localctx = new ExIndexAccessContext(new ExpressionContext(_parentctx, _parentState));
						((ExIndexAccessContext)_localctx).arraryExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(543);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(544);
						match(T__9);
						setState(545);
						((ExIndexAccessContext)_localctx).indexExpression = expression(0);
						setState(546);
						match(T__10);
						}
						break;
					}
					} 
				}
				setState(552);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
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
		enterRule(_localctx, 70, RULE_assignExpression);
		int _la;
		try {
			setState(568);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				_localctx = new ExAssignContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(553);
				expression(0);
				setState(558);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(554);
					match(T__1);
					setState(555);
					expression(0);
					}
					}
					setState(560);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(561);
				match(T__8);
				setState(562);
				((ExAssignContext)_localctx).rightExpression = expression(0);
				}
				break;
			case 2:
				_localctx = new ExOpAssignContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(564);
				((ExOpAssignContext)_localctx).leftExpression = expression(0);
				setState(565);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2194728288256L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(566);
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
		enterRule(_localctx, 72, RULE_expressionTuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(570);
			expression(0);
			setState(575);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(571);
				match(T__1);
				setState(572);
				expression(0);
				}
				}
				setState(577);
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
		enterRule(_localctx, 74, RULE_extend);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(578);
			match(EXTENDS);
			setState(579);
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
		enterRule(_localctx, 76, RULE_parameterDefinitions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
			parameterDefinition();
			setState(586);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(582);
				match(T__1);
				setState(583);
				parameterDefinition();
				}
				}
				setState(588);
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
		enterRule(_localctx, 78, RULE_parameterDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(590);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(589);
				tags();
				}
			}

			setState(592);
			typeName(0);
			setState(593);
			match(ID);
			setState(595);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(594);
				match(STAR);
				}
			}

			setState(599);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(597);
				match(T__8);
				setState(598);
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
		int _startState = 80;
		enterRecursionRule(_localctx, 80, RULE_typeName, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(605);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new TypeClassContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(602);
				classType();
				}
				break;
			case T__40:
			case T__41:
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
				setState(603);
				primitiveTypeName();
				}
				break;
			case FN:
				{
				_localctx = new TypeFunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(604);
				functionType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(616);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeArrayContext(new TypeNameContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_typeName);
					setState(607);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(610); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(608);
							match(T__9);
							setState(609);
							match(T__10);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(612); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(618);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
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
		int _startState = 82;
		enterRecursionRule(_localctx, 82, RULE_newType, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(622);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new NewTypeClassContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(620);
				classType();
				}
				break;
			case T__40:
			case T__41:
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
				setState(621);
				primitiveTypeName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(633);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NewTypeArrayContext(new NewTypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_newType);
					setState(624);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(627); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(625);
							match(T__9);
							setState(626);
							match(T__10);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(629); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(635);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
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
		enterRule(_localctx, 84, RULE_primitiveTypeName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(636);
			_la = _input.LA(1);
			if ( !(((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 35150012350467L) != 0)) ) {
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
		enterRule(_localctx, 86, RULE_classType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(638);
			fqdn(0);
			setState(640);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				{
				setState(639);
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
		enterRule(_localctx, 88, RULE_genericsDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(642);
			match(T__19);
			setState(643);
			genericDeclaration();
			setState(648);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(644);
				match(T__1);
				setState(645);
				genericDeclaration();
				}
				}
				setState(650);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(651);
			match(T__22);
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
		enterRule(_localctx, 90, RULE_genericDeclaration);
		try {
			setState(657);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				_localctx = new GenericDeclarationIdContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(653);
				match(ID);
				}
				break;
			case 2:
				_localctx = new GenericDeclarationRestrictedContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(654);
				match(ID);
				setState(655);
				match(IS);
				setState(656);
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
		enterRule(_localctx, 92, RULE_genericsInstantiations);
		int _la;
		try {
			setState(672);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
			case 1:
				_localctx = new GenericsInstantiationEmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(659);
				match(T__19);
				setState(660);
				match(T__22);
				}
				break;
			case 2:
				_localctx = new GenericsInstantiationTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(661);
				match(T__19);
				setState(662);
				typeName(0);
				setState(667);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(663);
					match(T__1);
					setState(664);
					typeName(0);
					}
					}
					setState(669);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(670);
				match(T__22);
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
		enterRule(_localctx, 94, RULE_functionType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(674);
			match(FN);
			setState(676);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
			case 1:
				{
				setState(675);
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
		enterRule(_localctx, 96, RULE_namedTypeTuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(678);
			typeName(0);
			setState(679);
			match(ID);
			setState(686);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(680);
				match(T__1);
				setState(681);
				typeName(0);
				setState(682);
				match(ID);
				}
				}
				setState(688);
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
		case 33:
			return fqdn_sempred((FqdnContext)_localctx, predIndex);
		case 34:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 40:
			return typeName_sempred((TypeNameContext)_localctx, predIndex);
		case 41:
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
			return precpred(_ctx, 22);
		case 14:
			return precpred(_ctx, 19);
		case 15:
			return precpred(_ctx, 18);
		}
		return true;
	}
	private boolean typeName_sempred(TypeNameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 16:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean newType_sempred(NewTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 17:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001]\u02b2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u0001\u0000\u0001\u0000"+
		"\u0005\u0000e\b\u0000\n\u0000\f\u0000h\t\u0000\u0001\u0000\u0005\u0000"+
		"k\b\u0000\n\u0000\f\u0000n\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0003\u0002u\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003|\b\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0003\u0007\u0087\b\u0007\u0001\u0007\u0003\u0007"+
		"\u008a\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u008e\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0094\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0099\b\u0007\u0003\u0007\u009b"+
		"\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00a0\b\u0007"+
		"\u0001\u0007\u0003\u0007\u00a3\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u00a7\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00ab\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00b0\b\u0007\u0003\u0007\u00b2"+
		"\b\u0007\u0001\u0007\u0003\u0007\u00b5\b\u0007\u0001\b\u0001\b\u0001\b"+
		"\u0003\b\u00ba\b\b\u0001\t\u0003\t\u00bd\b\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u00c2\b\t\u0001\t\u0001\t\u0003\t\u00c6\b\t\u0001\t\u0001\t\u0005\t"+
		"\u00ca\b\t\n\t\f\t\u00cd\t\t\u0001\t\u0005\t\u00d0\b\t\n\t\f\t\u00d3\t"+
		"\t\u0001\t\u0001\t\u0001\n\u0003\n\u00d8\b\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u00dd\b\n\u0001\n\u0001\n\u0003\n\u00e1\b\n\u0001\n\u0001\n\u0001\n"+
		"\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u00ec"+
		"\b\f\n\f\f\f\u00ef\t\f\u0001\r\u0001\r\u0003\r\u00f3\b\r\u0001\u000e\u0005"+
		"\u000e\u00f6\b\u000e\n\u000e\f\u000e\u00f9\t\u000e\u0001\u000f\u0003\u000f"+
		"\u00fc\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0100\b\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0106\b\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u010b\b\u000f\u0003\u000f\u010d"+
		"\b\u000f\u0001\u000f\u0003\u000f\u0110\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u0114\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0118\b"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u011d\b\u000f\u0003"+
		"\u000f\u011f\b\u000f\u0003\u000f\u0121\b\u000f\u0001\u0010\u0003\u0010"+
		"\u0124\b\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010"+
		"\u012a\b\u0010\n\u0010\f\u0010\u012d\t\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0135\b\u0011\n"+
		"\u0011\f\u0011\u0138\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u0144\b\u0012\u0001\u0013\u0001\u0013\u0005\u0013\u0148"+
		"\b\u0013\n\u0013\f\u0013\u014b\t\u0013\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0003\u0015\u0156\b\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u015d\b\u0015\u0001\u0015\u0001\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0166\b\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0003\u0016\u016e\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0005\u0017\u0174\b\u0017\n\u0017\f\u0017\u0177\t\u0017\u0001\u0017\u0003"+
		"\u0017\u017a\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0184\b\u0018\u0001"+
		"\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0005"+
		"\u001a\u018c\b\u001a\n\u001a\f\u001a\u018f\t\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0003\u001b\u0194\b\u001b\u0001\u001c\u0001\u001c\u0003\u001c"+
		"\u0198\b\u001c\u0001\u001d\u0004\u001d\u019b\b\u001d\u000b\u001d\f\u001d"+
		"\u019c\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0005\u001e\u01a7\b\u001e\n\u001e\f\u001e"+
		"\u01aa\t\u001e\u0001\u001e\u0001\u001e\u0003\u001e\u01ae\b\u001e\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u01b4\b\u001f\u0001"+
		" \u0001 \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0005!\u01be\b!\n!"+
		"\f!\u01c1\t!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0003\"\u01cb\b\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0004\"\u01d5\b\"\u000b\"\f\"\u01d6\u0001\"\u0001\"\u0001\""+
		"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0003\"\u01e8\b\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0003"+
		"\"\u0211\b\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0003\"\u0219"+
		"\b\"\u0001\"\u0001\"\u0003\"\u021d\b\"\u0001\"\u0001\"\u0001\"\u0001\""+
		"\u0001\"\u0001\"\u0005\"\u0225\b\"\n\"\f\"\u0228\t\"\u0001#\u0001#\u0001"+
		"#\u0005#\u022d\b#\n#\f#\u0230\t#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0003#\u0239\b#\u0001$\u0001$\u0001$\u0005$\u023e\b$\n$\f$\u0241"+
		"\t$\u0001%\u0001%\u0001%\u0001&\u0001&\u0001&\u0005&\u0249\b&\n&\f&\u024c"+
		"\t&\u0001\'\u0003\'\u024f\b\'\u0001\'\u0001\'\u0001\'\u0003\'\u0254\b"+
		"\'\u0001\'\u0001\'\u0003\'\u0258\b\'\u0001(\u0001(\u0001(\u0001(\u0003"+
		"(\u025e\b(\u0001(\u0001(\u0001(\u0004(\u0263\b(\u000b(\f(\u0264\u0005"+
		"(\u0267\b(\n(\f(\u026a\t(\u0001)\u0001)\u0001)\u0003)\u026f\b)\u0001)"+
		"\u0001)\u0001)\u0004)\u0274\b)\u000b)\f)\u0275\u0005)\u0278\b)\n)\f)\u027b"+
		"\t)\u0001*\u0001*\u0001+\u0001+\u0003+\u0281\b+\u0001,\u0001,\u0001,\u0001"+
		",\u0005,\u0287\b,\n,\f,\u028a\t,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001"+
		"-\u0003-\u0292\b-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0005.\u029a"+
		"\b.\n.\f.\u029d\t.\u0001.\u0001.\u0003.\u02a1\b.\u0001/\u0001/\u0003/"+
		"\u02a5\b/\u00010\u00010\u00010\u00010\u00010\u00010\u00050\u02ad\b0\n"+
		"0\f0\u02b0\t0\u00010\u0000\u0004BDPR1\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF"+
		"HJLNPRTVXZ\\^`\u0000\n\u0002\u00001144\u0002\u0000++\\\\\u0002\u0000\f"+
		"\f@@\u0002\u0000\u000f\u000fYY\u0002\u0000\u000e\u000e\u0011\u0011\u0001"+
		"\u0000\u0012\u0013\u0001\u0000\u0014\u0017\u0001\u0000\u0018\u0019\u0001"+
		"\u0000 (\u0002\u0000)*LU\u02fb\u0000b\u0001\u0000\u0000\u0000\u0002o\u0001"+
		"\u0000\u0000\u0000\u0004r\u0001\u0000\u0000\u0000\u0006{\u0001\u0000\u0000"+
		"\u0000\b}\u0001\u0000\u0000\u0000\n\u0080\u0001\u0000\u0000\u0000\f\u0083"+
		"\u0001\u0000\u0000\u0000\u000e\u00b4\u0001\u0000\u0000\u0000\u0010\u00b9"+
		"\u0001\u0000\u0000\u0000\u0012\u00bc\u0001\u0000\u0000\u0000\u0014\u00d7"+
		"\u0001\u0000\u0000\u0000\u0016\u00e6\u0001\u0000\u0000\u0000\u0018\u00e8"+
		"\u0001\u0000\u0000\u0000\u001a\u00f0\u0001\u0000\u0000\u0000\u001c\u00f7"+
		"\u0001\u0000\u0000\u0000\u001e\u0120\u0001\u0000\u0000\u0000 \u0123\u0001"+
		"\u0000\u0000\u0000\"\u0130\u0001\u0000\u0000\u0000$\u0143\u0001\u0000"+
		"\u0000\u0000&\u0145\u0001\u0000\u0000\u0000(\u014e\u0001\u0000\u0000\u0000"+
		"*\u0152\u0001\u0000\u0000\u0000,\u016d\u0001\u0000\u0000\u0000.\u0179"+
		"\u0001\u0000\u0000\u00000\u0183\u0001\u0000\u0000\u00002\u0185\u0001\u0000"+
		"\u0000\u00004\u0187\u0001\u0000\u0000\u00006\u0190\u0001\u0000\u0000\u0000"+
		"8\u0197\u0001\u0000\u0000\u0000:\u019a\u0001\u0000\u0000\u0000<\u01ad"+
		"\u0001\u0000\u0000\u0000>\u01b3\u0001\u0000\u0000\u0000@\u01b5\u0001\u0000"+
		"\u0000\u0000B\u01b7\u0001\u0000\u0000\u0000D\u01e7\u0001\u0000\u0000\u0000"+
		"F\u0238\u0001\u0000\u0000\u0000H\u023a\u0001\u0000\u0000\u0000J\u0242"+
		"\u0001\u0000\u0000\u0000L\u0245\u0001\u0000\u0000\u0000N\u024e\u0001\u0000"+
		"\u0000\u0000P\u025d\u0001\u0000\u0000\u0000R\u026e\u0001\u0000\u0000\u0000"+
		"T\u027c\u0001\u0000\u0000\u0000V\u027e\u0001\u0000\u0000\u0000X\u0282"+
		"\u0001\u0000\u0000\u0000Z\u0291\u0001\u0000\u0000\u0000\\\u02a0\u0001"+
		"\u0000\u0000\u0000^\u02a2\u0001\u0000\u0000\u0000`\u02a6\u0001\u0000\u0000"+
		"\u0000bf\u0003\u0002\u0001\u0000ce\u0003\u0004\u0002\u0000dc\u0001\u0000"+
		"\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001"+
		"\u0000\u0000\u0000gl\u0001\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000"+
		"ik\u0003\u0006\u0003\u0000ji\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000"+
		"\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000m\u0001\u0001"+
		"\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000op\u0005>\u0000\u0000pq\u0003"+
		"B!\u0000q\u0003\u0001\u0000\u0000\u0000rt\u0003\b\u0004\u0000su\u0003"+
		"\n\u0005\u0000ts\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000u\u0005"+
		"\u0001\u0000\u0000\u0000v|\u0003\f\u0006\u0000w|\u0003 \u0010\u0000x|"+
		"\u0003\u000e\u0007\u0000y|\u0003\u0012\t\u0000z|\u0003\u0014\n\u0000{"+
		"v\u0001\u0000\u0000\u0000{w\u0001\u0000\u0000\u0000{x\u0001\u0000\u0000"+
		"\u0000{y\u0001\u0000\u0000\u0000{z\u0001\u0000\u0000\u0000|\u0007\u0001"+
		"\u0000\u0000\u0000}~\u0005G\u0000\u0000~\u007f\u0003B!\u0000\u007f\t\u0001"+
		"\u0000\u0000\u0000\u0080\u0081\u0005/\u0000\u0000\u0081\u0082\u0005[\u0000"+
		"\u0000\u0082\u000b\u0001\u0000\u0000\u0000\u0083\u0084\u0005V\u0000\u0000"+
		"\u0084\r\u0001\u0000\u0000\u0000\u0085\u0087\u0003:\u001d\u0000\u0086"+
		"\u0085\u0001\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087"+
		"\u0089\u0001\u0000\u0000\u0000\u0088\u008a\u0005C\u0000\u0000\u0089\u0088"+
		"\u0001\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008b"+
		"\u0001\u0000\u0000\u0000\u008b\u008d\u0005[\u0000\u0000\u008c\u008e\u0003"+
		"X,\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000"+
		"\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0090\u0005\u0001\u0000"+
		"\u0000\u0090\u0093\u0003J%\u0000\u0091\u0092\u0005\u0002\u0000\u0000\u0092"+
		"\u0094\u0003L&\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001"+
		"\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u009a\u0005"+
		"\u0003\u0000\u0000\u0096\u0098\u0005\u0004\u0000\u0000\u0097\u0099\u0003"+
		"\u0010\b\u0000\u0098\u0097\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000"+
		"\u0000\u0000\u0099\u009b\u0001\u0000\u0000\u0000\u009a\u0096\u0001\u0000"+
		"\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000"+
		"\u0000\u0000\u009c\u009d\u0003&\u0013\u0000\u009d\u00b5\u0001\u0000\u0000"+
		"\u0000\u009e\u00a0\u0003:\u001d\u0000\u009f\u009e\u0001\u0000\u0000\u0000"+
		"\u009f\u00a0\u0001\u0000\u0000\u0000\u00a0\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a3\u0005C\u0000\u0000\u00a2\u00a1\u0001\u0000\u0000\u0000\u00a2"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a6\u0005[\u0000\u0000\u00a5\u00a7\u0003X,\u0000\u00a6\u00a5\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001"+
		"\u0000\u0000\u0000\u00a8\u00aa\u0005\u0001\u0000\u0000\u00a9\u00ab\u0003"+
		"L&\u0000\u00aa\u00a9\u0001\u0000\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000"+
		"\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00b1\u0005\u0003\u0000"+
		"\u0000\u00ad\u00af\u0005\u0004\u0000\u0000\u00ae\u00b0\u0003\u0010\b\u0000"+
		"\u00af\u00ae\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000"+
		"\u00b0\u00b2\u0001\u0000\u0000\u0000\u00b1\u00ad\u0001\u0000\u0000\u0000"+
		"\u00b1\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b5\u0003&\u0013\u0000\u00b4\u0086\u0001\u0000\u0000\u0000\u00b4"+
		"\u009f\u0001\u0000\u0000\u0000\u00b5\u000f\u0001\u0000\u0000\u0000\u00b6"+
		"\u00ba\u0003`0\u0000\u00b7\u00ba\u0003P(\u0000\u00b8\u00ba\u0005I\u0000"+
		"\u0000\u00b9\u00b6\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000"+
		"\u0000\u00b9\u00b8\u0001\u0000\u0000\u0000\u00ba\u0011\u0001\u0000\u0000"+
		"\u0000\u00bb\u00bd\u0003:\u001d\u0000\u00bc\u00bb\u0001\u0000\u0000\u0000"+
		"\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000"+
		"\u00be\u00bf\u00053\u0000\u0000\u00bf\u00c1\u0003\u0016\u000b\u0000\u00c0"+
		"\u00c2\u0003X,\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000\u00c1\u00c2\u0001"+
		"\u0000\u0000\u0000\u00c2\u00c5\u0001\u0000\u0000\u0000\u00c3\u00c4\u0005"+
		":\u0000\u0000\u00c4\u00c6\u0003\u0018\f\u0000\u00c5\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000"+
		"\u0000\u0000\u00c7\u00cb\u0005\u0005\u0000\u0000\u00c8\u00ca\u0003\"\u0011"+
		"\u0000\u00c9\u00c8\u0001\u0000\u0000\u0000\u00ca\u00cd\u0001\u0000\u0000"+
		"\u0000\u00cb\u00c9\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000"+
		"\u0000\u00cc\u00d1\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000"+
		"\u0000\u00ce\u00d0\u0003\u000e\u0007\u0000\u00cf\u00ce\u0001\u0000\u0000"+
		"\u0000\u00d0\u00d3\u0001\u0000\u0000\u0000\u00d1\u00cf\u0001\u0000\u0000"+
		"\u0000\u00d1\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d4\u0001\u0000\u0000"+
		"\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d4\u00d5\u0005\u0006\u0000"+
		"\u0000\u00d5\u0013\u0001\u0000\u0000\u0000\u00d6\u00d8\u0003:\u001d\u0000"+
		"\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00da\u0005<\u0000\u0000\u00da"+
		"\u00dc\u0003\u0016\u000b\u0000\u00db\u00dd\u0003X,\u0000\u00dc\u00db\u0001"+
		"\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000\u00dd\u00e0\u0001"+
		"\u0000\u0000\u0000\u00de\u00df\u00056\u0000\u0000\u00df\u00e1\u0003\u0018"+
		"\f\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e2\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005\u0005\u0000"+
		"\u0000\u00e3\u00e4\u0003\u001c\u000e\u0000\u00e4\u00e5\u0005\u0006\u0000"+
		"\u0000\u00e5\u0015\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005[\u0000\u0000"+
		"\u00e7\u0017\u0001\u0000\u0000\u0000\u00e8\u00ed\u0003\u001a\r\u0000\u00e9"+
		"\u00ea\u0005\u0002\u0000\u0000\u00ea\u00ec\u0003\u001a\r\u0000\u00eb\u00e9"+
		"\u0001\u0000\u0000\u0000\u00ec\u00ef\u0001\u0000\u0000\u0000\u00ed\u00eb"+
		"\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u0019"+
		"\u0001\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00f0\u00f2"+
		"\u0003B!\u0000\u00f1\u00f3\u0003\\.\u0000\u00f2\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f3\u001b\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f6\u0003\u001e\u000f\u0000\u00f5\u00f4\u0001\u0000\u0000"+
		"\u0000\u00f6\u00f9\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000\u0000"+
		"\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000\u00f8\u001d\u0001\u0000\u0000"+
		"\u0000\u00f9\u00f7\u0001\u0000\u0000\u0000\u00fa\u00fc\u0003:\u001d\u0000"+
		"\u00fb\u00fa\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000"+
		"\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u00ff\u0005[\u0000\u0000\u00fe"+
		"\u0100\u0003X,\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000\u00ff\u0100\u0001"+
		"\u0000\u0000\u0000\u0100\u0101\u0001\u0000\u0000\u0000\u0101\u0102\u0005"+
		"\u0001\u0000\u0000\u0102\u0105\u0003J%\u0000\u0103\u0104\u0005\u0002\u0000"+
		"\u0000\u0104\u0106\u0003L&\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0105"+
		"\u0106\u0001\u0000\u0000\u0000\u0106\u0107\u0001\u0000\u0000\u0000\u0107"+
		"\u010c\u0005\u0003\u0000\u0000\u0108\u010a\u0005\u0004\u0000\u0000\u0109"+
		"\u010b\u0003\u0010\b\u0000\u010a\u0109\u0001\u0000\u0000\u0000\u010a\u010b"+
		"\u0001\u0000\u0000\u0000\u010b\u010d\u0001\u0000\u0000\u0000\u010c\u0108"+
		"\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000\u0000\u0000\u010d\u0121"+
		"\u0001\u0000\u0000\u0000\u010e\u0110\u0003:\u001d\u0000\u010f\u010e\u0001"+
		"\u0000\u0000\u0000\u010f\u0110\u0001\u0000\u0000\u0000\u0110\u0111\u0001"+
		"\u0000\u0000\u0000\u0111\u0113\u0005[\u0000\u0000\u0112\u0114\u0003X,"+
		"\u0000\u0113\u0112\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000\u0000"+
		"\u0000\u0114\u0115\u0001\u0000\u0000\u0000\u0115\u0117\u0005\u0001\u0000"+
		"\u0000\u0116\u0118\u0003L&\u0000\u0117\u0116\u0001\u0000\u0000\u0000\u0117"+
		"\u0118\u0001\u0000\u0000\u0000\u0118\u0119\u0001\u0000\u0000\u0000\u0119"+
		"\u011e\u0005\u0003\u0000\u0000\u011a\u011c\u0005\u0004\u0000\u0000\u011b"+
		"\u011d\u0003\u0010\b\u0000\u011c\u011b\u0001\u0000\u0000\u0000\u011c\u011d"+
		"\u0001\u0000\u0000\u0000\u011d\u011f\u0001\u0000\u0000\u0000\u011e\u011a"+
		"\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f\u0121"+
		"\u0001\u0000\u0000\u0000\u0120\u00fb\u0001\u0000\u0000\u0000\u0120\u010f"+
		"\u0001\u0000\u0000\u0000\u0121\u001f\u0001\u0000\u0000\u0000\u0122\u0124"+
		"\u0003:\u001d\u0000\u0123\u0122\u0001\u0000\u0000\u0000\u0123\u0124\u0001"+
		"\u0000\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000\u0125\u0126\u0005"+
		"D\u0000\u0000\u0126\u0127\u0005[\u0000\u0000\u0127\u012b\u0005\u0005\u0000"+
		"\u0000\u0128\u012a\u0003\"\u0011\u0000\u0129\u0128\u0001\u0000\u0000\u0000"+
		"\u012a\u012d\u0001\u0000\u0000\u0000\u012b\u0129\u0001\u0000\u0000\u0000"+
		"\u012b\u012c\u0001\u0000\u0000\u0000\u012c\u012e\u0001\u0000\u0000\u0000"+
		"\u012d\u012b\u0001\u0000\u0000\u0000\u012e\u012f\u0005\u0006\u0000\u0000"+
		"\u012f!\u0001\u0000\u0000\u0000\u0130\u0131\u0003P(\u0000\u0131\u0136"+
		"\u0005[\u0000\u0000\u0132\u0133\u0005\u0002\u0000\u0000\u0133\u0135\u0005"+
		"[\u0000\u0000\u0134\u0132\u0001\u0000\u0000\u0000\u0135\u0138\u0001\u0000"+
		"\u0000\u0000\u0136\u0134\u0001\u0000\u0000\u0000\u0136\u0137\u0001\u0000"+
		"\u0000\u0000\u0137#\u0001\u0000\u0000\u0000\u0138\u0136\u0001\u0000\u0000"+
		"\u0000\u0139\u0144\u0003\u000e\u0007\u0000\u013a\u0144\u00034\u001a\u0000"+
		"\u013b\u0144\u0003.\u0017\u0000\u013c\u0144\u00032\u0019\u0000\u013d\u0144"+
		"\u0003\f\u0006\u0000\u013e\u0144\u0003(\u0014\u0000\u013f\u0144\u0003"+
		"*\u0015\u0000\u0140\u0144\u0003,\u0016\u0000\u0141\u0144\u0003D\"\u0000"+
		"\u0142\u0144\u0003F#\u0000\u0143\u0139\u0001\u0000\u0000\u0000\u0143\u013a"+
		"\u0001\u0000\u0000\u0000\u0143\u013b\u0001\u0000\u0000\u0000\u0143\u013c"+
		"\u0001\u0000\u0000\u0000\u0143\u013d\u0001\u0000\u0000\u0000\u0143\u013e"+
		"\u0001\u0000\u0000\u0000\u0143\u013f\u0001\u0000\u0000\u0000\u0143\u0140"+
		"\u0001\u0000\u0000\u0000\u0143\u0141\u0001\u0000\u0000\u0000\u0143\u0142"+
		"\u0001\u0000\u0000\u0000\u0144%\u0001\u0000\u0000\u0000\u0145\u0149\u0005"+
		"\u0005\u0000\u0000\u0146\u0148\u0003$\u0012\u0000\u0147\u0146\u0001\u0000"+
		"\u0000\u0000\u0148\u014b\u0001\u0000\u0000\u0000\u0149\u0147\u0001\u0000"+
		"\u0000\u0000\u0149\u014a\u0001\u0000\u0000\u0000\u014a\u014c\u0001\u0000"+
		"\u0000\u0000\u014b\u0149\u0001\u0000\u0000\u0000\u014c\u014d\u0005\u0006"+
		"\u0000\u0000\u014d\'\u0001\u0000\u0000\u0000\u014e\u014f\u0005J\u0000"+
		"\u0000\u014f\u0150\u0003D\"\u0000\u0150\u0151\u0003&\u0013\u0000\u0151"+
		")\u0001\u0000\u0000\u0000\u0152\u0155\u00058\u0000\u0000\u0153\u0156\u0003"+
		"F#\u0000\u0154\u0156\u00034\u001a\u0000\u0155\u0153\u0001\u0000\u0000"+
		"\u0000\u0155\u0154\u0001\u0000\u0000\u0000\u0156\u0157\u0001\u0000\u0000"+
		"\u0000\u0157\u0158\u0005\u0007\u0000\u0000\u0158\u0159\u0003D\"\u0000"+
		"\u0159\u015c\u0005\u0007\u0000\u0000\u015a\u015d\u0003D\"\u0000\u015b"+
		"\u015d\u0003F#\u0000\u015c\u015a\u0001\u0000\u0000\u0000\u015c\u015b\u0001"+
		"\u0000\u0000\u0000\u015d\u015e\u0001\u0000\u0000\u0000\u015e\u015f\u0003"+
		"&\u0013\u0000\u015f+\u0001\u0000\u0000\u0000\u0160\u0161\u00059\u0000"+
		"\u0000\u0161\u0165\u0003D\"\u0000\u0162\u0166\u0003&\u0013\u0000\u0163"+
		"\u0166\u0003.\u0017\u0000\u0164\u0166\u00032\u0019\u0000\u0165\u0162\u0001"+
		"\u0000\u0000\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0165\u0164\u0001"+
		"\u0000\u0000\u0000\u0166\u016e\u0001\u0000\u0000\u0000\u0167\u0168\u0005"+
		"9\u0000\u0000\u0168\u0169\u0003D\"\u0000\u0169\u016a\u0003&\u0013\u0000"+
		"\u016a\u016b\u00055\u0000\u0000\u016b\u016c\u0003&\u0013\u0000\u016c\u016e"+
		"\u0001\u0000\u0000\u0000\u016d\u0160\u0001\u0000\u0000\u0000\u016d\u0167"+
		"\u0001\u0000\u0000\u0000\u016e-\u0001\u0000\u0000\u0000\u016f\u0170\u0005"+
		"B\u0000\u0000\u0170\u0175\u0003D\"\u0000\u0171\u0172\u0005\u0002\u0000"+
		"\u0000\u0172\u0174\u0003D\"\u0000\u0173\u0171\u0001\u0000\u0000\u0000"+
		"\u0174\u0177\u0001\u0000\u0000\u0000\u0175\u0173\u0001\u0000\u0000\u0000"+
		"\u0175\u0176\u0001\u0000\u0000\u0000\u0176\u017a\u0001\u0000\u0000\u0000"+
		"\u0177\u0175\u0001\u0000\u0000\u0000\u0178\u017a\u0005B\u0000\u0000\u0179"+
		"\u016f\u0001\u0000\u0000\u0000\u0179\u0178\u0001\u0000\u0000\u0000\u017a"+
		"/\u0001\u0000\u0000\u0000\u017b\u017c\u0005\b\u0000\u0000\u017c\u017d"+
		"\u0003D\"\u0000\u017d\u017e\u0003\u000e\u0007\u0000\u017e\u0184\u0001"+
		"\u0000\u0000\u0000\u017f\u0180\u0005\b\u0000\u0000\u0180\u0181\u0003D"+
		"\"\u0000\u0181\u0182\u00030\u0018\u0000\u0182\u0184\u0001\u0000\u0000"+
		"\u0000\u0183\u017b\u0001\u0000\u0000\u0000\u0183\u017f\u0001\u0000\u0000"+
		"\u0000\u01841\u0001\u0000\u0000\u0000\u0185\u0186\u0007\u0000\u0000\u0000"+
		"\u01863\u0001\u0000\u0000\u0000\u0187\u0188\u00038\u001c\u0000\u0188\u018d"+
		"\u00036\u001b\u0000\u0189\u018a\u0005\u0002\u0000\u0000\u018a\u018c\u0003"+
		"6\u001b\u0000\u018b\u0189\u0001\u0000\u0000\u0000\u018c\u018f\u0001\u0000"+
		"\u0000\u0000\u018d\u018b\u0001\u0000\u0000\u0000\u018d\u018e\u0001\u0000"+
		"\u0000\u0000\u018e5\u0001\u0000\u0000\u0000\u018f\u018d\u0001\u0000\u0000"+
		"\u0000\u0190\u0193\u0005[\u0000\u0000\u0191\u0192\u0005\t\u0000\u0000"+
		"\u0192\u0194\u0003D\"\u0000\u0193\u0191\u0001\u0000\u0000\u0000\u0193"+
		"\u0194\u0001\u0000\u0000\u0000\u01947\u0001\u0000\u0000\u0000\u0195\u0198"+
		"\u0005H\u0000\u0000\u0196\u0198\u0003P(\u0000\u0197\u0195\u0001\u0000"+
		"\u0000\u0000\u0197\u0196\u0001\u0000\u0000\u0000\u01989\u0001\u0000\u0000"+
		"\u0000\u0199\u019b\u0003<\u001e\u0000\u019a\u0199\u0001\u0000\u0000\u0000"+
		"\u019b\u019c\u0001\u0000\u0000\u0000\u019c\u019a\u0001\u0000\u0000\u0000"+
		"\u019c\u019d\u0001\u0000\u0000\u0000\u019d;\u0001\u0000\u0000\u0000\u019e"+
		"\u019f\u0005X\u0000\u0000\u019f\u01ae\u0003B!\u0000\u01a0\u01a1\u0005"+
		"X\u0000\u0000\u01a1\u01a2\u0003B!\u0000\u01a2\u01a3\u0005\u0001\u0000"+
		"\u0000\u01a3\u01a8\u0003>\u001f\u0000\u01a4\u01a5\u0005\u0002\u0000\u0000"+
		"\u01a5\u01a7\u0003>\u001f\u0000\u01a6\u01a4\u0001\u0000\u0000\u0000\u01a7"+
		"\u01aa\u0001\u0000\u0000\u0000\u01a8\u01a6\u0001\u0000\u0000\u0000\u01a8"+
		"\u01a9\u0001\u0000\u0000\u0000\u01a9\u01ab\u0001\u0000\u0000\u0000\u01aa"+
		"\u01a8\u0001\u0000\u0000\u0000\u01ab\u01ac\u0005\u0003\u0000\u0000\u01ac"+
		"\u01ae\u0001\u0000\u0000\u0000\u01ad\u019e\u0001\u0000\u0000\u0000\u01ad"+
		"\u01a0\u0001\u0000\u0000\u0000\u01ae=\u0001\u0000\u0000\u0000\u01af\u01b0"+
		"\u0005[\u0000\u0000\u01b0\u01b1\u0005\t\u0000\u0000\u01b1\u01b4\u0003"+
		"@ \u0000\u01b2\u01b4\u0003@ \u0000\u01b3\u01af\u0001\u0000\u0000\u0000"+
		"\u01b3\u01b2\u0001\u0000\u0000\u0000\u01b4?\u0001\u0000\u0000\u0000\u01b5"+
		"\u01b6\u0007\u0001\u0000\u0000\u01b6A\u0001\u0000\u0000\u0000\u01b7\u01b8"+
		"\u0006!\uffff\uffff\u0000\u01b8\u01b9\u0005[\u0000\u0000\u01b9\u01bf\u0001"+
		"\u0000\u0000\u0000\u01ba\u01bb\n\u0001\u0000\u0000\u01bb\u01bc\u0005Z"+
		"\u0000\u0000\u01bc\u01be\u0005[\u0000\u0000\u01bd\u01ba\u0001\u0000\u0000"+
		"\u0000\u01be\u01c1\u0001\u0000\u0000\u0000\u01bf\u01bd\u0001\u0000\u0000"+
		"\u0000\u01bf\u01c0\u0001\u0000\u0000\u0000\u01c0C\u0001\u0000\u0000\u0000"+
		"\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c2\u01c3\u0006\"\uffff\uffff\u0000"+
		"\u01c3\u01e8\u0005\\\u0000\u0000\u01c4\u01e8\u0005+\u0000\u0000\u01c5"+
		"\u01e8\u0005[\u0000\u0000\u01c6\u01c7\u0005?\u0000\u0000\u01c7\u01c8\u0003"+
		"R)\u0000\u01c8\u01ca\u0005\u0001\u0000\u0000\u01c9\u01cb\u0003H$\u0000"+
		"\u01ca\u01c9\u0001\u0000\u0000\u0000\u01ca\u01cb\u0001\u0000\u0000\u0000"+
		"\u01cb\u01cc\u0001\u0000\u0000\u0000\u01cc\u01cd\u0005\u0003\u0000\u0000"+
		"\u01cd\u01e8\u0001\u0000\u0000\u0000\u01ce\u01cf\u0005?\u0000\u0000\u01cf"+
		"\u01d4\u0003R)\u0000\u01d0\u01d1\u0005\n\u0000\u0000\u01d1\u01d2\u0003"+
		"D\"\u0000\u01d2\u01d3\u0005\u000b\u0000\u0000\u01d3\u01d5\u0001\u0000"+
		"\u0000\u0000\u01d4\u01d0\u0001\u0000\u0000\u0000\u01d5\u01d6\u0001\u0000"+
		"\u0000\u0000\u01d6\u01d4\u0001\u0000\u0000\u0000\u01d6\u01d7\u0001\u0000"+
		"\u0000\u0000\u01d7\u01e8\u0001\u0000\u0000\u0000\u01d8\u01d9\u0005\u0001"+
		"\u0000\u0000\u01d9\u01da\u0003P(\u0000\u01da\u01db\u0005\u0003\u0000\u0000"+
		"\u01db\u01dc\u0003D\"\u0011\u01dc\u01e8\u0001\u0000\u0000\u0000\u01dd"+
		"\u01de\u0005\u0001\u0000\u0000\u01de\u01df\u0003D\"\u0000\u01df\u01e0"+
		"\u0005\u0003\u0000\u0000\u01e0\u01e8\u0001\u0000\u0000\u0000\u01e1\u01e2"+
		"\u0007\u0002\u0000\u0000\u01e2\u01e8\u0003D\"\u000f\u01e3\u01e4\u0005"+
		"\r\u0000\u0000\u01e4\u01e8\u0003D\"\u000e\u01e5\u01e6\u0005\u000e\u0000"+
		"\u0000\u01e6\u01e8\u0003D\"\r\u01e7\u01c2\u0001\u0000\u0000\u0000\u01e7"+
		"\u01c4\u0001\u0000\u0000\u0000\u01e7\u01c5\u0001\u0000\u0000\u0000\u01e7"+
		"\u01c6\u0001\u0000\u0000\u0000\u01e7\u01ce\u0001\u0000\u0000\u0000\u01e7"+
		"\u01d8\u0001\u0000\u0000\u0000\u01e7\u01dd\u0001\u0000\u0000\u0000\u01e7"+
		"\u01e1\u0001\u0000\u0000\u0000\u01e7\u01e3\u0001\u0000\u0000\u0000\u01e7"+
		"\u01e5\u0001\u0000\u0000\u0000\u01e8\u0226\u0001\u0000\u0000\u0000\u01e9"+
		"\u01ea\n\f\u0000\u0000\u01ea\u01eb\u0007\u0003\u0000\u0000\u01eb\u0225"+
		"\u0003D\"\r\u01ec\u01ed\n\u000b\u0000\u0000\u01ed\u01ee\u0005\u0010\u0000"+
		"\u0000\u01ee\u0225\u0003D\"\f\u01ef\u01f0\n\n\u0000\u0000\u01f0\u01f1"+
		"\u0007\u0004\u0000\u0000\u01f1\u0225\u0003D\"\u000b\u01f2\u01f3\n\t\u0000"+
		"\u0000\u01f3\u01f4\u0007\u0005\u0000\u0000\u01f4\u0225\u0003D\"\n\u01f5"+
		"\u01f6\n\b\u0000\u0000\u01f6\u01f7\u0007\u0006\u0000\u0000\u01f7\u0225"+
		"\u0003D\"\t\u01f8\u01f9\n\u0007\u0000\u0000\u01f9\u01fa\u0007\u0007\u0000"+
		"\u0000\u01fa\u0225\u0003D\"\b\u01fb\u01fc\n\u0006\u0000\u0000\u01fc\u01fd"+
		"\u0005\u001a\u0000\u0000\u01fd\u0225\u0003D\"\u0007\u01fe\u01ff\n\u0005"+
		"\u0000\u0000\u01ff\u0200\u0005\u001b\u0000\u0000\u0200\u0225\u0003D\""+
		"\u0006\u0201\u0202\n\u0004\u0000\u0000\u0202\u0203\u0005\u001c\u0000\u0000"+
		"\u0203\u0225\u0003D\"\u0005\u0204\u0205\n\u0003\u0000\u0000\u0205\u0206"+
		"\u00050\u0000\u0000\u0206\u0225\u0003D\"\u0004\u0207\u0208\n\u0002\u0000"+
		"\u0000\u0208\u0209\u0005A\u0000\u0000\u0209\u0225\u0003D\"\u0003\u020a"+
		"\u0210\n\u0001\u0000\u0000\u020b\u020c\u0005\u001d\u0000\u0000\u020c\u020d"+
		"\u0003D\"\u0000\u020d\u020e\u0005\u001e\u0000\u0000\u020e\u0211\u0001"+
		"\u0000\u0000\u0000\u020f\u0211\u0005\u001f\u0000\u0000\u0210\u020b\u0001"+
		"\u0000\u0000\u0000\u0210\u020f\u0001\u0000\u0000\u0000\u0211\u0212\u0001"+
		"\u0000\u0000\u0000\u0212\u0225\u0003D\"\u0002\u0213\u0214\n\u0016\u0000"+
		"\u0000\u0214\u0215\u0005Z\u0000\u0000\u0215\u0225\u0005[\u0000\u0000\u0216"+
		"\u0218\n\u0013\u0000\u0000\u0217\u0219\u0003\\.\u0000\u0218\u0217\u0001"+
		"\u0000\u0000\u0000\u0218\u0219\u0001\u0000\u0000\u0000\u0219\u021a\u0001"+
		"\u0000\u0000\u0000\u021a\u021c\u0005\u0001\u0000\u0000\u021b\u021d\u0003"+
		"H$\u0000\u021c\u021b\u0001\u0000\u0000\u0000\u021c\u021d\u0001\u0000\u0000"+
		"\u0000\u021d\u021e\u0001\u0000\u0000\u0000\u021e\u0225\u0005\u0003\u0000"+
		"\u0000\u021f\u0220\n\u0012\u0000\u0000\u0220\u0221\u0005\n\u0000\u0000"+
		"\u0221\u0222\u0003D\"\u0000\u0222\u0223\u0005\u000b\u0000\u0000\u0223"+
		"\u0225\u0001\u0000\u0000\u0000\u0224\u01e9\u0001\u0000\u0000\u0000\u0224"+
		"\u01ec\u0001\u0000\u0000\u0000\u0224\u01ef\u0001\u0000\u0000\u0000\u0224"+
		"\u01f2\u0001\u0000\u0000\u0000\u0224\u01f5\u0001\u0000\u0000\u0000\u0224"+
		"\u01f8\u0001\u0000\u0000\u0000\u0224\u01fb\u0001\u0000\u0000\u0000\u0224"+
		"\u01fe\u0001\u0000\u0000\u0000\u0224\u0201\u0001\u0000\u0000\u0000\u0224"+
		"\u0204\u0001\u0000\u0000\u0000\u0224\u0207\u0001\u0000\u0000\u0000\u0224"+
		"\u020a\u0001\u0000\u0000\u0000\u0224\u0213\u0001\u0000\u0000\u0000\u0224"+
		"\u0216\u0001\u0000\u0000\u0000\u0224\u021f\u0001\u0000\u0000\u0000\u0225"+
		"\u0228\u0001\u0000\u0000\u0000\u0226\u0224\u0001\u0000\u0000\u0000\u0226"+
		"\u0227\u0001\u0000\u0000\u0000\u0227E\u0001\u0000\u0000\u0000\u0228\u0226"+
		"\u0001\u0000\u0000\u0000\u0229\u022e\u0003D\"\u0000\u022a\u022b\u0005"+
		"\u0002\u0000\u0000\u022b\u022d\u0003D\"\u0000\u022c\u022a\u0001\u0000"+
		"\u0000\u0000\u022d\u0230\u0001\u0000\u0000\u0000\u022e\u022c\u0001\u0000"+
		"\u0000\u0000\u022e\u022f\u0001\u0000\u0000\u0000\u022f\u0231\u0001\u0000"+
		"\u0000\u0000\u0230\u022e\u0001\u0000\u0000\u0000\u0231\u0232\u0005\t\u0000"+
		"\u0000\u0232\u0233\u0003D\"\u0000\u0233\u0239\u0001\u0000\u0000\u0000"+
		"\u0234\u0235\u0003D\"\u0000\u0235\u0236\u0007\b\u0000\u0000\u0236\u0237"+
		"\u0003D\"\u0000\u0237\u0239\u0001\u0000\u0000\u0000\u0238\u0229\u0001"+
		"\u0000\u0000\u0000\u0238\u0234\u0001\u0000\u0000\u0000\u0239G\u0001\u0000"+
		"\u0000\u0000\u023a\u023f\u0003D\"\u0000\u023b\u023c\u0005\u0002\u0000"+
		"\u0000\u023c\u023e\u0003D\"\u0000\u023d\u023b\u0001\u0000\u0000\u0000"+
		"\u023e\u0241\u0001\u0000\u0000\u0000\u023f\u023d\u0001\u0000\u0000\u0000"+
		"\u023f\u0240\u0001\u0000\u0000\u0000\u0240I\u0001\u0000\u0000\u0000\u0241"+
		"\u023f\u0001\u0000\u0000\u0000\u0242\u0243\u00056\u0000\u0000\u0243\u0244"+
		"\u0003V+\u0000\u0244K\u0001\u0000\u0000\u0000\u0245\u024a\u0003N\'\u0000"+
		"\u0246\u0247\u0005\u0002\u0000\u0000\u0247\u0249\u0003N\'\u0000\u0248"+
		"\u0246\u0001\u0000\u0000\u0000\u0249\u024c\u0001\u0000\u0000\u0000\u024a"+
		"\u0248\u0001\u0000\u0000\u0000\u024a\u024b\u0001\u0000\u0000\u0000\u024b"+
		"M\u0001\u0000\u0000\u0000\u024c\u024a\u0001\u0000\u0000\u0000\u024d\u024f"+
		"\u0003:\u001d\u0000\u024e\u024d\u0001\u0000\u0000\u0000\u024e\u024f\u0001"+
		"\u0000\u0000\u0000\u024f\u0250\u0001\u0000\u0000\u0000\u0250\u0251\u0003"+
		"P(\u0000\u0251\u0253\u0005[\u0000\u0000\u0252\u0254\u0005Y\u0000\u0000"+
		"\u0253\u0252\u0001\u0000\u0000\u0000\u0253\u0254\u0001\u0000\u0000\u0000"+
		"\u0254\u0257\u0001\u0000\u0000\u0000\u0255\u0256\u0005\t\u0000\u0000\u0256"+
		"\u0258\u0003D\"\u0000\u0257\u0255\u0001\u0000\u0000\u0000\u0257\u0258"+
		"\u0001\u0000\u0000\u0000\u0258O\u0001\u0000\u0000\u0000\u0259\u025a\u0006"+
		"(\uffff\uffff\u0000\u025a\u025e\u0003V+\u0000\u025b\u025e\u0003T*\u0000"+
		"\u025c\u025e\u0003^/\u0000\u025d\u0259\u0001\u0000\u0000\u0000\u025d\u025b"+
		"\u0001\u0000\u0000\u0000\u025d\u025c\u0001\u0000\u0000\u0000\u025e\u0268"+
		"\u0001\u0000\u0000\u0000\u025f\u0262\n\u0001\u0000\u0000\u0260\u0261\u0005"+
		"\n\u0000\u0000\u0261\u0263\u0005\u000b\u0000\u0000\u0262\u0260\u0001\u0000"+
		"\u0000\u0000\u0263\u0264\u0001\u0000\u0000\u0000\u0264\u0262\u0001\u0000"+
		"\u0000\u0000\u0264\u0265\u0001\u0000\u0000\u0000\u0265\u0267\u0001\u0000"+
		"\u0000\u0000\u0266\u025f\u0001\u0000\u0000\u0000\u0267\u026a\u0001\u0000"+
		"\u0000\u0000\u0268\u0266\u0001\u0000\u0000\u0000\u0268\u0269\u0001\u0000"+
		"\u0000\u0000\u0269Q\u0001\u0000\u0000\u0000\u026a\u0268\u0001\u0000\u0000"+
		"\u0000\u026b\u026c\u0006)\uffff\uffff\u0000\u026c\u026f\u0003V+\u0000"+
		"\u026d\u026f\u0003T*\u0000\u026e\u026b\u0001\u0000\u0000\u0000\u026e\u026d"+
		"\u0001\u0000\u0000\u0000\u026f\u0279\u0001\u0000\u0000\u0000\u0270\u0273"+
		"\n\u0001\u0000\u0000\u0271\u0272\u0005\n\u0000\u0000\u0272\u0274\u0005"+
		"\u000b\u0000\u0000\u0273\u0271\u0001\u0000\u0000\u0000\u0274\u0275\u0001"+
		"\u0000\u0000\u0000\u0275\u0273\u0001\u0000\u0000\u0000\u0275\u0276\u0001"+
		"\u0000\u0000\u0000\u0276\u0278\u0001\u0000\u0000\u0000\u0277\u0270\u0001"+
		"\u0000\u0000\u0000\u0278\u027b\u0001\u0000\u0000\u0000\u0279\u0277\u0001"+
		"\u0000\u0000\u0000\u0279\u027a\u0001\u0000\u0000\u0000\u027aS\u0001\u0000"+
		"\u0000\u0000\u027b\u0279\u0001\u0000\u0000\u0000\u027c\u027d\u0007\t\u0000"+
		"\u0000\u027dU\u0001\u0000\u0000\u0000\u027e\u0280\u0003B!\u0000\u027f"+
		"\u0281\u0003\\.\u0000\u0280\u027f\u0001\u0000\u0000\u0000\u0280\u0281"+
		"\u0001\u0000\u0000\u0000\u0281W\u0001\u0000\u0000\u0000\u0282\u0283\u0005"+
		"\u0014\u0000\u0000\u0283\u0288\u0003Z-\u0000\u0284\u0285\u0005\u0002\u0000"+
		"\u0000\u0285\u0287\u0003Z-\u0000\u0286\u0284\u0001\u0000\u0000\u0000\u0287"+
		"\u028a\u0001\u0000\u0000\u0000\u0288\u0286\u0001\u0000\u0000\u0000\u0288"+
		"\u0289\u0001\u0000\u0000\u0000\u0289\u028b\u0001\u0000\u0000\u0000\u028a"+
		"\u0288\u0001\u0000\u0000\u0000\u028b\u028c\u0005\u0017\u0000\u0000\u028c"+
		"Y\u0001\u0000\u0000\u0000\u028d\u0292\u0005[\u0000\u0000\u028e\u028f\u0005"+
		"[\u0000\u0000\u028f\u0290\u0005=\u0000\u0000\u0290\u0292\u0003P(\u0000"+
		"\u0291\u028d\u0001\u0000\u0000\u0000\u0291\u028e\u0001\u0000\u0000\u0000"+
		"\u0292[\u0001\u0000\u0000\u0000\u0293\u0294\u0005\u0014\u0000\u0000\u0294"+
		"\u02a1\u0005\u0017\u0000\u0000\u0295\u0296\u0005\u0014\u0000\u0000\u0296"+
		"\u029b\u0003P(\u0000\u0297\u0298\u0005\u0002\u0000\u0000\u0298\u029a\u0003"+
		"P(\u0000\u0299\u0297\u0001\u0000\u0000\u0000\u029a\u029d\u0001\u0000\u0000"+
		"\u0000\u029b\u0299\u0001\u0000\u0000\u0000\u029b\u029c\u0001\u0000\u0000"+
		"\u0000\u029c\u029e\u0001\u0000\u0000\u0000\u029d\u029b\u0001\u0000\u0000"+
		"\u0000\u029e\u029f\u0005\u0017\u0000\u0000\u029f\u02a1\u0001\u0000\u0000"+
		"\u0000\u02a0\u0293\u0001\u0000\u0000\u0000\u02a0\u0295\u0001\u0000\u0000"+
		"\u0000\u02a1]\u0001\u0000\u0000\u0000\u02a2\u02a4\u0005W\u0000\u0000\u02a3"+
		"\u02a5\u0003\\.\u0000\u02a4\u02a3\u0001\u0000\u0000\u0000\u02a4\u02a5"+
		"\u0001\u0000\u0000\u0000\u02a5_\u0001\u0000\u0000\u0000\u02a6\u02a7\u0003"+
		"P(\u0000\u02a7\u02ae\u0005[\u0000\u0000\u02a8\u02a9\u0005\u0002\u0000"+
		"\u0000\u02a9\u02aa\u0003P(\u0000\u02aa\u02ab\u0005[\u0000\u0000\u02ab"+
		"\u02ad\u0001\u0000\u0000\u0000\u02ac\u02a8\u0001\u0000\u0000\u0000\u02ad"+
		"\u02b0\u0001\u0000\u0000\u0000\u02ae\u02ac\u0001\u0000\u0000\u0000\u02ae"+
		"\u02af\u0001\u0000\u0000\u0000\u02afa\u0001\u0000\u0000\u0000\u02b0\u02ae"+
		"\u0001\u0000\u0000\u0000Xflt{\u0086\u0089\u008d\u0093\u0098\u009a\u009f"+
		"\u00a2\u00a6\u00aa\u00af\u00b1\u00b4\u00b9\u00bc\u00c1\u00c5\u00cb\u00d1"+
		"\u00d7\u00dc\u00e0\u00ed\u00f2\u00f7\u00fb\u00ff\u0105\u010a\u010c\u010f"+
		"\u0113\u0117\u011c\u011e\u0120\u0123\u012b\u0136\u0143\u0149\u0155\u015c"+
		"\u0165\u016d\u0175\u0179\u0183\u018d\u0193\u0197\u019c\u01a8\u01ad\u01b3"+
		"\u01bf\u01ca\u01d6\u01e7\u0210\u0218\u021c\u0224\u0226\u022e\u0238\u023f"+
		"\u024a\u024e\u0253\u0257\u025d\u0264\u0268\u026e\u0275\u0279\u0280\u0288"+
		"\u0291\u029b\u02a0\u02a4\u02ae";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}