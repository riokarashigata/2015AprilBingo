/******************************************************/
/* Program Name:    GameMaster */
/* Name:            Tagashira Kaori */
/* Date yyyy/mm/dd: 2015/04/20 */
/* Description:     ビンゴゲームを管理する */
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
public class BingoGameMaster {
    public static void main(String args[])
    {
        System.out.println("Start BINGO Game!");
        bingoGame(5);
    }

    // ゲームを開始する
    private static void bingoGame(int players) 
    {
        // ビンゴカードを発行
        int [][][] bingo_cards = BingoCard.bingoCardsCreator(players);
        BingoCard.bingoCardPrinter_horizontal(bingo_cards);
        // プレイヤーにカードを渡す
        BingoPlayer.setBingoCards(bingo_cards);
        
        // ビンゴマシーンを回した回数
        int times = 0;
        // ビンゴマシーンから出された数字のリスト
        int [] calledNumList = BingoMachine.BingoNumbers();
        //BingoMachine.DebugPrinter(calledNumList);
        // その時ビンゴマシーンから出された数字
        int calledNumber = 0;
        // ビンゴの合図
        int bingoSign = BingoPlayer.bingoSign;
        
        while(bingoSign==0)
        {
            // ビンゴマシーンをまわす
            calledNumber = calledNumList[times];
            System.out.println("ビンゴマシーンから出された数字は　" + calledNumber + " です．");
            times++;
            
            // プレイヤーはカードに数字があるかどうかを確認する
            // // さらに、ビンゴになっていないかどうかを確認する
            // // （拡張）4つ0があればリーチ
            bingoSign = BingoPlayer.checkNumber(calledNumber);
            BingoCard.bingoCardPrinter_horizontal(bingo_cards);
            
            // ビンゴになっていたら報告する
            if(bingoSign > 0)
            {
                System.out.println("B I N G O !!!");
                break;
            }
            // 本当にビンゴになっているかどうかを確認する
            
            // どのプレイヤーがビンゴになったかを出力した後、ゲームを終了する
            
            
        }
        System.out.println("BINGO!"+"Player");
    }
}
