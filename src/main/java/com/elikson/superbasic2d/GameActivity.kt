package com.elikson.superbasic2d

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Rect
import android.opengl.GLSurfaceView
import android.os.Bundle
import com.twicecircled.spritebatcher.Drawer
import com.twicecircled.spritebatcher.SpriteBatcher
import javax.microedition.khronos.opengles.GL10

class GameActivity() : Activity(), Drawer {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val surfaceView: GLSurfaceView = getSurfaceView()
        setContentView(surfaceView)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    fun getSurfaceView(): GLSurfaceView {
        val surfaceView = GLSurfaceView(this)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        return surfaceView
    }

    fun drawing() {

    }

    fun draw(sb: SpriteBatcher, objectGames: List<ObjectGame>) {
        //Desenhando o que foi declarado
        for (objectGame in objectGames) {
            if (objectGame.getTag().contains("text")) {
                sb.drawText(
                    objectGame.getSource_id(),
                    objectGame.getText(),
                    objectGame.getX(),
                    objectGame.getY(),
                    objectGame.getScale()
                )
            } else {
                sb.draw(
                    objectGame.getSource_id(),
                    Rect(0, 0, objectGame.getOriginal_width(), objectGame.getOriginal_height()),
                    objectGame.getX(),
                    objectGame.getY(),
                    Rect(0, 0, objectGame.getWidth(), objectGame.getHeight()),
                    0,
                    1.0f
                )
            }
        }
        //
    }


    override fun onDrawFrame(gl10: GL10, spriteBatcher: SpriteBatcher?) {
        //Tamanho da tala

        //Tamanho da tala
        val viewWidth = spriteBatcher?.viewWidth
        val viewHeight = spriteBatcher?.viewHeight

        //Calcula a escala X referente a largura da tela de exibição do jogo

        //Calcula a escala X referente a largura da tela de exibição do jogo
        val scaleX: Float = (viewWidth?.toFloat() ?: 1000f) * 1.0f / Configs.Screen.SCREEN_WIDTH.toFloat()

        //Calcula a escala Y referente a altura da tela de exibição do jogo

        //Calcula a escala Y referente a altura da tela de exibição do jogo
        val scaleY: Float = (viewHeight?.toFloat() ?: 1000f) * 1.0f / Configs.Screen.SCREEN_HEIGHT.toFloat()

        //Redimensiona a tela de exibição de acordo com o tamanho da tela do dispositivo
        //baseado no cálculo das escalas X e Y

        //Redimensiona a tela de exibição de acordo com o tamanho da tela do dispositivo
        //baseado no cálculo das escalas X e Y
        gl10.glScalef(scaleX, scaleY, 1.0f)


        drawing()
    }
}