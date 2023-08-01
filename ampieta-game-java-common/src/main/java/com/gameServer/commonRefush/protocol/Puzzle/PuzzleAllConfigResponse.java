package com.gameServer.commonRefush.protocol.Puzzle;

import com.zfoo.protocol.IPacket;
import com.zfoo.protocol.registration.anno.Protocol;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zjy
 * @version 1.0
 * @since 2023/7/31 23 58
 */
@Protocol(id = 1036)
public class PuzzleAllConfigResponse implements IPacket {
    /**
     * 地图配置
     */
    private List<Puzzle> puzzleConfigList= new ArrayList<>();
    
    public static PuzzleAllConfigResponse ValueOf(){
        var packet=new PuzzleAllConfigResponse();
        packet.puzzleConfigList=new ArrayList<>();
        return packet;
    }
    public void setPuzzleConfigList(List<Puzzle> list){
        puzzleConfigList=list;
    }
    public List<Puzzle> getPuzzleConfigList(){
        return puzzleConfigList;
    }
}
