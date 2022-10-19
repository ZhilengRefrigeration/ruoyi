package com.ruoyi.system.api.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value="微信小程序生成二维码")
@Setter
@Getter
public class WxAppletsCodeVo {
    @ApiModelProperty(value="最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）",required=false)
    public String scene;
    @ApiModelProperty(value="必须是已经发布的小程序存在的页面（否则报错），例如 pages/index/index, 根路径前不要填加 /,不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面",required=false)
    public String page;
    @ApiModelProperty(value="二维码的宽度，单位 px，最小 280px，最大 1280px",required=false)
    public Integer width;
    @ApiModelProperty(value="自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false",required=false)
    public Boolean autoColor;
    @ApiModelProperty(value="auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {\"r\":\"xxx\",\"g\":\"xxx\",\"b\":\"xxx\"} 十进制表示",required=false)
    public Object lineColor;
    @ApiModelProperty(value="是否需要透明底色，为 true 时，生成透明底色的小程序",required=false)
    public Boolean isHyaline;
    @ApiModelProperty(value="返回的base64的图片字符串",required=false)
    public String base64;
    @ApiModelProperty(value="二维码存放url",required=false)
    public String codeImgUrl;
    @ApiModelProperty(value="返回的base64",required=false)
    public byte[] bytesBase64;
}
