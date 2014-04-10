﻿package  com.ItemModel{		import flash.display.Sprite;    import flash.display.Bitmap;	import flash.display.BitmapData;	import flash.events.Event;	import flash.display.Stage;	import com.events.ChangeWeaponEvent		public class Weapon extends ItemModel{				//Weapon Image		private var imageSprite:Sprite;		private var libraryImage:Bitmap;		private var weaponName:BitmapData;				//Weapon Data		private var damage:Number;		private var critChance:Number;				public function Weapon() {			super(false);									this.addEventListener(Event.ADDED_TO_STAGE,onAddedToStage,false,0,true);						libraryImage = new Bitmap();			imageSprite = new Sprite();//create an image container        	addChild(imageSprite);//add it to the display list			//displayImageFromLibrary("mace");        			}		private function onAddedToStage(evt:Event):void		{			trace("Added!");			this.removeEventListener(Event.ADDED_TO_STAGE,onAddedToStage);			stage.addEventListener(ChangeWeaponEvent.CHANGE,changeWeaponEvent);		}		private function changeWeaponEvent(evt:ChangeWeaponEvent):void		{			weaponName = evt.getWeaponName();			displayImageFromLibrary(weaponName);		}	    public function displayImageFromLibrary(weaponName:BitmapData):void    	{               //instantiate the bitmap in the library by its identifier       	 	 libraryImage.bitmapData = weaponName;			 libraryImage.scaleX = libraryImage.scaleY = 1;               //place the image in the image container						imageSprite.y = -libraryImage.height;			imageSprite.x = -(libraryImage.width*.5);			//imageSprite.x = this.x;        	imageSprite.addChild(libraryImage);			//trace(parent.getChildAt(2).);    	}	}	}