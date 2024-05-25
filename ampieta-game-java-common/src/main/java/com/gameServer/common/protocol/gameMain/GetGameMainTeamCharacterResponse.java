package com.gameServer.common.protocol.gameMain;

import com.gameServer.common.protocol.character.GameMainTeamCharacterIDData;
import com.zfoo.net.packet.IPacket;
import com.zfoo.protocol.anno.Note;

import java.util.List;

/**
 * 进入游戏获取 自定义的 队伍list 和 已出战 队伍 Response
 *
 * @author zjy
 * @version 1.0
 * @since 2024/4/11 18 43
 */
@Note("1级的攻击属性")
public class GetGameMainTeamCharacterResponse implements IPacket {
    /**
     * 当前出战的队伍一般为4个人
     */
    @Note("当前出战的队伍一般为4个人")
    private List<GameMainTeamCharacterIDData> mainTeamFightCharacterIDData;
    /**
     * 第一支队伍
     */
    @Note("第一支队伍")
    private List<GameMainTeamCharacterIDData> mainTeamCharacterIDData1;
    /**
     * 第二支队伍
     */
    @Note("第二支队伍")
    private List<GameMainTeamCharacterIDData> mainTeamCharacterIDData2;
    /**
     * 第三支队伍
     */
    @Note("第三支队伍")
    private List<GameMainTeamCharacterIDData> mainTeamCharacterIDData3;

    public static GetGameMainTeamCharacterResponse valueOf() {
        return new GetGameMainTeamCharacterResponse();
    }

    public List<GameMainTeamCharacterIDData> getMainTeamFightCharacterIDData() {
        return mainTeamFightCharacterIDData;
    }

    public void setMainTeamFightCharacterIDData(List<GameMainTeamCharacterIDData> mainTeamFightCharacterIDData) {
        this.mainTeamFightCharacterIDData = mainTeamFightCharacterIDData;
    }

    public List<GameMainTeamCharacterIDData> getMainTeamCharacterIDData1() {
        return mainTeamCharacterIDData1;
    }

    public void setMainTeamCharacterIDData1(List<GameMainTeamCharacterIDData> mainTeamCharacterIDData1) {
        this.mainTeamCharacterIDData1 = mainTeamCharacterIDData1;
    }

    public List<GameMainTeamCharacterIDData> getMainTeamCharacterIDData2() {
        return mainTeamCharacterIDData2;
    }

    public void setMainTeamCharacterIDData2(List<GameMainTeamCharacterIDData> mainTeamCharacterIDData2) {
        this.mainTeamCharacterIDData2 = mainTeamCharacterIDData2;
    }

    public List<GameMainTeamCharacterIDData> getMainTeamCharacterIDData3() {
        return mainTeamCharacterIDData3;
    }

    public void setMainTeamCharacterIDData3(List<GameMainTeamCharacterIDData> mainTeamCharacterIDData3) {
        this.mainTeamCharacterIDData3 = mainTeamCharacterIDData3;
    }
}
