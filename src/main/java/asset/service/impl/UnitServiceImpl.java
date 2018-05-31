package asset.service.impl;

import asset.mapper.UnitMapper;
import asset.pojo.Unit;
import asset.service.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements IUnitService {
    @Autowired
    private UnitMapper unitMapper;
    @Override
    public Unit getUnitId(Unit unit) {
        return unitMapper.getUnitId(unit);
    }

    @Override
    public Unit getUnitName(Unit unit) {
        return unitMapper.getUnitName(unit);
    }

    @Override
    public int addUnit(Unit unit) {
        return unitMapper.addUnit(unit);
    }

    @Override
    public int delUnit(Unit unit) {
        return unitMapper.delUnit(unit);
    }

    @Override
    public List<Unit> getUnitList() {
        return unitMapper.getUnitList();
    }
}
