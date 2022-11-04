import ObjectA from './ObjectA';

// 复杂的对象
// 包括了各种复杂的结构，数组，List，Set，Map
//
// @author godotg
// @version 3.0
class ComplexObject {

    // byte类型，最简单的整形
    a: number = 0;
    // byte的包装类型
    // 优先使用基础类型，包装类型会有装箱拆箱
    aa: number = 0;
    // 数组类型
    aaa: Array<number> | null = null;
    aaaa: Array<number> | null = null;
    b: number = 0;
    bb: number = 0;
    bbb: Array<number> | null = null;
    bbbb: Array<number> | null = null;
    c: number = 0;
    cc: number = 0;
    ccc: Array<number> | null = null;
    cccc: Array<number> | null = null;
    d: number = 0;
    dd: number = 0;
    ddd: Array<number> | null = null;
    dddd: Array<number> | null = null;
    e: number = 0;
    ee: number = 0;
    eee: Array<number> | null = null;
    eeee: Array<number> | null = null;
    f: number = 0;
    ff: number = 0;
    fff: Array<number> | null = null;
    ffff: Array<number> | null = null;
    g: boolean = false;
    gg: boolean = false;
    ggg: Array<boolean> | null = null;
    gggg: Array<boolean> | null = null;
    h: string = '';
    hh: string = '';
    hhh: Array<string> | null = null;
    hhhh: Array<string> | null = null;
    jj: string = '';
    jjj: Array<string> | null = null;
    kk: ObjectA | null = null;
    kkk: Array<ObjectA> | null = null;
    l: Array<number> | null = null;
    ll: Array<Array<Array<number>>> | null = null;
    lll: Array<Array<ObjectA>> | null = null;
    llll: Array<string> | null = null;
    lllll: Array<Map<number, string>> | null = null;
    m: Map<number, string> | null = null;
    mm: Map<number, ObjectA> | null = null;
    mmm: Map<ObjectA, Array<number>> | null = null;
    mmmm: Map<Array<Array<ObjectA>>, Array<Array<Array<number>>>> | null = null;
    mmmmm: Map<Array<Map<number, string>>, Set<Map<number, string>>> | null = null;
    s: Set<number> | null = null;
    ss: Set<Set<Array<number>>> | null = null;
    sss: Set<Set<ObjectA>> | null = null;
    ssss: Set<string> | null = null;
    sssss: Set<Map<number, string>> | null = null;
    // 如果要修改协议并且兼容老协议，需要加上Compatible注解，按照增加的顺序添加order
    myCompatible: number = 0;
    myObject: ObjectA | null = null;

    protocolId(): number {
        return 100;
    }

    static write(buffer: any, packet: ComplexObject | null) {
        if (buffer.writePacketFlag(packet)) {
            return;
        }
        if (packet === null) {
            return;
        }

        buffer.writeByte(packet.a);
        buffer.writeByte(packet.aa);
        buffer.writeByteArray(packet.aaa);
        buffer.writeByteArray(packet.aaaa);
        buffer.writeShort(packet.b);
        buffer.writeShort(packet.bb);
        buffer.writeShortArray(packet.bbb);
        buffer.writeShortArray(packet.bbbb);
        buffer.writeInt(packet.c);
        buffer.writeInt(packet.cc);
        buffer.writeIntArray(packet.ccc);
        buffer.writeIntArray(packet.cccc);
        buffer.writeLong(packet.d);
        buffer.writeLong(packet.dd);
        buffer.writeLongArray(packet.ddd);
        buffer.writeLongArray(packet.dddd);
        buffer.writeFloat(packet.e);
        buffer.writeFloat(packet.ee);
        buffer.writeFloatArray(packet.eee);
        buffer.writeFloatArray(packet.eeee);
        buffer.writeDouble(packet.f);
        buffer.writeDouble(packet.ff);
        buffer.writeDoubleArray(packet.fff);
        buffer.writeDoubleArray(packet.ffff);
        buffer.writeBoolean(packet.g);
        buffer.writeBoolean(packet.gg);
        buffer.writeBooleanArray(packet.ggg);
        buffer.writeBooleanArray(packet.gggg);
        buffer.writeChar(packet.h);
        buffer.writeChar(packet.hh);
        buffer.writeCharArray(packet.hhh);
        buffer.writeCharArray(packet.hhhh);
        buffer.writeString(packet.jj);
        buffer.writeStringArray(packet.jjj);
        buffer.writePacket(packet.kk, 102);
        buffer.writePacketArray(packet.kkk, 102);
        buffer.writeIntList(packet.l);
        if (packet.ll === null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(packet.ll.length);
            packet.ll.forEach(element0 => {
                if (element0 === null) {
                    buffer.writeInt(0);
                } else {
                    buffer.writeInt(element0.length);
                    element0.forEach(element1 => {
                        buffer.writeIntList(element1);
                    });
                }
            });
        }
        if (packet.lll === null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(packet.lll.length);
            packet.lll.forEach(element2 => {
                buffer.writePacketList(element2, 102);
            });
        }
        buffer.writeStringList(packet.llll);
        if (packet.lllll === null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(packet.lllll.length);
            packet.lllll.forEach(element3 => {
                buffer.writeIntStringMap(element3);
            });
        }
        buffer.writeIntStringMap(packet.m);
        buffer.writeIntPacketMap(packet.mm, 102);
        if (packet.mmm === null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(packet.mmm.size);
            packet.mmm.forEach((value5, key4) => {
                buffer.writePacket(key4, 102);
                buffer.writeIntList(value5);
            });
        }
        if (packet.mmmm === null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(packet.mmmm.size);
            packet.mmmm.forEach((value7, key6) => {
                if (key6 === null) {
                    buffer.writeInt(0);
                } else {
                    buffer.writeInt(key6.length);
                    key6.forEach(element8 => {
                        buffer.writePacketList(element8, 102);
                    });
                }
                if (value7 === null) {
                    buffer.writeInt(0);
                } else {
                    buffer.writeInt(value7.length);
                    value7.forEach(element9 => {
                        if (element9 === null) {
                            buffer.writeInt(0);
                        } else {
                            buffer.writeInt(element9.length);
                            element9.forEach(element10 => {
                                buffer.writeIntList(element10);
                            });
                        }
                    });
                }
            });
        }
        if (packet.mmmmm === null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(packet.mmmmm.size);
            packet.mmmmm.forEach((value12, key11) => {
                if (key11 === null) {
                    buffer.writeInt(0);
                } else {
                    buffer.writeInt(key11.length);
                    key11.forEach(element13 => {
                        buffer.writeIntStringMap(element13);
                    });
                }
                if (value12 === null) {
                    buffer.writeInt(0);
                } else {
                    buffer.writeInt(value12.size);
                    value12.forEach(element14 => {
                        buffer.writeIntStringMap(element14);
                    });
                }
            });
        }
        buffer.writeIntSet(packet.s);
        if (packet.ss === null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(packet.ss.size);
            packet.ss.forEach(element15 => {
                if (element15 === null) {
                    buffer.writeInt(0);
                } else {
                    buffer.writeInt(element15.size);
                    element15.forEach(element16 => {
                        buffer.writeIntList(element16);
                    });
                }
            });
        }
        if (packet.sss === null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(packet.sss.size);
            packet.sss.forEach(element17 => {
                buffer.writePacketSet(element17, 102);
            });
        }
        buffer.writeStringSet(packet.ssss);
        if (packet.sssss === null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(packet.sssss.size);
            packet.sssss.forEach(element18 => {
                buffer.writeIntStringMap(element18);
            });
        }
        buffer.writeInt(packet.myCompatible);
        buffer.writePacket(packet.myObject, 102);
    }

    static read(buffer: any): ComplexObject | null {
        if (!buffer.readBoolean()) {
            return null;
        }
        const packet = new ComplexObject();
        const result19 = buffer.readByte();
        packet.a = result19;
        const result20 = buffer.readByte();
        packet.aa = result20;
        const array21 = buffer.readByteArray();
        packet.aaa = array21;
        const array22 = buffer.readByteArray();
        packet.aaaa = array22;
        const result23 = buffer.readShort();
        packet.b = result23;
        const result24 = buffer.readShort();
        packet.bb = result24;
        const array25 = buffer.readShortArray();
        packet.bbb = array25;
        const array26 = buffer.readShortArray();
        packet.bbbb = array26;
        const result27 = buffer.readInt();
        packet.c = result27;
        const result28 = buffer.readInt();
        packet.cc = result28;
        const array29 = buffer.readIntArray();
        packet.ccc = array29;
        const array30 = buffer.readIntArray();
        packet.cccc = array30;
        const result31 = buffer.readLong();
        packet.d = result31;
        const result32 = buffer.readLong();
        packet.dd = result32;
        const array33 = buffer.readLongArray();
        packet.ddd = array33;
        const array34 = buffer.readLongArray();
        packet.dddd = array34;
        const result35 = buffer.readFloat();
        packet.e = result35;
        const result36 = buffer.readFloat();
        packet.ee = result36;
        const array37 = buffer.readFloatArray();
        packet.eee = array37;
        const array38 = buffer.readFloatArray();
        packet.eeee = array38;
        const result39 = buffer.readDouble();
        packet.f = result39;
        const result40 = buffer.readDouble();
        packet.ff = result40;
        const array41 = buffer.readDoubleArray();
        packet.fff = array41;
        const array42 = buffer.readDoubleArray();
        packet.ffff = array42;
        const result43 = buffer.readBoolean(); 
        packet.g = result43;
        const result44 = buffer.readBoolean(); 
        packet.gg = result44;
        const array45 = buffer.readBooleanArray();
        packet.ggg = array45;
        const array46 = buffer.readBooleanArray();
        packet.gggg = array46;
        const result47 = buffer.readChar();
        packet.h = result47;
        const result48 = buffer.readChar();
        packet.hh = result48;
        const array49 = buffer.readCharArray();
        packet.hhh = array49;
        const array50 = buffer.readCharArray();
        packet.hhhh = array50;
        const result51 = buffer.readString();
        packet.jj = result51;
        const array52 = buffer.readStringArray();
        packet.jjj = array52;
        const result53 = buffer.readPacket(102);
        packet.kk = result53;
        const array54 = buffer.readPacketArray(102);
        packet.kkk = array54;
        const list55 = buffer.readIntList();
        packet.l = list55;
        const result56 = new Array<Array<Array<number>>>();
        const size57 = buffer.readInt();
        if (size57 > 0) {
            for (let index58 = 0; index58 < size57; index58++) {
                const result59 = new Array<Array<number>>();
                const size60 = buffer.readInt();
                if (size60 > 0) {
                    for (let index61 = 0; index61 < size60; index61++) {
                        const list62 = buffer.readIntList();
                        result59.push(list62);
                    }
                }
                result56.push(result59);
            }
        }
        packet.ll = result56;
        const result63 = new Array<Array<ObjectA>>();
        const size64 = buffer.readInt();
        if (size64 > 0) {
            for (let index65 = 0; index65 < size64; index65++) {
                const list66 = buffer.readPacketList(102);
                result63.push(list66);
            }
        }
        packet.lll = result63;
        const list67 = buffer.readStringList();
        packet.llll = list67;
        const result68 = new Array<Map<number, string>>();
        const size69 = buffer.readInt();
        if (size69 > 0) {
            for (let index70 = 0; index70 < size69; index70++) {
                const map71 = buffer.readIntStringMap();
                result68.push(map71);
            }
        }
        packet.lllll = result68;
        const map72 = buffer.readIntStringMap();
        packet.m = map72;
        const map73 = buffer.readIntPacketMap(102);
        packet.mm = map73;
        const result74 = new Map<ObjectA, Array<number>>();
        const size75 = buffer.readInt();
        if (size75 > 0) {
            for (let index76 = 0; index76 < size75; index76++) {
                const result77 = buffer.readPacket(102);
                const list78 = buffer.readIntList();
                result74.set(result77, list78);
            }
        }
        packet.mmm = result74;
        const result79 = new Map<Array<Array<ObjectA>>, Array<Array<Array<number>>>>();
        const size80 = buffer.readInt();
        if (size80 > 0) {
            for (let index81 = 0; index81 < size80; index81++) {
                const result82 = new Array<Array<ObjectA>>();
                const size83 = buffer.readInt();
                if (size83 > 0) {
                    for (let index84 = 0; index84 < size83; index84++) {
                        const list85 = buffer.readPacketList(102);
                        result82.push(list85);
                    }
                }
                const result86 = new Array<Array<Array<number>>>();
                const size87 = buffer.readInt();
                if (size87 > 0) {
                    for (let index88 = 0; index88 < size87; index88++) {
                        const result89 = new Array<Array<number>>();
                        const size90 = buffer.readInt();
                        if (size90 > 0) {
                            for (let index91 = 0; index91 < size90; index91++) {
                                const list92 = buffer.readIntList();
                                result89.push(list92);
                            }
                        }
                        result86.push(result89);
                    }
                }
                result79.set(result82, result86);
            }
        }
        packet.mmmm = result79;
        const result93 = new Map<Array<Map<number, string>>, Set<Map<number, string>>>();
        const size94 = buffer.readInt();
        if (size94 > 0) {
            for (let index95 = 0; index95 < size94; index95++) {
                const result96 = new Array<Map<number, string>>();
                const size97 = buffer.readInt();
                if (size97 > 0) {
                    for (let index98 = 0; index98 < size97; index98++) {
                        const map99 = buffer.readIntStringMap();
                        result96.push(map99);
                    }
                }
                const result100 = new Set<Map<number, string>>();
                const size101 = buffer.readInt();
                if (size101 > 0) {
                    for (let index102 = 0; index102 < size101; index102++) {
                        const map103 = buffer.readIntStringMap();
                        result100.add(map103);
                    }
                }
                result93.set(result96, result100);
            }
        }
        packet.mmmmm = result93;
        const set104 = buffer.readIntSet();
        packet.s = set104;
        const result105 = new Set<Set<Array<number>>>();
        const size106 = buffer.readInt();
        if (size106 > 0) {
            for (let index107 = 0; index107 < size106; index107++) {
                const result108 = new Set<Array<number>>();
                const size109 = buffer.readInt();
                if (size109 > 0) {
                    for (let index110 = 0; index110 < size109; index110++) {
                        const list111 = buffer.readIntList();
                        result108.add(list111);
                    }
                }
                result105.add(result108);
            }
        }
        packet.ss = result105;
        const result112 = new Set<Set<ObjectA>>();
        const size113 = buffer.readInt();
        if (size113 > 0) {
            for (let index114 = 0; index114 < size113; index114++) {
                const set115 = buffer.readPacketSet(102);
                result112.add(set115);
            }
        }
        packet.sss = result112;
        const set116 = buffer.readStringSet();
        packet.ssss = set116;
        const result117 = new Set<Map<number, string>>();
        const size118 = buffer.readInt();
        if (size118 > 0) {
            for (let index119 = 0; index119 < size118; index119++) {
                const map120 = buffer.readIntStringMap();
                result117.add(map120);
            }
        }
        packet.sssss = result117;
        if (!buffer.isReadable()) {
            return packet;
        }
        const result121 = buffer.readInt();
        packet.myCompatible = result121;
        if (!buffer.isReadable()) {
            return packet;
        }
        const result122 = buffer.readPacket(102);
        packet.myObject = result122;
        return packet;
    }
}

export default ComplexObject;
