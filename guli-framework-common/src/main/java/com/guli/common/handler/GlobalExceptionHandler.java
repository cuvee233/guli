package com.guli.common.handler;

import com.guli.common.constants.ResultCodeEnum;
import com.guli.common.constants.ResultMessage;
import com.guli.common.util.ExceptionUtil;
import com.guli.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * 统一异常处理类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public R error(Exception e){
		e.printStackTrace();
		return R.error();
	}

	/**
	 * sql语法错误异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BadSqlGrammarException.class)
	@ResponseBody
	public R sqlError(BadSqlGrammarException e) {
		e.printStackTrace();
		log.error(ExceptionUtil.getMessage(e));
		return R.setResult(ResultCodeEnum.BAD_SQL_GRAMMAR);
	}

	@ExceptionHandler(SQLException.class)
	@ResponseBody
	public R sqlError(SQLException e) {
		e.printStackTrace();
		log.error(ExceptionUtil.getMessage(e));
		return R.setResult(ResultCodeEnum.SQL_EXCEPTION);
	}
	/**
	 * json解析错误
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public R jsonError(HttpMessageNotReadableException e) {
		e.printStackTrace();
		log.error(ExceptionUtil.getMessage(e));
		return R.setResult(ResultCodeEnum.JSON_PARSE_EXCEPTION);
	}
}