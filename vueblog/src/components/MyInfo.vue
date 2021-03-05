<template>

    <el-card class="box-card">
      <div slot="header" style="text-align: center;">
        <span>个人中心</span>

        <el-popover
          placement=""
          width="400"
          trigger="click" >
          <div slot="header" class="clearfix">
            <span>基本资料</span>
          </div>
          <div>
            <el-form label-width="80px"  size="small" label-position="right" :model="UpdateUserInfoPARM">
              <el-form-item label="用户别名">
                <el-input  placeholder="请输入新别名"  v-model="UpdateUserInfoPARM.inputNickName"></el-input>
              </el-form-item>
              <el-form-item label="用户邮箱">
                <el-input  placeholder="请输入新邮箱"  v-model="UpdateUserInfoPARM.inputEmail"></el-input>
              </el-form-item>
              <el-form-item label="原密码">
                <el-input placeholder="请输入原密码" v-model="UpdateUserInfoPARM.oldPassword" show-password></el-input>
              </el-form-item>
              <el-form-item label="新密码">
                <el-input placeholder="请输入新密码" v-model="UpdateUserInfoPARM.newPassword" show-password></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button size="mini" type="primary"  @click="onSubmit" round>提交</el-button>
              <el-button type="warning" size="mini" @click="onReset" round>撤销</el-button>
            </div>
          </div>
          <el-button style="float: right; padding: 3px 0;" type="text" icon="el-icon-setting" size="medium" slot="reference" >
          </el-button>
        </el-popover>


      </div>
      <el-row :gutter="20">
        <el-col :span="17">
          <div class="personal-relation">
            <i class="fa fa-address-card-o" aria-hidden="true"></i>
            <div class="relation-item">账号名称: </div>
            <div style="float: right; padding-right:20px;"> {{user.username}}</div>
          </div>
          <div class="personal-relation">
            <i class="fa fa-address-card" aria-hidden="true"></i>
            <div class="relation-item">用户别名: </div>
            <div style="float: right; padding-right:20px;"> {{user.nickname}}</div>
          </div>
          <div class="personal-relation">
            <li class="fa fa-clock-o"></li>
            <div class="relation-item">注册时间：</div>
            <div style="float: right;">{{user.registerDate | formatDateTime}}</div>
          </div>
          <div class="personal-relation">
            <i class="fa fa-envelope" aria-hidden="true"></i>
            <div class="relation-item">用户邮箱: </div>
            <div style="float: right; padding-right:20px;"> {{user.email}}</div>
          </div>
          <div class="personal-relation" >
            <i class="fa fa-users" aria-hidden="true"></i>
            <div class="relation-item">用户角色:</div>
            <div style="float: right; padding-right:20px;">
              <el-tag
                v-for="role in user.roles"
                :key="role.id"
                size="mini"
                style="margin-right: 8px"
                type="success">
                {{role.roleName}}
              </el-tag>
            </div>
          </div>
        </el-col>
        <el-col :span="6" >
<!--          <div class="demo-type" style="margin-top: 45%">-->
<!--            <el-avatar :src='user.icon' :size="125" ></el-avatar>-->
<!--          </div>-->

          <el-upload
            class="avatar-uploader"
            :show-file-list="false"
            action="userIconUpload"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :http-request="userIconUpload"
            >
            <el-tooltip class="item" effect="dark" content="点我修改头像" placement="bottom-start">
              <img v-if="user.icon" :src="user.icon" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-tooltip>

          </el-upload>
        </el-col>
      </el-row>


    </el-card>

</template>

<script>
  import {getRequest, postRequest, uploadFileRequest} from "../utils/api";
  import {isNotNullORBlank} from "../utils/utils";

  export default {
    data(){
      return{
        user: {
          username:"xxx",
          nickName: 'ssss',
          email: '9329@qq.com',
          registerDate: '2017-12-21T13:30:29',
          icon: 'http://m.imeitou.com/uploads/allimg/2021013115/dyjcgmnhbng.jpg',
          roles: [],
        },
        UpdateUserInfoPARM: {
          inputNickName: "",
          inputEmail: "",
          oldPassword: "",
          newPassword: ""
        }
      }
    },
    mounted: function () {
      this.loaderUser();

    },
    methods: {
      onSubmit(){
        var data = this.UpdateUserInfoPARM;
        // 检查邮箱是否符合格式
        if(data.inputEmail != ""){
          var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
          if (!reg.test(data.inputEmail)) {
            this.$message.error("请输入有效的邮箱！");
          }
        }
        // 检查密码是否输入
        if (data.oldPassword == "") {
          this.$message({type: 'error', message: '旧密码不能为空'});
        } else {
          // 判断所有数据是否为空
          if (data.inputNickName == ""
            && data.inputEmail == ""
            && data.newPassword == "") {
            this.$message({type: 'error', message: '您没有更新任何一项数据'});
          } else {
            console.log(data);
            postRequest("/updateUserInfo", data).then(resp => {
              console.log(resp);
              if (resp.data.status == 'success') {
                // 更新密码
                this.$message({type: resp.data.status, message: resp.data.msg});
                if (data.newPassword != "") {
                  getRequest("/logout");
                  this.$router.replace({path: '/'});
                } else {
                  // 如果有更新用户就更新
                  if (data.inputNickName != "") {
                    this.$root.currentUserNickname = data.inputNickName;
                  }
                  this.loaderUser();
                }
              } else {
                this.$message({type: resp.data.status, message: resp.data.msg});;
              }
            })
          }
        }

      },
      onReset(){
        this.UpdateUserInfoPARM.oldPassword = "";
        this.UpdateUserInfoPARM.newPassword = "";
        this.UpdateUserInfoPARM.inputEmail = "";
        this.UpdateUserInfoPARM.inputNickName = "";
      },
      loaderUser(){
        getRequest("/userInfo").then(resp=> {
          if (resp.status == 200) {
            this.user = resp.data;
          } else {
            this.$message({type: 'error', message: '数据加载失败!'});
          }
        }, resp=> {
          loading = false;
          if (resp.response.status == 403) {
            this.$message({type: 'error', message: resp.response.data});
          } else {
            this.$message({type: 'error', message: '数据加载失败!'});
          }
        }).catch(resp=> {
          //压根没见到服务器
          this.$message({type: 'error', message: '数据加载失败!'});
        })
      },

      userIconUpload(data){
        var file = data.file;
        var formdata = new FormData();
        formdata.append('image', file);
        uploadFileRequest("/userIconUpload", formdata).then(resp=> {
          var json = resp.data;
          console.log(resp)
          if (json.status == 'success') {
//            _this.$refs.md.$imgUpdateByUrl(pos, json.msg)
//             this.$refs.md.$imglst2Url([[pos, json.msg]])
            this.$message({type: json.status, message: "更新成功"});
            this.user.icon = json.msg;
          } else {
            this.$message({type: json.status, message: json.msg});
          }
        });
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }

    }

  }
</script>

<style lang="scss" scoped>

  //卡片样式
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }



  .box-card {
    width: 100%;
    margin-top: 15px;
  }
  //文本样式区
  .name-role {
    font-size: 16px;
    padding: 5px;
    text-align:center;
  }
  .sender{
    text-align:center;
  }
  .registe-info{
    text-align: center;
    padding-top:10px;
  }
  .personal-relation {
    font-size: 16px;

    margin-right: 1px;
    width: 100%;
    display: flex;
    align-items: center;
  }

  .relation-item {
    padding: 12px;

  }
  .dialog-footer{
    padding-left: 15%;
  }
  //布局样式区
  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }

  .box-card {
    width: 550px;
  }


  .el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    margin-top: 33%;
    font-size: 28px;
    color: #8c939d;
    width: 125px;
    height: 125px;
    line-height: 125px;
    text-align: center;
  }
  .avatar {
    margin-top: 33%;
    width: 125px;
    height: 125px;
    display: block;
    border-radius:50%;
  }

</style>
