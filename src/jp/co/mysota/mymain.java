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
	public CPlayWave cplay;
	public String speechRecogResult;
	public RecogResult recogresult;
	public String readText;
	public CRobotPose pose;
	public String time_string;
	public LocalDateTime localDateTime;
	public int getDateTimeElement;
	public void net_main()																								//@<BlockInfo>jp.vstone.block.func,48,432,816,432,False,9,socketでメッセージを受け取る@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.thread,112,432,752,432,False,8,スレッド@</BlockInfo>
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						
						
																														//@<OutputChild>
						{																											//@<BlockInfo>jp.vstone.block.tcpip.server.init,176,432,688,432,False,7,@</BlockInfo>
							TCPIPServer tcpipServer = new TCPIPServer((short)5001,(int)5000);
							
																																	//@<OutputChild>
							while(net_loop==0)																						//@<BlockInfo>jp.vstone.block.while,240,432,624,432,False,6,TRUE@</BlockInfo>
							{
						
						
																																	//@<OutputChild>
								try{																								//@<BlockInfo>jp.vstone.block.tcpip.server,304,384,560,384,False,5,@</BlockInfo>
									GlobalVariable.recvString = tcpipServer.waitRequest();
						
									if(GlobalVariable.recvString==null) GlobalVariable.recvString="";
									if(GlobalVariable.recvString.contentEquals((String)"cmd;end"))
									{
																																	//@<OutputChild>
										net_loop=(short)1;																				//@<BlockInfo>jp.vstone.block.calculater,368,384,368,384,False,2,@</BlockInfo>
																																		//@<EndOfBlock/>
										CRobotUtil.wait((int)2000);																		//@<BlockInfo>jp.vstone.block.wait,496,384,496,384,False,1,コメント@</BlockInfo>	@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else
									{
																																	//@<OutputChild>
										recvMesg=(String)GlobalVariable.recvString;														//@<BlockInfo>jp.vstone.block.calculater,368,480,368,480,False,4,@</BlockInfo>
																																		//@<EndOfBlock/>
										CRobotUtil.Log(getClass().getSimpleName(), (String)recvMesg);									//@<BlockInfo>jp.vstone.block.printlog,432,480,432,480,False,3,@</BlockInfo>	@<EndOfBlock/>
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
	public void auto()																									//@<BlockInfo>jp.vstone.block.func,32,1472,1264,1472,False,30,コメント@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.thread,96,1472,1184,1472,False,29,スレッド@</BlockInfo>
			Thread th = new Thread(new Runnable() {
				@Override
				public void run() {
					try{
						
						
																														//@<OutputChild>
						while(net_loop==0)																							//@<BlockInfo>jp.vstone.block.while,160,1472,1104,1472,False,28,TRUE@</BlockInfo>
						{
						
						
																																	//@<OutputChild>
							if(isAuto==GlobalVariable.TRUE)																			//@<BlockInfo>jp.vstone.block.if,224,1424,1024,1424,False,27,コメント@</BlockInfo>
							{
																																	//@<OutputChild>
								if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();					//@<BlockInfo>jp.vstone.block.talk.idling2,304,1424,960,1424,False,24,Idling開始@</BlockInfo>
								try{
						
						
																																	//@<OutputChild>
									recogresult = GlobalVariable.recog.getRecognitionwithAbort((int)60000);							//@<BlockInfo>jp.vstone.block.talk.speechrecog.score2,400,992,880,992,False,23,音声認識を行い、認識候補との完全一致で比較する。認識スコアが一番高い結果に分岐する。実際に認識された文字列はspeechRecogResultに代入される@</BlockInfo>
									speechRecogResult = recogresult.CheckBest(new String[]{
									 "こんにちは" ,  "何ができる" ,  "名前は" ,  "ばいばい" ,  "" , 
									},false);
									if(speechRecogResult == null) speechRecogResult = "";
						
									if(speechRecogResult.contains((String)"こんにちは"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"こんにちは。ようこそイグニスへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,608,992,608,992,False,10,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"何ができる"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"この建物の案内ができるよ",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,608,1072,608,1072,False,11,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"名前は"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"僕はソータ、っていうよ。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,608,1152,608,1152,False,12,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else if(speechRecogResult.contains((String)"ばいばい"))
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											GlobalVariable.sotawish.Say((String)"ばいばい！またきてね！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,608,1232,608,1232,False,13,@</BlockInfo>
																																		//@<EndOfBlock/>
																																			//@</OutputChild>
						
									}
									else
									{
										speechRecogResult = recogresult.getBasicResult();
										if(speechRecogResult == null) speechRecogResult = "";
						
																																	//@<OutputChild>
											switch(GlobalVariable.random.nextInt((int)16))												//@<BlockInfo>jp.vstone.block.random,480,1392,800,1392,True,22,何か音が聞こえたとき 1/5 (20%) の確率でランダムな定型文を発話する@</BlockInfo>
											{
												case (int)0:
												{
																																		//@<OutputChild>
													dayOrNight();																			//@<BlockInfo>jp.vstone.block.callfunc.base,544,1392,544,1392,False,17,@</BlockInfo>	@<EndOfBlock/>
													if(isDay==true)																			//@<BlockInfo>jp.vstone.block.if,608,1344,736,1344,False,16,コメント@</BlockInfo>
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.Say((String)"こんにちは。ようこそイグニスへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,672,1344,672,1344,False,14,@</BlockInfo>
																																			//@<EndOfBlock/>
																																			//@</OutputChild>
													}
													else
													{
																																			//@<OutputChild>
														GlobalVariable.sotawish.Say((String)"こんばんは。ようこそイグニスへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,672,1440,672,1440,False,15,@</BlockInfo>
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
													GlobalVariable.sotawish.Say((String)"お仕事、お疲れさまです！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,624,1520,624,1520,False,18,@</BlockInfo>
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												case (int)2:
												{
																																		//@<OutputChild>
													GlobalVariable.sotawish.Say((String)"今、受付をリモートで対応しています！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,624,1600,624,1600,False,19,@</BlockInfo>
																																			//@<EndOfBlock/>
																																				//@</OutputChild>
													break;
												}
												default:
												{
																																		//@<OutputChild>
													CRobotUtil.Log(getClass().getSimpleName(), (String)"発話を見送るよ。");							//@<BlockInfo>jp.vstone.block.printlog,608,1680,608,1680,False,21,@</BlockInfo>	@<EndOfBlock/>
													GlobalVariable.sotawish.Say((String)"ふうん",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,672,1680,672,1680,False,20,@</BlockInfo>
																																			//@<EndOfBlock/>
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
								CRobotUtil.Log(getClass().getSimpleName(), (String)"auto状態待機");										//@<BlockInfo>jp.vstone.block.printlog,368,1744,368,1744,False,26,@</BlockInfo>	@<EndOfBlock/>
								CRobotUtil.wait((int)1000);																			//@<BlockInfo>jp.vstone.block.wait,896,1744,896,1744,False,25,コメント@</BlockInfo>	@<EndOfBlock/>
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
	public void main()																									//@<BlockInfo>jp.vstone.block.func,48,208,1008,208,False,40,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		net_main();																										//@<BlockInfo>jp.vstone.block.callfunc.base,112,208,112,208,False,39,@</BlockInfo>	@<EndOfBlock/>
		auto();																											//@<BlockInfo>jp.vstone.block.callfunc.base,176,208,176,208,False,38,@</BlockInfo>	@<EndOfBlock/>
		if(!GlobalVariable.sotawish.isPlayIdling()) GlobalVariable.sotawish.StartIdling();								//@<BlockInfo>jp.vstone.block.talk.idling2,240,208,944,208,False,37,Idling開始@</BlockInfo>
		try{


																														//@<OutputChild>
			while(net_loop==0)																							//@<BlockInfo>jp.vstone.block.while,304,208,880,208,False,36,TRUE@</BlockInfo>
			{


																														//@<OutputChild>
				if(recvMesg!="")																						//@<BlockInfo>jp.vstone.block.if,368,160,752,160,False,35,コメント@</BlockInfo>
				{
																														//@<OutputChild>
					String argString=recvMesg;																			//@<BlockInfo>jp.vstone.block.variable,432,160,432,160,False,33,break@</BlockInfo>
																														//@<EndOfBlock/>
					recvMesg=(String)"";																				//@<BlockInfo>jp.vstone.block.calculater,560,160,560,160,False,32,@</BlockInfo>
																														//@<EndOfBlock/>
					teleop((String)argString);																			//@<BlockInfo>jp.vstone.block.callfunc.base,688,160,688,160,False,31,@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>
				}
				else
				{
																														//@<OutputChild>
					CRobotUtil.wait((int)100);																			//@<BlockInfo>jp.vstone.block.wait,544,256,544,256,False,34,コメント@</BlockInfo>	@<EndOfBlock/>
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
	public void doMotion(String strMotionName)																			//@<BlockInfo>jp.vstone.block.func,32,2144,688,2144,False,63,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.talk.idling.stop,96,2144,608,2144,False,62,Idling動作を一時的に無効にします@</BlockInfo>
			boolean isIdling = GlobalVariable.sotawish.isPlayIdling();
			if(isIdling) GlobalVariable.sotawish.StopIdling();
			{


																														//@<OutputChild>
				pose = new CRobotPose();																					//@<BlockInfo>jp.vstone.block.pose,160,2144,160,2144,False,61,コメント@</BlockInfo>
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
				switch((String)strMotionName)																				//@<BlockInfo>jp.vstone.block.switch,240,1856,528,1856,False,60,@</BlockInfo>
				{
					case (String)"Nodding":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"HandNodding ! -- うなずきます");							//@<BlockInfo>jp.vstone.block.printlog,352,1856,352,1856,False,42,@</BlockInfo>	@<EndOfBlock/>
						behavior.head_nodding();																				//@<BlockInfo>jp.vstone.block.callfunc.base,416,1856,416,1856,False,41,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"HeadShaking":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"HandShaking ! -- いやいやします");							//@<BlockInfo>jp.vstone.block.printlog,352,1952,352,1952,False,44,@</BlockInfo>	@<EndOfBlock/>
						behavior.head_shaking();																				//@<BlockInfo>jp.vstone.block.callfunc.base,416,1952,416,1952,False,43,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"LeftHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"LeftHandWaving !");									//@<BlockInfo>jp.vstone.block.printlog,352,2048,352,2048,False,46,@</BlockInfo>	@<EndOfBlock/>
						behavior.lefthand_waving();																				//@<BlockInfo>jp.vstone.block.callfunc.base,416,2048,416,2048,False,45,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"RightHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"RightHandWaving ! -- 右手を振ります");						//@<BlockInfo>jp.vstone.block.printlog,352,2144,352,2144,False,48,@</BlockInfo>	@<EndOfBlock/>
						behavior.righthand_waving();																			//@<BlockInfo>jp.vstone.block.callfunc.base,416,2144,416,2144,False,47,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"BothHandWaving":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"BothHandWaving ! -- 両手を振ります");						//@<BlockInfo>jp.vstone.block.printlog,352,2240,352,2240,False,50,@</BlockInfo>	@<EndOfBlock/>
						behavior.bothhand_waving();																				//@<BlockInfo>jp.vstone.block.callfunc.base,416,2240,416,2240,False,49,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"BothHandRaising":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"BothHandRaising ! -- 両手を上下します");					//@<BlockInfo>jp.vstone.block.printlog,352,2336,352,2336,False,52,@</BlockInfo>	@<EndOfBlock/>
						behavior.bothhand_raising();																			//@<BlockInfo>jp.vstone.block.callfunc.base,416,2336,416,2336,False,51,@</BlockInfo>	@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"AutoStart":
					{
																															//@<OutputChild>
						isAuto=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,320,2432,320,2432,False,55,@</BlockInfo>
																																//@<EndOfBlock/>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"AutoStart ! -- 自動応答を始めます");							//@<BlockInfo>jp.vstone.block.printlog,384,2432,384,2432,False,54,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"オペレータさん、お疲れ様！ここからは僕に任せて！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,448,2432,448,2432,False,53,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"AutoStop":
					{
																															//@<OutputChild>
						isAuto=(boolean)false;																					//@<BlockInfo>jp.vstone.block.calculater,320,2528,320,2528,False,58,@</BlockInfo>
																																//@<EndOfBlock/>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"AutoStop ! -- 自動応答を終了します");							//@<BlockInfo>jp.vstone.block.printlog,384,2528,384,2528,False,57,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"オペレータさんが来てくれたよ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,448,2528,448,2528,False,56,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					default:
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"それは登録されてないわー");										//@<BlockInfo>jp.vstone.block.printlog,384,2624,384,2624,False,59,@</BlockInfo>	@<EndOfBlock/>
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
	public void doScenario(String strMotionName)																		//@<BlockInfo>jp.vstone.block.func,848,2144,1488,2144,False,74,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		{																												//@<BlockInfo>jp.vstone.block.talk.idling.stop,912,2144,1424,2144,False,73,Idling動作を一時的に無効にします@</BlockInfo>
			boolean isIdling = GlobalVariable.sotawish.isPlayIdling();
			if(isIdling) GlobalVariable.sotawish.StopIdling();
			{


																														//@<OutputChild>
				pose = new CRobotPose();																					//@<BlockInfo>jp.vstone.block.pose,976,2144,976,2144,False,72,コメント@</BlockInfo>
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
				switch((String)strMotionName)																				//@<BlockInfo>jp.vstone.block.switch,1056,1856,1344,1856,False,71,@</BlockInfo>
				{
					case (String)"Greeting":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Greeting ! -- 挨拶します");								//@<BlockInfo>jp.vstone.block.printlog,1152,1856,1152,1856,False,65,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"こんにちは。ようこそイグニスへ！",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,1216,1856,1216,1856,False,64,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"Preset1":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Preset1 ! -- 定型文発話します");							//@<BlockInfo>jp.vstone.block.printlog,1152,1952,1152,1952,False,67,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"僕は、ソータっていうよ。この建物の案内をているよ。何か聞きたいことはあるかな？",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,1216,1952,1216,1952,False,66,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					case (String)"Preset2":
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"Preset2 ! -- 定型文発話します");							//@<BlockInfo>jp.vstone.block.printlog,1152,2048,1152,2048,False,69,@</BlockInfo>	@<EndOfBlock/>
						GlobalVariable.sotawish.Say((String)"うーん、難しい質問だね。スタッフに聞いてみるから、ちょっと待ってね。",MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);	//@<BlockInfo>jp.vstone.block.talk.say,1216,2048,1216,2048,False,68,@</BlockInfo>
																																//@<EndOfBlock/>
																																	//@</OutputChild>
						break;
					}
					default:
					{
																															//@<OutputChild>
						CRobotUtil.Log(getClass().getSimpleName(), (String)"それは登録されてないわー");										//@<BlockInfo>jp.vstone.block.printlog,1184,2144,1184,2144,False,70,@</BlockInfo>	@<EndOfBlock/>
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
	public void teleop(String cmdMesg)																					//@<BlockInfo>jp.vstone.block.func,48,736,976,736,False,90,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		String[] cmdArray=cmdMesg.split(";",-1);																		//@<BlockInfo>jp.vstone.block.variable,112,736,112,736,False,89,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isCmd=false;																							//@<BlockInfo>jp.vstone.block.variable,176,736,176,736,False,88,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isMotion=false;																							//@<BlockInfo>jp.vstone.block.variable,240,736,240,736,False,87,break@</BlockInfo>
																														//@<EndOfBlock/>
		boolean isScenario=false;																						//@<BlockInfo>jp.vstone.block.variable,304,736,304,736,False,86,break@</BlockInfo>
																														//@<EndOfBlock/>
		for(int i=0;i<(int)cmdArray.length;i++)																			//@<BlockInfo>jp.vstone.block.for,368,736,912,736,False,85,コメント@</BlockInfo>
		{
																														//@<OutputChild>
			CRobotUtil.Log(getClass().getSimpleName(), (String)"検索対象" + cmdArray[i]);									//@<BlockInfo>jp.vstone.block.printlog,432,736,432,736,False,84,@</BlockInfo>	@<EndOfBlock/>
			{																											//@<BlockInfo>jp.vstone.block.text.regex,496,592,784,592,False,83,textに設定した文字列を正規表現でマッチングする。@</BlockInfo>
				String text = (String)cmdArray[i];
				if(text.matches((String)"cmd"))
				{
				
																														//@<OutputChild>
					isCmd=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,640,592,640,592,False,75,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else if(text.matches((String)"motion"))
				{
				
																														//@<OutputChild>
					isMotion=(boolean)true;																					//@<BlockInfo>jp.vstone.block.calculater,576,688,576,688,False,78,@</BlockInfo>
																															//@<EndOfBlock/>
					doMotion((String)cmdArray[i+1]);																		//@<BlockInfo>jp.vstone.block.callfunc.base,640,688,640,688,False,77,@</BlockInfo>	@<EndOfBlock/>
					i=(int)i + 1;																							//@<BlockInfo>jp.vstone.block.calculater,704,688,704,688,False,76,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else if(text.matches((String)"scenario"))
				{
				
																														//@<OutputChild>
					isScenario=true;																						//@<BlockInfo>jp.vstone.block.calculater,576,784,576,784,False,81,@</BlockInfo>
																															//@<EndOfBlock/>
					doScenario((String)cmdArray[i+1]);																		//@<BlockInfo>jp.vstone.block.callfunc.base,640,784,640,784,False,80,@</BlockInfo>	@<EndOfBlock/>
					i=(int)i + 1;																							//@<BlockInfo>jp.vstone.block.calculater,704,784,704,784,False,79,@</BlockInfo>
																															//@<EndOfBlock/>
																																//@</OutputChild>

				}
				else
				{
				
																														//@<OutputChild>
																														//@</OutputChild>

				}
				
			}																											//@<EndOfBlock/>
			if(isCmd==false && i==0){																					//@<BlockInfo>jp.vstone.block.freeproc,848,736,848,736,False,82,@</BlockInfo>
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
	public mymain()																										//@<BlockInfo>jp.vstone.block.func.constructor,48,32,960,32,False,104,@</BlockInfo>
	{
																														//@<OutputChild>
		net_loop=0;																										//@<BlockInfo>jp.vstone.block.variable,112,32,112,32,False,103,break@</BlockInfo>
																														//@<EndOfBlock/>
		recvMesg="";																									//@<BlockInfo>jp.vstone.block.variable,176,32,176,32,False,102,break@</BlockInfo>
																														//@<EndOfBlock/>
		isAuto=false;																									//@<BlockInfo>jp.vstone.block.variable,240,32,240,32,False,101,break@</BlockInfo>
																														//@<EndOfBlock/>
		behavior=new jp.co.mysota.behavior();																			//@<BlockInfo>jp.vstone.block.variable,304,32,304,32,False,100,break@</BlockInfo>
																														//@<EndOfBlock/>
		isDay=true;																										//@<BlockInfo>jp.vstone.block.variable,368,32,368,32,False,99,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*CPlayWave cplay*/;																							//@<BlockInfo>jp.vstone.block.variable,448,32,448,32,False,98,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String speechRecogResult*/;																					//@<BlockInfo>jp.vstone.block.variable,512,32,512,32,False,97,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*RecogResult recogresult*/;																					//@<BlockInfo>jp.vstone.block.variable,576,32,576,32,False,96,break@</BlockInfo>
																														//@<EndOfBlock/>
		readText="";																									//@<BlockInfo>jp.vstone.block.variable,640,32,640,32,False,95,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*CRobotPose pose*/;																							//@<BlockInfo>jp.vstone.block.variable,704,32,704,32,False,94,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*String time_string*/;																							//@<BlockInfo>jp.vstone.block.variable,768,32,768,32,False,93,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*LocalDateTime localDateTime*/;																				//@<BlockInfo>jp.vstone.block.variable,832,32,832,32,False,92,break@</BlockInfo>
																														//@<EndOfBlock/>
		/*int getDateTimeElement*/;																						//@<BlockInfo>jp.vstone.block.variable,896,32,896,32,False,91,break@</BlockInfo>
																														//@<EndOfBlock/>
																														//@</OutputChild>
	}																													//@<EndOfBlock/>

	//@<Separate/>
	public void dayOrNight()																							//@<BlockInfo>jp.vstone.block.func,864,2320,1280,2320,False,110,@</BlockInfo>
	throws SpeechRecogAbortException {
		if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");

																														//@<OutputChild>
		localDateTime = LocalDateTime.now();																			//@<BlockInfo>jp.vstone.block.time.getlocaldatetime,928,2320,928,2320,False,109,ローカル時間をロボット内のカレンダーより取得し、変数LocalDateTime lodalDateTimeに代入。取得した情報から日時などを個別に切り出す場合は「日時の切り出しブロック」を使う@</BlockInfo>
																														//@<EndOfBlock/>
		{																												//@<BlockInfo>jp.vstone.block.time.getdatetimeelement,992,2320,992,2320,False,108,dateTimeに記録された日時情報から、年・月・日・時・分・秒のうち一つをtypeで指定し、変数int getDateTimeElementに代入する@</BlockInfo>
			LocalDateTime d = (LocalDateTime)localDateTime;
			getDateTimeElement = d.getHour();
		}																												//@<EndOfBlock/>
		if(getDateTimeElement >=0 && getDateTimeElement <17){															//@<BlockInfo>jp.vstone.block.freeproc,1072,2320,1072,2320,False,107,@</BlockInfo>
			isDay = true;
			System.out.println("昼です" + getDateTimeElement);
		}
		else if(getDateTimeElement>=17 && getDateTimeElement<24){
			isDay = false;
			System.out.println("夜です" + getDateTimeElement);
		}
																														//@<EndOfBlock/>
		CRobotUtil.Log(getClass().getSimpleName(), (String)"現在の時刻（Hour）は....");											//@<BlockInfo>jp.vstone.block.printlog,1136,2320,1136,2320,False,106,@</BlockInfo>	@<EndOfBlock/>
		CRobotUtil.Log(getClass().getSimpleName(), (String)time_string);												//@<BlockInfo>jp.vstone.block.printlog,1200,2320,1200,2320,False,105,@</BlockInfo>	@<EndOfBlock/>
																														//@</OutputChild>

	}																													//@<EndOfBlock/>

}
