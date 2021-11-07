public class ChessBoard {
    /*
     * 这个棋盘是15路的，因为文本难以打印出交点上的棋子，
     * 所以将以横15纵15的方格形式输出
     */

    public char[][] chessBoard=new char[15][15];

    public static int WHITE=1;
    public static int BLACK=0;

    public ChessBoard(){
        for(int i=0;i<15;i++)
            for(int j=0;j<15;j++)
                chessBoard[i][j]='.';
    }

    public void printBoard(){
        char[] a={'1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        System.out.print("  ");
        for(int i=0;i<15;i++)
            System.out.print(a[i]+" "); //输出列号
        System.out.print("\n");

        for(int i=0;i<15;i++){
            System.out.print(a[i]+" "); //输出行号
            for(int j=0;j<15;j++)
                System.out.print(chessBoard[i][j]+" ");
            System.out.print("\n");
        }
    }

    public void putChess(int x, int y, int color){
        //0: black
        //1: white
        char chess=(color==WHITE)?'O':'X';
        chessBoard[x-1][y-1]=chess;

        if(appearWinner(x,y,chess))
            success(color);
    }

    public boolean occupied(int x,int y){
        return (chessBoard[x-1][y-1]!='.');
    }

    public boolean appearWinner(int x,int y,char chess){
        /*
         * para--------------------------
         * x,y: 当前棋子的坐标
         * color: 当前执子方的颜色，值可能是 'X'||'O'
         *
         * return------------------------
         * 是否在任意一个方向上连成五子
         */

        //只要这次落子引起了任何一个方向上的五子连珠的形成，就直接这一方success
        return checkLR(x,y,chess)
                ||checkUD(x,y,chess)
                ||checkLURD(x,y,chess)
                ||checkRULD(x,y,chess);
    }

    public boolean checkLR(int x,int y,char color){
        // -
        //连起来一共有多长，1是当前的落子
        int sum=1;
        //从当前棋子出发向左
        for(int i=1;i<5;i++){
            int xx=x-1 - i,yy=y-1;
            //直到这个位置的棋子和当前落子颜色不一样停止。
            if((xx<0)||(xx>14)||yy<0||yy>14)
                break;
            if (chessBoard[xx][yy] != color)
                break;
            sum++;
        }
        //从当前棋子出发向右
        for(int i=1;i<5;i++){
            int xx=x-1 + i,yy=y-1;
            if((xx<0)||(xx>14)||yy<0||yy>14)
                break;
            if (chessBoard[xx][yy] != color)
                break;
            sum++;
        }
        return sum == 5;
    }
    public boolean checkUD(int x,int y,char color){
        // |
        int sum=1;
        for(int i=1;i<5;i++){
            int xx=x-1 ,yy=y-1-i;
            if((xx<0)||(xx>14)||yy<0||yy>14)
                break;
            if (chessBoard[xx][yy] != color)
                break;
            sum++;
        }
        for(int i=1;i<5;i++){
            int xx=x-1 ,yy=y-1+i;
            if((xx<0)||(xx>14)||yy<0||yy>14)
                break;
            if (chessBoard[xx][yy] != color)
                break;
            sum++;
        }
        return sum == 5;
    }
    public boolean checkLURD(int x,int y,char color){
        // \
        int sum=1;
        for(int i=1;i<5;i++){
            int xx=x-1-i ,yy=y-1+i;
            if((xx<0)||(xx>14)||yy<0||yy>14)
                break;
            if (chessBoard[xx][yy] != color)
                break;
            sum++;
        }
        for(int i=1;i<5;i++){
            int xx=x-1+i ,yy=y-1-i;
            if((xx<0)||(xx>14)||yy<0||yy>14)
                break;
            if (chessBoard[xx][yy] != color)
                break;
            sum++;
        }
        return sum == 5;
    }
    public boolean checkRULD(int x,int y,char color){
        // /
        int sum=1;
        for(int i=1;i<5;i++){
            int xx=x-1+i ,yy=y-1+i;
            if((xx<0)||(xx>14)||yy<0||yy>14)
                break;
            if (chessBoard[xx][yy] != color)
                break;
            sum++;
        }
        for(int i=1;i<5;i++){
            int xx=x-1-i ,yy=y-1-i;
            if((xx<0)||(xx>14)||yy<0||yy>14)
                break;
            if (chessBoard[xx][yy] != color)
                break;
            sum++;
        }
        return sum == 5;
    }

    public void success(int color){
        //某方成功之后输出成功的信息并且退出程序
        String tmp=(color==WHITE)?"WHITE wins!":"BLACK wins!";
        printBoard();
        System.out.println(tmp);
        System.exit(0);
    }

}