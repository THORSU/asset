package asset.service.impl;

import asset.mapper.UnitMapper;
import asset.pojo.Unit;
import asset.service.IUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Author:QuincySu
 * Date:Created in 2018/3/22
 */
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
}
