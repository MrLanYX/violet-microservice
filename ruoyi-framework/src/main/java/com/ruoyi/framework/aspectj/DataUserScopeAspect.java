package com.ruoyi.framework.aspectj;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.annotation.DataUserScope;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Administrator
 * @description: TODO
 * @date 2021/7/5 0005 16:52
 */
@Aspect
@Component
public class DataUserScopeAspect {

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_USER_SCOPE = "dataUserScope";

    @Pointcut("@annotation(com.ruoyi.common.annotation.DataUserScope)")
    public void dataUserScopePointCut(){

    }

    @Before("dataUserScopePointCut()")
    public void doBefore(JoinPoint point) throws Throwable
    {
        handleDataUserScope(point);
    }

    protected void handleDataUserScope(final JoinPoint joinPoint)
    {
        // 获得注解
        DataUserScope dataUserScope = getAnnotationLog(joinPoint);
        if (dataUserScope == null)
        {
            return;
        }
        // 获取当前的用户
        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
        if (StringUtils.isNotNull(loginUser))
        {
            SysUser currentUser = loginUser.getUser();
            dataScopeFilter(joinPoint, currentUser, dataUserScope.value());
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private DataUserScope getAnnotationLog(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null)
        {
            return method.getAnnotation(DataUserScope.class);
        }
        return null;
    }

    /**
     * 数据范围过滤
     *
     * @param joinPoint 切点
     * @param userAlias 用户id
     */
    public static void dataScopeFilter(JoinPoint joinPoint, SysUser user, String userAlias)
    {
        StringBuilder sqlString = new StringBuilder();
        sqlString.append(StringUtils.format(
                "user_id = {}  ",user.getUserId()));

        if (StringUtils.isNotBlank(sqlString.toString()))
        {
            Object params = joinPoint.getArgs()[0];
            if (StringUtils.isNotNull(params) && params instanceof BaseEntity)
            {
                BaseEntity baseEntity = (BaseEntity) params;
                baseEntity.getParams().put(DATA_USER_SCOPE, " AND " + sqlString);
            }
        }
    }
}
