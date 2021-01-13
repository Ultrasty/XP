<template>
  <div class="account-settings-info-view">
    <a-row :gutter="16">
      <a-col :md="24" :lg="16">

        <a-form :form="form">
          <a-form-item
            label="昵称"
          >
            <a-input
              size="large"
              type="text"
              placeholder="请输入昵称"
              v-decorator="['userNickname', {rules: [{ required: true }], validateTrigger: ['change', 'blur'], initialValue: InfoDetail.name}]"
            ></a-input>
          </a-form-item>

          <a-form-item
            label="个性签名"
          >
            <a-input
              size="large"
              type="text"
              placeholder="请输入个性签名"
              v-decorator="['userDescription', {rules: [{ required: true }], validateTrigger: ['change', 'blur'], initialValue: InfoDetail.welcome}]"
            ></a-input>
          </a-form-item>

          <a-form-item
            label="电子邮件"
          >
            <a-input
              size="large"
              type="text"
              placeholder="请输入电子邮件"
              v-decorator="['userEmail', {rules: [{ required: true }], validateTrigger: ['change', 'blur'], initialValue: InfoDetail.email}]"
            ></a-input>
          </a-form-item>

          <a-form-item
            label="登录密码"
            :required="false"
          >
            <a-input
              size="large"
              type="text"
              placeholder="请输入新密码"
              v-decorator="['userPassword', {rules: [{ required: false }], validateTrigger: ['change', 'blur'], initialValue: InfoDetail.password}]"
            ></a-input>
          </a-form-item>

          <a-form-item
            label="头像"
          >
            <div id="summernote-avatar-create"></div>
          </a-form-item>

          <a-form-item>
            <a-button type="primary" @click="HandleSubmit">提交</a-button>
          </a-form-item>
        </a-form>

      </a-col>
      <a-col :md="24" :lg="8" :style="{ minHeight: '180px' }">
        <div class="ant-upload-preview" >
          <img id="pic" width="50px" height="60px"/>
        </div>
      </a-col>

    </a-row>
  </div>
</template>

<script>
import AvatarModal from './AvatarModal'
import '../../../plugins/summernote'
import $ from 'jquery'
import { updateInfo, getInfo } from '../../../api/login'

export default {
  components: {
    AvatarModal
  },
  data () {
    return {
      // cropper
      userAvatar: undefined,
      preview: {},
      option: {
        img: '/avatar2.jpg',
        info: true,
        size: 1,
        outputType: 'jpeg',
        canScale: false,
        autoCrop: true,
        // 只有自动截图开启 宽度高度才生效
        autoCropWidth: 180,
        autoCropHeight: 180,
        fixedBox: true,
        // 开启宽度和高度比例
        fixed: true,
        fixedNumber: [1, 1]
      },
      InfoDetail: {},
      form: this.$form.createForm(this)
    }
  },
  updated () {
    this.initSummernote()
  },
  mounted () {
    getInfo()
      .then(res => {
        if (res.code === 0) {
          this.InfoDetail = res.data
          document.getElementById('pic').src = this.InfoDetail.avatar
          return res.data
        } else {
          this.$notification.error({
            message: '获取个人信息失败',
            description: res.message
          })
        }
      })
  },
  methods: {
    initSummernote () {
      console.log('初始化富文本插件')
      $('#summernote-avatar-create').summernote({
        lang: 'zh-CN',
        placeholder: '粘贴截图到这即可，图片最好不要大于80*80',
        height: 200,
        width: 600,
        htmlMode: true,
        toolbar: [],
        fontSizes: ['8', '9', '10', '11', '12', '14', '18', '24', '36'],
        fontNames: [
          'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New',
          'Helvetica Neue', 'Helvetica', 'Impact', 'Lucida Grande',
          'Tahoma', 'Times New Roman', 'Verdana'
        ]
      })
    },
    HandleSubmit () {
      const { form: { validateFields } } = this
      validateFields((errors, values) => {
        // values.userAvatar = $('#summernote-avatar-create').summernote('code')
        if (values.userPassword === '') {
          values.userPassword = null
        }

        values.userAvatar = $('#summernote-avatar-create').summernote('code')
        if (values.userAvatar === '<p><br></p>') {
          values.userAvatar = '<p><img src="' + this.InfoDetail.avatar + ' " data-filename="image.png" style="width: 15px;"><br></p>'
        }
        console.log('提交数据到后端')
        console.log(values.userAvatar)
        if (!errors) {
          updateInfo(values).then(res => {
            if (res === 'ok') {
              this.$notification.success({
                message: '修改成功',
                description: '修改个人信息成功'
              })

              this.visible = false
              this.$emit = ('ok')
            }
          }).catch(err => {
            this.$notification.error({
              message: '修改失败',
              description: err.message
            })
          })
        }
      })
    }
  }
}
</script>

<style lang="less" scoped>

  .avatar-upload-wrapper {
    height: 200px;
    width: 100%;
  }

  .ant-upload-preview {
    position: relative;
    margin: 0 auto;
    width: 100%;
    max-width: 180px;
    border-radius: 50%;
    box-shadow: 0 0 4px #ccc;

    img, .mask {
      width: 100%;
      max-width: 180px;
      height: 100%;
      border-radius: 50%;
      overflow: hidden;
    }
  }
</style>
