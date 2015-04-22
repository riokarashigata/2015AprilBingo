/******************************************************/
/* Program Name:    BingoMachine */
/* Name:            Tagashira Kaori */
/* Date yyyy/mm/dd: 2015/04/20 */
/* Description:     ビンゴゲームで数字を出力するビンゴマシーン */
/* Reuse Instruction:*/
/*       */
/*        Purpose: */
/*        Return: */
/******************************************************/
package Ver1;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author kaori
 */
public class BingoMachine {
    
    public static int[] BingoNumbers ()
    {        
        int [] calledNumList = new int[75];
        calledNumList[0] = RandGenerator();
                
        for(int range=1; range<75; range++)
        {
            int calledNum = RandGenerator();
            
            for(int index=0; index<calledNumList.length; index++)
            {
                /* 数字を重複させない。0は含まない。 */
                if(calledNumList[index] == calledNum)
                {
                    break;
                }else{
                    calledNumList[range] = RandGenerator();
                }
                break;
            }
        }
        //DebugPrinter(calledNumList);
        return calledNumList;
    }
    
    /* 乱数を発生する */
    public static int RandGenerator()
    {
        Random rnd = new Random();
        int ran = rnd.nextInt(75) + 1;
        return ran;
    }
    
    public static void DebugPrinter(int[] NumList)
    {
        for(int index=0; index<NumList.length; index++)
        {
            System.out.println(NumList[index]);
        }
    }
    
}