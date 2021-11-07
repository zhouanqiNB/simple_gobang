import java.util.Scanner;

public class Player {

    int color;
    public static int WHITE=1;
    public static int BLACK=0;

    public Player(int c){
        color=c;
    }
    public void play(ChessBoard cb){
        String tmp=(color==WHITE)?"P2's turn, you control white.":"P1's turn, you control black.";
        System.out.println(tmp);
        System.out.println("Input the position you want to place your chess, e.g. \"1 2\":");

        Scanner sc=new Scanner(System.in);
        String str=null;
        str=sc.nextLine();
        char x1 =  str.charAt(0);
        char y1 =  str.charAt(2);
        //从输入获取真实坐标
        int x=chToInt(x1);
        int y=chToInt(y1);

        //把输入限制在(1~F)之间，而且输入的坐标不可以是被占用的，如果不对就接着输入。
        while((!((x1>'0'&&x1<='9'||x1>='A'&&x1<='F')&&(y1>'0'&&y1<='9'||y1>='A'&&y1<='F')))
        ||cb.occupied(x,y)){
            System.out.println("please give right input!");
            str=sc.nextLine();
            x1 =  str.charAt(0);
            y1 =  str.charAt(2);
            x=chToInt(x1);
            y=chToInt(y1);
        }

        cb.putChess(x,y,color);
    }

    public static void main(String[] args){
        Player p1=new Player(BLACK);
        Player p2=new Player(WHITE);
        ChessBoard cb=new ChessBoard();
        cb.printBoard();

        //黑子先行，轮流下棋。
        //15*15的棋盘，最多下225个棋子，先下一个黑子，然后112回合
        p1.play(cb);
        cb.printBoard();
        for(int i=0;i<112;i++){
            p2.play(cb);
            cb.printBoard();
            p1.play(cb);
            cb.printBoard();
        }

        //112回合过后如果还没有胜利者，输出平局（否则会在中间就退出程序）
        System.out.println("The result of this game is a draw.");
    }
    public static int chToInt(char ch){
        if(ch>='1'&&ch<='9')
            return ch-'0';
        else
            return ch-55;
    }
}