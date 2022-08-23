//Comp 2004, Assignment 7, question 1 
//Abdul Shaji
//201956968

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <time.h>
#include <unistd.h>




// global variables to be taken from command line for required purpose
int maxTime, numWaiting, numProgramming, numStudents, maxProgram, maxHelping, stopTime;

int currentStudent = 0;  //to check the current student being helped

int busyTA = 0; // to act like  boolean variable to check if TA is busy or not
int helpedStudent = -1;  // number of student being helped, starting with negative to avoid premature coding

int check = -10;   //temp value used to check the waiting array (substitute for NULL to avoid warnings)

int waiting[] = {-10, -10, -10} ;//array to hold student numbers of waiting students
int updateflag = 0;   //used to update for state change

//creating semaphores and mutex 
sem_t sem;
pthread_mutex_t mutex,lock;
time_t start, current;


//prototypes of threads functions
void* workStudent(void* arg);
void* workTA(void* arg);



int main(int argc, char* argv[]) { 
    //checking if command line args have been passed
    if (argc < 2) {
        fprintf(stderr, "Required arguments not passed");
    } 
    
    //assigning global variables to command line args
    maxTime = atoi(argv[1]);
    numStudents = atoi(argv[2]);
    maxProgram = atoi(argv[3]);
    maxHelping = atoi(argv[4]);
    int i;
    
    //initializing mutee and semaphor (sem has value 3 as 3 students can wait at once)
    pthread_mutex_init(&mutex, NULL);
    pthread_mutex_init(&lock, NULL);
    sem_init(&sem, 0, 3);
    
    //declaring pthreads
    pthread_t students[numStudents];
    pthread_t TA;
    
    //locking mutex so TA thread will be prepared first
    pthread_mutex_lock(&mutex);
    pthread_create(&TA, NULL, workTA, NULL);
    
    //creating threads
    for (i = 0; i < numStudents; i++) {
        pthread_create(&students[i], NULL, workStudent, NULL);
    }
   
    
    //joining TA thread since it is the thread printing
    pthread_join(TA, NULL);

    //destroying mutexes
    pthread_mutex_destroy(&mutex);
    pthread_mutex_destroy(&lock);
    sem_destroy(&sem);
    
    return 0;
    
    
}



//threads function that simulates a student visiting a TA for help (random time)
//waiting for TA otherwise (3 students at a time)
//if waiting is not possible, it goes back to coding
void* workStudent(void* arg) {
    
    //locks other mutex and unlocking after so each student can be assigned their repective student numbers
    pthread_mutex_lock(&lock);
    currentStudent++;
    int thisStudent = currentStudent - 1;
    pthread_mutex_unlock(&lock);
    
    
    while(1) {
        //programing for random period of time
        int random = rand() % maxProgram;
        numProgramming++;
        sleep(random);
        numProgramming--;
        
            //acquires semaphpre to make sure only three can wait at once
            sem_wait(&sem);
            numWaiting++;
            for (int i = 0; i < 3; i++) {
                if (waiting[i] ==  check) {
                    waiting[i] = thisStudent;
                    break;
                }
            }
            
            //other mutex locks so only one thread ata time will become student being helped
            pthread_mutex_lock(&mutex);
            busyTA = 1;
            updateflag = 1;  //updatig flag for use in TA
            helpedStudent = thisStudent;
            for (int i = 0; i < 3; i++) {
                if (waiting[i] ==  thisStudent) {
                    waiting[i] = check;
                    break;
                }
            }
            numWaiting--;
            sem_post(&sem);  //releasing sem for next student to join waiting queue
            sleep(random);     //simulating work
            busyTA = 0;
            helpedStudent = check;
            pthread_mutex_unlock(&mutex); //allowing next thread to become helped

    }
    
}


void* workTA(void* arg) {
    //chara array to print sleeping and helping easier
    char state[2][10] =
    { "Sleeping",
      "Helping",
    };
    
    // setting time values
    start = time(NULL);
    current = start;
    stopTime = (int) start + maxTime;
    
    //first and second line being printed as Required
    printf("Time\tTA,\t\tStudents Waiting,\tStudents Programming\n");
    printf("%d,\t%s,\t%d,\t\t\t", ((int) current - (int)start), state[busyTA], numWaiting);
    for (int i = 0; i < numStudents; i++) {
        printf("S%d:", i);
    }
    printf("\n");
    pthread_mutex_unlock(&mutex);
    
    while(1) {

        //condition to stop the loop when time is up
        if (current >= stopTime) {
            printf("%d,\toff,\t\t%d,\t\t\t%d\n", ((int) current - (int)start), numWaiting, numProgramming);
            pthread_exit(NULL);
        }
        
        //removing flag
        if (updateflag && busyTA == 1) {
            updateflag = 0;
        }
        
        //printing lines depending on waiting and helping students
        if(helpedStudent == check) {
             printf("%d,\t%s\t", ((int) current - (int)start), state[0]);
        }
            
        else {
            printf("%d,\t%s S%d,\t", ((int) current - (int)start), state[1], helpedStudent);
        }
            
        for (int i = 0; i < 3; i++){
            // checking if all seats are taken (check denotes this)
            if (waiting[0] == check && waiting[1] == check && waiting[2] == check ) {
                printf("0,");
                break;
            }
            if (waiting[i] != check) {
                printf("S%d:", waiting[i]);
            }
        }
            //formatting rows to align orrectly with different values in each column
            if (waiting[0] == check || waiting[1] == check || waiting[2] == check ) {
                printf("\t\t\t");
            }
            else {
                printf("\t\t"); 
            }
            
        for (int i = 0; i < numStudents; i++) {
            if (i != waiting[0] && i != waiting[1] && i != waiting[2] && helpedStudent != i) {
                printf("S%d:", i);
            }
        }
        printf("\n");
            
            

        }   
        //updating time to end the loop
        current = (int) time(NULL);
    }
    
    
}
