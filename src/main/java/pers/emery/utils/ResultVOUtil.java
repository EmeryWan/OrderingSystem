package pers.emery.utils;

import pers.emery.enums.ResultEnum;
import pers.emery.vo.ResultVO;

/**
 * @author emery
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO<Object> resultVO = new ResultVO<>();

        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg("成功");
        resultVO.setData(object);

        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();

        resultVO.setCode(code);
        resultVO.setMsg(msg);

        return resultVO;
    }

}
