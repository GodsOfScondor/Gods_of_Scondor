#version 330

in vec2 coords;

out vec4 out_color;

uniform sampler2D font;
uniform vec3 color;
uniform float transparency;

void main(void){
	
	out_color = vec4(color.xyz, min(transparency, texture(font, coords).a));
	
}