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
		IS=61, MODULE=62, NEW=63, NOT=64, OR=65, RETURN=66, TAG=67, THROW=68, 
		TRY=69, USE=70, VAR=71, VOID=72, WHILE=73, YIELD=74, U8=75, I8=76, U16=77, 
		I16=78, U32=79, I32=80, U64=81, I64=82, F32=83, F64=84, NATIVE=85, FN=86, 
		SHARP=87, STAR=88, DOT=89, ID=90, NUMBER=91, DigitSequence=92;
	public static final int
		RULE_compilationUnit = 0, RULE_module = 1, RULE_useStatement = 2, RULE_compileBlock = 3, 
		RULE_use = 4, RULE_use_alias = 5, RULE_nativeBlock = 6, RULE_function = 7, 
		RULE_returnType = 8, RULE_classDefinition = 9, RULE_interfaceDefinition = 10, 
		RULE_name = 11, RULE_interfaceImplementations = 12, RULE_interfaceImplementation = 13, 
		RULE_interfaceStatements = 14, RULE_functionDeclaration = 15, RULE_tagDefinition = 16, 
		RULE_fieldDeclaration = 17, RULE_statement = 18, RULE_block = 19, RULE_whileBlock = 20, 
		RULE_forBlock = 21, RULE_ifBlock = 22, RULE_tryCatchBlock = 23, RULE_return = 24, 
		RULE_decoratorCall = 25, RULE_controlFlow = 26, RULE_variableDeclarations = 27, 
		RULE_variableDeclaration = 28, RULE_varType = 29, RULE_tags = 30, RULE_tag = 31, 
		RULE_tagParam = 32, RULE_constExpression = 33, RULE_fqdn = 34, RULE_expression = 35, 
		RULE_expressionTuple = 36, RULE_extend = 37, RULE_parameterDefinitions = 38, 
		RULE_parameterDefinition = 39, RULE_typeName = 40, RULE_newType = 41, 
		RULE_primitiveTypeName = 42, RULE_classType = 43, RULE_genericsDeclarations = 44, 
		RULE_genericDeclaration = 45, RULE_genericsInstantiations = 46, RULE_functionType = 47, 
		RULE_typeTuple = 48;
	private static String[] makeRuleNames() {
		return new String[] {
			"compilationUnit", "module", "useStatement", "compileBlock", "use", "use_alias", 
			"nativeBlock", "function", "returnType", "classDefinition", "interfaceDefinition", 
			"name", "interfaceImplementations", "interfaceImplementation", "interfaceStatements", 
			"functionDeclaration", "tagDefinition", "fieldDeclaration", "statement", 
			"block", "whileBlock", "forBlock", "ifBlock", "tryCatchBlock", "return", 
			"decoratorCall", "controlFlow", "variableDeclarations", "variableDeclaration", 
			"varType", "tags", "tag", "tagParam", "constExpression", "fqdn", "expression", 
			"expressionTuple", "extend", "parameterDefinitions", "parameterDefinition", 
			"typeName", "newType", "primitiveTypeName", "classType", "genericsDeclarations", 
			"genericDeclaration", "genericsInstantiations", "functionType", "typeTuple"
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
			"'is'", "'module'", "'new'", "'not'", "'or'", "'return'", "'tag'", "'throw'", 
			"'try'", "'use'", "'var'", "'void'", "'while'", "'yield'", "'u8'", "'i8'", 
			"'u16'", "'i16'", "'u32'", "'i32'", "'u64'", "'i64'", "'f32'", "'f64'", 
			null, "'fn'", "'#'", "'*'", "'.'"
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
			"NEW", "NOT", "OR", "RETURN", "TAG", "THROW", "TRY", "USE", "VAR", "VOID", 
			"WHILE", "YIELD", "U8", "I8", "U16", "I16", "U32", "I32", "U64", "I64", 
			"F32", "F64", "NATIVE", "FN", "SHARP", "STAR", "DOT", "ID", "NUMBER", 
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
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1155173304420532512L) != 0) || ((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & 9699329L) != 0)) {
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
		public DecoratorCallContext decoratorCall() {
			return getRuleContext(DecoratorCallContext.class,0);
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
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
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
			setState(126);
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
				decoratorCall();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(120);
				tagDefinition();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(121);
				function();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(122);
				classDefinition();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(123);
				interfaceDefinition();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(124);
				variableDeclaration();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(125);
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
			setState(128);
			match(USE);
			setState(129);
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
			setState(131);
			match(AS);
			setState(132);
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
			setState(134);
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
			setState(177);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(136);
					tags();
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
					if (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 615711479169027L) != 0)) {
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

				setState(161);
				match(ID);
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__19) {
					{
					setState(162);
					genericsDeclarations();
					}
				}

				setState(165);
				match(T__0);
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 686078075863043L) != 0)) {
					{
					setState(166);
					parameterDefinitions();
					}
				}

				setState(169);
				match(T__2);
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(170);
					match(T__3);
					setState(172);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 615711479169027L) != 0)) {
						{
						setState(171);
						returnType();
						}
					}

					}
				}

				setState(176);
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
		public TypeTupleContext typeTuple() {
			return getRuleContext(TypeTupleContext.class,0);
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
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
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
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				typeTuple();
				}
				break;
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				match(VOID);
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
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(183);
				tags();
				}
			}

			setState(186);
			match(CLASS);
			setState(187);
			name();
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(188);
				genericsDeclarations();
				}
			}

			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(191);
				match(IMPLEMENTS);
				setState(192);
				interfaceImplementations();
				}
			}

			setState(195);
			match(T__4);
			setState(199);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(196);
					fieldDeclaration();
					}
					} 
				}
				setState(201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==ID) {
				{
				{
				setState(202);
				function();
				}
				}
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(208);
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
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(210);
				tags();
				}
			}

			setState(213);
			match(INTERFACE);
			setState(214);
			name();
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(215);
				genericsDeclarations();
				}
			}

			setState(220);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(218);
				match(EXTENDS);
				setState(219);
				interfaceImplementations();
				}
			}

			setState(222);
			match(T__4);
			setState(223);
			interfaceStatements();
			setState(224);
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
			setState(226);
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
			setState(228);
			interfaceImplementation();
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(229);
				match(T__1);
				setState(230);
				interfaceImplementation();
				}
				}
				setState(235);
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
			setState(236);
			fqdn(0);
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__19) {
				{
				setState(237);
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
			setState(243);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARP || _la==ID) {
				{
				{
				setState(240);
				functionDeclaration();
				}
				}
				setState(245);
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
			setState(284);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(246);
					tags();
					}
				}

				setState(249);
				match(ID);
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__19) {
					{
					setState(250);
					genericsDeclarations();
					}
				}

				setState(253);
				match(T__0);
				setState(254);
				extend();
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(255);
					match(T__1);
					setState(256);
					parameterDefinitions();
					}
				}

				setState(259);
				match(T__2);
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(260);
					match(T__3);
					setState(262);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
					case 1:
						{
						setState(261);
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
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SHARP) {
					{
					setState(266);
					tags();
					}
				}

				setState(269);
				match(ID);
				setState(271);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__19) {
					{
					setState(270);
					genericsDeclarations();
					}
				}

				setState(273);
				match(T__0);
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 686078075863043L) != 0)) {
					{
					setState(274);
					parameterDefinitions();
					}
				}

				setState(277);
				match(T__2);
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(278);
					match(T__3);
					setState(280);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
					case 1:
						{
						setState(279);
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
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(286);
				tags();
				}
			}

			setState(289);
			match(TAG);
			setState(290);
			match(ID);
			setState(291);
			match(T__4);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 615709331685379L) != 0)) {
				{
				{
				setState(292);
				fieldDeclaration();
				}
				}
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(298);
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
			setState(300);
			typeName(0);
			setState(301);
			match(ID);
			setState(306);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(302);
				match(T__1);
				setState(303);
				match(ID);
				}
				}
				setState(308);
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
		public DecoratorCallContext decoratorCall() {
			return getRuleContext(DecoratorCallContext.class,0);
		}
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
		public TryCatchBlockContext tryCatchBlock() {
			return getRuleContext(TryCatchBlockContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
			setState(320);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(309);
				decoratorCall();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(310);
				function();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(311);
				variableDeclarations();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(312);
				return_();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(313);
				controlFlow();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(314);
				nativeBlock();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(315);
				whileBlock();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(316);
				forBlock();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(317);
				ifBlock();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(318);
				tryCatchBlock();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(319);
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
			setState(322);
			match(T__4);
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9002117311997382398L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 218103461L) != 0)) {
				{
				{
				setState(323);
				statement();
				}
				}
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(329);
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
			setState(331);
			match(WHILE);
			setState(332);
			expression(0);
			setState(333);
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
		public ExpressionContext initExpression;
		public ExpressionContext conditionExpression;
		public ExpressionContext incrementExpression;
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
			setState(335);
			match(FOR);
			setState(338);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(336);
				((ForBlockContext)_localctx).initExpression = expression(0);
				}
				break;
			case 2:
				{
				setState(337);
				variableDeclarations();
				}
				break;
			}
			setState(340);
			match(T__6);
			setState(341);
			((ForBlockContext)_localctx).conditionExpression = expression(0);
			setState(342);
			match(T__6);
			setState(343);
			((ForBlockContext)_localctx).incrementExpression = expression(0);
			setState(344);
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
		public TerminalNode IF() { return getToken(caffcParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public ReturnContext return_() {
			return getRuleContext(ReturnContext.class,0);
		}
		public ControlFlowContext controlFlow() {
			return getRuleContext(ControlFlowContext.class,0);
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
			setState(359);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(346);
				match(IF);
				setState(347);
				expression(0);
				setState(351);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__4:
					{
					setState(348);
					block();
					}
					break;
				case RETURN:
				case YIELD:
					{
					setState(349);
					return_();
					}
					break;
				case BREAK:
				case CONTINUE:
					{
					setState(350);
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
				setState(353);
				match(IF);
				setState(354);
				expression(0);
				setState(355);
				block();
				setState(356);
				match(ELSE);
				setState(357);
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
	public static class TryCatchBlockContext extends ParserRuleContext {
		public TerminalNode TRY() { return getToken(caffcParser.TRY, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public TerminalNode CATCH() { return getToken(caffcParser.CATCH, 0); }
		public ClassTypeContext classType() {
			return getRuleContext(ClassTypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(caffcParser.ID, 0); }
		public TerminalNode FINALLY() { return getToken(caffcParser.FINALLY, 0); }
		public TryCatchBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryCatchBlock; }
	}

	public final TryCatchBlockContext tryCatchBlock() throws RecognitionException {
		TryCatchBlockContext _localctx = new TryCatchBlockContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_tryCatchBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(361);
			match(TRY);
			setState(362);
			block();
			setState(369);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CATCH) {
				{
				setState(363);
				match(CATCH);
				setState(364);
				match(T__0);
				setState(365);
				classType();
				setState(366);
				match(ID);
				setState(367);
				match(T__2);
				}
			}

			setState(373);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(371);
				match(FINALLY);
				setState(372);
				block();
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
	public static class ReturnContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(caffcParser.RETURN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode YIELD() { return getToken(caffcParser.YIELD, 0); }
		public ReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return; }
	}

	public final ReturnContext return_() throws RecognitionException {
		ReturnContext _localctx = new ReturnContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_return);
		try {
			setState(380);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(375);
				match(RETURN);
				setState(376);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				match(RETURN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(378);
				match(YIELD);
				setState(379);
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
		enterRule(_localctx, 50, RULE_decoratorCall);
		try {
			setState(390);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(382);
				match(T__7);
				setState(383);
				expression(0);
				setState(384);
				function();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(386);
				match(T__7);
				setState(387);
				expression(0);
				setState(388);
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
		enterRule(_localctx, 52, RULE_controlFlow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
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
		enterRule(_localctx, 54, RULE_variableDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			varType();
			setState(395);
			variableDeclaration();
			setState(400);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(396);
				match(T__1);
				setState(397);
				variableDeclaration();
				}
				}
				setState(402);
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
		enterRule(_localctx, 56, RULE_variableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(403);
			match(ID);
			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(404);
				match(T__8);
				setState(405);
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
		enterRule(_localctx, 58, RULE_varType);
		try {
			setState(410);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(408);
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
				setState(409);
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
		enterRule(_localctx, 60, RULE_tags);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(412);
				tag();
				}
				}
				setState(415); 
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
		enterRule(_localctx, 62, RULE_tag);
		int _la;
		try {
			setState(432);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(417);
				match(SHARP);
				setState(418);
				fqdn(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(419);
				match(SHARP);
				setState(420);
				fqdn(0);
				setState(421);
				match(T__0);
				setState(422);
				tagParam();
				setState(427);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(423);
					match(T__1);
					setState(424);
					tagParam();
					}
					}
					setState(429);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(430);
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
		enterRule(_localctx, 64, RULE_tagParam);
		try {
			setState(438);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(434);
				match(ID);
				setState(435);
				match(T__8);
				setState(436);
				constExpression();
				}
				break;
			case STRING:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(437);
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
		enterRule(_localctx, 66, RULE_constExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
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
		int _startState = 68;
		enterRecursionRule(_localctx, 68, RULE_fqdn, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new FqdnIdContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			setState(443);
			match(ID);
			}
			_ctx.stop = _input.LT(-1);
			setState(450);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FqdnMultipleContext(new FqdnContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_fqdn);
					setState(445);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(446);
					match(DOT);
					setState(447);
					match(ID);
					}
					} 
				}
				setState(452);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
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
	public static class ExOpAssignContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExOpAssignContext(ExpressionContext ctx) { copyFrom(ctx); }
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
	public static class ExAssignContext extends ExpressionContext {
		public ExpressionContext leftExpression;
		public ExpressionContext rightExpression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExAssignContext(ExpressionContext ctx) { copyFrom(ctx); }
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
		int _startState = 70;
		enterRecursionRule(_localctx, 70, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(485);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				_localctx = new ExNumberContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(454);
				match(NUMBER);
				}
				break;
			case 2:
				{
				_localctx = new ExStringContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(455);
				match(STRING);
				}
				break;
			case 3:
				{
				_localctx = new ExIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(456);
				match(ID);
				}
				break;
			case 4:
				{
				_localctx = new ExParensContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(457);
				match(T__0);
				setState(458);
				expression(0);
				setState(459);
				match(T__2);
				}
				break;
			case 5:
				{
				_localctx = new ExNewObjectContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(461);
				match(NEW);
				setState(462);
				newType(0);
				setState(463);
				match(T__0);
				setState(465);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9223363240761724926L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 201326593L) != 0)) {
					{
					setState(464);
					expressionTuple();
					}
				}

				setState(467);
				match(T__2);
				}
				break;
			case 6:
				{
				_localctx = new ExNewArrayContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(469);
				match(NEW);
				setState(470);
				newType(0);
				setState(475); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(471);
						match(T__9);
						setState(472);
						expression(0);
						setState(473);
						match(T__10);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(477); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 7:
				{
				_localctx = new ExBoolNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(479);
				_la = _input.LA(1);
				if ( !(_la==T__11 || _la==NOT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(480);
				expression(17);
				}
				break;
			case 8:
				{
				_localctx = new ExBitNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(481);
				match(T__12);
				setState(482);
				expression(16);
				}
				break;
			case 9:
				{
				_localctx = new ExUnaryMinusContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(483);
				match(T__13);
				setState(484);
				expression(15);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(554);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(552);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
					case 1:
						{
						_localctx = new ExMulModContext(new ExpressionContext(_parentctx, _parentState));
						((ExMulModContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(487);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(488);
						_la = _input.LA(1);
						if ( !(_la==T__14 || _la==STAR) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(489);
						((ExMulModContext)_localctx).rightExpression = expression(15);
						}
						break;
					case 2:
						{
						_localctx = new ExDivContext(new ExpressionContext(_parentctx, _parentState));
						((ExDivContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(490);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(491);
						match(T__15);
						setState(492);
						((ExDivContext)_localctx).rightExpression = expression(14);
						}
						break;
					case 3:
						{
						_localctx = new ExAddSubContext(new ExpressionContext(_parentctx, _parentState));
						((ExAddSubContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(493);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(494);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==T__16) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(495);
						((ExAddSubContext)_localctx).rightExpression = expression(13);
						}
						break;
					case 4:
						{
						_localctx = new ExShiftContext(new ExpressionContext(_parentctx, _parentState));
						((ExShiftContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(496);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(497);
						_la = _input.LA(1);
						if ( !(_la==T__17 || _la==T__18) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(498);
						((ExShiftContext)_localctx).rightExpression = expression(12);
						}
						break;
					case 5:
						{
						_localctx = new ExLtLteGtGteContext(new ExpressionContext(_parentctx, _parentState));
						((ExLtLteGtGteContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(499);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(500);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 15728640L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(501);
						((ExLtLteGtGteContext)_localctx).rightExpression = expression(11);
						}
						break;
					case 6:
						{
						_localctx = new ExEqNeqContext(new ExpressionContext(_parentctx, _parentState));
						((ExEqNeqContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(502);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(503);
						_la = _input.LA(1);
						if ( !(_la==T__23 || _la==T__24) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(504);
						((ExEqNeqContext)_localctx).rightExpression = expression(10);
						}
						break;
					case 7:
						{
						_localctx = new ExBitAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitAndContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(505);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(506);
						match(T__25);
						setState(507);
						((ExBitAndContext)_localctx).rightExpression = expression(9);
						}
						break;
					case 8:
						{
						_localctx = new ExBitXorContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitXorContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(508);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(509);
						match(T__26);
						setState(510);
						((ExBitXorContext)_localctx).rightExpression = expression(8);
						}
						break;
					case 9:
						{
						_localctx = new ExBitOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExBitOrContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(511);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(512);
						match(T__27);
						setState(513);
						((ExBitOrContext)_localctx).rightExpression = expression(7);
						}
						break;
					case 10:
						{
						_localctx = new ExBoolAndContext(new ExpressionContext(_parentctx, _parentState));
						((ExBoolAndContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(514);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(515);
						match(AND);
						setState(516);
						((ExBoolAndContext)_localctx).rightExpression = expression(6);
						}
						break;
					case 11:
						{
						_localctx = new ExBoolOrContext(new ExpressionContext(_parentctx, _parentState));
						((ExBoolOrContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(517);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(518);
						match(OR);
						setState(519);
						((ExBoolOrContext)_localctx).rightExpression = expression(5);
						}
						break;
					case 12:
						{
						_localctx = new ExTernaryContext(new ExpressionContext(_parentctx, _parentState));
						((ExTernaryContext)_localctx).checkExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(520);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(526);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case T__28:
							{
							setState(521);
							match(T__28);
							setState(522);
							((ExTernaryContext)_localctx).trueExpression = expression(0);
							setState(523);
							match(T__29);
							}
							break;
						case T__30:
							{
							setState(525);
							match(T__30);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(528);
						((ExTernaryContext)_localctx).falseExpression = expression(4);
						}
						break;
					case 13:
						{
						_localctx = new ExOpAssignContext(new ExpressionContext(_parentctx, _parentState));
						((ExOpAssignContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(529);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(530);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2194728288256L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(531);
						((ExOpAssignContext)_localctx).rightExpression = expression(3);
						}
						break;
					case 14:
						{
						_localctx = new ExAssignContext(new ExpressionContext(_parentctx, _parentState));
						((ExAssignContext)_localctx).leftExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(532);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(533);
						match(T__8);
						setState(534);
						((ExAssignContext)_localctx).rightExpression = expression(2);
						}
						break;
					case 15:
						{
						_localctx = new ExDotAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(535);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(536);
						match(DOT);
						setState(537);
						match(ID);
						}
						break;
					case 16:
						{
						_localctx = new ExFnCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(538);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(540);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__19) {
							{
							setState(539);
							genericsInstantiations();
							}
						}

						setState(542);
						match(T__0);
						setState(544);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -9223363240761724926L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 201326593L) != 0)) {
							{
							setState(543);
							expressionTuple();
							}
						}

						setState(546);
						match(T__2);
						}
						break;
					case 17:
						{
						_localctx = new ExIndexAccessContext(new ExpressionContext(_parentctx, _parentState));
						((ExIndexAccessContext)_localctx).arraryExpression = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(547);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(548);
						match(T__9);
						setState(549);
						((ExIndexAccessContext)_localctx).indexExpression = expression(0);
						setState(550);
						match(T__10);
						}
						break;
					}
					} 
				}
				setState(556);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,65,_ctx);
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
			setState(557);
			expression(0);
			setState(562);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(558);
				match(T__1);
				setState(559);
				expression(0);
				}
				}
				setState(564);
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
			setState(565);
			match(EXTENDS);
			setState(566);
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
			setState(568);
			parameterDefinition();
			setState(573);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(569);
				match(T__1);
				setState(570);
				parameterDefinition();
				}
				}
				setState(575);
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
			setState(577);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARP) {
				{
				setState(576);
				tags();
				}
			}

			setState(579);
			typeName(0);
			setState(580);
			match(ID);
			setState(582);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(581);
				match(STAR);
				}
			}

			setState(586);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(584);
				match(T__8);
				setState(585);
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
			setState(592);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new TypeClassContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(589);
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
				setState(590);
				primitiveTypeName();
				}
				break;
			case FN:
				{
				_localctx = new TypeFunctionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(591);
				functionType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(603);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeArrayContext(new TypeNameContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_typeName);
					setState(594);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(597); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(595);
							match(T__9);
							setState(596);
							match(T__10);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(599); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,72,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(605);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
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
			setState(609);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				_localctx = new NewTypeClassContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(607);
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
				setState(608);
				primitiveTypeName();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(620);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new NewTypeArrayContext(new NewTypeContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_newType);
					setState(611);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(614); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(612);
							match(T__9);
							setState(613);
							match(T__10);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(616); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(622);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
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
			setState(623);
			_la = _input.LA(1);
			if ( !(((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 17575006175235L) != 0)) ) {
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
			setState(625);
			fqdn(0);
			setState(627);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(626);
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
			setState(629);
			match(T__19);
			setState(630);
			genericDeclaration();
			setState(635);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(631);
				match(T__1);
				setState(632);
				genericDeclaration();
				}
				}
				setState(637);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(638);
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
			setState(644);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				_localctx = new GenericDeclarationIdContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(640);
				match(ID);
				}
				break;
			case 2:
				_localctx = new GenericDeclarationRestrictedContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(641);
				match(ID);
				setState(642);
				match(IS);
				setState(643);
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
			setState(659);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				_localctx = new GenericsInstantiationEmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(646);
				match(T__19);
				setState(647);
				match(T__22);
				}
				break;
			case 2:
				_localctx = new GenericsInstantiationTypeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(648);
				match(T__19);
				setState(649);
				typeName(0);
				setState(654);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(650);
					match(T__1);
					setState(651);
					typeName(0);
					}
					}
					setState(656);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(657);
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
			setState(661);
			match(FN);
			setState(663);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				setState(662);
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
	public static class TypeTupleContext extends ParserRuleContext {
		public List<TypeNameContext> typeName() {
			return getRuleContexts(TypeNameContext.class);
		}
		public TypeNameContext typeName(int i) {
			return getRuleContext(TypeNameContext.class,i);
		}
		public TypeTupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeTuple; }
	}

	public final TypeTupleContext typeTuple() throws RecognitionException {
		TypeTupleContext _localctx = new TypeTupleContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_typeTuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(665);
			typeName(0);
			setState(670);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(666);
				match(T__1);
				setState(667);
				typeName(0);
				}
				}
				setState(672);
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
		case 34:
			return fqdn_sempred((FqdnContext)_localctx, predIndex);
		case 35:
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
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		case 5:
			return precpred(_ctx, 10);
		case 6:
			return precpred(_ctx, 9);
		case 7:
			return precpred(_ctx, 8);
		case 8:
			return precpred(_ctx, 7);
		case 9:
			return precpred(_ctx, 6);
		case 10:
			return precpred(_ctx, 5);
		case 11:
			return precpred(_ctx, 4);
		case 12:
			return precpred(_ctx, 3);
		case 13:
			return precpred(_ctx, 2);
		case 14:
			return precpred(_ctx, 1);
		case 15:
			return precpred(_ctx, 22);
		case 16:
			return precpred(_ctx, 19);
		case 17:
			return precpred(_ctx, 18);
		}
		return true;
	}
	private boolean typeName_sempred(TypeNameContext _localctx, int predIndex) {
		switch (predIndex) {
		case 18:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean newType_sempred(NewTypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 19:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\\\u02a2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003\u007f\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0003\u0007"+
		"\u008a\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u008e\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0094\b\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u0099\b\u0007\u0003\u0007\u009b"+
		"\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00a0\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0003\u0007\u00a4\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007\u00a8\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u00ad\b\u0007\u0003\u0007\u00af\b\u0007\u0001\u0007\u0003\u0007\u00b2"+
		"\b\u0007\u0001\b\u0001\b\u0003\b\u00b6\b\b\u0001\t\u0003\t\u00b9\b\t\u0001"+
		"\t\u0001\t\u0001\t\u0003\t\u00be\b\t\u0001\t\u0001\t\u0003\t\u00c2\b\t"+
		"\u0001\t\u0001\t\u0005\t\u00c6\b\t\n\t\f\t\u00c9\t\t\u0001\t\u0005\t\u00cc"+
		"\b\t\n\t\f\t\u00cf\t\t\u0001\t\u0001\t\u0001\n\u0003\n\u00d4\b\n\u0001"+
		"\n\u0001\n\u0001\n\u0003\n\u00d9\b\n\u0001\n\u0001\n\u0003\n\u00dd\b\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0005\f\u00e8\b\f\n\f\f\f\u00eb\t\f\u0001\r\u0001\r\u0003\r"+
		"\u00ef\b\r\u0001\u000e\u0005\u000e\u00f2\b\u000e\n\u000e\f\u000e\u00f5"+
		"\t\u000e\u0001\u000f\u0003\u000f\u00f8\b\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u00fc\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u0102\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u0107\b\u000f\u0003\u000f\u0109\b\u000f\u0001\u000f\u0003\u000f\u010c"+
		"\b\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u0110\b\u000f\u0001\u000f"+
		"\u0001\u000f\u0003\u000f\u0114\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0003\u000f\u0119\b\u000f\u0003\u000f\u011b\b\u000f\u0003\u000f\u011d"+
		"\b\u000f\u0001\u0010\u0003\u0010\u0120\b\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0005\u0010\u0126\b\u0010\n\u0010\f\u0010\u0129"+
		"\t\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0005\u0011\u0131\b\u0011\n\u0011\f\u0011\u0134\t\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0141\b\u0012"+
		"\u0001\u0013\u0001\u0013\u0005\u0013\u0145\b\u0013\n\u0013\f\u0013\u0148"+
		"\t\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0153\b\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0160"+
		"\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0003\u0016\u0168\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0172"+
		"\b\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0176\b\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u017d\b\u0018"+
		"\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0003\u0019\u0187\b\u0019\u0001\u001a\u0001\u001a"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u018f\b\u001b"+
		"\n\u001b\f\u001b\u0192\t\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0003"+
		"\u001c\u0197\b\u001c\u0001\u001d\u0001\u001d\u0003\u001d\u019b\b\u001d"+
		"\u0001\u001e\u0004\u001e\u019e\b\u001e\u000b\u001e\f\u001e\u019f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0005\u001f\u01aa\b\u001f\n\u001f\f\u001f\u01ad\t\u001f"+
		"\u0001\u001f\u0001\u001f\u0003\u001f\u01b1\b\u001f\u0001 \u0001 \u0001"+
		" \u0001 \u0003 \u01b7\b \u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\""+
		"\u0001\"\u0001\"\u0005\"\u01c1\b\"\n\"\f\"\u01c4\t\"\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0003"+
		"#\u01d2\b#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0004"+
		"#\u01dc\b#\u000b#\f#\u01dd\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0003"+
		"#\u01e6\b#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0003#\u020f\b#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0003#\u021d\b#\u0001#\u0001#\u0003#\u0221"+
		"\b#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0005#\u0229\b#\n#\f#\u022c"+
		"\t#\u0001$\u0001$\u0001$\u0005$\u0231\b$\n$\f$\u0234\t$\u0001%\u0001%"+
		"\u0001%\u0001&\u0001&\u0001&\u0005&\u023c\b&\n&\f&\u023f\t&\u0001\'\u0003"+
		"\'\u0242\b\'\u0001\'\u0001\'\u0001\'\u0003\'\u0247\b\'\u0001\'\u0001\'"+
		"\u0003\'\u024b\b\'\u0001(\u0001(\u0001(\u0001(\u0003(\u0251\b(\u0001("+
		"\u0001(\u0001(\u0004(\u0256\b(\u000b(\f(\u0257\u0005(\u025a\b(\n(\f(\u025d"+
		"\t(\u0001)\u0001)\u0001)\u0003)\u0262\b)\u0001)\u0001)\u0001)\u0004)\u0267"+
		"\b)\u000b)\f)\u0268\u0005)\u026b\b)\n)\f)\u026e\t)\u0001*\u0001*\u0001"+
		"+\u0001+\u0003+\u0274\b+\u0001,\u0001,\u0001,\u0001,\u0005,\u027a\b,\n"+
		",\f,\u027d\t,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001-\u0003-\u0285\b"+
		"-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0005.\u028d\b.\n.\f.\u0290"+
		"\t.\u0001.\u0001.\u0003.\u0294\b.\u0001/\u0001/\u0003/\u0298\b/\u0001"+
		"0\u00010\u00010\u00050\u029d\b0\n0\f0\u02a0\t0\u00010\u0000\u0004DFPR"+
		"1\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`\u0000\n\u0002\u000011"+
		"44\u0002\u0000++[[\u0002\u0000\f\f@@\u0002\u0000\u000f\u000fXX\u0002\u0000"+
		"\u000e\u000e\u0011\u0011\u0001\u0000\u0012\u0013\u0001\u0000\u0014\u0017"+
		"\u0001\u0000\u0018\u0019\u0001\u0000 (\u0002\u0000)*KT\u02ec\u0000b\u0001"+
		"\u0000\u0000\u0000\u0002o\u0001\u0000\u0000\u0000\u0004r\u0001\u0000\u0000"+
		"\u0000\u0006~\u0001\u0000\u0000\u0000\b\u0080\u0001\u0000\u0000\u0000"+
		"\n\u0083\u0001\u0000\u0000\u0000\f\u0086\u0001\u0000\u0000\u0000\u000e"+
		"\u00b1\u0001\u0000\u0000\u0000\u0010\u00b5\u0001\u0000\u0000\u0000\u0012"+
		"\u00b8\u0001\u0000\u0000\u0000\u0014\u00d3\u0001\u0000\u0000\u0000\u0016"+
		"\u00e2\u0001\u0000\u0000\u0000\u0018\u00e4\u0001\u0000\u0000\u0000\u001a"+
		"\u00ec\u0001\u0000\u0000\u0000\u001c\u00f3\u0001\u0000\u0000\u0000\u001e"+
		"\u011c\u0001\u0000\u0000\u0000 \u011f\u0001\u0000\u0000\u0000\"\u012c"+
		"\u0001\u0000\u0000\u0000$\u0140\u0001\u0000\u0000\u0000&\u0142\u0001\u0000"+
		"\u0000\u0000(\u014b\u0001\u0000\u0000\u0000*\u014f\u0001\u0000\u0000\u0000"+
		",\u0167\u0001\u0000\u0000\u0000.\u0169\u0001\u0000\u0000\u00000\u017c"+
		"\u0001\u0000\u0000\u00002\u0186\u0001\u0000\u0000\u00004\u0188\u0001\u0000"+
		"\u0000\u00006\u018a\u0001\u0000\u0000\u00008\u0193\u0001\u0000\u0000\u0000"+
		":\u019a\u0001\u0000\u0000\u0000<\u019d\u0001\u0000\u0000\u0000>\u01b0"+
		"\u0001\u0000\u0000\u0000@\u01b6\u0001\u0000\u0000\u0000B\u01b8\u0001\u0000"+
		"\u0000\u0000D\u01ba\u0001\u0000\u0000\u0000F\u01e5\u0001\u0000\u0000\u0000"+
		"H\u022d\u0001\u0000\u0000\u0000J\u0235\u0001\u0000\u0000\u0000L\u0238"+
		"\u0001\u0000\u0000\u0000N\u0241\u0001\u0000\u0000\u0000P\u0250\u0001\u0000"+
		"\u0000\u0000R\u0261\u0001\u0000\u0000\u0000T\u026f\u0001\u0000\u0000\u0000"+
		"V\u0271\u0001\u0000\u0000\u0000X\u0275\u0001\u0000\u0000\u0000Z\u0284"+
		"\u0001\u0000\u0000\u0000\\\u0293\u0001\u0000\u0000\u0000^\u0295\u0001"+
		"\u0000\u0000\u0000`\u0299\u0001\u0000\u0000\u0000bf\u0003\u0002\u0001"+
		"\u0000ce\u0003\u0004\u0002\u0000dc\u0001\u0000\u0000\u0000eh\u0001\u0000"+
		"\u0000\u0000fd\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gl\u0001"+
		"\u0000\u0000\u0000hf\u0001\u0000\u0000\u0000ik\u0003\u0006\u0003\u0000"+
		"ji\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000"+
		"\u0000lm\u0001\u0000\u0000\u0000m\u0001\u0001\u0000\u0000\u0000nl\u0001"+
		"\u0000\u0000\u0000op\u0005>\u0000\u0000pq\u0003D\"\u0000q\u0003\u0001"+
		"\u0000\u0000\u0000rt\u0003\b\u0004\u0000su\u0003\n\u0005\u0000ts\u0001"+
		"\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000u\u0005\u0001\u0000\u0000"+
		"\u0000v\u007f\u0003\f\u0006\u0000w\u007f\u00032\u0019\u0000x\u007f\u0003"+
		" \u0010\u0000y\u007f\u0003\u000e\u0007\u0000z\u007f\u0003\u0012\t\u0000"+
		"{\u007f\u0003\u0014\n\u0000|\u007f\u00038\u001c\u0000}\u007f\u0003&\u0013"+
		"\u0000~v\u0001\u0000\u0000\u0000~w\u0001\u0000\u0000\u0000~x\u0001\u0000"+
		"\u0000\u0000~y\u0001\u0000\u0000\u0000~z\u0001\u0000\u0000\u0000~{\u0001"+
		"\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~}\u0001\u0000\u0000\u0000"+
		"\u007f\u0007\u0001\u0000\u0000\u0000\u0080\u0081\u0005F\u0000\u0000\u0081"+
		"\u0082\u0003D\"\u0000\u0082\t\u0001\u0000\u0000\u0000\u0083\u0084\u0005"+
		"/\u0000\u0000\u0084\u0085\u0005Z\u0000\u0000\u0085\u000b\u0001\u0000\u0000"+
		"\u0000\u0086\u0087\u0005U\u0000\u0000\u0087\r\u0001\u0000\u0000\u0000"+
		"\u0088\u008a\u0003<\u001e\u0000\u0089\u0088\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b"+
		"\u008d\u0005Z\u0000\u0000\u008c\u008e\u0003X,\u0000\u008d\u008c\u0001"+
		"\u0000\u0000\u0000\u008d\u008e\u0001\u0000\u0000\u0000\u008e\u008f\u0001"+
		"\u0000\u0000\u0000\u008f\u0090\u0005\u0001\u0000\u0000\u0090\u0093\u0003"+
		"J%\u0000\u0091\u0092\u0005\u0002\u0000\u0000\u0092\u0094\u0003L&\u0000"+
		"\u0093\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000"+
		"\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u009a\u0005\u0003\u0000\u0000"+
		"\u0096\u0098\u0005\u0004\u0000\u0000\u0097\u0099\u0003\u0010\b\u0000\u0098"+
		"\u0097\u0001\u0000\u0000\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099"+
		"\u009b\u0001\u0000\u0000\u0000\u009a\u0096\u0001\u0000\u0000\u0000\u009a"+
		"\u009b\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0003&\u0013\u0000\u009d\u00b2\u0001\u0000\u0000\u0000\u009e\u00a0"+
		"\u0003<\u001e\u0000\u009f\u009e\u0001\u0000\u0000\u0000\u009f\u00a0\u0001"+
		"\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000\u0000\u00a1\u00a3\u0005"+
		"Z\u0000\u0000\u00a2\u00a4\u0003X,\u0000\u00a3\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a5\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a7\u0005\u0001\u0000\u0000\u00a6\u00a8\u0003L&\u0000\u00a7"+
		"\u00a6\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a9\u0001\u0000\u0000\u0000\u00a9\u00ae\u0005\u0003\u0000\u0000\u00aa"+
		"\u00ac\u0005\u0004\u0000\u0000\u00ab\u00ad\u0003\u0010\b\u0000\u00ac\u00ab"+
		"\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001\u0000\u0000\u0000\u00ad\u00af"+
		"\u0001\u0000\u0000\u0000\u00ae\u00aa\u0001\u0000\u0000\u0000\u00ae\u00af"+
		"\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b2"+
		"\u0003&\u0013\u0000\u00b1\u0089\u0001\u0000\u0000\u0000\u00b1\u009f\u0001"+
		"\u0000\u0000\u0000\u00b2\u000f\u0001\u0000\u0000\u0000\u00b3\u00b6\u0003"+
		"`0\u0000\u00b4\u00b6\u0005H\u0000\u0000\u00b5\u00b3\u0001\u0000\u0000"+
		"\u0000\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6\u0011\u0001\u0000\u0000"+
		"\u0000\u00b7\u00b9\u0003<\u001e\u0000\u00b8\u00b7\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00ba\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bb\u00053\u0000\u0000\u00bb\u00bd\u0003\u0016\u000b\u0000\u00bc"+
		"\u00be\u0003X,\u0000\u00bd\u00bc\u0001\u0000\u0000\u0000\u00bd\u00be\u0001"+
		"\u0000\u0000\u0000\u00be\u00c1\u0001\u0000\u0000\u0000\u00bf\u00c0\u0005"+
		":\u0000\u0000\u00c0\u00c2\u0003\u0018\f\u0000\u00c1\u00bf\u0001\u0000"+
		"\u0000\u0000\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c7\u0005\u0005\u0000\u0000\u00c4\u00c6\u0003\"\u0011"+
		"\u0000\u00c5\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c9\u0001\u0000\u0000"+
		"\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000"+
		"\u0000\u00c8\u00cd\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000"+
		"\u0000\u00ca\u00cc\u0003\u000e\u0007\u0000\u00cb\u00ca\u0001\u0000\u0000"+
		"\u0000\u00cc\u00cf\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000\u0000"+
		"\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00d0\u0001\u0000\u0000"+
		"\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005\u0006\u0000"+
		"\u0000\u00d1\u0013\u0001\u0000\u0000\u0000\u00d2\u00d4\u0003<\u001e\u0000"+
		"\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d6\u0005<\u0000\u0000\u00d6"+
		"\u00d8\u0003\u0016\u000b\u0000\u00d7\u00d9\u0003X,\u0000\u00d8\u00d7\u0001"+
		"\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00dc\u0001"+
		"\u0000\u0000\u0000\u00da\u00db\u00056\u0000\u0000\u00db\u00dd\u0003\u0018"+
		"\f\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000"+
		"\u0000\u00dd\u00de\u0001\u0000\u0000\u0000\u00de\u00df\u0005\u0005\u0000"+
		"\u0000\u00df\u00e0\u0003\u001c\u000e\u0000\u00e0\u00e1\u0005\u0006\u0000"+
		"\u0000\u00e1\u0015\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005Z\u0000\u0000"+
		"\u00e3\u0017\u0001\u0000\u0000\u0000\u00e4\u00e9\u0003\u001a\r\u0000\u00e5"+
		"\u00e6\u0005\u0002\u0000\u0000\u00e6\u00e8\u0003\u001a\r\u0000\u00e7\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e8\u00eb\u0001\u0000\u0000\u0000\u00e9\u00e7"+
		"\u0001\u0000\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u0019"+
		"\u0001\u0000\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000\u0000\u00ec\u00ee"+
		"\u0003D\"\u0000\u00ed\u00ef\u0003\\.\u0000\u00ee\u00ed\u0001\u0000\u0000"+
		"\u0000\u00ee\u00ef\u0001\u0000\u0000\u0000\u00ef\u001b\u0001\u0000\u0000"+
		"\u0000\u00f0\u00f2\u0003\u001e\u000f\u0000\u00f1\u00f0\u0001\u0000\u0000"+
		"\u0000\u00f2\u00f5\u0001\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u001d\u0001\u0000\u0000"+
		"\u0000\u00f5\u00f3\u0001\u0000\u0000\u0000\u00f6\u00f8\u0003<\u001e\u0000"+
		"\u00f7\u00f6\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000\u0000\u0000"+
		"\u00f8\u00f9\u0001\u0000\u0000\u0000\u00f9\u00fb\u0005Z\u0000\u0000\u00fa"+
		"\u00fc\u0003X,\u0000\u00fb\u00fa\u0001\u0000\u0000\u0000\u00fb\u00fc\u0001"+
		"\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000\u0000\u00fd\u00fe\u0005"+
		"\u0001\u0000\u0000\u00fe\u0101\u0003J%\u0000\u00ff\u0100\u0005\u0002\u0000"+
		"\u0000\u0100\u0102\u0003L&\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101"+
		"\u0102\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000\u0000\u0103"+
		"\u0108\u0005\u0003\u0000\u0000\u0104\u0106\u0005\u0004\u0000\u0000\u0105"+
		"\u0107\u0003\u0010\b\u0000\u0106\u0105\u0001\u0000\u0000\u0000\u0106\u0107"+
		"\u0001\u0000\u0000\u0000\u0107\u0109\u0001\u0000\u0000\u0000\u0108\u0104"+
		"\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000\u0000\u0109\u011d"+
		"\u0001\u0000\u0000\u0000\u010a\u010c\u0003<\u001e\u0000\u010b\u010a\u0001"+
		"\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c\u010d\u0001"+
		"\u0000\u0000\u0000\u010d\u010f\u0005Z\u0000\u0000\u010e\u0110\u0003X,"+
		"\u0000\u010f\u010e\u0001\u0000\u0000\u0000\u010f\u0110\u0001\u0000\u0000"+
		"\u0000\u0110\u0111\u0001\u0000\u0000\u0000\u0111\u0113\u0005\u0001\u0000"+
		"\u0000\u0112\u0114\u0003L&\u0000\u0113\u0112\u0001\u0000\u0000\u0000\u0113"+
		"\u0114\u0001\u0000\u0000\u0000\u0114\u0115\u0001\u0000\u0000\u0000\u0115"+
		"\u011a\u0005\u0003\u0000\u0000\u0116\u0118\u0005\u0004\u0000\u0000\u0117"+
		"\u0119\u0003\u0010\b\u0000\u0118\u0117\u0001\u0000\u0000\u0000\u0118\u0119"+
		"\u0001\u0000\u0000\u0000\u0119\u011b\u0001\u0000\u0000\u0000\u011a\u0116"+
		"\u0001\u0000\u0000\u0000\u011a\u011b\u0001\u0000\u0000\u0000\u011b\u011d"+
		"\u0001\u0000\u0000\u0000\u011c\u00f7\u0001\u0000\u0000\u0000\u011c\u010b"+
		"\u0001\u0000\u0000\u0000\u011d\u001f\u0001\u0000\u0000\u0000\u011e\u0120"+
		"\u0003<\u001e\u0000\u011f\u011e\u0001\u0000\u0000\u0000\u011f\u0120\u0001"+
		"\u0000\u0000\u0000\u0120\u0121\u0001\u0000\u0000\u0000\u0121\u0122\u0005"+
		"C\u0000\u0000\u0122\u0123\u0005Z\u0000\u0000\u0123\u0127\u0005\u0005\u0000"+
		"\u0000\u0124\u0126\u0003\"\u0011\u0000\u0125\u0124\u0001\u0000\u0000\u0000"+
		"\u0126\u0129\u0001\u0000\u0000\u0000\u0127\u0125\u0001\u0000\u0000\u0000"+
		"\u0127\u0128\u0001\u0000\u0000\u0000\u0128\u012a\u0001\u0000\u0000\u0000"+
		"\u0129\u0127\u0001\u0000\u0000\u0000\u012a\u012b\u0005\u0006\u0000\u0000"+
		"\u012b!\u0001\u0000\u0000\u0000\u012c\u012d\u0003P(\u0000\u012d\u0132"+
		"\u0005Z\u0000\u0000\u012e\u012f\u0005\u0002\u0000\u0000\u012f\u0131\u0005"+
		"Z\u0000\u0000\u0130\u012e\u0001\u0000\u0000\u0000\u0131\u0134\u0001\u0000"+
		"\u0000\u0000\u0132\u0130\u0001\u0000\u0000\u0000\u0132\u0133\u0001\u0000"+
		"\u0000\u0000\u0133#\u0001\u0000\u0000\u0000\u0134\u0132\u0001\u0000\u0000"+
		"\u0000\u0135\u0141\u00032\u0019\u0000\u0136\u0141\u0003\u000e\u0007\u0000"+
		"\u0137\u0141\u00036\u001b\u0000\u0138\u0141\u00030\u0018\u0000\u0139\u0141"+
		"\u00034\u001a\u0000\u013a\u0141\u0003\f\u0006\u0000\u013b\u0141\u0003"+
		"(\u0014\u0000\u013c\u0141\u0003*\u0015\u0000\u013d\u0141\u0003,\u0016"+
		"\u0000\u013e\u0141\u0003.\u0017\u0000\u013f\u0141\u0003F#\u0000\u0140"+
		"\u0135\u0001\u0000\u0000\u0000\u0140\u0136\u0001\u0000\u0000\u0000\u0140"+
		"\u0137\u0001\u0000\u0000\u0000\u0140\u0138\u0001\u0000\u0000\u0000\u0140"+
		"\u0139\u0001\u0000\u0000\u0000\u0140\u013a\u0001\u0000\u0000\u0000\u0140"+
		"\u013b\u0001\u0000\u0000\u0000\u0140\u013c\u0001\u0000\u0000\u0000\u0140"+
		"\u013d\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140"+
		"\u013f\u0001\u0000\u0000\u0000\u0141%\u0001\u0000\u0000\u0000\u0142\u0146"+
		"\u0005\u0005\u0000\u0000\u0143\u0145\u0003$\u0012\u0000\u0144\u0143\u0001"+
		"\u0000\u0000\u0000\u0145\u0148\u0001\u0000\u0000\u0000\u0146\u0144\u0001"+
		"\u0000\u0000\u0000\u0146\u0147\u0001\u0000\u0000\u0000\u0147\u0149\u0001"+
		"\u0000\u0000\u0000\u0148\u0146\u0001\u0000\u0000\u0000\u0149\u014a\u0005"+
		"\u0006\u0000\u0000\u014a\'\u0001\u0000\u0000\u0000\u014b\u014c\u0005I"+
		"\u0000\u0000\u014c\u014d\u0003F#\u0000\u014d\u014e\u0003&\u0013\u0000"+
		"\u014e)\u0001\u0000\u0000\u0000\u014f\u0152\u00058\u0000\u0000\u0150\u0153"+
		"\u0003F#\u0000\u0151\u0153\u00036\u001b\u0000\u0152\u0150\u0001\u0000"+
		"\u0000\u0000\u0152\u0151\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000"+
		"\u0000\u0000\u0154\u0155\u0005\u0007\u0000\u0000\u0155\u0156\u0003F#\u0000"+
		"\u0156\u0157\u0005\u0007\u0000\u0000\u0157\u0158\u0003F#\u0000\u0158\u0159"+
		"\u0003&\u0013\u0000\u0159+\u0001\u0000\u0000\u0000\u015a\u015b\u00059"+
		"\u0000\u0000\u015b\u015f\u0003F#\u0000\u015c\u0160\u0003&\u0013\u0000"+
		"\u015d\u0160\u00030\u0018\u0000\u015e\u0160\u00034\u001a\u0000\u015f\u015c"+
		"\u0001\u0000\u0000\u0000\u015f\u015d\u0001\u0000\u0000\u0000\u015f\u015e"+
		"\u0001\u0000\u0000\u0000\u0160\u0168\u0001\u0000\u0000\u0000\u0161\u0162"+
		"\u00059\u0000\u0000\u0162\u0163\u0003F#\u0000\u0163\u0164\u0003&\u0013"+
		"\u0000\u0164\u0165\u00055\u0000\u0000\u0165\u0166\u0003&\u0013\u0000\u0166"+
		"\u0168\u0001\u0000\u0000\u0000\u0167\u015a\u0001\u0000\u0000\u0000\u0167"+
		"\u0161\u0001\u0000\u0000\u0000\u0168-\u0001\u0000\u0000\u0000\u0169\u016a"+
		"\u0005E\u0000\u0000\u016a\u0171\u0003&\u0013\u0000\u016b\u016c\u00052"+
		"\u0000\u0000\u016c\u016d\u0005\u0001\u0000\u0000\u016d\u016e\u0003V+\u0000"+
		"\u016e\u016f\u0005Z\u0000\u0000\u016f\u0170\u0005\u0003\u0000\u0000\u0170"+
		"\u0172\u0001\u0000\u0000\u0000\u0171\u016b\u0001\u0000\u0000\u0000\u0171"+
		"\u0172\u0001\u0000\u0000\u0000\u0172\u0175\u0001\u0000\u0000\u0000\u0173"+
		"\u0174\u00057\u0000\u0000\u0174\u0176\u0003&\u0013\u0000\u0175\u0173\u0001"+
		"\u0000\u0000\u0000\u0175\u0176\u0001\u0000\u0000\u0000\u0176/\u0001\u0000"+
		"\u0000\u0000\u0177\u0178\u0005B\u0000\u0000\u0178\u017d\u0003F#\u0000"+
		"\u0179\u017d\u0005B\u0000\u0000\u017a\u017b\u0005J\u0000\u0000\u017b\u017d"+
		"\u0003F#\u0000\u017c\u0177\u0001\u0000\u0000\u0000\u017c\u0179\u0001\u0000"+
		"\u0000\u0000\u017c\u017a\u0001\u0000\u0000\u0000\u017d1\u0001\u0000\u0000"+
		"\u0000\u017e\u017f\u0005\b\u0000\u0000\u017f\u0180\u0003F#\u0000\u0180"+
		"\u0181\u0003\u000e\u0007\u0000\u0181\u0187\u0001\u0000\u0000\u0000\u0182"+
		"\u0183\u0005\b\u0000\u0000\u0183\u0184\u0003F#\u0000\u0184\u0185\u0003"+
		"2\u0019\u0000\u0185\u0187\u0001\u0000\u0000\u0000\u0186\u017e\u0001\u0000"+
		"\u0000\u0000\u0186\u0182\u0001\u0000\u0000\u0000\u01873\u0001\u0000\u0000"+
		"\u0000\u0188\u0189\u0007\u0000\u0000\u0000\u01895\u0001\u0000\u0000\u0000"+
		"\u018a\u018b\u0003:\u001d\u0000\u018b\u0190\u00038\u001c\u0000\u018c\u018d"+
		"\u0005\u0002\u0000\u0000\u018d\u018f\u00038\u001c\u0000\u018e\u018c\u0001"+
		"\u0000\u0000\u0000\u018f\u0192\u0001\u0000\u0000\u0000\u0190\u018e\u0001"+
		"\u0000\u0000\u0000\u0190\u0191\u0001\u0000\u0000\u0000\u01917\u0001\u0000"+
		"\u0000\u0000\u0192\u0190\u0001\u0000\u0000\u0000\u0193\u0196\u0005Z\u0000"+
		"\u0000\u0194\u0195\u0005\t\u0000\u0000\u0195\u0197\u0003F#\u0000\u0196"+
		"\u0194\u0001\u0000\u0000\u0000\u0196\u0197\u0001\u0000\u0000\u0000\u0197"+
		"9\u0001\u0000\u0000\u0000\u0198\u019b\u0005G\u0000\u0000\u0199\u019b\u0003"+
		"P(\u0000\u019a\u0198\u0001\u0000\u0000\u0000\u019a\u0199\u0001\u0000\u0000"+
		"\u0000\u019b;\u0001\u0000\u0000\u0000\u019c\u019e\u0003>\u001f\u0000\u019d"+
		"\u019c\u0001\u0000\u0000\u0000\u019e\u019f\u0001\u0000\u0000\u0000\u019f"+
		"\u019d\u0001\u0000\u0000\u0000\u019f\u01a0\u0001\u0000\u0000\u0000\u01a0"+
		"=\u0001\u0000\u0000\u0000\u01a1\u01a2\u0005W\u0000\u0000\u01a2\u01b1\u0003"+
		"D\"\u0000\u01a3\u01a4\u0005W\u0000\u0000\u01a4\u01a5\u0003D\"\u0000\u01a5"+
		"\u01a6\u0005\u0001\u0000\u0000\u01a6\u01ab\u0003@ \u0000\u01a7\u01a8\u0005"+
		"\u0002\u0000\u0000\u01a8\u01aa\u0003@ \u0000\u01a9\u01a7\u0001\u0000\u0000"+
		"\u0000\u01aa\u01ad\u0001\u0000\u0000\u0000\u01ab\u01a9\u0001\u0000\u0000"+
		"\u0000\u01ab\u01ac\u0001\u0000\u0000\u0000\u01ac\u01ae\u0001\u0000\u0000"+
		"\u0000\u01ad\u01ab\u0001\u0000\u0000\u0000\u01ae\u01af\u0005\u0003\u0000"+
		"\u0000\u01af\u01b1\u0001\u0000\u0000\u0000\u01b0\u01a1\u0001\u0000\u0000"+
		"\u0000\u01b0\u01a3\u0001\u0000\u0000\u0000\u01b1?\u0001\u0000\u0000\u0000"+
		"\u01b2\u01b3\u0005Z\u0000\u0000\u01b3\u01b4\u0005\t\u0000\u0000\u01b4"+
		"\u01b7\u0003B!\u0000\u01b5\u01b7\u0003B!\u0000\u01b6\u01b2\u0001\u0000"+
		"\u0000\u0000\u01b6\u01b5\u0001\u0000\u0000\u0000\u01b7A\u0001\u0000\u0000"+
		"\u0000\u01b8\u01b9\u0007\u0001\u0000\u0000\u01b9C\u0001\u0000\u0000\u0000"+
		"\u01ba\u01bb\u0006\"\uffff\uffff\u0000\u01bb\u01bc\u0005Z\u0000\u0000"+
		"\u01bc\u01c2\u0001\u0000\u0000\u0000\u01bd\u01be\n\u0001\u0000\u0000\u01be"+
		"\u01bf\u0005Y\u0000\u0000\u01bf\u01c1\u0005Z\u0000\u0000\u01c0\u01bd\u0001"+
		"\u0000\u0000\u0000\u01c1\u01c4\u0001\u0000\u0000\u0000\u01c2\u01c0\u0001"+
		"\u0000\u0000\u0000\u01c2\u01c3\u0001\u0000\u0000\u0000\u01c3E\u0001\u0000"+
		"\u0000\u0000\u01c4\u01c2\u0001\u0000\u0000\u0000\u01c5\u01c6\u0006#\uffff"+
		"\uffff\u0000\u01c6\u01e6\u0005[\u0000\u0000\u01c7\u01e6\u0005+\u0000\u0000"+
		"\u01c8\u01e6\u0005Z\u0000\u0000\u01c9\u01ca\u0005\u0001\u0000\u0000\u01ca"+
		"\u01cb\u0003F#\u0000\u01cb\u01cc\u0005\u0003\u0000\u0000\u01cc\u01e6\u0001"+
		"\u0000\u0000\u0000\u01cd\u01ce\u0005?\u0000\u0000\u01ce\u01cf\u0003R)"+
		"\u0000\u01cf\u01d1\u0005\u0001\u0000\u0000\u01d0\u01d2\u0003H$\u0000\u01d1"+
		"\u01d0\u0001\u0000\u0000\u0000\u01d1\u01d2\u0001\u0000\u0000\u0000\u01d2"+
		"\u01d3\u0001\u0000\u0000\u0000\u01d3\u01d4\u0005\u0003\u0000\u0000\u01d4"+
		"\u01e6\u0001\u0000\u0000\u0000\u01d5\u01d6\u0005?\u0000\u0000\u01d6\u01db"+
		"\u0003R)\u0000\u01d7\u01d8\u0005\n\u0000\u0000\u01d8\u01d9\u0003F#\u0000"+
		"\u01d9\u01da\u0005\u000b\u0000\u0000\u01da\u01dc\u0001\u0000\u0000\u0000"+
		"\u01db\u01d7\u0001\u0000\u0000\u0000\u01dc\u01dd\u0001\u0000\u0000\u0000"+
		"\u01dd\u01db\u0001\u0000\u0000\u0000\u01dd\u01de\u0001\u0000\u0000\u0000"+
		"\u01de\u01e6\u0001\u0000\u0000\u0000\u01df\u01e0\u0007\u0002\u0000\u0000"+
		"\u01e0\u01e6\u0003F#\u0011\u01e1\u01e2\u0005\r\u0000\u0000\u01e2\u01e6"+
		"\u0003F#\u0010\u01e3\u01e4\u0005\u000e\u0000\u0000\u01e4\u01e6\u0003F"+
		"#\u000f\u01e5\u01c5\u0001\u0000\u0000\u0000\u01e5\u01c7\u0001\u0000\u0000"+
		"\u0000\u01e5\u01c8\u0001\u0000\u0000\u0000\u01e5\u01c9\u0001\u0000\u0000"+
		"\u0000\u01e5\u01cd\u0001\u0000\u0000\u0000\u01e5\u01d5\u0001\u0000\u0000"+
		"\u0000\u01e5\u01df\u0001\u0000\u0000\u0000\u01e5\u01e1\u0001\u0000\u0000"+
		"\u0000\u01e5\u01e3\u0001\u0000\u0000\u0000\u01e6\u022a\u0001\u0000\u0000"+
		"\u0000\u01e7\u01e8\n\u000e\u0000\u0000\u01e8\u01e9\u0007\u0003\u0000\u0000"+
		"\u01e9\u0229\u0003F#\u000f\u01ea\u01eb\n\r\u0000\u0000\u01eb\u01ec\u0005"+
		"\u0010\u0000\u0000\u01ec\u0229\u0003F#\u000e\u01ed\u01ee\n\f\u0000\u0000"+
		"\u01ee\u01ef\u0007\u0004\u0000\u0000\u01ef\u0229\u0003F#\r\u01f0\u01f1"+
		"\n\u000b\u0000\u0000\u01f1\u01f2\u0007\u0005\u0000\u0000\u01f2\u0229\u0003"+
		"F#\f\u01f3\u01f4\n\n\u0000\u0000\u01f4\u01f5\u0007\u0006\u0000\u0000\u01f5"+
		"\u0229\u0003F#\u000b\u01f6\u01f7\n\t\u0000\u0000\u01f7\u01f8\u0007\u0007"+
		"\u0000\u0000\u01f8\u0229\u0003F#\n\u01f9\u01fa\n\b\u0000\u0000\u01fa\u01fb"+
		"\u0005\u001a\u0000\u0000\u01fb\u0229\u0003F#\t\u01fc\u01fd\n\u0007\u0000"+
		"\u0000\u01fd\u01fe\u0005\u001b\u0000\u0000\u01fe\u0229\u0003F#\b\u01ff"+
		"\u0200\n\u0006\u0000\u0000\u0200\u0201\u0005\u001c\u0000\u0000\u0201\u0229"+
		"\u0003F#\u0007\u0202\u0203\n\u0005\u0000\u0000\u0203\u0204\u00050\u0000"+
		"\u0000\u0204\u0229\u0003F#\u0006\u0205\u0206\n\u0004\u0000\u0000\u0206"+
		"\u0207\u0005A\u0000\u0000\u0207\u0229\u0003F#\u0005\u0208\u020e\n\u0003"+
		"\u0000\u0000\u0209\u020a\u0005\u001d\u0000\u0000\u020a\u020b\u0003F#\u0000"+
		"\u020b\u020c\u0005\u001e\u0000\u0000\u020c\u020f\u0001\u0000\u0000\u0000"+
		"\u020d\u020f\u0005\u001f\u0000\u0000\u020e\u0209\u0001\u0000\u0000\u0000"+
		"\u020e\u020d\u0001\u0000\u0000\u0000\u020f\u0210\u0001\u0000\u0000\u0000"+
		"\u0210\u0229\u0003F#\u0004\u0211\u0212\n\u0002\u0000\u0000\u0212\u0213"+
		"\u0007\b\u0000\u0000\u0213\u0229\u0003F#\u0003\u0214\u0215\n\u0001\u0000"+
		"\u0000\u0215\u0216\u0005\t\u0000\u0000\u0216\u0229\u0003F#\u0002\u0217"+
		"\u0218\n\u0016\u0000\u0000\u0218\u0219\u0005Y\u0000\u0000\u0219\u0229"+
		"\u0005Z\u0000\u0000\u021a\u021c\n\u0013\u0000\u0000\u021b\u021d\u0003"+
		"\\.\u0000\u021c\u021b\u0001\u0000\u0000\u0000\u021c\u021d\u0001\u0000"+
		"\u0000\u0000\u021d\u021e\u0001\u0000\u0000\u0000\u021e\u0220\u0005\u0001"+
		"\u0000\u0000\u021f\u0221\u0003H$\u0000\u0220\u021f\u0001\u0000\u0000\u0000"+
		"\u0220\u0221\u0001\u0000\u0000\u0000\u0221\u0222\u0001\u0000\u0000\u0000"+
		"\u0222\u0229\u0005\u0003\u0000\u0000\u0223\u0224\n\u0012\u0000\u0000\u0224"+
		"\u0225\u0005\n\u0000\u0000\u0225\u0226\u0003F#\u0000\u0226\u0227\u0005"+
		"\u000b\u0000\u0000\u0227\u0229\u0001\u0000\u0000\u0000\u0228\u01e7\u0001"+
		"\u0000\u0000\u0000\u0228\u01ea\u0001\u0000\u0000\u0000\u0228\u01ed\u0001"+
		"\u0000\u0000\u0000\u0228\u01f0\u0001\u0000\u0000\u0000\u0228\u01f3\u0001"+
		"\u0000\u0000\u0000\u0228\u01f6\u0001\u0000\u0000\u0000\u0228\u01f9\u0001"+
		"\u0000\u0000\u0000\u0228\u01fc\u0001\u0000\u0000\u0000\u0228\u01ff\u0001"+
		"\u0000\u0000\u0000\u0228\u0202\u0001\u0000\u0000\u0000\u0228\u0205\u0001"+
		"\u0000\u0000\u0000\u0228\u0208\u0001\u0000\u0000\u0000\u0228\u0211\u0001"+
		"\u0000\u0000\u0000\u0228\u0214\u0001\u0000\u0000\u0000\u0228\u0217\u0001"+
		"\u0000\u0000\u0000\u0228\u021a\u0001\u0000\u0000\u0000\u0228\u0223\u0001"+
		"\u0000\u0000\u0000\u0229\u022c\u0001\u0000\u0000\u0000\u022a\u0228\u0001"+
		"\u0000\u0000\u0000\u022a\u022b\u0001\u0000\u0000\u0000\u022bG\u0001\u0000"+
		"\u0000\u0000\u022c\u022a\u0001\u0000\u0000\u0000\u022d\u0232\u0003F#\u0000"+
		"\u022e\u022f\u0005\u0002\u0000\u0000\u022f\u0231\u0003F#\u0000\u0230\u022e"+
		"\u0001\u0000\u0000\u0000\u0231\u0234\u0001\u0000\u0000\u0000\u0232\u0230"+
		"\u0001\u0000\u0000\u0000\u0232\u0233\u0001\u0000\u0000\u0000\u0233I\u0001"+
		"\u0000\u0000\u0000\u0234\u0232\u0001\u0000\u0000\u0000\u0235\u0236\u0005"+
		"6\u0000\u0000\u0236\u0237\u0003V+\u0000\u0237K\u0001\u0000\u0000\u0000"+
		"\u0238\u023d\u0003N\'\u0000\u0239\u023a\u0005\u0002\u0000\u0000\u023a"+
		"\u023c\u0003N\'\u0000\u023b\u0239\u0001\u0000\u0000\u0000\u023c\u023f"+
		"\u0001\u0000\u0000\u0000\u023d\u023b\u0001\u0000\u0000\u0000\u023d\u023e"+
		"\u0001\u0000\u0000\u0000\u023eM\u0001\u0000\u0000\u0000\u023f\u023d\u0001"+
		"\u0000\u0000\u0000\u0240\u0242\u0003<\u001e\u0000\u0241\u0240\u0001\u0000"+
		"\u0000\u0000\u0241\u0242\u0001\u0000\u0000\u0000\u0242\u0243\u0001\u0000"+
		"\u0000\u0000\u0243\u0244\u0003P(\u0000\u0244\u0246\u0005Z\u0000\u0000"+
		"\u0245\u0247\u0005X\u0000\u0000\u0246\u0245\u0001\u0000\u0000\u0000\u0246"+
		"\u0247\u0001\u0000\u0000\u0000\u0247\u024a\u0001\u0000\u0000\u0000\u0248"+
		"\u0249\u0005\t\u0000\u0000\u0249\u024b\u0003F#\u0000\u024a\u0248\u0001"+
		"\u0000\u0000\u0000\u024a\u024b\u0001\u0000\u0000\u0000\u024bO\u0001\u0000"+
		"\u0000\u0000\u024c\u024d\u0006(\uffff\uffff\u0000\u024d\u0251\u0003V+"+
		"\u0000\u024e\u0251\u0003T*\u0000\u024f\u0251\u0003^/\u0000\u0250\u024c"+
		"\u0001\u0000\u0000\u0000\u0250\u024e\u0001\u0000\u0000\u0000\u0250\u024f"+
		"\u0001\u0000\u0000\u0000\u0251\u025b\u0001\u0000\u0000\u0000\u0252\u0255"+
		"\n\u0001\u0000\u0000\u0253\u0254\u0005\n\u0000\u0000\u0254\u0256\u0005"+
		"\u000b\u0000\u0000\u0255\u0253\u0001\u0000\u0000\u0000\u0256\u0257\u0001"+
		"\u0000\u0000\u0000\u0257\u0255\u0001\u0000\u0000\u0000\u0257\u0258\u0001"+
		"\u0000\u0000\u0000\u0258\u025a\u0001\u0000\u0000\u0000\u0259\u0252\u0001"+
		"\u0000\u0000\u0000\u025a\u025d\u0001\u0000\u0000\u0000\u025b\u0259\u0001"+
		"\u0000\u0000\u0000\u025b\u025c\u0001\u0000\u0000\u0000\u025cQ\u0001\u0000"+
		"\u0000\u0000\u025d\u025b\u0001\u0000\u0000\u0000\u025e\u025f\u0006)\uffff"+
		"\uffff\u0000\u025f\u0262\u0003V+\u0000\u0260\u0262\u0003T*\u0000\u0261"+
		"\u025e\u0001\u0000\u0000\u0000\u0261\u0260\u0001\u0000\u0000\u0000\u0262"+
		"\u026c\u0001\u0000\u0000\u0000\u0263\u0266\n\u0001\u0000\u0000\u0264\u0265"+
		"\u0005\n\u0000\u0000\u0265\u0267\u0005\u000b\u0000\u0000\u0266\u0264\u0001"+
		"\u0000\u0000\u0000\u0267\u0268\u0001\u0000\u0000\u0000\u0268\u0266\u0001"+
		"\u0000\u0000\u0000\u0268\u0269\u0001\u0000\u0000\u0000\u0269\u026b\u0001"+
		"\u0000\u0000\u0000\u026a\u0263\u0001\u0000\u0000\u0000\u026b\u026e\u0001"+
		"\u0000\u0000\u0000\u026c\u026a\u0001\u0000\u0000\u0000\u026c\u026d\u0001"+
		"\u0000\u0000\u0000\u026dS\u0001\u0000\u0000\u0000\u026e\u026c\u0001\u0000"+
		"\u0000\u0000\u026f\u0270\u0007\t\u0000\u0000\u0270U\u0001\u0000\u0000"+
		"\u0000\u0271\u0273\u0003D\"\u0000\u0272\u0274\u0003\\.\u0000\u0273\u0272"+
		"\u0001\u0000\u0000\u0000\u0273\u0274\u0001\u0000\u0000\u0000\u0274W\u0001"+
		"\u0000\u0000\u0000\u0275\u0276\u0005\u0014\u0000\u0000\u0276\u027b\u0003"+
		"Z-\u0000\u0277\u0278\u0005\u0002\u0000\u0000\u0278\u027a\u0003Z-\u0000"+
		"\u0279\u0277\u0001\u0000\u0000\u0000\u027a\u027d\u0001\u0000\u0000\u0000"+
		"\u027b\u0279\u0001\u0000\u0000\u0000\u027b\u027c\u0001\u0000\u0000\u0000"+
		"\u027c\u027e\u0001\u0000\u0000\u0000\u027d\u027b\u0001\u0000\u0000\u0000"+
		"\u027e\u027f\u0005\u0017\u0000\u0000\u027fY\u0001\u0000\u0000\u0000\u0280"+
		"\u0285\u0005Z\u0000\u0000\u0281\u0282\u0005Z\u0000\u0000\u0282\u0283\u0005"+
		"=\u0000\u0000\u0283\u0285\u0003P(\u0000\u0284\u0280\u0001\u0000\u0000"+
		"\u0000\u0284\u0281\u0001\u0000\u0000\u0000\u0285[\u0001\u0000\u0000\u0000"+
		"\u0286\u0287\u0005\u0014\u0000\u0000\u0287\u0294\u0005\u0017\u0000\u0000"+
		"\u0288\u0289\u0005\u0014\u0000\u0000\u0289\u028e\u0003P(\u0000\u028a\u028b"+
		"\u0005\u0002\u0000\u0000\u028b\u028d\u0003P(\u0000\u028c\u028a\u0001\u0000"+
		"\u0000\u0000\u028d\u0290\u0001\u0000\u0000\u0000\u028e\u028c\u0001\u0000"+
		"\u0000\u0000\u028e\u028f\u0001\u0000\u0000\u0000\u028f\u0291\u0001\u0000"+
		"\u0000\u0000\u0290\u028e\u0001\u0000\u0000\u0000\u0291\u0292\u0005\u0017"+
		"\u0000\u0000\u0292\u0294\u0001\u0000\u0000\u0000\u0293\u0286\u0001\u0000"+
		"\u0000\u0000\u0293\u0288\u0001\u0000\u0000\u0000\u0294]\u0001\u0000\u0000"+
		"\u0000\u0295\u0297\u0005V\u0000\u0000\u0296\u0298\u0003\\.\u0000\u0297"+
		"\u0296\u0001\u0000\u0000\u0000\u0297\u0298\u0001\u0000\u0000\u0000\u0298"+
		"_\u0001\u0000\u0000\u0000\u0299\u029e\u0003P(\u0000\u029a\u029b\u0005"+
		"\u0002\u0000\u0000\u029b\u029d\u0003P(\u0000\u029c\u029a\u0001\u0000\u0000"+
		"\u0000\u029d\u02a0\u0001\u0000\u0000\u0000\u029e\u029c\u0001\u0000\u0000"+
		"\u0000\u029e\u029f\u0001\u0000\u0000\u0000\u029fa\u0001\u0000\u0000\u0000"+
		"\u02a0\u029e\u0001\u0000\u0000\u0000Tflt~\u0089\u008d\u0093\u0098\u009a"+
		"\u009f\u00a3\u00a7\u00ac\u00ae\u00b1\u00b5\u00b8\u00bd\u00c1\u00c7\u00cd"+
		"\u00d3\u00d8\u00dc\u00e9\u00ee\u00f3\u00f7\u00fb\u0101\u0106\u0108\u010b"+
		"\u010f\u0113\u0118\u011a\u011c\u011f\u0127\u0132\u0140\u0146\u0152\u015f"+
		"\u0167\u0171\u0175\u017c\u0186\u0190\u0196\u019a\u019f\u01ab\u01b0\u01b6"+
		"\u01c2\u01d1\u01dd\u01e5\u020e\u021c\u0220\u0228\u022a\u0232\u023d\u0241"+
		"\u0246\u024a\u0250\u0257\u025b\u0261\u0268\u026c\u0273\u027b\u0284\u028e"+
		"\u0293\u0297\u029e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}