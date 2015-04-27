/******************************************************/
/* Program Name:    BingoCard */
/* Name:            Tagashira Kaori */
/* Date yyyy/mm/dd: 2015/04/15 */
/* Description:     ビンゴカードを出力する部分 */
/* Reuse Instruction:*/
/*       */
/*        Purpose: */
/*        Return: */
/******************************************************/
package AutoBingo;

import java.util.Random;
/**
 *
 * @author kaori
 */
public class BingoCard 
{
    /*指定された枚数だけビンゴカードを作成する */
    public static int[][][] bingoCardsCreator(int playerNumber)
    {
        int[][][] bingoCards = new int[playerNumber][5][5];
        int bingoNumber;
        
        for(int player=0; player<playerNumber; player++)
        {       
            for(int column=0; column<5; column++)
            {
                // 1行目は重複チェック無しで配置
                bingoCards[player][column][0] = randGenerator() % 15 + 15 * column + 1;
                
                for(int row=1; row<5; row++)
                {
                    bingoNumber = randGenerator() % 15 + 15 * column + 1;
                    int flag = 0;
                    // すでにカードに配置されている数字と重複していないかを確認する
                    for(int target=0; target<row; target++)
                    {
                        if(bingoNumber == bingoCards[player][column][target])
                        {
                            flag = 1;
                        }
                    }
                    if(flag == 1)
                    {
                        row--;
                    }else{
                        bingoCards[player][column][row] = bingoNumber;
                    }
                }
            }
            bingoCards[player][2][2] = 0;
            // カードの内容が重複しているかどうかをチェックる
            // 今まで作成したカードと重複していればそのプレイヤーのカードを作り直す
            if(player!=0){
                player = overlapChecker(bingoCards, player);
            }
        }       
        return bingoCards;
    }
    
    /* ビンゴカードを一枚作成する */
    public static int[][] bingoCardCreator()
    {
        int bingoCard[][] = new int[5][5];
        int bingoNumber;

        for (int column = 0; column < 5; column++) {
            // 1行目は重複チェック無しで配置
            bingoCard[column][0] = randGenerator() % 15 + 15 * column + 1;

            for (int row = 1; row < 5; row++) {
                bingoNumber = randGenerator() % 15 + 15 * column + 1;
                int flag = 0;
                // すでにカードに配置されている数字と重複していないかを確認する
                for (int target = 0; target < row; target++) {
                    if (bingoNumber == bingoCard[column][target]) {
                        flag = 1;
                    }
                }
                if (flag == 1) {
                    row--;
                } else {
                    bingoCard[column][row] = bingoNumber;
                  }
            }
        }
        
        bingoCard[2][2] = 0;

        return bingoCard;
    }
    
    /* 乱数を発生する */
    public static int randGenerator()
    {
        Random rnd = new Random();
        int ran = rnd.nextInt(1000);
        return ran;
    }
    
    /* ビンゴカードを表す5*5の配列を受け取り、ビンゴカードの見た目で出力する */
    public static void bingoCardPrinter (int[][] bingoCard)
    {
        System.out.println("\u001b[00;31m B|  I|  N|  G|  O\u001b[00m");
        int bingoNumber;
        String lineNumbers;
        for(int column=0; column<bingoCard.length; column++)
        {
            for(int row=0; row<bingoCard[column].length; row++)
            {
                bingoNumber = bingoCard[row][column];
                lineNumbers = "";
                // 数字を右揃えにする
                if(bingoNumber < 10)
                {
                    // 中央値はスペースを表示する
                    if(bingoNumber==0)
                    {
                        lineNumbers += "  ";
                    }else{
                        lineNumbers += " " + bingoNumber;
                    }
                }else{
                    lineNumbers += bingoNumber;
                }
                // 各列をパイプ（｜）で区切る
                if(row!=4)
                {
                    lineNumbers += "| ";
                }
            }
            System.out.println();
        }
    }
    
    /* 複数のビンゴカードを垂直に並べて出力する */
    public static void bingoCardsPrinter_vertical(int[][][] bingoCards)
    {
        for(int player=0; player<bingoCards.length; player++)
        {
            System.out.println("Player " + (player+1) + " : ");
            bingoCardPrinter(bingoCards[player]);
        }
    }
    
    /* 複数のビンゴカードを水平（横）に並べて出力する */
    public static void bingoCardPrinter_horizontal(int[][][] bingoCards)
    {
        // 一行ごとの出力        
        String lineNumbers;
        
        // プレイヤーを出力する
        // BINGOの行を出力する
        for(int player=0; player<bingoCards.length; player++)
        {
            System.out.print("   ～Player " + (player + 1) + "～      ");
        }
        System.out.print("\n");
        
        // BINGOの行を出力する
        for(int player=0; player<bingoCards.length; player++)
        {
            System.out.print("\u001b[00;31m B|  I|  N|  G|  O   \u001b[00m");
        }
        System.out.print("\n");
        
        for(int column=0; column<bingoCards[0][0].length; column++)
        {
            lineNumbers = "";
            for(int player=0; player<bingoCards.length; player++)
            {
                for(int row=0; row<bingoCards[0].length; row++)
                {
                    int bingoNumber = bingoCards[player][row][column];
                    // 数字を右揃えにする
                    if(bingoNumber < 10)
                    {
                        // 中央値，空いた文字（0）はスペースを表示する
                        if(bingoNumber==0)
                        {
                            lineNumbers += "  ";
                        }else{
                            lineNumbers += " " + bingoNumber;
                        }
                    }else{
                        lineNumbers += bingoNumber;
                    }
                    // 各列をパイプ（｜）で区切る
                    if(row==4)
                    {
                        lineNumbers += "   ";
                    }else{
                        lineNumbers += "| ";
                    }
                }
            }
            System.out.println(lineNumbers);
        }
    }
    
    
    /* ビンゴカードが重複していないかをチェックする */
    public static int overlapChecker(int[][][] bingoCards, int player)
    {
        int renewPlayer = player;
        int flag = 0;
        
        // すでに完成したすべてのカードと比較する
        // この重複チェック機能をテストする時は，index<player+1 にすると良い
        for(int index=0; index<player; index++)
        {
            flag = 0;
            for(int column=0; column<5; column++)
            {
                for(int row=0; row<5; row++)
                {
                    // 中央値は必ず0で重複するので、チェックしない
                    if(column==2 && row==2)
                    {
                        break;
                    }else if(bingoCards[player][column][row] != bingoCards[index][column][row])
                    {
                        flag = 1;
                    }
                }
            }
            // 重複していない場所が無ければ（＝全て重複していれば）、そのプレイヤーのカードを作り直す
            if(flag==0)
            {
                renewPlayer = player - 1;
                System.out.println("renew");
                //System.exit(0);
            }
        }      
        
        return renewPlayer;
    }
}