package com.exyui.androidtoolkit.components

import android.app.Activity
import android.app.TaskStackBuilder
import android.app.assist.AssistContent
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.*
import androidx.appcompat.view.ActionMode
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class ActivityObservable: AppCompatActivity() {
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return super.onContextItemSelected(item)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onProvideAssistData(data: Bundle?) {
        super.onProvideAssistData(data)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun onPreparePanel(featureId: Int, view: View?, menu: Menu?): Boolean {
        return super.onPreparePanel(featureId, view, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onActionModeFinished(mode: android.view.ActionMode?) {
        super.onActionModeFinished(mode)
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        super.onMultiWindowModeChanged(isInMultiWindowMode)
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean, newConfig: Configuration?) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig)
    }

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
    }

    override fun onKeyShortcut(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyShortcut(keyCode, event)
    }

    override fun onSupportActionModeStarted(mode: ActionMode) {
        super.onSupportActionModeStarted(mode)
    }

    override fun onCreateNavigateUpTaskStack(builder: TaskStackBuilder?) {
        super.onCreateNavigateUpTaskStack(builder)
    }

    override fun onContextMenuClosed(menu: Menu?) {
        super.onContextMenuClosed(menu)
    }

    override fun onNavigateUp(): Boolean {
        return super.onNavigateUp()
    }

    override fun onPointerCaptureChanged(hasCapture: Boolean) {
        super.onPointerCaptureChanged(hasCapture)
    }

    override fun onCreateDescription(): CharSequence? {
        return super.onCreateDescription()
    }

    override fun onPostResume() {
        super.onPostResume()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    override fun onProvideKeyboardShortcuts(data: MutableList<KeyboardShortcutGroup>?, menu: Menu?, deviceId: Int) {
        super.onProvideKeyboardShortcuts(data, menu, deviceId)
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
    }

    override fun onActionModeStarted(mode: android.view.ActionMode?) {
        super.onActionModeStarted(mode)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
    }

    override fun onKeyMultiple(keyCode: Int, repeatCount: Int, event: KeyEvent?): Boolean {
        return super.onKeyMultiple(keyCode, repeatCount, event)
    }

    override fun onMenuOpened(featureId: Int, menu: Menu?): Boolean {
        return super.onMenuOpened(featureId, menu)
    }

    override fun onPanelClosed(featureId: Int, menu: Menu?) {
        super.onPanelClosed(featureId, menu)
    }

    override fun onSupportActionModeFinished(mode: ActionMode) {
        super.onSupportActionModeFinished(mode)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyLongPress(keyCode, event)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onLocalVoiceInteractionStopped() {
        super.onLocalVoiceInteractionStopped()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    override fun onContentChanged() {
        super.onContentChanged()
    }

    override fun onSearchRequested(searchEvent: SearchEvent?): Boolean {
        return super.onSearchRequested(searchEvent)
    }

    override fun onSearchRequested(): Boolean {
        return super.onSearchRequested()
    }

    override fun onNavigateUpFromChild(child: Activity?): Boolean {
        return super.onNavigateUpFromChild(child)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onWindowStartingSupportActionMode(callback: ActionMode.Callback): ActionMode? {
        return super.onWindowStartingSupportActionMode(callback)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
        return super.onGenericMotionEvent(event)
    }

    override fun onProvideAssistContent(outContent: AssistContent?) {
        super.onProvideAssistContent(outContent)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any {
        return super.onRetainCustomNonConfigurationInstance()
    }

    override fun onOptionsMenuClosed(menu: Menu?) {
        super.onOptionsMenuClosed(menu)
    }

    override fun onWindowAttributesChanged(params: WindowManager.LayoutParams?) {
        super.onWindowAttributesChanged(params)
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onTitleChanged(title: CharSequence?, color: Int) {
        super.onTitleChanged(title, color)
    }

    override fun onProvideReferrer(): Uri {
        return super.onProvideReferrer()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onChildTitleChanged(childActivity: Activity?, title: CharSequence?) {
        super.onChildTitleChanged(childActivity, title)
    }

    override fun onStateNotSaved() {
        super.onStateNotSaved()
    }

    override fun onCreateView(parent: View?, name: String?, context: Context?, attrs: AttributeSet?): View {
        return super.onCreateView(parent, name, context, attrs)
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
        return super.onCreateView(name, context, attrs)
    }

    override fun onCreateSupportNavigateUpTaskStack(builder: androidx.core.app.TaskStackBuilder) {
        super.onCreateSupportNavigateUpTaskStack(builder)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
    }

    override fun onApplyThemeResource(theme: Resources.Theme?, resid: Int, first: Boolean) {
        super.onApplyThemeResource(theme, resid, first)
    }

    override fun onLocalVoiceInteractionStarted() {
        super.onLocalVoiceInteractionStarted()
    }

    override fun onTrackballEvent(event: MotionEvent?): Boolean {
        return super.onTrackballEvent(event)
    }

    override fun onPrepareSupportNavigateUpTaskStack(builder: androidx.core.app.TaskStackBuilder) {
        super.onPrepareSupportNavigateUpTaskStack(builder)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    override fun onCreatePanelView(featureId: Int): View? {
        return super.onCreatePanelView(featureId)
    }

    override fun onPrepareNavigateUpTaskStack(builder: TaskStackBuilder?) {
        super.onPrepareNavigateUpTaskStack(builder)
    }

    override fun onCreatePanelMenu(featureId: Int, menu: Menu?): Boolean {
        return super.onCreatePanelMenu(featureId, menu)
    }

    override fun onWindowStartingActionMode(callback: android.view.ActionMode.Callback?): android.view.ActionMode? {
        return super.onWindowStartingActionMode(callback)
    }

    override fun onWindowStartingActionMode(callback: android.view.ActionMode.Callback?, type: Int): android.view.ActionMode? {
        return super.onWindowStartingActionMode(callback, type)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration?) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }
}