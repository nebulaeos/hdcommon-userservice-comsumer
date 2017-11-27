package com.holdtime.userservice.consumer.fegin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import com.holdtime.framework.utils.common.ApiResponseModel;
import com.holdtime.framework.utils.common.ApiResponseModel.ApiResponseResult;
import com.holdtime.userservice.model.TbUser;

import feign.hystrix.FallbackFactory;
@FeignClient(name = "hdcommon-userservice-provider",fallbackFactory=FeginClientFallBackFactory.class)
public interface UserFeginClient {
	@GetMapping("queryUserList")
	public ApiResponseModel<List<TbUser>> queryUserList();
}
@Component
class FeginClientFallBackFactory implements FallbackFactory<UserFeginClient>{
	
	private static final Logger logger = LoggerFactory.getLogger(FeginClientFallBackFactory.class);

	@Override
	public UserFeginClient create(Throwable arg0) {
		// TODO Auto-generated method stub
		return new UserFeginClient() {
			
			@Override
			public ApiResponseModel<List<TbUser>> queryUserList() {
				// TODO Auto-generated method stub
				logger.info("fallback;reson:"+arg0);
				ApiResponseModel<List<TbUser>> apiResponseModel = new ApiResponseModel<List<TbUser>>();
				apiResponseModel.error(ApiResponseResult.ERROR.getCode(),"服务异常");
				return apiResponseModel;
			}
		};
	}
	
}
