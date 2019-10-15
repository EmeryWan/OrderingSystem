package pers.emery.utils;

import pers.emery.enums.ResultEnum;
import pers.emery.vo.ResultVO;

/**
 * @author emery
 */
public class ResultVOUtil {

    public static ResultVO success(Object object, String msg) {
        ResultVO<Object> resultVO = new ResultVO<>();

        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setMsg(msg);
        resultVO.setData(object);

        return resultVO;
    }

    public static ResultVO success(Object object) {
        return success(object, "成功");
    }

    public static ResultVO success(String msg) {
        return success(null, msg);
    }

    public static ResultVO success() {
        return success(null, null);
    }

    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();

        resultVO.setCode(code);
        resultVO.setMsg(msg);

        return resultVO;
    }

}
