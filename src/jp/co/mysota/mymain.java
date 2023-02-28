//このソースは、VstoneMagicによって自動生成されました。
//ソースの内容を書き換えた場合、VstoneMagicで開けなくなる場合があります。
package	jp.co.mysota;
import	main.main.GlobalVariable;
import	jp.vstone.RobotLib.*;
import	jp.vstone.sotatalk.*;
import	jp.vstone.sotatalk.SpeechRecog.*;
import	jp.vstone.network.*;
import	java.io.*;
import	java.util.*;
import	java.awt.Color;
import	java.time.*;
import	java.util.Random;

public class mymain
{

	public short net_loop;
	public String recvMesg;
	public boolean isAuto;
	public jp.co.mysota.behavior behavior;
	public boolean isDay;
	public int dayStatus;
	public CPlayWave cplay;
	public String speechRecogResult;
	public RecogResult recogresult;
	public String readText;
	public CRobotPose pose;
	public String time_string;
	public LocalDateTime localDateTime;
	public int getDateTimeElement;
	public void main()																									//@<BlockInfo>jp.vstone.block.func,1664,240,2544,240,False,11,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,1728,240,1728,240,False,10,@</BlockInfo>
																														//@<EndOfBlock/>
		net_main();																										//@<BlockInfo>jp.vstone.block.callfunc.base,1808,240,1808,240,False,9,@</BlockInfo>	@<EndOfBlock/>
		auto();																											//@<BlockInfo>jp.vstone.block.callfunc.base,1872,240,1872,240,False,8,@</BlockInfo>	@<EndOfBlock/>
		if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();								//@<BlockInfo>jp.vstone.block.talk.idling2,1936,240,2480,240,False,7,Idling開始@</BlockInfo>
		try{


																														//@<OutputChild>
			while(net_loop==0)																							//@<BlockInfo>jp.vstone.block.while,2000,240,2416,240,False,6,TRUE@</BlockInfo>
			{


																														//@<OutputChild>
				if(recvMesg!="")																						//@<BlockInfo>jp.vstone.block.if,2064,192,2352,192,False,5,コメント@</BlockInfo>
				{
																														//@<OutputChild>
					String argString=recvMesg;																			//@<BlockInfo>jp.vstone.block.variable,2128,192,2128,192,False,3,break@</BlockInfo>
																														//@<EndOfBlock/>
					recvMesg=(String)"";																				//@<BlockInfo>jp.vstone.block.calculater,2208,192,2208,192,False,2,@</BlockInfo>
																														//@<EndOfBlock/>
					teleop((String)argString);																			//@<BlockInfo>jp.vstone.block.callfunc.base,2288,192,2288,192,False,1,@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>
				}
				else
				{
																														//@<OutputChild>
					CRobotUtil.wait((int)100);																			//@<BlockInfo>jp.vstone.block.wait,2208,288,2208,288,False,4,コメント@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>
				}
																														//@<EndOfBlock/>
																														//@</OutputChild>
			}
																														//@<EndOfBlock/>
																														//@</OutputChild>


		}
		finally
		{
			GlobalVariable.sotawish.StopIdling();
		}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void net_main()																								//@<BlockInfo>jp.vstone.block.func,1664,464,2432,464,False,20,socketでメッセージを受け取る@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.thread,1728,464,2368,464,False,19,スレッド@</BlockInfo>
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						
						
																														//@<OutputChild>
						{																											//@<BlockInfo>jp.vstone.block.tcpip.server.init,1792,464,2304,464,False,18,@</BlockInfo>
							TCPIPServer tcpipServer = new TCPIPServer((short)5001,(int)5000);
							
																																	//@<OutputChild>
							while(net_loop==0)																						//@<BlockInfo>jp.vstone.block.while,1856,464,2240,464,False,17,TRUE@</BlockInfo>
							{
						
						
																																	//@<OutputChild>
								try{																								//@<BlockInfo>jp.vstone.block.tcpip.server,1920,416,2176,416,False,16,@</BlockInfo>
									GlobalVariable.recvString = tcpipServer.waitRequest();
						
									if(GlobalVariable.recvString==null) GlobalVariable.recvString="";
									if(GlobalVariable.recvString.contentEquals((String)"cmd;end"))
									{
																																	//@<OutputChild>
										net_loop=(short)1;																				//@<BlockInfo>jp.vstone.block.calculater,1984,416,1984,416,False,13,@</BlockInfo>
																																		//@<EndOfBlock/>
										CRobotUtil.wait((int)2000);																		//@<BlockInfo>jp.vstone.block.wait,2080,416,2080,416,False,12,コメント@</BlockInfo>	@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else
									{
																																	//@<OutputChild>
										recvMesg=(String)GlobalVariable.recvString;														//@<BlockInfo>jp.vstone.block.calculater,1984,512,1984,512,False,15,@</BlockInfo>
																																		//@<EndOfBlock/>
										CRobotUtil.Log(getClass().getSimpleName(), (String)recvMesg);									//@<BlockInfo>jp.vstone.block.printlog,2048,512,2048,512,False,14,@</BlockInfo>	@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									
								} catch(Exception e) { }
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
							}
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
						
							
						}
																																	//@<EndOfBlock/>
																																		//@</OutputChild>

						
					} catch(Exception e) {
						CRobotUtil.Err("jp.vstone.block.thread","例外が発生しました。");
						e.printStackTrace();
					}
				}
			});
			th.start();
		}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void teleop(String cmdMesg)																					//@<BlockInfo>jp.vstone.block.func,1664,784,2592,784,False,36,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		String[] cmdArray=cmdMesg.split(";",-1);																		//@<BlockInfo>jp.vstone.block.variable,1728,784,1728,784,False,35,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isCmd=false;																							//@<BlockInfo>jp.vstone.block.variable,1792,784,1792,784,False,34,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isMotion=false;																							//@<BlockInfo>jp.vstone.block.variable,1856,784,1856,784,False,33,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isScenario=false;																						//@<BlockInfo>jp.vstone.block.variable,1920,784,1920,784,False,32,break@</BlockInfo>
																														//@<EndOfBlock/>
		for(int i=0;i<(int)cmdArray.length;i++)																			//@<BlockInfo>jp.vstone.block.for,1984,784,2528,784,False,31,コメント@</BlockInfo>
		{
																														//@<OutputChild>
			CRobotUtil.Log(getClass().getSimpleName(), (String)"検索対象" + cmdArray[i]);									//@<BlockInfo>jp.vstone.block.printlog,2048,784,2048,784,False,30,@</BlockInfo>	@<EndOfBlock/>
			{																											//@<BlockInfo>jp.vstone.block.text.regex,2112,640,2400,640,False,29,textに設定した文字列を正規表現でマッチングする。@</BlockInfo>
				String text = (String)cmdArray[i];
				if(text.matches((String)"cmd"))
				{
				
																														//@<OutputChild>
					isCmd=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,2256,640,2256,640,False,21,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else if(text.matches((String)"motion"))
				{
				
																														//@<OutputChild>
					isMotion=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,2192,736,2192,736,False,24,@</BlockInfo>
																															//@<EndOfBlock/>
					doMotion((String)cmdArray[i+1]);																		//@<BlockInfo>jp.vstone.block.callfunc.base,2256,736,2256,736,False,23,@</BlockInfo>	@<EndOfBlock/>
					i=(int)i + 1;																							//@<BlockInfo>jp.vstone.block.calculater,2320,736,2320,736,False,22,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else if(text.matches((String)"scenario"))
				{
				
																														//@<OutputChild>
					isScenario=true;																						//@<BlockInfo>jp.vstone.block.calculater,2192,832,2192,832,False,27,@</BlockInfo>
																															//@<EndOfBlock/>
					doScenario((String)cmdArray[i+1]);																		//@<BlockInfo>jp.vstone.block.callfunc.base,2256,832,2256,832,False,26,@</BlockInfo>	@<EndOfBlock/>
					i=(int)i + 1;																							//@<BlockInfo>jp.vstone.block.calculater,2320,832,2320,832,False,25,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else
				{
				
																														//@<OutputChild>
																														//@</OutputChild>

				}
				
			}																											//@<EndOfBlock/>
			if(isCmd==false && i==0){																					//@<BlockInfo>jp.vstone.block.freeproc,2464,784,2464,784,False,28,@</BlockInfo>
				System.out.println("第一コマンドが間違っています");
				break;
			}
			else if(isMotion==false && i==1){
				System.out.println("第二コマンドが間違っています");
				break;
			}
																														//@<EndOfBlock/>
																														//@</OutputChild>
		}																												//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void auto()																									//@<BlockInfo>jp.vstone.block.func,48,720,1424,720,False,62,コメント@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.thread,112,720,1344,720,False,61,スレッド@</BlockInfo>
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						
						
																														//@<OutputChild>
						while(net_loop==0)																							//@<BlockInfo>jp.vstone.block.while,176,720,1264,720,False,60,TRUE@</BlockInfo>
						{
						
						
																																	//@<OutputChild>
							pose = new CRobotPose();																				//@<BlockInfo>jp.vstone.block.pose,240,720,240,720,False,59,コメント@</BlockInfo>
							pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
											new Short[]{0,-900,0,900,0,0,0,0}
											);
							pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
											new Short[]{100,100,100,100,100,100,100,100}
											);
							pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
											new Short[]{0,-255,0,180,80,0,180,80,0}
											);
							GlobalVariable.motion.play(pose,500);
							CRobotUtil.wait(500);																					//@<EndOfBlock/>
							if(isAuto==GlobalVariable.TRUE)																			//@<BlockInfo>jp.vstone.block.if,304,672,1184,672,False,58,コメント@</BlockInfo>
							{
																																	//@<OutputChild>
								if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();					//@<BlockInfo>jp.vstone.block.talk.idling2,384,672,1104,672,False,54,Idling開始@</BlockInfo>
								try{
						
						
																																	//@<OutputChild>
									recogresult = GlobalVariable.recog.getRecognitionwithAbort((int)60000);							//@<BlockInfo>jp.vstone.block.talk.speechrecog.score2,512,160,992,160,False,53,音声認識を行い、認識候補との完全一致で比較する。認識スコアが一番高い結果に分岐する。実際に認識された文字列はspeechRecogResultに代入される@</BlockInfo>
									speechRecogResult = recogresult.CheckBest(new String[]{
									 "こんにちは" ,  "何ができる" ,  "名前は" ,  "ばいばい" ,  "建物" ,  "イベント" ,  "ありがとう" ,  "" , 
									},false);
									if(speechRecogResult == null) speechRecogResult = "";
						
									if(speechRecogResult.contains((String)"こんにちは"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,656,160,656,160,False,37,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"何ができる"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"イベントの案内や、この建物の案内ができるよ",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,656,256,656,256,False,38,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"名前は"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"僕はソータ、っていうよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,656,352,656,352,False,39,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"ばいばい"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"ばいばい！またきてね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,656,448,656,448,False,40,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"建物"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"建物の案内だね、任せてよ。9階はコワーキングスペースです。7階と8階には、会議室と、プライベートオフィスや、オンラインブースがあるよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,656,544,656,544,False,41,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"イベント"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"ホームページに掲載しているよ。ぜひ見てみてね。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,656,640,656,640,False,42,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"ありがとう"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"えへへ、どういたしまして。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,656,736,656,736,False,123,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											switch(GlobalVariable.random.nextInt((int)16))												//@<BlockInfo>jp.vstone.block.random,592,1216,912,1200,True,52,何か音が聞こえたとき 1/5 (20%) の確率でランダムな定型文を発話する@</BlockInfo>
											{
												case (int)0:
												{
																																		//@<OutputChild>
													dayOrNight();																			//@<BlockInfo>jp.vstone.block.callfunc.base,624,1056,624,1056,False,47,@</BlockInfo>	@<EndOfBlock/>
													if(dayStatus==0)																		//@<BlockInfo>jp.vstone.block.if,688,960,816,960,False,46,コメント@</BlockInfo>
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.Say((String)"おはようございます。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,752,960,752,960,False,43,@</BlockInfo>
																																			//@<EndOfBlock/>
																																			//@</OutputChild>
													}
													else if(dayStatus==1)
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,752,1056,752,1056,False,44,@</BlockInfo>
																																			//@<EndOfBlock/>
																																			//@</OutputChild>
													}
													else if(dayStatus==2)
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.Say((String)"こんばんは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,752,1152,752,1152,False,45,@</BlockInfo>
																																			//@<EndOfBlock/>
																																			//@</OutputChild>
													}
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												case (int)1:
												{
																																		//@<OutputChild>
													GlobalVariable.sotawish.Say((String)"お仕事、お疲れさまです！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,736,1264,736,1264,False,48,@</BlockInfo>
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												case (int)2:
												{
																																		//@<OutputChild>
													GlobalVariable.sotawish.Say((String)"今、受付のお手伝いをしています！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,736,1344,736,1344,False,49,@</BlockInfo>
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												case (int)3:
												{
																																		//@<OutputChild>
													GlobalVariable.sotawish.Say((String)"何かわからないことがあれば、話しかけてね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,736,1440,736,1440,False,50,@</BlockInfo>
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												default:
												{
																																		//@<OutputChild>
													CRobotUtil.Log(getClass().getSimpleName(), (String)"発話を見送るよ。");							//@<BlockInfo>jp.vstone.block.printlog,736,1584,736,1584,False,51,@</BlockInfo>	@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
											
											}																							//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
						
						
								}
								finally
								{
									GlobalVariable.sotawish.StopIdling();
								}
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
							}
							else
							{
																																	//@<OutputChild>
								if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();					//@<BlockInfo>jp.vstone.block.talk.idling2,400,1680,1104,1680,False,57,Idling開始@</BlockInfo>
								try{
						
						
																																	//@<OutputChild>
									CRobotUtil.Log(getClass().getSimpleName(), (String)"auto状態待機");									//@<BlockInfo>jp.vstone.block.printlog,464,1680,464,1680,False,56,@</BlockInfo>	@<EndOfBlock/>
									CRobotUtil.wait((int)100);																		//@<BlockInfo>jp.vstone.block.wait,1040,1680,1040,1680,False,55,コメント@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						
						
								}
								finally
								{
									GlobalVariable.sotawish.StopIdling();
								}
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
							}
																																	//@<EndOfBlock/>
																																	//@</OutputChild>
						}
																																	//@<EndOfBlock/>
																																		//@</OutputChild>

						
					} catch(Exception e) {
						CRobotUtil.Err("jp.vstone.block.thread","例外が発生しました。");
						e.printStackTrace();
					}
				}
			});
			th.start();
		}
																														//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void doMotion(String strMotionName)																			//@<BlockInfo>jp.vstone.block.func,1664,1440,2288,1440,False,85,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.talk.idling.stop,1728,1440,2224,1440,False,84,Idling動作を一時的に無効にします@</BlockInfo>
			boolean isIdling = GlobalVariable.sotawish.isPlayIdling();
			if(isIdling) GlobalVariable.sotawish.StopIdling();
			{


																														//@<OutputChild>
				pose = new CRobotPose();																					//@<BlockInfo>jp.vstone.block.pose,1792,1440,1792,1440,False,83,コメント@</BlockInfo>
				pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{0,-900,0,900,0,0,0,0}
								);
				pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{100,100,100,100,100,100,100,100}
								);
				pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
								new Short[]{0,-255,0,180,80,0,180,80,0}
								);
				GlobalVariable.motion.play(pose,800);
				CRobotUtil.wait(800);																						//@<EndOfBlock/>
				switch((String)strMotionName)																				//@<BlockInfo>jp.vstone.block.switch,1856,1056,2144,1056,False,82,@</BlockInfo>
				{
					case (String)"Nodding":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"HandNodding ! -- うなずきます");							//@<BlockInfo>jp.vstone.block.printlog,1968,1056,1968,1056,False,64,@</BlockInfo>	@<EndOfBlock/>
						behavior.head_nodding();																				//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1056,2032,1056,False,63,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"HeadShaking":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"HandShaking ! -- いやいやします");							//@<BlockInfo>jp.vstone.block.printlog,1968,1152,1968,1152,False,66,@</BlockInfo>	@<EndOfBlock/>
						behavior.head_shaking();																				//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1152,2032,1152,False,65,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"LeftHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"LeftHandWaving !");									//@<BlockInfo>jp.vstone.block.printlog,1968,1248,1968,1248,False,68,@</BlockInfo>	@<EndOfBlock/>
						behavior.lefthand_waving();																				//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1248,2032,1248,False,67,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"RightHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"RightHandWaving ! -- 右手を振ります");						//@<BlockInfo>jp.vstone.block.printlog,1968,1344,1968,1344,False,70,@</BlockInfo>	@<EndOfBlock/>
						behavior.righthand_waving();																			//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1344,2032,1344,False,69,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"BothHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"BothHandWaving ! -- 両手を振ります");						//@<BlockInfo>jp.vstone.block.printlog,1968,1440,1968,1440,False,72,@</BlockInfo>	@<EndOfBlock/>
						behavior.bothhand_waving();																				//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1440,2032,1440,False,71,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"BothHandRaising":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"BothHandRaising ! -- 両手を上下します");					//@<BlockInfo>jp.vstone.block.printlog,1968,1536,1968,1536,False,74,@</BlockInfo>	@<EndOfBlock/>
						behavior.bothhand_raising();																			//@<BlockInfo>jp.vstone.block.callfunc.base,2032,1536,2032,1536,False,73,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"AutoStart":
					{
																															//@<OutputChild>
						isAuto=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,1936,1632,1936,1632,False,77,@</BlockInfo>
																																//@<EndOfBlock/>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"AutoStart ! -- 自動応答を始めます");							//@<BlockInfo>jp.vstone.block.printlog,2000,1632,2000,1632,False,76,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"オペレータさん、お疲れ様！ここからは僕に任せて！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,2064,1632,2064,1632,False,75,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"AutoStop":
					{
																															//@<OutputChild>
						isAuto=(boolean)false;																					//@<BlockInfo>jp.vstone.block.calculater,1936,1728,1936,1728,False,80,@</BlockInfo>
																																//@<EndOfBlock/>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"AutoStop ! -- 自動応答を終了します");							//@<BlockInfo>jp.vstone.block.printlog,2000,1728,2000,1728,False,79,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"オペレータさんが来てくれたよ！バトンタッチするね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,2064,1728,2064,1728,False,78,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					default:
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"それは登録されてないわー");										//@<BlockInfo>jp.vstone.block.printlog,2000,1824,2000,1824,False,81,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
				
				}																											//@<EndOfBlock/>
																																//@</OutputChild>


			}
			if(isIdling) GlobalVariable.sotawish.StartIdling();
		}																												//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void dayOrNight()																							//@<BlockInfo>jp.vstone.block.func,2576,512,2992,512,False,91,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		localDateTime = LocalDateTime.now();																			//@<BlockInfo>jp.vstone.block.time.getlocaldatetime,2640,512,2640,512,False,90,ローカル時間をロボット内のカレンダーより取得し、変数LocalDateTime lodalDateTimeに代入。取得した情報から日時などを個別に切り出す場合は「日時の切り出しブロック」を使う@</BlockInfo>
																														//@<EndOfBlock/>
		{																												//@<BlockInfo>jp.vstone.block.time.getdatetimeelement,2704,512,2704,512,False,89,dateTimeに記録された日時情報から、年・月・日・時・分・秒のうち一つをtypeで指定し、変数int getDateTimeElementに代入する@</BlockInfo>
			LocalDateTime d = (LocalDateTime)localDateTime;
			getDateTimeElement = d.getHour();
		}																												//@<EndOfBlock/>
		if(getDateTimeElement>=0 && getDateTimeElement<5){																//@<BlockInfo>jp.vstone.block.freeproc,2784,512,2784,512,False,88,@</BlockInfo>
			isDay = false;
			dayStatus = 2;
			System.out.println("夜です" + getDateTimeElement);
		}
		else if(getDateTimeElement >=5 && getDateTimeElement <10){
			isDay = true;
			dayStatus = 0;
			System.out.println("朝です" + getDateTimeElement);
		}
		else if(getDateTimeElement >=10 && getDateTimeElement <17){
			isDay = true;
			dayStatus = 1;
			System.out.println("昼です" + getDateTimeElement);
		}
		else if(getDateTimeElement>=17 && getDateTimeElement<24){
			isDay = false;
			dayStatus = 2;
			System.out.println("夜です" + getDateTimeElement);
		}
																														//@<EndOfBlock/>
		CRobotUtil.Log(getClass().getSimpleName(), (String)"現在の時刻（Hour）は....");											//@<BlockInfo>jp.vstone.block.printlog,2848,512,2848,512,False,87,@</BlockInfo>	@<EndOfBlock/>
		CRobotUtil.Log(getClass().getSimpleName(), (String)time_string);												//@<BlockInfo>jp.vstone.block.printlog,2912,512,2912,512,False,86,@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

	//@<Separate/>
	public mymain()																										//@<BlockInfo>jp.vstone.block.func.constructor,48,32,1056,32,False,106,@</BlockInfo>
	{
																														//@<OutputChild>
		net_loop=0;																										//@<BlockInfo>jp.vstone.block.variable,112,32,112,32,False,105,break@</BlockInfo>
																														//@<EndOfBlock/>
		recvMesg="";																									//@<BlockInfo>jp.vstone.block.variable,176,32,176,32,False,104,break@</BlockInfo>
																														//@<EndOfBlock/>
		isAuto=true;																									//@<BlockInfo>jp.vstone.block.variable,240,32,240,32,False,103,break@</BlockInfo>
																														//@<EndOfBlock/>
		behavior=new jp.co.mysota.behavior();																			//@<BlockInfo>jp.vstone.block.variable,304,32,304,32,False,102,break@</BlockInfo>
																														//@<EndOfBlock/>
		isDay=true;																										//@<BlockInfo>jp.vstone.block.variable,368,32,368,32,False,101,break@</BlockInfo>
																														//@<EndOfBlock/>
		dayStatus=0;																									//@<BlockInfo>jp.vstone.block.variable,432,32,432,32,False,100,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*CPlayWave cplay*/;																							//@<BlockInfo>jp.vstone.block.variable,544,32,544,32,False,99,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String speechRecogResult*/;																					//@<BlockInfo>jp.vstone.block.variable,608,32,608,32,False,98,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*RecogResult recogresult*/;																					//@<BlockInfo>jp.vstone.block.variable,672,32,672,32,False,97,break@</BlockInfo>
																														//@<EndOfBlock/>
		readText="";																									//@<BlockInfo>jp.vstone.block.variable,736,32,736,32,False,96,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*CRobotPose pose*/;																							//@<BlockInfo>jp.vstone.block.variable,800,32,800,32,False,95,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String time_string*/;																							//@<BlockInfo>jp.vstone.block.variable,864,32,864,32,False,94,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*LocalDateTime localDateTime*/;																				//@<BlockInfo>jp.vstone.block.variable,928,32,928,32,False,93,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*int getDateTimeElement*/;																						//@<BlockInfo>jp.vstone.block.variable,992,32,992,32,False,92,break@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>
	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void doScenario(String strMotionName)																		//@<BlockInfo>jp.vstone.block.func,2432,1344,3280,1344,False,122,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.talk.idling.stop,2496,1344,3216,1344,False,121,Idling動作を一時的に無効にします@</BlockInfo>
			boolean isIdling = GlobalVariable.sotawish.isPlayIdling();
			if(isIdling) GlobalVariable.sotawish.StopIdling();
			{


																														//@<OutputChild>
				pose = new CRobotPose();																					//@<BlockInfo>jp.vstone.block.pose,2560,1344,2560,1344,False,120,コメント@</BlockInfo>
				pose.SetPose(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{0,-900,0,900,0,0,0,0}
								);
				pose.SetTorque(	new Byte[]{1,2,3,4,5,6,7,8},
								new Short[]{100,100,100,100,100,100,100,100}
								);
				pose.SetLed(	new Byte[]{0,1,2,8,9,10,11,12,13},
								new Short[]{0,-255,0,180,80,0,180,80,0}
								);
				GlobalVariable.motion.play(pose,500);
				CRobotUtil.wait(500);																						//@<EndOfBlock/>
				switch((String)strMotionName)																				//@<BlockInfo>jp.vstone.block.switch,2640,1056,3136,1056,False,119,@</BlockInfo>
				{
					case (String)"Greeting":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Greeting ! -- 挨拶します");								//@<BlockInfo>jp.vstone.block.printlog,2736,1056,2736,1056,False,108,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"こんにちは。ようこそスタートアップサイドへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,2800,1056,2800,1056,False,107,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"Selfintroduction":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Introduction ! -- 自己紹介します");						//@<BlockInfo>jp.vstone.block.printlog,2736,1152,2736,1152,False,110,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"僕は、ソータっていうよ。この建物の案内をしているんだ。何か聞きたいことはあるかな？",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,2800,1152,2800,1152,False,109,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"VenueInfo":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"VenueInfo ! -- 建物の案内をします");							//@<BlockInfo>jp.vstone.block.printlog,2736,1248,2736,1248,False,112,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"建物の案内だね、任せてよ、。　9階はコワーキングスペースです。。。7階と8階には、会議室と、プライベートオフィスや、オンラインブースがあるよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,2800,1248,2800,1248,False,111,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"EventInfo":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"EventInfo ! -- イベント情報を発話します");						//@<BlockInfo>jp.vstone.block.printlog,2736,1344,2736,1344,False,117,@</BlockInfo>	@<EndOfBlock/>
						String[] info=GlobalVariable.recvString.split(";", -1);													//@<BlockInfo>jp.vstone.block.variable,2800,1344,2800,1344,False,116,break@</BlockInfo>
																																//@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"直近のイベントを紹介します。" + info[3] + "が、",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,2880,1344,2880,1344,False,115,イベント名@</BlockInfo>
																																//@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)info[4] + "予定です。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,2960,1344,2960,1344,False,114,日程@</BlockInfo>
																																//@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)info[5] + "で開催するよ。ご参加をお待ちしております。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,3040,1344,3040,1344,False,113,日程@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					default:
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"それは登録されてないよ");										//@<BlockInfo>jp.vstone.block.printlog,2768,1440,2768,1440,False,118,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
				
				}																											//@<EndOfBlock/>
																																//@</OutputChild>


			}
			if(isIdling) GlobalVariable.sotawish.StartIdling();
		}																												//@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

}
