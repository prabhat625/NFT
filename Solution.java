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
     //lr();
     kmeans();
    }
    // Linear regression
    public void lr(){
        int n=sc.ni();
        double[] arr=new double[n];
        double[] brr=new double[n];
        double s=0,t=0,yt=0,t2=0;
        for(int i=0;i<n;i++){
            System.out.println("Enter value and time");
            arr[i]=sc.nd();
            brr[i]=sc.nd();
            s+=arr[i];
            t2=t2+brr[i]*brr[i];
            t+=brr[i];
            yt+=arr[i]*brr[i];
        }
        double tt=n;
        double a=(t2*s-yt*t)/(t2*tt-t*t);
        double b=(s-tt*a)/t;
        System.out.println(a+" "+b);
    }
    public boolean euclid=true;
    public void kmeans(){
        System.out.println("Enter 1 for euclid distance");
        int n=sc.ni();
        if(n!=1)
        euclid=false;
        System.out.println("Enter number of points");
        n=sc.ni();
        System.out.println("Enter the value of k");
        int k=sc.ni();
        double[][] points=new double[n][2];
        int[] center=new int[n];
        Arrays.fill(center,-1);
        double[][] crr=new double[k][2];
        for(int i=0;i<n;i++){
            points[i][0]=sc.nd();
            points[i][1]=sc.nd();
            if(i<k){
                crr[i][0]=points[i][0];
                crr[i][1]=points[i][1];
            }
        }
        boolean change=true;
        while(change){
            change = false;
            System.out.print("Centers are: ");
            for(double[] arr:crr){
                System.out.print("( "+arr[0]+" , "+arr[1]+" )  ");
            }
            System.out.println("");
            for(int i=0;i<n;i++){
                double min=Double.MAX_VALUE;
                int ind=-1;
                for(int j=0;j<k;j++){
                    double d=dist(points[i],crr[j]);
                    System.out.print(d+"  ");
                    if(d<min){
                        min=d;
                        ind=j;
                    }
                }
                if(ind!=center[i]){
                    change=true;
                    center[i]=ind;
                }
                System.out.println("  minCenter is: "+center[i]);
            }
            ArrayList<double[]>[] list=new ArrayList[k];
            for(int i=0;i<k;i++)
            list[i]=new ArrayList();
            for(int i=0;i<n;i++)
                list[center[i]].add(points[i]);
            for(int i=0;i<k;i++){
                double x=0,y=0;
                for(double[] arr:list[i]){
                    x+=arr[0];
                    y+=arr[1];
                }
                y/=Double.valueOf(list[i].size());
                x/=Double.valueOf(list[i].size());
                crr[i][0]=x;
                crr[i][1]=y;
            }
            System.out.println("--------------");
        }
    }
    public double dist(double[] arr,double[] brr){
        if(euclid){
            return Math.sqrt(Math.pow(arr[0]-brr[0],2)+Math.pow(arr[1]-brr[1],2));
        }
        else{ 
            return Math.abs(arr[0]-brr[0])+Math.abs(arr[1]-brr[1]);
        }
    }
}