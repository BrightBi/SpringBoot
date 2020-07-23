package com.bright.learn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bright.learn.domain.ErrorInfo;
import com.bright.learn.util.MyException;
/*
 * @ControllerAdvice 定义统一的异常处理类
 * @ExceptionHandler 用来定义函数针对的异常类型，最后将 Exception 对象和请求 URL 映射到 error.html 中
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "error";

	// 针对 Exception 的全局处理
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	// 针对 NullPointerException 的全局处理
	@ExceptionHandler(value = NullPointerException.class)
	public ModelAndView defaultNullPointerExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.addObject("comments", "NullPointerException");
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	// 针对 IllegalArgumentException 的全局处理
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ModelAndView defaultIllegalArgumentExceptionHandler(HttpServletRequest req, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.addObject("url", req.getRequestURL());
		mav.addObject("comments", "IllegalArgumentException");
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}

	// 针对 MyException 返回 json 数据的全局处理
	@ExceptionHandler(value = MyException.class)
	@ResponseBody
	public ErrorInfo<String> defaultMyExceptionHandler(HttpServletRequest req, MyException e) throws Exception {
		ErrorInfo<String> r = new ErrorInfo<>();
		r.setMessage(e.getMessage());
		r.setCode(ErrorInfo.ERROR);
		r.setData("Some Data");
		r.setUrl(req.getRequestURL().toString());
		return r;
	}
}
