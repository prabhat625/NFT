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
            ;
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
        //System.out.println("Enter random number");
        sc.next();
        System.out.println("Enter the size of input  ");
        int n=sc.ni();
        System.out.println("Enter the number of predefine pattern");
        int h=sc.ni();
        int[][] weight = new int[n][n];
        int[][] pattern =  new int[h][n];
        
        for(int i=0;i<h;i++){
          System.out.println(i+1+" pattern ?");
          char[] arr=sc.next().toCharArray();
          for(int j=0;j<n;j++){
            if(arr[j]=='0')
            pattern[i][j]=0;
            else
            pattern[i][j]=1;
          }
        }
        for(int i=0;i<h;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    if(j==k)
                        weight[j][k]=0;
                    else
                        weight[j][k]+=((2*pattern[i][j]-1)*(2*pattern[i][k]-1));
                }
            }
        }
        System.out.println("Weight matrix is:-- ");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
                System.out.print(weight[i][j]+" ");
            System.out.println();
        }

        System.out.println("Enter the number of input present for pattern matching");
        int m=sc.ni();
        int[][] input=new int[m][n];
        int[][] output=new int[m][n];
        for(int i=0;i<m;i++){
            System.out.println(i+1+" pattern ?");
            char[] arr=sc.next().toCharArray();
            for(int j=0;j<n;j++){
              if(arr[j]=='0')
              input[i][j]=0;
              else
              input[i][j]=1;
              output[i][j]=input[i][j];
            }
        }
        
        System.out.println("Enter threshold");
        int thres = sc.ni();

        for(int t=0;t<m;t++){
            System.out.println(t+1+ " inpupt Evaluating\n Enter the random order of number from 1 to "+n);
            int[] order=new int[n];
            for(int i=0;i<n;i++)
            order[i]=sc.ni()-1;

            System.out.println("Enter the number of epochs?");
            int epoch = sc.ni();

            for(int e=0;e<epoch;e++){
                System.out.println(e+1+" Epoch:-");
                for(int o:order){
                    int w=input[t][o];
                    for(int i=0;i<n;i++)
                        w+=(weight[i][o]*output[t][i]);
                    System.out.println("Wighted sum: "+w);
                    if(w > thres)
                    output[t][o]=1;
                    else
                    output[t][o]=0;
                    t++;o++;
                    System.out.print("o"+t+o+" :- ");
                    t--;o--;
                    for(int i=0;i<n;i++){
                        System.out.print(output[t][i]);
                    }
                    System.out.println("\n");
                }
            }
        }
    }
}
