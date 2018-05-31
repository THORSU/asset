package asset.service;

import asset.pojo.Unit;

import java.util.List;

public interface IUnitService {
    //查询单位号
    public Unit getUnitId(Unit unit);
    //查询单位名
    public Unit getUnitName(Unit unit);
    //增加单位
    public int addUnit(Unit unit);
    //删除单位
    public int delUnit(Unit unit);

    //查看单位信息
    public List<Unit> getUnitList();
}
