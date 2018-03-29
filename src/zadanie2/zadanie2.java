package zadanie2;

import java.util.*;
import java.io.*;

public class zadanie2 {
	public static boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	} 
	
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("cron.txt");
		Scanner in = new Scanner(file);
		String[] sheduleMinute = new String[100000];
        String[] sheduleHour = new String[100000];
        String[] sheduleDayOfMonth = new String[100000];
        String[] sheduleDayOfWeek = new String[100000];
        int[] flaga = new int[1000];
        int index = 0;
        while(in.hasNext()){
        	flaga[index] = 0;
        	sheduleMinute[index] = "";
            sheduleHour[index] = "";
            sheduleDayOfMonth[index] = "";
            sheduleDayOfWeek[index] = "";
        	int ileKresek = 0;
        	String wiersz = in.nextLine();
            String[] element = new String[6];
            StringTokenizer kreska = new StringTokenizer(wiersz, "|");
            while(kreska.hasMoreElements()){
            	element[ileKresek] = kreska.nextElement().toString();
            	ileKresek++;
            	if(ileKresek == 5){
            		break;
            	}
            }
            if(ileKresek != 4 || wiersz.indexOf('.') != -1){
            	//System.out.println("z³e dane");
            	flaga[index] = 1;
            }else{
            	//sprawdzanie minut
            	if(element[0].equals("*")){
            		sheduleMinute[index] = "10 20 30 40 50 60";
            	}else{
            		int indexMinuty = 0;
            		String[] minutyPrzecinki = new String[6];
            		StringTokenizer przecinek = new StringTokenizer(element[0], ",");
            		while(przecinek.hasMoreElements()){
            			if(indexMinuty == 6){
            				flaga[index] = 1;
            				break;
            			}
            			minutyPrzecinki[indexMinuty] = przecinek.nextElement().toString();
            			if(isNumeric(minutyPrzecinki[indexMinuty]) && Integer.parseInt(minutyPrzecinki[indexMinuty]) >= 10 && Integer.parseInt(minutyPrzecinki[indexMinuty]) <= 60 && Integer.parseInt(minutyPrzecinki[indexMinuty]) % 10 == 0){
            				sheduleMinute[index] += minutyPrzecinki[indexMinuty] + " ";
 
            			}else{
            				flaga[index] = 1;
            			}
            			indexMinuty++;
            		}
            		
            		
            	}
            	//sprawdzenie godziny
            	if(element[1].equals("*")){
            		sheduleHour[index] = "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23";
            	}else{
            		int indexGodziny = 0;
            		String[] godzinyPrzecinki = new String[24];
            		StringTokenizer przecinek = new StringTokenizer(element[1], ",");
            		while(przecinek.hasMoreElements()){
            			if(indexGodziny == 24){
            				flaga[index] = 1;
            				break;
            			}
            			godzinyPrzecinki[indexGodziny] = przecinek.nextElement().toString();
            			indexGodziny++;
            			}
            		for(int i = 0; i <indexGodziny; i++){
            			int indexGodzinaMyslniki = 0;
            			String[] podzieloneMyslnikami = new String[2];
            			if(godzinyPrzecinki[i].charAt(0) == '-'){
            				flaga[index] = 1;
            			}
            			StringTokenizer myslnik = new StringTokenizer(godzinyPrzecinki[i], "-");
            			while(myslnik.hasMoreElements()){
            				if(indexGodzinaMyslniki == 2){
            					flaga[index] = 1;
                				break;
            				}
            				podzieloneMyslnikami[indexGodzinaMyslniki] = myslnik.nextElement().toString();
            				indexGodzinaMyslniki++;
            			}
            			if(indexGodzinaMyslniki == 1){
            				if(isNumeric(podzieloneMyslnikami[0]) && Integer.parseInt(podzieloneMyslnikami[0]) >= 0 && Integer.parseInt(podzieloneMyslnikami[0]) <= 24){
            					sheduleHour[index] += podzieloneMyslnikami[0] + " ";
            				}
            				else{
            					flaga[index] = 1;
            				}
            			}else{
            				if(Integer.parseInt(podzieloneMyslnikami[0]) < Integer.parseInt(podzieloneMyslnikami[1])){
            					if(Integer.parseInt(podzieloneMyslnikami[0]) >=0 && Integer.parseInt(podzieloneMyslnikami[0]) <= 23 && Integer.parseInt(podzieloneMyslnikami[1]) >= 0 && Integer.parseInt(podzieloneMyslnikami[1]) <= 23){
            						for(int j = Integer.parseInt(podzieloneMyslnikami[0]); j <= Integer.parseInt(podzieloneMyslnikami[1]); j++){
            							sheduleHour[index] += j + " ";
            						}
            					}else{
            						flaga[index] = 1;
            					}
            				}else{
            					flaga[index] = 1;
            				}
            			}
            			
            		}
            		
            	}
            	//sprawdzanie dni misiaca
            	if(element[2].equals("*")){
            		sheduleDayOfMonth[index] = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31";
            	}else{
            		int indexGodziny = 0;
            		String[] godzinyPrzecinki = new String[31];
            		StringTokenizer przecinek = new StringTokenizer(element[2], ",");
            		while(przecinek.hasMoreElements()){
            			if(indexGodziny == 31){
            				flaga[index] = 1;
            				break;
            			}
            			godzinyPrzecinki[indexGodziny] = przecinek.nextElement().toString();
            			indexGodziny++;
            			}
            		for(int i = 0; i <indexGodziny; i++){
            			int indexGodzinaMyslniki = 0;
            			String[] podzieloneMyslnikami = new String[2];
            			if(godzinyPrzecinki[i].charAt(0) == '-'){
            				flaga[index] = 1;
            			}
            			StringTokenizer myslnik = new StringTokenizer(godzinyPrzecinki[i], "-");
            			while(myslnik.hasMoreElements()){
            				if(indexGodzinaMyslniki == 2){
            					flaga[index] = 1;
                				break;
            				}
            				podzieloneMyslnikami[indexGodzinaMyslniki] = myslnik.nextElement().toString();
            				indexGodzinaMyslniki++;
            			}
            			if(indexGodzinaMyslniki == 1){
            				if(isNumeric(podzieloneMyslnikami[0]) && Integer.parseInt(podzieloneMyslnikami[0]) >= 1 && Integer.parseInt(podzieloneMyslnikami[0]) <= 31){
            					sheduleDayOfMonth[index] += podzieloneMyslnikami[0] + " ";
            				}
            				else{
            					flaga[index] = 1;
            				}
            			}else{
            				if(Integer.parseInt(podzieloneMyslnikami[0]) < Integer.parseInt(podzieloneMyslnikami[1])){
            					if(Integer.parseInt(podzieloneMyslnikami[0]) >= 1 && Integer.parseInt(podzieloneMyslnikami[0]) <= 31 && Integer.parseInt(podzieloneMyslnikami[1]) >= 1 && Integer.parseInt(podzieloneMyslnikami[1]) <= 31){
            						for(int j = Integer.parseInt(podzieloneMyslnikami[0]); j <= Integer.parseInt(podzieloneMyslnikami[1]); j++){
            							sheduleDayOfMonth[index] += j + " ";
            						}
            					}else{
            						flaga[index] = 1;
            					}
            				}
            				else{
            					flaga[index] = 1;
            				}
            			}
            			
            		}
            		
            	}
            	//sprawdzenie dni tygodnia
            	if(element[3].equals("*")){
            		sheduleDayOfWeek[index] = "1 2 3 4 5 6 7";
            	}else{
            		int indexGodziny = 0;
            		String[] godzinyPrzecinki = new String[31];
            		StringTokenizer przecinek = new StringTokenizer(element[3], ",");
            		while(przecinek.hasMoreElements()){
            			if(indexGodziny == 7){
            				flaga[index] = 1;
            				break;
            			}
            			godzinyPrzecinki[indexGodziny] = przecinek.nextElement().toString();
            			indexGodziny++;
            			}
            		for(int i = 0; i <indexGodziny; i++){
            			int indexGodzinaMyslniki = 0;
            			String[] podzieloneMyslnikami = new String[2];
            			if(godzinyPrzecinki[i].charAt(0) == '-'){
            				flaga[index] = 1;
            			}
            			StringTokenizer myslnik = new StringTokenizer(godzinyPrzecinki[i], "-");
            			while(myslnik.hasMoreElements()){
            				if(indexGodzinaMyslniki == 2){
            					flaga[index] = 1;
                				break;
            				}
            				podzieloneMyslnikami[indexGodzinaMyslniki] = myslnik.nextElement().toString();
            				indexGodzinaMyslniki++;
            			}
            			if(indexGodzinaMyslniki == 1){
            				if(isNumeric(podzieloneMyslnikami[0]) && Integer.parseInt(podzieloneMyslnikami[0]) >= 1 && Integer.parseInt(podzieloneMyslnikami[0]) <= 7){
            					sheduleDayOfWeek[index] += podzieloneMyslnikami[0] + " ";
            				}
            				else{
            					flaga[index] = 1;
            				}
            			}else{
            				if(Integer.parseInt(podzieloneMyslnikami[0]) < Integer.parseInt(podzieloneMyslnikami[1])){
            					if(Integer.parseInt(podzieloneMyslnikami[0]) >= 1 && Integer.parseInt(podzieloneMyslnikami[0]) <= 7 && Integer.parseInt(podzieloneMyslnikami[1]) >= 1 && Integer.parseInt(podzieloneMyslnikami[1]) <= 7){
            						for(int j = Integer.parseInt(podzieloneMyslnikami[0]); j <= Integer.parseInt(podzieloneMyslnikami[1]); j++){
            							sheduleDayOfWeek[index] += j + " ";
            						}
            					}else{
            						flaga[index] = 1;
            					}
            				}else{
            					flaga[index] = 1;
            				}
            			}
            			
            		}
            		
            	}
            }
            //System.out.println(index+ wiersz + " "+flaga[index]);
            if(flaga[index] == 0){
            	System.out.println("Minuty: " + sheduleMinute[index]);
            	System.out.println("Godziny: " + sheduleHour[index]);
            	System.out.println("Dzieñ miesiaca: " + sheduleDayOfMonth[index]);
            	System.out.println("Dzieñ miesiaca: " + sheduleDayOfWeek[index]);
            	System.out.println();
            	index++;
            }
            else{
            	//System.out.println("Z³e dane");
            	index++;
            }
        }
	}

}
