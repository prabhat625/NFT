import java.util.*;
import java.io.*;
import java.text.*;
class Solution implements Runnable {
    FastScanner sc;
    PrintWriter pw;
    
    final class FastScanner {
        BufferedReader br;
        StringTokenizer st;
 
        public FastScanner() {
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
        public long nlo() {
            return Long.parseLong(next());
        }
 
        public String next() {
            if (st.hasMoreTokens()) return st.nextToken();
            try {
                st = new StringTokenizer(br.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }
 
        public int ni() {
            return Integer.parseInt(next());
        }
 
        public String nli() {
            String line = "";
            if (st.hasMoreTokens()) line = st.nextToken();
            else try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (st.hasMoreTokens()) line += " " + st.nextToken();
            return line;
        }
 
        public double nd() {
            return Double.parseDouble(next());
        }
    }
    public static void main(String[] args) throws Exception
    {
        new Thread(null,new Solution(),"codeforces",1<<28).start();
    }
    public void run()
    {
        sc=new FastScanner();
        pw=new PrintWriter(System.out);
        try{
            solve();
        }
        catch(Exception e)
        {
            pw.println(e);
        }
        pw.flush();
        pw.close();
    }
    public long gcd(long a,long b)
    {
        return b==0L?a:gcd(b,a%b);
    }
    public long ppow(long a,long b,long mod)
    {
        if(b==0L)
        return 1L;
        long tmp=1;
        while(b>1L)
        {
            if((b&1L)==1L)
            tmp*=a;
            a*=a;
            a%=mod;
            tmp%=mod;
            b>>=1;
        }
        return (tmp*a)%mod;
    }
    public long pow(long a,long b)
    {
        if(b==0L)
        return 1L;
        long tmp=1;
        while(b>1L)
        {
            if((b&1L)==1)
            tmp*=a;
            a*=a;
            b>>=1;
        }
        return (tmp*a);
    }
    public  int gcd(int x,int y)
    {
        return y==0?x:gcd(y,x%y);
    }
   
    //////////////////////////////////
    /////////////  LOGIC  ///////////
    ////////////////////////////////
    public void solve() throws Exception{
        sc.next();
       // Madaline();
        perceptron();
    }

    public void perceptron(){
        double[][] input=new double[4][3];
        input[0][0]=0;input[0][1]=0;
        input[1][0]=0;input[1][1]=1;
        input[2][0]=1;input[2][1]=0;
        input[3][0]=1;input[3][1]=1;
        for(int i=0;i<4;i++){
            System.out.println("Desired Output for "+input[i][0]+" "+input[i][1]);
            input[i][2]=sc.nd();
        }
        DecimalFormat df2 = new DecimalFormat("#.##");
        System.out.println("Learning Rate ?");
        double r=sc.nd();
        System.out.println("w0  w1  w2");
        double w0=sc.nd(),w1=sc.nd(),w2=sc.nd();
        boolean change = true;
        System.out.println("x1   x2   Desired    sum    output   diff   w0   w1   w2");
        while(change){
            change = false;
            for(int i=0;i<4;i++){
                double sum=w0+w1*input[i][0]+w2*input[i][1];
                double output = sum > 0 ? 1:0;
                double diff = input[i][2]-output;
                if(diff!=0d){
                    change = true;
                    w0+=(r*diff);
                    w1+=(diff*r*input[i][0]);
                    w2+=(diff*r*input[i][1]);
                }
                System.out.println(df2.format(input[i][0])+"   "+df2.format(input[i][1])+"   "+df2.format(input[i][2])+"   "+df2.format(sum)+"   "+df2.format(output)+"   "+df2.format(diff)+"   "+df2.format(w0)+"   "+df2.format(w1)+"   "+df2.format(w2));
            }
            System.out.println(" ------------------ ");
        }
    }

    public void Madaline() {
    DecimalFormat df2 = new DecimalFormat("#.##");
    double[][] input = new double[4][3];
    input[0][0]=1d;input[0][1]=1d;
    input[1][0]=1d;input[1][1]=-1d;
    input[2][0]=-1d;input[2][1]=1d;
    input[3][0]=-1d;input[3][1]=-1d;
    for(int i=0;i<4;i++){
        System.out.println("Desired Output for: "+input[i][0]+" "+input[i][1]);
        input[i][2]=sc.nd(); 
    }
    double r,w11,w12,w21,w22,b1,b2,b3,z1,z2;
    System.out.println("\n Provide Details \n");
    System.out.println("learning rate");
    r=sc.nd();
    System.out.println("w11");
    w11=sc.nd();
    System.out.println("w12");
    w12=sc.nd();
    System.out.println("w21");
    w21=sc.nd();
    System.out.println("w22");
    w22=sc.nd();
    System.out.println("b1");
    b1=sc.nd();
    System.out.println("b2");
    b2=sc.nd();
    System.out.println("b3");
    b3=sc.nd();
    System.out.println("z1");
    z1=sc.nd();
    System.out.println("z2");
    z2=sc.nd();

    System.out.println("Number of iterations ?");
    int n=sc.ni();
    System.out.println("I1   I2   sum1   sum2   h1   h2   sum   Output   Desired   w11   w12   w21   w22   b1   b2\n");
    while(n-->0){
        for(int i=0;i<4;i++){
            double sum1=b1+input[i][0]*w11+input[i][1]*w21;
            double sum2=b2+input[i][0]*w12+input[i][1]*w22;
            double h1=sum1>=0d?1d:-1d;
            double h2=sum2>=0d?1d:-1d;
            double sum=b3+h1*z1+h2*z2;
            double output= sum>=0?1d:-1d;
            if(output!=input[i][2]){
                if(input[i][2]==1d){
                    if(Math.abs(sum1)<=Math.abs(sum2)){
                        w11=w11+r*(1d-sum1)*input[i][0];
                        w21=w21+r*(1d-sum1)*input[i][1];
                        b1=b1+r*(1d-sum1);
                    }
                    if(Math.abs(sum2)<=Math.abs(sum1)){
                        w12=w12+r*(1d-sum2)*input[i][0];
                        w22=w22+r*(1d-sum2)*input[i][1];
                        b2=b2+r*(1d-sum2);
                    }
                }
                else{
                    if(sum1>0d){
                        w11=w11+r*(-1d-sum1)*input[i][0];
                        w21=w21+r*(-1d-sum1)*input[i][1];
                        b1=b1+r*(-1d-sum1);
                    }
                    if(sum2>0d){
                        w12=w12+r*(-1d-sum2)*input[i][0];
                        w22=w22+r*(-1d-sum2)*input[i][1];
                        b2=b2+r*(-1d-sum2);
                    }
                }
            }
            System.out.println(df2.format(input[i][0])+"   "+df2.format(input[i][1])+"   "+df2.format(sum1)+"   "+df2.format(sum2)+"   "+df2.format(h1)+"   "+df2.format(h2)+"   "+df2.format(sum)+"   "+df2.format(output)+"   "+df2.format(input[i][2])+"   "+df2.format(w11)+"   "+df2.format(w12)+"   "+df2.format(w21)+"   "+df2.format(w22)+"   "+df2.format(b1)+"   "+df2.format(b2));
        }
        System.out.println("\n  ------------ \n");
    }
    }
}
/*
maradine input
dasdsa
-1 1 1 -1 0.7 0.1 0.2 0.3 0.4 0.5 0.5 0.5 0.5 0.6 1
*/