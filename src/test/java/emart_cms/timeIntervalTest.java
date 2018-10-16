package emart_cms;


import emart_cms.brodDetail;
import emart_cms.annDetail;

import java.util.ArrayList;




public class timeIntervalTest {

	
	public static void main(String args[]) throws Exception {
		//timeCheck();
		System.out.println("lenReplace1:"+lenReplace1("0",3));
	}
	public static String lenReplace1(String txt, int i){
		if (txt.length() == i){
			return txt;
		}else if (txt.equals("0")){
			String zero_txt = ""; 
			for (int a=0; a < i ; a++ ){
				zero_txt += txt;
			}
			return zero_txt;
		}else {
			return "0"+txt;
		}
	}
	private static void timeCheck(){
		
		int ret = 0;
		String startTime = "1000";
		String endTime = "2400";
		String dayGubun = "REG";
		String timeInterval = "60";
		int totalTime = 0;
		
		//특정 방송 
		
		
		annDetail anDetail = new annDetail("BROD_0000000020", "060", "ann1Mp3", "49", "1", "R", "10");
		ArrayList<annDetail> abrod = new ArrayList<annDetail>();
		abrod.add(anDetail);
		annDetail anDetail3 = new annDetail("BROD_0000000020", "060", "ann2Mp3", "49", "2", "R", "10");		
		abrod.add(anDetail3);
		
		annDetail anDetail1 = new annDetail("BROD_0000000020", "120", "ann3Mp3", "49", "1", "R", "20");		
		abrod.add(anDetail1);
		
		annDetail anDetail2 = new annDetail("BROD_0000000020", "1530", "ann4Mp3", "49", "1", "S", "");		
		abrod.add(anDetail2);
		
		
		
		//방송
		ArrayList<brodDetail> brod = new ArrayList<brodDetail>();
		brodDetail detail = new brodDetail("BROD_0000000020", "00", "00분이후1.mp3", "228",  "1");
		brod.add(detail);
		brodDetail detail1 = new brodDetail("BROD_0000000021", "00", "00분이후2.mp3", "218",  "2");
		brod.add(detail1);
		
		brodDetail detail2 = new brodDetail("BROD_0000000022", "10", "10분이후1.mp3", "208",  "1");
		brod.add(detail2);
		brodDetail detail3 = new brodDetail("BROD_0000000023", "10", "10분이후2.mp3", "197",  "2");
		brod.add(detail3);
		
		brodDetail detail4 = new brodDetail("BROD_0000000024", "20", "20분이후1.mp3", "218",  "1");
		brod.add(detail4);
		brodDetail detail5 = new brodDetail("BROD_0000000025", "20", "20분이후2.mp3", "128",  "2");
		brod.add(detail5);
		//30분 시작시 여기 부분 안나와야함
		brodDetail detail6 = new brodDetail("BROD_0000000026", "30", "30분이후1.mp3", "208",  "1");
		brod.add(detail6);
		brodDetail detail7 = new brodDetail("BROD_0000000027", "30", "30분이후2.mp3", "192",  "2");
		brod.add(detail7);
		
		brodDetail detail8 = new brodDetail("BROD_0000000028", "40", "40분이후1.mp3", "208",  "1");
		brod.add(detail8);
		brodDetail detail9 = new brodDetail("BROD_0000000029", "40", "40분이후2.mp3", "192",  "2");
		brod.add(detail9);
		brodDetail detail12 = new brodDetail("BROD_0000000029", "40", "40분이후3.mp3", "49",  "3");
		brod.add(detail12);
		
		
		brodDetail detail10 = new brodDetail("BROD_0000000030", "50", "50분이후1.mp3", "228",  "1");
		brod.add(detail10);
		brodDetail detail11 = new brodDetail("BROD_0000000031", "50", "50분이후2.mp3", "218",  "2");
		brod.add(detail11);
		
		

		//시간별 하기 //나누기는 어떻게 처리
		for ( int i = 0; i < (   (((Integer.parseInt(endTime) - Integer.parseInt(startTime)) * 60)/60)/100); i++  ){
			//10분 간격 처리 하기
			System.out.println("i:" + i);
			for (int a =0; a < (Integer.parseInt(timeInterval)/10); a++ ){
				if (timeInterval.equals("30")){
					for (int interval=0; interval < 2; interval++) {						
						brodReport(i , a , abrod, brod , startTime, timeInterval, interval);	
					}
				}else {
					brodReport(i , a , abrod, brod , startTime, timeInterval, 0);	
				}
						 	
			}					
		}
		
	}	
	
	private static String secToMin(String sec){
	    
	   int miu = Integer.parseInt(sec)/60;
	   int sec_r = (Integer.parseInt(sec) - (miu*60));
	   return lenReplace(String.valueOf(miu),2) +":"+ lenReplace(String.valueOf(sec_r),2);
	   
	}
	private static String secToMinTimeChart(String sec){
	    
		   int miu = Integer.parseInt(sec)/60;
		   int sec_r = (Integer.parseInt(sec) - (miu*60));
		   return String.valueOf(miu) +":"+ lenReplace(String.valueOf(sec_r),2);
		   
		}
	private static String lenReplace(String txt, int i){
		if (txt.length() == i){
			return txt;
		}else {
			return "0"+txt;
		}
	}
	//작업 리포트 보여 주기 
	private static void brodReport(int i , int a , ArrayList<annDetail> abrod, ArrayList<brodDetail> brod , String startTime, String timeInterval, int forGubun ){
		//System.out.println(lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i))), 2)+""+lenReplace(String.valueOf(a*10),2)); 
		int totalTime = 0;
		for (int an = 0; an < abrod.size(); an++) {
			if (!abrod.get(an).getAnnGubun().equals("R") 
					&& abrod.get(an).getIntervalTime().equals(
		    				lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i))), 2)+""+lenReplace(String.valueOf(a*10),2))
		    		&& forGubun == 0
		    	){
				
				//특정 시간 방송 먼저
				
				System.out.println(lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + a +""+ secToMinTimeChart(String.valueOf(totalTime)) +":"+ abrod.get(an).getAtchFileId() );
		    	totalTime += Integer.parseInt(abrod.get(an).getMp3Time());
		    	
		    }else if (!abrod.get(an).getAnnGubun().equals("R") 
					&& abrod.get(an).getIntervalTime().equals(
		    				lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i))), 2)+""+lenReplace(String.valueOf((a+3)*10),2))
		    		&& forGubun == 1
		    	){
				
				//특정 시간 방송 먼저
				
				System.out.println(lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + (a+3) +""+ secToMinTimeChart(String.valueOf(totalTime)) +":"+ abrod.get(an).getAtchFileId() );
		    	totalTime += Integer.parseInt(abrod.get(an).getMp3Time());
		    	
		    }else if (abrod.get(an).getAnnGubun().equals("R") && i == 0 && abrod.get(an).getAnnStartTime().equals( String.valueOf(a*10)) && forGubun == 0 ){
		    	//나누기 없을때
		    	
                System.out.println(lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + a +""+ secToMinTimeChart(String.valueOf(totalTime))+":"+ abrod.get(an).getAtchFileId()  );
		    	totalTime += Integer.parseInt(abrod.get(an).getMp3Time());
		    }
			else if (abrod.get(an).getAnnGubun().equals("R") && i > 0 && 
					( i% (Integer.parseInt(abrod.get(an).getIntervalTime())/ 60) ) == 0	&& ((a*10) == Integer.parseInt(abrod.get(an).getAnnStartTime())) 
					&& forGubun == 0
					){
		    	//이후 나누기 생각						
		    	System.out.println(lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + a +""+ secToMinTimeChart(String.valueOf(totalTime)) +":"+ abrod.get(an).getAtchFileId() );				    	
		    	totalTime += Integer.parseInt(abrod.get(an).getMp3Time());
		    }else if (abrod.get(an).getAnnGubun().equals("R") && i == 0 && abrod.get(an).getAnnStartTime().equals( String.valueOf((a+3)*10)) && forGubun == 1 ){
		    	//나누기 없을때
		    	
                System.out.println(lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + (a+3) +""+ secToMinTimeChart(String.valueOf(totalTime))+":"+ abrod.get(an).getAtchFileId()  );
		    	totalTime += Integer.parseInt(abrod.get(an).getMp3Time());
		    }
			else if (abrod.get(an).getAnnGubun().equals("R") && i > 0 && 
					( i% (Integer.parseInt(abrod.get(an).getIntervalTime())/ 60) ) == 0	&& (((a+3)*10) == Integer.parseInt(abrod.get(an).getAnnStartTime())) 
					&& forGubun == 1
					){
		    	//이후 나누기 생각						
		    	System.out.println(lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + (a+3) +""+ secToMinTimeChart(String.valueOf(totalTime)) +":"+ abrod.get(an).getAtchFileId() );				    	
		    	totalTime += Integer.parseInt(abrod.get(an).getMp3Time());
		    }
			
		}
		
		
		for (int aa = 0; aa < brod.size(); aa++) {
			if (brod.get(aa).getIntervalTime().equals( lenReplace(String.valueOf( a*10), 2))){
				//기념일 정리 해서 넣기 
				if (totalTime <= 600){
					if (timeInterval.equals("30")){						
						//구분을 넣어야 함 1시간 단위 설정시
						if (forGubun == 0){
							System.out.println(lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + a +""+ secToMinTimeChart(String.valueOf(totalTime)) +":"+ brod.get(aa).getAtchFileId() );	
						}else {
							System.out.println(lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + (a+3) +""+ secToMinTimeChart(String.valueOf(totalTime)) +":"+ brod.get(aa).getAtchFileId() );
						}						
						totalTime +=  Integer.parseInt(brod.get(aa).getMp3Time());	
					}else {
						System.out.println(lenReplace( String.valueOf((Integer.parseInt(startTime.substring(0,2)) + (i*1))), 2)  + ":" + a +""+ secToMinTimeChart(String.valueOf(totalTime)) +":"+ brod.get(aa).getAtchFileId() );
						totalTime +=  Integer.parseInt(brod.get(aa).getMp3Time());	
					}
				
				}else {
					break;
				}
				
			}						
		}
		System.out.println("-------------------------------------------------------------------------------------------");
		
	}
	
}

