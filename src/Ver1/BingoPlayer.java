/******************************************************/
/* Program Name:    BingoPlayer */
/* Name:            Tagashira Kaori */
/* Date yyyy/mm/dd: 2015/04/20 */
/* Description:     ビンゴゲームでカードを確認するプレイヤー */
/* Reuse Instruction:*/
/*       */
/*        Purpose: */
/*        Return: */
/******************************************************/
package Ver1;

/**
 *
 * @author kaori
 */
public class BingoPlayer {

    // ビンゴカード
    public static int [][][] bingoCards;
    // ビンゴの合図（ビンゴなし：0，ビンゴあり：1）
    public static int bingoSign = 0;
    
    static void setBingoCards(int [][][] bingo_cards) {
        bingoCards = bingo_cards;
    }

    // カードにビンゴマシーンの数字があるかどうかを調べる
    static int checkNumber(int calledNumber) {
        int line = -1;
        for(int player=0; player<bingoCards.length; player++)
        {
            if(calledNumber < 16)
            {
                line = 0;
            }else if(calledNumber < 31)
            {
                line = 1;
            }else if(calledNumber < 46)
            {
                line = 2;
            }else if(calledNumber < 61)
            {
                line = 3;
            }else{
                line = 4;
            }
            checkLine(player, line, calledNumber);
        }
        return bingoSign;
    }
    
    // カードの特定の列にビンゴマシーンの数字があるかどうかを調べる
    private static void checkLine(int player, int line, int calledNumber) 
    {
        for(int row=0; row<5; row++)
        {
            if(bingoCards[player][line][row] == calledNumber)
            {
                bingoCards[player][line][row] = 0;
                checkBingo(player);
            }
        }
    }

    // プレイヤーが一人ずつ、カードがビンゴになっているかどうかを確認する
    private static void checkBingo(int player) 
    {
        // 行を調べる bingoLine(player);
        // 列を調べる bingoRow(player);
        // 斜めを調べる bingoCross();
        if( (bingoLine(player) + bingoRow(player) + bingoCross(player)) > 0 )
        {
            bingoSign = 1;
        }
    }
    
    
    // ゲーム管理者が参加者のカードを全て確認し，がビンゴになったのかを確認する用
    // 別クラス
    public static void checkBingo(int[][][] bingoCards)
    {
        
    }

    // 列でビンゴになっていないかを調べ，ビンゴになった列の数を返す
    private static int bingoLine(int player)
    {
        int bingoLines = 0;
        int lineSum = 0;
        for(int line=0; line<5; line++)
        { 
            lineSum = 0;
            for(int row=0; row<5; row++)
            {
                lineSum += bingoCards[player][line][row];
            }
            if(lineSum == 0)
            {
                bingoLines++;
            }
        }
        
        return bingoLines;
    }

    // 行でビンゴになっていないかを調べ，ビンゴになった行の数を返す
    private static int bingoRow(int player)
    {
        int bingoRows = 0;
        int rowSum = 0;
        for(int row=0; row<5; row++)
        { 
            rowSum = 0;
            for(int line=0; line<5; line++)
            {
                rowSum += bingoCards[player][line][row];
            }
            if(rowSum == 0)
            {
                bingoRows++;
            }
        }
        
        return bingoRows;
    }
    
    // 斜めにビンゴになっていないかを調べ，ビンゴになった斜めの数を返す
    private static int bingoCross(int player) {
        int bingoCross = 0;
        int rightDownSum = 0;
        int rightUpSum = 0;

        for(int index=0; index<5; index++)
        {
            // 右下がり斜めの合計をとる
            rightDownSum += bingoCards[player][index][index];
            // 右上がり斜めの合計をとる
            rightUpSum += bingoCards[player][index][4-index];
        }
        
        if( (rightDownSum == 0) && (rightUpSum ==0) )
        {
            bingoCross = 2;
        }else if ( (rightDownSum == 0) || (rightUpSum ==0) )
        {
            bingoCross = 1;
        }     
        
        return bingoCross;
    }
}
