<template>
  <div class="login clearfix">
    <div class="login-wrap">
      <el-form ref="loginForm" :model="user" status-icon label-width="80px">
      <el-row type="flex" justify="center" :gutter="20">
        <el-col :span="10">
          <h3>添加用户</h3>
          <hr>
          <el-form-item label="用户名称" >
            <el-input v-model="user.name" placeholder="请输入名称"></el-input>
          </el-form-item>
          <el-form-item label="用户密码">
            <el-input v-model="user.password" placeholder="请输入密码" show-password></el-input>
          </el-form-item>
          <el-form-item label="用户邮箱" >
            <el-input v-model="user.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
          <el-form-item label="用户姓名" >
            <el-input v-model="user.nickname" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="用户角色" >
            <el-checkbox-group v-model="user.role"  @change="customChange">
              <el-checkbox
                v-for="(item,index) in roles"
                :key="item.id+'-'+item.roleName"
                :label="item.roleName"
                :value="item.id"
              >
              </el-checkbox>
            </el-checkbox-group>
          </el-form-item>
          <el-form-item label="用户状态" style="text-align: left">
              <el-switch
              v-model="user.enabled"
              active-text="启用"
              active-color="#13ce66"
              inactive-text="禁用"
              style="font-size: 12px;">
            </el-switch>
          </el-form-item>
        </el-col>


          <el-form-item style="padding-top: 5%;">
            <el-upload
              class="avatar-uploader"
              action="userIconUpload"
              ref="upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload"
              id="uploadIcon"
              :http-request="uploadIcon"
            >
              <el-tooltip class="item" effect="dark" content="上传头像" placement="bottom-start">
              <img v-if="file" :src="user.icon" class="avatar" >

              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-tooltip>

            </el-upload>
          </el-form-item>

      </el-row>
        <el-row type="flex" justify="center" :gutter="20">
          <el-col :span="10" :pull="3">
            <el-form-item>
              <el-button type="primary" @click="onSubmit" :disabled="!isAdmin">添加用户</el-button>
            </el-form-item>
          </el-col>

      </el-row>
      </el-form>
    </div>
  </div>
</template>

<script>
  import {isNotNullORBlank} from '../utils/utils'
  import {getRequest, postRequest, uploadFileRequest} from '../utils/api'
    export default {
        data() {
            return {
                user: {
                    name: '',
                    password: '',
                    email: '',
                    nickname: '',
                    role: [],
                    enabled: true,
                    icon: '',
                },
              roles: [],
              file: '',
              isAdmin: false
            }
        },
      mounted: function () {
        this.loadRoleList();
        console.log(this.isAdmin)
      },
        methods: {
          handleAvatarSuccess(res, file) {
            this.user.icon = URL.createObjectURL(file.raw);
          },
          beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;
            if (!isJPG) {
              this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if
            (!isLt2M) {
              this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
          },
          uploadIcon(data) {
            this.file = data.file;
            console.log(this.file);
          },

          customChange(item){
            console.log(item);
          },
          onSubmit() {
            console.log(this.user);
            if (!(isNotNullORBlank(this.user.name, this.user.password, this.user.email, this.user.nickname,
              this.user.enabled, this.user.role, this.file))) {
              this.$message({type: 'error', message: '数据不能为空!'});
              return;
            }
            if (this.user.email != "") {
              var reg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
              if (!reg.test(this.user.email)) {
                this.$message.error("请输入有效的邮箱！");
                return;
              }
            }

            var _this = this;
            var file = _this.file;
            var formdata = new FormData();
            formdata.append('image', file);
            uploadFileRequest("/userIconUpload", formdata).then(resp => {
              var json = resp.data;
              if (json.status == 'success') {
                _this.user.icon = json.msg;
                postRequest("admin/user/register", {
                  userName: _this.user.name,
                  password: _this.user.password,
                  email: _this.user.email,
                  nickname: _this.user.nickname,
                  roles: _this.user.role,
                  enabled: _this.user.enabled,
                  icon: _this.user.icon,
                }).then(resp => {
                    _this.$message({type: resp.data.status, message: resp.data.msg});
                })
              } else {
                this.$message({type: json.status, message: json.msg});
              }
            });
          },
          loadRoleList() {
            var _this = this;

            getRequest("/admin/roles").then(resp => {
              if (resp.status == 200) {
                _this.roles = resp.data;
                console.log(_this.roles);
                _this.isAdmin = true;

              } else {
                _this.isAdmin = false;
                _this.$message({type: 'error', message: '数据加载失败!'});
              }
            }, resp => {
              if (resp.response.status == 403) {
                var data = resp.response.data;
                _this.isAdmin = false;
                _this.$message({type: 'error', message: data});
              }
            });
          }
        }


    }
</script>

<style>
  #uploadIcon .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
    right: 55px;

  }

  #uploadIcon .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    left: 5px;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
    .home_container {
        height: 100%;
        position: absolute;
        top: 0px;
        left: 0px;
        width: 100%;
    }

    .el-header {
        background-color: #DEB887;
        color: #333;
        text-align: center;
        display: flex;
        align-items: center;
        justify-content: space-between;
    }

    .el-aside {
        background-color: #ECECEC;
    }

    .el-main {
        background-color: #fff;
        color: #000;
        text-align: center;
    }

    .home_title {
        color: #fff;
        font-size: 22px;
        display: inline;
    }

    .home_userinfo {
        color: #fff;
        cursor: pointer;
    }

    .home_userinfoContainer {
        display: inline;
        margin-right: 20px;
    }

    .homeWelcome {
        text-align: center;
        font-size: 30px;
        font-family: 华文行楷;
        color: #DEB887;
        padding-top: 50px;
    }
</style>
