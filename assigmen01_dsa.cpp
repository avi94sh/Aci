#include<stdio.h>
#include<stdlib.h> // header file containing rand() function

char avi(int num)  // main condition which differentiates 0-9 and A-F characters
{
    if( 0<= num  && num<=9)
    {
        return  '0' + num;   
    }

                         else if( num <=15)
                         {
                           return 'A' + (num -10 ); 
                                                   }

             else{
             printf("Enter a valid number");
                                            }
}

    
int main()
{

    
    

    int num;
     
              num = rand() % 15;
     
                                printf("%d\n",num);


                    char temp = avi(num);
    printf("The Randomly Generated Character is :%c" ,temp);


    return 0;
}