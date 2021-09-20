package cn.lynntech.magicapi.interceptor;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.security.service.TokenService;
import com.ruoyi.system.api.RemoteLoginService;
import com.ruoyi.system.api.model.LoginBody;
import com.ruoyi.system.api.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.ssssssss.magicapi.exception.MagicLoginException;
import org.ssssssss.magicapi.interceptor.Authorization;
import org.ssssssss.magicapi.interceptor.AuthorizationInterceptor;
import org.ssssssss.magicapi.interceptor.MagicUser;
import org.ssssssss.magicapi.model.ApiInfo;
import org.ssssssss.magicapi.model.DataSourceInfo;
import org.ssssssss.magicapi.model.FunctionInfo;
import org.ssssssss.magicapi.model.Group;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 自定义UI界面鉴权
 * https://ssssssss.org/guide/custom/authorization.html
 * @see AuthorizationInterceptor
 */
//@Component  //注入到Spring容器中
public class CustomUIAuthorizationInterceptor implements AuthorizationInterceptor {

	//@Autowired
	//private LoginService loginService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private RemoteLoginService loginService;

	//@Autowired
	//private IMagicUserFileService magicUserFileService;

	/**
	 * 配置UI是否需要登录
	 */
	@Override
	public boolean requireLogin() {
		return true;
	}

	/**
	 * 自定义登录方法
	 *
	 * @param username 用户名
	 * @param password 密码
	 */
	@Override
	public MagicUser login(String username, String password) throws MagicLoginException {
		try{
			LoginBody loginForm = new LoginBody();
			loginForm.setUsername(username);
			loginForm.setPassword(password);
			// 登录并返回登录用户
			R<?> loginRet = loginService.login(loginForm, "magic-api");
			if (R.FAIL == loginRet.getCode())
			{
				throw new ServiceException(loginRet.getMsg());
			}
			LoginUser loginUser = tokenService.getLoginUser(((Map<String, Object>)loginRet.getData()).get("access_token").toString());
			//tokenService.createToken(loginUser);
			return new MagicUser(loginUser.getUserid().toString(), loginUser.getUsername(), loginUser.getToken());
		}catch (Exception ex){
			throw new MagicLoginException("账号或密码错误！");
		}
	}

	/**
	 * 根据Token获取用户信息
	 */
	@Override
	public MagicUser getUserByToken(String token) throws MagicLoginException {
		try{
			LoginUser loginUser = tokenService.getLoginUser(token);
			if(loginUser == null){
				throw new MagicLoginException("TOKEN无效，请重新登录！");
			}
			return new MagicUser(loginUser.getUserid().toString(), loginUser.getUsername(), token);
		}catch (Exception ex){
			throw new MagicLoginException("TOKEN无效，请重新登录！");
		}
	}

	/**
	 * 是否允许访问
	 * @param magicUser	用户信息
	 * @return
	 */
	@Override
	public boolean allowVisit(MagicUser magicUser, HttpServletRequest request, Authorization authorization) {
		// Authorization.SAVE 保存
		// Authorization.DELETE 删除
		// Authorization.VIEW 查询
		// Authorization.DOWNLOAD 导出
		// Authorization.UPLOAD 上传
		// Authorization.PUSH 推送
		if(authorization == Authorization.DELETE || authorization == Authorization.UPLOAD){
			// 禁止上传和删除
			return false;
		}
		return true;
	}

	/**
	 * 是否拥有对该接口的增删改权限
	 * 此方法可以不重写，则走默认的 boolean allowVisit(MagicUser magicUser, HttpServletRequest request, Authorization authorization) 方法
	 */
	@Override
	public boolean allowVisit(MagicUser magicUser, HttpServletRequest request, Authorization authorization, ApiInfo apiInfo) {
		if(authorization == Authorization.SAVE){
			LoginUser loginUser = tokenService.getLoginUser(request);
			/*MagicUserFile entity = new MagicUserFile();
			entity.setUserId(loginUser.getUserId());
			entity.setFilePath(apiInfo.getPath());
			entity.setOwner(1);
			entity.setEditable(1);

			magicUserFileService.insertMagicUserFile(entity);*/
		}

		// Authorization.SAVE 保存
		// Authorization.DELETE 删除
		// Authorization.VIEW 查询
		// 自行写逻辑判断是否拥有如果有，则返回true，反之为false
		return this.allowVisit(magicUser, request, authorization);
	}

	/**
	 * 是否拥有对该函数的增删改权限
	 * 此方法可以不重写，则走默认的 boolean allowVisit(MagicUser magicUser, HttpServletRequest request, Authorization authorization) 方法
	 */
	@Override
	public boolean allowVisit(MagicUser magicUser, HttpServletRequest request, Authorization authorization, FunctionInfo functionInfo) {
		// Authorization.SAVE 保存
		// Authorization.DELETE 删除
		// Authorization.VIEW 查询
		// 自行写逻辑判断是否拥有如果有，则返回true，反之为false
		return this.allowVisit(magicUser, request, authorization);
	}

	/**
	 * 是否拥有对该分组的增删改权限
	 * 此方法可以不重写，则走默认的 boolean allowVisit(MagicUser magicUser, HttpServletRequest request, Authorization authorization) 方法
	 */
	@Override
	public boolean allowVisit(MagicUser magicUser, HttpServletRequest request, Authorization authorization, Group group) {
		// Authorization.SAVE 保存
		// Authorization.DELETE 删除
		// Authorization.VIEW 查询
		// 自行写逻辑判断是否拥有如果有，则返回true，反之为false
		return this.allowVisit(magicUser, request, authorization);
	}

	/**
	 * 是否拥有对该数据源的增删改权限
	 * 此方法可以不重写，则走默认的 boolean allowVisit(MagicUser magicUser, HttpServletRequest request, Authorization authorization) 方法
	 */
	@Override
	public boolean allowVisit(MagicUser magicUser, HttpServletRequest request, Authorization authorization, DataSourceInfo dataSourceInfo) {
		// Authorization.SAVE 保存
		// Authorization.DELETE 删除
		// Authorization.VIEW 查询
		// 自行写逻辑判断是否拥有如果有，则返回true，反之为false
		return this.allowVisit(magicUser, request, authorization);
	}
}
