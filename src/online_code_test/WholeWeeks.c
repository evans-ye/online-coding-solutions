#include <stdio.h>
#include <stdlib.h>
#include <math.h>       /* round, floor, ceil, trunc */

int eql(char*A, char*B) {
    int e = 1;
    for (int i = 0 ; i< sizeof(&A) ; i++) {
        printf("%c",A[i]);
        if (A[i] != B[i] ) {
            e = 0;
            break;
        }
    }
    return e;
}

int solution(int Y, char *A, char *B, char *W) {

    int intA=0, intB=0, intW=-1;

    char *January = "January";
    char *February = "February";
    char *March = "March";
    char *April = "April";
    char *May = "May";
    char *June = "June";
    char *July = "July";
    char *August = "August";
    char *September = "September";
    char *October = "October";
    char *November = "November";
    char *December = "December";
    if (strcmp(A, January)==0 ) intA=0;
    if (strcmp(A, February)==0 ) intA=1;
    if (strcmp(A, March)==0 ) intA=2;
    if (strcmp(A, April)==0 ) intA=3;
    if (strcmp(A, May)==0 ) intA=4;
    if (strcmp(A, June)==0 ) intA=5;
    if (strcmp(A, July)==0 ) intA=6;
    if (strcmp(A, August)==0 ) intA=7;
    if (strcmp(A, September)==0 ) intA=8;
    if (strcmp(A, October)==0 ) intA=9;
    if (strcmp(A, November)==0 ) intA=10;
    if (strcmp(A, December)==0 ) intA=11;

    if (strcmp(B, January)==0 ) intB=0;
    if (strcmp(B, February)==0 ) intB=1;
    if (strcmp(B, March)==0 ) intB=2;
    if (strcmp(B, April)==0 ) intB=3;
    if (strcmp(B, May)==0 ) intB=4;
    if (strcmp(B, June)==0 ) intB=5;
    if (strcmp(B, July)==0 ) intB=6;
    if (strcmp(B, August)==0 ) intB=7;
    if (strcmp(B, September)==0 ) intB=8;
    if (strcmp(B, October)==0 ) intB=9;
    if (strcmp(B, November)==0 ) intB=10;
    if (strcmp(B, December)==0 ) intB=11;

    char *Monday ="Monday";
    char *Tuesday = "Tuesday";
    char *Wednesday = "Wednesday";
    char *Thursday = "Thursday";
    char *Friday = "Friday";
    char *Saturday ="Saturday";
    char *Sunday ="Sunday";
    if (strcmp(W, Monday)==0) intW=0;
    if (strcmp(W, Tuesday)==0) intW=1;
    if (strcmp(W, Wednesday)==0) intW=2;
    if (strcmp(W, Thursday)==0) intW=3;
    if (strcmp(W, Friday)==0) intW=4;
    if (strcmp(W, Saturday)==0) intW=5;
    if (strcmp(W, Sunday)==0) intW=6;

    //HRable months
    intA+=1;
    intB+=1;
    int weekday=intW;

    //printf ("%i_%i_%i",intA,intB,intW);

    int months[12]= {31,28,31,30,31,30,31,31,30,31,30,31};
    int r_months[12]= {31,29,31,30,31,30,31,31,30,31,30,31};
    int shiftdays = 0;

    //find the weekday of the month's first day
    shiftdays=0;
    int leap = 0;
    if((Y%4==0 )) {
        leap=1;
    }
    for (int i=1; i<intA; i++) {
        if (leap) {
            shiftdays+=r_months[i-1];
        }
        else {
            shiftdays+=months[i-1];
        }
    }
    weekday=(weekday+shiftdays)%7;

    //printf("weekday=%i\n",weekday);

    // count wholeweeks for two months.
    // The idea is to subtract the first incomplete week, and then / the number of days by 7
    int availDays=0;
    if (leap) {
        for (int i = intA-1 ; i<= intB-1 ; i++) {
            availDays+=r_months[i];
        }
    }
    else {
        for (int i = intA-1 ; i<= intB-1 ; i++) {
            availDays+=months[i];
        }
    }
    if (weekday>0) {
        availDays-=(7-weekday);
    }
    return floor(availDays/7);
}

int main(void)
{
    //int weeks = solution(2070,"September", "December", "Thursday");
    int weeks = solution(2070,"May", "June", "Thursday");
    printf("%i",weeks);
}

