/* Generated By:JavaCC: Do not edit this line. PS2Tokenizer.java */
import java.io.*;

public class PS2Tokenizer implements PS2TokenizerConstants {

        public static void main(String[] args) throws ParseException, IOException
        {
                if(args.length != 2)
                {
                        System.out.println("ERROR: Invalid arguments specified\u005cn");
                        System.out.println("Please run this program with the following syntax: java PS2Tokenizer.java <path of input dir> <path of output dir>");
                        System.exit(1);
                }
                else
                {
                        File indir = new File(args[0]);
                        File outdir = new File(args[1]);

                        if(!outdir.exists())
                                outdir.mkdir();

                        if(!indir.exists())
                                indir.mkdir();

                        File[] files = indir.listFiles();

                        for(File file : files)
                                tokenize(file, outdir);
                }
        }

        private static void tokenize(File input, File outdir) throws ParseException, IOException
        {
                BufferedReader br = new BufferedReader(new FileReader(input));
                PS2Tokenizer u = new PS2Tokenizer(br);
                Token t;

                t = u.getNextToken();

                String outpath = outdir.getName();
                if(outpath.charAt(outpath.length() - 1) != '/')
                        outpath = outpath + "/";

                String outputFileName = outpath + input.getName() + ".out";
                File output = new File(outputFileName);

                if(output.exists())
                        output.delete();

                output.createNewFile();
                FileWriter fw = new FileWriter(output);
                BufferedWriter bw = new BufferedWriter(fw);

                while ( t.kind != PS2TokenizerConstants.EOF )
                {
                        if(PS2Tokenizer.tokenImage[ t.kind ].equalsIgnoreCase("<WORD>"))
                                bw.write(t.image + "\u005cn");

                        String token = t.image;
                        if(token.equals("\u005cn"))
                                token = "newline";
                        if(token.equals("\u005cr"))
                                token = "carraige return";
                        if(token.equals("\u005ct"))
                                token = "tab";

                        System.out.printf("Token:  %15s    Type:  %15s  %n" , token, PS2Tokenizer.tokenImage[ t.kind ]  );
                        t = u.getNextToken();
                }

                br.close();
                bw.close();
        }

  /** Generated Token Manager. */
  public PS2TokenizerTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[0];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {};
   }

  /** Constructor with InputStream. */
  public PS2Tokenizer(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public PS2Tokenizer(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new PS2TokenizerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public PS2Tokenizer(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new PS2TokenizerTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public PS2Tokenizer(PS2TokenizerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(PS2TokenizerTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 0; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[39];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 0; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 39; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}