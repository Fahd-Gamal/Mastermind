package mastermind;
import java.lang.reflect.*;
import java.util.*;

class Check{
       public void playGame(int[] userOne,int[] userTwo,Scanner input){
            int i=1;     
        while(i<=10) // Number Of Transformers
        {
            System.out.println("This Transformers Number: "+ i);
        userTwo[0]=input.nextInt();
        userTwo[1]=input.nextInt();
        userTwo[2]=input.nextInt();
        userTwo[3]=input.nextInt();
            boolean[] guessCorrectly= new boolean[userOne.length];
            boolean[] codeMatched=new boolean[userOne.length];
                int blackPins=0,whitePins=0;;
         for(int j=0;j<userTwo.length;j++){
             if(userOne[j]==userTwo[j]){
                 blackPins++;
                guessCorrectly[j]=true;
                codeMatched[j]=true;
             }};
         for(int j=0;j<4;j++){
             if(!codeMatched[j]){
             for(int k=0;k<4;k++){
                  if(!guessCorrectly[k]&&userOne[j]==userTwo[k]){
                   whitePins++;
                guessCorrectly[k]=true;
                break;
             }
         }
             }
             }
                  System.out.println("Hints:");
                  System.out.println("BlackHints(correct color in correct place):"+blackPins);
                  System.out.println("WhiteHints(correct color in wrong place):"+whitePins);
            if(Arrays.equals(userOne,userTwo)){
        System.out.println("Your Guess Correct.Congratulation");
        break;
    }else{
                System.out.println("Your Guess Incorrect.please Try Again");
            }
        i++;
            if(i>10&&!Arrays.equals(userOne,userTwo)){
        System.out.println("Game Over,You Finish All Attempts!And This Is The Correct colors: ");
        System.out.println(Arrays.toString(userOne));
        break;
   }
  }
       }
}
class Game extends Check{
    List<Integer>  guessNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);//select four colors from seven colors
}
public class Mastermind {
    
    public static void main(String[] args) {
        Game objGame=new Game();
        System.out.println("Numbers available for selection"+objGame.guessNumbers);
        int[] userOne=new int[4];
        Scanner input=new Scanner(System.in);
        System.out.println("Please Enter Four Number(secrt):");//from first usre
        userOne[0]=input.nextInt();
        userOne[1]=input.nextInt();
        userOne[2]=input.nextInt();
        userOne[3]=input.nextInt();
        System.out.println("You Have Ten Attempts Each Time Entering Four Colors To Guess The Correct Time");
        int[] userTwo=new int[4];//from second 
        objGame.playGame(userOne, userTwo, input);
        }
}
